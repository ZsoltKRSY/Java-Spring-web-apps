package ro.utcluj.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "products_categories",
            joinColumns = {
                    @JoinColumn(name = "PRODUCTS_ID",
                            referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORIES_ID",
                            referencedColumnName = "ID")})
    private Category category;

    @NonNull
    private Long stock;

    @NonNull
    private Long price;
}
