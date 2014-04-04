package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.view.View.OnKeyListener;
import android.content.Context;
public class tst extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		final View view = this.findViewById(R.id.lltst);
		final EditText myEdt= (EditText)findViewById(R.id.edit_text);
		 
		Log.i("mytag", Boolean.toString(isChild())    );
		
		//press hardkey (menu, back, volume) to see the result in logcat
		myEdt.setOnKeyListener(new OnKeyListener(){
			public boolean onKey(View v, int keyCode, KeyEvent event)
			{
				Log.i("mytag", Integer.toString(keyCode)  +Boolean.toString(event.isSystem())  );
				return true;
			}
		});

		Button button =(Button) findViewById(R.id.btnReq);

		button.setOnClickListener(new View.OnClickListener(){
		  public void onClick(View v){
			String s= myEdt.getText().toString();
			Log.i("mytag","you entered:"+ s  );
		  }
		});

    }
}
