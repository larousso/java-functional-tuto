package com.adelegue.libs;

import com.adelegue.Unit;
import io.vavr.control.Either;
import io.vavr.control.Option;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserRepository {
    private AtomicInteger ids = new AtomicInteger(0);
    private ConcurrentHashMap<String, User> users =  new ConcurrentHashMap<>();

    public String nextId() {
        return String.valueOf(ids.incrementAndGet());
    }

    public UserRepository() {
    }

    public UserRepository(Map<String, User> users) {
        this.users.putAll(users);
    }

    public Either<String, User> create(User user) {
        String id = nextId();
        User saved = user.withId(id);
        users.put(id, saved);
        return Either.right(saved);
    }


    public Mono<User> createAsync(User user) {
        String id = nextId();
        User saved = user.withId(id);
        users.put(id, saved);
        return Mono.just(saved);
    }

    public Either<String, User> update(String id, User user) {
        String idCourant = user.id;
        User saved = user.withId(id);
        users.remove(idCourant);
        users.put(id, saved);
        return Either.right(saved);
    }

    public Mono<User> updateAsync(String id, User user) {
        String idCourant = user.id;
        User saved = user.withId(id);
        users.remove(idCourant);
        users.put(id, saved);
        return Mono.just(saved);
    }

    public Either<String, Unit> delete(String id) {
        users.remove(id);
        return Either.right(Unit.unit());
    }

    public Option<User> getById(String id) {
        return Option.of(users.get(id));
    }

    public Mono<User> getByIdAsync(String id) {
        return getById(id).fold(Mono::empty, Mono::just);
    }

    public Flux<User> listAsync() {
        return Flux.fromIterable(users.values());
    }

    public Flux<User> findByNameAsync(String name) {
        return Flux.fromIterable(users.values()).filter(user -> user.name.equals(name));
    }

    public List<User> findByName(String name) {
        return users.values().stream().filter(user -> user.name.equals(name)).collect(Collectors.toList());
    }

}
