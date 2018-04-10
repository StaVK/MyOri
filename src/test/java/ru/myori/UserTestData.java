package ru.myori;


import ru.myori.matcher.BeanMatcher;
import ru.myori.model.Role;
import ru.myori.model.User;
import ru.myori.web.json.JsonUtil;

import java.util.Objects;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ+11;
    public static final int USER11_ID = START_SEQ+12;
    public static final int USER21_ID = START_SEQ+13;


    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User2", "user2@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER11 = new User(USER11_ID, "User11", "user11@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER21 = new User(USER21_ID, "User21", "user21@yandex.ru", "password", Role.ROLE_USER);



    public static final BeanMatcher<User> MATCHER = BeanMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeWithExtraProps(user, "password", passw);
    }
}