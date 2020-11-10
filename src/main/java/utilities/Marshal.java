package utilities;

public class Marshal {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    // Static all the way
    private Marshal(){}

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public static boolean stringToBoolean(String value){

        // Allow user to pass string 'true'
        if(value.equalsIgnoreCase("true")){
            return true;
        }

        // ...or integer 1, the rest defaults to false
        return value.equalsIgnoreCase("1");

    }


}
