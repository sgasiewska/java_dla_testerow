package jdt.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String phome;
  private final String email;

  public ContactData(String firstname, String lastname, String address, String phome, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phome = phome;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhome() {
    return phome;
  }

  public String getEmail() {
    return email;
  }
}
