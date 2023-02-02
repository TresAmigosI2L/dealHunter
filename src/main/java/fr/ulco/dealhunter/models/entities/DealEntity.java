package fr.ulco.dealhunter.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="deals")
public class DealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UserEntity author;

    private String title;
    private String description;
    private Double originalPrice;
    private Double dealPrice;


}
