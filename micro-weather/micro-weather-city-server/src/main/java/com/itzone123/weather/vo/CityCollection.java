package com.itzone123.weather.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Data
@XmlRootElement(name = "c")
public class CityCollection {

    @XmlElement(name = "d")
    private List<City> cities;

    @XmlTransient
    public List<City> getCities() {
        return cities;
    }
}
