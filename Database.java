import java.sql.*;

public class Database {
    private Connection con;
    
    public Connection getConnection() {
        try {  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "491415");  
        } catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public String getMemory(String formattedDate,Connection con){
        String memory="";
        try{
            Connection connection = con;
            String query = "SELECT memory FROM personaldiary WHERE edate='" + formattedDate + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) { // Move the cursor to the first row in the result set
                memory=resultSet.getString("memory"); // Retrieve the 'memory' column value
            }
            statement.close();
            // connection.close();
        }
        catch(SQLException se){
            System.out.println(se);
        }
        return memory;
    }
    public void closeConnection(Connection con){
        try{
            con.close();
        }
        catch(SQLException s){
            System.out.println(s);
        }
    }
   
}
