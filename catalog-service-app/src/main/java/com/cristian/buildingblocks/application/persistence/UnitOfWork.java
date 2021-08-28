package com.cristian.buildingblocks.application.persistence;

import com.cristian.buildingblocks.domain.Event;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.UserTransaction;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class UnitOfWork {

    private UUID id;
    private LinkedHashMap<ChangeEntry, Operation> changes = new LinkedHashMap<>();

    private final UserTransaction transaction;

    public boolean begin() {
        log.debug("Starting transaction.");
        this.id = UUID.randomUUID();

        try {
            transaction.begin();
            return true;
        } catch (Exception e) {
            log.error("There was an exception starting the transaction.", e);
            return false;
        }
    }

    public UnitOfWork add(Map<Event<?>, Operation> operations) {
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

    private ChangeEntry getOperationEntry(Event<?> entity) {
        return new ChangeEntry(entity.getTimestamp(), entity);
    }

    public boolean complete() {
        try {
            transaction.commit();
            log.debug("Transaction committed.");
            return true;
        } catch (Exception e) {
            log.debug("Transaction couldn't be committed.", e);
            return false;
        }
    }

    public void undo() {
        try {
            transaction.rollback();
            log.debug("Transaction rolled back!");
        } catch (Exception e) {
            log.error("There was an error committing the transaction.", e);
        }
    }

    private record ChangeEntry(Instant time, Event<?> entity) {
    }

}
