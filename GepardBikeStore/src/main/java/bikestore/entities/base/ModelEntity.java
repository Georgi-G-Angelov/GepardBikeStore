package bikestore.entities.base;

import java.io.Serializable;

public interface ModelEntity extends Serializable {
    int getId();

    void setId(int id);
}
