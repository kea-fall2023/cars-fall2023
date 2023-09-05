package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
  int id;
  String memberUsername;
  int carId;
  String carBrand;
  String carModel;
  double pricePrDay;

  @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
  private LocalDate rentalDate;

  public ReservationResponse(Reservation r) {
    this.id= r.getId();
    this.memberUsername = r.getMember().getUsername();
    this.carId = r.getCarToRent().getId();
    this.carBrand = r.getCarToRent().getBrand();
    this.carModel = r.getCarToRent().getModel();
    this.rentalDate = r.getRentalDate();
    this.pricePrDay = r.getCarToRent().getPricePrDay();
  }
}