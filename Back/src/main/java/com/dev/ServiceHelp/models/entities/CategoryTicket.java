package com.dev.ServiceHelp.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "categoryTicket")
public class CategoryTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // NEW
    @ManyToOne(fetch = FetchType.EAGER)
    private SolvingArea solvingArea;

}
