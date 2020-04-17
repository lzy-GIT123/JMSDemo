package com.lzy;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducter {
    public static void main(String[] args) throws JMSException {
        //创建工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取链接
        Connection connection = factory.createConnection();
        //启动链接
        connection.start();
        //获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建主题对象
        Topic topic = session.createTopic("lzy1");
        //创建消息生产者
        MessageProducer producer = session.createProducer(topic);
        //创建消息
        TextMessage textMessage = session.createTextMessage("订阅模式");
        //发送消息
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
