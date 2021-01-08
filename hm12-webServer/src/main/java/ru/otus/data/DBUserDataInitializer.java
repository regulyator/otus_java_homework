package ru.otus.data;

import ru.otus.data.core.model.User;
import ru.otus.data.core.service.DBServiceUser;

public class DBUserDataInitializer {

    public static void initSampleUsers(DBServiceUser dbServiceUser) {
        for (int i = 1; i <= 10; i++) {
            var user = new User("user" + i, "password", null, null);
            dbServiceUser.save(user);
        }
    }
}
