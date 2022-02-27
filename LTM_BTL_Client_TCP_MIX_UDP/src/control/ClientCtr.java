package control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.IPAddress;
import model.ObjectWrapper;
import model.Player;
import view.LoginFrm;
import view.ClientMainFrm;
import view.GameSapXepSoFrm;
import view.HistoryFrm;
import view.HomeFrm;
import view.LoginFrm;
import view.RegisterFrm;
import view.RoomFrm;

public class ClientCtr {

    private Socket mySocket;
    private ClientMainFrm view;
    private HomeFrm homeview;
    private Player player;
    private ClientListening myListening;                            // thread to listen the data from the server
    private ArrayList<ObjectWrapper> myFunction;                  // list of active client functions
    private IPAddress serverAddress = new IPAddress("localhost", 8888);  // default server host and port

    public ClientCtr(ClientMainFrm view) {
        super();
        this.view = view;
        myFunction = new ArrayList<ObjectWrapper>();
    }

    public ClientCtr(ClientMainFrm view, IPAddress serverAddr) {
        super();
        this.view = view;
        this.serverAddress = serverAddr;
        myFunction = new ArrayList<ObjectWrapper>();
    }

    public boolean openConnection() {
        try {
            mySocket = new Socket(serverAddress.getHost(), serverAddress.getPort());
            myListening = new ClientListening();
            myListening.start();
            view.showMessage("Connected to the server at host: " + serverAddress.getHost() + ", port: " + serverAddress.getPort());
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when connecting to the server!");
            return false;
        }
        return true;
    }

    public boolean sendData(Object obj) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(obj);

        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when sending data to the server!");
            return false;
        }
        return true;
    }

    /*
    public Object receiveData(){
        Object result = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            result = ois.readObject();
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when receiving data from the server!");
            return null;
        }
        return result;
    }*/
    public boolean closeConnection() {
        try {
            if (myListening != null) {
                myListening.stop();
            }
            if (mySocket != null) {
                mySocket.close();
                view.showMessage("Disconnected from the server!");
            }
            myFunction.clear();
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when disconnecting from the server!");
            return false;
        }
        return true;
    }

    public ArrayList<ObjectWrapper> getActiveFunction() {
        return myFunction;
    }
//    public Player getPlayer(){
//        return this.player;
//    }

    class ClientListening extends Thread {

        public ClientListening() {
            super();
        }

        public void run() {
            try {
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                    Object obj = ois.readObject();
                    if (obj instanceof ObjectWrapper) {
                        ObjectWrapper data = (ObjectWrapper) obj;
                        for (ObjectWrapper fto : myFunction) {
                            if (fto.getPerformative() == data.getPerformative()) {
                                switch (data.getPerformative()) {
                                    case ObjectWrapper.REPLY_LOGIN_USER:
                                        LoginFrm loginView = (LoginFrm) fto.getData();
                                        loginView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_REGISTER_USER:
                                        RegisterFrm rtv = (RegisterFrm) fto.getData();
                                        rtv.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_TABLE_FRIEND_UPDATE:
                                        HomeFrm homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_FIGHT:
                                        //System.out.println("ok+fight");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_FIGHT_TO:
                                        //System.out.println("ok+fight+TO");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_FIGHT_YES:
                                        //System.out.println("ok+fight+TO");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_FIGHT_YES_TO:
                                        //System.out.println("ok+fight+TO");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_FIGHT_NO:
                                        //System.out.println("ok+fight+TO");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_ROOM_READY:
                                        System.out.println("test reply roon ready");
                                        RoomFrm roomView = (RoomFrm) fto.getData();
                                        roomView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.ROOM_READY_TO:
                                        System.out.println("test reply roon ready broadcast");
                                        roomView = (RoomFrm) fto.getData();
                                        roomView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_GAME_WIN:
                                        System.out.println("test reply game win");
                                        GameSapXepSoFrm gameView = (GameSapXepSoFrm) fto.getData();
                                        gameView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_GAME_WIN_TO:
                                        System.out.println("you lose ctr");
                                        gameView = (GameSapXepSoFrm) fto.getData();
                                        gameView.receivedDataProcessing(data);
                                        break;

                                    case ObjectWrapper.REPLY_GAME_END:
                                        // System.out.println("you lose ctr");
                                        homeView = (HomeFrm) fto.getData();
                                        homeView.receivedDataProcessing(data);
                                        break;
                                    case ObjectWrapper.REPLY_GET_HISTORY:
                                        HistoryFrm histf = (HistoryFrm) fto.getData();
                                        histf.receivedDataProcessing(data);
                                }
                            }
                        }
                        view.showMessage("Received an object: " + data.getPerformative());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                view.showMessage("Error when receiving data from the server!");
                view.resetClient();
            }
        }
    }
}
