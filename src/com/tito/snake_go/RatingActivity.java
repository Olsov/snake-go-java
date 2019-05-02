package com.tito.snake_go;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tito.snake_go.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class RatingActivity extends Activity {
LinearLayout ll;
TextView tx;
MenuActivity Menu=new MenuActivity();
String s="";
Cursor c;
Button clear;
Context context;
DBHelper dbHelper;
final String DROP_TABLE ="drop table if exists mytable2";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_rating);
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		final TextView tx=(TextView)this.findViewById(R.id.lastResult);
		dbHelper= new DBHelper(this);
		clear=(Button)this.findViewById(R.id.clear_button);
		clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				db.execSQL(DROP_TABLE);
				dbHelper.onCreate(db);
				tx.setText("No results");
			}});
		ll=(LinearLayout)this.findViewById(R.id.linear);
		context=RatingActivity.this;
		
		SQLiteDatabase db=dbHelper.getReadableDatabase();
		dbHelper.onCreate(db);
		 c=db.query("mytable2",null, null, null, null, null, null);
			if(c.moveToFirst()){
				int index=c.getColumnIndex("id");
				int result =c.getColumnIndex("Result");
				int time_db=c.getColumnIndex("time");
				int apple_count=c.getColumnIndex("apple");
			do {
				 s=s+" "+c.getInt(index)+" "+c.getString(result)+" "
			+c.getString(time_db)+" "+c.getString(apple_count)+"\n";
			}
			while (c.moveToNext());
			tx.setText(s);
			
			}else{
				c.close();
				tx.setText("No results");
			}
			dbHelper.close();
	}
	
}

	

