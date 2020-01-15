package com.amramos.flightfinder.tests.integration;

import com.amramos.flightfinder.Main;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MainTests {
    String testFile = "tests/test_files/input1.csv";

    @Test
    public void run_fileExistsAndThereIsAPath_returnsTrue() {
        String[] args = { testFile, "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(args);

        Assert.assertTrue(result.getKey());
        Assert.assertTrue(result.getValue().isEmpty());
    }

    @Test
    public void run_fileExistsAndThereIsNoPath_returnsFalse() {
        String[] noPathFromMontrealToNewYork = { testFile, "Montreal", "New York" };
        String[] noPathFromNewYorkToParis = { testFile, "New York", "Paris" };
        String[] noPathFromNewYorkToUnknown = { testFile, "New York", "Unknown" };

        Assert.assertFalse(Main.run(noPathFromMontrealToNewYork).getKey());
        Assert.assertFalse(Main.run(noPathFromNewYorkToParis).getKey());
        Assert.assertFalse(Main.run(noPathFromNewYorkToUnknown).getKey());
    }

    @Test
    public void run_fileDoesNotExist_returnsError() {
        String[] fileDoesNotExist = { "nonFile.ext", "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(fileDoesNotExist);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }

    @Test
    public void run_missingOneArgument_returnsError() {
        String[] missingOneArgument = { "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(missingOneArgument);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }

    @Test
    public void run_missingTwoArguments_returnsError() {
        String[] missingTwoArguments = { testFile };

        Pair<Boolean, String> result = Main.run(missingTwoArguments);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }

    @Test
    public void run_missingAllArguments_returnsError() {
        String[] missingAllArguments = {};

        Pair<Boolean, String> result = Main.run(missingAllArguments);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }
}
