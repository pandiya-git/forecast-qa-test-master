package api;

import okhttp3.*;

import java.io.IOException;
import java.net.URI;

public class FakeInterceptor implements Interceptor {

   @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        // do not change response in case not matched
        Response response = null;

        String responseString = null;
        final URI uri = chain.request().url().uri();
        final String pathValue = uri.getPath();
        if(pathValue.contains("/api/location/search/")){
            final String query = uri.getQuery();
            final String [] parseQuery = query.split("=");
            if(parseQuery[0].equalsIgnoreCase("query") && parseQuery[1].equalsIgnoreCase("Dubai")){
                responseString = dubWoeid;
            }
            else if (parseQuery[0].equalsIgnoreCase("query") && parseQuery[1].equalsIgnoreCase("Chennai")){
                responseString = chnWoeid;
            }
        }

        else {
            if (pathValue.contains("2295424")){
                responseString = wthrChennai;
            }
            else if (pathValue.contains("1940345")){
                responseString = wthrDubai;
            }
        }

        response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"),responseString.getBytes()))
                .addHeader("content-type","application/json")
                .build();

        //return chain.proceed(chain.request());
        return response;
    }

    private final static String chnWoeid = "[{\n" +
            "   \"title\": \"Chennai\",\n" +
            "   \"location_type\": \"City\",\n" +
            "   \"woeid\": 2295424,\n" +
            "   \"latt_long\": \"13.05939,80.245667\"\n" +
            "}]";

    private final static String dubWoeid = "[{\n" +
            "   \"title\": \"Dubai\",\n" +
            "   \"location_type\": \"City\",\n" +
            "   \"woeid\": 1940345,\n" +
            "   \"latt_long\": \"25.269440,55.308651\"\n" +
            "}]";

    private final static String wthrChennai = "[\n" +
            "      {\n" +
            "      \"id\": 6264875773853696,\n" +
            "      \"weather_state_name\": \"Heavy Rain\",\n" +
            "      \"weather_state_abbr\": \"hr\",\n" +
            "      \"wind_direction_compass\": \"SSW\",\n" +
            "      \"created\": \"2021-09-20T05:34:44.551631Z\",\n" +
            "      \"applicable_date\": \"2021-09-21\",\n" +
            "      \"min_temp\": 25.78,\n" +
            "      \"max_temp\": 33.58,\n" +
            "      \"the_temp\": 32.52,\n" +
            "      \"wind_speed\": 7.493307484291735,\n" +
            "      \"wind_direction\": 203.5,\n" +
            "      \"air_pressure\": 1007,\n" +
            "      \"humidity\": 67,\n" +
            "      \"visibility\": 9.999726596675416,\n" +
            "      \"predictability\": 77\n" +
            "   },\n" +
            "      {\n" +
            "      \"id\": 6411733859041280,\n" +
            "      \"weather_state_name\": \"Heavy Rain\",\n" +
            "      \"weather_state_abbr\": \"hr\",\n" +
            "      \"wind_direction_compass\": \"SSW\",\n" +
            "      \"created\": \"2021-09-20T02:34:44.048282Z\",\n" +
            "      \"applicable_date\": \"2021-09-21\",\n" +
            "      \"min_temp\": 26.240000000000002,\n" +
            "      \"max_temp\": 33.29,\n" +
            "      \"the_temp\": 32.3,\n" +
            "      \"wind_speed\": 5.932899864789629,\n" +
            "      \"wind_direction\": 206.49999999999997,\n" +
            "      \"air_pressure\": 1006,\n" +
            "      \"humidity\": 67,\n" +
            "      \"visibility\": 9.999726596675416,\n" +
            "      \"predictability\": 77\n" +
            "   }]";

    private final static String wthrDubai ="[\n" +
            "      {\n" +
            "      \"id\": 6254979498115072,\n" +
            "      \"weather_state_name\": \"Clear\",\n" +
            "      \"weather_state_abbr\": \"c\",\n" +
            "      \"wind_direction_compass\": \"W\",\n" +
            "      \"created\": \"2021-09-20T04:28:34.661906Z\",\n" +
            "      \"applicable_date\": \"2021-09-21\",\n" +
            "      \"min_temp\": 29.3,\n" +
            "      \"max_temp\": 34.94,\n" +
            "      \"the_temp\": 34.72,\n" +
            "      \"wind_speed\": 8.166401573199183,\n" +
            "      \"wind_direction\": 264.0436085262793,\n" +
            "      \"air_pressure\": 1005,\n" +
            "      \"humidity\": 60,\n" +
            "      \"visibility\": 12.00986240356319,\n" +
            "      \"predictability\": 68\n" +
            "   },\n" +
            "      {\n" +
            "      \"id\": 5027637127806976,\n" +
            "      \"weather_state_name\": \"Clear\",\n" +
            "      \"weather_state_abbr\": \"c\",\n" +
            "      \"wind_direction_compass\": \"W\",\n" +
            "      \"created\": \"2021-09-20T01:28:35.167451Z\",\n" +
            "      \"applicable_date\": \"2021-09-21\",\n" +
            "      \"min_temp\": 29.82,\n" +
            "      \"max_temp\": 34.93,\n" +
            "      \"the_temp\": 35.545,\n" +
            "      \"wind_speed\": 7.843730520642118,\n" +
            "      \"wind_direction\": 260.1561467094457,\n" +
            "      \"air_pressure\": 1004,\n" +
            "      \"humidity\": 54,\n" +
            "      \"visibility\": 12.546105742464011,\n" +
            "      \"predictability\": 68\n" +
            "   }]";
}
