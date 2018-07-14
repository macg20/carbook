package pl.emgie.carbook.feedstockservice.utils;

public enum FeedstockType {

    OIL("Oil"),
    DIESEL("Diesel"),
    PETROL("Petrol"),
    GAS("Gas");

    private String name;

    FeedstockType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
