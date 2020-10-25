package pl.akademiaspring.carsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.akademiaspring.carsapi.model.Car;
import pl.akademiaspring.carsapi.repository.CarRepo;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CarService {

    private List<Car> carList;
    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car("Fiat","Punto","silver"));
        carList.add(new Car("Volksvagen","Golf","black"));
        carList.add(new Car("Ford","Focus","silver"));
        carList.add(new Car("Fiat","Punto","silver"));
        carList.add(new Car("Volksvagen","Golf","black"));
        carList.add(new Car("Ford","Focus","silver"));
        carList.add(new Car("Fiat","Punto","silver"));
        carList.add(new Car("Volksvagen","Golf","black"));
        carList.add(new Car("Ford","Focus","silver"));
        carList.add(new Car("Fiat","Punto","silver"));
        carList.add(new Car("Volksvagen","Golf","black"));
        carList.add(new Car("Ford","Focus","silver"));
        carList.add(new Car("Fiat","Punto","silver"));
        carList.add(new Car("Volksvagen","Golf","black"));
        carList.add(new Car("Ford","Focus","silver"));
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Optional<Car> getCarById(long id) {
        return carRepo.findById(id);
    }

    public List<Car> getCarByColor(String color) {
//        List<Car> cars = carList.stream().filter(car -> color.equalsIgnoreCase(car.getColor())).collect(Collectors.toList());
        List<Car> cars = carRepo.findCarsByColor(color);
        return cars;
    }

    public Car addCar(Car car) {
        System.out.print("Car Added: ");
        System.out.println(car.toString());
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
