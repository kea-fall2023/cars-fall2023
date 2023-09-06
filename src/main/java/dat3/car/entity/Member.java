package dat3.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
// ----Lombok anotations above --------- //
@Entity
public class Member extends AdminDetails  {

  @Id
  private String username;
  @Column(nullable = false)
  private String email;
  @Column(name="bruger_password",nullable = false)
  private String password;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String zip;
  private boolean approved;
  private int ranking;

  @OneToMany(mappedBy = "member")
  List<Reservation> reservations;//; = new ArrayList<>();

  public void addReservation(Reservation reservation){
    if (reservations == null){
      reservations = new ArrayList<>();
    }
    reservations.add(reservation);
  }


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