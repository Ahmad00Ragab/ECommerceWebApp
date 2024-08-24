package gov.iti.jets.category;

import gov.iti.jets.product.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categ_id")
    private Long id;

    @Column(name = "categ_name", nullable = false)
    private String name;

    @Column(name = "categ_descr")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}