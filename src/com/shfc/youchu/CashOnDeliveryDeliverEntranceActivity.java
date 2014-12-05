package com.shfc.youchu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CashOnDeliveryDeliverEntranceActivity extends Activity {
	/**
	 * 本界面的context，用于startActivity等
	 */
	private Context context;
	/**
	 * 返回按钮
	 */
	private Button buttonGoBack;
	/**
	 * 选择收货按钮，点击进入查询确认界面
	 */
	private Button buttonTake;
	/**
	 * 选择退货按钮，点击进入查询确认界面
	 */
	private Button buttonReturn;
	/**
	 * 选择收货为0，退货为1，默认-1
	 */
	private int selectChoice = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deliver_entrance_cash_on_delivery);
		
		context = this;
		
		buttonGoBack = (Button) findViewById(R.id.cod_delivery_goback_btn);
		buttonGoBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		buttonTake = (Button) findViewById(R.id.cod_take_good_btn);
		buttonTake.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectChoice = 0;
				nextPage(selectChoice);
			}
		});
		
		buttonReturn = (Button) findViewById(R.id.cod_return_good_btn);
		buttonReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectChoice = 1;
				nextPage(selectChoice);
			}
		});
	}
	
	protected void nextPage(int choice) {
		if (selectChoice != -1) {
			Intent intent = new Intent(context, BarCodeTestActivity.class);
			intent.putExtra("selectChoise", selectChoice);
			context.startActivity(intent);
			overridePendingTransition(0,0);
//		   	((Activity) context).finish();
		}
	}
}
