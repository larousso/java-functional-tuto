package com.adelegue;

import com.adelegue.libs.User;
import com.adelegue.libs.UserRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReactorSpec {

    @Test
    void createMono() {
        // TODO create a Mono in success
        Mono<String> monoSuccess = null;
        assertThat(monoSuccess.block()).isEqualTo("Success");

        // TODO create a Mono in failure
        Mono<String> monoFailed = null;
        assertThatThrownBy(() -> monoFailed.block()).hasMessage("Oops failed");

        AtomicInteger integer = new AtomicInteger(0);
        // TODO create a lazy Mono that will increment and get the integer once it's used
        Mono<Integer> deferredMono = null;
        assertThat(deferredMono.block()).isEqualTo(1);

        // TODO create an empty Mono
        Mono<String> emptyMono = null;
        assertThat(emptyMono.blockOptional()).isEmpty();

        // TODO create a Mono from a vanilla java CompletionStage
        CompletionStage<String> future = CompletableFuture.completedStage("Future value");
        Mono<String> futureSuccess = null;
        assertThat(futureSuccess.block()).isEqualTo("Future value");
    }


    @Test
    void createFlux() {
        // TODO create a flux of 2 elements
        Flux<String> fluxSuccess = null;
        assertThat(fluxSuccess.toIterable()).containsExactly("Element 1", "Element 2");

        // TODO create a failed flux
        Flux<String> fluxFailed = null;
        assertThatThrownBy(() -> fluxFailed.blockLast()).hasMessage("Oops failed");

        List<String> elements = List.of("Element 1", "Element 2");
        // TODO create a flux from a List
        Flux<String> fluxFromList = null;
        assertThat(fluxFromList.toIterable()).containsExactly("Element 1", "Element 2");

        // TODO create an empty flux
        Flux<String> emptyFlux = null;
        assertThat(emptyFlux.toIterable()).isEmpty();

        // TODO create a flux from a vanilla java CompletionStage
        CompletionStage<String> future = CompletableFuture.completedStage("Future value");
        Flux<String> futureSuccess = null;
        assertThat(futureSuccess.toIterable()).containsExactly("Future value");

        // TODO create a flux containing a range of integer from 1 to 5.
        Flux<Integer> range = null;
        assertThat(range.toIterable()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void transformedMono() {
        Mono<String> mono = Mono.just("A value");

        // TODO find a way to transform the string to his upper case variant
        Mono<String> upper = null;

        assertThat(upper.block()).isEqualTo("A VALUE");
    }

    @Test
    void transformedFlux() {
        Flux<String> flux = Flux.just("Element 1", "Element 2", "Element 3", "Element 4");

        // TODO find a way to transform each element to upper cases
        Flux<String> upper = null;

        assertThat(upper.toIterable()).containsExactly("ELEMENT 1", "ELEMENT 2", "ELEMENT 3", "ELEMENT 4");
    }

    @Test
    void filteredMono() {
        Mono<String> mono = Mono.just("A value");

        // TODO filter the value if it's equal to "A value"
        Mono<String> filtered = null;

        assertThat(filtered.blockOptional()).isEmpty();
    }

    @Test
    void filteredFlux() {
        Flux<String> flux = Flux.just("Element 1", "Element 2", "Element 3", "Element 4");

        // TODO filter the value if it's equal to "Element 3"
        Flux<String> filtered = null;

        assertThat(filtered.toIterable()).containsExactly("Element 1", "Element 2", "Element 4");
    }

    @Test
    void sumFlux() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // TODO sum all the integers of the flux
        Mono<Integer> sum = null;

        assertThat(sum.block()).isEqualTo(15);
    }


    @Test
    void recover() {
        Mono<String> monoNpe = Mono.error(new NullPointerException("Oops"));

        // TODO recover only npe
        Mono<String> recoverFromNpe = null;

        assertThat(recoverFromNpe.block()).isEqualTo("Recovered");
    }

    @Test
    void chained() {
        var expectedUser = new User("1", "John doe");
        var userRepository = new UserRepository(Map.of(
                "1", expectedUser
        ));
        Mono<String> monoId = Mono.just("1");

        // TODO monoId is an id that will be available later. Call userRepository.getByIdAsync when it's here
        Mono<User> user = null;

        assertThat(user.block()).isEqualTo(expectedUser);
    }

    @Test
    void chainedWithFlux() {
        var user1 = new User("1", "John doe");
        var user2 = new User("2", "John doe");
        var user3 = new User("3", "Agent 42");
        var userRepository = new UserRepository(Map.of(
                user1.id, user1,
                user2.id, user2,
                user3.id, user3
        ));
        Flux<String> fluxOfName = Flux.just("John doe", "Agent 42");

        // TODO fluxOfName is names that will be available later. For each name Call userRepository.findByNameAsync when it's here
        Flux<User> user = null;

        assertThat(user.toIterable()).containsExactly(
                user1,
                user2,
                user3
        );
    }
    @Test
    void chainedWithList() {
        var user1 = new User("1", "John doe");
        var user2 = new User("2", "John doe");
        var user3 = new User("3", "Agent 42");
        var userRepository = new UserRepository(Map.of(
                user1.id, user1,
                user2.id, user2,
                user3.id, user3
        ));
        Flux<String> fluxOfName = Flux.just("John doe", "Agent 42");

        // TODO fluxOfName is names that will be available later. For each name Call userRepository.findByName when it's here
        Flux<User> user = null;

        assertThat(user.toIterable()).containsExactly(
                user1,
                user2,
                user3
        );
    }

    @Test
    void combine() {
        Mono<String> monoString = Mono.just("He is ");
        Mono<Integer> monoAge = Mono.just(18);

        // TODO combine monoString and monoAge to concatenate the two. Have a look at static methods on Mono
        Mono<String> combined = null;

        assertThat(combined.block()).isEqualTo("He is 18");
    }

    @Test
    void delayedElements() {
        Flux<Integer> range = Flux.range(1, 5);

        // TODO add a delay ofs 500 millis between each elements
        Flux<Integer> delayed = null;

        LocalDateTime start = LocalDateTime.now();
        delayed.blockLast();
        LocalDateTime end = LocalDateTime.now();

        Duration between = Duration.between(start, end);
        assertThat(between.toMillis()).isGreaterThanOrEqualTo(2500);

    }
    @Test
    void infiniteStream() {
        Flux<String> elements = Flux.interval(Duration.ofMillis(1)).map(__ -> "New element");

        // TODO elements is an infinite stream that will never stop. keep only the 3 first elemens
        Flux<String> first = null;

        assertThat(first.toIterable()).containsExactly("New element", "New element", "New element");

    }
}
