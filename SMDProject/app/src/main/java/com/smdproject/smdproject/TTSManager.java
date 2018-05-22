package com.smdproject.smdproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;


public class TTSManager {

    private TextToSpeech mTts = null;
    private boolean isLoaded = false;
    private Context context;

    public void init(Context context) {
        this.context=context;
        try {
            mTts = new TextToSpeech(context, onInitListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = mTts.setLanguage(Locale.US);
                isLoaded = true;

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(context,"Lanuage not supported.",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context,"Text-To-Speech initialization failed.",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void shutDown() {
        mTts.shutdown();
    }

    public void addQueue(String text) {
        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_ADD, null);
        else
            Toast.makeText(context,"Text-To-Speech is not initialized.",Toast.LENGTH_SHORT).show();

    }

    public void initQueue(String text) {

        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        else
            Toast.makeText(context,"Text-To-Speech is not initialized.",Toast.LENGTH_SHORT).show();
    }
}