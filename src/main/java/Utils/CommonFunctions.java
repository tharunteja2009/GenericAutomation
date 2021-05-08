package Utils;

import Exceptions.GenericAutomationException;
import Models.TaxRelief;
import Models.WorkClassHero;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonFunctions {

  public static String maskString(String strText, int start, int end, char maskChar) {
    if (strText == null || strText.equals("")) {
      return "";
    }
    if (start < 0) {
      start = 0;
    }
    if (end > strText.length()) {
      end = strText.length();
    }
    if (start > end) {
      try {
        throw new Exception("End index cannot be greater than start index");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    int maskLength = end - start;
    if (maskLength == 0) {
      return strText;
    }
    StringBuilder sbMaskString = new StringBuilder(maskLength);
    for (int i = 0; i < maskLength; i++) {
      sbMaskString.append(maskChar);
    }
    return strText.substring(0, start)
        + sbMaskString.toString()
        + strText.substring(start + maskLength);
  }

  public static BigDecimal getTaxReleif(WorkClassHero workClassHero) {
    BigDecimal variable = BigDecimal.ZERO;
    BigDecimal genderBonus = new BigDecimal(0);
    BigDecimal taxRelief;
    Integer birthday = Integer.valueOf(workClassHero.getBirthday().substring(0, 2));
    Integer birthMonth = Integer.valueOf(workClassHero.getBirthday().substring(2, 4));
    Integer birthYear = Integer.valueOf(workClassHero.getBirthday().substring(4, 8));
    LocalDate previous = LocalDate.of(birthYear, birthMonth, birthday);
    LocalDate now = LocalDate.now();
    Period diff = Period.between(previous, now);
    Integer personAge = diff.getYears();
    if (personAge <= 18) {
      variable = BigDecimal.valueOf(1.0D);
    }
    if (personAge>18 && personAge <= 35) {
      variable = BigDecimal.valueOf(0.8D);
    }
    if (personAge>35 && personAge <= 50) {
      variable = BigDecimal.valueOf(0.5D);
    }
    if (personAge>50 && personAge <= 75) {
      variable = BigDecimal.valueOf(0.367D);
    } else if (personAge>75){
      variable = BigDecimal.valueOf(0.05D);
    }
    if (workClassHero.getGender().equalsIgnoreCase("F")) {
      genderBonus = BigDecimal.valueOf(500L);
    }
    BigDecimal salary = new BigDecimal(workClassHero.getSalary());
    BigDecimal tax = new BigDecimal(workClassHero.getTax());
    taxRelief = ((salary.subtract(tax)).multiply(variable)).add(genderBonus);
    return normalRoundedTaxRelief(taxRelief);
  }

  public static List<WorkClassHero> recordBatchFile(String inputFilePath) throws Exception {
    List<WorkClassHero> workClassHeroList;
    try {
      File inputF = new File(inputFilePath);
      InputStream inputFS = new FileInputStream(inputF);
      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
      // skip the header of the csv
      workClassHeroList = br.lines().skip(1).map(mapToWorkClassHero).collect(Collectors.toList());
      br.close();
    } catch (Exception e) {
      throw new GenericAutomationException("Unable to record the file");
    }
    return workClassHeroList;
  }

  private static BigDecimal normalRoundedTaxRelief(BigDecimal taxRelief) {
    BigDecimal centsPart = taxRelief.remainder(BigDecimal.ONE).stripTrailingZeros();
    if (numDp(centsPart) > 2) {
      centsPart = centsPart.setScale(2, RoundingMode.DOWN);
    }
    if (centsPart.compareTo(new BigDecimal("0.50")) == 1) {
      taxRelief = taxRelief.setScale(2, RoundingMode.CEILING);
    } else {
      taxRelief = taxRelief.setScale(0, RoundingMode.FLOOR);
    }
    return taxRelief.setScale(2);
  }

  private static int numDp(BigDecimal bigDecimal) {
    String string = bigDecimal.stripTrailingZeros().toPlainString();
    int index = string.indexOf(".");
    return (index < 0) ? 0 : (string.length() - index - 1);
  }

  private static Function<String, WorkClassHero> mapToWorkClassHero = (line) -> {
    String[] p = line.split(",");
    WorkClassHero workClassHero = new WorkClassHero();
    workClassHero.setNatid(p[0]);
    workClassHero.setName(p[1]);
    workClassHero.setGender(p[2]);
    workClassHero.setSalary(p[3]);
    workClassHero.setBirthday(p[4]);
    workClassHero.setTax(p[5]);
    return workClassHero;
  };

  public static List<TaxRelief> getTaxReliefFromWorkClassHeros(
      List<WorkClassHero> workClassHeroList) {
    Function<WorkClassHero, BigDecimal> releifAmountFun = workClassHero -> getTaxReleif(
        workClassHero);
    Function<String, String> maskedNatId = natId -> maskString(natId, natId.length() - 2,
        natId.length(), '$');
    List<String> names = workClassHeroList.stream().map(WorkClassHero::getName)
        .collect(Collectors.toList());
    List<String> natId = workClassHeroList.stream().map(WorkClassHero::getNatid).map(maskedNatId)
        .collect(
            Collectors.toList());
    List<BigDecimal> relief = workClassHeroList.stream().map(releifAmountFun)
        .collect(Collectors.toList());
    List<TaxRelief> taxReliefList = IntStream.range(0, Math.max(names.size(), natId.size()))
        .mapToObj(i -> new TaxRelief((i < names.size() ? names.get(i) : null),
            (i < natId.size() ? natId.get(i) : null),
            (i < relief.size() ? relief.get(i).toString() : null)))
        .collect(Collectors.toList());
    return taxReliefList;
  }
}