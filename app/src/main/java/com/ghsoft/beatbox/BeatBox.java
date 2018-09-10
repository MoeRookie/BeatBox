package com.ghsoft.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 为了能在应用中定位、管理记录以及播放声音资产资源
 * 资产资源管理类
 */
public class BeatBox {
    private static final String TAG = "BeatBox"; // 记录日志
    private static final String SOUNDS_FOLDER = "sample_sounds"; // 存储声音资产文件目录名
    private final AssetManager mAssets;
    private final SoundPool mSoundPool;
    private List<Sound> mSounds = new ArrayList<>();
    private static final int MAX_SOUNDS = 5;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        // 这个旧的构造函数不赞成使用，但为了兼容性我们需要它
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    /**
     * 加载所有声音资产
     */
    private void loadSounds() {
        String[] soundNames = null;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.e(TAG, "Could not list assets",ioe);
        }
        for (String fileName : soundNames) { // 遍历声音资产文件名清单,转化为sound列表
            // 加载当前的声音资产资源
            try {
                String assetPath = SOUNDS_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + fileName,ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException {
        // 根据文件路径获取当前音频资产资源的文件描述符
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetsPath());
        // 加载该描述符所指向的音频资产资源文件
        int soundId = mSoundPool.load(afd, 1);
        // 返回的id即为当前音频资产资源文件的id
        sound.setSoundId(soundId);
    }
    public void play(Sound sound){
        // 获取描述当前声音资产资源的soundId
        Integer soundId = sound.getSoundId();
        // 由于存在文件加载失败的情况,加载失败时无需播放
        if (soundId == null) {
            return;
        }
        // 加载成功后获取到soundId,播放音频文件
        //                       左右音量 - 最大                  优先级 - 无效 不循环 常速播放
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
    public List<Sound> getSounds() {
        return mSounds;
    }
}
