package ua.com.admin.console.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String about;
    private AddressDto address;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    private UserDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        password = builder.password;
        about = builder.about;
        dateOfBirth = builder.dateOfBirth;
        address = builder.address;
    }

    protected UserDto() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public AddressDto getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        return Objects.equals(id, userDto.id) &&
                name.equals(userDto.name) &&
                surname.equals(userDto.surname) &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                Objects.equals(about, userDto.about) &&
                dateOfBirth.equals(userDto.dateOfBirth) &&
                address.equals(userDto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password, about, dateOfBirth, address);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private String surname;
        private String login;
        private String password;
        private String about;
        private LocalDate dateOfBirth;
        private AddressDto address;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withSurname(String val) {
            surname = val;
            return this;
        }

        public Builder withLogin(String val) {
            login = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withAbout(String val) {
            about = val;
            return this;
        }

        public Builder withDateOfBirth(LocalDate val) {
            dateOfBirth = val;
            return this;
        }

        public Builder withAddress(AddressDto val) {
            address = val;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
