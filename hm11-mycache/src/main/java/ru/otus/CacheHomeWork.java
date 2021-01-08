package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.data.cachehw.HwCache;
import ru.otus.data.cachehw.MyCache;
import ru.otus.data.core.dao.Dao;
import ru.otus.data.core.model.AddressDataSet;
import ru.otus.data.core.model.PhoneDataSet;
import ru.otus.data.core.model.User;
import ru.otus.data.core.service.impl.DbCachedServiceUser;
import ru.otus.data.core.service.impl.DbServiceUser;
import ru.otus.data.hibernate.HibernateUtils;
import ru.otus.data.hibernate.dao.UserDaoHibernate;
import ru.otus.data.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;


public class CacheHomeWork {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final Logger logger = LoggerFactory.getLogger(CacheHomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CONFIG, User.class, AddressDataSet.class, PhoneDataSet.class);

        // Работа с пользователем
        try (var sessionManager = new SessionManagerHibernate(sessionFactory)) {
            //без кэша
            Dao<User, Long> userDao = new UserDaoHibernate(sessionManager);
            var dbServiceUserWoCache = new DbServiceUser(userDao);


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
