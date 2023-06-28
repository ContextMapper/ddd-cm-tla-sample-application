package org.contextmapper.sample.tlas.infrastructure.main;

import org.contextmapper.sample.tlas.application.SampleApplicationService;

public class JavaMain {

    public static void main(String[] args) {
        var service = new SampleApplicationService();
        System.out.println(service.getSampleMessage());
    }

}
