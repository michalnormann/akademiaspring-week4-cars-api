package pl.akademiaspring.carsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiaspring.carsapi.model.Car;
import pl.akademiaspring.carsapi.repository.CarRepo;

import java.util.*;


@Service
public class CarService {

    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public CarService() {
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Optional<Car> getCarById(long id) {
        return carRepo.findById(id);
    }

    public List<Car> getCarByColor(String color) {
        List<Car> cars = carRepo.findCarsByColor(color);
        return cars;
    }

    public Car addCar(Car car) {
        return carRepo.save(car);
    }

    public void editCar(long id, Map<String, String> changes) {
        Car editedCar = carRepo.findById(id).get();
        changes.forEach((change, value) -> {
            switch (change) {
                case "mark":
                    editedCar.setMark(value);
                    break;
                case "model":
                    editedCar.setModel(value);
                    break;
                case "color":
                    editedCar.setColor(value);
                    break;
            }
            carRepo.save(editedCar);
        });
    }

        public boolean deleteCar(long id) {
            Car findCar = carRepo.findById(id).get();
            if (findCar != null) {
                carRepo.delete(findCar);
                return true;
            }
            return false;
        };
    }
