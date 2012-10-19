package com.samsung.android.temperatureconverter.test;

import junit.framework.TestCase;

import com.samsung.android.temperatureconverter.TempConverter;

public class TempConverterTest extends TestCase {

	public TempConverterTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testConvertCelciusToFahrenheit() {
		final Double expected = new Double(50.00f);
		assertEquals(expected, TempConverter.celsiusToFahrenheit(5.00f));
	}

	public void testConvertFahrenheitToCelcius() {
		final Double expected = new Double(5.00f);
		assertEquals(expected, TempConverter.fahrenheitToCelcius(50.00f));
	}

}
