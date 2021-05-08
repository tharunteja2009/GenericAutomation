package Models;

public class TaxRelief {

  String natid;
  String name;
  String relief;

  public TaxRelief(String name, String natid, String relief) {
    this.name = name;
    this.natid = natid;
    this.relief = relief;
  }

  public String getNatid() {
    return natid;
  }

  public void setNatid(String natid) {
    this.natid = natid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRelief() {
    return relief;
  }

  public void setRelief(String relief) {
    this.relief = relief;
  }

  @Override
  public String toString() {
    return "TaxRelief{" +
        "natid='" + natid + '\'' +
        ", name='" + name + '\'' +
        ", relief='" + relief + '\'' +
        '}';
  }
}
