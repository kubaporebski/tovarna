package pl.kpp.tovarna.data.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    Product produced;

    @ManyToOne
    @JoinColumn(name = "RequirementId")
    Product required;

    public Requirement() {

    }

    public Requirement(Product produced, Product required) {
        this.produced = produced;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduced() {
        return produced;
    }

    public void setProduced(Product produced) {
        this.produced = produced;
    }

    public Product getRequired() {
        return required;
    }

    public void setRequired(Product required) {
        this.required = required;
    }
}
