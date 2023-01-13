import java.sql.*;
import java.util.Date;
import  java.util.*;

public class Test {
    public static  Connection conn;//创建连接对象（将Connection 放在方法体外面扩大适用范围）
    //mysql语句：添加信息
    private static final String SQL1 = "insert  into students(name,sex,classid) values (?,?,?)";

    //mysql语句：删除语句
    private static final String SQL2 = "delete from  Students where id=?";
    //mysql语句：修改语句
    private static final String SQL3 = "update  students set name=?,sex=?,classid=? where id=?";
    //mysql语句：根据学号查询信息
    private static final String SQL4 = " select  * from  students WHERE id=?";
    //mysql语句：查询所有学生

    private static final String SQL5 = " SELECT id,NAME,sex,classid,class_id,classtime\n" +
            "FROM students AS s\n" +
            "INNER JOIN class AS c\n" +
            "WHERE s.classid=c.class_id";
    //连接数据库的方法

    public static void link() throws Exception {
        String url = "jdbc:mysql://localhost:3306/" + "school";
        String user = "root";
        String password = "xjr20021107";


        conn= DriverManager.getConnection(url,user,password);
        System.out.println("数据库连接成功");
    }


    public static void show(int id) throws SQLException {
        //通过mysql语句实现查询操作
        PreparedStatement ps5 = conn.prepareStatement(SQL4);
        System.out.println("------------查找学生信息---------------------");

        ps5.setInt(1,id);
        ResultSet resultSet1= ps5.executeQuery();
        System.out.println("------------查询结果-------------------");
        while (resultSet1.next()){
            System.out.println("学号："+resultSet1.getString("id"));
            System.out.println("姓名："+resultSet1.getString("name"));
            System.out.println("性别："+resultSet1.getString("sex"));
            System.out.println("班级："+resultSet1.getString("classid"));

        }
    }

    public static void showAll() throws SQLException {
        //通过mysql语句实现查询所有操作
        PreparedStatement ps4 = conn.prepareStatement(SQL5);

        System.out.println("------------所有学生信息---------------------");
        ResultSet resultSet = ps4.executeQuery();

        System.out.println("------------查询结果---------------------");
        while (resultSet.next()){
            System.out.println("学号："+resultSet.getString("id"));
            System.out.println("姓名："+resultSet.getString("name"));
            System.out.println("性别："+resultSet.getString("sex"));
            System.out.println("班级："+resultSet.getString("classid"));
            System.out.println("入学时间："+resultSet.getString("classtime"));
        }
    }

    public static void remove(int id) throws SQLException {
        //通过mysql语句实现删除操作
        PreparedStatement ps2 = conn.prepareStatement(SQL2);
        System.out.println("------------删除学生信息---------------------");
        System.out.println("请输入您要删除的学生学号");

        ps2.setInt(1,id);
        if(ps2.executeUpdate()>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }

    public static void add(String name, String  sex, int classid) throws SQLException {
        // 添加学生信息

        //3.通过mysql语句实现添加操作
        PreparedStatement ps =conn.prepareStatement(SQL1);
        //4.将添加的信息传入数据库中
        System.out.println("-----------------正在添加学生信息-----------------");
        System.out.println("姓名："+name);
        System.out.println("性别："+sex);
        System.out.println("班级："+classid);

        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setInt(3, classid);
        if(ps.executeUpdate()>0){
            System.out.println("添加成功!");
        }else{
            System.out.println("添加失败!");
        }

    }



    public static void update(String name, String sex, int classid,int id) throws SQLException {
        //通过mysql语句实现修改操作
        PreparedStatement ps3 = conn.prepareStatement(SQL3);
        System.out.println("------------修改学生信息---------------------");


        ps3.setInt(4,id);
        ps3.setString(1,name);
        ps3.setString(2,sex);
        ps3.setInt(3,classid);
        if(ps3.executeUpdate()>0){
            System.out.println("修改成功！");
        }else{
            System.out.println("修改失败!");
        }
    }

    public static void main(String[] args) throws Exception {
        Test.link();
        Date date = new Date(); // this object contains the current date value
        Test.showAll();
        Test.show(1);
        Test.add("小黑", "男", 2);//姓名，性别，班级
        Test.add("小毕", "男", 2);
        Test.showAll();
        Test.update("小毕","女",3,9);//名字，性别，班级，学号
        Test.update("小毕","女",3,1);
        Test.showAll();
        Test.remove(1);
        Test.showAll();
        conn.close();
        }



}








