package com.lzy;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducter {
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取链接
        Connection connection = factory.createConnection();
        //启动链接
        connection.start();
        //获取session（参数1：是否启动事务  参数2：消息确认模式）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("lzy");
        //创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //创建消息
        TextMessage textMessage = session.createTextMessage("点对点模式");
        //发送消息
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
