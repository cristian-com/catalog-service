package com.cristian.buildingblocks.application.persistence;

import com.cristian.buildingblocks.domain.Entity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Slf4j
public class UnitOfWork {

    private UUID id;
    private LinkedHashMap<ChangeEntry, Operation> changes;

    private UnitOfWork() {
    }

    public static UnitOfWork instance() {
        return new UnitOfWork();
    }

    public UnitOfWork begin() {
        log.info("Starting unit of work ");
        this.id = UUID.randomUUID();
        return this;
    }

    public UnitOfWork add(Map<Entity<?>, Operation> operations) {
        Map<ChangeEntry, Operation> newEntries = operations.entrySet().stream()
                .collect(Collectors.toMap(operationEntry -> getOperationEntry(operationEntry.getKey()),
                        Map.Entry::getValue,
                        (conflictedEntityOperation1, conflictedEntityOperation2) ->
                        {
                            log.warn("It looks like something is wrong here my friend. Check it out!");
                            return conflictedEntityOperation2;
                        }
                ));

        this.changes.putAll(newEntries);

        return this;
    }

    private ChangeEntry getOperationEntry(Entity<?> entity) {
        return new ChangeEntry(entity.getVersion(), entity);
    }

    public boolean commit() {
        log.info("Unit of work committed;");
        return true;
    }

    private record ChangeEntry(Instant time, Entity<?> entity) {
    }

}
