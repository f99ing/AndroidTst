package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
import android.content.Intent;

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
			final EditText txt= (EditText)findViewById(R.id.edit_text);
			 
			Log.i("mytag","start service");
			Intent intent = new Intent(this, HelloService.class);
			intent.putExtra("ID","5" );
			startService(intent);
			Log.i("mytag"," service started"); 
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}
    }
}
