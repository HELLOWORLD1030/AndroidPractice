package top.zzgpro.androidpractice.Utils;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static String PostRequest(String url,String json) throws IOException {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static String AsyncGetRequest(String url){

      return "";
    }
}
