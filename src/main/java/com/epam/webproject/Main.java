package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.model.dao.impl.UserDaoImpl;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.type.RatesType;
import com.epam.webproject.model.entity.type.Role;
import com.epam.webproject.model.entity.type.Status;
import com.epam.webproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

class Main {
    private final static Logger logger=LogManager.getLogger();
    public static void main(String args[]) throws DaoException {
        logger.info(2);
        UserDaoImpl dao=new UserDaoImpl();
    // Optional<User> user= dao.findUserByLoginAndPassword("2362","2S@1a.com");
     ///   dao.createNewUser(new User(12,"2362","2S@1a.com",0, Role.ADMIN,RatesType.NEWBIE,Status.NORMAL),"123","23");
        ///dao.createNewUser(new User(4,"m2ila3","2mi1l1a@11a.com",0, Role.USER, RatesType.HARDWORKER, Status.BLOCKED),"qwe123","123");
       // System.out.println( user);
     PasswordEncryptor encryptor=   PasswordEncryptor.getInstance();
        System.out.println(encryptor.getHash("milena    "));
        

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
//        ConnectionPool connectionPool=ConnectionPool.INSTANCE;
//        Connection connection=connectionPool.getConnection();
//
//            try {
//
//
//
//                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//w
//                    String sql = "SELECT id,login FROM users";
//                    ResultSet resultSet = statement.executeQuery(sql);
//
//                    List<User> users = new ArrayList<>();
//
//                    resultSet.moveToInsertRow();
//
//                    resultSet.updateLong(1, 2);
//                    resultSet.updateString(2, "my first sql table");
//                    resultSet.updateString(3, "mail");
//                    resultSet.insertRow();
//                    while (resultSet.next()) {
                        //insert
////
//                        long id = resultSet.getLong(1);
//                        System.out.println(id);
//                        if (id == 0) {
//                            resultSet.updateLong(1, 23);
//                            resultSet.updateRow();
//                        }
                        //get
//                            long id = resultSet.getLong(1);
//                        String login = resultSet.getString(2);
//                        users.add(new User(id, login, "", 0, RoleType.valueOf("user".toUpperCase()), RatesType.valueOf("newbie".toUpperCase())));
////remove
//
//                    }
//                System.out.println(users.toString());
//                }catch (SQLException e){};





    }
}