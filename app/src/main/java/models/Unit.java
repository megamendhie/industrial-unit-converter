package models;

public class Unit {
    private String name;
    private String unit;
    private double value;

    public Unit(String name, String unit, double value){
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
