package example.pr1;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import example.pr1.R.raw;

public class Notifn extends Activity implements OnInitListener 
{Button b;
TextToSpeech t1;
@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.txtsp);
		b=(Button)findViewById(R.id.button1);
		//String mess=getIntent().getStringExtra("N");
		speakout("Hai hoW ARE YOU");
		b.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				long sec = 0,min,hr,ts;
				String st;
				Calendar cal=Calendar.getInstance();
				hr=cal.get(cal.HOUR);
				min=cal.get(cal.MINUTE);
				sec=cal.get(cal.SECOND);
				ts=cal.get(cal.AM_PM);
				st=ts>0?"pm":"am";
				
				
				
				while(sec!=40)
				{Log.d("Toast",""+sec);
				 cal=Calendar.getInstance();
				hr=cal.get(cal.HOUR);
				min=cal.get(cal.MINUTE);
				sec=cal.get(cal.SECOND);
				ts=cal.get(cal.AM_PM);
				st=ts>0?"pm":"am";
				
				}	
				
				{	NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				Notification notify=new Notification(android.R.drawable.stat_notify_chat,"This isimportant",System.currentTimeMillis());
				Intent in=new Intent(getApplicationContext(),Notifn.class);
				PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,in,0);
				notify.setLatestEventInfo(getApplicationContext(),"You have been notified","Continue if you needsbccdcb",pi);
				notify.sound=Uri.parse("android.resource://example.pr1/"+raw.beep);
					nm.notify(0,notify);
				Log.d("Toast",""+sec+" Success");
				}
				
				speakout("Hai hoW ARE YOU");
			}
			
			private void speakout(String cname) 
			{
				// TODO Auto-generated method stub
				
				
				
				//while(1==1)
				{t1.speak(cname,TextToSpeech.QUEUE_FLUSH,null);
				t1.setLanguage(Locale.ENGLISH);
				}
			}
		});
		
	}
@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	
								}

private void speakout(String cname) 
{
	// TODO Auto-generated method stub
	
	
	
	//while(1==1)
	{t1.speak(cname,TextToSpeech.QUEUE_FLUSH,null);
	t1.setLanguage(Locale.ENGLISH);
	}
}
}
