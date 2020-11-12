package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.dao.Dao;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.impl.DbCachedServiceUser;
import ru.otus.core.service.impl.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;


public class WebServerHomeWork {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final Logger logger = LoggerFactory.getLogger(CacheHomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, User.class, AddressDataSet.class, PhoneDataSet.class);

        // Работа с пользователем
        try (var sessionManager = new SessionManagerHibernate(sessionFactory)) {
            //без кэша
            UserDao<User, Long> userDao = new UserDaoHibernate(sessionManager);
            var dbServiceUserWoCache = new DbServiceUserImpl(userDao);


            List<Long> newUserIdsWoCache = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                var address = new AddressDataSet("sample street" + i);
                ArrayList<PhoneDataSet> phones = new ArrayList<>(List.of(
                        new PhoneDataSet("123456789" + i),
                        new PhoneDataSet("987654321" + i)
                ));
                var user = new User("sample user" + i, address, phones);

                newUserIdsWoCache.add(dbServiceUserWoCache.save(user));
            }

            long startWoCache = System.currentTimeMillis();
            newUserIdsWoCache.forEach(dbServiceUserWoCache::get);
            long endWoCache = System.currentTimeMillis();

            //с кэшом
            HwCache<Long, User> userCache = new MyCache<>();
            userCache.addListener((key, value, action) -> {
                logger.info("value {} action {}", value, action);
            });

            var dbServiceUserCache = new DbCachedServiceUser(userDao, userCache);

            List<Long> newUserIdsCache = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                var address = new AddressDataSet("sample street" + i);
                ArrayList<PhoneDataSet> phones = new ArrayList<>(List.of(
                        new PhoneDataSet("123456789" + i),
                        new PhoneDataSet("987654321" + i)
                ));
                var user = new User("sample user" + i, address, phones);

                newUserIdsCache.add(dbServiceUserCache.save(user));
            }

            long startCache = System.currentTimeMillis();
            newUserIdsCache.forEach(dbServiceUserCache::get);
            long endCache = System.currentTimeMillis();

            logger.info("-----------------------------------------------------");
            logger.info("Time work WITHOUT cache:{}", endWoCache - startWoCache);
            logger.info("Time work WITH cache:{}", endCache - startCache);
        }
    }
}
