package ru.otus.webserver.servlet;

import com.google.gson.Gson;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class UsersApiServlet extends HttpServlet {
    private static final String USER_PARAMETER_NAME = "name";
    private static final String USER_PARAMETER_PASSWORD = "password";


    private final DBServiceUser<User, Long> dbServiceUser;
    private final Gson gson;

    public UsersApiServlet(DBServiceUser<User, Long> dbServiceUser, Gson gson) {
        this.dbServiceUser = dbServiceUser;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> requestParams = req.getParameterMap();
        if (Objects.nonNull(requestParams)
                && checkParamsForUserCreation(requestParams)) {
            String username = requestParams.get(USER_PARAMETER_NAME)[0];
            String password = requestParams.get(USER_PARAMETER_PASSWORD)[0];
            createUser(username, password);
            resp.sendRedirect("/users");
        }
    }

    private void createUser(String username, String password) {
        User user = new User(username, password, null, null);
        dbServiceUser.save(user);
    }

    private boolean checkParamsForUserCreation(Map<String, String[]> requestParams) {
        return requestParams.containsKey(USER_PARAMETER_NAME)
                && requestParams.containsKey(USER_PARAMETER_PASSWORD)
                && requestParams.get(USER_PARAMETER_NAME).length == 1
                && requestParams.get(USER_PARAMETER_PASSWORD).length == 1;
    }

}
