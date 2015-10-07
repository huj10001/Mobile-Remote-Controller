package com.example.remotecontrolserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.app.Activity;
import android.app.Instrumentation;

import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	
	public final static int PREV=0;
	public final static int NEXT=1;
	public final static int LEFT=2;
	public final static int RIGHT=3;
	public final static int UP=4;
	public final static int DOWN=5;
	public final static int ENTER=6;
	
	Handler handler = null;
	private Receiver2 recv;
	
	private static int key;
	private static InputStream is;
	
	private ServerService sese;
	
	String s;
	
	ViewFlipper flipper;
	Animation[] animations = new Animation[4];
	private Handler hdl = new Handler();
	private Instrumentation in = new Instrumentation();
	
	Button b1 = null;
	Button b2 = null;
	Button b3 = null;
	ImageButton b4 = null;
	Button b5 = null;
	Button b6 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
		flipper = (ViewFlipper) this.findViewById(R.id.flipper);
		
		View view1=View.inflate(this,R.layout.activity_page1,null);
		View view2=View.inflate(this,R.layout.activity_page2,null);
		View view3=View.inflate(this,R.layout.activity_page3,null);
		
		flipper.addView(view1);
		flipper.addView(view2);
		flipper.addView(view3);
		
		
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		
		b1 = (Button) findViewById(R.id.one);
		b2 = (Button) findViewById(R.id.two);
		b3 = (Button) findViewById(R.id.three);
		b4 = (ImageButton) findViewById(R.id.four);
		b5 = (Button) findViewById(R.id.five);
		b6 = (ToggleButton) findViewById(R.id.six);
		
		b1.setFocusable(true);
		b1.setFocusableInTouchMode(true);
		b1.requestFocus();
		b1.requestFocusFromTouch();
		
		
		b2.setFocusable(true);
		b2.setFocusableInTouchMode(true);
		b3.setFocusable(true);
		b3.setFocusableInTouchMode(true);
		b4.setFocusable(true);
		b4.setFocusableInTouchMode(true);
		b5.setFocusable(true);
		b5.setFocusableInTouchMode(true);
		b6.setFocusable(true);
		b6.setFocusableInTouchMode(true);
		

		
		
		
		new Thread(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ServerSocket ss = new ServerSocket(8888);
				
				System.out.println("WAITING FOR CONNECTION!");
				Socket sock = ss.accept();

				System.out.println("RECEIVING CONNECTION!");
				is = sock.getInputStream();
			
			do{

				byte buffer[] = new byte[1024*4];
				int temp = 0;

					temp = is.read(buffer);
					String string = new String(buffer,0,temp);
					System.out.println(string);

					key = Integer.parseInt(string);

				
				System.out.println(key);
				switch(key){
				case PREV:
					System.out.println("receive PREV");
					hdl.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							flipper.setInAnimation(animations[2]);
							flipper.setInAnimation(animations[3]);
							flipper.showPrevious();
//	
							;
						}
					});
					break;
					
					
				case NEXT:
					System.out.println("receive NEXT");
					hdl.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							flipper.setInAnimation(animations[0]);
							flipper.setInAnimation(animations[1]);
							flipper.showNext();

						}
					});
					break;
					
				
				case LEFT:

										Instrumentation ins2 = new Instrumentation();
										ins2.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT);
					System.out.println("receive LEFT");
					break;

					
				case RIGHT:

										Instrumentation ins3 = new Instrumentation();
										ins3.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT);
					System.out.println("receive RIGHT");
					break;

					
				case UP:
										Instrumentation ins4 = new Instrumentation();
										ins4.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_UP);
					System.out.println("receive UP");
					break;

					
				case DOWN:
					System.out.println("receive DOWN");
							Instrumentation ins5 = new Instrumentation();
							ins5.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
					break;

					
				case ENTER:
										Instrumentation ins6 = new Instrumentation();
										ins6.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER);
					System.out.println("receive ENTER");
					break;
					
				default:
					break;
				}
			}while(key!=-1);
			System.out.println("EXIT");
			
			is.close();  
	        sock.close();  
	        ss.close();  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
			
		}.start();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void SelfClick(View v){
		Toast toast = new Toast(this);
		toast.makeText(this, v.getId()+" is clicked", Toast.LENGTH_SHORT).show();
	}
	
	/* on resume */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.activity_main);
		
