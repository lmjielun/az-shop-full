package com.yzit;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 1、创建连接工厂 如有有外网端口，端口号可以是外网的
 * 2、获取连接
 * 3、启动连接
 * 4、获取session
 * 5、创建队列对象， 这里的队列名称与 生产者的 队列名称一致
 * 6、创建消息消费者
 * 7、监听消息
 * 8、等待键盘输入
 * 9、关闭资源
 *
 */
public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象  这里的创建队列名称 与  生产者的队列名称保持一致 test-queue
        Queue queue = session.createQueue("test-queue");
        //6.创建消息消费
        MessageConsumer consumer = session.createConsumer(queue);

        //7.监听消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("接收到消息："+textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        //8.等待键盘输入
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
