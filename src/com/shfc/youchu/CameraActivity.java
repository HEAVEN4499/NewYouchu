package com.shfc.youchu;

import android.app.Activity;  
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;  
import android.provider.MediaStore;
import android.widget.ImageView;
  
public class CameraActivity extends Activity{  
  
	private Context context;
	
	final int TAKE_PICTURE = 1;
	ImageView iv;
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);   
        context = this;
        
       try { 
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
            startActivityForResult(intent, 0); 
       } catch (Exception e) { 
           System.out.println(e.getMessage());
       } 
    }  

    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    { 
        try { 
            if (requestCode != 0) { 
                return; 
            } 
            super.onActivityResult(requestCode, resultCode, data); 
            Bundle extras = data.getExtras(); 
            Bitmap b = (Bitmap) extras.get("data"); 
            /*
               得到图片对图片处理...
            */
            Intent intent = new Intent(CameraActivity.this, CashOnDeliverySucessfulActivity.class);
            context.startActivity(intent);   
            ((Activity) context).finish();
        } catch (Exception e) { 
            // TODO: handle exception 
            System.out.println(e.getMessage()); 
        } 
    } 
}  
