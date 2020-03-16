package com.distance.city.jdbc.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class City {
    private Integer id;
    private String name;
    private double latitude;
    private double longitude;
}
