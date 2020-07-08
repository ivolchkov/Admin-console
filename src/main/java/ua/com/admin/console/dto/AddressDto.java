package ua.com.admin.console.dto;

import java.util.Objects;

public class AddressDto {
    private final String city;
    private final String street;
    private final Integer streetNumber;

    public AddressDto(String city, String street, Integer streetNumber) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDto that = (AddressDto) o;

        return city.equals(that.city) &&
                street.equals(that.street) &&
                streetNumber.equals(that.streetNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, streetNumber);
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                '}';
    }
}
