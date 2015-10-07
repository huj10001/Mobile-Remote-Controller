package com.example.remotecontrolserver;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 



public class Qrcode extends Activity {
	
	private TextView et;
	private ImageView qrimage; 
	private Serializable user;
	
	private ServerService sese;
	private Intent mi;
	
	private Receiver1 recv;
	
	Handler handler = null;
	public final static int MSG_FIRST_RECV = 0x01;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrcode);
		
		et = (TextView) this.findViewById(R.id.ip);
		qrimage = (ImageView) this.findViewById(R.id.qr); 
		 
		/* check wifi status */
		 WifiManager wfm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		 if(!wfm.isWifiEnabled()){
			 wfm.setWifiEnabled(true);
		 }
		 WifiInfo wfi = wfm.getConnectionInfo();
		 /* get ip address */
		 int ipaddr = wfi.getIpAddress();
		 String addr = IntToIp(ipaddr);
		 et.setText("±¾»úIP£º " + addr);
		 
		 Bitmap bmp = null;
		 String str = addr;
		 try {
			if(str != null && !"".equals(str)){
				bmp = CreateTwoDCode(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(bmp != null){
			qrimage.setImageBitmap(bmp);
		}
		 
		 /* generate qr code image */
//		 try{
//		 String text = et.getText().toString();  
//		 QRCodeWriter writer = new QRCodeWriter();
//		 BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE,300,300);
//
//		 
//		 int QR_WIDTH = matrix.getWidth();
//         int QR_HEIGHT = matrix.getHeight();
//
//         Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//         hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//         BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                 BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
//         int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//         for (int y = 0; y < QR_HEIGHT; y++) {
//             for (int x = 0; x < QR_WIDTH; x++) {
//                 if (bitMatrix.get(x, y)) {
//                     pixels[y * QR_WIDTH + x] = 0xff000000;
//                 } else {
//                     pixels[y * QR_WIDTH + x] = 0xffffffff;
//                 }
//
//             }
//         }
//
//         Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
//                 Bitmap.Config.ARGB_8888);
//
//         bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//         qrimage.setImageBitmap(bitmap);}
//		 catch(WriterException e){
//			 e.printStackTrace();
//		 }
////         if (!contentString.equals("")) {  
////             Bitmap qrCodeBitmap = null;
////			try {
////				Bitmap qrCodeBitmap;
////				try {
////					qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
////					qrimage.setImageBitmap(qrCodeBitmap); 
////				} catch (WriterException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//////			} catch (WriterException e) {
//////				// TODO Auto-generated catch block
//////				e.printStackTrace();
//////			}  
//////             qrimage.setImageBitmap(qrCodeBitmap);  
//////         }else {  
//////             Toast.makeText(this, "Text can not be empty", Toast.LENGTH_SHORT).show();  
//////         }  
		
		/* switch to gesture activity */
		
//		mi = new Intent();
//		if(mi.setAction("com.example.remotecontrolserver.ServerService") ){
//			Intent intent = new Intent();
//			intent.setClass(Qrcode.this, MainActivity.class);
//			startActivity(intent);
//		}
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what)				
				{
				case MSG_FIRST_RECV:
					Intent intent = new Intent();
					intent.setClass(Qrcode.this, MainActivity.class);
//					Bundle bundle = new Bundle();
//					
//					bundle.putSerializable("user", user);
//					intent.putExtras(intent);
					startActivity(intent);
					break;
				}
			}
			
		};
		
		/********** service add ********/
		Intent intent = new Intent();
		intent.setAction("com.example.remotecontrolserver.SESE");
		startService(intent);
		
		recv = new Receiver1();
		recv.setHandler(handler);
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.remotecontrolserver.FIRST_RECV");
		registerReceiver(recv, filter);
		
//		Intent intent2 = new Intent();
//		intent2.setClass(Qrcode.this, MainActivity.class);
//		
//		startActivity(intent);
		/********************************/
		
//		new Thread(){
//			@Override
//			public void run() {
//				try {
//					ServerSocket ss = new ServerSocket(8888);
//					
//					System.out.println("WAITING FOR CONNECTION!");
//					
////					Intent intent = new Intent();
////					intent.setAction("com.example.remotecontrolserver.SESE");
////					startService(intent);
////					intent.
////					Socket sock = ss.accept();
//					if(ss.accept() != null){
//						Intent intent = new Intent();
//						intent.setClass(Qrcode.this, MainActivity.class);
////						Bundle bundle = new Bundle();
////						
////						bundle.putSerializable("user", user);
////						intent.putExtras(intent);
//						startActivity(intent);
//						ss.close();
//					}
//					else{
//						System.out.println("LOSER~~~~");
//					}
////					
//				}catch(IOException e){
//						e.printStackTrace();
//					}
//			}
//		}.start();
		
		
		
	}
	
	
@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(recv);
	}


	//
	private String IntToIp(int i) {
		// TODO Auto-generated method stub
		return (i & 0xFF ) + "." +       
        ((i >> 8 ) & 0xFF) + "." +       
        ((i >> 16 ) & 0xFF) + "." +       
        ( i >> 24 & 0xFF) ;  
	}
	
	public Bitmap CreateTwoDCode(String content) throws WriterException{
		BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		for(int y=0; y< height; y++){
			for(int x = 0; x<width; x++){
				if(matrix.get(x, y)){
					pixels[y*width+x]= 0xff000000;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qrcode, menu);
		return true;
	}
	
	

}
