package interview;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 8080; // Specify the port you want to use
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context for handling incoming requests
        server.createContext("/", new MyHandler());

        // Start the server
        server.start();

        System.out.println("Server is running on port " + port);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Handle the incoming GET request
            if ("GET".equals(exchange.getRequestMethod())) {
                // Set the response content type
                exchange.getResponseHeaders().set("Content-Type", "text/plain");

                // Prepare the response message
                String response = "Hello, World! This is your Java HTTP server.";

                // Send the response
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Handle other HTTP methods (e.g., POST, PUT, etc.) if needed
                exchange.sendResponseHeaders(405, 0); // Method Not Allowed
            }
        }
    }
}