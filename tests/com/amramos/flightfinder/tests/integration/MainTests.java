package com.amramos.flightfinder.tests.integration;

import com.amramos.flightfinder.Main;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class MainTests {
    @Test
    public void run_fileExistsAndThereIsAPath_returnsTrue() {
        String[] args = { "test_files/input1.csv", "New York", "Montreal" };

        Pair<Boolean, String> result = Main.run(args);

        Assert.assertTrue(result.getKey());
    }

    @Test
    public void run_fileExistsAndThereIsNoPath_returnsFalse() {
        String[] noPathFromMontrealToNewYork = { "test_files/input1.csv", "Montreal", "New York" };
        String[] noPathFromNewYorkToParis = { "test_files/input1.csv", "New York", "Paris" };
        String[] noPathFromNewYorkToUnknown = { "test_files/input1.csv", "New York", "Unknown" };

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
        String[] missingTwoArguments = { "test_files/input1.csv" };

        Pair<Boolean, String> result = Main.run(missingTwoArguments);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }

    @Test
    public void run_allArguments_returnsError() {
        String[] missingAllArguments = {};

        Pair<Boolean, String> result = Main.run(missingAllArguments);

        Assert.assertFalse(result.getKey());
        Assert.assertFalse(result.getValue().isEmpty());
    }
}
