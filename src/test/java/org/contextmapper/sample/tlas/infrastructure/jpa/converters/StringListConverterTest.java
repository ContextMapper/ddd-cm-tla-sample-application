package org.contextmapper.sample.tlas.infrastructure.jpa.converters;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StringListConverterTest {

    @Test
    void canConvertListToString() {
        // given
        var list = List.of("string1", "string2");

        // when
        var string = new StringListConverter().convertToDatabaseColumn(list);

        // then
        assertThat(string).isEqualTo("string1,string2");
    }

    @Test
    void canConvertStringToList() {
        // given
        var string = "string1,string2";

        // when
        var list = new StringListConverter().convertToEntityAttribute(string);

        // then
        assertThat(list).isEqualTo(List.of("string1", "string2"));
    }

}
