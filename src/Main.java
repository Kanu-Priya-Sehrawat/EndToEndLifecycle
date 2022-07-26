import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("/Users/rapido/Downloads/username.csv");
        BufferedReader br = new BufferedReader(fr);
        List<User> users = new ArrayList<>();
        String line;
        while((line =  br.readLine()) != null){
            String [] array = line.split(";");
            String username = array[0];
            String identifier = array[1];
            String first_name = array[2];
            String last_name = array[3];

            User user = new User(username, identifier, first_name, last_name);
            users.add(user);
        }

            String responseText = "";

            for(User user:users)
            {
                responseText += user.toString();
                responseText += '\n';
            }

            HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);

        String finalResponseText = responseText;

        server.createContext("/users", (exchange -> {

                        if("GET".equals(exchange.getRequestMethod()))
                        {
                            exchange.sendResponseHeaders(200, finalResponseText.getBytes().length );
                            OutputStream output = exchange.getResponseBody();
                            output.write(finalResponseText.getBytes());
                            output.flush();
                        }
                        else
                        {
                            exchange.sendResponseHeaders(405, -1);
                        }
                        exchange.close();
                    }));
            server.setExecutor(null);
            server.start();
            System.out.println("Server Started");
        }

    }