package dat3.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
public class Member extends AdminDetails  {

  @Id
  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private boolean approved;
  private int ranking;


  public Member(String user, String password, String email,
                String firstName, String lastName, String street, String city, String zip) {
    this.username = user;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
  }
}