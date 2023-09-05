package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
  String username;
  int carId;
  @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
  LocalDate date;
}
