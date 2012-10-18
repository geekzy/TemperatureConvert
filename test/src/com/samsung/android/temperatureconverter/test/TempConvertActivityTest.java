package com.samsung.android.temperatureconverter.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.samsung.android.temperatureconverter.R;
import com.samsung.android.temperatureconverter.TempConvertActivity;

public class TempConvertActivityTest extends
ActivityInstrumentationTestCase2<TempConvertActivity> {

	private TempConvertActivity mActivity;

	public TempConvertActivityTest() {
		super(TempConvertActivity.class);
		setName("TempConvertActivityTest");
	}

	public TempConvertActivityTest(Class<TempConvertActivity> activityClass) {
		super(activityClass);
	}

	@Override
	public void setUp() {
		mActivity = getActivity();
		assertNotNull(mActivity);
	}

	public void testlayout() {
		TextView hello = (TextView) mActivity.findViewById(R.id.hello);
		assertEquals("Hello world!", hello.getText().toString());
	}

}
