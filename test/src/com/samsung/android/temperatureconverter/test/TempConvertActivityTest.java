package com.samsung.android.temperatureconverter.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.samsung.android.temperatureconverter.R;
import com.samsung.android.temperatureconverter.TempConvertActivity;

public class TempConvertActivityTest extends
ActivityInstrumentationTestCase2<TempConvertActivity> {

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

	public void testDummy() {
		assertTrue(true);
	}

}
