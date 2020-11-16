package ru.otus.webserver.servlet;

import com.google.gson.Gson;
import ru.otus.data.core.model.User;
import ru.otus.data.core.service.DBServiceUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class UsersApiServlet extends HttpServlet {


    private final DBServiceUser dbServiceUser;
    private final Gson gson;

    public UsersApiServlet(DBServiceUser dbServiceUser, Gson gson) {
        this.dbServiceUser = dbServiceUser;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User receivedUser = gson.fromJson(req.getReader(), User.class);
        if (checkReceivedUserForCreation(receivedUser)) {
            dbServiceUser.save(receivedUser);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private boolean checkReceivedUserForCreation(User receivedUser) {
        return Objects.nonNull(receivedUser)
                && Objects.nonNull(receivedUser.getUsername())
                && Objects.nonNull(receivedUser.getPassword())
                && !receivedUser.getUsername().isEmpty()
                && !receivedUser.getPassword().isEmpty();
    }

}
