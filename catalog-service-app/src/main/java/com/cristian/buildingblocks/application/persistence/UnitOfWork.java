package com.cristian.buildingblocks.application.persistence;

import com.cristian.buildingblocks.domain.Event;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
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
    private UserTransaction transaction;

    private UnitOfWork() {
    }

    public static UnitOfWork instance() {
        return new UnitOfWork();
    }

    public UnitOfWork begin() {
        log.info("Starting unit of work ");
        this.id = UUID.randomUUID();

        try {
            transaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }

        return this;
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

    public boolean commit() {
        log.info("Unit of work committed;");
        try {
            transaction.commit();
            return true;
        } catch (RollbackException | HeuristicMixedException
                | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void rollback() {
        log.debug("Transaction rolled back!");
        try {
            transaction.rollback();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    private record ChangeEntry(Instant time, Event<?> entity) {
    }

}
