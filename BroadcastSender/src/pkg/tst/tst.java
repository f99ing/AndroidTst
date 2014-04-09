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
			Log.i("mytag","tst  onCreate"   );

			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			Log.i("mytag","tst  setContentView"   );

			final View view = this.findViewById(R.id.lltst);
			final EditText myEdt= (EditText)findViewById(R.id.edit_text);
			 
			 
			Button button =(Button) findViewById(R.id.btnReq);

			button.setOnClickListener(new View.OnClickListener(){
			  public void onClick(View v){
				String s= myEdt.getText().toString();
				Log.i("mytag","you entered:"+ s  );

				Intent i = new Intent();
				i.putExtra("name",s);
				i.putExtra("id",12);
				i.setAction("tst.myBroadcast");
				Log.i("mytag","tst sendBroadcast" );
				sendBroadcast(i); 
			  }
			});
		}
		catch (Exception e)
		{
			Log.e("mytag", "exception", e);
		}

    }
}
