package bikestore.entities;

import bikestore.entities.base.ModelEntity;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "bike_carts")
public class BikeCart implements ModelEntity {

    int user_id;
    int bike_id;
    int id;

    public BikeCart() {

    }

    public BikeCart(int user_id, int bike_id) {
        this(-1, user_id, bike_id);
    }

    public BikeCart(int id, int user_id, int bike_id) {
        setId(id);
        setUserId(user_id);
        setBikeId(bike_id);
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

    @Column(name = "user_id")
    public int getUserId(){
        return user_id;
    }

    public void setUserId(int user_id){
        this.user_id = user_id;
    }

    @Column(name = "bike_id")
    public int getBikeId(){
        return bike_id;
    }

    public void setBikeId(int bike_id){
        this.bike_id = bike_id;
    }



    @Override
    public String toString() {
        return MessageFormat.format(
                "({0}, {1}, {2})",
                getId(),
                getUserId(),
                getBikeId()
        );
    }

}