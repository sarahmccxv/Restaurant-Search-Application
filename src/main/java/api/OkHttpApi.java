package api;

import okhttp3.*;

import java.io.IOException;

public class OkHttpApi {
    public String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                  .build();
//        MediaType body = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                  .url(url)
//                  .method("GET", body)
                  .addHeader("Accept", "application/json")
                  .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}


