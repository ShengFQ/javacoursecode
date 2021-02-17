package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MainApp {


   public static void main(String[] args) {

      ApplicationContext context;
       context = new ClassPathXmlApplicationContext("applicationContext.xml");
       IStudentService studentService=(IStudentService)context.getBean("studentServiceImpl");
       //StudentJdbcTemplate studentJDBCTemplate =
     // (StudentJdbcTemplate)context.getBean("studentJDBCTemplate");
     // StudentJdbcRepository studentJDBCTemplate=(StudentJdbcRepository)context.getBean("studentJDBCRepository");
      /*System.out.println("------Records Creation--------" );
      studentJDBCTemplate.create("Zara", 11);
      studentJDBCTemplate.create("Nuha", 2);
      studentJDBCTemplate.create("Ayan", 15);
      System.out.println("------Listing Multiple Records--------" );
      List<Student> students = studentJDBCTemplate.listStudents();
      for (Student record : students) {
         System.out.print("ID : " + record.getId() );
         System.out.print(", Name : " + record.getName() );
         System.out.println(", Age : " + record.getAge());
      }
      System.out.println("----Updating Record with ID = 2 -----" );
      studentJDBCTemplate.update(2, 20);
      System.out.println("----Listing Record with ID = 2 -----" );
      Student student = studentJDBCTemplate.getStudent(2);
      System.out.print("ID : " + student.getId() );
      System.out.print(", Name : " + student.getName() );
      System.out.println(", Age : " + student.getAge());      */
       List<Student> students = new ArrayList<>();
       students.add(new Student(33,"kimmking"));
       students.add(new Student(11,"workman"));
       studentService.saveStudent(students);
   }
}