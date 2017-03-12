package example.pr1;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener 
{
	Button b1,b2;
	TextView tv;
	EditText e1;
	String sph="";
	String cname="Nothing found";
	TextToSpeech t1;
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		e1=(EditText)findViewById(R.id.editText1);
		tv=(TextView)findViewById(R.id.textView1); 
		t1=new TextToSpeech(this,this);
		/*final AudioManager am;
		 am= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		 if(am.getRingerMode()!=AudioManager.RINGER_MODE_SILENT)
			{am.setRingerMode(AudioManager.RINGER_MODE_SILENT);}
		 //am.setRingerMode(0)
		am.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
		  
		  */
		   final Glb G=(Glb)getApplicationContext(); 
		   G.setGlobalVarValue("on");
		b1.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				/*Intent in=new Intent();
				in.setAction("br");
				sendBroadcast(in);
				*/
				 G.setGlobalVarValue("off");
				  String ts =G.getGlobalVarValue();
			        Toast.makeText(getApplicationContext(),"Value set:"+ts,Toast.LENGTH_SHORT).show();
				
			}
		});
		b2.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				/*
				
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				switch (am.getRingerMode()) 
								{
			    case AudioManager.RINGER_MODE_SILENT:
			        Log.i("MyApp","Silent mode");
			        Toast.makeText(getApplicationContext(),"Silent",Toast.LENGTH_SHORT).show();
			        break;
			    case AudioManager.RINGER_MODE_VIBRATE:
			        Log.i("MyApp","Vibrate mode");
			        Toast.makeText(getApplicationContext(),"Vibrate",Toast.LENGTH_SHORT).show();
			        break;
			    case AudioManager.RINGER_MODE_NORMAL:
			        Log.i("MyApp","Normal mode");
			        Toast.makeText(getApplicationContext(),"Normal",Toast.LENGTH_SHORT).show();
			        break;
			    default:    
			    	Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
			
								}
			*/
			/*sph=e1.getText().toString();
			if(sph.length()==0){e1.setError("No input found");}
			*/
			//cname=getContactDisplayNameByNumber(sph,getBaseContext());
			//tv.setText(cname);
			//Toast.makeText(getApplicationContext(),cname,Toast.LENGTH_SHORT).show();
				 G.setGlobalVarValue("on");
				  String ts =G.getGlobalVarValue();
			        Toast.makeText(getApplicationContext(),"Value set:"+ts,Toast.LENGTH_SHORT).show();
				
			}
			
			
		});
		
	}
	private void speakout(String cname) 
	{
		// TODO Auto-generated method stub
		
		
		Log.e("Imp","Inside fn body");
		t1.speak("hello",TextToSpeech.QUEUE_FLUSH,null);
		t1.setLanguage(Locale.ENGLISH);
		Log.e("Imp","Inside fn body end");

		
	}
	
/*public void broadcastsent(View v)
	{Intent in=new Intent();
	in.setAction("br");
	sendBroadcast(in);
	
	}
*/	
	
	public String getContactDisplayNameByNumber(String number,Context c) 
	{
	    Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
	    String name = "Incoming call from";

	    ContentResolver contentResolver = c.getContentResolver();
	    Cursor contactLookup = contentResolver.query(uri, null, null, null, null);

	    try {
	        if (contactLookup != null && contactLookup.getCount() > 0) {
	            contactLookup.moveToNext();
	            name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
	            Log.v("testname","called by"+name);
	            // this.id =
	            // contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.CONTACT_ID));
	            // String contactId =
	            // contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
	        }
	    } finally 
	     {
	        if (contactLookup != null) 
	        {
	            contactLookup.close();
	        }
	     }

	    return name;
	}
@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	
}

}
