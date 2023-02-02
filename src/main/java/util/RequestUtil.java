package util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RequestUtil {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static String sendRequest(int startNum, int endNum) throws IOException {
        Request request = new Request.Builder()
                .url(
                        new StringBuilder()
                                .append(ConfigUtil.getApiConfig().getUrl())
                                .append("/" + ConfigUtil.getApiConfig().getKey())
                                .append("/" + ConfigUtil.getApiConfig().getType())
                                .append("/" + ConfigUtil.getApiConfig().getName())
                                .append("/" + String.valueOf(startNum))
                                .append("/" + String.valueOf(endNum))
                                .toString()
                )
                .addHeader("Content-type", "application/json")
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        return response.body().string();
    }
//    public static void main(String[] args) throws IOException {
//        System.out.println(sendRequest(1, 10));
//    }
}
