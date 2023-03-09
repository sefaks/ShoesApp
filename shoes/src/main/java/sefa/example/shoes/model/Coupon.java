package sefa.example.shoes.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="coupons")
public class Coupon {

    @Id
    @SequenceGenerator(name = "id", allocationSize = 1)
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="coupon_id")
    private Long couponId;

    @Column(name="coupon_amount")
    private Long couponAmount;

    @OneToOne
    private User user;


}
