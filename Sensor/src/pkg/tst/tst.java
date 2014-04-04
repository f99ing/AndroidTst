package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.List;

public class tst extends Activity
{
	private SensorManager mSensorManager;
 
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
			List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
			for(Sensor ss:deviceSensors)
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
				Log.i("mytag"," ----"   );
			}

		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}

    }
}
