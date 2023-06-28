package org.contextmapper.sample.tlas.domain;

import org.contextmapper.archunit.AbstractTacticArchUnitTest;

class TacticDDDModelTest extends AbstractTacticArchUnitTest {

    @Override
    protected String getBoundedContextName() {
        return "TLAs_Context";
    }

    @Override
    protected String getCMLFilePath() {
        return "src/main/cml/TLAs-context.cml";
    }

    @Override
    protected String getJavaPackageName2Test() {
        return "org.contextmapper.sample.tlas.domain";
    }
}
