//Model class for mostly doing validation and returning the specific data requested by the interfaces
package model;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.UUID;
import java.util.Random;

//JAVAFX IMPORTS
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


//Model Class
public final class Model {
    public static  ObservableList<person>userInfo = FXCollections.observableArrayList();
    public static ObservableList<Property> propertyList = FXCollections.observableArrayList();

    public Model(){
        
    }
    

    //Method for loading the person's info into the program
    //Written by Leong Xin Nan
    public void loadUserInfo() throws IOException { 

        String file = "userData.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            while (line != null) {

                String[] stringInfo = line.split(",");

                person tempUser = addUserInfo(stringInfo);

                userInfo.add(tempUser);
                
                line = br.readLine();
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Creating the first admin account");
            Admin firstAdmin = new Admin("admin","iamnotadmin","Admin_1","",UUID.randomUUID(),"admin");
            writeToUserDataCSV(firstAdmin);
            System.out.println("Created the first admin account");
            
            System.out.println("Preloading the property owner accounts");
            propertyOwner preload_PropOwner1 = new propertyOwner("hajiwan488", "zdahalan8083", "Haji Wan Luthfi", "0160660488", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner2 = new propertyOwner("GinnzChan", "9aef5cd5", "Ginnz Chan", " 0129727137", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner3 = new propertyOwner("Celine", "6bc5f52d", "Celine", "0176438890", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner4 = new propertyOwner("JoanNg", "02vos0uf", "Joan Ng", "01110655009", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner5 = new propertyOwner("AppleWong", "w2z2am24", "Apple Wong", "0182889222 ", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner6 = new propertyOwner("Amyho", "weyx2r40", "Amy Ho", "0123321188", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner7 = new propertyOwner("Kith", "qbh05hq6", "Kith", "0163885371", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner8 = new propertyOwner("Jeffrey", "nxnc7ic0", "Jeffrey", "0126362428", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner9 = new propertyOwner("DavidYee", "urwcg7i4", "David Yee", "0122865787", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner10 = new propertyOwner("StevenFoong", "hmf5k9a8", "Steven Foong", "0123986141", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner11 = new propertyOwner("WillianLooi", "fzvetpmr", "Willian Looi", "0192225337", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner12 = new propertyOwner("BrandonAnn", "9aef5cd5", "Brandon Ann", "0177788251", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner13 = new propertyOwner("JudyWatkins", "6bc5f52d", "Judy Watkins", "0176438890", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner14 = new propertyOwner("AaronWaters", "02vos0uf", "Aaron Waters", "01110655009", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner15 = new propertyOwner("JeremiahFrancis", "w2z2am24", "Jeremiah Francis", "0182889222 ", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner16 = new propertyOwner("EdwardMay", "weyx2r40", "Edward May", "0123321188", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner17 = new propertyOwner("DwayneFoster", "qbh05hq6", "Dwayne Foster", "0163885371", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner18 = new propertyOwner("Pat Long", "nxnc7ic0", "Pat Long", "0126362428", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner19 = new propertyOwner("DougSparks", "urwcg7i4", "Doug Sparks", "0122865787", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner20 = new propertyOwner("GeoffreyChristensen", "hmf5k9a8", "Geoffrey Christensen", "0123986141", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner21 = new propertyOwner("CarltonLindsey", "zdahalan8083", "Carlton Lindsey", "0160660488", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner22 = new propertyOwner("FelixMoreno", "9aef5cd5", "Felix Moreno", " 0129727137", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner23 = new propertyOwner("JaimeLeonard", "6bc5f52d", "Jaime Leonard", "0176438890", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner24 = new propertyOwner("JonCohen", "02vos0uf", "Jon Cohen", "01110655009", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner25 = new propertyOwner("DianneMitchell", "w2z2am24", "Dianne Mitchell", "0182889222 ", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner26 = new propertyOwner("OtisKelly", "weyx2r40", "Otis Kelly", "0123321188", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner27 = new propertyOwner("MarshaSnyder", "qbh05hq6", "Marsha Snyder", "0163885371", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner28 = new propertyOwner("SheilaJohnston", "nxnc7ic0", "Sheila Johnston", "0126362428", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner29 = new propertyOwner("MattieJensen", "urwcg7i4", "Mattie Jensen", "0122865787", UUID.randomUUID(), "property owner");
            propertyOwner preload_PropOwner30 = new propertyOwner("GraceHouston", "hmf5k9a8", "Grace Houston", "0123986141", UUID.randomUUID(), "property owner");

            writeToUserDataCSV(preload_PropOwner1);
            writeToUserDataCSV(preload_PropOwner2);
            writeToUserDataCSV(preload_PropOwner3);
            writeToUserDataCSV(preload_PropOwner4);
            writeToUserDataCSV(preload_PropOwner5);
            writeToUserDataCSV(preload_PropOwner6);
            writeToUserDataCSV(preload_PropOwner7);
            writeToUserDataCSV(preload_PropOwner8);
            writeToUserDataCSV(preload_PropOwner9);
            writeToUserDataCSV(preload_PropOwner10);
            writeToUserDataCSV(preload_PropOwner11);
            writeToUserDataCSV(preload_PropOwner12);
            writeToUserDataCSV(preload_PropOwner13);
            writeToUserDataCSV(preload_PropOwner14);
            writeToUserDataCSV(preload_PropOwner15);
            writeToUserDataCSV(preload_PropOwner16);
            writeToUserDataCSV(preload_PropOwner17);
            writeToUserDataCSV(preload_PropOwner18);
            writeToUserDataCSV(preload_PropOwner19);
            writeToUserDataCSV(preload_PropOwner20);
            writeToUserDataCSV(preload_PropOwner21);
            writeToUserDataCSV(preload_PropOwner22);
            writeToUserDataCSV(preload_PropOwner23);
            writeToUserDataCSV(preload_PropOwner24);
            writeToUserDataCSV(preload_PropOwner25);
            writeToUserDataCSV(preload_PropOwner26);
            writeToUserDataCSV(preload_PropOwner27);
            writeToUserDataCSV(preload_PropOwner28);
            writeToUserDataCSV(preload_PropOwner29);
            writeToUserDataCSV(preload_PropOwner30);

            System.out.println("Preloaded the property owner accounts");

            System.out.println("Preloading the tenant accounts");
            Tenant preload_Tenant1 = new Tenant("chiewpei", "db9612f8", "Chiew Thok Pei", "0152221731", UUID.randomUUID(), "tenant");
            Tenant preload_Tenant2 = new Tenant("hjhmarzi", "4cf3ae40", "Marlina Robani", "0193086426", UUID.randomUUID(), "tenant");
            Tenant preload_Tenant3 = new Tenant("rameshan", "4e39290b", "Ramesh Sivanesan", "0188404994", UUID.randomUUID(), "tenant");
            Tenant preload_Tenant4 = new Tenant("kangpoh", "d01d29a4", "Kang Sui Poh", "0109426448", UUID.randomUUID(), "tenant");
            Tenant preload_Tenant5 = new Tenant("muhamif", "33f7536e", "Muhamed Zakri Yusseri", "0154945845", UUID.randomUUID(), "tenant");
            writeToUserDataCSV(preload_Tenant1);
            writeToUserDataCSV(preload_Tenant2);
            writeToUserDataCSV(preload_Tenant3);
            writeToUserDataCSV(preload_Tenant4);
            writeToUserDataCSV(preload_Tenant5);
            System.out.println("Preloaded the property owner accounts");

        }

    }

    //Method for creating a person's object
    //Written by Leong Xin Nan
    public person addUserInfo(String[] data){
        return new person(data[0],data[1],data[2],data[3],UUID.fromString(data[4]),data[5]);
    }

    //Method for validating login status
    //Written by Leong Xin Nan
    public boolean loginStatus(String loginUsername, String loginPass){
        boolean login = false;
        for (person p: userInfo){
            if (p.getUsername().equals(loginUsername) && p.getPassword().equals(loginPass)){

                login = true;

                break;
            }
            else{
                login = false;
            }
        }

        return login;
    }

    //Method for getting persons's username by passing in the person's fullname
    //Written by Leong Xin Nan
    public String getUsername(String fullname,String usertype){
        String username = "";

        for (person p: userInfo){
            if (p.getFullName().equals(fullname)){
                if(p.getUserType().equals(usertype)){
                    username = p.getUsername();
                    break;
                }
               
            }
            else{
                username = "user not found";
            }
        }

        return username;
    }   

    //Method for getting person's fullname by passing in the person's username 
    //Written by Leong Xin Nan
    public String getFullname(String username){
        String fullname = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                fullname = p.getFullName();
                break;
            }
            else{
                fullname = "user not found";
            }
        }

        return fullname;
    }

    //Method for getting person's usertype by passing in the person's username 
    //Written by Leong Xin Nan
    public String getUsertype(String username){
        String usertype = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                usertype = p.getUserType();
                break;
            }
            else{
                usertype = "user not found";
            }
        }

        return usertype;
    }

    //Method for getting person's ID by passing in the person's username
    //Written by Leong Xin Nan
    public String getUserID(String username){
        String userID = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                userID = p.getID();
                break;
            }
            else{
                userID = "user not found";
            }
        }

        return userID;
    }

    //Method for getting person's password by passing in the person's username 
    //Written by Leong Xin Nan
    public String getUserPassword (String username){
        String userPassword = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                userPassword = p.getPassword();
                break;
            }
            else{
                userPassword = "user not found";
            }
        }

        return userPassword;
    }

    //Method for getting person's contact number by passing in the person's username 
    //Written by Leong Xin Nan
    public String getUserContactNum(String username){
        String getUserContactNum = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                getUserContactNum = p.getPhoneNumber();
                break;
            }
            else{
                getUserContactNum = "user not found";
            }
        }

        return getUserContactNum;
    }

    //Method for getting person object by passing in the person's username 
    //Written by Leong Xin Nan
    public person getPersonObject(String username){

        person tempP = new person();

        for (person p: userInfo){
            if (p.getUsername().equals(username)){
                tempP = p;
                break;
            }
        }

        return tempP;
    }

    //Method for getting the list of property owner
    //Written by Leong Xin Nan
    public String[] getListofPropOwnerName(){
        int propOwnerNameListSize = 0;

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propOwnerNameListSize++;
            }
        }

        String[] propOwnerNameList = new String[propOwnerNameListSize];
        int propOwnerNameListIndex = 0;

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propOwnerNameList[propOwnerNameListIndex] = p.getFullName();
                propOwnerNameListIndex++;
            }
        }

        return propOwnerNameList;
    }

    public ObservableList<person> getListofPropertyOwner(){
        ObservableList<person> propownerList = FXCollections.observableArrayList();

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propownerList.add(p);
            }
        }

        return propownerList;
    }

    
    //Method for validating the username for account registration
    //Written by Leong Xin Nan
    public boolean registerUsernameValidation(String username){
        boolean registerusernameValid = false;

        if (username.length() == 0){
            registerusernameValid = false;
        }
        else{
            if (userInfo.size() == 0){
                registerusernameValid = true;
            }
            else{
                for (int i=0;i<userInfo.size();i++){
                    if (username.equals(userInfo.get(i).getUsername())){
                         registerusernameValid = false;
                         break;
                    }
                    else{
                         registerusernameValid = true;
                    }
                }
            }
        }
        
        return registerusernameValid;
    }

    //Method for validating the password for account registration
    //Written by Leong Xin Nan
    public boolean registerPasswordValidation(String password, String rePassword){
        boolean registerPasswordValid = false;
        
        if (password.length() == 0 || rePassword.length() == 0){
            registerPasswordValid = false;
        }
        else{
            if (password.equals(rePassword)){
                registerPasswordValid = true;
            }
            else{
                registerPasswordValid = false;
            }
        }

        return registerPasswordValid;
    }

    //Method for validating the contact number for account registration
    //Written by Leong Xin Nan
    public boolean ContactnumValidation(String contactnum){
        boolean registerContactNumValid = false;

        if (contactnum.length() == 0){
            registerContactNumValid = false;
        }
        else{
            if ((contactnum.charAt(0)) == ('0') && (contactnum.charAt(1)) == ('1')){
                if (contactnum.length() == 10 || contactnum.length() == 11){
                    registerContactNumValid = true;
                }
                else{
                    registerContactNumValid = false;
                }
            }
            else{
                registerContactNumValid = false;
            }
        }

        return registerContactNumValid;
    }

    //Method for validating the infos for account registration
    //Written by Leong Xin Nan
    public boolean registerInfoValidation(String[] registerInfo){
        
        boolean registerInfoValid = false;
        boolean registerusernameValid = registerUsernameValidation(registerInfo[0]);
        boolean registerPasswordValid = registerPasswordValidation(registerInfo[2],registerInfo[3]);
        boolean registerContactnumValid = ContactnumValidation(registerInfo[4]);

        if (registerusernameValid && registerPasswordValid && registerContactnumValid){
            registerInfoValid = true;
        }
        else{
            registerInfoValid = false;

            if (registerusernameValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Username, Please try again!");
                alert.show();
            }

            if(registerPasswordValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Password, Please try again!");
                alert.show();
            }

            if(registerContactnumValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Contact Number, Please try again!");
                alert.show();
            }
        }

        return registerInfoValid;
    }

    //Method 1 for writing person data into the userData csv file
    //Written by Leong Xin Nan
    public void writeToUserDataCSV(person p) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("userData.csv"));
        userInfo.add(p);

        for (person u : userInfo) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method 2 for writing person data into the userData csv file
    //Written by Leong Xin Nan
    public void writeToUserDataCSV() throws IOException{
        FileWriter fileWriter = new FileWriter(new File("userData.csv"));

        for (person u : userInfo) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method for loading property list into the program
    //Written by Leong Xin Nan
    public void loadPropertyList() throws IOException {
        String file = "propertyList.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            while (line != null) {

                //projectName,propertySize,rentalRate,propertyType,propertyOwner,contactNum,propertyID,numofRoom,numofBathroom,facilities,hiddenStatus,rentStatus
                String[] stringInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Property property = getPropertyObjectWithID(stringInfo);
                propertyList.add(property);
                
                line = br.readLine();
            }

            Globals.setCurrentID(propertyList.get(propertyList.size()-1).getPropertyID());
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {

            Random rand = new Random();

            //step 1
            person owner_property1 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            //step 2
            String [] preloadProperty1 = {"\"5, Jalan 4/2S, Bandar Segambut, 78083 Umbai, Melaka\"","2000","300","Terrace",owner_property1.getFullName(),owner_property1.getPhoneNumber(),"5","4","\"Aircond,Pool,Indoor Gym,Washing Machine,Cooking Utensil\"","false","active"};

            WriteToPropertyListCsv(getPropertyObject(preloadProperty1));

            person owner_property2 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty2 = {"\"163, Jalan Kiara 4 Mont Kiara, Mont Kiara, Kuala Lumpur \"","696","1800","Condominium",owner_property2.getFullName(),owner_property2.getPhoneNumber(),"1","1","\"Car Park,Game room,Jacuzzi,Clubhouse\"","false","inactive"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty2));

            person owner_property3 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty3 = {"\"Ss2/3, SS2, Petaling Jaya, Selangor\"","4500","7000","Bungalow",owner_property3.getFullName(),owner_property3.getPhoneNumber(),"6","3","\"Aircond,Pool,Near to SMK SeaPark,Near to LRT,Cooking Utensil\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty3));

            person owner_property4 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty4 = {"\"Jalan Tanjung Kupang, Gelang Patah, Iskandar Puteri, Johor\"","1600","1800","Townhouse",owner_property4.getFullName(),owner_property4.getPhoneNumber(),"3","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty4));

            person owner_property5 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty5 = {"\"Jalan Molek, Taman Molek, Johor Bahru, Johor\"","1800","1400","Terrace",owner_property5.getFullName(),owner_property5.getPhoneNumber(),"5","4","\"Car Park,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty5));

            person owner_property6 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty6 = {"\"Jalan Penaga, 59100, Bangsar, Kuala Lumpur\"","6000","9900","Semi-D",owner_property6.getFullName(),owner_property6.getPhoneNumber(),"5","4","\"Car Park,24 hours security, Pool, kitchen cabinet \"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty6));

            person owner_property7 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty7 = {"\"J-2-7, No 2 Jalan Solaris, Solaris Mont Kiara, 50480, Kuala Lumpur, Malaysia\"","4150","12800","Bungalow",owner_property7.getFullName(),owner_property7.getPhoneNumber(),"6","6","\"Big Car Park,Pool,Near DesaParkCity,Fully Furnished\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty7));

            person owner_property8 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty8 = {"\"Lebuhraya Persekutuan, Bangsar South, 59200, Kuala Lumpur\"","495","1700","Apartment",owner_property8.getFullName(),owner_property8.getPhoneNumber(),"2","1","\"Car Park, Gym, Pool, Playground\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty8));

            person owner_property9 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty9 = {"\"Jalan Dahlia, 47400, Selangor\"","1400","1800","Townhouse",owner_property9.getFullName(),owner_property9.getPhoneNumber(),"4","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty9));

            person owner_property10 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty10 = {"\"Jalan Klang Lama, Jalan Klang Lama (Old Klang Road), 58000, Kuala Lumpur\"","978","1600","Condominium",owner_property10.getFullName(),owner_property10.getPhoneNumber(),"3","2","\"Car Park, Gym, 24-hours security, Pool, Playgorund\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty10));

            person owner_property11 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty11 = {"\"18, Jalan SS23/19B, Selangor, 47301, Selangor\"","2200","2450","Terrace",owner_property11.getFullName(),owner_property11.getPhoneNumber(),"5","3","\"Car Park, Air Conditioner, Kitchen Cabinet\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty11));

            person owner_property12 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty12 = {"\"Jalan Dutamas 1, Duta Tropika, 50480, Kuala Lumpur\"","3600","9000","Terrace",owner_property12.getFullName(),owner_property12.getPhoneNumber(),"1","1","\"Car Park,Game room,Jacuzzi,Clubhouse\"","false","inactive"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty12));

            person owner_property13 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty13 = {"\"Jalan Bayu 6, Bandar Seri Alam, 81750, Johor\"","1300","1300","Terrace",owner_property13.getFullName(),owner_property13.getPhoneNumber(),"6","3","\"Aircond,Pool,Near to SMK SeaPark,Near to LRT,Cooking Utensil\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty13));

            person owner_property14 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty14 = {"\"Jalan Selengdang 1x, Taman Mewah，81000 Kulai Johor\"","1100","800","Terrace",owner_property14.getFullName(),owner_property14.getPhoneNumber(),"3","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty14));

            person owner_property15 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty15 = {"\"Jalan Gelang Patah, Taman Nusa Perintis, 81550, Johor\"","1033","1000","Apartment",owner_property15.getFullName(),owner_property15.getPhoneNumber(),"5","4","\"Car Park,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty15));

            person owner_property16 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty16 = {"\"Southlink, Bangsar South, Kampung Kerinchi (Bangsar South), Lebuhraya Persekutuan, Bangsar South, 59200, Kuala Lumpur\"","500","1499","Apartment",owner_property16.getFullName(),owner_property16.getPhoneNumber(),"5","4","\"Car Park,24 hours security, Pool, kitchen cabinet \"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty16));

            person owner_property17 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty17 = {"\"Melody Homes, Farlim, Ayer Itam, Lebuhraya Thean Teik, Farlim, 11500, Penang\"","900","950","Apartment",owner_property17.getFullName(),owner_property17.getPhoneNumber(),"6","6","\"Big Car Park,Pool,Near DesaParkCity,Fully Furnished\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty17));

            person owner_property18 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty18 = {"\"Jalan Mewah 4, Taman Pandan Mewah, 68000, Ampang, Selangor\"","828","1200","Apartment",owner_property18.getFullName(),owner_property18.getPhoneNumber(),"2","1","\"Car Park, Gym, Pool, Playground\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty18));

            person owner_property19 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty19 = {"\"Southlink, Bangsar South, Kampung Kerinchi (Bangsar South), Lebuhraya Persekutuan, Bangsar South, 59200, Kuala Lumpur\"","495","1550","Apartment",owner_property19.getFullName(),owner_property19.getPhoneNumber(),"4","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty19));

            person owner_property20 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty20 = {"\"eco sanctuary, Eco Sanctuary, Eco Santuari, Kota Kemuning, Selangor\"","3052","3500","Semi-D",owner_property20.getFullName(),owner_property20.getPhoneNumber(),"3","2","\"Car Park, Gym, 24-hours security, Pool, Playgorund\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty20));

            person owner_property21 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty21 = {"\"Jalan Dutamas Raya, MESRA TERRACE, DUTAMAS, JALAN DUTAMAS RAYA, 51200, Kuala Lumpur\"","3590","8000","Semi-D",owner_property21.getFullName(),owner_property21.getPhoneNumber(),"5","3","\"Car Park, Air Conditioner, Kitchen Cabinet\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty21));

            person owner_property22 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty22 = {"\"Mutiara Damansara Petaling Jaya, Selangor\"","6000","13000","Semi-D",owner_property22.getFullName(),owner_property22.getPhoneNumber(),"1","1","\"Car Park,Game room,Jacuzzi,Clubhouse\"","false","inactive"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty22));

            person owner_property23 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty23 = {"\"Ss2/3, SS2, Petaling Jaya, Selangor\"","4500","7000","Bungalow",owner_property23.getFullName(),owner_property23.getPhoneNumber(),"6","3","\"Aircond,Pool,Near to SMK SeaPark,Near to LRT,Cooking Utensil\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty23));

            person owner_property24 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty24 = {"\"Kota Kemuning JLN AMAN CHERIA,TROPICANA AMAN, 42500, Selangor\"","3670","3800","Semi-D",owner_property24.getFullName(),owner_property24.getPhoneNumber(),"3","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty24));

            person owner_property25 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty25 = {"\"Bukit Indah, Nusa Duta 2 storey cluster 34x80, 79150, Johor\"","2923","2600","Semi-D",owner_property25.getFullName(),owner_property25.getPhoneNumber(),"5","4","\"Car Park,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty25));

            person owner_property26 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty26 = {"\"Jalan Ceria 22, Nusa Indah near Bukit Indah, Nusa Snetral, Nusa Idaman, Taman Perling, Taman Nusa Indah , 79100, Johor\"","1878","1200","Townhouse",owner_property26.getFullName(),owner_property26.getPhoneNumber(),"5","4","\"Car Park,24 hours security, Pool, kitchen cabinet \"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty26));

            person owner_property27 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty27 = {"\"Jalan Abadi 15, Mutiara Rini home 2, 81300, Johor\"","3245","1200","Townhouse",owner_property27.getFullName(),owner_property27.getPhoneNumber(),"6","6","\"Big Car Park,Pool,Near DesaParkCity,Fully Furnished\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty27));

            person owner_property28 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty28 = {"\"Horizon Residence, Jalan Lingkungan Indah Utama, Taman Bukit Indah, 81200, Johor\"","1213","1800","Apartment",owner_property28.getFullName(),owner_property28.getPhoneNumber(),"2","1","\"Car Park, Gym, Pool, Playground\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty28));

            person owner_property29 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty29 = {"\"Jalan Permas 3, Bandar Baru Permas Jaya, 81750, Johor\"","1400","1000","Duplex",owner_property29.getFullName(),owner_property29.getPhoneNumber(),"4","2","\"Car Park,Playground,24 hours security\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty29));

            person owner_property30 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty30 = {"\"Pangsapuri Rakyat, Bandar Putra, Kulai, Jalan Cengal, Bandar Putra, 81000, Johor\"","978","1600","Duplex",owner_property30.getFullName(),owner_property30.getPhoneNumber(),"3","2","\"Car Park, Gym, 24-hours security, Pool, Playgorund\"","false","active"};
            WriteToPropertyListCsv(getPropertyObject(preloadProperty30));

            System.out.println("Preloading property list completed");
        }
    }

    public static void printPropertyList(){
        for (Property p: propertyList){
            System.out.println(p.toString());
        }
    }

    //Method for getting a property object
    //Written by Leong Xin Nan
    public Property getPropertyObject(String[] propertyinfo){

        String projectName =  propertyinfo[0].replace("\"", "");

        long propertySize;
        if (propertyinfo[1].equals("")){
            propertySize = 0;
        } 
        else{
            propertySize = Long.parseLong(propertyinfo[1]);
        }

        long rentalRate;
        if (propertyinfo[2].equals("")){
            rentalRate = 0;
        } 
        else{
            rentalRate = Long.parseLong(propertyinfo[2]);
        }

        String propertyType = propertyinfo[3];
        String propertyOwner = propertyinfo[4];
        String contactNum = propertyinfo[5];
        int numofRoom = Integer.parseInt(propertyinfo[6]);
        int numofBathroom = Integer.parseInt(propertyinfo[7]);
        String facilities = propertyinfo[8].replace("\"", "");

        Boolean hiddenStatus = Boolean.parseBoolean(propertyinfo[9]);

        String rentStatus;
        if (propertyinfo[10]==null){
            rentStatus = "active";
        } 
        else{
            rentStatus = propertyinfo[10];
        }
        
        
        Property returnProperty = new Property.propertyBuilder(Globals.generatePropertyID())
                                            .projectName(projectName)
                                            .propertyOwner(propertyOwner)
                                            .contactNum(contactNum)
                                            .propertySize(propertySize)
                                            .rentalRate(rentalRate)
                                            .propertyType(propertyType)
                                            .numofRoom(numofRoom)
                                            .numofBathroom(numofBathroom)
                                            .facilities(facilities)
                                            .rentStatus(rentStatus)
                                            .hiddenStatus(hiddenStatus)
                                            .build();

        return returnProperty;


    }

    //Method for getting the property list 
    //Written by Leong Xin Nan
    public ObservableList<Property> getPropertyList(){
        return propertyList;
    }

    //Method for getting the lsit of property list with the hiddenstatus = false
    //Written by Leong Xin Nan
    public ObservableList<Property> getVisiblePropertyList(){
        ObservableList<Property> visiblePropList = FXCollections.observableArrayList();

        for(Property property:propertyList){
            if (!property.getHiddenStatus()){
                visiblePropList.add(property);
            }
        }

        return visiblePropList;
    }

    //Method for getting a property object with ID
    //Written by Leong Xin Nan
    public Property getPropertyObjectWithID(String[] propertyinfo){
        String projectName =  propertyinfo[0].replace("\"", "");

        long propertySize;
        if (propertyinfo[1].equals("")){
            propertySize = 0;
        } 
        else{
            propertySize = Long.parseLong(propertyinfo[1]);
        }

        long rentalRate;
        if (propertyinfo[2].equals("")){
            rentalRate = 0;
        } 
        else{
            rentalRate = Long.parseLong(propertyinfo[2]);
        }

        String propertyType = propertyinfo[3];
        String propertyOwner = propertyinfo[4];
        String contactNum = propertyinfo[5];
        Long propertyID = Long.parseLong(propertyinfo[6]);
        int numofRoom = Integer.parseInt(propertyinfo[7]);
        int numofBathroom = Integer.parseInt(propertyinfo[8]);
        String facilities = propertyinfo[9].replace("\"", "");

        Boolean hiddenStatus = Boolean.parseBoolean(propertyinfo[10]);

        String rentStatus;
        if (propertyinfo[11]==null){
            rentStatus = "active";
        } 
        else{
            rentStatus = propertyinfo[11];
        }
        
        
        Property returnProperty = new Property.propertyBuilder(propertyID)
                                            .projectName(projectName)
                                            .propertyOwner(propertyOwner)
                                            .contactNum(contactNum)
                                            .propertySize(propertySize)
                                            .rentalRate(rentalRate)
                                            .propertyType(propertyType)
                                            .numofRoom(numofRoom)
                                            .numofBathroom(numofBathroom)
                                            .facilities(facilities)
                                            .rentStatus(rentStatus)
                                            .hiddenStatus(hiddenStatus)
                                            .build();

        return returnProperty;
    }

    //Method for Property ID Validation
    //Written by Leong Xin Nan
    public Boolean propertyIDValidation(String propertyID){
        Boolean validPropertyID = false;

        for (Property p: propertyList){
            if (p.getPropertyID() == Long.parseLong(propertyID)){
                validPropertyID = false;
                break;
            }
            else{
                validPropertyID = true;
            }
        }

        return validPropertyID;
    }

    

    //Method for Property Name Validation
    //Written by Leong Xin Nan
    public Boolean propertyNameValidation(String propertyName){
        Boolean validPropertyName = false;
        
        //Checking if property name is duplicated
        for (Property p: propertyList){
            System.out.println(p.getProjectName().replaceAll("\\s+","") + " compare " + propertyName.replaceAll("\\s+",""));
            if (p.getProjectName().replaceAll("\\s+","").equals(propertyName.replaceAll("\\s+",""))){
                validPropertyName = false;
                break;
            }
            else{
                validPropertyName = true;
            }
        }
        
        //Checking if the property name is blank
        if (propertyName.length() == 0){
            validPropertyName = true;
        }

        return validPropertyName;
    }

    //Method for Property Rental Rate Validation
    //Written by Leong Xin Nan
    public Boolean propertyRentalRateValidation(String propertyRentalRate){
        Boolean validRentalRate = false;

        if (Long.parseLong(propertyRentalRate) <= 0 ){
                    validRentalRate = false;
            }
        else{
            validRentalRate = true;
        }
        return validRentalRate;
    }

    //Method for Property Size Validation
    //Written by Leong Xin Nan
    public Boolean propertySizeValidation(String propertySize){
        Boolean validPropertySize = false;

        if (Long.parseLong(propertySize) < 0 ){
                validPropertySize = false;
        }
        else{
            validPropertySize = true;
        }     

        return validPropertySize;
    }

    //Method for Validating property info that will be added
    //Written by Leong Xin Nan
    public Boolean addingPropertyValidation(String[] propValidationInfo,String[] propInfoList){
        Boolean hiddenStatusValid = false;
        Boolean propownerselected = false;
        Boolean PropertyValidation = false;

        //String[] propInfoList_TobeValided = {propHiddenStatusComboBox.getValue(),propOwnerComboBox.getValue()};
        for (int i=0;i<propInfoList.length;i++){
            if(propInfoList[i].equals("")){
                if (propValidationInfo[0].equals("false") || propValidationInfo[0].isEmpty()){
                    hiddenStatusValid = false;
                    (new Alert(AlertType.ERROR,"Incomplete Property Details can't be displayed on property listing")).show();
                    break;
                }
                else{
                    hiddenStatusValid = true;
                }
            }
            else{
                hiddenStatusValid = true;
            }
        }

        if (propValidationInfo[1].equals("")){
            propownerselected = false;
            (new Alert(AlertType.ERROR,"Property Owner can't be empty, please select a property owner")).show();
        }
        else{
            propownerselected = true;
        }

        if (hiddenStatusValid && propownerselected){
            PropertyValidation = true;
        }
        else{
            PropertyValidation = false;
        }

        return PropertyValidation;
    }

    //Method 1 for writing property into the propertylist csv file (takes a property object as a parameter)
    //Written by Leong Xin Nan
    public void WriteToPropertyListCsv(Property propertytoWrite) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("PropertyList.csv"));
        propertyList.add(propertytoWrite);

        for (Property u : propertyList) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method 2 for writing property into the propertylist csv file (takes nothing as parameter)
    //Written by Leong Xin Nan
    public void WriteToPropertyListCsv() throws IOException{
        FileWriter fileWriter = new FileWriter(new File("PropertyList.csv"));

        for (Property u : propertyList) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method for validating the edited person's data before commiting the changes
    //Written by Leong Xin Nan
    public boolean editSelectedPersonDataValidation(String[] selectedPersonData_TobeValid){
        Boolean completeData = false;
        Boolean passwordMatch =  registerPasswordValidation(selectedPersonData_TobeValid[2],selectedPersonData_TobeValid[3]);
        Boolean editSelectedPersonDataValid = false;
        Boolean validContactNum = ContactnumValidation(selectedPersonData_TobeValid[4]);

        //Checking whether the edited selected person's data is empty
        for (int i=0;i<selectedPersonData_TobeValid.length;i++){
            if (selectedPersonData_TobeValid[i].isEmpty()){
                completeData = false;
                break;
            }
            else{
                completeData = true;
            }
        }

        //Final validating for edited person's data before committing the edits
        if(completeData && passwordMatch && validContactNum){
            editSelectedPersonDataValid = true;
        }
        else{
            editSelectedPersonDataValid = false;
        }

        //ERROR MESSAGES
        if(!completeData){  
            (new Alert(AlertType.ERROR,"Incomplete data, please fill in the information before committing to the changes!")).showAndWait();
        }
        if(!passwordMatch){
            (new Alert(AlertType.ERROR,"Password does not match, please try again!")).showAndWait();
        }
        if(!validContactNum){
            (new Alert(AlertType.ERROR,"Invalid phone number, please try again!")).showAndWait();
        }

        return editSelectedPersonDataValid;
    }

    //Method for validating the admin account's information before creating the admin account
    //Written by Leong Xin Nan
    public Boolean createAdminAccountValidation(String username,String password, String reenterPassowrd, String adminSecretPhrase, String fullname) throws IOException{
        Boolean validAdminAccountInfo = false;
        Boolean validAdminSecretPhrase = false;
        Boolean validUsername = false;
        Boolean validPassword = false;
        Boolean validFullname = false;

        //Validating secret phrase for creating admin account
        if (adminSecretPhrase.isEmpty()){
            validAdminSecretPhrase = false;
        }
        else{
            if (adminSecretPhrase.equals(Admin.secretPhrase)){
                validAdminSecretPhrase = true;
            }
            else{
                validAdminSecretPhrase = false;
            }
        }

        //Validating duplicate admin username
        if (username.isEmpty()){
            validUsername = false;
        }else{
            for (person u : userInfo){
                if (u.getUserType().equals("admin")){
                    if(u.getUsername().equals(username)){
                        validUsername = false;
                        break;
                    }
                    else{
                        validUsername = true;
                    }
                }
            }
        }

        //Validating duplicate admin fullname
        if (fullname.isEmpty()){
            validFullname = false;
        }
        else{
            for (person u : userInfo){
                if (u.getUserType().equals("admin")){
                    if(u.getFullName().toLowerCase().equals(fullname.toLowerCase())){
                        validFullname = false;
                        break;
                    }
                    else{
                        validFullname = true;
                    }
                }
            }
        }     

        //Validating password
        if (password.isEmpty() || reenterPassowrd.isEmpty()){
            validFullname = false;
        }
        else{
            if(password.equals(reenterPassowrd)){
                validPassword = true;
            }
            else{
                validPassword = false;
            }
        }

        //ERROR MESSAGES
        if(!validAdminSecretPhrase){
            (new Alert(AlertType.ERROR,"Invalid Admin Secret Phrase, please try again")).showAndWait();
        }
        if(!validFullname){
            (new Alert(AlertType.ERROR,"Invalid Fullname, please try again")).showAndWait();
        }
        if(!validPassword){
            (new Alert(AlertType.ERROR,"Invalid Password, please try again")).showAndWait();
        }
        if(!validUsername){
            (new Alert(AlertType.ERROR,"Invalid Username, please try again")).showAndWait();
        }

        //Final Validation for Admin Account Info
        if(validAdminSecretPhrase && validFullname && validPassword && validUsername){
            validAdminAccountInfo = true;
        }
        else{
            validAdminAccountInfo = false;
        }

        return validAdminAccountInfo;
    }

}
