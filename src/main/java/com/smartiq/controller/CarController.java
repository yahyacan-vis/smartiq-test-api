package com.smartiq.controller;

import com.smartiq.dto.BaseReturn;
import com.smartiq.enums.MessageTypes;
import com.smartiq.exception.ApiHeaderKeyNotFoundException;
import com.smartiq.exception.NotFoundException;
import com.smartiq.exception.WrongUsernamePasswordException;
import com.smartiq.model.Car;
import com.smartiq.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<BaseReturn<Car>> save(@RequestHeader(value = "api-key") String apiKey, @RequestHeader(value = "api-secret") String apiSecret, @RequestBody Car car) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        car = carService.save(apiKey, apiSecret, car);
        BaseReturn resp = BaseReturn.builder()
                .code(MessageTypes.SUCCESS.getCode())
                .message(MessageTypes.SUCCESS.getMessage())
                .data(car)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BaseReturn<Car>> update(@RequestHeader(value = "api-key") String apiKey, @RequestHeader(value = "api-secret") String apiSecret, @RequestBody Car car, @PathVariable Long id) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException, NotFoundException {
        car = carService.update(apiKey, apiSecret, id, car);
        BaseReturn resp = BaseReturn.builder()
                .code(MessageTypes.SUCCESS.getCode())
                .message(MessageTypes.SUCCESS.getMessage())
                .data(car)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseReturn<List<Car>>> find(@RequestHeader(value = "api-key") String apiKey, @RequestHeader(value = "api-secret") String apiSecret) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        List<Car> cars = carService.find(apiKey, apiSecret);
        BaseReturn resp = BaseReturn.builder()
                .code(MessageTypes.SUCCESS.getCode())
                .message(MessageTypes.SUCCESS.getMessage())
                .data(cars)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BaseReturn<Car>> find(@RequestHeader(value = "api-key") String apiKey, @RequestHeader(value = "api-secret") String apiSecret, @PathVariable Long id) throws ApiHeaderKeyNotFoundException, NotFoundException, WrongUsernamePasswordException {
        Car car = carService.find(apiKey, apiSecret, id);
        BaseReturn resp = BaseReturn.builder()
                .code(MessageTypes.SUCCESS.getCode())
                .message(MessageTypes.SUCCESS.getMessage())
                .data(car)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BaseReturn> delete(@RequestHeader(value = "api-key") String apiKey, @RequestHeader(value = "api-secret") String apiSecret, @PathVariable Long id) throws ApiHeaderKeyNotFoundException, NotFoundException, WrongUsernamePasswordException {
        carService.delete(apiKey, apiSecret, id);
        BaseReturn resp = BaseReturn.builder()
                .code(MessageTypes.SUCCESS.getCode())
                .message(MessageTypes.SUCCESS.getMessage())
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



}