//		if (GetVersion.GetSystemVersion() > 2.3) {
//			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//					.detectDiskReads().detectDiskWrites().detectNetwork()
//					.penaltyLog().build());
//
//			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
//					.build());
//		}	
		
		flipper = (ViewFlipper) this.findViewById(R.id.flipper);
		
		View view1=View.inflate(this,R.layout.activity_page1,null);
		View view2=View.inflate(this,R.layout.activity_page2,null);
		View view3=View.inflate(this,R.layout.activity_page3,null);
		
		flipper.addView(view1);
		flipper.addView(view2);
		flipper.addView(view3);
		
		
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		
		b1 = (Button) findViewById(R.id.one);
		b2 = (Button) findViewById(R.id.two);
		b3 = (Button) findViewById(R.id.three);
		b4 = (ImageButton) findViewById(R.id.four);
		b5 = (Button) findViewById(R.id.five);
		b6 = (ToggleButton) findViewById(R.id.six);
		
		b1.setFocusable(true);
		b1.setFocusableInTouchMode(true);
		b1.requestFocus();
		b1.requestFocusFromTouch();
		
		
		b2.setFocusable(true);
		b2.setFocusableInTouchMode(true);
		b3.setFocusable(true);
		b3.setFocusableInTouchMode(true);
		b4.setFocusable(true);
		b4.setFocusableInTouchMode(true);
		b5.setFocusable(true);
		b5.setFocusableInTouchMode(true);
		b6.setFocusable(true);
		b6.setFocusableInTouchMode(true);
		
		
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what)				
				{
				case PREV:
					System.out.println("receive PREV");
					hdl.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							flipper.setInAnimation(animations[2]);
							flipper.setInAnimation(animations[3]);
							flipper.showPrevious();
//	
							;
						}
					});
					break;
					
					
				case NEXT:
					System.out.println("receive NEXT");
					hdl.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							flipper.setInAnimation(animations[0]);
							flipper.setInAnimation(animations[1]);
							flipper.showNext();

						}
					});
					break;
					
				
				case LEFT:

										Instrumentation ins2 = new Instrumentation();
										ins2.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT);
					System.out.println("receive LEFT");
					break;

					
				case RIGHT:

										Instrumentation ins3 = new Instrumentation();
										ins3.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT);
					System.out.println("receive RIGHT");
					break;

					
				case UP:
										Instrumentation ins4 = new Instrumentation();
										ins4.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_UP);
					System.out.println("receive UP");
					break;

					
				case DOWN:
					System.out.println("receive DOWN");
					new Thread(){
						@Override
							public void run() {
							Instrumentation ins5 = new Instrumentation();
							ins5.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
						}
					}.start();
					break;

					
				case ENTER:
										Instrumentation ins6 = new Instrumentation();
										ins6.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER);
					System.out.println("receive ENTER");
					break;
					
				default:
					break;
				}
				while(key!=-1);
				System.out.println("EXIT");
			}
			};
	
		
		Intent intent = new Intent();
		intent.setAction("com.example.remotecontrolserver.SESE");
		startService(intent);
		
		recv = new Receiver2();
		recv.setHandler(handler);
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.remotecontrolserver.SECOND_RECV");
		registerReceiver(recv, filter);
		
		
//		b5.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				int num = Integer.parseInt((String) b5.getText());
//				num ++;
//				b5.setText(num);
//			}
//		});
		
