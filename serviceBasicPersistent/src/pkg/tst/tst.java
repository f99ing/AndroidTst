package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

 

public class tst extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		try
		{

			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			final View view = this.findViewById(R.id.lltst);
			
			Button button =(Button) findViewById(R.id.btnReq);
			button.setOnClickListener(new View.OnClickListener(){
				 public void onClick(View v){
					callService();
				 }
			});

			Button btn =(Button) findViewById(R.id.btnStopSvc);
			btn.setOnClickListener(new View.OnClickListener(){
				 public void onClick(View v){
					stopService();
				 }
			});
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }
	void callService()
	{
		final EditText txt= (EditText)findViewById(R.id.edit_text);

		Log.i("mytag","start service");
		Intent intent = new Intent(this, HelloService.class);
		String s= txt.getText().toString();
		intent.putExtra("ID",s );
		startService(intent);//this will invoke HelloService's onCreate if HelloService is not started,then onStartCommand,which is always invoked.
		Log.i("mytag"," service started"); 
	}

	void stopService()
	{
		Intent intent = new Intent(this, HelloService.class);
		stopService (intent);
	}
}
