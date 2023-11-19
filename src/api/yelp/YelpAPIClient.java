package api.yelp;

import okhttp3.*;

import java.io.IOException;

public class YelpAPIClient {
    private final String API_TOKEN = "m50nmIojrs9_k4NDBc7TeGaSoPFtLXERQpG1o17SNWvp29XQbhSveJAzFwvodpyx2PCZX8yLA-37ULJKxE-Dxno0Hlpb1RfsnSk_3fWjEadWEjs9MPmpOQbhwHxMZXYx";

    public Response makeGETRequest(String API_URL) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        return client.newCall(request).execute();
    }
}
