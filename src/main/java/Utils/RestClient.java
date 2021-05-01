package Utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import java.lang.reflect.Modifier;

public class RestClient {

  public static RequestSpecification given() {
    LogConfig logConfig = LogConfig.logConfig().enablePrettyPrinting(true)
        .enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssuredConfig assuredConfig = RestAssuredConfig.config().logConfig(logConfig);
    RequestSpecification given = RestAssured.given().config(assuredConfig).relaxedHTTPSValidation()
        .log().ifValidationFails();
    return given;
  }

  private static final ExclusionStrategy EXCLUSION_STRATEGY = new ExclusionStrategy() {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
      return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
      return false;
    }
  };

  public static Gson gson() {
    return GSON;
  }

  private static final Gson GSON = new GsonBuilder()
      .addSerializationExclusionStrategy(EXCLUSION_STRATEGY)
      .excludeFieldsWithModifiers(Modifier.STATIC)
      .setPrettyPrinting().create();

}
