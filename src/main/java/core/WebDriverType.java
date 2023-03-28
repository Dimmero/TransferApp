package core;

public enum WebDriverType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private String driverName;

    WebDriverType(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }
}
