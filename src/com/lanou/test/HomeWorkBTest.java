package com.lanou.test;

import com.lanou.domain.Classes;
import com.lanou.domain.Student;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class HomeWorkBTest {

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

    /**
     * 1.已经存在一个班级, 新建一个学生, 把学生加入到该班级
     * 配置：classes的inverse为"true"
     **/
    @Test
    public void b_one() {
        Classes classes = session.get(Classes.class, 1);

        Student student = new Student("郭丽静");
        student.setClasses(classes);

        classes.getStudents().add(student);
    }

    /**
     * 2. 已经存在一个学生, 新建一个班级, 把学生加入到该班级
     * 配置：classes的inverse为"true",classes的cascade为"save-update"
     **/
    @Test
    public void b_two() {
        Student student = session.get(Student.class, 1);

        Classes classes = new Classes("J0804");
        classes.getStudents().add(student);

        student.setClasses(classes);

        session.save(classes);
    }

    /**
     * 3. 已经存在一个班级, 已经存在一个学生, 把学生加入到该班级
     * 配置：classes的inverse为"true",classes的cascade为"save-update"
     **/
    @Test
    public void b_three() {
        Student student = session.get(Student.class, 1);
        Classes classes = session.get(Classes.class, 1);

        classes.getStudents().add(student);
        student.setClasses(classes);

        session.update(classes);
    }

    /**
     * 4.已经存在一个学生, 把一个学生从另一个班级cid=2转入该班级cid=1
     * 配置：classes的inverse为"true",classes的cascade为"save-update"
     **/
    @Test
    public void b_four() {
        Student student = session.get(Student.class, 1);
        Classes classes = session.get(Classes.class, 2);

        classes.getStudents().add(student);
        student.setClasses(classes);

        session.update(student);
    }
}
