package com.app.streetgoods.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Coordinates{
    private double lat;
    private double lng;

    public Coordinates(){}
}
