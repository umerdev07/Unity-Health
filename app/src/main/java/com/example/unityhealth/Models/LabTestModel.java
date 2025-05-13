package com.example.unityhealth.Models;

public class LabTestModel {
    private String testName;
    private String testDescription;

    public LabTestModel() {}

    public LabTestModel(String testName, String testDescription) {
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestDescription() {
        return testDescription;
    }
}
