package bikestore.entities;

import bikestore.entities.base.ModelEntity;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "part_carts")
public class PartCart implements ModelEntity {

    int user_id;
    int part_id;
    int id;

    public PartCart() {

    }

    public PartCart(int user_id, int part_id) {
        this(-1, user_id, part_id);
    }

    public PartCart(int id, int user_id, int part_id) {
        setId(id);
        setUserId(user_id);
        setPartId(part_id);
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

    @Column(name = "part_id")
    public int getPartId(){
        return part_id;
    }

    public void setPartId(int part_id){
        this.part_id = part_id;
    }



    @Override
    public String toString() {
        return MessageFormat.format(
                "({0}, {1}, {2})",
                getId(),
                getUserId(),
                getPartId()
        );
    }

}