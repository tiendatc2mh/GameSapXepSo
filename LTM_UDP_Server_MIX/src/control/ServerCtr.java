package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;
import jdbc.dao.GameDAO;
import jdbc.dao.PlayGameDAO;

import jdbc.dao.PlayerDAO;
import model.Game;
import model.IPAddress;
import model.ObjectWrapper;
import model.PlayGame;
import model.Player;
import view.ServerMainFrm;

public class ServerCtr {

    private ServerMainFrm view;
    private DatagramSocket myServer;
    private IPAddress myAddress = new IPAddress("localhost", 5555); //default server address
    private UDPListening myListening;

    public ServerCtr(ServerMainFrm view) {
        this.view = view;
    }

    public ServerCtr(ServerMainFrm view, int port) {
        this.view = view;
        myAddress.setPort(port);
    }

    public boolean open() {
        try {
            myServer = new DatagramSocket(myAddress.getPort());
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            view.showServerInfo(myAddress);
            myListening = new UDPListening();
            myListening.start();
            view.showMessage("UDP server is running at the host: " + myAddress.getHost() + ", port: " + myAddress.getPort());
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error to open the datagram socket!");
            return false;
        }
        return true;
    }

    public boolean close() {
        try {
            myListening.stop();
            myServer.close();
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Error to close the datagram socket!");
            return false;
        }
        return true;
    }

    class UDPListening extends Thread {

        public UDPListening() {

        }

        public void run() {
            while (true) {
                try {
                    //prepare the buffer and fetch the received data into the buffer
                    byte[] receiveData = new byte[10000];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    myServer.receive(receivePacket);

                    //read incoming data from the buffer 
                    ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    ObjectWrapper receivedData = (ObjectWrapper) ois.readObject();

                    //processing
                    ObjectWrapper resultData = new ObjectWrapper();
                    switch (receivedData.getPerformative()) {
                        case ObjectWrapper.LOGIN_USER:              // login
                            Player player = (Player) receivedData.getData();
                            PlayerDAO pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_LOGIN_USER);
                            boolean ok = pd.checkLogin(player);
                            if (ok) {
                                //System.out.println(player.getIdPlayer()+ " " + player.getName());
                                ArrayList<Player> listFriend = new ArrayList<Player>();
                                listFriend = pd.getTblFriend(player);
                                listFriend.add(player);
                                resultData.setData(listFriend);
                            } else {
                                System.out.println("false");
                                resultData.setData("false");
                            }
                            break;

                        case ObjectWrapper.GET_REQUEST_FRIEND:              // login
                            player = (Player) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_GET_REQUEST_FRIEND);
                            ArrayList<Player> listRequest = new ArrayList<Player>();
                            listRequest = pd.getFriendRequest(player);
                            resultData.setData(listRequest);
                            break;

                        case ObjectWrapper.SEARCH_PLAYER_BY_NAME:
                            String name = (String) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_SEARCH_PLAYER_BY_NAME);
                            ArrayList<Player> listFriend = new ArrayList<Player>();
                            listFriend = pd.searchPlayer(name);
                            resultData.setData(listFriend);
                            break;

                        case ObjectWrapper.REGISTER_USER:
                            player = (Player) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_REGISTER_USER);
                            int check = pd.register(player);
                            if (check == 1) {
                                resultData.setData("1");
                            } else if (check == 2) {
                                resultData.setData("2");

                            } else if (check == 3) {
                                resultData.setData("3");
                            }
                            break;
                        case ObjectWrapper.CHANGE_PASSWORRD:
                            player = (Player) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_CHANGE_PASSWORRD);
                            ok = pd.changePassword(player);
                            if (ok) {
                                resultData.setData("ok");
                            } else {
                                resultData.setData("false");

                            }
                            break;
                        case ObjectWrapper.ADD_FRIEND:
                            ArrayList<Player> list = new ArrayList<Player>();
                            list = (ArrayList<Player>) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_ADD_FRIEND);
                            if (pd.addFriend(list)) {
                                resultData.setData("ok");
                            } else {
                                resultData.setData("false");
                            }
                            break;
                        case ObjectWrapper.YES_OPTION_FRIEND:
                            list = new ArrayList<Player>();
                            list = (ArrayList<Player>) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_YES_OPTION_FRIEND);
                            if (pd.yesOptionRequest(list)) {
                                resultData.setData("ok");
                            } else {
                                resultData.setData("false");
                            }
                            break;
                        case ObjectWrapper.NO_OPTION_FRIEND:
                            list = new ArrayList<Player>();
                            list = (ArrayList<Player>) receivedData.getData();
                            pd = new PlayerDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_NO_OPTION_FRIEND);
                            if (pd.noOptionRequest(list)) {
                                resultData.setData("ok");
                            } else {
                                resultData.setData("false");
                            }
                            break;
                        case ObjectWrapper.GAME_WIN:
