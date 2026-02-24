package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {


    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var yard = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.QuantityLength(
                3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(yard, feet);
        assertEquals(feet, yard);
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var yard = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var inch = new QuantityMeasurementApp.QuantityLength(
                36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(yard, inch);
    }


    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        var cm = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        var inch = new QuantityMeasurementApp.QuantityLength(
                0.393701, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(cm, inch);
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalent() {
        var cm = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        var feet = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertNotEquals(cm, feet);
    }

    @Test
    void testEquality_TransitiveProperty() {

        var yard = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.QuantityLength(
                3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.QuantityLength(
                36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testEquality_SameReference() {
        var q = new QuantityMeasurementApp.QuantityLength(
                2.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(q, q);
    }

    @Test
    void testEquality_NullComparison() {
        var q = new QuantityMeasurementApp.QuantityLength(
                2.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertNotEquals(q, null);
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(1.0, null));
    }
}