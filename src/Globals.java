import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public final class Globals {
    public static Model LogicModel = new Model();
    public static AtomicLong idGen = new AtomicLong(900000001);
    public static String[] propertyType = {"","Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment"};
}
