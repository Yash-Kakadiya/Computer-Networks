package P2P;

// Java Program to Implement Server Side 
import java.io.*;
import java.net.*;

public class Server {

    // Initialize socket and input stream
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    // Constructor with port
    public Server(int port) {

        // Starts server and waits for a connection
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            s = ss.accept();
            System.out.println("Client accepted");

            // Takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(s.getInputStream()));
            out = new DataOutputStream(
                    new BufferedOutputStream(s.getOutputStream()));

            String m = "";

            // Reads message from client until "Over" is sent
            while (!m.equals("Over")) {
                try {
                    m = in.readUTF();
                    System.out.println(m);

                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // Close connection
            s.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server s = new Server(7777);
    }
}
