package bikestore.entities;

import bikestore.entities.base.ModelEntity;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "parts")
public class Part implements ModelEntity {
    int id;
    String name;
    float price;
    String category;
    String description;
    String image;

    public Part() {

    }

    public Part(String name, float price, String category, String description) {
        this(-1, name, price, category, description);
    }

    public Part(int id, String name, float price, String category, String description) {
        setId(id);
        setName(name);
        setPrice(price);
        setCategory(category);
        setDescription(description);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 25, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "category", length = 45, nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "description", length = 250, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image", length = 100, nullable = false)
    public String getImage(){
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "({0}, {1}, {2}, {3}, {4})",
                getId(),
                getName(),
                getPrice(),
                getCategory(),
                getDescription()
        );
    }

}