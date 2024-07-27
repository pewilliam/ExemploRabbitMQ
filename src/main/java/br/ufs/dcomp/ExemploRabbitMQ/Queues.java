package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Queues {

  private final static String QUEUE_NAME = "minha-fila";
  private static String routerKey = "";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.228.100.244"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("PedroW2001"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare("E1", "fanout");
    channel.exchangeDeclare("E2", "direct");
    
    for(int i = 0; i < 10; i++){
      if(i % 2 != 0){
        channel.queueBind(QUEUE_NAME + i, "E1", "");
      }
      else{
        if(routerKey == "B" || routerKey.isEmpty()){
          routerKey = "A";
          channel.queueBind(QUEUE_NAME + i, "E2", routerKey);  
        }
        else{
          routerKey = "B";
          channel.queueBind(QUEUE_NAME + i, "E2", routerKey);  
        }
      }
    }
      
    channel.close();
    connection.close();
  }
}