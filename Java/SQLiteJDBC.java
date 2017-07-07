import java.sql.*;

class SQLiteJDBC extends Runnable{

Thread t;
String threadname

publiv SQLiteJDBC(String name)
{
   this.threadname = name;
}

public void run() {
   Connection c = null;
   Statement stmt = null;
   try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
}

public void start()
{
   if(t==null)
   {
      t.start(this,threadname);
   }
}  

}

public class test
{
   SQLiteJDBC t1 = new SQLiteJDBC("1");
   SQLiteJDBC t2 = new SQLiteJDBC("2");

   t1.start();
   t2.start();
}