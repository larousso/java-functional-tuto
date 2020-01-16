package com.adelegue;

import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionSpec {

    @Test
    void optionWithAValue() {
        // TODO
        Option<String> mayBeValue = null;

        assertThat(mayBeValue).isEqualTo(Some("A value"));
    }


    @Test
    void emptyOption() {
        // TODO
        Option<String> mayBeValue = null;

        assertThat(mayBeValue).isEmpty();
    }

    @Test
    void transformed() {
        Option<String> mayBeValue = Some("A text");

        // TODO
        Option<String> mayBeUpper = null;

        assertThat(mayBeValue).isEqualTo(Some("A TEXT"));
    }

    @Test
    void defaultValueIfEmpty() {
        Option<String> mayBeString = Option.none();

        // TODO
        String value = null;

        assertThat(value).isEqualTo("Default value");
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

        // TODO with options
        Option<List<String>> optionOfStrings = null;
        // TODO with optionsWithEmpty
        Option<List<String>> optionOfStringsWithEmpty = null;

        assertThat(optionOfStrings).isEqualTo(Some(List("1", "2", "3")));
        assertThat(optionOfStringsWithEmpty).isEqualTo(None());
    }

    @Test
    void convertToEither() {
        Option<String> mayBeString = Option.none();

        Either<String, String> either = null;

        assertThat(either).isEqualTo(Left("It's empty ..."));
    }
}
