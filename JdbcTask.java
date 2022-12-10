package com.jspider.jdbc.main;

 

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

 

public class JdbcTask {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultset;
    private static String filepath="C:\\Users\\Rushikesh\\OneDrive\\Desktop\\WEJM4\\jdbc\\resources\\db_info2.properties";
    private static FileReader filereader;
    private static Properties properties;
    private static int result;
//    public static String driverpath="com.mysql.cj.jdbc.Driver";

    public void connection1()
    {
        try {
            filereader=new FileReader(filepath);
            properties=new Properties();
            properties.load(filereader);

            //Loading driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //establishing the connection
            connection=DriverManager.getConnection(properties.getProperty("dburl"),properties);

            statement=connection.createStatement();
            System.out.println("conection succesfull");

        } catch (IOException | ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
        finally {
            try {
                if(connection!=null)
                {
                    connection.close();
                }
                if(statement!=null)
                {
                    statement.close();
                }
            }
            catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
    public void createTable() throws SQLException
    {
        result=statement.executeUpdate(properties.getProperty("createTableQuery"));
        System.out.println(result+ " table is affected");
    }
    public void inserData() throws SQLException
    {
        result=statement.executeUpdate(properties.getProperty("inserQuery"));
        System.out.println(result + "Row is affetced");

    }
    public void updateData() throws SQLException
    {
        result=statement.executeUpdate(properties.getProperty("updateQuery"));
        System.out.println(result + "Row is affected");
    }
    public void deleteData() throws SQLException
    {
        result=statement.executeUpdate(properties.getProperty("deleteQuery"));
        System.out.println(result + "Row is affected");
    }
    public void displayData() throws SQLException
    {
        ResultSet result1=statement.executeQuery(properties.getProperty("displayQuery"));
        while(result1.next())
        {
            System.out.println(result1.getString(1) + "|" + result1.getString(2) + "|" + result1.getString(3) + "|" + result1.getString(4));
        }
    }
    public static void main(String args[])
    {
        JdbcTask obj=new JdbcTask();
        obj.connection1();
        int ch;
        char str;
        while(true)
        {
            System.out.println("Main Menu");
            System.out.println("1.Create table");
            System.out.println("2.Insert Data");
            System.out.println("3.update data");
            System.out.println("4.delete data");
            System.out.println("5.display data");
            Scanner sc=new Scanner(System.in);
            System.out.println("enter your choice");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1: {
                    try {
                        obj.createTable();
                        System.out.println("========================================");
                    } catch (SQLException e) {
                        System.out.println("Table is already present.please give another name");
                        System.out.println("========================================");
                    }
                    break;
                }
                case 2:{
                    try {
                        obj.inserData();
                        System.out.println("========================================");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3:{
                    try {
                        obj.updateData();
                        System.out.println("========================================");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 4:{
                    try {
                        obj.deleteData();
                        System.out.println("========================================");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 5:{
                    try {
                        obj.displayData();
                        System.out.println("========================================");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    System.out.println("");
            }
            System.out.println("Do you want continue(y/n): ");
            str = sc.next().charAt(0);
            if(str=='n')
            {
                System.exit(0);
            }
        }

    }

 

}
