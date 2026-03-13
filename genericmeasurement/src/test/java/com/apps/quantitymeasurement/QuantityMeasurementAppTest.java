package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // -------------------- VOLUME --------------------

    @Test
    void testVolumeEquality() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v4 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v5 = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertEquals(v1, v2);
        assertEquals(v4, v5);
        assertNotEquals(v1, new Quantity<>(2.0, VolumeUnit.LITRE));
    }

    @Test
    void testVolumeConversion() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = litre.convertTo(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);

        assertEquals(1000.0, millilitre.getValue(), EPSILON);
        assertEquals(3.78541, gallon.getValue(), EPSILON);
    }

    @Test
    void testVolumeArithmetic() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> sum = v1.add(v2);
        Quantity<VolumeUnit> sumInMl = v1.add(v2, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> diff = v1.subtract(v2);
        double quotient = v1.divide(new Quantity<>(0.5, VolumeUnit.LITRE));

        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(2000.0, sumInMl.getValue(), EPSILON);
        assertEquals(0.0, diff.getValue(), EPSILON);
        assertEquals(2.0, quotient, EPSILON);
    }

    // -------------------- LENGTH --------------------

    @Test
    void testLengthEquality() {
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);

        assertEquals(l1, l2);
    }

    @Test
    void testLengthArithmetic() {
        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = l1.add(l2, LengthUnit.FEET);
        Quantity<LengthUnit> diff = l1.subtract(l2);
        double div = l1.divide(new Quantity<>(2, LengthUnit.FEET));

        assertEquals(10.5, sum.getValue(), EPSILON);
        assertEquals(9.5, diff.getValue(), EPSILON);
        assertEquals(5.0, div, EPSILON);
    }

    // -------------------- WEIGHT --------------------

    @Test
    void testWeightEquality() {
        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testWeightArithmetic() {
        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(500, WeightUnit.GRAM);

        Quantity<WeightUnit> sum = w1.add(w2);
        Quantity<WeightUnit> diff = w1.subtract(w2);
        double div = w1.divide(new Quantity<>(0.5, WeightUnit.KILOGRAM));

        assertEquals(1.5, sum.getValue(), EPSILON);
        assertEquals(0.5, diff.getValue(), EPSILON);
        assertEquals(2.0, div, EPSILON);
    }

    // -------------------- TEMPERATURE --------------------

    @Test
    void testTemperatureEquality() {
        Quantity<TemperatureUnit> tC = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> tF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> tK = new Quantity<>(273.15, TemperatureUnit.KELVIN);

        assertEquals(tC, tF);
        assertEquals(tC, tK);
        assertEquals(tF, tK);
    }

    @Test
    void testTemperatureConversion() {
        Quantity<TemperatureUnit> tC = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> tF = tC.convertTo(TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> tK = tC.convertTo(TemperatureUnit.KELVIN);

        assertEquals(212.0, tF.getValue(), EPSILON);
        assertEquals(373.15, tK.getValue(), EPSILON);
    }

    @Test
    void testTemperatureUnsupportedOperations() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> t1.add(t2, TemperatureUnit.CELSIUS));
        assertThrows(UnsupportedOperationException.class, () -> t1.subtract(t2));
        assertThrows(UnsupportedOperationException.class, () -> t1.divide(t2));
    }

    // -------------------- CROSS-CATEGORY SAFETY --------------------

    @Test
    void testCrossCategoryInequality() {
        Quantity<TemperatureUnit> temp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> length = new Quantity<>(100.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(50.0, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> volume = new Quantity<>(25.0, VolumeUnit.LITRE);

        assertNotEquals(temp, length);
        assertNotEquals(temp, weight);
        assertNotEquals(temp, volume);
    }

    @Test
    void testCrossCategoryArithmeticThrows() {
        Quantity<LengthUnit> l = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> l.add((Quantity) w, LengthUnit.FEET));
    }
}