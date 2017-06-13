package model;

/**
 * Created by Jesper on 12/06/2017.
 */
public class Constraints {
    int numberOfFilters = 2;
    int numberOfHeaters = 2;
    int numberOfMixers = 2;
    int numberOfDetectors = 2;

    // DEFAULT RUN TIME OF COMPONENTS
    int filterDefaultTime = 0;
    int heaterDefaultTime = 0;
    int mixerDefaultTime = 0;
    int detectorDefaultTime = 0;

    int inputDefaultTime = 0;

    // TODO: Maybe add a buffer time just to be sure
    public Constraints(int numberOfFilters, int numberOfHeaters, int numberOfMixers, int numberOfDetectors) {
        this.numberOfFilters = numberOfFilters;
        this.numberOfHeaters = numberOfHeaters;
        this.numberOfMixers = numberOfMixers;
        this.numberOfDetectors = numberOfDetectors;
    }

    public int getNumberOfFilters() {
        return numberOfFilters;
    }

    public int getNumberOfHeaters() {
        return numberOfHeaters;
    }

    public int getNumberOfMixers() {
        return numberOfMixers;
    }

    public int getNumberOfDetectors() {
        return numberOfDetectors;
    }

    public int getFilterDefaultTime() {
        return filterDefaultTime;
    }

    public int getHeaterDefaultTime() {
        return heaterDefaultTime;
    }

    public int getMixerDefaultTime() {
        return mixerDefaultTime;
    }

    public int getDetectorDefaultTime() {
        return detectorDefaultTime;
    }

    public int getInputDefaultTime() {
        return inputDefaultTime;
    }
}
