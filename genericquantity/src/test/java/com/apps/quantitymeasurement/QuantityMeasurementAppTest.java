package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var q2 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        var feet = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var inch = new QuantityMeasurementApp.QuantityLength(
                12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(feet, inch);
        assertEquals(inch, feet); // symmetry
    }

    @Test
    void testEquality_DifferentValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.QuantityLength(
                2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertNotEquals(q1, q2);
    }

    @Test
    void testEquality_SameReference() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(q1, q1);
    }

    @Test
    void testEquality_NullComparison() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertNotEquals(q1, null);
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
}