package ua.com.admin.console.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 45)
    private String name;

    @Column(name = "user_surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "user_login", nullable = false, unique = true, length = 45)
    private String login;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_about", length = 1000)
    private String about;

    @Column(name = "user_date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "city", column = @Column(name = "address_city")),
            @AttributeOverride( name = "street", column = @Column(name = "address_street")),
            @AttributeOverride( name = "streetNumber", column = @Column(name = "address_street_number"))
    })
    private AddressEntity address;

    protected UserEntity() {
    }

    private UserEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        password = builder.password;
        about = builder.about;
        dateOfBirth = builder.dateOfBirth;
        address = builder.address;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public AddressEntity getAddress() {
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

        UserEntity that = (UserEntity) o;

        return Objects.equals(id, that.id) &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                login.equals(that.login) &&
                password.equals(that.password) &&
                Objects.equals(about, that.about) &&
                dateOfBirth.equals(that.dateOfBirth) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password, about, dateOfBirth, address);
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String surname;
        private String login;
        private String password;
        private String about;
        private LocalDate dateOfBirth;
        private AddressEntity address;

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

        public Builder withAddress(AddressEntity val) {
            address = val;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
