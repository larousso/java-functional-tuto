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
        // TODO create a right either
        Either<String, Integer> success = null;

        assertThat(success).isEqualTo(Right(10));
    }

    @Test
    void createError() {
        // TODO create a left either
        Either<String, Integer> error = null;

        assertThat(error).isEqualTo(Left("Oops an error"));
    }

    @Test
    void convertToOption() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("An error");

        // TODO convert success to option
        Option<Integer> optionSuccess = null;
        // TODO convert failure to option
        Option<Integer> optionFailure = null;

        assertThat(optionSuccess).isEqualTo(Some(10));
        assertThat(optionFailure).isEqualTo(None());
    }

    @Test
    void transformSuccess() {
        Either<String, Integer> success = Either.right(10);

        // TODO transform the right side to "Value 10" if the either is a `Right` one. Find the good method looking at method signature
        Either<String, String> transformed = null;

        assertThat(transformed).isEqualTo(Right("Value 10"));
    }

    @Test
    void transformError() {
        Either<String, Integer> failure = Either.left("An error");

        // TODO transform the left side to "A really big error" if the either is a `Left` one. Find the good method looking at method signature
        Either<String, Integer> transformed = null;

        assertThat(transformed).isEqualTo(Left("A really big error"));
    }

    @Test
    void recover() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("Une erreur");

        // TODO extract the value of success if the either is a `Right` or get a default value if it's a `Left`
        Integer getOrDefaultSuccess = null;
        // TODO extract the value of failure if the either is a `Right` or get a default value if it's a `Left`
        Integer getOrDefaultFailure = null;

        assertThat(getOrDefaultSuccess).isEqualTo(10);
        assertThat(getOrDefaultFailure).isEqualTo(0);
    }


    @Test
    void chained() {
        UserRepository userRepository = new UserRepository();
        Either<String, User> userOuErreur = Either.right(new User(null, "John Doe"));

        // TODO userOuErreur is a validated User that need to be saved in DB using userRepository.create. In return you will have a user with an id.
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

        // TODO filter the list to keep only the Right values. Tips: an either could be converted to List
        List<Integer> filtered = null;

        assertThat(filtered).containsExactly(1, 2, 3);
    }

    @Test
    void accumulate() {
        List<Either<String, String>> eithers = List(Right("Hello"), Right("World"));
        List<Either<String, String>> eithersError = List(Left("Et non"), Right("Hello"), Right("World"), Left("c'est en erreur"));

        // TODO Have a look at static methods
        Either<String, List<String>> listEithers = null;
        // TODO Have a look at static methods
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
        userRepository.create(new User("1", "Agent 42"));

        Either<String, User> agent_42 = createIfNotExists(userRepository, new User("1", "Agent 42"));
        assertThat(agent_42).isEqualTo(Left("The user already exists"));
        Either<String, User> john_doe = createIfNotExists(userRepository, new User("2", "John Doe"));
        assertThat(john_doe).isEqualTo(Right(new User("1", "John Doe")));
    }


    @Test
    void traverse() {
        var userRepository = new UserRepository();
        var listOfUsers = List(
                new User(null, "Agent 41"),
                new User(null, "Agent 42"),
                new User(null, "Agent 43")
        );

        // TODO have a look at the traverse method and use it with the previously implemented createIfNotExists
        Either<List<String>, List<User>> batchCreate = null;

        assertThat(batchCreate).isEqualTo(Right(List(
                new User("1", "Agent 41"),
                new User("2", "Agent 42"),
                new User("3", "Agent 43")
        )));
    }

    private Either<String, User> createIfNotExists(UserRepository userRepository, User user) {
        // TODO in order to validate if the user already exists, you need to perform a get by id and then create the user. Use the previously used method in order to to that
        return Either.left("Not implemented");
    }

}
