package com.ghsoft.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    /**
     * 获取所有声音资产的文件名
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
            String assetPath = SOUNDS_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
