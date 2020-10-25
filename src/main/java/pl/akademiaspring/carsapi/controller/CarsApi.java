package pl.akademiaspring.carsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspring.carsapi.model.Car;
import pl.akademiaspring.carsapi.service.CarService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/cars")
@CrossOrigin
public class CarsApi {

    private CarService carService;

    @Autowired
    public CarsApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<Car> getCars() {
        return new ResponseEntity(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable long id) {
        Optional<Car> car = carService.getCarById(id);
        if(!car.equals(Optional.empty())) {
            return new ResponseEntity(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Car> getCarByColor(@PathVariable String color) {
        List<Car> cars = carService.getCarByColor(color);
        if (cars.size() > 0) {
            return new ResponseEntity(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.addCar(car),HttpStatus.OK) ;
    }

//    @PutMapping
//    public ResponseEntity<Boolean> editCar(@RequestBody Car newCar) {
//        if(carService.modCar(newCar)) {
//            carService.modCar(newCar);
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity editCar(@PathVariable long id,@RequestBody Map<String, String> changes) {
    carService.editCar(id, changes);
    return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        if(carService.deleteCar(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
