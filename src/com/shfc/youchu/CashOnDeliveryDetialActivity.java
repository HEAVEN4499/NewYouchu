package com.shfc.youchu;

import com.shfc.youchu.R.id;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CashOnDeliveryDetialActivity extends Activity {
	
	private Button buttonGoBack;
	private Button cashButton;
	private Button cardButton;
	
	private TextView idTxt;
	private TextView nameTxt;
	private TextView adapterTxt;
	private TextView addressTxt;
	
	private Context context;
	
	private int purchaseType;
	private int nextPage = -1;
	
	private String scanResult;
	
	private int[] indexOfResult = new int[4];
	
	@SuppressWarnings("unused")
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cash_on_delivery_detial);
		
		Log.i("code","Detial on create" + this.getIntent().getStringExtra("result"));
		context = this;
		
		try {
			purchaseType = Integer.parseInt(this.getIntent().getStringExtra("result").substring(0, 1));
		} catch (NumberFormatException e) {
			purchaseType = -1;
		}
		Log.i("purchase type", String.valueOf(purchaseType));
		
		scanResult = this.getIntent().getStringExtra("result").substring(1);
		Log.i("scan result", scanResult);
		
        if (purchaseType == -1 || scanResult.equals(null)) {
        	Toast.makeText(CashOnDeliveryDetialActivity.this, "获取业务类型数据失败!", Toast.LENGTH_SHORT).show();
        	onBackPressed();
        } else {
        	idTxt = (TextView) findViewById(R.id.txt_good_id);
        	nameTxt = (TextView) findViewById(R.id.txt_good_name);
        	adapterTxt = (TextView) findViewById(R.id.txt_good_customer);
        	addressTxt = (TextView) findViewById(R.id.txt_good_to);
        	
        	if (isNumber(scanResult)) {
     			String id = scanResult;
     			idTxt.setText(id);
     		} else if (scanResult.substring(0, scanResult.indexOf("&")).equals("List")) {
    			indexOfResult[0] = scanResult.indexOf("&");
    			indexOfResult[1] = scanResult.indexOf("&", indexOfResult[0] + 1);
    			indexOfResult[2] = scanResult.indexOf("&", indexOfResult[1] + 1);
    			indexOfResult[3] = scanResult.indexOf("&", indexOfResult[2] + 1);
    			
    			String id	   = scanResult.substring(indexOfResult[0] + 1, scanResult.indexOf("&", indexOfResult[1]));
    			String name	   = scanResult.substring(indexOfResult[1] + 1, scanResult.indexOf("&", indexOfResult[2]));
    			String adapter = scanResult.substring(indexOfResult[2] + 1, scanResult.indexOf("&", indexOfResult[3]));
    			String address = scanResult.substring(indexOfResult[3] + 1);
    			
    			try {
    				int idInt = Integer.parseInt(id);
    			} catch (NumberFormatException e) {
    				id = "Null";
    				name = "Null";
    				adapter = "Null";
    				address = "Null";
    				onBackPressed();
    				((Activity) context).finish();
    			}
    			
    			idTxt.setText(id);
    			nameTxt.setText(name);
    			adapterTxt.setText(adapter);
    			addressTxt.setText(address);
    		} else {
    			idTxt.setText("Null");
				onBackPressed();
				((Activity) context).finish();
    		}
        }
        
		cashButton = (Button) findViewById(R.id.btn_activity_detial_cash);
		cashButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nextPage = 0;
				goToNextPage(nextPage);
			}
		});
		
		cardButton = (Button) findViewById(R.id.btn_activity_detial_creditcard);
		cardButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nextPage = 1;
				goToNextPage(nextPage);
			}
		});
		
		buttonGoBack = (Button) findViewById(R.id.cod_detial_goback_btn);
		buttonGoBack.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	
	public boolean isNumber(String str) {
		try {
			Integer.parseInt(str.substring(0, str.length() / 2));
			Integer.parseInt(str.substring(str.length() / 2));
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public void goToNextPage(int nextPage) {
		if (nextPage == -1) {
			return;
		}
			
		Intent intent = null;
		if (nextPage == 0) {
			intent = new Intent(CashOnDeliveryDetialActivity.this, CameraActivity.class);
		} else if (nextPage == 1) {
			intent = new Intent(CashOnDeliveryDetialActivity.this, DigitPasswordKeyboardActivity.class);
		}
		
		context.startActivity(intent);
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
