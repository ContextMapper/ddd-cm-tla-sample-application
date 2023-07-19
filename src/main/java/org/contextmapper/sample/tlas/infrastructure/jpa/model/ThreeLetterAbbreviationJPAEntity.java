package org.contextmapper.sample.tlas.infrastructure.jpa.model;

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

}
