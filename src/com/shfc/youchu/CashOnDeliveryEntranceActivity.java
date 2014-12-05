package com.shfc.youchu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CashOnDeliveryEntranceActivity extends Activity {
	/**
	 * 本界面的context，用于startActivity等
	 */
	private Context context;
	/**
	 * 选择快递员按钮，点击进入查询确认界面
	 */
	private Button buttonTake;
	/**
	 * 选择消费者按钮，点击进入查询确认界面
	 */
	private Button buttonReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cash_on_delivery_entrance);
		
		context = this;
		
		buttonTake = (Button) findViewById(R.id.cod_take_good_btn);
		buttonTake.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, CashOnDeliveryDeliverEntranceActivity.class);
				context.startActivity(intent);
//				((Activity) context).finish();
			}
		});
		
		buttonReturn = (Button) findViewById(R.id.cod_return_good_btn);
		buttonReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cash_on_delivery_entrance, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
