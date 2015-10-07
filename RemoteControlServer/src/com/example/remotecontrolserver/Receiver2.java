package com.example.remotecontrolserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class Receiver2 extends BroadcastReceiver{

	Handler mHandler = null;
	
	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals("com.example.remotecontrolserver.SECOND_RECV")){
			int in = Integer.parseInt(intent.getStringExtra("keyy"));
			if( intent.getStringExtra("keyy") != null && mHandler != null){
				switch(in){
				case ServerService.PREV:
					mHandler.sendEmptyMessage(MainActivity.PREV);
					
				case ServerService.NEXT:
					mHandler.sendEmptyMessage(MainActivity.NEXT);
					
				case ServerService.LEFT:
					mHandler.sendEmptyMessage(MainActivity.LEFT);
					
				case ServerService.RIGHT:
					mHandler.sendEmptyMessage(MainActivity.RIGHT);
					
				case ServerService.UP:
					mHandler.sendEmptyMessage(MainActivity.UP);
					
				case ServerService.DOWN:
					mHandler.sendEmptyMessage(MainActivity.DOWN);
				
				case ServerService.ENTER:
					mHandler.sendEmptyMessage(MainActivity.ENTER);	
				}
			}
		}
	}

	/**
	 * @param args
	 */
	

}
