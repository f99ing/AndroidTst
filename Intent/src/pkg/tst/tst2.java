package pkg.tst2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.content.Intent;

public class tst2 extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		try
		{
			Log.i("mytag","tst2 onCreate"   );

			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			Log.i("mytag","tst2 setContentView"   );

			final View view = this.findViewById(R.id.lltst);
			final EditText myEdt= (EditText)findViewById(R.id.edit_text);
			 
			Log.i("mytag", Boolean.toString(isChild())    );

			Button btnSvc =(Button) findViewById(R.id.btnSvc);
			btnSvc.setOnClickListener(new View.OnClickListener(){
			  public void onClick(View v){
				 callService( );
			  }
			});

			Button btnSvcStop =(Button) findViewById(R.id.btnSvcStop);
			btnSvcStop.setOnClickListener(new View.OnClickListener(){
			  public void onClick(View v){
				 stopService( );
			  }
			});

			Button btnAct =(Button) findViewById(R.id.btnAct);
			btnAct.setOnClickListener(new View.OnClickListener(){
			  public void onClick(View v){
				startActivity();
			  }
			});
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}

    }
	void startActivity()
	{
		Intent i = new Intent( );
		i.setClassName("pkg.tst", "pkg.tst.tst");
		startActivity(i);  
	}
	void callService()
	{
		try
		{
			final EditText txt= (EditText)findViewById(R.id.edit_text);

			Log.i("mytag","start service");
			Intent intent = new Intent( );
			intent.setClassName("pkg.tst", "pkg.tst.HelloService");
			String s= txt.getText().toString();
			intent.putExtra("ID",s );
			startService(intent);//this will invoke HelloService's onCreate if HelloService is not started,then onStartCommand,which is always invoked.
			Log.i("mytag"," service started"); 
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
	}

	void stopService()
	{
		try
		{
			Intent intent = new Intent( );
			intent.setClassName("pkg.tst", "pkg.tst.HelloService");
			stopService (intent);
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
	}
}
