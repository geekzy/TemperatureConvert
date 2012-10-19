package com.samsung.android.temperatureconverter.exception;

public class InvalidTemperatureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidTemperatureException(String msg) {
		super(msg);
	}
}
