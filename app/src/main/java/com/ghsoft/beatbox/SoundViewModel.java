package com.ghsoft.beatbox;

public class SoundViewModel {
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound(){
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
    }
}
