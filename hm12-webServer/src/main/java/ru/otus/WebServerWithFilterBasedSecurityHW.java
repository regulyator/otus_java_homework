package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import ru.otus.data.DBUserDataInitializer;
import ru.otus.data.cachehw.HwCache;
import ru.otus.data.cachehw.MyCache;
import ru.otus.data.core.dao.UserDao;
import ru.otus.data.core.model.AddressDataSet;
import ru.otus.data.core.model.PhoneDataSet;
import ru.otus.data.core.model.User;
import ru.otus.data.core.service.DBServiceUser;
import ru.otus.data.core.service.DbCachedServiceUser;
import ru.otus.data.hibernate.HibernateUtils;
import ru.otus.data.hibernate.dao.UserDaoHibernate;
import ru.otus.data.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.webserver.server.UsersWebServer;
import ru.otus.webserver.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.webserver.services.TemplateProcessor;
import ru.otus.webserver.services.TemplateProcessorImpl;
import ru.otus.webserver.services.UserAuthService;
import ru.otus.webserver.services.UserAuthServiceImpl;

/*
    // Стартовая страница
    http://localhost:8080

    Имя пользователя и паль для входа user1 (или любой user{1-10}:) password
*/
public class WebServerWithFilterBasedSecurityHW {
    public static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        DBServiceUser userService = initDBService();
        DBUserDataInitializer.initSampleUsers(userService);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, userService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static DBServiceUser initDBService() {
        SessionFactory sessionFactory = HibernateUtils
                .buildSessionFactory(HIBERNATE_CONFIG, ru.otus.data.core.model.User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao<User, Long> userDao = new UserDaoHibernate(sessionManager);
        HwCache<String, User> userCache = new MyCache<>();
        return new DbCachedServiceUser(userDao, userCache);
    }
}
