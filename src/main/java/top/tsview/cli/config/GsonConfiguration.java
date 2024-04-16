package top.tsview.cli.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfiguration {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final JsonSerializer<LocalDateTime> DATETIME_SERIALIZER = (datetime, type, context) -> new JsonPrimitive(datetime.format(DATETIME_FORMATTER));
    private static final JsonDeserializer<LocalDateTime> DATETIME_DESERIALIZER = (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DATETIME_FORMATTER);

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .registerTypeAdapter(LocalDateTime.class, DATETIME_SERIALIZER)
                .registerTypeAdapter(LocalDateTime.class, DATETIME_DESERIALIZER)
                .create();
    }

    @Bean
    @Primary
    public HttpMessageConverter<?> messageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

}
