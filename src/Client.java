import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

private int num1;
private int num2;
private int result;

public Client() throws IOException {
    try {

        //Se inicia la conexión con el servidor
        Socket server = new Socket("127.0.0.1", 4444);
        DataInputStream dis = new DataInputStream(server.getInputStream());
        DataOutputStream dos = new DataOutputStream(server.getOutputStream());

        //Interfata GUI
        Frame f = new Frame();
        f.setLayout(null);
        f.setTitle("Calculator");
        f.setBounds(100, 100, 435, 300);

        Label calc = new Label("Calculadora");
        calc.setBounds(149, 48, 109, 24);
        calc.setFont(new Font("Arial", Font.PLAIN, 14));
        calc.setAlignment(Label.CENTER);

        TextField numar1 = new TextField();
        numar1.setBounds(36, 106, 83, 22);

        Label label_1 = new Label("+");
        label_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        label_1.setBounds(139, 106, 13, 22);

        TextField numar2 = new TextField();
        numar2.setBounds(175, 106, 83, 22);

        Label egal = new Label("=");
        egal.setFont(new Font("Dialog", Font.PLAIN, 16));
        egal.setBounds(264, 106, 13, 22);

        TextField rezultat = new TextField();
        rezultat.setBounds(283, 106, 109, 22);
        rezultat.setEditable(false);
        rezultat.setText("");

        Button calcul = new Button("Calculeaza");
        calcul.setBackground(SystemColor.activeCaption);
        calcul.setForeground(SystemColor.textText);
        calcul.setBounds(0, 227, 434, 34);

        calcul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int num1 = Integer.parseInt(numar1.getText());
                int num2 = Integer.parseInt(numar2.getText());
                //Se envían los valores

                try {

                    dos.writeInt(num1);
                    dos.writeInt(num2);

                    //Se recoge el resultado
                    int answer;
                    answer = dis.readInt();

                    rezultat.setText(Integer.toString(answer));

                } catch (IOException ex) {
                   System.out.print(ex);
                }

            }

        });

        f.add(calc);
        f.add(numar1);
        f.add(label_1);
        f.add(numar2);
        f.add(egal);
        f.add(rezultat);
        f.add(calcul);

        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {

                System.exit(0);

            }

        });

    } catch (HeadlessException | IOException ex) {
    }

}

public static void main(String[] args) throws IOException {

    Client cl = new Client();

}
}