package Models;

public class WorkClassHero {

  private String birthday;
  private String gender;
  private String name;
  private String natId;
  private String salary;
  private String tax;

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
      this.birthday = birthday;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNatId() {
    return natId;
  }

  public void setNatId(String natId) {
    this.natId = natId;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public String getTax() {
    return tax;
  }

  public void setTax(String tax) {
    this.tax = tax;
  }

  @Override
  public String toString() {
    return "WorkClassHero{" +
        "dob='" + birthday + '\'' +
        ", gender='" + gender + '\'' +
        ", name='" + name + '\'' +
        ", natId='" + natId + '\'' +
        ", salary='" + salary + '\'' +
        ", tax='" + tax + '\'' +
        '}';
  }
}
