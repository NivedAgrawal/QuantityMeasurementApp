package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    // FEET CLASS
    public static class Feet {
        private final double value;

        public Feet(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Feet value must be numeric");
            }
            this.value = value;
        }

        public double toInches() {
            return value * 12;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;

            if (obj instanceof Feet) {
                Feet other = (Feet) obj;
                return Double.compare(this.value, other.value) == 0;
            }

            if (obj instanceof Inches) {
                Inches other = (Inches) obj;
                return Double.compare(this.toInches(), other.value) == 0;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toInches());
        }

        @Override
        public String toString() {
            return value + " ft";
        }
    }

    // INCHES CLASS
    public static class Inches {
        private final double value;

        public Inches(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Inches value must be numeric");
            }
            this.value = value;
        }

        public double toFeet() {
            return value / 12;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;

            if (obj instanceof Inches) {
                Inches other = (Inches) obj;
                return Double.compare(this.value, other.value) == 0;
            }

            if (obj instanceof Feet) {
                Feet other = (Feet) obj;
                return Double.compare(this.value, other.toInches()) == 0;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }

        @Override
        public String toString() {
            return value + " inch";
        }
    }


    public static boolean checkFeetEquality(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    public static boolean checkInchesEquality(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
        return i1.equals(i2);
    }

    public static boolean checkFeetToInchesEquality(double feet, double inches) {
        Feet f = new Feet(feet);
        Inches i = new Inches(inches);
        return f.equals(i);
    }



    public static void main(String[] args) {

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" +
                checkInchesEquality(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" +
                checkFeetEquality(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 ft and 12.0 inch");
        System.out.println("Output: Equal (" +
                checkFeetToInchesEquality(1.0, 12.0) + ")");
    }
}