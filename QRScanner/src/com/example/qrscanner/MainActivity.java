package com.example.qrscanner;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
/** Called when the activity is first created. */
	
	private String result;
	
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button pressToScan = (Button) findViewById(R.id.scanner);
    
    Button go = (Button) findViewById(R.id.go);

    pressToScan.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent data = new Intent("com.google.zxing.client.android.SCAN");
            data.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(data, 0);

            TextView tv = (TextView) findViewById(R.id.scanResult);
            tv.setText(data.getStringExtra("SCAN_RESULT"));
        }
    });
    
    go.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ComponentName cn = new ComponentName("qianyan.rc","qianyan.rc.GestureActivity");
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			System.out.println("yes" + result);
			bundle.putString("key", result);
			intent.putExtras(bundle);
			intent.setComponent(cn);
			startActivity(intent);
		}
	});
}





public void onActivityResult(int requestCode, int resultCode, Intent data) {
    String contents = null;
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 0) {
          if (resultCode == RESULT_OK) {
             contents = data.getStringExtra("SCAN_RESULT");
             String format = data.getStringExtra("SCAN_RESULT_FORMAT");
             TextView tv = (TextView) findViewById(R.id.scanResult);
             tv.setText("Scan Result: " + contents);
             result = contents;
             // Handle successful scan
          } else if (resultCode == RESULT_CANCELED) {
             // Handle cancel
          }
    }
}
}