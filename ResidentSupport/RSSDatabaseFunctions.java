import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Kyle James Ranaghan
 * This class contains all the necessary database functions and queries
 * for the Resident Support Service application.
 */
public class RSSDatabaseFunctions {

    private RSSDatabaseConnect database;
    
    public RSSDatabaseFunctions(){
        connectToDatabase();
   }
    
    /**
     * Establish connection to database using RSSDatabaseConnect class.
     */
    public void connectToDatabase(){
        database = new RSSDatabaseConnect();
        database.connect("");
   }
   
   /**
    * Add new client to the database and assigns ID to object.
    * @param newClient
    * @return true if insertion query was successful or false if not.
    */
   public boolean addNewClient(Client newClient){
 
        String newClientSQL = "INSERT INTO Client VALUES("+null+",'"+newClient.getForename()+"' ,'"+newClient.getSurname()+"' , '"+newClient.getDOB()+"' , '"+newClient.getAddress()+"' , '"+newClient.getPhone()+"' , "
        + "'"+newClient.getEmail()+"', '"+newClient.getJoinDate().toString()+"');" + ""; 
   
        boolean newClientSuccess = database.runSQL(newClientSQL);
        
        if(!newClientSuccess){
            System.out.print("Failed to add new client to the database. ");
            return false;
        }
        
        String getNewID = "SELECT MAX(client_id) AS 'largest_id' FROM Client;";
        ResultSet newID = database.runSQLQuery(getNewID);
        try {
            newClient.setId(newID.getInt("largest_id"));
            
        } 
        catch (SQLException ex) {
            System.out.println("Couldn't set new ID to object" + ex.getMessage());
        }
        return true;
   }
   
   /**
    * Add new case department to the database and assigns ID to object.
    * @param caseDepartment
    * @return true if insertion query was successful or false if not.
    */
   public boolean addCaseDepartment(CaseDepartment caseDepartment){
 
        String newCaseDepartmentSQL = "INSERT INTO Case_Department VALUES("+null+",'"+caseDepartment.getDepartmentName()+"');" + ""; 
   
        boolean newCaseDepartmentSuccess = database.runSQL(newCaseDepartmentSQL);
        
        if(!newCaseDepartmentSuccess){
            System.out.print("Failed to add new case department to the database. ");
            return false;
        }
        
        String getNewID = "SELECT MAX(dep_id) AS 'largest_id' FROM Case_Department;";
        ResultSet newID = database.runSQLQuery(getNewID);
        try {
            caseDepartment.setId(newID.getInt("largest_id"));
            
        } 
        catch (SQLException ex) {
            System.out.println("Couldn't set new ID to object" + ex.getMessage());
        }
        return true;
   }
   
   /**
    * Add new case to the database and assigns ID to object.
    * @param newCase
    * @return true if insertion query was successful or false if not.
    */
   public boolean addCase(Case newCase){
 
        String newCaseSQL = "INSERT INTO Client_Case VALUES("+null+",'"+newCase.getCaseDepartment().getId()+"' ,'"+newCase.getClient().getId()+"' , '"+newCase.getUser().getId()+"' , '"+newCase.getCaseOpenDate()+"' , '"+newCase.getCaseCloseDate()+"');" + ""; 
        
        boolean newCaseSuccess = database.runSQL(newCaseSQL);
        
        if(!newCaseSuccess){
            System.out.print("Failed to add new to the database. ");
            return false;
        }
        
        String getNewID = "SELECT MAX(case_id) AS 'largest_id' FROM Client_Case;";
        ResultSet newID = database.runSQLQuery(getNewID);
        try {
            newCase.setId(newID.getInt("largest_id"));
            
        } 
        catch (SQLException ex) {
            System.out.println("Couldn't set new ID to object" + ex.getMessage());
        }
        return true;
   }
  
}
