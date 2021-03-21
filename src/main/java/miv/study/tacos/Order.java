package miv.study.tacos;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taco_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // id isn't the primary key in the taco_order table

    @Column(name = "placedat")
    private Date placedAt;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "street")
    @NotBlank(message = "Street is required")
    private String street;

    @Column(name = "city")
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "state")
    @Size(min = 1, max = 2, message = "State is required. Min = 1, Max = 2 symbols")
    private String state;

    @Column(name = "zip")
    @NotBlank(message = "Zip code is required")
    private String zip;

    @Column(name = "ccnumber")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column(name = "ccexpiration")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name = "cccvv")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToMany
    @JoinTable(name = "taco_order_tacos",
            joinColumns = @JoinColumn(name = "tacoorder"),
            inverseJoinColumns = @JoinColumn(name = "taco"))
    private List<Taco> tacos = new ArrayList<>();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    private void placedAt() {
        this.placedAt = new Date();
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                '}';
    }
}
