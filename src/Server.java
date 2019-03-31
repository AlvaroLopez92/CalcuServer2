import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

private ServerSocket server;
private Socket connection;

public static void main(String[] args) throws IOException {

    try (
            ServerSocket server = new ServerSocket(4444);
            Socket connection = server.accept();

            DataInputStream dis = new DataInputStream(connection.getInputStream());
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());) {
        System.out.print("Se ha conectado satisfactoriamente con la calculadora. \n");

        int num1 = dis.readInt();
        int num2 = dis.readInt();
        while (Integer.toString(num1) != "" && Integer.toString(num2) != "") {
            int answer = num1 + num2;

            
            dos.writeInt(answer);
        }
    } catch (IOException ie) {
        System.out.println(ie);
    }
}
}