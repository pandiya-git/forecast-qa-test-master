package api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MockClient {

    private static ForecastService mForeCastService = null;

    public static ForecastService getClient() {

        if(mForeCastService == null) {
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new FakeInterceptor())
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ForecastService.BaseURL)
                    .client(client)
                    .build();

            mForeCastService = retrofit.create(ForecastService.class);
        }
        return mForeCastService;
    }
}
