package example.pr1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

public class ReadTheMessage extends Activity implements OnInitListener,OnUtteranceCompletedListener 
{

private TextToSpeech tts = null;
private String msg = "";

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent startingIntent = this.getIntent();
    msg = startingIntent.getStringExtra("MESSAGE");
    tts = new TextToSpeech(this,this);
}

@Override
protected void onDestroy() {
    super.onDestroy();
    if (tts!=null) {
        tts.shutdown();
    }
}

// OnInitListener impl
public void onInit(int status) {
    tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
}

// OnUtteranceCompletedListener impl
public void onUtteranceCompleted(String utteranceId) {
    tts.shutdown();
    tts = null;
    finish();
}
}