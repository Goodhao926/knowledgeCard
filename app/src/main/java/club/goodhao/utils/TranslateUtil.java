package club.goodhao.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import club.goodhao.config.ConstantData;
import club.goodhao.object.JsonGoogleTranslate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateUtil {
    private static final String TAG = "translateUtil";
    public interface TranslateResultListener{
        public void onSuccess(String res);
    }
    public static String translateEN2ZH(String en,TranslateResultListener translateResultListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(ConstantData.GOOGLE_TRANSLATE + en).build();
                try {
                    Response response = client.newCall(request).execute();
                    Gson gson = new Gson();
                    JsonGoogleTranslate jsonGoogleTranslate = new JsonGoogleTranslate();
                    jsonGoogleTranslate = gson.fromJson(response.body().string(), JsonGoogleTranslate.class);
                    translateResultListener.onSuccess(jsonGoogleTranslate.getSentences().get(0).getTrans());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "";

    }
}
