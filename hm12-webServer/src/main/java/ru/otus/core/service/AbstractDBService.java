package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.Dao;

import java.util.Optional;

public abstract class AbstractDBService<T, ID> implements DBService<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDBService.class);

    private final Dao<T, ID> dao;

    protected AbstractDBService(Dao<T, ID> dao) {
        this.dao = dao;
    }

    @Override
    public ID save(T entity) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var id = dao.insert(entity);
                sessionManager.commitSession();
                logger.info("created entity: {}", id);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> get(ID id) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> entityOptional = dao.findById(id);

                logger.info("entity: {}", entityOptional.orElse(null));
                return entityOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
