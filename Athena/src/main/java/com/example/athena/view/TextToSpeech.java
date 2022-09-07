package com.example.athena.view;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextToSpeech {
    private static TextToSpeech instance = null;

    private final Synthesizer synthesizer ;

    private TextToSpeech(Synthesizer synthesizer) throws EngineException, AudioException{
        this.synthesizer = synthesizer ;
        this.synthesizer.allocate();
        this.synthesizer.resume();
    }

    public static synchronized TextToSpeech getInstance() throws EngineException, AudioException {
        if (instance == null) {
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            instance = new TextToSpeech(Central.createSynthesizer(new SynthesizerModeDesc(Locale.US))) ;

        }
        return instance;
    }

    public void speak(String text) {
        this.synthesizer.speakPlainText(text, null) ;
    }

}
