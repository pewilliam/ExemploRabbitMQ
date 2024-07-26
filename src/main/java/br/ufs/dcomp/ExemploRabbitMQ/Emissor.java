package br.ufs.dcomp.ExemploRabbitMQ;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  private final static String QUEUE_NAME = "minha-fila";
  
  private static String message;

  public static void main(String[] argv) throws Exception {
    Scanner sc = new Scanner(System.in);
    
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("34.228.100.244"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("PedroW2001"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    while (true) {
    
                        //(queue-name, durable, exclusive, auto-delete, params); 
      channel.queueDeclare(QUEUE_NAME, false,   false,     false,       null);
  
      System.out.print("Mensagem: ");
      message = sc.nextLine();
      
      if(message.equalsIgnoreCase("sair")){
        break;
      }
      else{
                        //  (exchange, routingKey, props, message-body             ); 
      channel.basicPublish("",       QUEUE_NAME, null,  message.getBytes("UTF-8"));
      System.out.println(" [x] Mensagem enviada: '" + message + "'");
      }
    }

    sc.close();
    channel.close();
    connection.close();
  }
}