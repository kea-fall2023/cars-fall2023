package dat3.car.service;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ReservationServiceTest {

  ReservationService reservationService;

  @Autowired
  ReservationRepository reservationRepository;
  @Autowired
  CarRepository carRepository;

  @Autowired
  MemberRepository memberRepository;

  int carId;
  boolean dataInitialized = false;

  @BeforeEach
  void setupData(){
    if(dataInitialized){
      return;
    }
    reservationRepository.deleteAll();
    memberRepository.deleteAll();
    carRepository.deleteAll();
    Car volvo = Car.builder().brand("Volvo").model("V70").pricePrDay(700).bestDiscount(30).build();
    volvo = carRepository.save(volvo);
    carId = volvo.getId();
    Member m1 = memberRepository.save(new Member("m1", "pw", "m1@a.dk", "fm", "ln", "vej 2", "Lynby", "2800"));
    //Reserve the Car
    reservationRepository.save(new Reservation(LocalDate.of(2023,5,5),volvo,m1));
    reservationService = new ReservationService(memberRepository,carRepository, reservationRepository);
    dataInitialized = true;
  }


  @Test
  void testReserveCar_Available() {
    System.out.println("############### testReserveCarV2_Available #################");
    reservationService.reserveCar("m1",carId,LocalDate.of(2023,11,11));
    Member member = memberRepository.findById("m1").get();
    Car car = carRepository.findById(carId).get();
    assertEquals(2,member.getReservations().size());
    assertEquals(2,car.getReservations().size());
  }
  @Test
  void testReserveCar_UnAvailableCarThrows() {
    Assertions.assertThrows(ResponseStatusException.class,()->
            reservationService.reserveCar("m1",carId,LocalDate.of(2023,5,5)));
  }

  @Test
  void testReserveCar_CannotReserveDateInPast() {
    Assertions.assertThrows(ResponseStatusException.class,()->
            reservationService.reserveCar("m1",carId,LocalDate.of(2022,5,5)));
  }

  @Test
  void testReserveCarV2_CannotReserveDateInPast() {
    Assertions.assertThrows(ResponseStatusException.class,()->
            reservationService.reserveCar("m1",carId,LocalDate.of(2022,5,5)));
  }
  @Test
  void testReserveCarV2_Available()  {
    System.out.println("############### testReserveCarV2_Available #################");
    reservationService.reserveCarV2("m1",carId,LocalDate.of(2023,11,11));
    Member member = memberRepository.findById("m1").get();
    Car car = carRepository.findById(carId).get();
    assertEquals(2,member.getReservations().size());
    assertEquals(2,car.getReservations().size());
  }
  @Test
  void testReserveCarV2_UnAvailableCarThrows() {
    Assertions.assertThrows(ResponseStatusException.class,()->
            reservationService.reserveCarV2("m1",carId,LocalDate.of(2023,5,5)));
  }

}