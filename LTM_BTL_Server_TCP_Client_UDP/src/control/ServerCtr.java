package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Game;

import model.IPAddress;
import model.ObjectWrapper;
import model.PlayGame;
import model.Player;
import view.ServerMainFrm;

public class ServerCtr {

    private ServerMainFrm view;
    private ServerSocket myServer;
    private ServerListening myListening;
    private ArrayList<ServerProcessing> myProcess;
    //client_udp
    private IPAddress myAddress = new IPAddress("localhost", 8888);  //default server host and port
    private IPAddress serverAddress = new IPAddress("localhost", 5555); //default server address
    private IPAddress myAddressUDP = new IPAddress("localhost", 6666); //default client address
    private DatagramSocket myClient;

    //client_udp
    public ServerCtr(ServerMainFrm view) {
        myProcess = new ArrayList<ServerProcessing>();
        this.view = view;
        openServer();
    }

    public ServerCtr(ServerMainFrm view, int serverPort) {
        myProcess = new ArrayList<ServerProcessing>();
        this.view = view;
        myAddress.setPort(serverPort);
        openServer();

    }

    public ServerCtr(ServerMainFrm view, IPAddress serverAddr) {
        this.view = view;

        serverAddress = serverAddr;
    }

    public ServerCtr(ServerMainFrm view, IPAddress serverAddr, int clientPort) {
        this.view = view;
        serverAddress = serverAddr;
        myAddress.setPort(clientPort);
    }

    public boolean open() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            System.out.println(serverSocket.getLocalPort());
            myAddress.setPort(serverSocket.getLocalPort());
            myClient = new DatagramSocket(myAddress.getPort());
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            //view.setServerandClientInfo(serverAddress, myAddress);
            view.showMessage("UDP client is running at the host: " + myAddress.getHost() + ", port: " + myAddress.getPort());
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error to open the datagram socket!");
            return false;
        }
        return true;
    }

    public boolean close() {
        try {
            myClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error to close the datagram socket!");
            return false;
        }
        return true;
    }

    private void openServer() {
        try {
            myServer = new ServerSocket(myAddress.getPort());
            myListening = new ServerListening();
            myListening.start();
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            view.showServerInfor(myAddress);
            //System.out.println("server started!");
            view.showMessage("TCP server is running at the port " + myAddress.getPort() + "...");
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void stopServer() {
        try {
            for (ServerProcessing sp : myProcess) {
                sp.stop();
            }
            myListening.stop();
            myServer.close();
            view.showMessage("TCP server is stopped!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publicClientNumber() {
        ObjectWrapper data = new ObjectWrapper(ObjectWrapper.TABLE_FRIEND_UPDATE, myProcess.size());
        for (ServerProcessing sp : myProcess) {
            sp.sendData(data);
        }
    }

    /**
     * The class to listen the connections from client, avoiding the blocking of
     * accept connection
     *
     */
    class ServerListening extends Thread {

        public ServerListening() {
            super();
        }

        public void run() {
            view.showMessage("server is listening... ");
            try {
                while (true) {
                    Socket clientSocket = myServer.accept();
                    ServerProcessing sp = new ServerProcessing(clientSocket);
                    sp.start();
                    myProcess.add(sp);
                    boolean ok = open();
                    // System.out.println(myClient.getPort());

                    view.showMessage("Number of client connecting to the server: " + myProcess.size());
                    //publicClientNumber();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The class to treat the requirement from client
     *
     */
    class ServerProcessing extends Thread {

        private Socket mySocket;
        //private ObjectInputStream ois;
        //private ObjectOutputStream oos;

        public ServerProcessing(Socket s) {
            super();
            mySocket = s;
        }

        public void sendData(Object obj) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
                oos.writeObject(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
                    Object o = ois.readObject();
                    if (o instanceof ObjectWrapper) {
                        ObjectWrapper data = (ObjectWrapper) o;

                        switch (data.getPerformative()) {
                            case ObjectWrapper.LOGIN_USER:
                                Player player = (Player) data.getData();
                                // System.out.println(player.getIdPlayer() + " " + player.getName());
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_LOGIN_USER) {  //udpclient
                                        //servertcp
                                        if (dataRecieveUDP.getData().equals("false")) {
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER, "false"));
                                        } else {
                                            ArrayList<Player> result = (ArrayList<Player>) dataRecieveUDP.getData();
                                            player = result.get(result.size() - 1);
                                            //System.out.println(player.getIdPlayer() + " " + player.getName());
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER, result));
                                            for (ServerProcessing sp : myProcess) {
                                                if (sp.mySocket != mySocket) {
                                                    ObjectOutputStream oos2 = new ObjectOutputStream(sp.mySocket.getOutputStream());
                                                    oos2.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_TABLE_FRIEND_UPDATE, player));
                                                }
                                            }
                                        }
                                        //het servertcp
                                    }
                                }
                                break;
                            case ObjectWrapper.REGISTER_USER:
                                player = (Player) data.getData();
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataReceived = receiveDataUDP();
                                    if (dataReceived.getPerformative() == ObjectWrapper.REPLY_REGISTER_USER) {
                                        if (dataReceived.getData().equals("false")) {
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_REGISTER_USER, "false"));
                                            view.showMessage("false");
                                        } else {
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_REGISTER_USER, "ok"));
                                            view.showMessage("ok");
                                        }
                                    }
                                }
                                break;
                            case ObjectWrapper.FIGHT:
                                ArrayList<Player> result = (ArrayList<Player>) data.getData();
                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT, result));
                                for (ServerProcessing sp : myProcess) {
                                    ObjectOutputStream oos3 = new ObjectOutputStream(sp.mySocket.getOutputStream());
                                    oos3.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_TO, result));
                                }
                                break;
                            case ObjectWrapper.FIGHT_YES:
                                result = (ArrayList<Player>) data.getData();
                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_YES, result));
                                for (ServerProcessing sp : myProcess) {
                                    if (sp.mySocket != mySocket) {
                                        // System.out.println("khac");
                                        ObjectOutputStream oos4 = new ObjectOutputStream(sp.mySocket.getOutputStream());
                                        oos4.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_YES_TO, result));
                                    }
                                }
                                break;
                            case ObjectWrapper.ROOM_READY:

                                Game game1 = (Game) data.getData();
                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_ROOM_READY, "ok"));
                                for (ServerProcessing sp : myProcess) {
                                    if (sp.mySocket != mySocket) {
                                        ObjectOutputStream oos5 = new ObjectOutputStream(sp.mySocket.getOutputStream());
                                        oos5.writeObject(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, game1));
                                        //System.out.println("btnstart test server broadcast +: " + pgame.get(0).getPlayer().getIdPlayer());
                                    }
                                }
