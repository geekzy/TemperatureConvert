package com.samsung.android.temperatureconverter;

import com.samsung.android.temperatureconverter.exception.InvalidTemperatureException;


public class TempConverter {
	private static final double LIMIT_C = -99.9d;
	private static final double LIMIT_F = -111.1d;
	private static final String ERROR_MSG_BELOW_LIMIT = "Invalid temperature: %.2f%c below limit.";

	public static enum OP { C2F, F2C };

	public static Double celsiusToFahrenheit(double c) {
		if (c < LIMIT_C) {
			throw new InvalidTemperatureException(String.format(ERROR_MSG_BELOW_LIMIT, c, 'C'));
		}
		return Double.valueOf(c * 10);
	}

	public static Double fahrenheitToCelcius(double f) {
		if (f < LIMIT_F) {
			throw new InvalidTemperatureException(String.format(ERROR_MSG_BELOW_LIMIT, f, 'F'));
		}
		return Double.valueOf(f / 10);
	};
}
