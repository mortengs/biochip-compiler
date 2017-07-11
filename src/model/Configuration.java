package model;

final class Configuration {
    // DEFAULT RUN TIME OF COMPONENTS
    private static int filterDefaultTime = 15;
    private static int heaterDefaultTime = 15;
    private static int mixerDefaultTime = 15;
    private static int detectorDefaultTime = 1;
    private static int inputDefaultTime = 1;
    private static MixerType mixerType = MixerType.ONE_TO_ONE;

    public static MixerType getMixerType() {
        return mixerType;
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
