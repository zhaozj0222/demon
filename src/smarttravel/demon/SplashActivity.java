package smarttravel.demon;

import android.app.Activity;   
import android.content.Intent;    
import android.os.AsyncTask;
import android.os.Bundle;  
import android.widget.TextView;

public class SplashActivity extends Activity {
	
	private TextView mVersionNameText;
	private static final int SHOW_TIME_MIN = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		mVersionNameText = (TextView) findViewById(R.id.version_name);
		mVersionNameText.setText("v1.0");

		new AsyncTask<Void, Void, Integer>() {
			
			@Override
			protected Integer doInBackground(Void... params) {
            	long startTime = System.currentTimeMillis();
				long loadingTime = System.currentTimeMillis() - startTime;
				if (loadingTime < SHOW_TIME_MIN) {
					try {
						Thread.sleep(SHOW_TIME_MIN - loadingTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return 1;
			}
			
			@Override
			protected void onPostExecute(Integer result) {
				Intent intent = new Intent();
				intent.setClassName(SplashActivity.this, getString(R.string.splash_out_activity));
				startActivity(intent);
				finish();
				
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			};
		}.execute(new Void[]{});
	}
}
