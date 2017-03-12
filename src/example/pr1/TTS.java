package example.pr1;

import java.util.HashMap;
import java.util.Locale;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.widget.Toast;

public class TTS extends Service implements TextToSpeech.OnInitListener, OnUtteranceCompletedListener {
    private TextToSpeech mTts;
    private String spokenText="default";
    
    @Override
    public void onCreate() {
    	Toast.makeText(getApplicationContext(),"Tts Service started",1000).show();
        mTts = new TextToSpeech(this, this);
        
        // This is a good place to set spokenText
    }

    @Override
    public void onInit(int status) {
    	
    	
    	
        if (status == TextToSpeech.SUCCESS) 
        	{
            int result = mTts.setLanguage(Locale.US);
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) 
            {
            	mTts.setSpeechRate((float) 0.5);
               //mTts.setPitch((float) 1.5);
             Toast.makeText(getApplicationContext(),"Servce middle",1000).show();
            // mTts.speak("You are called by "+spokenText+"i repeat You are called by "+spokenText+"i repeat You are called by "+spokenText, TextToSpeech.QUEUE_FLUSH, null);
             
            	this.mTts.setOnUtteranceCompletedListener(this);
            	HashMap<String, String> params = new HashMap<String, String>();

            	params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"stringId");
            	
            	mTts.speak("You are called by "+spokenText+"i repeat You are called by "+spokenText+"i repeat You are called by "+spokenText,TextToSpeech.QUEUE_FLUSH, params);
             Toast.makeText(getBaseContext(),"after speech",Toast.LENGTH_SHORT).show();
             Glb G=((Glb)getApplicationContext());
     		  String ts =G.getGlobalVarValue();
     	        Toast.makeText(getApplicationContext(),"GSTRING:"+ts,Toast.LENGTH_LONG).show();
             //stopSelf();
     	       AudioManager audio_mngr = (AudioManager)getBaseContext().getSystemService(Context.AUDIO_SERVICE);
   	    	getBaseContext().getSystemService(Context.AUDIO_SERVICE);
   	    	audio_mngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
     	     
     	    }
            }
        else
        {  Toast.makeText(this, "Failed to initialize TTS.", Toast.LENGTH_SHORT).show();}    
    }
        
    

    @Override
    public void onUtteranceCompleted(String uttId) 
    {
    	//Toast.makeText(getApplicationContext(),"Utterance Over id:"+uttId,Toast.LENGTH_LONG ).show();;
    	/*
    	*/
    	 
    	stopSelf();
       	
    	
    }

    @Override
    public void onDestroy() 
    {
        if (mTts != null)
        {
            mTts.stop();
            mTts.shutdown();
        }
        
        
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) 
    {
        return null;
    }
   @Override
public int onStartCommand(Intent intent, int flags, int startId)
   {
	// TODO Auto-generated method stub
	   spokenText=intent.getStringExtra("iser");
	return super.onStartCommand(intent, flags, startId);
   }
}