//                                ArrayList<PlayGame> pgame = (ArrayList<PlayGame>) data.getData();
//                                System.out.println("btnstart test server : " + pgame.get(1).getPlayer().getIdPlayer());
//                                if (sendDataUDP(data)) {
//                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
//                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_ROOM_READY) {
//                                        System.out.println("check else server tcp");
//                                        pgame=(ArrayList<PlayGame>)dataRecieveUDP.getData();
//                                        System.out.println("server tcp test, id pgame1: " + pgame.get(0).getId() + "id pgame2: " + pgame.get(1).getId());
//                                        oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_ROOM_READY, pgame));
//                                        for (ServerProcessing sp : myProcess) {
//                                            if (sp.mySocket != mySocket) {
//                                                ObjectOutputStream oos5 = new ObjectOutputStream(sp.mySocket.getOutputStream());
//                                                oos5.writeObject(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, pgame));
//                                                System.out.println("btnstart test server broadcast +: " + pgame.get(0).getPlayer().getIdPlayer());
//                                            }
//                                        }
//                                    }
//
//                                }
//                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_ROOM_READY, pgame));
//                                System.out.println("btnstart test server : " + pgame.get(1).getPlayer().getIdPlayer());
//                                for (ServerProcessing sp : myProcess) {
//                                    if (sp.mySocket != mySocket) {
//                                        // System.out.println("khac");
//                                        ObjectOutputStream oos5 = new ObjectOutputStream(sp.mySocket.getOutputStream());
//                                        oos5.writeObject(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, pgame));
//                                        System.out.println("btnstart test server broadcast +: " + pgame.get(0).getPlayer().getIdPlayer());
//                                    }
//                                }
//                                player = (Player) data.getData();
//                                
//                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_ROOM_READY, "ok"));
//                                System.out.println("btnstart test server +: " + player.getIdPlayer());
//                                for (ServerProcessing sp : myProcess) {
//                                    if (sp.mySocket != mySocket) {
//                                        // System.out.println("khac");
//                                        ObjectOutputStream oos5 = new ObjectOutputStream(sp.mySocket.getOutputStream());
//                                        oos5.writeObject(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, player));
//                                          System.out.println("btnstart test server broadcast +: " + player.getIdPlayer());
//                                    }
//                                }
                                break;
                            case ObjectWrapper.GAME_WIN:
                                //String[] game = (String[]) data.getData();