//		Intent intent = this.getIntent();
//		final Bundle bundle = intent.getExtras();
//		
//		new Thread(){
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
////					ServerSocket sss = new ServerSocket(8888);
//					ServerSocket ss = new ServerSocket(8889);
//					
//					System.out.println("WAITING FOR CONNECTION!");
//					Socket sock = ss.accept();
////					sock = ss.accept();
////					sock.close();
////					Socket sock2 = ss.accept();
//					System.out.println("RECEIVING CONNECTION!");
////					is = (InputStream) bundle.get("user");
//					is = sock.getInputStream();
//					
//				
////			try {
////				ServerSocket ss = new ServerSocket(2011);
////				Socket sock = ss.accept();
////				fc = new ObjectInputStream(sock.getInputStream());
////				fs = new ObjectOutputStream(sock.getOutputStream());
//				
//				do{
////					Choices choice = (Choices)is.readObject();
////					System.out.println("the flag is " + choice.getKey());
////					key = choice.getKey();
//					byte buffer[] = new byte[1024*4];
//					int temp = 0;
////					temp = is.read(buffer);
////					while((temp=is.read(buffer))!=-1){
//						temp = is.read(buffer);
//						String string = new String(buffer,0,temp);
//						System.out.println(string);
////						Choices choice = (Choices) is.read(buffer, 0, temp);
//						key = Integer.parseInt(string);
////						s = string;
////					}
//					
//					System.out.println(key);
//					switch(key){
//					case PREV:
//						System.out.println("receive PREV");
//						hdl.post(new Runnable() {
//							
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								flipper.setInAnimation(animations[2]);
//								flipper.setInAnimation(animations[3]);
//								flipper.showPrevious();
////								try {
////									Thread.sleep(10);
////									
////								} catch (InterruptedException e) {
////									// TODO Auto-generated catch block
////									e.printStackTrace();
////								}
//								;
//							}
//						});
//						break;
//						
//						
//					case NEXT:
//						System.out.println("receive NEXT");
//						hdl.post(new Runnable() {
//							
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								flipper.setInAnimation(animations[0]);
//								flipper.setInAnimation(animations[1]);
//								flipper.showNext();
////								try {
////									Thread.sleep(10);
////								} catch (InterruptedException e) {
////									// TODO Auto-generated catch block
////									e.printStackTrace();
////								}
//							}
//						});
//						break;
//						
//					
//					case LEFT:
////						new Thread(){
////							public void run(){
////								try{
////									hdl.post(new Runnable() {
////										
////										@Override
////										public void run() {
//											// TODO Auto-generated method stub
//											Instrumentation ins2 = new Instrumentation();
//											ins2.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT);
//											
////										}
////									});
////									
////								}
////								catch(Exception e){
////									Log.e("Exception when on left", e.toString());
////								}
////							}
////						}.start();
//						System.out.println("receive LEFT");
//						break;
////						Toast.makeText(this, "move left", Toast.LENGTH_SHORT).show();
//						
//					case RIGHT:
////						new Thread(){
////							public void run(){
////								try{
////									hdl.post(new Runnable() {
////										
////										@Override
////										public void run() {
//											// TODO Auto-generated method stub
//											Instrumentation ins3 = new Instrumentation();
//											ins3.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT);
////										}
////									});
////									
////								}
////								catch(Exception e){
////									Log.e("Exception when on right", e.toString());
////								}
////							}
////						}.start();
//						System.out.println("receive RIGHT");
//						break;
////						Toast.makeText(this, "move right", Toast.LENGTH_SHORT).show();
//						
//					case UP:
////						new Thread(){
////							public void run(){
////								try{
////									hdl.post(new Runnable() {
////										
////										@Override
////										public void run() {
//											// TODO Auto-generated method stub
//											Instrumentation ins4 = new Instrumentation();
//											ins4.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_UP);
////										}
////									});
////									
////								}
////								catch(Exception e){
////									Log.e("Exception when on up", e.toString());
////								}
////							}
////						}.start();
//						System.out.println("receive UP");
//						break;
////						Toast.makeText(this, "move up", Toast.LENGTH_SHORT).show();
//						
//					case DOWN:
//						System.out.println("receive DOWN");
////						hdl.post(new Runnable() {
////							
////							@Override
////							public void run() {
//								// TODO Auto-generated method stub
//								Instrumentation ins5 = new Instrumentation();
//								ins5.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
////								try {
////									Thread.sleep(10);
////									
////								} catch (InterruptedException e) {
////									// TODO Auto-generated catch block
////									e.printStackTrace();
////								}
////								;
////							}
////						});
////						new Thread(){
////							public void run(){
////								try{
////									hdl.post(new Runnable() {
////										
////										@Override
////										public void run() {
////											// TODO Auto-generated method stub
////											Instrumentation ins = new Instrumentation();
////											ins.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN);
////										}
////									});
////									
////								}
////								catch(Exception e){
////									Log.e("Exception when on down", e.toString());
////								}
////							}
////						}.start();
////						System.out.println("receive DOWN");
//						break;
////						Toast.makeText(this, "move down", Toast.LENGTH_SHORT).show();
//						
//					case ENTER:
////						new Thread(){
////							public void run(){
////								try{
////									hdl.post(new Runnable() {
////										
////										@Override
////										public void run() {
//											// TODO Auto-generated method stub
//											Instrumentation ins6 = new Instrumentation();
//											ins6.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_CENTER);
////										}
////									});
////									
////								}
////								catch(Exception e){
////									Log.e("Exception when on center", e.toString());
////								}
////							}
////						}.start();
//						System.out.println("receive ENTER");
//						break;
//						
//					default:
//						break;
//					}
//				}while(key!=-1);
//				System.out.println("EXIT");
//				
//				is.close();  
////		        oos.close();  
//		        sock.close();  
//		        ss.close();  
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} //catch (ClassNotFoundException e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
//		}
//				
//			}.start();
	}

}
