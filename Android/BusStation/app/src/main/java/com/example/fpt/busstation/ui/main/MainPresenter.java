package com.example.fpt.busstation.ui.main;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.fpt.busstation.data.conn.RestClient;
import com.example.fpt.busstation.data.conn.request.IntentRequest;
import com.example.fpt.busstation.data.conn.request.RouteRequest;
import com.example.fpt.busstation.data.conn.request.StationRequest;
import com.example.fpt.busstation.data.conn.response.IntentResponse;
import com.example.fpt.busstation.data.conn.response.RouteResponse;
import com.example.fpt.busstation.data.conn.response.StationResponse;
import com.example.fpt.busstation.data.db.IntentDTO;
import com.example.fpt.busstation.service.OnResponseStringListener;
import com.example.fpt.busstation.ui.base.BasePresenter;
import com.example.fpt.busstation.ui.behaviorbottom.RouteInstructionViewPagerFragment;
import com.example.fpt.busstation.ui.behaviorbottom.dto.BusDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.RecommendRoutesDto;
import com.example.fpt.busstation.ui.behaviorbottom.dto.StationDto;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by cuong on 9/13/2017.
 */

public class MainPresenter<T extends MainMvpView> extends BasePresenter<T> implements MainMvpPresenter<T> {
    MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private static final int PERMISSION_AUDIO = 2;
    private static final String AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/voiceRecord.3gp";

    public MainPresenter() {
        super();
    }


    @Override
    public void sendDetectRequest(final Double lng, final Double lat, String text) {
        Log.d("Send request", "test");
        IntentRequest.sendGetRequest(text, new OnResponseStringListener() {
            @Override
            public void onResponse(String data) {
                try {
                    IntentDTO dto = IntentResponse.convertData(data);

                    sendTTSRequest("Xác nhận " + dto.getMess());
                    switch (dto.getType()) {
                        case 1:
                            sendStationRequest(lng, lat, "", dto.getType());
                            break;
                        case 2:
                            sendStationRequest(lng, lat, "", dto.getType());
                            break;
                        case 3:
                            sendStationRequest(lng, lat, dto.getBusnum(), dto.getType());
                            break;
                        case 4:
                            sendStationRequest(lng, lat, dto.getBusnum(), dto.getType());
                            break;
                        case 5:
                            sendRouteRequest(lng, lat, dto.getBegin(), dto.getEnd(), dto.getType());
                            break;
                        case 6:
                            sendRouteRequest(lng, lat, "", dto.getEnd(), dto.getType());
                            break;
                        case 7:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
    }

    @Override
    public void sendRouteRequest(Double lng, Double lat, String begin, String end, int type) {

        RouteRequest.sendGetRequest(lng, lat, begin, end, type, new OnResponseStringListener() {
            @Override
            public void onResponse(String data) {
                List<RecommendRoutesDto> result = RouteResponse.convertData(data);
                Log.d("===convertData", "size: " + result.size());
                getMvpView().removeAllMarkerAndPolyline();
                getMvpView().showRouteInstruction(result);
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
    }

    @Override
    public void sendStationRequest(Double lng, Double lat, String number, int type) {

        StationRequest.sendGetRequest(lng, lat, number, type, new OnResponseStringListener() {
            @Override
            public void onResponse(String data) {
                List<StationDto> result = StationResponse.convertData(data);
                getMvpView().removeAllMarkerAndPolyline();
                for (int i = 0; i < result.size(); i++) {
                    StationDto dto = result.get(i);
                    getMvpView().placeStation(dto.getLng(), dto.getLat(), dto.getStationAddress(), dto.getStationName(), i);
                }
                getMvpView().showBusAndStation(result);
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
    }

    @Override
    public void sendTTSRequest(String text) {
        Log.d("String text", text);
        RestClient.getInstance().postAudioRequest("http://api.openfpt.vn/text2speech/v4", text, new OnResponseStringListener() {
            @Override
            public void onResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    int success = jsonObject.getInt("error");
                    if (success == 0) {
                        String url = jsonObject.getString("async");
                        try {
                            MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(url);
                            player.prepare();
                            player.start();
                        } catch (Exception e) {

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });

    }

    @Override
    public void startRecordAudio() {
        if (checkPermissionAudio()) {
            mediaRecorderReady();
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getMvpView().requestPermissionsSafely(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, PERMISSION_AUDIO);
        }
    }

    public void mediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public boolean checkPermissionAudio() {
        return getMvpView().hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && getMvpView().hasPermission(Manifest.permission.RECORD_AUDIO);
    }

    @Override
    public void stopRecordAudio() {
        if (!checkPermissionAudio()) return;
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
