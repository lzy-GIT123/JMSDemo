package com.lzy;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //创建链接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取链接
        Connection connection = factory.createConnection();
        //启动链接
        connection.start();
        //获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("lzy");
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);
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
        //等待键盘输入
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}
