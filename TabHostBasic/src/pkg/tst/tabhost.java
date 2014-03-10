package pkg.tst;

import android.app.Activity;
import android.os.Bundle;
 
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

public class tabhost extends Activity
{
    private TabHost mTabHost;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();
 
		addTab(new TextView(this), "Tab 1");
		addTab(new TextView(this), "Tab 2");
		addTab(new TextView(this), "Tab 3");
	}
 
	private void addTab(final View content, final String title) {
		View tabView = LayoutInflater.from(this).inflate(R.layout.tab, null);
		TextView tv = (TextView) tabView.findViewById(R.id.txtInfo);
		tv.setText(title);
 
		TabSpec setContent = mTabHost.newTabSpec(title).setIndicator(tabView).setContent(new TabContentFactory() {
			public View createTabContent(String tag) {
				LayoutInflater inflater=getLayoutInflater();
				View v=inflater.inflate(R.layout.content, null);
				TextView t = (TextView)v.findViewById( R.id.txtInfo);
				t.setText(title+" content");
				return v;
			}
		});
		mTabHost.addTab(setContent);
	}
}
