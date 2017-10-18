package com.lanou.test;

import com.lanou.domain.Customer;
import com.lanou.domain.Order;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class ManyToOneTest {

    @Test
    public void single() {
        //先创建用户对象
        Customer customer = new Customer(
                "李奕鹏", "liyipeng",
                "123", "男");
        //创建一个订单对象
        Order order = new Order(
                "L002", 18880, new Date());

        order.setCustomer(customer);//绑定订单所属的用户

        //保存用户以及订单对象
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        /*单向n-1, 在进行n的插入是需要确定1已经保存到数据库，即1是一个持久化状态的对象
        * 此示例中指的是插入订单时需要确保所属用户已经存在*/
        session.save(customer);//保存用户

        session.save(order);//保存订单

        transaction.commit();
        HibernateUtil.closeSession();
    }

    /**
     * 查询订单所属的用户
     **/
    @Test
    public void singleFind() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        //查找主键id为1的订单对象
        Order order = session.get(Order.class, 1);

        System.out.println("订单的基础属性：" + order);

//        System.out.println("订单所属用户的属性：" + order.getCustomer());

        transaction.commit();
        HibernateUtil.closeSession();

    }

    /**
     * 双向1-n中的插入
     **/
    @Test
    public void doubleInsert() {
        //创建用户对象和订单对象
        Customer customer = new Customer(
                "张瑞超", "zhangruichao",
                "123", "男");

        Order order = new Order("L003",
                19880, new Date());

        order.setCustomer(customer);//绑定订单所属的用户

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        //保存用户与订单对象
        session.save(customer);//保存用户
        session.save(order);//保存订单

        transaction.commit();
        HibernateUtil.closeSession();
    }

    /**
     * 双向1-n 查询用户下的所有订单集合
     **/
    @Test
    public void doubleFind() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        //查找主键id为2的用户对象
        Customer customer = session.get(Customer.class, 2);
        System.out.println("基础属性：" + customer);

        System.out.println("用户名下的订单集合：" + customer.getOrders());

        transaction.commit();
        HibernateUtil.closeSession();
    }
}
