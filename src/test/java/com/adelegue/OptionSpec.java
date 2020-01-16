package com.adelegue;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static java.util.function.Function.identity;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionSpec {

    @Test
    void optionWithAValue() {
        // TODO create a option containing "A value"
        Option<String> mayBeValue = Option.some("A value");

        assertThat(mayBeValue).isEqualTo(Some("A value"));
    }


    @Test
    void emptyOption() {
        // TODO create an empty option
        Option<String> mayBeValue = Option.none();

        assertThat(mayBeValue).isEmpty();
    }

    @Test
    void transformed() {
        Option<String> mayBeValue = Some("A text");

        // TODO transform the value inside the option
        Option<String> mayBeUpper = mayBeValue.map(String::toUpperCase);

        assertThat(mayBeUpper).isEqualTo(Some("A TEXT"));
    }

    @Test
    void defaultValueIfEmpty() {
        Option<String> mayBeString = Option.none();

        // TODO get the value or a "Default value" if the option is empty
        String value = mayBeString.getOrElse("Default value");

        assertThat(value).isEqualTo("Default value");
    }


    @Test
    void transformOrDefaultValueIfEmpty() {
        Option<String> mayBeString = Option.some("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Option<String> mayBeAnEmpty = Option.none();

        // TODO count the caracters inside mayBeString or get 0 by default. Have a look at the fold method
        Integer value = mayBeString.fold(() -> 0, str -> str.length());
        // TODO count the caracters inside mayBeAnEmpty or get 0 by default.
        Integer ifEmpty = mayBeAnEmpty.fold(() -> 0, str -> str.length());

        assertThat(value).isEqualTo(26);
        assertThat(ifEmpty).isEqualTo(0);
    }

    @Test
    void collect() {
        List<Option<String>> options = List(
                Some("1"),
                Some("2"),
                Some("3")
        );

        List<Option<String>> optionsWithEmpty = List(
                Some("1"),
                None(),
                Some("3")
        );

        // TODO with options. Have a look at static methods available in Option
        Option<Seq<String>> optionOfStrings = Option.sequence(options);
        // TODO with optionsWithEmpty
        Option<Seq<String>> optionOfStringsWithEmpty = Option.sequence(optionsWithEmpty);

        assertThat(optionOfStrings).isEqualTo(Some(List("1", "2", "3")));
        assertThat(optionOfStringsWithEmpty).isEqualTo(None());
    }

    @Test
    void convertToEither() {
        Option<String> mayBeString = Option.none();

        // TODO Either is an option where you why it's empty
        Either<String, String> either = mayBeString.toEither("It's empty ...");

        assertThat(either).isEqualTo(Left("It's empty ..."));
    }

    @Test
    void listAndOptionToBuildUrl() {
        // TODO you need to complete the buildUrl method
        String url = buildUrl(Some("johndoe@gmail.com"), None(), Some(79000));

        assertThat(url).isEqualTo("/api/persons?email=johndoe@gmail.com&zipCode=79000");
    }

    private static String buildUrl(Option<String> mayBeEmail, Option<String> mayBeName, Option<Integer> mayBeZipCode) {
        String baseApi = "/api/persons";

        // TODO complete this part. Remember option is also a list
        List<String> queryParams = List(
                mayBeEmail.map(email -> "email="+email),
                mayBeName.map(name -> "name="+name),
                mayBeZipCode.map(zipCode -> "zipCode="+zipCode)
        ).flatMap(identity());

        if (queryParams.isEmpty()) {
            return baseApi;
        } else {
            return baseApi + "?" + queryParams.mkString("&");
        }
    }
}
