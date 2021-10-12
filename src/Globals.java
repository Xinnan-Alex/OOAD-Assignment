//GLOBALS CLASS FOR INITIALISING GLOBAL VARIABLE FOR EASIER ACCESS OF SOME METHODS

//JAVA IMPORTS
import java.util.concurrent.atomic.AtomicLong;

//Global class
public final class Globals {
    public static Long currentPropID = (long) 900000000;
    public static Model LogicModel = new Model();
    public static String[] propertyType = {"Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment","Unspecified"};
    public static String[] personType = {"admin","tenant","property owner"};

    public static Long generatePropertyID(){
        for (Property propertyList : Model.propertyList ){
            setCurrentID(propertyList.getPropertyID());
        }
        AtomicLong idGen = new AtomicLong(currentPropID);
        Long generatedId = idGen.incrementAndGet();
        setCurrentID(generatedId);
        return generatedId;
        
    }

    public static void setCurrentID(Long currntid){
        currentPropID = currntid;
    }


}
