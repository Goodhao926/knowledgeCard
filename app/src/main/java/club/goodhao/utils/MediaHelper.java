package club.goodhao.utils;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import club.goodhao.R;
import club.goodhao.config.ConstantData;

public class MediaHelper {
    public static MediaPlayer mediaPlayer;
    public static final int EN = 0;
    public static final int USA = 1;

    public static void playWord(int type, String word) {
        if (mediaPlayer == null) {
            releasePlayer();
        }
        mediaPlayer = new MediaPlayer();
        String source = String.format("%s%s", type == 0 ? ConstantData.YOU_DAO_VOICE_EN : ConstantData.YOU_DAO_VOICE_USA, word);
        Log.e("MediaHelper", "playWord: " + source);
        try {
            mediaPlayer.setDataSource(source);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void releasePlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
