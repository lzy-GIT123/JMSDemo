package com.lzy;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer2 {
    public static void main(String[] args) throws JMSException, IOException {
        //创建工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取链接
        Connection connection = factory.createConnection();
        //启动链接
        connection.start();
        //创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建主题对象
        Topic topic = session.createTopic("lzy1");
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //监听消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收到消息："+textMessage.getText());
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
        //等待键盘录入
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}
