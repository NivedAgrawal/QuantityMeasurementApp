package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ENUM FOR UNITS
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
    }


    // GENERIC QUANTITY CLASS

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
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

            return Double.compare(
                    this.convertToBase(),
                    other.convertToBase()
            ) == 0;
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


    public static void main(String[] args) {

        var q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        var q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: " + q1 + " and " + q2);
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        var q3 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        var q4 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Input: " + q3 + " and " + q4);
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");
    }
}