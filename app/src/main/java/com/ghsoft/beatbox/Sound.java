package com.ghsoft.beatbox;

/**
 * 管理获取到的声音资产的文件
 * 及其显示给用户看的文件名
 */
public class Sound {
    private final String mName;
    private String mAssetsPath;
    private Integer mSoundId;

    public Sound(String assetsPath) {
        mAssetsPath = assetsPath;
        String[] components = assetsPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public String getName() {
        return mName;
    }

    public String getAssetsPath() {
        return mAssetsPath;
    }

    public void setAssetsPath(String assetsPath) {
        mAssetsPath = assetsPath;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
