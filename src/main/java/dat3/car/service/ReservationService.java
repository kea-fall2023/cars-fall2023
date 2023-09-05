package dat3.car.service;

import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ReservationService {
  MemberRepository memberRepository;
  CarRepository carRepository;
  ReservationRepository reservationRepository;

  public ReservationService(MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
    this.memberRepository = memberRepository;
    this.carRepository = carRepository;
    this.reservationRepository = reservationRepository;
  }

  public ReservationResponse reserveCar(String memberId, int carId, LocalDate dateToReserve){
    if(dateToReserve.isBefore(LocalDate.now())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date must be in the future");
    }
    Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
    //Observe in the following, this strategy requires two round trips to the database
    Car car = carRepository.findById(carId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No car with this id found"));
//    if(reservationRepository.existsByCarIdAndRentalDate(car.getId(),dateToReserve)){
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car is already reserved on this date");
//    }
    Reservation reservation = new Reservation(dateToReserve,car,member);
    Reservation res = reservationRepository.save(reservation);
    return new ReservationResponse(res);
  }
  public void reserveCarV2(String memberId, int carId, LocalDate dateToReserve){
    if(dateToReserve.isBefore(LocalDate.now())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date must be in the future");
    }
    Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
    //Observe in the following, this strategy requires only ONE round trip to the database
    Car car = carRepository.findCarByIdIfNotAlreadyReserved(carId, dateToReserve).orElseThrow(()->
            new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car is already reserved on this date"));
    Reservation reservation = new Reservation(dateToReserve,car,member);
    reservationRepository.save(reservation);
  }

}
