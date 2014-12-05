package com.shfc.youchu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DigitPasswordKeyboardActivity extends Activity {
	
	private Context context;
	
	private Button button_01;
	private Button button_02;
	private Button button_03;
	private Button button_04;
	private Button button_05;
	private Button button_06;
	private Button button_07;
	private Button button_08;
	private Button button_09;
	private Button button_00;
	private Button button_cl;
	private Button button_ok;
	private Button goBackButton;
	
	private TextView password;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_digit_password_keyboard);
		
		context = this;
		
		password = (TextView) findViewById(R.id.digit_password_textview);
		password.setText("");
		
		Log.i("INFO", "DPKA START");
		
		button_01 = (Button) findViewById(R.id.digitkeypad_1);
		button_01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(1);
			}
		});
		
		button_02 = (Button) findViewById(R.id.digitkeypad_2);
		button_02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(2);
			}
		});
		button_03 = (Button) findViewById(R.id.digitkeypad_3);
		button_03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(3);
			}
		});
		
		button_04 = (Button) findViewById(R.id.digitkeypad_4);
		button_04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(4);
			}
		});
		
		button_05 = (Button) findViewById(R.id.digitkeypad_5);
		button_05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(5);
			}
		});
		
		button_06 = (Button) findViewById(R.id.digitkeypad_6);
		button_06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(6);
			}
		});
		
		button_07 = (Button) findViewById(R.id.digitkeypad_7);
		button_07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(7);
			}
		});
		
		button_08 = (Button) findViewById(R.id.digitkeypad_8);
		button_08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(8);
			}
		});
		
		button_09 = (Button) findViewById(R.id.digitkeypad_9);
		button_09.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(9);
			}
		});
		
		button_00 = (Button) findViewById(R.id.digitkeypad_0);
		button_00.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(0);
			}
		});
		
		button_cl = (Button) findViewById(R.id.digitkeypad_cl);
		button_cl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(-1);
			}
		});
		
		button_ok = (Button) findViewById(R.id.digitkeypad_ok);
		button_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				digitKeyboardAction(-2);
			}
		});
		
		goBackButton = (Button) findViewById(R.id.cod_keyboard_goback_btn);
		goBackButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		Log.i("INFO", "DPKA STARTED");
	}
	
	public void digitKeyboardAction(int buttonID) {
		if (buttonID >= 0 && password.getText().length() < 6) {
			password.setText(password.getText() + String.valueOf(buttonID));
		} else if (buttonID == -1) {
			password.setText(password.getText().toString().substring(0, password.getText().length() - 1));
		} else if (buttonID == -2) {
			Intent intent = new Intent(DigitPasswordKeyboardActivity.this, CameraActivity.class);
			context.startActivity(intent);
		} else if (password.getText().length() >= 6) {
			Toast.makeText(DigitPasswordKeyboardActivity.this, "密码应为六位", Toast.LENGTH_SHORT).show();
		} 
	}
}
