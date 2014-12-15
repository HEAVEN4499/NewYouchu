package com.shfc.youchu.service;

import com.shfc.youchu.CashOnDeliverySucessfulActivity;
import com.shfc.youchu.R;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

public class PushService extends Service {

	private NotificationManager nm;
	
	private String noteTitle = "邮储到付";
	private String notiContent;
	
	private Context context;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("push_service", "push_service onCreate");  
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public void onStart(Intent intent,int startId) {
		super.onStart(intent, startId);
		Log.i("push_service", "push_service start");  
        
		Intent rIntent = new Intent(this, CashOnDeliverySucessfulActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(CashOnDeliverySucessfulActivity.class);
		stackBuilder.addNextIntent(rIntent);
		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
		
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		Builder builder = new Notification.Builder(context);
		builder.setContentIntent(pendingIntent)
			.setSmallIcon(R.drawable.ic_launcher)
			.setVibrate(new long[]{1000})
			.setLights(Color.WHITE, 2000, 2000)
			.setWhen(System.currentTimeMillis())
			.setAutoCancel(true)
			.setContentTitle(noteTitle)
			.setContentText(notiContent);
		Notification notification = builder.build();
		nm.notify(1, notification);
	}
	
	@Override  
	public void onDestroy()  
	{  
        super.onDestroy();  
        Log.i("push_service", "push_service destroy");  
    }  
	
	public NotificationManager getNotificationManger() {
		return nm;
	}
}
