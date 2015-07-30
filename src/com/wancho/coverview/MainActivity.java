package com.wancho.coverview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCheckedChangeListener {
	
	private TextView tvRl;
	
	private TextView tvFl;
	
	private TextView tvLl;

	private CoverView coverViewRl;
	
	private CoverView coverViewFl;
	
	private CoverView coverViewLl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CheckBox cbRl = (CheckBox) findViewById(R.id.cb_rl);
		tvRl = (TextView) findViewById(R.id.tv_rl);
		cbRl.setOnCheckedChangeListener(this);

		CheckBox cbFl = (CheckBox) findViewById(R.id.cb_fl);
		tvFl = (TextView) findViewById(R.id.tv_fl);
		cbFl.setOnCheckedChangeListener(this);

		CheckBox cbLl = (CheckBox) findViewById(R.id.cb_ll);
		tvLl = (TextView) findViewById(R.id.tv_ll);
		cbLl.setOnCheckedChangeListener(this);
		initCoverViews();
	}
	
	/**
	 * ConverView由使用者指定
	 */
	private void initCoverViews() {
		TextView tv = new TextView(this);
		tv.setText(System.currentTimeMillis() + "");
		tv.setPadding(12, 12, 12, 12);
		tv.setGravity(Gravity.CENTER);
		tv.setBackgroundColor(Color.BLACK);
		coverViewRl = new CoverView(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		tv.setLayoutParams(lp);
		coverViewRl.addView(tv);
		
		tv = new TextView(this);
		tv.setText(System.currentTimeMillis() + "");
		tv.setPadding(12, 12, 12, 12);
		tv.setGravity(Gravity.CENTER);
		tv.setBackgroundColor(Color.BLACK);
		coverViewFl = new CoverView(this);
		lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		tv.setLayoutParams(lp);
		coverViewFl.addView(tv);
		
		tv = new TextView(this);
		tv.setText(System.currentTimeMillis() + "");
		tv.setPadding(12, 12, 12, 12);
		tv.setGravity(Gravity.CENTER);
		tv.setBackgroundColor(Color.BLACK);
		coverViewLl = new CoverView(this);
		lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		tv.setLayoutParams(lp);
		coverViewLl.addView(tv);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		switch (arg0.getId()) {
		case R.id.cb_rl:
			if(arg1) {
				coverViewRl.showTargetView(tvRl);
			} else {
				coverViewRl.hide();
			}
			break;
		case R.id.cb_fl:
			if(arg1) {
				coverViewFl.showTargetView(tvFl);
			} else {
				coverViewFl.hide();
			}
			break;
		case R.id.cb_ll:
			if(arg1) {
				coverViewLl.showTargetView(tvLl);
			} else {
				coverViewLl.hide();
			}
			break;

		default:
			break;
		}
	}
	
}
