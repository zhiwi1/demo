package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.model.dao.CommentDao;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.dao.impl.CommentDaoImpl;
import com.epam.webproject.model.dao.impl.TaskDaoImpl;
import com.epam.webproject.model.dao.impl.UserDaoImpl;
import com.epam.webproject.model.email.MailSender;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

class Main {
    private final static Logger logger=LogManager.getLogger();
    public static void main(String args[]) throws DaoException {
//        TaskDao postDao=new TaskDaoImpl();
//        postDao.createNewTask("title","text",new Date(),"Qwe123",1);
        UserDao dao= DaoProvider.getInstance().getUserDao();
        String salt=PasswordEncryptor.getInstance().generateSalt();
        dao.createNewUser(new User(14,"admin","zhyuliuk.ivan@gmail.com",0, Role.ADMIN, RatesType.PROFESSIONAL, Status.NORMAL),PasswordEncryptor.getInstance().getHash("1",salt), salt);
        System.out.println(  dao.findByLogin("Qwe123"));
      //  MailSender sender=new MailSender();
        //MailSender.send("zhyuliuk.ivan@gmail.com","hello)))");
//        CommentDaoImpl commentDao=new CommentDaoImpl();
//        TaskDaoImpl taskDao=new TaskDaoImpl();
//        commentDao.createNewComment("hi",new Date(),"Qwe123","title");
//        System.out.println(   commentDao.findAll().toString());
//        System.out.println(taskDao.findAll().toString());
      //  postDao.createNewPost(new Post("title","text",new Date(),null,1,6,9));
//       Date date=new Date();
        java.util.Date date1=new java.util.Date();
        System.out.println(date1.toString());
//        java.sql.Date date=new java.sql.Date(date1.);
//        System.out.println( date);
    // Optional<User> user= dao.findUserByLoginAndPassword("2362","2S@1a.com");
       //(new User(12,"2362","2S@1a.com",0, Role.ADMIN,RatesType.NEWBIE,Status.NORMAL),"123","23");
        ///dao.createNewUser(new User(4,"m2ila3","2mi1l1a@11a.com",0, Role.USER, RatesType.HARDWORKER, Status.BLOCKED),"qwe123","123");
       // System.out.println( user);
       // System.out.println(  dao.existRowsByEmail("12@j1.com"));
//     PasswordEncryptor encryptor=   PasswordEncryptor.getInstance();
//        System.out.println(encryptor.getHash("milena    "));
//        UserService service=new UserServiceImpl();
//        try {
//            service.signInUser("2","Qwe123");
//        } catch (ServiceException exception) {
//            exception.printStackTrace();
//        }

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