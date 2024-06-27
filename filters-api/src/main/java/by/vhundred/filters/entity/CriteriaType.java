package by.vhundred.filters.entity;


import by.vhundred.filters.dto.enums.CriteriaTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "criteria_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CriteriaTypes type;
}
