package com.amramos.flightfinder.tests.integration;

import com.amramos.flightfinder.Main;
import com.amramos.flightfinder.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MainTests {
    String testFile = "tests/test_files/input1.csv";

    @Test
    public void run_fileExistsAndThereIsAPath_returnsTrue() {
        String[] args = { testFile, "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(args);

        Assert.assertTrue(result.item1());
        Assert.assertTrue(result.item2().isEmpty());
    }

    @Test
    public void run_fileExistsAndThereIsNoPath_returnsFalse() {
        String[] noPathFromMontrealToNewYork = { testFile, "Montreal", "New York" };
        String[] noPathFromNewYorkToParis = { testFile, "New York", "Paris" };
        String[] noPathFromNewYorkToUnknown = { testFile, "New York", "Unknown" };

        Assert.assertFalse(Main.run(noPathFromMontrealToNewYork).item1());
        Assert.assertFalse(Main.run(noPathFromNewYorkToParis).item1());
        Assert.assertFalse(Main.run(noPathFromNewYorkToUnknown).item1());
    }

    @Test
    public void run_fileDoesNotExist_returnsError() {
        String[] fileDoesNotExist = { "nonFile.ext", "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(fileDoesNotExist);

        Assert.assertFalse(result.item1());
        Assert.assertFalse(result.item2().isEmpty());
    }

    @Test
    public void run_missingOneArgument_returnsError() {
        String[] missingOneArgument = { "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(missingOneArgument);

        Assert.assertFalse(result.item1());
        Assert.assertFalse(result.item2().isEmpty());
    }

    @Test
    public void run_missingTwoArguments_returnsError() {
        String[] missingTwoArguments = { testFile };

        Pair<Boolean, String> result = Main.run(missingTwoArguments);

        Assert.assertFalse(result.item1());
        Assert.assertFalse(result.item2().isEmpty());
    }

    @Test
    public void run_missingAllArguments_returnsError() {
        String[] missingAllArguments = {};

        Pair<Boolean, String> result = Main.run(missingAllArguments);

        Assert.assertFalse(result.item1());
        Assert.assertFalse(result.item2().isEmpty());
    }
}
