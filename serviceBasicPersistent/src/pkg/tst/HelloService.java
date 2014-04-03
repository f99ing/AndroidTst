package pkg.tst;

import 	android.app.Service;
import  android.os.Looper;
import android.os.Message;
import android.os.HandlerThread;
import android.os.Process;
import android.os.IBinder;
import 	android.content.Intent;
import 	android.widget.Toast;
import android.os.Handler;
import android.util.Log;
import android.content.Intent;

public class HelloService extends Service {

	String startID="";//for stopping this service
  @Override
  public void onCreate() {
	try
	{
		Log.i("mytag","service onCreate");

	}
	catch (Exception e)
	{
		Log.e("mytag", "exception", e);
	}
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
	try
	{
		if(startID ==null || startID.isEmpty())
		{
			startID=Integer.toString(startId);
		}

		String n = intent.getStringExtra("ID");
		Log.i("mytag", "service onStartCommand ,received data: ID="+n); 
		if("stop".equals(n))
		{
			stopSelf(startId);
		}
  	}
	catch (Exception e)
	{
		Log.e("mytag", "exception", e);
	}
	// If we get killed, after returning from here, restart
	return START_STICKY;
  }

  @Override
  public IBinder onBind(Intent intent) {
      // We don't provide binding, so return null
      return null;
  }

  @Override
  public void onDestroy() {
	Log.i("mytag", "service onDestroy ");  
  }
}