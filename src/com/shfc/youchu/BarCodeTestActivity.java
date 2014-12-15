package com.shfc.youchu;

import com.zxing.activity.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BarCodeTestActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView resultTextView;
	private EditText qrStrEditText;
	private Button buttonGoBack;
	
	private Context context;
	private int purchaseType;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        context = this;
        purchaseType = this.getIntent().getIntExtra("selectChoise", -1);
        Log.i(getLocalClassName(), String.valueOf(purchaseType));
        
        if (purchaseType == -1) {
        	Intent intent = new Intent(context, CashOnDeliveryDeliverEntranceActivity.class);
        	Toast.makeText(this, "获取业务类型数据失败!", Toast.LENGTH_SHORT).show();
        	context.startActivity(intent);
        	BarCodeTestActivity.this.finish();
        }
        
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        
		buttonGoBack = (Button) findViewById(R.id.cod_barcode_goback_btn);
		buttonGoBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
        
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//打开扫描界面扫描条形码或二维码
				Intent openCameraIntent = new Intent(BarCodeTestActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
        
        Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				try {
					String contentString = qrStrEditText.getText().toString();
//					if (!contentString.equals("")) {
//						//根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
//						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
//						qrImgImageView.setImageBitmap(qrCodeBitmap);
//					}else {
//						Toast.makeText(BarCodeTestActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
//					}
//					
//				} catch (WriterException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				if (!contentString.equals("") || !resultTextView.getText().toString().equals("")) {
					nextPage(purchaseType + contentString);
				} else {
					Toast.makeText(context, "验证码不可为空", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }

	@SuppressLint("NewApi")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
			nextPage(purchaseType + scanResult);
			
			Intent rIntent = new Intent(context, CashOnDeliverySucessfulActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			stackBuilder.addParentStack(CashOnDeliverySucessfulActivity.class);
			stackBuilder.addNextIntent(rIntent);
			PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
			
			NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			String noteTitle = "邮储到付";
			Builder builder = new Notification.Builder(context);
			builder.setContentIntent(pendingIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setVibrate(new long[]{1000})
				.setLights(Color.WHITE, 2000, 2000)
				.setWhen(System.currentTimeMillis())
				.setAutoCancel(true)
				.setContentTitle(noteTitle)
				.setContentText(scanResult);
			Notification notification = builder.build();
			nm.notify(1, notification);
		}
	}
	
	protected void nextPage(String code) {
		Intent intent = new Intent(BarCodeTestActivity.this, CashOnDeliveryDetialActivity.class);
		intent.putExtra("result", code);
		context.startActivity(intent);
		Log.i(code,"Detial on create" + code);
		overridePendingTransition(0,0);
		((Activity) context).finish();
	}
}