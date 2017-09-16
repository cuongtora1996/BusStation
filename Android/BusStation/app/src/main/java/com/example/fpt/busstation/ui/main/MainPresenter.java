package com.example.fpt.busstation.ui.main;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import com.example.fpt.busstation.ui.base.BasePresenter;

import java.io.File;
import java.util.Random;

/**
 * Created by cuong on 9/13/2017.
 */

public class MainPresenter<T extends MainMvpView> extends BasePresenter<T> implements MainMvpPresenter<T> {
    MediaPlayer mediaPlayer ;
    private MediaRecorder mediaRecorder;
    private static final int PERMISSION_AUDIO = 2;
    private static final String AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath()+"/voiceRecord.3gp";
    public MainPresenter(){
        super();
    }

    @Override
    public void startRecordAudio() {
        if(checkPermissionAudio()){
            mediaRecorderReady();
            try{
                mediaRecorder.prepare();
                mediaRecorder.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            getMvpView().requestPermissionsSafely(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},PERMISSION_AUDIO);
        }
    }
    public void mediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }
    public boolean checkPermissionAudio(){
        return getMvpView().hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)&&getMvpView().hasPermission(Manifest.permission.RECORD_AUDIO);
    }
    @Override
    public void stopRecordAudio() {
        if(!checkPermissionAudio()) return;
        getMvpView().showLoading();
        mediaRecorder.stop();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                mediaPlayer.release();
                File file = new File(AudioSavePathInDevice);
                file.delete();
                getMvpView().hideLoading();
            }
        });
        try {
            mediaPlayer.setDataSource(AudioSavePathInDevice);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}
