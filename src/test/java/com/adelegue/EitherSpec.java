package com.adelegue;

import com.adelegue.libs.User;
import com.adelegue.libs.UserRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static org.assertj.core.api.Assertions.assertThat;


public class EitherSpec {

    @Test
    void createSuccess() {
        // TODO
        Either<String, Integer> success = null;

        assertThat(success).isEqualTo(Right(10));
    }

    @Test
    void createError() {
        // TODO
        Either<String, Integer> error = null;

        assertThat(error).isEqualTo(Left("Oops an error"));
    }

    @Test
    void convertToOption() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("An error");

        // TODO
        Option<Integer> optionSuccess = null;
        // TODO
        Option<Integer> optionFailure = null;

        assertThat(optionSuccess).isEqualTo(Some(10));
        assertThat(optionFailure).isEqualTo(None());
    }

    @Test
    void transformSuccess() {
        Either<String, Integer> success = Either.right(10);

        // TODO
        Either<String, String> transformed = null;

        assertThat(transformed).isEqualTo(Right("Valeur 10"));
    }

    @Test
    void transformError() {
        Either<String, Integer> failure = Either.left("An error");

        // TODO
        Either<String, Integer> transformed = null;

        assertThat(transformed).isEqualTo(Left("A really big error"));
    }

    @Test
    void recover() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("Une erreur");

        // TODO
        Integer getOrDefaultSuccess = null;
        // TODO
        Integer getOrDefaultFailure = null;

        assertThat(getOrDefaultSuccess).isEqualTo(10);
        assertThat(getOrDefaultFailure).isEqualTo(0);
    }


    @Test
    void chained() {
        UserRepository userRepository = new UserRepository();
        Either<String, User> userOuErreur = Either.right(new User(null, "John Doe"));

        // TODO
        Either<String, User> savedInDb = null;

        assertThat(savedInDb).isEqualTo(Right(new User("1", "John Doe")));
    }

    @Test
    void filtered() {
        List<Either<String, Integer>> list = List(
                Right(1),
                Left("Oops"),
                Right(2),
                Left("Argghhhh"),
                Right(3)
        );

        // TODO
        List<Integer> filtree = null;

        assertThat(filtree).containsExactly(1, 2, 3);
    }

    @Test
    void accumulate() {
        List<Either<String, String>> eithers = List(Right("Hello"), Right("World"));
        List<Either<String, String>> eithersError = List(Left("Et non"), Right("Hello"), Right("World"), Left("c'est en erreur"));

        // TODO Have a look to static methods
        Either<String, List<String>> listEithers = null;
        // TODO Have a look to static methods
        Either<String, List<String>> listEithersError = null;

        assertThat(listEithers).isEqualTo(Right(List("Hello", "World")));
        assertThat(listEithersError).isEqualTo(Left("Et non"));
    }

    @Test
    void accumulateSuccessAndErrors() {

        List<Either<String, String>> eithers = List(Right("Hello"), Right("World"));
        List<Either<String, String>> eithersError = List(Left("Oh no"), Right("Hello"), Right("World"), Left("It's an error"));

        // TODO Have a look to static methods
        Either<List<String>, List<String>> listEithers = null;
        // TODO Have a look to static methods
        Either<List<String>, List<String>> listEithersError = null;

        assertThat(listEithers).isEqualTo(Right(List("Hello", "World")));
        assertThat(listEithersError).isEqualTo(Left(List("Oh no", "It's an error")));
    }

    @Test
    void bigCase() {
        UserRepository userRepository = new UserRepository();
        userRepository.creer(new User("1", "Agent 42"));

        Either<String, User> agent_42 = createIfNotExists(userRepository, new User("1", "Agent 42"));
        assertThat(agent_42).isEqualTo(Left("The user already exists"));
        Either<String, User> john_doe = createIfNotExists(userRepository, new User("2", "John Doe"));
        assertThat(john_doe).isEqualTo(Right(new User("1", "John Doe")));
    }

    private Either<String, User> createIfNotExists(UserRepository userRepository, User user) {
        // TODO
        return Either.left("Not implemented");
    }

}
