package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
  ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping()
  public ReservationResponse makeReservation(@RequestBody ReservationRequest request) throws IOException {
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //LocalDate reservationDate = LocalDate.parse(request.getDate(),formatter);
    //return reservationService.reserveCar(request.getUsername(), request.getCarId(), reservationDate);
    System.out.println(request.getUsername());
    System.in.read();
    return reservationService.reserveCar(request.getUsername(), request.getCarId(), request.getDate());
  }


}
