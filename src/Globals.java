import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public final class Globals {
    public static Long currentPropID = (long) 900000000;
    public static Model LogicModel = new Model();
    public static String[] propertyType = {"","Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment","Unspecified"};

    public static Long generatePropertyID(){
        AtomicLong idGen = new AtomicLong(currentPropID);
        Long generatedId = idGen.incrementAndGet();
        setCurrentID(generatedId);
        return generatedId;
        
    }

    public static void setCurrentID(Long currntid){
        currentPropID = currntid;
    }

    
}
