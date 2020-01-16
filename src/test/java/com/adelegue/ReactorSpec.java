package com.adelegue;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReactorSpec {

    @Test
    void createMono() {
        // TODO
        Mono<String> monoSuccess = null;
        assertThat(monoSuccess.block()).isEqualTo("Success");

        // TODO
        Mono<String> monoFailed = null;
        assertThatThrownBy(() -> monoFailed.block()).hasMessage("Oops failed");

        AtomicInteger integer = new AtomicInteger(0);
        // TODO
        Mono<Integer> deferredMono = null;
        assertThat(deferredMono.block()).isEqualTo(1);

        // TODO
        Mono<String> emptyMono = null;
        assertThat(emptyMono.blockOptional()).isEmpty();

        // TODO
        CompletionStage<String> future = CompletableFuture.completedStage("Future value");
        Mono<String> futureSuccess = null;
        assertThat(futureSuccess.block()).isEqualTo("Future value");
    }


    @Test
    void createFlux() {
        // TODO
        Flux<String> monoSuccess = null;
        assertThat(monoSuccess.toIterable()).containsExactly("Element 1", "Element 2");

        // TODO
        Flux<String> monoFailed = null;
        assertThatThrownBy(() -> monoFailed.blockLast()).hasMessage("Oops failed");

        List<String> elements = List.of("Element 1", "Element 2");
        // TODO
        Flux<String> fluxFromList = null;
        assertThat(fluxFromList.toIterable()).containsExactly("Element 1", "Element 2");

        // TODO
        Flux<String> emptyFlux = null;
        assertThat(emptyFlux.toIterable()).isEmpty();

        // TODO
        CompletionStage<String> future = CompletableFuture.completedStage("Future value");
        Flux<String> futureSuccess = null;
        assertThat(futureSuccess.toIterable()).containsExactly("Future value");

        // TODO
        Flux<Integer> range = null;
        assertThat(range.toIterable()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void transformedMono() {
        Mono<String> mono = Mono.just("A value");

        // TODO
        Mono<String> upper = null;

        assertThat(upper.block()).isEqualTo("A VALUE");
    }

    @Test
    void transformedFlux() {
        Flux<String> flux = Flux.just("Element 1", "Element 2", "Element 3", "Element 4");

        // TODO
        Flux<String> upper = null;

        assertThat(upper.toIterable()).containsExactly("ELEMENT 1", "ELEMENT 2", "ELEMENT 3", "ELEMENT 4");
    }

    @Test
    void filteredMono() {
        Mono<String> mono = Mono.just("A value");

        // TODO
        Mono<String> filtered = null;

        assertThat(filtered.blockOptional()).isEmpty();
    }

    @Test
    void filteredFlux() {
        Flux<String> flux = Flux.just("Element 1", "Element 2", "Element 3", "Element 4");

        // TODO
        Flux<String> filtered = null;

        assertThat(filtered.toIterable()).containsExactly("Element 1", "Element 2", "Element 4");
    }

    @Test
    void sumFlux() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        // TODO
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

    }


}
