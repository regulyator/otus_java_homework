package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.core.model.User;
import ru.otus.core.service.DBService;
import ru.otus.core.service.DBServiceUser;
import ru.otus.webserver.server.UsersWebServer;
import ru.otus.webserver.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.webserver.services.TemplateProcessor;
import ru.otus.webserver.services.TemplateProcessorImpl;
import ru.otus.webserver.services.UserAuthService;
import ru.otus.webserver.services.UserAuthServiceImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityHW {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        DBWorkerUser dbWorker = new DBWorkerUser();
        DBServiceUser<User, Long> userService = dbWorker.initDBService();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, userService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
