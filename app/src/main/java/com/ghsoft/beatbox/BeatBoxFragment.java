package com.ghsoft.beatbox;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ghsoft.beatbox.databinding.FragmentBeatBoxBinding;
import com.ghsoft.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box,container,false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
        // todo=> 待删除
        // View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        // RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        // recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        // recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        // return view;
    }
    private class SoundHolder extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
//    todo=>待删除
//    private class SoundHolder extends RecyclerView.ViewHolder
//    implements View.OnClickListener{
//        private final Button mButton;
//        private Sound mSound;
//        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
//            super(inflater.inflate(R.layout.list_item_sound,container,false));
//            mButton = itemView.findViewById(R.id.list_item_sound_button);
//            mButton.setOnClickListener(SoundHolder.this);
//        }
//        public void bindSound(Sound sound){
//            mSound = sound;
//            mButton.setText(mSound.getName());
//        }
//
//        @Override
//        public void onClick(View v) {
//            // 播放当前音频资产资源文件
//            mBeatBox.play(mSound);
//        }
//    }
//    todo=> 待删除
//    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
//        private List<Sound> mSounds;
//
//        public SoundAdapter(List<Sound> sounds) {
//            mSounds = sounds;
//        }
//
//        @NonNull
//        @Override
//        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            LayoutInflater inflater = LayoutInflater.from(getActivity());
//            return new SoundHolder(inflater,viewGroup);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int i) {
//            Sound sound = mSounds.get(i);
//            soundHolder.bindSound(sound);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mSounds.size();
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }
}
