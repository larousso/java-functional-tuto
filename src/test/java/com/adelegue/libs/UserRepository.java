package com.adelegue.libs;

import com.adelegue.Unit;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {
    private AtomicInteger ids = new AtomicInteger(0);
    private ConcurrentHashMap<String, User> users =  new ConcurrentHashMap<>();

    public String nextId() {
        return String.valueOf(ids.incrementAndGet());
    }

    public Either<String, User> creer(User user) {
        String id = nextId();
        User saved = user.withId(id);
        users.put(id, saved);
        return Either.right(saved);
    }

    public Either<String, User> mettreAJour(String id, User user) {
        String idCourant = user.id;
        User saved = user.withId(id);
        users.remove(idCourant);
        users.put(id, saved);
        return Either.right(saved);
    }

    public Either<String, Unit> delete(String id) {
        users.remove(id);
        return Either.right(Unit.unit());
    }

    public Option<User> getById(String id) {
        return Option.of(users.get(id));
    }
}
