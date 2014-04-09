package tst.Broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.BroadcastReceiver;
import 	android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
public class BroadcastHome extends Activity
{
	private MyListener listener = null;
	private Boolean MyListenerIsRegistered = false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			Log.i("mytag","BroadcastHome.onCreate");

			listener = new MyListener();
			registerReceiver(listener, new IntentFilter("tst.myBroadcast"));
			Log.i("mytag","registerReceiver" );
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }

	@Override
    protected void onResume() {
		try{
			super.onResume();
			Log.i("mytag","BroadcastHome onResume" );
			if (!MyListenerIsRegistered) {
				registerReceiver(listener, new IntentFilter("tst.myBroadcast"));
				MyListenerIsRegistered = true;
				Log.i("mytag","registerReceiver" );
			}
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }

    @Override
    protected void onPause() {
		try{
			super.onPause();
			Log.i("mytag","BroadcastHome onPause" );

			if (MyListenerIsRegistered) {
				Log.i("mytag","BroadcastHome   MyListenerIsRegistered=true" );
				//unregisterReceiver(listener);
				//MyListenerIsRegistered = false;
			}
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }
	@Override
    protected void onDestroy() {
		try{
			super.onPause();
			Log.i("mytag","BroadcastHome onDestroy" );

			if (MyListenerIsRegistered) {
				Log.i("mytag","BroadcastHome   MyListenerIsRegistered=true" );
				unregisterReceiver(listener);
				MyListenerIsRegistered = false;
			}
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }
	protected class MyListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent data) {
			try{
				Log.i("mytag","BroadcastHome onReceive" );
				// No need to check for the action unless the listener will handle more than one - let's do it anyway
				if (data.getAction().equals("tst.myBroadcast")) {
					int i=  data.getIntExtra("id",0); 
					String s= data.getStringExtra("name");
					Log.i("mytag","BroadcastHome get result "+s );
				 }
			}
			catch (Exception e)
			{
				Log.e("mytag", "exception", e);
			}
        }
    }
}
