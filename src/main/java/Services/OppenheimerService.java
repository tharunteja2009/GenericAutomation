package Services;

import Models.ReliefSummary;
import Models.TaxRelief;
import Models.WorkClassHero;
import Utils.PropertyManager;
import Utils.RestClient;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OppenheimerService {
  static String oppenheimerServiceUrl = PropertyManager.oppenheimerServiceUrl;

  public static void addWorkClassHero(WorkClassHero workClassHero){
    String body = RestClient.gson().toJson(workClassHero);
    Response response = RestClient.given().request().contentType("application/json")
            .body(body).post(oppenheimerServiceUrl+"/calculator/insert").andReturn();
    response.prettyPrint();
    response.then().statusCode(202);
  }

  public static void addListOfWorkClassHero(List<WorkClassHero> workClassHeros){
    Type listType = new TypeToken<List<WorkClassHero>>() {}.getType();
    String body = RestClient.gson().toJson(workClassHeros,listType);
    Response response = RestClient.given().request().contentType("application/json")
        .body(body).post(oppenheimerServiceUrl+"/calculator/insertMultiple").andReturn();
    response.prettyPrint();
    response.then().statusCode(202);
  }


  public static ReliefSummary getTaxReliefSummary(){
    Response response = RestClient.given().request().contentType("application/json")
        .get(oppenheimerServiceUrl+"/calculator/taxReliefSummary").andReturn();
    response.then().statusCode(200);
    ReliefSummary reliefSummary = RestClient.gson().fromJson(response.getBody().asString(),ReliefSummary.class);
    return reliefSummary;
  }

  public static List<TaxRelief> getListOfTaxRelief(){
    Response response = RestClient.given().request().contentType("application/json")
        .get(oppenheimerServiceUrl+"/calculator/taxRelief").andReturn();
    response.then().statusCode(200);
    Type userListType = new TypeToken<ArrayList<TaxRelief>>(){}.getType();
    List<TaxRelief> taxReliefList = RestClient.gson().fromJson(response.getBody().asString(),userListType);
    taxReliefList.stream().forEach(System.out::println);
    return taxReliefList;
  }

}
