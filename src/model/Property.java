package model;
import main.*;

//PROPERTY CLASS FOR PROPERTY IN OUR PROGRAM

//Property class
public class Property {

    //Property ID format : Bungalow = 1xxxxxxxx , Semi-D = 2xxxxxxxx , Terrace = 3xxxxxxxx , Townhouse = 4xxxxxxxx , 
    //                     Penthouse = 5xxxxxxxx , Condominium = 6xxxxxxxx , Duplex = 7xxxxxxxx , Apartment = 8xxxxxxxx, Unspecified = 9xxxxxxxx          

        private Long propertyID;
        private String propertyOwner;
        private Long propertySize;
        private String contactNum;
        private Long rentalRate;
        private String projectName;
        private String propertyType;
        private int numofRoom;
        private int numofBathroom;
        private String facilities;
        private String rentStatus;
        private Boolean hiddenStatus;

    //IMPLEMENT BUILDER DP FOR THE CONSTRUCTOR - use propertyBuilder class - different 
    //ID based on property type

    //Property Constructor
    public Property(propertyBuilder builder) {
        this.propertyID = builder.propertyID;
        this.projectName = builder.projectName;
        this.propertySize = builder.propertySize;
        this.propertyType = builder.propertyType;
        this.rentalRate = builder.rentalRate;
        this.rentStatus = builder.rentStatus;
        this.hiddenStatus = builder.hiddenStatus;
        this.propertyOwner = builder.propertyOwner;
        this.contactNum = builder.contactNum;
        this.numofBathroom = builder.numofBathroom;
        this.numofRoom = builder.numofRoom;
        this.facilities = builder.facilities;
    }

    //Property class Setters
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setPropertySize(Long propertySize){
        this.propertySize = propertySize;
    }

    public void setRentalRate(Long rentalRate){
        this.rentalRate = rentalRate;
    }
    
    public void setPropertyType(String propertyType){
        this.propertyType = propertyType;
        String firstDigit = Long.toString(this.propertyID).substring(0, 1);

        for(int i = 0;i<Globals.propertyType.length;i++){
            if (propertyType.equals(Globals.propertyType[i])){
                this.propertyID = Long.parseLong(Long.toString(this.propertyID).replaceFirst(firstDigit,Integer.toString(i+1)));
            }
        }
    
    }

    public void setPropertyOwner(String propertyOwner){
        this.propertyOwner = propertyOwner;
    }

    public void setContactNum(String contactNum){
        this.contactNum = contactNum;
    }

    public void setFacilities(String facilities){
        this.facilities = facilities;
    }

    public void setNumofRoom(int numofRoom){
        this.numofRoom = numofRoom;
    }

    public void setNumofBathroom(int numofBathroom){
        this.numofBathroom = numofBathroom;
    }

    public void setRentStatus(String rentStatus){
        this.rentStatus = rentStatus;
    }
    
    public void setHiddenStatus(Boolean hiddenStatus){
        this.hiddenStatus = hiddenStatus;
    }

    //Property Getters
    public Long getPropertyID() {
        return this.propertyID;
    }

    public Long getPropertySize() {
        return this.propertySize;
    }

    public Long getRentalRate() {
        return this.rentalRate;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public String getRentStatus() {
        return this.rentStatus;
    }

    public Boolean getHiddenStatus() {
        return this.hiddenStatus;
    }

    public String getPropertyOwner(){
        return propertyOwner;
    }

    public String getContactNum(){
        return contactNum;

    }

    public String getFacilities(){
        return facilities;
    }

    public int getNumofRoom(){
        return numofRoom;
    }

    public int getNumofBathroom(){
        return numofBathroom;
    }

    //ToString Format for easy printing
    public String toString() {
        return ("projectName: " + projectName + ",propertySize: " + propertySize + ",rentalRate: " + rentalRate + ",propertyType: " + propertyType + ",propertyOwner: " + propertyOwner + ",contactNum: " +  contactNum + ",propertyID: " + propertyID + ",numofRoom: " + numofRoom + ",numofBathroom: " + numofBathroom + ",Facilities: " + facilities + ",hiddenStatus: " + hiddenStatus + ",rentStatus: " + rentStatus);
    }

    //ToCSV Format for easy writing into CSV file
    public String toCSVFormat(){
        return ("\"" + projectName + "\"" + "," + propertySize + "," + rentalRate + "," + propertyType + "," + propertyOwner + "," +  contactNum + "," + propertyID + "," + numofRoom + "," + numofBathroom + "," + "\"" + facilities + "\"" + "," + hiddenStatus + "," + rentStatus);
    }

    //Property Builder Class
    public static class propertyBuilder{

        private Long propertyID;
        private String propertyOwner = "";
        private long propertySize = 0;
        private String contactNum = "";
        private long rentalRate = 0;
        private String projectName = "";
        private String propertyType = "";
        private int numofRoom = 1; //default number of room is 1
        private int numofBathroom = 1; //default number of bathroom is 1
        private String facilities =  "";
        private String rentStatus = "active"; //(active/default -> has no tenant  inactive -> has tenant)
        private Boolean hiddenStatus = true; //(true -> keep hidden/default  false -> show on viewboard)
    
        //CONSTRUCTIR FOR MANDATORY PARAMETER
        public propertyBuilder (long uPropertyID) {
            this.propertyID = uPropertyID; 
        }

        //CONSTRUCTOR FUNCTIONS FOR OPTIONAL PARAMETERS
        public propertyBuilder propertySize(long uPropertySize) {
            this.propertySize = uPropertySize;
            return this;
        }

        public propertyBuilder rentalRate(long uRentalRate) {
            this.rentalRate = uRentalRate;
            return this;
        }
        
        public propertyBuilder projectName(String uProjectName) {
            this.projectName = uProjectName;
            return this;
        }

        public propertyBuilder propertyType(String uPropertyType) {
            this.propertyType = uPropertyType;
            String firstDigit = Long.toString(this.propertyID).substring(0, 1);

            for(int i = 0;i<Globals.propertyType.length;i++){
                if (uPropertyType.equals(Globals.propertyType[i])){
                    this.propertyID = Long.parseLong(Long.toString(propertyID).replaceFirst(firstDigit,Integer.toString(i+1)));
                }
            }
            
            return this;
        }

        public propertyBuilder rentStatus(String uRentStatus) {
            this.rentStatus = uRentStatus;
            return this;            
        }

        public propertyBuilder hiddenStatus(Boolean uHiddenStatus) {
            this.hiddenStatus = uHiddenStatus;
            return this;
        }

        public propertyBuilder propertyOwner(String uPropertyOwner){
            this.propertyOwner = uPropertyOwner;
            return this;
        }

        public propertyBuilder contactNum(String ucontactNum){
            this.contactNum = ucontactNum;
            return this;
        }

        public propertyBuilder facilities(String facilities){
            this.facilities = facilities;
            return this;
        }

        public propertyBuilder numofRoom(int numofRoom){
            this.numofRoom = numofRoom;
            return this;
        }
    
        public propertyBuilder numofBathroom(int numofBathroom){
            this.numofBathroom = numofBathroom;
            return this;
        }

        //RETURN CONSTRUCTED OBJECT
        public Property build() {
            Property property =  new Property(this);
            validateUserObject(property);
            return property;
        }

        private void validateUserObject(Property property) {
            //Do some basic validations to check 
            //if user object does not break any assumption of system
        }
    }
}
