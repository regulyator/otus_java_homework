package ru.otus.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.AbstractCachedDBService;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceException;

import java.util.Objects;
import java.util.Optional;

public class DbCachedServiceUser implements DBServiceUser<User, Long> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCachedDBService.class);

    private final UserDao<User, Long> dao;

    private final HwCache<Long, User> daoCache;

    public DbCachedServiceUser(UserDao<User, Long> dao,
                               HwCache<Long, User> daoCache) {
        this.dao = dao;
        this.daoCache = daoCache;
    }

    @Override
    public Long save(User entity) {
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
    public Optional<User> get(Long id) {
        User cachedValue = daoCache.get(id);
        if (Objects.nonNull(cachedValue)) {
            return Optional.of(cachedValue);
        } else {
            try (var sessionManager = dao.getSessionManager()) {
                sessionManager.beginSession();
                try {
                    Optional<User> entityOptional = dao.findById(id);

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

    @Override
    public Optional<User> getByUsername(String username) {

        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> entityOptional = dao.findByUsername(username);
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
