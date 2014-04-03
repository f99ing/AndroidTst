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
  private Looper mServiceLooper;
  private ServiceHandler mServiceHandler;

  // Handler that receives messages from the thread
  private final class ServiceHandler extends Handler {
      public ServiceHandler(Looper looper) {
          super(looper);
      }
      @Override
      public void handleMessage(Message msg) {
		  try
		  {
			
		  
			  Log.i("mytag","service handleMessage");
			  // Normally we would do some work here, like download a file.
			  // For our sample, we just sleep for 5 seconds.
			  long endTime = System.currentTimeMillis() + 5*1000;
			  while (System.currentTimeMillis() < endTime) {
				  synchronized (this) {
					  try {
						  wait(endTime - System.currentTimeMillis());
					  } catch (Exception e) {
					  }
				  }
			  }
			  // Stop the service using the startId, so that we don't stop
			  // the service in the middle of handling another job
			  stopSelf(msg.arg1);
		  }
		  catch (Exception e)
			{
				Log.e("mytag", "exception", e);
			}
      }
  }

  @Override
  public void onCreate() {
	  try
	  {
		
		// Start up the thread running the service.  Note that we create a
		// separate thread because the service normally runs in the process's
		// main thread, which we don't want to block.  We also make it
		// background priority so CPU-intensive work will not disrupt our UI.
		Log.i("mytag","service onCreate");
		HandlerThread thread = new HandlerThread("ServiceStartArguments",
				Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();

		// Get the HandlerThread's Looper and use it for our Handler
		mServiceLooper = thread.getLooper();
		mServiceHandler = new ServiceHandler(mServiceLooper);
	
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
		
	  String n = intent.getStringExtra("ID");
      Toast.makeText(this, "service starting ,ID="+n, Toast.LENGTH_SHORT).show();

      // For each start request, send a message to start a job and deliver the
      // start ID so we know which request we're stopping when we finish the job
      Message msg = mServiceHandler.obtainMessage();
      msg.arg1 = startId;
      mServiceHandler.sendMessage(msg);

		
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
    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
  }
}