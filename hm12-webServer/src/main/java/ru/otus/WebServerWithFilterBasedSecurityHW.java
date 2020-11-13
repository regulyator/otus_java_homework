package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
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
