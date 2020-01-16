package com.adelegue.libs;

import java.util.StringJoiner;

public class User {

    public String id;
    public String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User withId(String id) {
        return new User(id, name);
    }

    public User withName(String name) {
        return new User(id, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
