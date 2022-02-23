package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oxotn3
 * @create 2022-02-23
 * @description
 */
public class RabbitMQConn {
    private static final String EXCHANGE_NAME = "RankingListExchange";
    private static final String ROUTING_KEY = "RankingListRouting";
    private static final String QUEUE_NAME = "RankingListQueue";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static void sendMsg(String playerName, double score, String playTime) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 创建一个type="direct"、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        // 创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        // 发送一条持久化的消息：hello world！
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("playerName", playerName);
        map.put("score", score);
        map.put("playTime", playTime);
        // map转byte数组
        byte[] bt = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(map);
        bt = os.toByteArray();
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, bt);
        channel.close();
        connection.close();
        System.out.println("send success");
    }

//    public static void main(String[] args) {
//        try {
//            String playTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            sendMsg("AIRCRAFT_WARS", 98d, playTime);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
