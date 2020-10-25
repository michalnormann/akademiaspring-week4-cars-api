package pl.akademiaspring.carsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspring.carsapi.model.Car;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
     List<Car> findCarsByColor(String color);
}
