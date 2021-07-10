package com.epam.webproject;

import com.epam.webproject.model.connection.ConnectionCreator;
import com.epam.webproject.model.connection.ConnectionPool;

import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.RoleType;
import com.epam.webproject.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;

class Main {
    private final static Logger logger=LogManager.getLogger();
    public static void main(String args[]) {

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException exception) {
//            exception.printStackTrace();
//        }




//try {
//   connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test" +
//           "","root","Qwe123okA123");
//} catch (SQLException throwables) {
//    throwables.printStackTrace();
//}


//
//        String url = "jdbc:mysql://localhost:3306/first_project";
//        Properties properties = new Properties();
//        properties.put("user", "root");
//        properties.put("password", "Qwe123okA123");
//        properties.put("autoReconnect", "true");
//        properties.put("characterEncoding", "UTF-8");
//        properties.put("useUnicode", "true");
//        try (
//                Connection connection = DriverManager.getConnection(url, properties);
        ConnectionPool connectionPool=ConnectionPool.INSTANCE;
        Connection connection=connectionPool.getConnection();

            try {



                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    String sql = "SELECT id,login FROM users";
                    ResultSet resultSet = statement.executeQuery(sql);

                    List<User> users = new ArrayList<>();

                    resultSet.moveToInsertRow();

                    resultSet.updateLong(1, 2);
                    resultSet.updateString(2, "my first sql table");
                    resultSet.updateString(3, "mail");
                    resultSet.insertRow();
                    while (resultSet.next()) {
                        //insert
////
//                        long id = resultSet.getLong(1);
//                        System.out.println(id);
//                        if (id == 0) {
//                            resultSet.updateLong(1, 23);
//                            resultSet.updateRow();
//                        }
                        //get
                            long id = resultSet.getLong(1);
                        String login = resultSet.getString(2);
                        users.add(new User(id, login, "", 0, RoleType.valueOf("user".toUpperCase()), RatesType.valueOf("newbie".toUpperCase())));
//remove

                    }
                System.out.println(users.toString());
                }catch (SQLException e){};





    }
}