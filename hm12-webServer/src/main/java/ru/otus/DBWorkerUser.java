package ru.otus;

import org.hibernate.SessionFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.impl.DbCachedServiceUser;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

public class DBWorkerUser {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";

    public DBServiceUser<User, Long> initDBService() {
        SessionFactory sessionFactory = HibernateUtils
                .buildSessionFactory(HIBERNATE_CONFIG, ru.otus.core.model.User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao<User, Long> userDao = new UserDaoHibernate(sessionManager);
        HwCache<Long, User> userCache = new MyCache<>();
        DbCachedServiceUser dbServiceUserCache = new DbCachedServiceUser(userDao, userCache);

        initSampleUsers(dbServiceUserCache);

        return dbServiceUserCache;
    }

    private void initSampleUsers(DbCachedServiceUser dbServiceUserCache) {
        for (int i = 1; i <= 10; i++) {
            var user = new User("user" + i, "password", null, null);
            dbServiceUserCache.save(user);
        }
    }
}
