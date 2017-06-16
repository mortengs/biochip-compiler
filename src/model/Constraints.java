package model;

/**
 * Created by Jesper on 12/06/2017.
 */
final class Constraints {
    private static int numberOfFilters = 2;
    private static int numberOfHeaters = 2;
    private static int numberOfMixers = 2;
    private static int numberOfDetectors = 2;

    private Constraints() {

    }

    static int getNumberOfFilters() {
        return numberOfFilters;
    }

    static int getNumberOfHeaters() {
        return numberOfHeaters;
    }

    static int getNumberOfMixers() {
        return numberOfMixers;
    }

    static int getNumberOfDetectors() {
        return numberOfDetectors;
    }

    static void setNumberOfFilters(int filters) {
        numberOfFilters = filters;
    }

    static void setNumberOfHeaters(int heaters) {
        numberOfHeaters = heaters;
    }

    static void setNumberOfMixers(int mixers) {
        numberOfMixers = mixers;
    }

    static void setNumberOfDetectors(int detectors) {
        numberOfDetectors = detectors;
    }
}
