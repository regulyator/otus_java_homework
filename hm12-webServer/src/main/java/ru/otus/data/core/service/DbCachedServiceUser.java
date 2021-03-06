package ru.otus.data.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.data.cachehw.HwCache;
import ru.otus.data.core.dao.UserDao;
import ru.otus.data.core.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DbCachedServiceUser implements DBServiceUser {

    private static final Logger logger = LoggerFactory.getLogger(DbCachedServiceUser.class);

    private final UserDao<User, Long> dao;

    private final HwCache<String, User> daoCache;

    public DbCachedServiceUser(UserDao<User, Long> dao,
                               HwCache<String, User> daoCache) {
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
                daoCache.put(String.valueOf(id), entity);
                return id;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> get(Long id) {
        User cachedValue = daoCache.get(String.valueOf(id));
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

    @Override
    public List<User> getAllUsers() {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return dao.getAllUsers();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Collections.emptyList();
        }
    }
}
