package bikestore.utils.validators;

import bikestore.entities.Bike;
import bikestore.utils.validators.base.Validator;

public class BikeValidator implements Validator<Bike> {
    private static final int MIN_NAME_LENGTH = 4;
    private static final float MIN_PRICE = 0;
    private static final int MAX_CATEGORY_LENGTH = 25;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 250;

    @Override
    public boolean isValid(Bike bike) {
        return bike != null &&
            isNameValid(bike.getName()) &&
            isPriceValid(bike.getPrice()) &&
            isCategoryValid(bike.getCategory()) &&
            isDescriptionValid(bike.getDescription());
    }


    private boolean isPriceValid(float price) {
        return price > MIN_PRICE;
    }

    private boolean isNameValid(String name) {
        return name != null &&
            name.length() >= MIN_NAME_LENGTH &&
            name.length() <= MAX_NAME_LENGTH;
    }

    private boolean isCategoryValid(String category) {
        return category != null &&
            category.length() <= MAX_CATEGORY_LENGTH;
    }

    private boolean isDescriptionValid(String description) {
        return description != null &&
            description.length() <= MAX_DESCRIPTION_LENGTH;
    }
}
