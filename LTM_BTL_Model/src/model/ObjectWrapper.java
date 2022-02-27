package model;

import java.io.Serializable;

public class ObjectWrapper implements Serializable {

    private static final long serialVersionUID = 20210811011L;
    public static final int LOGIN_USER = 1;
    public static final int REPLY_LOGIN_USER = 2;
    public static final int EDIT_CUSTOMER = 3;
    public static final int TABLE_FRIEND_UPDATE = 4;
    public static final int REPLY_TABLE_FRIEND_UPDATE = 5;
    public static final int LOGOUT_USER = 6;
    public static final int REPLY_LOGOUT_USER = 7;
    public static final int REGISTER_USER = 8;
    public static final int REPLY_REGISTER_USER = 9;
    public static final int CHANGE_PASSWORRD = 10;
    public static final int REPLY_CHANGE_PASSWORRD = 11;
    public static final int ADD_FRIEND = 12;
    public static final int REPLY_ADD_FRIEND = 13;
    public static final int GET_REQUEST_FRIEND = 14;
    public static final int REPLY_GET_REQUEST_FRIEND = 15;
    public static final int YES_OPTION_FRIEND = 16;
    public static final int REPLY_YES_OPTION_FRIEND = 17;
    public static final int NO_OPTION_FRIEND = 18;
    public static final int REPLY_NO_OPTION_FRIEND = 19;
    public static final int SEARCH_PLAYER_BY_NAME = 20;
    public static final int REPLY_SEARCH_PLAYER_BY_NAME = 21;
    public static final int FIGHT = 22;
    public static final int REPLY_FIGHT = 23;
    public static final int REPLY_FIGHT_TO = 24;
    public static final int FIGHT_YES = 25;
    public static final int FIGHT_NO = 26;
    public static final int REPLY_FIGHT_YES = 27;
    public static final int REPLY_FIGHT_YES_TO = 28;
    public static final int REPLY_FIGHT_NO = 29;
    public static final int REPLY_FIGHT_NO_TO = 30;
    public static final int ROOM_READY = 31;
    public static final int REPLY_ROOM_READY = 32;
    public static final int ROOM_READY_TO = 33;
    public static final int GAME_WIN = 34;
    public static final int REPLY_GAME_WIN = 35;
    public static final int REPLY_GAME_WIN_TO = 36;
    public static final int GAME_END = 37;
    public static final int REPLY_GAME_END = 38;
    public static final int GET_HISTORY = 39;
    public static final int REPLY_GET_HISTORY = 40;
    public static final int GET_DETAIL_HISTORY_WIN = 41;
    public static final int GET_DETAIL_HISTORY_LOSE = 42;
    public static final int REPLY_GET_DETAIL_HISTORY = 43;

    private int performative;
    private Object data;

    public ObjectWrapper() {
        super();
    }

    public ObjectWrapper(int performative, Object data) {
        super();
        this.performative = performative;
        this.data = data;
    }

    public int getPerformative() {
        return performative;
    }

    public void setPerformative(int performative) {
        this.performative = performative;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
