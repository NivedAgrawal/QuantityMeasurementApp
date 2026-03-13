package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        //Existing Length, Weight, Volume 
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);
        System.out.println(l1.equals(l2)); // true

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);
        System.out.println(w1.equals(w2)); // true

        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        System.out.println(v1.equals(v2)); // true

        Quantity<LengthUnit> l3 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l4 = new Quantity<>(6, LengthUnit.INCHES);
        System.out.println(l3.subtract(l4));              // 9.5 FEET
        System.out.println(l3.subtract(l4, LengthUnit.INCHES)); // 114 INCHES
        System.out.println(l3.divide(new Quantity<>(2, LengthUnit.FEET))); // 5.0

        // Temperature
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t3 = new Quantity<>(273.15, TemperatureUnit.KELVIN);

        System.out.println(t1.equals(t2)); // true (0°C = 32°F)
        System.out.println(t1.equals(t3)); // true (0°C = 273.15K)
        System.out.println(t2.equals(t3)); // true (32°F = 273.15K)

        // Conversion examples
        System.out.println(t1.convertTo(TemperatureUnit.FAHRENHEIT)); // 32°F
        System.out.println(t2.convertTo(TemperatureUnit.CELSIUS));    // 0°C
        System.out.println(t3.convertTo(TemperatureUnit.CELSIUS));    // 0°C

        // Unsupported operations
        try {
            t1.add(t2, TemperatureUnit.CELSIUS);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage()); // Temperature does not support addition
        }

        try {
            t1.subtract(t2);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage()); // Temperature does not support subtraction
        }

        try {
            t1.divide(t2);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage()); // Temperature does not support division
        }
    }
}