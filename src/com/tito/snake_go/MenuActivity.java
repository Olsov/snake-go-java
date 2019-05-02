package com.tito.snake_go;

import com.tito.snake_go.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener{
Button start,exit,reit;
Intent intent;
Cursor c;
public int b=1;
SQLiteDatabase db;
String s="No result";
TextView lastResult;
DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_menu);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		start=(Button)this.findViewById(R.id.start);
		reit=(Button)this.findViewById(R.id.ladder);
		exit=(Button)this.findViewById(R.id.exit);	
		lastResult=(TextView)this.findViewById(R.id.lastResult);
	 start.setOnClickListener(this);
	 reit.setOnClickListener(this);
	 exit.setOnClickListener(this);
	 dbHelper= new DBHelper(this);
	 final Handler h=new Handler();
	 Thread t=new Thread (new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
				db=dbHelper.getReadableDatabase();
				dbHelper.onCreate(db);
				 c=db.query("mytable2",null, null, null, null, null, null);
					if(c.moveToFirst()){
						int index=c.getColumnIndex("id");
						int result =c.getColumnIndex("Result");
						int time_db=c.getColumnIndex("time");
						int apple_count=c.getColumnIndex("apple");
					do {
						 s=c.getString(result)+" "
					+c.getString(time_db)+" "+c.getString(apple_count);
					}
					while (c.moveToNext());
				
					}else{
						c.close();
				s="No result";
						
					}
					dbHelper.close();	
					h.post(onResult);
	
			}
		 
		 
	 });
	 t.start();
	}
	Runnable onResult=new Runnable(){
		public void run(){
			lastResult.setText(s);
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i =v.getId();
		
		if (i==R.id.start)
		{
			intent= new Intent(MenuActivity.this,MainActivity.class);
			startActivity(intent);
		}
		
		if (i==R.id.exit)
		{
			finish();
		}
		if(i==R.id.ladder){
			intent= new Intent(MenuActivity.this,RatingActivity.class);
			startActivity(intent);
			
		}
	}
}
