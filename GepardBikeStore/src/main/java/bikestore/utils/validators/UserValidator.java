package bikestore.utils.validators;

import bikestore.entities.User;
import bikestore.utils.validators.base.Validator;

public class UserValidator implements Validator<User> {
    final int MAX_PASSWORD_LENGTH = 45;
    final int MAX_USERNAME_LENGTH = 45;
    final int MIN_USERNAME_LENGTH = 6;
    final int MIN_PASSWORD_LENGTH = 8;

    @Override
    public boolean isValid(User user) {
        if(user.getUsername().length()>=MIN_USERNAME_LENGTH&&user.getPassword().length()>=MIN_PASSWORD_LENGTH&&user.getUsername().length()<=MAX_USERNAME_LENGTH&&user.getPassword().length()<=MAX_PASSWORD_LENGTH){
            return true;
        }
        else{
            return false;
        }
    }
}
