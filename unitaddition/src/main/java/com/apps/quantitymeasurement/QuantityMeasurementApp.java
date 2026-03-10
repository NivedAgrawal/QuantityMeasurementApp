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

        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double baseThis = this.unit.toFeet(this.value);
            double baseOther = other.unit.toFeet(other.value);

            double baseSum = baseThis + baseOther;

            double resultValue = this.unit.fromFeet(baseSum);

            return new QuantityLength(resultValue, this.unit);
        }

        public static QuantityLength add(
                QuantityLength q1,
                QuantityLength q2,
                LengthUnit targetUnit) {

            if (q1 == null || q2 == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base1 = q1.unit.toFeet(q1.value);
            double base2 = q2.unit.toFeet(q2.value);

            double baseSum = base1 + base2;

            double resultValue = targetUnit.fromFeet(baseSum);

            return new QuantityLength(resultValue, targetUnit);
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

    public static void main(String[] args) {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETERS);


        System.out.println("1 FEET + 2 FEET = "
                + feet.add(new QuantityLength(2.0, LengthUnit.FEET)));

        System.out.println("1 FEET + 12 INCH = "
                + feet.add(inches));

        System.out.println("12 INCH + 1 FEET = "
                + inches.add(feet));

        System.out.println("1 YARD + 3 FEET = "
                + yards.add(new QuantityLength(3.0, LengthUnit.FEET)));

        System.out.println("2.54 CM + 1 INCH = "
                + cm.add(new QuantityLength(1.0, LengthUnit.INCH)));

        System.out.println("5 FEET + 0 INCH = "
                + new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCH)));

        System.out.println("5 FEET + (-2 FEET) = "
                + new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET)));
    }
}