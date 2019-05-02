package com.tito.snake_go;

import android.util.Log;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import com.tito.snake_go.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity    implements OnClickListener  {
 ImageView apple,img2,snake,snake_body;
Context context;
boolean running;
final String LOG_TAG="myLogs";
MenuActivity Menu=new MenuActivity();
Cursor c;
private static final String AD_UNIT_ID = "ca-app-pub-4885317560012072/3084761146";
Button but_exit;
RelativeLayout view;
DBHelper dbHelper;
Integer  save=0;
Runnable run;
Integer k=1;
Integer[] telo_nom= new Integer[61];
Integer snake_img,dots_img;
ImageView [] telo;
AbsoluteLayout.LayoutParams [] param_lot ;
AbsoluteLayout abl;
Handler h,h2;
TextView time,res_text,apple_count;

int seconds = 0, minutes = 0;
Integer save_apple=0;
Integer save_behind;
String[] telo_name= new String[61];
AlertDialog.Builder ok;
private int DIALOG= 0;
 String name,name2,snake_name,dots_name;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		 
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 time=(TextView)this.findViewById(R.id.time);
		time.setText("Time");
		apple_count=(TextView)this.findViewById(R.id.applecoun);
		apple_count.setText(Integer.toString(k-1));
		dbHelper= new DBHelper(this);
		context=MainActivity.this;
		ok=new AlertDialog.Builder(context);
		ok.setNegativeButton("Ok", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int id) {
				// TODO Auto-generated method stub
					
				finish();
		
			}
		});
		abl=(AbsoluteLayout)this.findViewById(R.id.dots_2);
		apple=(ImageView)this.findViewById(R.id.apple);
		
		Random r= new Random();
		snake_img=r.nextInt(11)+1;
		snake_name="eclipse_snake_"+snake_img;
		dots_img=r.nextInt(11)+1;
		dots_name="eclipse_dot_"+dots_img;
		int rand=r.nextInt(60)+1;
		telo_nom[1]=rand;

		name="img"+rand;
		
		telo_name[1]=name;
		 param_lot = new AbsoluteLayout.LayoutParams[61];
			telo=new ImageView[61];

			telo[1]=(ImageView)this.findViewById(R.id.snake);
			if(snake_img==1)telo[1].setImageResource(R.drawable.eclipse_snake_1);
			if(snake_img==2)telo[1].setImageResource(R.drawable.eclipse_snake_2);
			if(snake_img==3)telo[1].setImageResource(R.drawable.eclipse_snake_3);
			if(snake_img==4)telo[1].setImageResource(R.drawable.eclipse_snake_4);
			if(snake_img==5)telo[1].setImageResource(R.drawable.eclipse_snake_5);
			if(snake_img==6)telo[1].setImageResource(R.drawable.eclipse_snake_6);
			if(snake_img==7)telo[1].setImageResource(R.drawable.eclipse_snake_7);
			if(snake_img==8)telo[1].setImageResource(R.drawable.eclipse_snake_8);
			if(snake_img==9)telo[1].setImageResource(R.drawable.eclipse_snake_9);
			if(snake_img==10)telo[1].setImageResource(R.drawable.eclipse_snake_10);
			if(snake_img==11)telo[1].setImageResource(R.drawable.eclipse_snake_11);
