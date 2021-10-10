public class Property {

    //Property ID format : Bungalow = 1xxxxxxxx , Semi-D = 2xxxxxxxx , Terrace = 3xxxxxxxx , Townhouse = 4xxxxxxxx , 
    //                     Penthouse = 5xxxxxxxx , Condominium = 6xxxxxxxx , Duplex = 7xxxxxxxx , Apartment = 8xxxxxxxx, Unspecified = 9xxxxxxxx          

    private final long propertyID;
    public String propertyOwner;
    private long propertySize;
    public String contactNum;
    private long rentalRate;
    private String projectName;
    private String propertyType;
    private Boolean rentStatus; //(true/not active -> has tenant  false/active -> no tenant)
    private Boolean hiddenStatus; //(true -> keep hidden  false -> show on viewboard)

    //IMPLEMENT BUILDER DP FOR THE CONSTRUCTOR - use propertyBuilder class - different 
    //ID based on property type

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
    }

    public void setRentStatus() {
        if (this.rentStatus == false) {
            this.rentStatus = true;
        }
        else {
            this.rentStatus = false;
        }
    }

    public void setHiddenStatus() {
        if (this.hiddenStatus == false) {
            this.hiddenStatus = true;
        }
        else {
            this.hiddenStatus = false;
        }
    }


    public long getPropertyID() {
        return this.propertyID;
    }

    public long getPropertySize() {
        return this.propertySize;
    }

    public long getRentalRate() {
        return this.rentalRate;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public Boolean getRentStatus() {
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

    public String toString() {
        return ("projectName: " + projectName + ",propertySize: " + propertySize + ",rentalRate: " + rentalRate + ",propertyType: " + propertyType + ",propertyOwner: " + propertyOwner + ",contactNum: " +  contactNum + ",propertyID: " + propertyID + ",hiddenStatus: " + hiddenStatus + ",rentStatus: " + rentStatus);
    }

    //7 Jalan Durian,2000,300,Apartment,Leong Xin Nan, 0102529375, 10000, false, active
    public String toCSVFormat(){
        return (projectName + "," + propertySize + "," + rentalRate + "," + propertyType + "," + propertyOwner + "," +  contactNum + "," + propertyID + "," + hiddenStatus + "," + rentStatus);
    }

    public static class propertyBuilder{

        public Long propertyID;
        public String propertyOwner = "";
        public long propertySize = 0;
        public String contactNum = "";
        public long rentalRate = 0;
        public String projectName = "";
        public String propertyType = "";
        public Boolean rentStatus = false; //(true -> has tenant  false -> no tenant/default)
        public Boolean hiddenStatus = true; //(true -> keep hidden/default  false -> show on viewboard)
    
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

        public propertyBuilder rentStatus(Boolean uRentStatus) {
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
