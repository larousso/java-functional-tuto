package com.adelegue;

import com.adelegue.libs.User;
import com.adelegue.libs.UserRepository;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static org.assertj.core.api.Assertions.assertThat;


public class EitherSpec {

    @Test
    void createSuccess() {
        // TODO create a right either
        Either<String, Integer> success = Either.right(10);

        assertThat(success).isEqualTo(Right(10));
    }

    @Test
    void createError() {
        // TODO create a left either
        Either<String, Integer> error = Either.left("Oops an error");

        assertThat(error).isEqualTo(Left("Oops an error"));
    }

    @Test
    void convertToOption() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("An error");

        // TODO convert success to option
        Option<Integer> optionSuccess = success.toOption();
        // TODO convert failure to option
        Option<Integer> optionFailure = failure.toOption();

        assertThat(optionSuccess).isEqualTo(Some(10));
        assertThat(optionFailure).isEqualTo(None());
    }

    @Test
    void transformSuccess() {
        Either<String, Integer> success = Either.right(10);

        // TODO transform the right side to "Value 10" if the either is a `Right` one. Find the good method looking at method signature
        Either<String, String> transformed = success.map(num -> "Value "+num);

        assertThat(transformed).isEqualTo(Right("Value 10"));
    }

    @Test
    void transformError() {
        Either<String, Integer> failure = Either.left("An error");

        // TODO transform the left side to "A really big error" if the either is a `Left` one. Find the good method looking at method signature
        Either<String, Integer> transformed = failure.mapLeft(__ -> "A really big error");

        assertThat(transformed).isEqualTo(Left("A really big error"));
    }

    @Test
    void recover() {
        Either<String, Integer> success = Either.right(10);
        Either<String, Integer> failure = Either.left("Une erreur");

        // TODO extract the value of success if the either is a `Right` or get a default value if it's a `Left`
        Integer getOrDefaultSuccess = success.getOrElseGet(err -> 0);
        // TODO extract the value of failure if the either is a `Right` or get a default value if it's a `Left`
        Integer getOrDefaultFailure = failure.getOrElseGet(err -> 0);

        assertThat(getOrDefaultSuccess).isEqualTo(10);
        assertThat(getOrDefaultFailure).isEqualTo(0);
    }


    @Test
    void chained() {
        UserRepository userRepository = new UserRepository();
        Either<String, User> userOuErreur = Either.right(new User(null, "John Doe"));

        // TODO userOuErreur is a validated User that need to be saved in DB using userRepository.create. In return you will have a user with an id.
        Either<String, User> savedInDb = userOuErreur.flatMap(user -> userRepository.create(user));

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
        List<Integer> filtered = list.flatMap(either -> either.toList());

        assertThat(filtered).containsExactly(1, 2, 3);
    }

    @Test
    void accumulate() {
        List<Either<String, String>> eithers = List(Right("Hello"), Right("World"));
        List<Either<String, String>> eithersError = List(Left("Et non"), Right("Hello"), Right("World"), Left("c'est en erreur"));

        // TODO Have a look at static methods
        Either<String, Seq<String>> listEithers = Either.sequenceRight(eithers);
        // TODO Have a look at static methods
        Either<String, Seq<String>> listEithersError = Either.sequenceRight(eithersError);

        assertThat(listEithers).isEqualTo(Right(List("Hello", "World")));
        assertThat(listEithersError).isEqualTo(Left("Et non"));
    }

    @Test
    void accumulateSuccessAndErrors() {

        List<Either<String, String>> eithers = List(Right("Hello"), Right("World"));
        List<Either<String, String>> eithersError = List(Left("Oh no"), Right("Hello"), Right("World"), Left("It's an error"));

        // TODO Have a look to static methods
        Either<Seq<String>, Seq<String>> listEithers = Either.sequence(eithers);
        // TODO Have a look to static methods
        Either<Seq<String>, Seq<String>> listEithersError = Either.sequence(eithersError);

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
        assertThat(john_doe).isEqualTo(Right(new User("2", "John Doe")));
    }


    @Test
    void traverse() {
        var userRepository = new UserRepository();
        var listOfUsers = List(
                new User("1", "Agent 41"),
                new User("2", "Agent 42"),
                new User("3", "Agent 43")
        );

        // TODO have a look at the traverse method and use it with the previously implemented createIfNotExists
        Either<Seq<String>, Seq<User>> batchCreate = Either.traverse(listOfUsers, user -> createIfNotExists(userRepository, user));

        assertThat(batchCreate).isEqualTo(Right(List(
                new User("1", "Agent 41"),
                new User("2", "Agent 42"),
                new User("3", "Agent 43")
        )));
    }

    private Either<String, User> createIfNotExists(UserRepository userRepository, User user) {
        // TODO in order to validate if the user already exists, you need to perform a get by id and then create the user.
        //  Use the previously used method in order to to that.
        //  You can use the swap method to inverse right and left if needed

        Either<String, Unit> verifiyIfExists = userRepository.getById(user.id)
                // If a user exists, this will raise an error with this message
                .map(aUser -> "The user already exists")
                // toEither need the left side, we put an arbitrary value
                .toEither(Unit.unit())
                // swap will inverse the right and the left
                .swap();

        return verifiyIfExists.flatMap(__ -> userRepository.create(user));
    }

}
