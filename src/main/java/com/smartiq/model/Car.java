package com.smartiq.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Car {

    private long id;

    private String title;

    private int year;

    private String brand;

    private String model;

    private String bodyStyle;

    private String fuelType;

    private String transmissionType;

    private String version;

}
