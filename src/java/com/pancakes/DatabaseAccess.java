package com.pancakes;

import java.sql.*;


/**
 * Created by krithikabaskaran on 10/21/18.
 */
public class DatabaseAccess {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/pancake_database";

    //  Database credentials
    static final String USER = "mysqluser";
    static final String PASS = "pass123";


    public SqlReturnClass readDatabaseQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
          /*  while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("user_id");
                String age = rs.getString("user_password");
                String name = rs.getString("user_name");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + name);
            }
            //STEP 6: Clean-up environment
            rs.close();
            */

            return new SqlReturnClass(rs, conn, stmt);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    public int runDatabaseQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            int rs = stmt.executeUpdate(sql);

            //STEP 5: Extract data from result set
          /*  while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("user_id");
                String age = rs.getString("user_password");
                String name = rs.getString("user_name");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + name);
            }
            //STEP 6: Clean-up environment
            rs.close();
            */
            stmt.close();
            conn.close();
            return rs;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return 0;
    }
}