//                            Game gameResult = new Game();
//                            gameResult = (Game) receivedData.getData();
//                            GameDAO gd = new GameDAO();
//                            resultData.setPerformative(ObjectWrapper.REPLY_GAME_WIN);
//                            if (gd.addGame(gameResult)) {
//                                resultData.setData("ok");
//                            } else {
//                                resultData.setData("false");
//                            }
                            Game game1 = new Game();
                            game1 = (Game) receivedData.getData();
                            GameDAO gd = new GameDAO();
                            PlayGameDAO pgd = new PlayGameDAO();
                            game1 = gd.addGame(game1);
                            resultData.setPerformative(ObjectWrapper.REPLY_GAME_WIN);
                            if (pgd.addPlayGameWinner(game1) && pgd.addPlayGameLoser(game1)) {
                                resultData.setData("ok");
                            } else {
                                resultData.setData("false");
                            }
                            break;
//                        case ObjectWrapper.ROOM_READY:
////                            ArrayList<PlayGame> pgame = new ArrayList<PlayGame>();
////                            pgame = (ArrayList<PlayGame>) receivedData.getData();
//                            //System.out.println("btnstart server udp test: " + pgame.get(1).getPlayer().getIdPlayer());
//
////                            PlayGameDAO pgd = new PlayGameDAO();
////                            resultData.setPerformative(ObjectWrapper.REPLY_ROOM_READY);
////                            resultData.setData(pgd.addPlayGame(pgame));
//                            Game game = new Game();
//                            game = (Game) receivedData.getData();
//                            
//                            resultData.setPerformative(ObjectWrapper.REPLY_ROOM_READY);
//                            resultData.setData(gd.addGame(game));
//                            break;
                        case ObjectWrapper.GET_HISTORY:
                            ArrayList<Game> listGame = new ArrayList<Game>();
                            player = (Player) receivedData.getData();
                            gd = new GameDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_GET_HISTORY);
                            resultData.setData(gd.getHistory(player));
                            break;
                        case ObjectWrapper.GET_DETAIL_HISTORY_WIN:
                            Game game2 = new Game();
                            game2 = (Game) receivedData.getData();
                            pgd = new PlayGameDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_GET_DETAIL_HISTORY);
                            resultData.setData(pgd.getDetailHistoryWin(game2));
                            break;
                        case ObjectWrapper.GET_DETAIL_HISTORY_LOSE:
                            game2 = new Game();
                            game2 = (Game) receivedData.getData();
                            pgd = new PlayGameDAO();
                            resultData.setPerformative(ObjectWrapper.REPLY_GET_DETAIL_HISTORY);
                            resultData.setData(pgd.getDetailHistoryLose(game2));
                            break;

                    }

                    //prepare the buffer and write the data to send into the buffer
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(resultData);
                    oos.flush();

                    //create data package and send
                    byte[] sendData = baos.toByteArray();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    myServer.send(sendPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showMessage("Error when processing an incoming package");
                }
            }
        }
    }
}
