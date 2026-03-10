package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084167);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {

        private static final double EPSILON = 1e-6;

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite numeric");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = unit.toFeet(value);
            double convertedValue = targetUnit.fromFeet(baseValue);

            return new QuantityLength(convertedValue, targetUnit);
        }

        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite numeric");
            }

            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            double baseValue = source.toFeet(value);
            return target.fromFeet(baseValue);
        }

        private double convertToBase() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.convertToBase() - other.convertToBase()
            ) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(convertToBase());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void demonstrateLengthConversion(
            double value, LengthUnit from, LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(
            QuantityLength quantity, LengthUnit target) {

        QuantityLength converted = quantity.convertTo(target);
        System.out.println(quantity + " -> " + converted);
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCH);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCH);

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(q1, LengthUnit.INCH);

        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("Equality: " + q1 + " and " + q2 + " -> " + q1.equals(q2));
    }
}