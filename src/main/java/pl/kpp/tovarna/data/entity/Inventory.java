package pl.kpp.tovarna.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    Product object;

    @Column(name = "InsertDate", nullable = false)
    LocalDateTime insertDate;

    public Inventory() {
        setInsertDate(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getObject() {
        return object;
    }

    public void setObject(Product object) {
        this.object = object;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }
}
