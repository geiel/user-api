package com.geielpeguero.userapi.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PhoneDto {

    @Pattern(regexp = "^\\d*$", message = "tiene que ser un numero")
    @NotBlank(message = "no puede estar vacio")
    private String number;

    @NotBlank(message = "no puede estar vacio")
    private String cityCode;

    @NotBlank(message = "no puede estar vacio")
    private String countryCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
