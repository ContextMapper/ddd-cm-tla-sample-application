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

package org.contextmapper.sample.tlas.domain;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaEnumConstant;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.contextmapper.archunit.AbstractTacticArchUnitTest;
import org.contextmapper.tactic.dsl.tacticdsl.Enum;
import org.contextmapper.tactic.dsl.tacticdsl.EnumValue;
import org.eclipse.xtext.EcoreUtil2;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.properties.HasType.Predicates.rawType;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * This ArchUnit test uses our Context Mapper Extension to verify whether the code is in sync with the
 * Context Mapper domain model in TLAs-context.cml.
 * <p>
 * ArchUnit extension documentation: https://github.com/ContextMapper/context-mapper-archunit-extension
 */
class TacticDDDModelTest extends AbstractTacticArchUnitTest {

    @Override
    protected String getCMLFilePath() {
        return "src/main/cml/TLAs-context.cml";
    }

    @Override
    protected String getBoundedContextName() {
        return "TLA_Resolver";
    }

    @Override
    protected String getJavaPackageName2Test() {
        return "org.contextmapper.sample.tlas.domain";
    }

    @Test
    void domainModelClassesShouldBeAnnotatedWithJMolecules() {
        classes().that()
                .areNotEnums()
                .and().areNotAssignableTo(Exception.class)
                .and().areNotNestedClasses()
                .should().beAnnotatedWith(rawType(resideInAPackage("org.jmolecules..")))
                .check(classes);
    }

    @Test
    void statusEnumsShouldBeModelledInCML() {
        classes().that()
                .areEnums()
                .and().haveSimpleNameEndingWith("Status")
                .should(new ArchCondition<>("adhere to CML enum values") {
                    @Override
                    public void check(JavaClass statusEnum, ConditionEvents events) {
                        Optional<? extends Enum> optionalCmlStatus = EcoreUtil2.eAllOfType(context, Enum.class).stream()
                                .filter(o -> o.getName().equals(statusEnum.getSimpleName()))
                                .findAny();
                        events.add(new SimpleConditionEvent(optionalCmlStatus, optionalCmlStatus.isPresent(),
                                String.format("The Status enum '%s' is not modelled in CML.", statusEnum.getSimpleName())));

                        if (optionalCmlStatus.isPresent()) {
                            Enum cmlStatus = optionalCmlStatus.get();
                            for (JavaEnumConstant javaEnumConstant : statusEnum.getEnumConstants()) {
                                Optional<EnumValue> cmlEnumValue = cmlStatus.getValues().stream()
                                        .filter(v -> v.getName().equals(javaEnumConstant.name()))
                                        .findAny();
                                events.add(new SimpleConditionEvent(statusEnum, cmlEnumValue.isPresent(),
                                        String.format("The status enum '%s' does not have a value called '%s' in CML.",
                                                cmlStatus.getName(), javaEnumConstant.name())));
                            }
                        }
                    }
                })
                .check(classes);
    }

}
