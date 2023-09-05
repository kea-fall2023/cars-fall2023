package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Reservation extends AdminDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  LocalDate rentalDate;

  @ManyToOne
  Car carToRent;

  @ManyToOne
  Member member;

  public Reservation(LocalDate rentalDate, Car carToRent, Member member) {
    this.rentalDate = rentalDate;
    this.carToRent = carToRent;
    this.member = member;
    carToRent.addReservation(this);
    member.addReservation(this);
  }
}
