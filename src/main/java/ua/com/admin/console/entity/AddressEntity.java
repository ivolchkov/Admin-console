package ua.com.admin.console.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AddressEntity {
    private String city;
    private String street;
    private Integer streetNumber;

    public AddressEntity(String city, String street, Integer streetNumber) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    protected AddressEntity() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(Integer streetNumber) {
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

        AddressEntity that = (AddressEntity) o;
        return city.equals(that.city) &&
                street.equals(that.street) &&
                streetNumber.equals(that.streetNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, streetNumber);
    }
}
