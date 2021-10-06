import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
    public static int MAX_CLIENTS = 100;

    private static ArrayList<Client> clients = new ArrayList<Client>();

    public static void main(String[] args){
        Server server = new Server();
        try{
            startServer();
        } catch (Exception e){
            System.out.println("error while starting server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startServer() throws IOException {
        System.out.println("Starting server");
        ServerSocket serverSocket = new ServerSocket(2134);

        System.out.println("server started");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client : " + socket.getInetAddress().toString() + " " + socket.getPort());
            Client c = instantiateNewClient(socket);
            if (c == null) {
                // max capacity reached 
                socket.close();
            } else {
                // pool the work
                Pooling.execute((Runnable) c);
            }
        }
        
        
    }

    public static Client instantiateNewClient(Socket socket) throws IOException{
        if (clients.size() < MAX_CLIENTS){
            Client client = new Client(socket);
            clients.add(client);
            return client;
        }
        else {
            return null;
        }
    }

    public static void deleteClient(Client c){
        clients.remove(c);
    }

}


