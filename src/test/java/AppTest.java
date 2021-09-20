import api.ForecastServiceImpl;
import org.junit.jupiter.api.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    protected static Retrofit retrofit;
    static LocalDate tomorrow =LocalDate.now().plusDays(1);
    static ForecastServiceImpl service;
    @BeforeAll
    static void startUp(){
        retrofit = new Retrofit.Builder()
                //.baseUrl("https://www.mockweather.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = new ForecastServiceImpl(retrofit);
    }

    @Test
    @DisplayName("Chennai's tomorrow weather report")
    void testApp() throws IOException {
        String resp= service.getForecast("Chennai", tomorrow);
        System.out.println(resp);
        assertTrue(resp.contains("Chennai"));
        assertTrue(resp.contains("Humidity"));
        assertTrue(resp.contains("Wind"));

    }

    @Test
    @DisplayName("Dubai's tomorrow weather report")
    void testApp1() throws IOException {
        String resp= service.getForecast("Dubai", tomorrow);
        System.out.println(resp);
        assertTrue(resp.contains("Dubai"));
        assertTrue(resp.contains("Humidity"));
        assertTrue(resp.contains("Wind"));
    }

    @AfterAll
    static void tearDown(){
        System.exit(0);
    }

}