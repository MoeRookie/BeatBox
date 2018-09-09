package com.ghsoft.beatbox;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * 为了能在应用中定位、管理记录以及播放声音资产资源
 * 资产资源管理类
 */
public class BeatBox {
    private static final String TAG = "BeatBox"; // 记录日志
    private static final String SOUNDS_FOLDER = "sample_sounds"; // 存储声音资产文件目录名
    private final AssetManager mAssets;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
    }
}
