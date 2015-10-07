package com.google.zxing.client.android;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.history.HistoryActivity;
import com.google.zxing.client.android.history.HistoryManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BackIntent extends Activity {
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_back_intent);

    Button button_qr = (Button) findViewById(R.id.scanner_qr);
    Button button_history = (Button) findViewById(R.id.history);
    
    button_qr.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(BackIntent.this, CaptureActivity.class);
			startActivity(intent);
		}
	});
    
    button_history.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(BackIntent.this, HistoryActivity.class);
			startActivity(intent);
		}
	});
}
}