package model;

/**
 * Created by Jesper on 16/06/2017.
 */
public final class DefaultValues {
    // DEFAULT RUN TIME OF COMPONENTS
    private static int filterDefaultTime = 15;
    private static int heaterDefaultTime = 15;
    private static int mixerDefaultTime = 15;
    private static int detectorDefaultTime = 1;
    private static int inputDefaultTime = 1;

    private DefaultValues() {
    }

    static int getFilterDefaultTime() {
        return filterDefaultTime;
    }

    static int getHeaterDefaultTime() {
        return heaterDefaultTime;
    }

    static int getMixerDefaultTime() {
        return mixerDefaultTime;
    }

    static int getDetectorDefaultTime() {
        return detectorDefaultTime;
    }

    static int getInputDefaultTime() {
        return inputDefaultTime;
    }
}
