package com.samsung.android.temperatureconverter.test;

import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertRightAligned;

import java.util.concurrent.CountDownLatch;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samsung.android.temperatureconverter.R;
import com.samsung.android.temperatureconverter.TempConvertActivity;
import com.samsung.android.temperatureconverter.TempConverter;

public class TempConvertActivityTest extends
ActivityInstrumentationTestCase2<TempConvertActivity> {
	protected static final String EMPTY_STRING = "";

	protected final CountDownLatch signal = new CountDownLatch(1);

	private TempConvertActivity mActivity;
	private TextView mLblTitle;
	private TextView mLblCelcius;
	private TextView mLblFahrenheit;
	private EditText mCelcius;
	private EditText mFahrenheit;

	public TempConvertActivityTest() {
		super(TempConvertActivity.class);
		setName("TempConvertActivityTest");
	}

	public TempConvertActivityTest(Class<TempConvertActivity> activityClass) {
		super(activityClass);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		mActivity = getActivity();
		assertNotNull(mActivity);

		mLblTitle = (TextView) mActivity.findViewById(R.id.lbl_title);
		mLblCelcius = (TextView) mActivity.findViewById(R.id.lbl_celcius);
		mLblFahrenheit = (TextView) mActivity.findViewById(R.id.lbl_fahrenheit);
		mCelcius = (EditText) mActivity.findViewById(R.id.txt_celcius);
		mFahrenheit = (EditText) mActivity.findViewById(R.id.txt_fahrenheit);

		// precondition check
		assertNotNull(mLblTitle);
		assertNotNull(mLblCelcius);
		assertNotNull(mLblFahrenheit);
		assertNotNull(mCelcius);
		assertNotNull(mFahrenheit);
	}

	public final void testFormTitle() {
		final String TITLE = "Enter Temperature";
		assertEquals(TITLE, mLblTitle.getText().toString());
	}

	public final void testLabelAlignment() {
		assertLeftAligned(mLblCelcius, mLblFahrenheit);
		assertLeftAligned(mLblTitle, mLblCelcius);
		assertLeftAligned(mCelcius, mFahrenheit);
		assertRightAligned(mCelcius, mFahrenheit);
	}

	public final void testHasInput() {
		View origin = mActivity.getWindow().getDecorView();

		assertOnScreen(origin, mCelcius);
		assertOnScreen(origin, mFahrenheit);
	}

	public final void testInputIsEmpty() {
		final String EMPTY_STR = "";

		assertEquals(EMPTY_STR, mCelcius.getText().toString());
		assertEquals(EMPTY_STR, mFahrenheit.getText().toString());
	}

	public final void testInputIsDecimal() throws Exception {
		final String expected = "-5.55";

		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mCelcius.requestFocus();
				mCelcius.setText(EMPTY_STRING);
			}
		});
		sendKeys("MINUS 5 NUMPAD_DOT 5 5");
		assertEquals(expected, mCelcius.getText().toString());

		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mFahrenheit.requestFocus();
				mFahrenheit.setText(EMPTY_STRING);
			}
		});
		sendKeys("MINUS 5 NUMPAD_DOT 5 5");
		assertEquals(expected, mFahrenheit.getText().toString());
	}

	public final void testInputJustification() {
		final int expected = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
		assertEquals(expected, mCelcius.getGravity());
		assertEquals(expected, mFahrenheit.getGravity());
	}

	public final void testVirtualKeyboardSpaceReserved() {
		final int expected = 280;
		final int actual = mFahrenheit.getBottom();

		assertTrue(actual <= expected);
	}

	public final void testRetainValue() {
		final String expected = "-5.55";
		Instrumentation mInstr = this.getInstrumentation();

		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mCelcius.requestFocus();
			}
		});
		sendKeys("MINUS 5 NUMPAD_DOT 5 5");

		// pause it
		mInstr.callActivityOnPause(mActivity);

		// check it
		assertEquals(expected, mCelcius.getText().toString());

		// resume it
		mInstr.callActivityOnResume(mActivity);

		// check it
		assertEquals(expected, mCelcius.getText().toString());

	}

	public final void testConvertFahrenheitToCelcius() {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mFahrenheit.requestFocus();
			}
		});
		sendKeys("5 5");

		final Double expected = TempConverter.fahrenheitToCelcius(55d);
		final Double actual = Double.valueOf(mCelcius.getText().toString());
		assertEquals(expected, actual);
	}

	public final void testConvertCelciusToFahrenheit() {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mCelcius.requestFocus();
			}
		});
		sendKeys("5 NUMPAD_DOT 5");

		final Double expected = TempConverter.celsiusToFahrenheit(5.5d);
		final Double actual = Double.valueOf(mFahrenheit.getText().toString());
		assertEquals(expected, actual);
	}
}
