package com.ghsoft.beatbox;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class BeatBoxActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
