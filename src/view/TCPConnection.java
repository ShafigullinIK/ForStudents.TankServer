package view;

import controller.CommandController;
import controller.TankController;
import model.Tank;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPConnection {

    private static ServerSocket server;
    private static final int port = 12345;
    private static ArrayList<Connection> connections = new ArrayList<>();
    private static TankController tankController;

    public TCPConnection(CommandController commandController, TankController tankController){
        try {
            this.tankController = tankController;
            server = new ServerSocket(port);
            Socket socket = server.accept();
            Connection connection = new Connection(socket, commandController, tankController);
        } catch (IOException e) {

        }

    }

    static class Connection{
        Socket socket;
        BufferedReader in;
        BufferedWriter out;

        public Connection(Socket socket, CommandController commandController, TankController tankController) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        while (true){
                            String msg = in.readLine();
                            commandController.addCommand(msg);
                            System.out.println(msg);
                            sendInfo(tankController);
                        }

                    } catch (IOException e) {

                    }
                }
            }).start();
        }

        public void sendInfo(TankController tankController) throws IOException {
            ArrayList<Tank> tanks = tankController.getTanks();
            for (Tank tank: tanks) {
                out.write(tank.toString());
                out.newLine();
            }
            out.flush();
        }

        public void close() throws IOException {
            in.close();
            out.close();
            socket.close();
        }
    }

}
