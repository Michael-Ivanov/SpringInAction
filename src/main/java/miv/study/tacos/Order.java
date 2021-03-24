package miv.study.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taco_order")
public class Order implements Serializable {

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

    @ManyToOne
    private User user;

    @PrePersist
    private void placedAt() {
        this.placedAt = new Date();
    }

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
