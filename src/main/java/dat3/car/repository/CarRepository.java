package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> getByBrand(String brand);

    List<Car> findByBrandAndModel(String brand, String model);

    @Query(value = "SELECT AVG(c.pricePrDay) from Car c")
    Double avrPricePrDay();


    @Query("SELECT c FROM Car c WHERE c.bestDiscount = (SELECT MAX(c.bestDiscount) FROM Car c)")
    List<Car> findAllByBestDiscount();

    List<Car> findByReservationsIsEmpty();

    @Query("SELECT c FROM Car c WHERE c.id = :carId AND NOT EXISTS (SELECT res FROM Reservation res WHERE res.carToRent = c AND res.rentalDate = :date)")
    Optional<Car> findCarByIdIfNotAlreadyReserved(int carId, LocalDate date);
}
