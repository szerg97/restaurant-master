package com.restaurant.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "foods")
public class Food implements Serializable {

    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_seq"
    )
    @SequenceGenerator(
            name = "food_seq",
            sequenceName = "food_seq"
    )
    private Long id;
    private String name;
    private LocalDateTime timestamp;

    public Food(String name, LocalDateTime timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
