package com.yzit;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 *  MQ生产者步骤
 *  1、创建连接工厂
 *  2、获取连接
 *  3、启动连接
 *  4、获取session
 *  5、创建队列对象 记住队列对象的命名
 *  6、创建消息生产者，将生产者与session绑定
 *  7、创建消息
 *  8、发送消息
 *  9、关闭资源由小到大关
 */
public class QueueProducer {

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue = session.createQueue("test-queue");
        //6.创建消息生产者，将生产者与session与队列进行绑定
        MessageProducer producer = session.createProducer(queue);
        //7.创建消息
        TextMessage textMessage = session.createTextMessage("你好啊");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
    }
}
