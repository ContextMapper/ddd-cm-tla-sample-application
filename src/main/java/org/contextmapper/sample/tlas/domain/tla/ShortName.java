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
public class ShortName {

    private String name;

    public ShortName(final String name) {
        checkArgument(name != null, "Short name cannot be null!");
        checkArgument(!name.isEmpty(), "Short name cannot be empty!");
        checkArgument(!name.contains(" "), "A single short name cannot contain spaces.");

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
        ShortName that = (ShortName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
