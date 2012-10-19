package com.samsung.android.temperatureconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

import com.samsung.android.temperatureconverter.TempConverter.OP;

public class TempConvertActivity extends Activity {

	private EditText txtCelcius;
	private EditText txtFahrenheit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp_convert);

		txtCelcius = (EditText) findViewById(R.id.txt_celcius);
		txtFahrenheit = (EditText) findViewById(R.id.txt_fahrenheit);

		txtCelcius.addTextChangedListener(new TempChangedWatcher(OP.C2F));
		txtFahrenheit.addTextChangedListener(new TempChangedWatcher(OP.F2C));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.temp_convert, menu);
		return true;
	}

	public class TempChangedWatcher implements TextWatcher {

		private static final String EMPTY_STRING = "";
		private EditText txtSource;
		private EditText txtDest;
		private final TempConverter.OP op;

		public TempChangedWatcher(OP op) {
			this.op = op;
			if (op == OP.C2F) {
				txtSource = txtCelcius;
				txtDest = txtFahrenheit;
			} else {
				txtSource = txtFahrenheit;
				txtDest = txtCelcius;
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!txtDest.hasWindowFocus() || txtDest.hasFocus() || s == null) {
				return;
			}

			if (EMPTY_STRING.equals(s.toString())) {
				txtDest.setText(EMPTY_STRING);
				return;
			}

			try {
				double temp = Double.parseDouble(s.toString());
				Double result;
				if (op == OP.C2F) {
					result = TempConverter.celsiusToFahrenheit(temp);
				} else {
					result = TempConverter.fahrenheitToCelcius(temp);
				}

				String resultStr = String.format("%.2f", result.doubleValue());

				txtDest.setText(resultStr);
			}
			catch(NumberFormatException ignored) {}
			catch(Exception ex) {
				txtSource.setError(ex.getMessage());
			}

		}

		@Override
		public void afterTextChanged(Editable s) {}

	}
}
