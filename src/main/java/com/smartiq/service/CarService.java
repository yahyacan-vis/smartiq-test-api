package com.smartiq.service;

import com.smartiq.enums.MessageTypes;
import com.smartiq.exception.ApiHeaderKeyNotFoundException;
import com.smartiq.exception.NotFoundException;
import com.smartiq.exception.WrongUsernamePasswordException;
import com.smartiq.model.Car;
import com.smartiq.util.SmartiqUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CarService {

    @Value("${api-key}")
    String mApiKey;

    @Value("${api-secret}")
    String mApiSecret;

    public Car save(String apikey, String apiSecret, Car car) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        checkHeader(apikey, apiSecret);
        car.setId(SmartiqUtil.nextCarId());
        car.setTitle(SmartiqUtil.createTitle(car));
        SmartiqUtil.cars.add(car);
        return car;
    }

    public Car update(String apikey, String apiSecret, long id, Car car) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException, NotFoundException {
        checkHeader(apikey, apiSecret);
        if (!SmartiqUtil.cars.isEmpty()){
            for (int i = 0; i < SmartiqUtil.cars.size(); i++) {
                Car item = SmartiqUtil.cars.get(i);
                if (item.getId() == id){
                    item = car;
                    item.setId(id);
                    item.setTitle(SmartiqUtil.createTitle(item));
                    SmartiqUtil.cars.set(i,item);
                    return item;
                }
            }
        }
        throw new NotFoundException(MessageTypes.NOT_FOUND.getMessage() + " id : " + id);
    }

    public void delete(String apikey, String apiSecret, long id) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException, NotFoundException {
        checkHeader(apikey, apiSecret);
        if (!SmartiqUtil.cars.isEmpty()){
            for (int i = 0; i < SmartiqUtil.cars.size(); i++) {
                if (SmartiqUtil.cars.get(i).getId() == id) {
                    SmartiqUtil.cars.remove(i);
                    return;
                }
            }
        }
        throw new NotFoundException(MessageTypes.NOT_FOUND.getMessage() + " id : " + id);
    }

    public List<Car> find(String apikey, String apiSecret) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        checkHeader(apikey, apiSecret);
        return SmartiqUtil.cars;
    }

    public Car find(String apikey, String apiSecret, long id) throws NotFoundException, ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        checkHeader(apikey, apiSecret);
        if (!SmartiqUtil.cars.isEmpty()){
            for (Car car : SmartiqUtil.cars){
                if (car.getId() == id)
                    return car;
            }
        }
        throw new NotFoundException(MessageTypes.NOT_FOUND.getMessage() + " id : " + id);
    }

    public boolean checkHeader(String apikey, String apiSecret) throws ApiHeaderKeyNotFoundException, WrongUsernamePasswordException {
        if (StringUtils.isEmpty(apikey) || StringUtils.isEmpty(apiSecret))
            throw new ApiHeaderKeyNotFoundException(MessageTypes.API_KEY_HEADER_NOT_FOUND.getMessage());
        if (!(mApiKey.equalsIgnoreCase(apikey) && mApiSecret.equalsIgnoreCase(apiSecret)))
            throw new WrongUsernamePasswordException(MessageTypes.WRONG_USERNAME_PASSWORD.getMessage());
        return true;
    }


}
