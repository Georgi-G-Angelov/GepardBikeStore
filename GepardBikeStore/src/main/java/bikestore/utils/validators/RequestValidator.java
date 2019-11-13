package bikestore.utils.validators;

import bikestore.entities.Request;
import bikestore.utils.validators.base.Validator;

public class RequestValidator implements Validator<Request> {


    @Override
    public boolean isValid(Request request) {
        if(isEmailValid(request)&&isMessageValid(request)&&isNameValid(request)){
            return true;
        }
        return false;
    }

    public boolean isNameValid(Request request){
        if(request.getName().length()>=1&&request.getName().length()<=40){
            return true;
        }
        return false;
    }

    public boolean isEmailValid(Request request){
        if(request.getEmail().length()>=6&&request.getEmail().length()<=40&&request.getEmail().contains("@")){
            for(int i = request.getEmail().length();i>=0;i--){
                char x = request.getEmail().charAt(i-1);
                if(x=='.'){
                    return true;
                }
                if(x=='@'){
                    return false;
                }
            }
        }
        return false;
    }

    public boolean isMessageValid(Request request){
        if(request.getMessage().length()>=20&&request.getMessage().length()<=300){
            return true;
        }
        return false;
    }
}
