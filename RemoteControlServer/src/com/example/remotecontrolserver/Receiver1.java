package com.example.remotecontrolserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class Receiver1 extends BroadcastReceiver{

	Handler mHandler = null;
	
	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals("com.example.remotecontrolserver.FIRST_RECV"))
		{
			String s = intent.getStringExtra("acpt");
			if(s.equals("yes") && null!=mHandler){
	//			intent.setClass(Qrcode.this, MainActivity.class);
				mHandler.sendEmptyMessage(Qrcode.MSG_FIRST_RECV);
			}
		}
		
	}

	/**
	 * @param args
	 */
	

}
