package tst.Broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BroadcastHome extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Log.i("mytag","BroadcastHome.onCreate");
    }
}