//			ok.setTitle(Integer.toString(snake_img));
//			ok.show();
			for(int i=2;i<=60;i++){
				telo[i]=new ImageView(this);
				
				if(snake_img==1)telo[i].setImageResource(R.drawable.eclipse_snake_1);
				if(snake_img==2)telo[i].setImageResource(R.drawable.eclipse_snake_2);
				if(snake_img==3)telo[i].setImageResource(R.drawable.eclipse_snake_3);
				if(snake_img==4)telo[i].setImageResource(R.drawable.eclipse_snake_4);
				if(snake_img==5)telo[i].setImageResource(R.drawable.eclipse_snake_5);
				if(snake_img==6)telo[i].setImageResource(R.drawable.eclipse_snake_6);
				if(snake_img==7)telo[i].setImageResource(R.drawable.eclipse_snake_7);
				if(snake_img==8)telo[i].setImageResource(R.drawable.eclipse_snake_8);
				if(snake_img==9)telo[i].setImageResource(R.drawable.eclipse_snake_9);
				if(snake_img==10)telo[i].setImageResource(R.drawable.eclipse_snake_10);
				if(snake_img==11)telo[i].setImageResource(R.drawable.eclipse_snake_11);
				
			}
		ImageView [] nazv=new ImageView[61];
		for(int i=1;i<=60;i++){
			String name="img"+i;
			int resID = getResources().getIdentifier(name,
				    "id", getPackageName());
		nazv[i]=(ImageView)this.findViewById(resID);
		}
	for (int i=1;i<=60;i++){
		if(dots_img==1)nazv[i].setImageResource(R.drawable.eclipse_dot_1);
		if(dots_img==2)nazv[i].setImageResource(R.drawable.eclipse_dot_2);
		if(dots_img==3)nazv[i].setImageResource(R.drawable.eclipse_dot_3);
		if(dots_img==4)nazv[i].setImageResource(R.drawable.eclipse_dot_4);
		if(dots_img==5)nazv[i].setImageResource(R.drawable.eclipse_dot_5);
		if(dots_img==6)nazv[i].setImageResource(R.drawable.eclipse_dot_6);
		if(dots_img==7)nazv[i].setImageResource(R.drawable.eclipse_dot_7);
		if(dots_img==8)nazv[i].setImageResource(R.drawable.eclipse_dot_8);
		if(dots_img==9)nazv[i].setImageResource(R.drawable.eclipse_dot_9);
		if(dots_img==10)nazv[i].setImageResource(R.drawable.eclipse_dot_10);
		if(dots_img==11)nazv[i].setImageResource(R.drawable.eclipse_dot_11);
		nazv[i].setOnClickListener(this);
		}
		int resID = getResources().getIdentifier(name,
			    "id", getPackageName());
		img2=(ImageView)this.findViewById(resID);
		AbsoluteLayout.LayoutParams params = ((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
		telo[1].setLayoutParams(params);	
			rand=r.nextInt(60)+1;
		save_apple=rand;
		name2="img"+rand;
			while (name2==name){
				rand=r.nextInt(60)+1;
				save_apple=rand;
				name2="img"+rand;
			}
			resID = getResources().getIdentifier(name2,
				    "id", getPackageName());
		
			img2=(ImageView)this.findViewById(resID);
			 params=((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
			 apple.setLayoutParams(params);
			 h=new Handler();
			 h2=new Handler();
			 Thread t2=new Thread (new Runnable(){
				 public void run(){
					 int rand=0;
						while(true){
					 try {
						Thread.sleep(100000/k);
						Random r=new Random();
						
						boolean apple_z=false;
						while (apple_z!=true)
						{	rand=r.nextInt(60)+1;
							for(int j=1;j<=k;j++)
							{
								if (rand!=telo_nom[j])
								{
									apple_z=true;
								}
								else
								{
									apple_z=false;
									break;
								}
							}
						}
					
						save_apple=rand;
						name2="img"+rand;
							while (name2==name){
								rand=r.nextInt(60)+1;
								save_apple=rand;
								name2="img"+rand;
							}
							int resID = getResources().getIdentifier(name2,
								    "id", getPackageName());
							img2=GetImage(resID);
						 h.post(changeApple);
						
						} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 }
				 }
			 });
			 
			 Thread t = new Thread (new Runnable(){
				 public void run (){
						while (true) {
						    try {
								Thread.sleep(1000);
								 seconds++;
								    if (seconds == 59) {
								        seconds = 0;
								        minutes++;
								    }
								    h.post(updateTime);
								   
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   
				 }
				 } 
			 });
			 t2.start();
			 t.start();
	}
	@Override
    protected Dialog onCreateDialog(int id) {
		switch(id){
		case 0:
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
	    // создаем view из dialog.xml
	    view = (RelativeLayout) getLayoutInflater()
	        .inflate(R.layout.dialog, null);
	    // устанавливаем ее, как содержимое тела диалога
	    adb.setView(view);

	    adb.setCancelable(false);
	   
	    // находим TexView для отображения кол-ва
	    res_text=(TextView)view.findViewById(R.id.applecoun);
	    res_text.setText("Wasted");
	    return adb.create();
		case 1:
			AlertDialog.Builder adb1 = new AlertDialog.Builder(this);
			adb1.setCancelable(false);
		    // создаем view из dialog.xml
		    view = (RelativeLayout) getLayoutInflater()
		        .inflate(R.layout.dialog, null);
		    // устанавливаем ее, как содержимое тела диалога
		    adb1.setView(view);
			
			
		    // находим TexView для отображения кол-ва
		    res_text=(TextView)view.findViewById(R.id.applecoun);
		    res_text.setText("Won");
		    return adb1.create();
		default:	   
			return null;
		}
	}
	  @Override
	  protected void onPrepareDialog(int id, Dialog dialog) {
		  super.onPrepareDialog(id, dialog);
		  but_exit=(Button)view.findViewById(R.id.button1);
			 res_text=(TextView)view.findViewById(R.id.applecoun);
		  but_exit.setOnClickListener(new OnClickListener(){
			@Override
			  public void onClick(View v){
				// TODO Auto-generated method stub
				  finish();
			}
			  
		  });
	  }
	Runnable updateTime= new Runnable(){
	public void run()
	{	if (minutes==0){
		
		time.setText(Integer.toString(seconds));
	}
	else{
	time.setText(minutes+":"+seconds);
	}
	} 	
		
	};
	Runnable changeApple =new Runnable(){
		public void run (){
				
						
						 apple.setLayoutParams(getParams(img2));
		
			
		
		}
	};
	
	public void findID(int r)
	{
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i=v.getId();
		int top=telo_nom[1]-1;
		int bot=telo_nom[1]+1;
		int left=telo_nom[1]-10;
		int right=telo_nom[1]+10;
		String name_top="img"+top;
		String name_bot="img"+bot;
		String name_left="img"+left;
		String name_right="img"+right;
		int topID = getResources().getIdentifier(name_top,
			    "id", getPackageName());
		int botID = getResources().getIdentifier(name_bot,
			    "id", getPackageName());
		int leftID = getResources().getIdentifier(name_left,
			    "id", getPackageName());
		int rightID = getResources().getIdentifier(name_right,
			    "id", getPackageName());
		
		
	if (i==topID && top>0  && getParams(GetImage(i))!=getParams(telo[2])){
	
	for(int j=2;j<=k;j++)
	{
		if((getParams(GetImage(i))==getParams(telo[j]))){
		
			  DIALOG=0;
				ContentValues cv=new ContentValues();
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				
				dbHelper.onCreate(db);
				Log.d(LOG_TAG, "---Insert in mytable---");
				cv.put("Result", "Wasted");
				cv.put("time", minutes+":"+seconds);
				cv.put("apple","apples:"+(k-1));
				long rowID=db.insert("mytable2", null, cv);
				Log.d(LOG_TAG, "row insterted, id ="+rowID);
				Log.d(LOG_TAG, "--ROWS IN MYTABLE");
				 c=db.query("mytable",null, null, null, null, null, null);
				if(c.moveToFirst()){
					int index=c.getColumnIndex("id");
					int result =c.getColumnIndex("Result");
					int time_db=c.getColumnIndex("time");
				do {
					Log.d(LOG_TAG,"id="+c.getInt(index)+"Result"+c.getString(result)+"time"+c.getString(time_db));
				}
				while (c.moveToNext());
				}else{
					Log.d(LOG_TAG, "0 ROWS");
					c.close();
					break;
				}
			    showDialog(DIALOG);
	
			break;
		}
		dbHelper.close();
		
	}
	
				for (int j=k;j>=2;j--){
					telo_nom[j]=telo_nom[j-1];
					
					String teloName="img"+telo_nom[j];
					telo_name[j]=teloName;
//					ok.setTitle(teloName);
//					ok.show();
					int teloID = getResources().getIdentifier(teloName,
						    "id", getPackageName());
					img2=GetImage(teloID);
	telo[j].setLayoutParams(getParams(img2));
					
				}
				telo_nom[1]=top;
				img2=(ImageView)this.findViewById(topID);
				AbsoluteLayout.LayoutParams param = ((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
				param_lot[1]=param;
				telo[1].setLayoutParams(param);	
	
			
	}
	if(i==botID && bot>0 && getParams(GetImage(i))!=getParams(telo[2])){
		
	for(int j=2;j<=k;j++)
	{
		if((getParams(GetImage(i))==getParams(telo[j]))){
			 DIALOG=0;
				ContentValues cv=new ContentValues();
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				dbHelper.onCreate(db);
				Log.d(LOG_TAG, "---Insert in mytable---");
				cv.put("Result", "Wasted");
				cv.put("time", minutes+":"+seconds);
				cv.put("apple","apples:"+(k-1));
				long rowID=db.insert("mytable2", null, cv);
				Log.d(LOG_TAG, "row insterted, id ="+rowID);
				Log.d(LOG_TAG, "--ROWS IN MYTABLE");
				 c=db.query("mytable",null, null, null, null, null, null);
				if(c.moveToFirst()){
					int index=c.getColumnIndex("id");
					int result =c.getColumnIndex("Result");
					int time_db=c.getColumnIndex("time");
				do {
					Log.d(LOG_TAG,"id="+c.getInt(index)+"Result"+c.getString(result)+"time"+c.getString(time_db));
				}
				while (c.moveToNext());
				}else{
					Log.d(LOG_TAG, "0 ROWS");
					c.close();
					break;
				}
				   showDialog(DIALOG);
	
			break;
		}
		dbHelper.close();
	}
		for (int j=k;j>=2;j--){
			
			telo_nom[j]=telo_nom[j-1];
		
			String teloName="img"+telo_nom[j];
			telo_name[j]=teloName;
//			ok.setTitle(teloName);
//			ok.show();
			int teloID = getResources().getIdentifier(teloName,
				    "id", getPackageName());
			img2=GetImage(teloID);
telo[j].setLayoutParams(getParams(img2));
			
		}
				telo_nom[1]=bot;
				img2=(ImageView)this.findViewById(botID);
				AbsoluteLayout.LayoutParams param = ((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
				param_lot[1]=param;
				telo[1].setLayoutParams(param);	

		
	}
	 if (i==rightID && right>0  && getParams(GetImage(i))!=getParams(telo[2]) )
	{ left=-1;
		 for(int j=2;j<=k;j++)
			{
				if((getParams(GetImage(i))==getParams(telo[j]))){
					 DIALOG=0;
						ContentValues cv=new ContentValues();
						SQLiteDatabase db=dbHelper.getWritableDatabase();
						dbHelper.onCreate(db);
						Log.d(LOG_TAG, "---Insert in mytable---");
						cv.put("Result", "Wasted");
						cv.put("time", minutes+":"+seconds);
						cv.put("apple","apples:"+(k-1));
						long rowID=db.insert("mytable2", null, cv);
						Log.d(LOG_TAG, "row insterted, id ="+rowID);
						Log.d(LOG_TAG, "--ROWS IN MYTABLE");
						 c=db.query("mytable",null, null, null, null, null, null);
						if(c.moveToFirst()){
							int index=c.getColumnIndex("id");
							int result =c.getColumnIndex("Result");
							int time_db=c.getColumnIndex("time");
						do {
							Log.d(LOG_TAG,"id="+c.getInt(index)+"Result"+c.getString(result)+"time"+c.getString(time_db));
						}
						while (c.moveToNext());
						}else{
							Log.d(LOG_TAG, "0 ROWS");
							c.close();
							break;
						}
						   showDialog(DIALOG);
			
					break;
				}
				dbHelper.close();

			}
		for (int j=k;j>=2;j--){
			telo_nom[j]=telo_nom[j-1];
		
			String teloName="img"+telo_nom[j];
			telo_name[j]=teloName;
//			ok.setTitle(teloName);
//			ok.show();
			int teloID = getResources().getIdentifier(teloName,
				    "id", getPackageName());
			img2=GetImage(teloID);
telo[j].setLayoutParams(getParams(img2));
			
		}
		telo_nom[1]=right;
		img2=(ImageView)this.findViewById(rightID);
		AbsoluteLayout.LayoutParams param = ((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
		param_lot[1]=param;
		telo[1].setLayoutParams(param);	
}
	 if(i==leftID && left>0  && getParams(GetImage(i))!=getParams(telo[2])){
		 right=-1;
		 for(int j=2;j<=k;j++)
			{
				if((getParams(GetImage(i))==getParams(telo[j]))){
					 DIALOG=0;
						ContentValues cv=new ContentValues();
						SQLiteDatabase db=dbHelper.getWritableDatabase();
						dbHelper.onCreate(db);
						Log.d(LOG_TAG, "---Insert in mytable---");
						cv.put("Result", "Wasted");
						cv.put("time", minutes+":"+seconds);
						cv.put("apple","apples:"+(k-1));
						long rowID=db.insert("mytable2", null, cv);
						Log.d(LOG_TAG, "row insterted, id ="+rowID);
						Log.d(LOG_TAG, "--ROWS IN MYTABLE");
						 c=db.query("mytable",null, null, null, null, null, null);
						if(c.moveToFirst()){
							int index=c.getColumnIndex("id");
							int result =c.getColumnIndex("Result");
							int time_db=c.getColumnIndex("time");
						do {
							Log.d(LOG_TAG,"id="+c.getInt(index)+"Result"+c.getString(result)+"time"+c.getString(time_db));
						}
						while (c.moveToNext());
						}else{
							Log.d(LOG_TAG, "0 ROWS");
							c.close();
							break;
						}
						   showDialog(DIALOG);
			
					break;
				}
				dbHelper.close();
				
			}
		for (int j=k;j>=2;j--){
			telo_nom[j]=telo_nom[j-1];
		
			String teloName="img"+telo_nom[j];
			telo_name[j]=teloName;
//			ok.setTitle(teloName);
//			ok.show();
			int teloID = getResources().getIdentifier(teloName,
				    "id", getPackageName());
			img2=GetImage(teloID);
telo[j].setLayoutParams(getParams(img2));
			
		}
			telo_nom[1]=left;
			img2=(ImageView)this.findViewById(leftID);
			AbsoluteLayout.LayoutParams param = ((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
			telo[1].setLayoutParams(param);
}
		
	Random r=new Random();
	Boolean apple_z=false;
	Integer rand=0;
	if (telo_nom[1]==save_apple){
		k=k+1;
		apple_count.setText(Integer.toString(k-1));
		if (k==61)
		{
			ContentValues cv=new ContentValues();
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Log.d(LOG_TAG, "---Insert in mytable---");
			cv.put("Result", "Won");
			cv.put("time", minutes+":"+seconds);
			cv.put("apple","apples:"+(k-1));
			long rowID=db.insert("mytable2", null, cv);
			  DIALOG=1;
			   showDialog(DIALOG);
		}
		telo[k].setLayoutParams(getParams(apple));
		telo_nom[k]=telo_nom[k-1];
	abl.addView(telo[k]);
	save_apple=rand;
	name2="img"+rand;
	while (apple_z!=true)
	{	rand=r.nextInt(60)+1;
		for(int j=1;j<=k;j++)
		{
			if (rand!=telo_nom[j])
			{
				apple_z=true;
			}
			else
			{
				apple_z=false;
				break;
			}
		}
	}
		save_apple=rand;
		name2="img"+rand;
			int resID = getResources().getIdentifier(name2,
				    "id", getPackageName());
			img2=(ImageView)this.findViewById(resID);
			AbsoluteLayout.LayoutParams	 params=((AbsoluteLayout.LayoutParams) img2.getLayoutParams());
			 apple.setLayoutParams(params);
		
	}
	
	}
	
	public  LayoutParams getParams(ImageView imageView){
		AbsoluteLayout.LayoutParams params = ((AbsoluteLayout.LayoutParams) imageView.getLayoutParams());
		return params;
		
	}
	public ImageView GetImage(int v){
		ImageView imageView=(ImageView)this.findViewById(v);
		return imageView;
	}
	}
class DBHelper extends SQLiteOpenHelper {
	final String LOG_TAG="myLogs";
	public  DBHelper(Context context)
	{
		super(context,"mydb",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG,"--ON CREATE DATABASE--");
		db.execSQL("create table if not exists `mytable2`("+"id integer primary key autoincrement,"
		+"Result text,"
		+"time text,"
		+"apple text"+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
