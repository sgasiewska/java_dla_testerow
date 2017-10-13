package jdt.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mantis_user_table")
public class UsersData {
  @Column(name = "username")
  private String username;

  @Id
  @Column(name = "id")
  private int id;


  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public UsersData withId(int id) {
    this.id = id;
    return this;
  }

  public UsersData withUsername(String username) {
    this.username = username;
    return this;
  }


  @Override
  public String toString() {
    return "UserData{" +
            "username='" + username + '\'' +
            ", id=" + id + '\'' +
            '}';
  }
}
