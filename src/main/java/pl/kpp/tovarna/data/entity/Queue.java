package pl.kpp.tovarna.data.entity;

import jakarta.persistence.*;
import pl.kpp.tovarna.data.classes.BuildState;

@Entity
@Table
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;

    @ManyToOne
    @JoinColumn(name = "BuiltProductId")
    Product built;

    @Column(name = "BuildState")
    @Convert(converter = BuildState.BuildStateConverter.class)
    BuildState state;

    public Queue() {

    }

    public Queue(BuildState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getBuilt() {
        return built;
    }

    public void setBuilt(Product built) {
        this.built = built;
    }

    public BuildState getState() {
        return state;
    }

    public void setState(BuildState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("[Queue %s. Product to build: %s]", state.name(), built.getName());
    }
}
