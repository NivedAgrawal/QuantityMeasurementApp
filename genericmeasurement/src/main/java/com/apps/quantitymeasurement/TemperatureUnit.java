package com.apps.quantitymeasurement;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(value -> value, false),
    FAHRENHEIT(f -> (f - 32) * 5/9, false),
    KELVIN(k -> k - 273.15, false);

    private final Function<Double, Double> toCelsius;
    private final boolean supportsArithmetic;

    TemperatureUnit(Function<Double, Double> toCelsius, boolean supportsArithmetic) {
        this.toCelsius = toCelsius;
        this.supportsArithmetic = supportsArithmetic;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        // Convert Celsius → this unit
        switch (this) {
            case CELSIUS: return baseValue;
            case FAHRENHEIT: return baseValue * 9/5 + 32;
            case KELVIN: return baseValue + 273.15;
            default: throw new IllegalStateException("Unknown unit");
        }
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
            "Temperature does not support " + operation + " operation."
        );
    }

    public boolean supportsArithmetic() {
        return supportsArithmetic;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // placeholder for temperature (formula-based)
    }
}