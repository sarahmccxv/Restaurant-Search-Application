import api.OkHttpApi;

import java.io.IOException;

public class TestOkHttp {
    public static void main(String[] args) throws IOException {
       OkHttpApi api = new OkHttpApi();
       String res = api.run("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo");
       System.out.println(res);
    }
}
