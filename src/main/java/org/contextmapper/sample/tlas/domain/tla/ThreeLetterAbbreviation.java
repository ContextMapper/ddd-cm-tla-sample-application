package org.contextmapper.sample.tlas.domain.tla;

import org.contextmapper.sample.tlas.domain.tla.exception.InvalidTLAStateTransitionException;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static org.contextmapper.sample.tlas.domain.tla.TLAStatus.*;

@Entity
@AggregateRoot
public class ThreeLetterAbbreviation {

    private final Abbreviation name;
    private final String meaning;
    private final Set<String> alternativeMeanings;
    private URL link;
    private TLAStatus status;

    private ThreeLetterAbbreviation(final TLABuilder builder) {
        checkArgument(builder != null);
        checkArgument(builder.name != null, "A TLAs name cannot be null!");
        checkArgument(builder.meaning != null && !builder.meaning.isEmpty(),
                "A TLAs meaning cannot be null or empty.");
        checkArgument(builder.status != null, "The status of a TLA must be defined.");

        this.name = builder.name;
        this.meaning = builder.meaning;
        this.alternativeMeanings = builder.alternativeMeanings.stream()
                .filter(s -> !s.isEmpty()).collect(java.util.stream.Collectors.toSet());
        if (builder.link != null) {
            try {
                this.link = new URL(builder.link);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("The passed link is not a valid URL.", e);
            }
        }
        this.status = builder.status;
    }

    public void accept() {
        if (this.status != PROPOSED)
            throw new InvalidTLAStateTransitionException("A TLA has to be in state PROPOSED to be accepted.");

        this.status = ACCEPTED;
    }

    public void decline() {
        if (this.status != PROPOSED)
            throw new InvalidTLAStateTransitionException("A TLA has to be in state PROPOSED to be declined.");

        this.status = DECLINED;
    }

    public void archive() {
        if (this.status != ACCEPTED)
            throw new InvalidTLAStateTransitionException("A TLA has to be in state ACCEPTED to be archived.");

        this.status = ARCHIVED;
    }

    public Set<String> getAlternativeMeanings() {
        return Collections.unmodifiableSet(this.alternativeMeanings);
    }

    public String getLink() {
        return link.toString();
    }

    public TLAStatus getStatus() {
        return status;
    }

    public String getMeaning() {
        return meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeLetterAbbreviation that = (ThreeLetterAbbreviation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class TLABuilder {
        private final Abbreviation name;
        private String meaning;
        private final Set<String> alternativeMeanings = new HashSet<>();
        private String link;
        private TLAStatus status = PROPOSED;

        public TLABuilder(final Abbreviation name) {
            this.name = name;
        }

        public TLABuilder(final String name) {
            this.name = new Abbreviation(name);
        }

        public TLABuilder withMeaning(final String meaning) {
            this.meaning = meaning;
            return this;
        }

        public TLABuilder withAlternativeMeaning(final String alternativeMeaning) {
            this.alternativeMeanings.add(alternativeMeaning);
            return this;
        }

        public TLABuilder withLink(final String link) {
            this.link = link;
            return this;
        }

        public TLABuilder withStatus(final TLAStatus status) {
            this.status = status;
            return this;
        }

        public ThreeLetterAbbreviation build() {
            return new ThreeLetterAbbreviation(this);
        }

    }

}
