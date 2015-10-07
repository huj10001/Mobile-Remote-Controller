package qianyan.rc;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GestureActivity extends Activity implements OnGestureListener{
	
	String TAG = "GestureActivity";
	GestureDetector detector;
	final int distance = 25;
	final int speed = 2000;
	private Handler hdl = new Handler();
	private Instrumentation in = new Instrumentation();
	
	private final static int PREV = 0;
	private final static int NEXT = 1;
	private final static int LEFT = 2;
	private final static int RIGHT = 3;
	private final static int UP = 4;
	private final static int DOWN = 5;
	private final static int ENTER = 6;
	
	private Socket sock;  
//	private Socket sock2;
    private OutputStream os; 
    private OutputStream os2;
    
    private DataOutputStream out;
    private DataInputStream inp;
    
    private String ipnum;
     

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture);
		detector = new GestureDetector(this,this);
		Log.v(TAG, "onCreate");
		
//		try {
//			sock = new Socket(InetAddress.getByName("192.168.1.111"),2011);
//			fc = new ObjectOutputStream(sock.getOutputStream());
//			fs = new ObjectInputStream(sock.getInputStream());
//		} catch (UnknownHostException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e2){
//			e2.printStackTrace();
//		}
		
		
//		new Thread(){
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
////					sock = new Socket(InetAddress.getByName("192.168.1.111"),2011);
//					Log.v(TAG, "connect before ipnum:"+ipnum);
//					sock = new Socket(ipnum,8888);
//					os = sock.getOutputStream();
//					Log.v(TAG, "connect after ipnum:"+ipnum);
////					sock.close();
////					Thread.sleep(2000);
////					os2 = sock.getOutputStream();
////					sock2 = new Socket(ipnum,8888);
////					os = sock2.getOutputStream();
//				
////					sock2 = new Socket(ipnum,5555);
////					os = sock.getOutputStream();
//					
//				} catch (UnknownHostException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					Log.v(TAG, "error:"+e1.toString());
//				} catch (IOException e2){
//					Log.v(TAG, "error:"+e2.toString());
//					e2.printStackTrace();
//				}
//			}
//		}.start();
	}
	
	
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		/* page flip prev */
		if (e2.getX() - e1.getX() > distance && Math.abs(velocityX)>speed){
//			flipper.setInAnimation(animations[2]);
//			flipper.setInAnimation(animations[3]);
//			flipper.showPrevious();
			Choices choice = new Choices(PREV);
			
			try{
				os.write("0".getBytes());
				System.out.println("send PREV");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "prev page", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		/* page flip next */
		else if(e1.getX() - e2.getX() > distance && Math.abs(velocityX)>speed){
//			flipper.setInAnimation(animations[0]);
//			flipper.setInAnimation(animations[1]);
//			flipper.showNext();
			Choices choice = new Choices(NEXT);
			try{
				os.write("1".getBytes());
				System.out.println("send NEXT");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "next page", Toast.LENGTH_SHORT).show();
			return true;
		}
		/* cursor move left */
		else if(e1.getX() - e2.getX() > distance  && Math.abs(velocityX)<=speed && Math.abs(velocityX)>Math.abs(velocityY)){
//			new Thread(){
//				public void run(){
//					try{
//						Instrumentation ins = new Instrumentation();
//						ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT);
//					}
//					catch(Exception e){
//						Log.e("Exception when on left", e.toString());
//					}
//				}
//			}.start();
//			Choices choice = new Choices(LEFT);
			try{
				os.write("2".getBytes());
				System.out.println("send LEFT");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "move left", Toast.LENGTH_SHORT).show();
			return true;
		}
		/* cursor move right */
		else if(e2.getX() - e1.getX() > distance && Math.abs(velocityX)<=speed && Math.abs(velocityX)>Math.abs(velocityY)){
//			new Thread(){
//				public void run(){
//					try{
//						Instrumentation ins = new Instrumentation();
//						ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT);
//					}
//					catch(Exception e){
//						Log.e("Exception when on right", e.toString());
//					}
//				}
//			}.start();
//			Choices choice = new Choices(RIGHT);
//			choice.setKey(RIGHT);
			try{
				os.write("3".getBytes());
				System.out.println("send RIGHT");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "move right", Toast.LENGTH_SHORT).show();
			return true;
		}
		/* cursor move up */
		else if(e1.getY() - e2.getY() > distance && Math.abs(velocityY)>Math.abs(velocityX) ){
//			new Thread(){
//				public void run(){
//					try{
//						Instrumentation ins = new Instrumentation();
//						ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_UP);
//					}
//					catch(Exception e){
//						Log.e("Exception when on up", e.toString());
//					}
//				}
//			}.start();
//			Choices choice = new Choices(UP);
			try{
				os.write("4".getBytes());
				System.out.println("send UP");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "move up", Toast.LENGTH_SHORT).show();
			return true;
		}
		/* cursor move down */
		else if(e2.getY() - e1.getY() > distance && Math.abs(velocityY)>Math.abs(velocityX)){
//			new Thread(){
//				public void run(){
//					try{
//						Instrumentation ins = new Instrumentation();
//						ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
//					}
//					catch(Exception e){
//						Log.e("Exception when on down", e.toString());
//					}
//				}
//			}.start();
			Choices choice = new Choices(DOWN);
			
			try{
				
				os.write("5".getBytes());
//				os.flush();
				System.out.println("send DOWN");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			Toast.makeText(this, "move down", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		
		return false;
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gesture, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* ENTER */
	@Override
	public boolean onSingleTapUp(MotionEvent me) {
		// TODO Auto-generated method stub
//		new Thread(){
//			public void run(){
//				try{
//					Instrumentation ins = new Instrumentation();
//					ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER);
//				}
//				catch(Exception e){
//					Log.e("Exception when on center", e.toString());
//				}
//			}
//		}.start();
//		Choices choice = new Choices(ENTER);
		try{
			os.write("6".getBytes());
			System.out.println("send ENTER");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void SelfClick(View v){
		Toast toast = new Toast(this);
		toast.makeText(this, v.getId()+" is clicked", Toast.LENGTH_SHORT).show();
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ipnum = bundle.getString("key");
//        ipET.setText(ipnum);
        Settings.ipnum = ipnum;
//        try {
//			InetAddress serverAddress = InetAddress.getByName(ipnum);
//		} catch (UnknownHostException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        detector = new GestureDetector(this,this);
        new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				ApplicationUtil au = (ApplicationUtil) GestureActivity.this.getApplication();
				try {
					sock = new Socket(ipnum,8888);		
					os = sock.getOutputStream();
//					au.init(ipnum,8888);
//					Socket socket = au.getSocket();
//					os = au.getOut();
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2){
					e2.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
    }

}
