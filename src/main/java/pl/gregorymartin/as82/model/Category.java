package pl.gregorymartin.as82.model;

public enum Category {
    HOME("Home"),
    WORK("Work"),
    LEISURE("Leisure");

    private final String displayValue;

    Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override public String toString() { return displayValue; }
}
