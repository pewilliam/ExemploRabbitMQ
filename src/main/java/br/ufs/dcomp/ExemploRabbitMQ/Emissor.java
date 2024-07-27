package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  private final static String QUEUE_NAME = "minha-fila";
  
  private static String message;

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.228.100.244"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("PedroW2001"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    message = "Olá, ímpares!";
                      //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("E1",       QUEUE_NAME, null,  message.getBytes("UTF-8"));
    
    System.out.println(" [x] Mensagem enviada: '" + message + "'");
    
    message = "Olá, pares!";
                      //  (exchange, routingKey, props, message-body             ); 
    channel.basicPublish("E2",       "A", null,  message.getBytes("UTF-8"));
    
    System.out.println(" [x] Mensagem enviada: '" + message + "'");
    
    channel.close();
    connection.close();
  }
}