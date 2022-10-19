package server;

import data.Shared;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    private Shared shared;
    private boolean active = true;

    public ServerThread(Socket socket, Shared shared){
        this.socket = socket;
        this.shared = shared;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter (socket.getOutputStream());

            shared.addClient(out);

            work(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void work(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        while(active && (line = in.readLine()) != null){
            System.out.println(line);
            String[] arguments = line.split(" ");
            switch (arguments[0]) {
                case "REQUEST":
                    out.println(shared.addRequests(arguments[1], Integer.parseInt(arguments[2])));
                    out.flush();
                    break;
                case "REQUESTS":
                    out.println(shared.getAllRequest());
                    out.flush();
                    break;
                case "CONFIRM":
                    out.println(shared.addConfirmations(arguments[1],Integer.parseInt(arguments[2])));
                    out.flush();
                    break;
                case "CONFIRMATIONS":
                    out.println(shared.getAllConfirmations());
                    out.flush();
                    break;
                case "EXIT":
                    active = false;
                    shared.removeClient(out);
                    break;
                default:
                    out.println("UNKNOWN COMMAND");
                    out.flush();
                    break;
            }
        }
        in.close();
        out.close();
    }
}
