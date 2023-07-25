/*
 * Copyright 2023 The Context Mapper project team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
