package com.lanou.test;

import com.lanou.domain.Classes;
import com.lanou.domain.Student;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class HomeWorkTest {

    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destroy() {
        transaction.commit();
        HibernateUtil.closeSession();
    }

    /***
     * 1. 保存班级级联保存学生
     * 配置：classes的cascade为"save-update"
     **/
    @Test
    public void a_one() {
        Student student = new Student("张瑞超");

        Classes classes = new Classes("0703");

        student.setClasses(classes);

        classes.getStudents().add(student);

        session.save(classes);
    }

    /***
     * 2. 保存班级级联更新学生
     * 配置：classes的cascade为"save-update"
     **/
    @Test
    public void a_two() {
        Classes classes = new Classes("J0804");

        Student student = session.get(Student.class, 1);

        student.setClasses(classes);//重新设置某个学生的所在班级

        classes.getStudents().add(student);

        session.save(classes);//保存新班级
    }

    /**
     * 3. 更新班级级联保存学生
     * 配置：classes的cascade为"save-update"
     **/
    @Test
    public void a_three() {
        Classes classes = session.get(Classes.class, 1);

        Student student = new Student("高鹏");
        student.setClasses(classes);
        classes.getStudents().add(student);//加入新的学生对象

        session.update(classes);//更新班级

        Student student1 = session.get(Student.class, 1);
        System.out.println("更新后的班级：" + student1.getClasses());
    }

    /**
     * 4. 更新班级级联更新学生
     * 配置：classes的cascade为"save-update"
     **/
    @Test
    public void a_four() {
        Classes classes = session.get(Classes.class, 1);

        //更新学生
        Iterator<Student> iterator = classes.getStudents().iterator();
        if (iterator.hasNext()) {
            Student student = iterator.next();
            student.setName("海燕");
        }

        session.update(classes);//更新班级

        Student student = session.get(Student.class, 1);
        System.out.println("更新后的班级：" + student.getClasses());
    }


    /**
     * 5. 删除班级级联删除学生
     * 配置：classes的cascade为"delete"
     **/
    @Test
    public void a_five() {
        Classes classes = session.get(Classes.class, 1);
        session.delete(classes);
    }


    /**
     * 6. 在班级有级联 save-update 的情况下,从关联得到学生并删除
     * 配置：classes的cascade为"save-update",student的cascade为"delete"
     **/
    @Test
    public void a_six() {
        Classes classes = session.get(Classes.class, 1);

        //更新学生
        Iterator<Student> iterator = classes.getStudents().iterator();
        if (iterator.hasNext()) {
            Student student = iterator.next();
            session.delete(student);
        }
    }
}
