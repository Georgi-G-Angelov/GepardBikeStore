package bikestore.utils.validators;

import bikestore.entities.Part;
import bikestore.utils.validators.base.Validator;

public class PartValidator implements Validator<Part> {
    private static final int MIN_NAME_LENGTH = 4;
    private static final float MIN_PRICE = 0;
    private static final int MAX_CATEGORY_LENGTH = 25;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 250;

    @Override
    public boolean isValid(Part part) {
        return part != null &&
                isNameValid(part.getName()) &&
                isPriceValid(part.getPrice()) &&
                isCategoryValid(part.getCategory()) &&
                isDescriptionValid(part.getDescription());
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
