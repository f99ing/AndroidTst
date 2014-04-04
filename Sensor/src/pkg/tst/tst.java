package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent ;


//see user manual for where proximate sensor , light sensor lies in on GW-I8150: Two circles on top left, front side, which are dark and hard to see.

public class tst extends Activity  implements SensorEventListener 
{
	private SensorManager mSensorManager; 
	private Sensor mLight;
	private Sensor mProximity;
	/*
	 
	Can detect Sharp GP2A Ambient Light Sensor,but actually GW-I8150 don't have a light sensor,see its user manual.
	Some apps detect it as a light sensor because those 2 use the same drivers, but one lacks the light sensor while still having the proximity one.

	Galaxy Wonder does NOT have a light sensor. It is detected wrong. It was supposed to be there in development stage, but later they decided to drop it. Unfortunately some manuals still say it is in the phone, but there isn't any.

	ref 	http://forum.xda-developers.com/showthread.php?t=1450846

	*/
	/*
		GW-I8150 proximity sensor report only value[0],with 2 possible values:0.0 & 5.0 (centimeters).
		the right circle is the proximate sensor,the left one have no effect.
		Even transparent material has the same effect as opaque material on this proximity sensor.
	*/

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

		try
		{
			Log.i("mytag","tst  onCreate"   );

			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			Log.i("mytag","tst  setContentView"   );

			final View view = this.findViewById(R.id.lltst);
			final EditText myEdt= (EditText)findViewById(R.id.edit_text);
			 
			Button button =(Button) findViewById(R.id.btnReq);

			button.setOnClickListener(new View.OnClickListener(){
			  public void onClick(View v){
			 
			  }
			});

			mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
			
			//TYPE_LIGHT  TYPE_PROXIMITY    TYPE_ACCELEROMETER    TYPE_GRAVITY...
			mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
			printSensorInfo(mLight);

			mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

			listSensor();
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}

    }
	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	// Do something here if sensor accuracy changes.
	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
		try{
			// The light sensor returns a single value.
			// Many sensors return 3 values, one for each axis. some return less or more.
			float recDatax = event.values[0];// see SensorEvent.values for possible values and meaning. 
			float recDatay = event.values[1];
			float recDataz = event.values[2];
			// Do something with this sensor value.
			final TextView tvTst= (TextView)findViewById(R.id.tvTst);
			String s ="x="+ String.valueOf(recDatax);
			s+=" y="+String.valueOf(recDatay);
			s+=" z="+String.valueOf(recDataz);;
			tvTst.setText (s) ; 
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
			mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_FASTEST);
			Log.i("mytag","registerListener"    );
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
			mSensorManager.unregisterListener(this);
			Log.i("mytag","unregisterListener"    );
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
	}
	void listSensor()
	{
		
		List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		for(Sensor ss:deviceSensors)
		{
			 printSensorInfo(ss);
		}
	}
	void printSensorInfo(Sensor ss)
	{
			 
			Log.i("mytag","Name:" +ss.getName()  );
			Log.i("mytag","Vendor:" +ss.getVendor() );
			Log.i("mytag","Version:" +Integer.toString(ss.getVersion() ) );
			//Log.i("mytag","FifoMaxEventCount:" +Integer.toString(ss.getFifoMaxEventCount())  );
			//Log.i("mytag","FifoReservedEventCount:" +Integer.toString(ss.getFifoReservedEventCount ())  );
			Log.i("mytag","MaximumRange:" +String.valueOf( ss.getMaximumRange () )  );
			//Log.i("mytag","MinDelay:" +Integer.toString(ss. getMinDelay ()) );
			Log.i("mytag","Power :" +String.valueOf(ss. getPower ())  );
			Log.i("mytag","Resolution:" +String.valueOf(ss.  getResolution ()) );
			Log.i("mytag","Type:" +Integer.toString(ss. getType ())  );
			Log.i("mytag","toString:" +ss. toString () );
			Log.i("mytag","-------------------------"   );
	}
}
