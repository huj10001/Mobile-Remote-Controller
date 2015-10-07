package com.example.remotecontrolserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class ServerService extends Service {
	
	private InputStream is;
	public final static int PREV=0;
	public final static int NEXT=1;
	public final static int LEFT=2;
	public final static int RIGHT=3;
	public final static int UP=4;
	public final static int DOWN=5;
	public final static int ENTER=6;
	
	private static int key;
	private String st;
	
	/**
	 * @param args
	 */

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public void onCreate(){
		super.onCreate();
		
		new Thread(){
			@Override
			public void run(){
				try{
					ServerSocket ss = new ServerSocket(8888);
					Socket s = ss.accept();
					if(s != null){
						String string = "yes";
					
						Intent intent = new Intent();
						intent.setAction("com.example.remotecontrolserver.FIRST_RECV");
						Bundle bundle = new Bundle();
						bundle.putString("acpt", string);
						intent.putExtras(bundle);
						sendBroadcast(intent);
					
					is = s.getInputStream();
					do{
						byte buffer[] = new byte[1024*4];
						int temp = 0;
						temp = is.read(buffer);
						String stringg = new String(buffer,0,temp);
						System.out.println(stringg);
						key = Integer.parseInt(stringg);
						switch(key){
						case PREV:
							String s1 = String.valueOf(PREV);
							Intent intent1 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle1 = new Bundle();
			                bundle1.putString("keyy", s1);
			                intent1.putExtras(bundle1);
			                sendBroadcast(intent1);
						case NEXT:
							String s2 = String.valueOf(NEXT);;
							Intent intent2 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle2 = new Bundle();
			                bundle2.putString("keyy", s2);
			                intent2.putExtras(bundle2);
			                sendBroadcast(intent2);
						case LEFT:
							String s3 = String.valueOf(LEFT);;
							Intent intent3 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle3 = new Bundle();
			                bundle3.putString("keyy", st);
			                intent3.putExtras(bundle3);
			                sendBroadcast(intent3);
						case RIGHT:
							String s4 = String.valueOf(RIGHT);;
							Intent intent4 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle4 = new Bundle();
			                bundle4.putString("keyy", s4);
			                intent4.putExtras(bundle4);
			                sendBroadcast(intent4);
						case UP:
							String s5 = String.valueOf(UP);;
							Intent intent5 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle5 = new Bundle();
			                bundle5.putString("keyy", s5);
			                intent5.putExtras(bundle5);
			                sendBroadcast(intent5);
						case DOWN:
							String s6 = String.valueOf(DOWN);;
							Intent intent6 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle6 = new Bundle();
			                bundle6.putString("keyy", s6);
			                intent6.putExtras(bundle6);
			                sendBroadcast(intent6);
						case ENTER:
							String s7 = String.valueOf(ENTER);;
							Intent intent7 = new Intent("com.example.remotecontrolserver.SECOND_RECV");
			                Bundle bundle7 = new Bundle();
			                bundle7.putString("keyy", s7);
			                intent7.putExtras(bundle7);
			                sendBroadcast(intent7);
						}
						}while(key!=-1);
						System.out.println("EXIT");
					}
						
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
		}.start();
	}
		
	
	public int onStartCommand(Intent intent, int flags, int startId) {
//		try {
//			this.ss = new ServerSocket(8888);
//			this.socket = ss.accept();
//			this.is = socket.getInputStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	
//	@Override
//	public void onCreate() {
//		// TODO Auto-generated method stub
//		super.onCreate();
//		Log.v("","SocketService onCreate");
//		mservice = this;
//		new Thread(mSocketRunnable).start();
//	}
//	
//	Runnable mSocketRunnable = new Runnable() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			try{
//				ss = new ServerSocket(port);
////				while(!quit){
//				Socket s = ss.accept();
////					Log.v("", "Socket ---> s : " + s.toString());                      
////                    socketList.add(s);
////                    new Thread(new ServerThread(s)).start();  
////                    
////                    MainActivity.mHandler.sendEmptyMessage(MainActivity.CONN_SUCCESS);  
//                    
////				}
//			}catch(IOException e){
//				e.printStackTrace();
//			}
//		}
//	};

}
