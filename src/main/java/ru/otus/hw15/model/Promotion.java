package ru.otus.hw15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {

    public enum DataSource {
        DATABASE, ML_SERVICE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column
    private String promoName;

    @Column
    @JsonIgnore
    private String location;

    @Enumerated(EnumType.STRING)
    @Column
    @JsonIgnore
    private DataSource dataSource;

    @Transient
    private String model;
}
