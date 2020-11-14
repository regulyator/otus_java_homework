package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.core.dao.Dao;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractCachedDBService<T, ID> implements DBService<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCachedDBService.class);

    private final Dao<T, ID> dao;

    private final HwCache<ID, T> daoCache;

    protected AbstractCachedDBService(Dao<T, ID> dao,
                                      HwCache<ID, T> daoCache) {
        this.dao = dao;
        this.daoCache = daoCache;
    }

    @Override
    public ID save(T entity) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var id = dao.insert(entity);
                sessionManager.commitSession();
                logger.info("created entity: {}", id);
                daoCache.put(id, entity);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> get(ID id) {
        T cachedValue = daoCache.get(id);
        if (Objects.nonNull(cachedValue)) {
            return Optional.of(cachedValue);
        } else {
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
}