//                                Game gameResult = (Game) data.getData();
//                                        
//                                System.out.println("winner is: " + gameResult.getPlayer1().getIdPlayer()+ ", loser is: "+gameResult.getPlayer2().getIdPlayer()+", time is: "+gameResult.getTime());
//                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN, "ok"));
//                                for (ServerProcessing sp : myProcess) {
//                                    if (sp.mySocket != mySocket) {
//                                        System.out.println("test win ok");
//                                        ObjectOutputStream oos6 = new ObjectOutputStream(sp.mySocket.getOutputStream());
//                                        oos6.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN_TO, gameResult));
//                                    }
//                                }
                                Game gameResult = (Game) data.getData();
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_GAME_WIN) {
                                        if (dataRecieveUDP.getData().equals("false")) {
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN, "false"));
                                        } else {
                                            //System.out.println("winner is: " + gameResult.getPgameWinner().getPlayer().getIdPlayer() + ", loser is: " + gameResult.getPgameLoser().getPlayer().getIdPlayer() + ", time is: " + gameResult.getTime());
                                            oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN, "ok"));
                                            for (ServerProcessing sp : myProcess) {
                                                if (sp.mySocket != mySocket) {
                                                    System.out.println("test win ok");
                                                    ObjectOutputStream oos6 = new ObjectOutputStream(sp.mySocket.getOutputStream());
                                                    oos6.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN_TO, gameResult));
                                                }
                                            }
                                        }
                                    }

                                }
                                break;
                            case ObjectWrapper.GAME_END:
                                System.out.println("test game end serverctr");
                                oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GAME_END, "ok"));
                                break;

                            case ObjectWrapper.GET_HISTORY:
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_GET_HISTORY) {
                                        ArrayList<Game> listGame = new ArrayList<Game>();
                                        listGame = (ArrayList<Game>) dataRecieveUDP.getData();
                                        System.out.println("test history listGame server tcp: " + listGame.size());
                                        oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GET_HISTORY, listGame));
                                    }
                                }
                                break;
                            case ObjectWrapper.GET_DETAIL_HISTORY_WIN:
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_GET_DETAIL_HISTORY) {
                                        gameResult = (Game) dataRecieveUDP.getData();
                                        //System.out.println("test history listGame server udp: " + listGame.get(0).getPgameWinner().getId());
                                        oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GET_DETAIL_HISTORY, gameResult));
                                    }
                                }
                                break;
                            case ObjectWrapper.GET_DETAIL_HISTORY_LOSE:
                                if (sendDataUDP(data)) {
                                    ObjectWrapper dataRecieveUDP = receiveDataUDP();
                                    if (dataRecieveUDP.getPerformative() == ObjectWrapper.REPLY_GET_DETAIL_HISTORY) {
                                        gameResult = (Game) dataRecieveUDP.getData();
                                        //System.out.println("test history listGame server udp: " + listGame.get(0).getPgameWinner().getId());
                                        oos.writeObject(new ObjectWrapper(ObjectWrapper.REPLY_GET_DETAIL_HISTORY, gameResult));
                                    }
                                }
                                break;
                        }
                    }
                }
            } catch (EOFException | SocketException e) {
                //e.printStackTrace();
                myProcess.remove(this);
                view.showMessage("Number of client connecting to the server: " + myProcess.size());
                publicClientNumber();
                try {
                    mySocket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sendDataUDP(ObjectWrapper data) {
        try {
            //prepare the buffer and write the data to send into the buffer
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            oos.flush();

            //create data package and send
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress.getHost()), serverAddress.getPort());
            myClient.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error in sending data package");
            return false;
        }
        return true;
    }

    public ObjectWrapper receiveDataUDP() {
        ObjectWrapper result = null;
        try {
            //prepare the buffer and fetch the received data into the buffer
            byte[] receiveData = new byte[10000];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myClient.receive(receivePacket);

            //read incoming data from the buffer 
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            result = (ObjectWrapper) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error in receiving data package");
        }
        return result;
    }
}
