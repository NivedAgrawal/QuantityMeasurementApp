package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.checkFeetEquality(1.0, 1.0));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkFeetEquality(1.0, 2.0));
    }

    @Test
    void testInchesEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.checkInchesEquality(5.0, 5.0));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkInchesEquality(5.0, 6.0));
    }

    @Test
    void testFeetToInchesEquality() {
        assertTrue(QuantityMeasurementApp.checkFeetToInchesEquality(1.0, 12.0));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(feet, null);
    }

    @Test
    void testNonNumericInput() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.Feet(Double.NaN));
    }

    @Test
    void testSameReference() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertEquals(feet, feet);
    }
}