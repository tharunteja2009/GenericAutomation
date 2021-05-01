package Services;

import Models.WorkClassHero;
import Utils.PropertyManager;
import Utils.RestClient;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import java.lang.reflect.Type;
import java.util.List;

public class OppenheimerService {

  public void addWorkClassHero(WorkClassHero workClassHero){
    String oppenheimerServiceUrl = PropertyManager.oppenheimerServiceUrl;
    String body = RestClient.gson().toJson(workClassHero);
    Response response = RestClient.given().request().contentType("application/json")
            .body(body).post(oppenheimerServiceUrl+"/calculator/insert").andReturn();
    System.out.println(response.prettyPrint());
    response.then().statusCode(200);
  }

  public void addListOfWorkClassHero(List<WorkClassHero> workClassHeros){
    String oppenheimerServiceUrl = PropertyManager.oppenheimerServiceUrl;
    Type listType = new TypeToken<List<WorkClassHero>>() {}.getType();
    String body = RestClient.gson().toJson(workClassHeros,listType);
    Response response = RestClient.given().request().contentType("application/json")
        .body(body).post(oppenheimerServiceUrl+"/calculator/insertMultiple").andReturn();
    System.out.println(response.prettyPrint());
    response.then().statusCode(200);
  }

}
