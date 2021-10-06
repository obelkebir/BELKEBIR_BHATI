import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader input;
    private OutputStreamWriter output;

    public Client(Socket socket) throws IOException{
        System.out.println("Creating new Client");
        this.socket = socket;
        output = new OutputStreamWriter(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("lecture du contenu");
        String request = "";
        while (true){
            try {
                System.out.println("Socket fermee ? " + socket.isClosed());
                String received = "";
                received = input.readLine();
                //System.out.println("Lecture : " + received);
                //System.out.println("Count : " + received.length() + " " + (received.length() == 0));                    
                if (received.length() == 0)
                    break;
                if (received == null) {
                    System.out.print("NULL ");
                    output.write("\r\n\r\n");
                    output.flush();
                }
                request += "\n" + received;
            } catch (IOException e) {
                System.err.println("Socket fermée pour la lecture");
                e.printStackTrace();
                closeCommunication();
                return;
            } catch (java.lang.NullPointerException e) {
                System.err.println("Null pointer, interruption");
                //e.printStackTrace();
                closeCommunication();
                return;
            }
        }

        System.out.println("Le client demande : " + request);
        try {
            output.write(Response.greetingResponse());
            output.flush();
        } catch (IOException e) {
            System.err.println("Socket fermée pour l'ecriture");
        }
        closeCommunication();
        System.out.println("FIN DE REQUETE");
    }

    public void closeCommunication(){
        System.out.println("Terminating communication with client on " + socket.getInetAddress().toString() + " " + socket.getPort());
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error during close " + e.getMessage());
            e.printStackTrace();
        }
        Server.deleteClient(this);
    }

    private int numberEmptyLines(byte[] bytes){
        byte end[] = new String("\n").getBytes();
        int numberEmptyLine = 0;
        int counter = 0;
        for (int i = 0; i < bytes.length; i++) {
            counter++;
            if (bytes[i] == end[0]){
                System.out.println("found end of line, counter :" + counter);
                if (counter == 2) {
                    numberEmptyLine++;
                    System.out.println("found an empty line, numberEmptyLine :" + numberEmptyLine);
                }
                counter = 0;
            }
        }
        return numberEmptyLine;
    }


}