package nl.bertriksikken.stofradar.samenmeten.csv;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class SamenmetenCsvDownloader {
    
    private static final Logger LOG = LoggerFactory.getLogger(SamenmetenCsvDownloader.class);

    private final ISamenmetenCsvRestApi restApi;

    SamenmetenCsvDownloader(ISamenmetenCsvRestApi restApi) {
        this.restApi = restApi;
    }
    
    public static SamenmetenCsvDownloader create(SamenmetenCsvConfig config) {
        LOG.info("Creating new REST client for URL '{}' with timeout {}", config.getUrl(), config.getTimeout());
        OkHttpClient client = new OkHttpClient().newBuilder().callTimeout(config.getTimeout()).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(config.getUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client).build();
        ISamenmetenCsvRestApi restApi = retrofit.create(ISamenmetenCsvRestApi.class);
        return new SamenmetenCsvDownloader(restApi);
    }
    
    public String downloadDataFromFile(String compartiment) throws IOException {
        Response<String> response = restApi.getDataFromFile(compartiment).execute();
        return response.body();
    }

}