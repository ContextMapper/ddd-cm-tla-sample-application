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

package org.contextmapper.sample.tlas.infrastructure.jpa.model;

import com.google.common.collect.Lists;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.contextmapper.sample.tlas.infrastructure.jpa.converters.StringListConverter;

import java.util.List;

@Entity(name = "ThreeLetterAbbreviation")
public class ThreeLetterAbbreviationJPAEntity {

    @Id
    private String name;

    private String meaning;

    @Convert(converter = StringListConverter.class)
    private List<String> alternativeMeanings;

    private String url;

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAlternativeMeanings() {
        return alternativeMeanings;
    }

    public void setAlternativeMeanings(List<String> alternativeMeanings) {
        this.alternativeMeanings = Lists.newArrayList(alternativeMeanings);
    }

}
