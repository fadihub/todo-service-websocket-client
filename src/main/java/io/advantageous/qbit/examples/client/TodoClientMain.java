package io.advantageous.qbit.examples.client;


import io.advantageous.qbit.client.Client;
import io.advantageous.qbit.client.ClientBuilder;


/**
 * Created by fadi on 1/9/15.
 */
public class TodoClientMain {

    public static void main(String... args) {

        String host = "localhost";

        int port = 8080;


        Client client = new ClientBuilder().setPort(port).setHost(host).setPollTime(10)
                .setAutoFlush(true).setFlushInterval(50).setRequestBatchSize(50)
                .setProtocolBatchSize(50).build();

        TodoServiceClientInterface todoService =
                client.createProxy(TodoServiceClientInterface.class, "todoService");

        client.start();


        todoService.add(new TodoItem("Greeting", "Hello World"));
        todoService.add(new TodoItem("Tutorial","I hope you got this to work"));

        client.flush();


        todoService.list(todoItems -> { //LAMBDA EXPRESSION Java 8

            for (TodoItem item : todoItems) {
                System.out.println("TODO ITEM " + item.getDescription() + " " + item.getName() + " " + item.getDue());
            }
        });

        client.flush();

        



    }
}
