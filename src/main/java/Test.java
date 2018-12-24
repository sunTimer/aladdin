//import sun.reflect.Reflection;
//
//import javax.xml.ws.soap.Addressing;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Test {
//    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//////        System.out.println(Reflection.getCallerClass());
////        Class clazz = Class.forName("Shixu");
////        System.out.println(clazz.getAnnotations()[0].toString());
////
////        System.out.println(clazz.getMethods()[0]);
////        System.out.println(clazz.getClassLoader().getParent().getParent());
////
////        System.out.println(clazz.getConstructors());
////        Constructor constructor = clazz.getConstructor(Shixu.class);
////
////        Shixu shixu = (Shixu) constructor.newInstance();
////        System.out.println(shixu);
//
//        Class clazz = Class.forName("com.mysql");
//        Driver driver = (Driver) clazz.newInstance();
//        Connection connection = driver.connect();
//        DriverManager driverManager = new DriverManager();
//    }
//}
//
//@Addressing
//
//class Shixu {
//
//    Shixu() {
//
//    }
//
//    private String age;
//
//    public void test() {
//    }
//
//    @Override
//    public String toString() {
//        return this.getClass().getName();
//    }
//}