package sefa.example.shoes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="shoes")
public class Shoe {
    @Id
    @SequenceGenerator(name = "seq_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_id", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="stock")
    private int stock;

    @Column(name="colour")
    private String colour;
    
    @Column(name = "type")
   private String type;

    @Column(name ="price")
    private int price;

    @Column(name="size")
    private int size;

    @Column(name="model")
    private String model;

    @Column(name ="discount_rate")
    private int discountRate;

    @Column(name="amount_sold")
    private int amountOfSold;
}
