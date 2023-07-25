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

package org.contextmapper.sample.tlas.domain.tla;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

@ValueObject
public class Abbreviation {

    private String name;

    public Abbreviation(final String name) {
        checkArgument(name != null, "Abbreviation name cannot be null!");
        checkArgument(!name.isEmpty(), "Abbreviation cannot be empty!");
        checkArgument(!name.contains(" "), "A single abbreviation cannot contain spaces.");

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abbreviation that = (Abbreviation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
