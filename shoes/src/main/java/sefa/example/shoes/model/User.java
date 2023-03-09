package sefa.example.shoes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {


    @Id
    @SequenceGenerator(name = "s_id", allocationSize = 1)
    @GeneratedValue(generator = "s_id", strategy = GenerationType.SEQUENCE)
    private int id;


    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name="balance")
    private int balance;

    @Column(name="purchase")
    private Long purchaseAmount;

    @Column(name="buy")
    private boolean buy;


    @Column(name="use_coupon")
    private boolean useCoupon;
    @OneToOne
    private Coupon selectedCoupon;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Coupon> coupons;










}
