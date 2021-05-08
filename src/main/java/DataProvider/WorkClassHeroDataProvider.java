package DataProvider;

import Models.WorkClassHero;
import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;

public class WorkClassHeroDataProvider {

  static public List<WorkClassHero> recordedWorkClassHeros = new ArrayList<>();

  public static WorkClassHero getSingleWorkClassHero() {
    WorkClassHero workClassHero = new WorkClassHero();
    workClassHero.setBirthday("10091990");
    workClassHero.setGender("M");
    workClassHero.setName("user1");
    workClassHero.setNatid("123456");
    workClassHero.setSalary("1000");
    workClassHero.setTax("100");
    recordedWorkClassHeros.add(workClassHero);
    return workClassHero;
  }

  public static List<WorkClassHero> getMultipleWorkClassHero(DataTable table) {
    List<WorkClassHero> workClassHeroList = new ArrayList<>();
    List<String> natids = table.column(0);
    List<String> names = table.column(1);
    List<String> genders = table.column(2);
    List<String> birthdays = table.column(3);
    List<String> salary = table.column(4);
    List<String> tax = table.column(5);
    for (int i = 1; i < natids.size(); i++) {
      WorkClassHero workClassHero = new WorkClassHero();
      workClassHero.setBirthday(birthdays.get(i));
      workClassHero.setGender(genders.get(i));
      workClassHero.setName(names.get(i));
      workClassHero.setNatid(natids.get(i));
      workClassHero.setSalary(salary.get(i));
      workClassHero.setTax(tax.get(i));
      workClassHeroList.add(workClassHero);
    }
    for(WorkClassHero hero : workClassHeroList) {
      recordedWorkClassHeros.add(hero);
    }
    return workClassHeroList;
  }

}
