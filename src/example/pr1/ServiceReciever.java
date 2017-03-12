package example.pr1;

import java.util.HashMap;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class ServiceReciever extends BroadcastReceiver implements OnInitListener, OnUtteranceCompletedListener
{
	   static String contactName= "";
	   //static int f=0;
	   TextToSpeech t1;
	   int ready = 999;
	  
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
	    try {
	    	
	    	 
	    	
	        System.out.println("Receiver start");
	      
	        AudioManager audio_mngr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	    	context.getSystemService(Context.AUDIO_SERVICE);
	    	/*audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	    	*/
	       Toast.makeText(context," Receiver start ",Toast.LENGTH_SHORT).show();
	        //Log.v(, msg)
	        
	       
	       int mode =audio_mngr.getRingerMode();
	    	// int musicVolume= audio_mngr.getStreamVolume(AudioManager.STREAM_MUSIC);
	    	audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	    	// audio_mngr.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume, 0);
	    	audio_mngr.setStreamVolume(AudioManager.STREAM_MUSIC,audio_mngr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);

	    	
	       String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
	        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

	        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
	        {
	           // Toast.makeText(context,"Ringing State Number is -"+incomingNumber,Toast.LENGTH_SHORT).show();
	            contactName = getContactDisplayNameByNumber(incomingNumber,context);
	            Toast.makeText(context,"Success, called by "+contactName,Toast.LENGTH_LONG).show();
	          Log.v("ended","called by"+contactName);
	      
	       Glb G=(Glb)context.getApplicationContext();
	          String ts =G.getGlobalVarValue();
		        Toast.makeText(context,"ts's val: "+ts,Toast.LENGTH_LONG).show();
	        
	          //String ts="on";
	          if(ts.equals("off"))
	        	{
	        	  Toast.makeText(context,"it is off ,val::"+ts,Toast.LENGTH_LONG).show();
	        	}
	          else
	          {
	        	  
	        	  
		        	 Toast.makeText(context,"it is on ,val: "+ts,Toast.LENGTH_LONG).show();
		        	  Intent ins = new Intent(context,TTS.class);
		        	   ins.putExtra("iser",contactName);
		        	  context.startService(ins);
	          }
	        }
	        
	        
	        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
	        	{
	        	audio_mngr.setRingerMode(mode);
	            Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
	        	Toast.makeText(context,"Called by "+contactName,Toast.LENGTH_SHORT).show();
	        	
	        	}
	        
	    }    
	    catch (Exception e)
	    	{
	        e.printStackTrace();
	    	}

	
	}
	
	private void speakout(String cname) 
	{
		// TODO Auto-generated method stub
		
		t1.setLanguage(Locale.ENGLISH);
		//while(1==1)
		{t1.speak("cname is calling",TextToSpeech.QUEUE_FLUSH,null);
		
		}
	}
	public String getContactDisplayNameByNumber(String number,Context c) 
	{
	    Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
	    String name = "Unknown Number";

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
	        if(contactLookup==null){name="Unknown Number";}
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
	public void onInit(int status) 
	{
		
	}       

	@Override
	public void onUtteranceCompleted(String uttId) 
	{
		
	}

	

}
