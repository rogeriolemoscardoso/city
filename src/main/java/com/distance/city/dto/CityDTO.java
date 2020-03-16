package com.distance.city.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CityDTO {
    private String cityFrom;
    private String cityTo;
    private Double distance;
    private String measures;
}
