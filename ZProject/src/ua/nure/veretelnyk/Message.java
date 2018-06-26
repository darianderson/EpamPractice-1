package ua.nure.veretelnyk;

import java.util.ResourceBundle;

public class Message {
    private static final String RES_STR = "resources";
    public static ResourceBundle res = ResourceBundle.getBundle(RES_STR);


    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    public static final String WRONG_CARRIAGE = res.getString("msg.wrong_carriage");
    public static final String WRONG_PLACE = res.getString("msg.wrong_place");
    public static final String NOT_LOGIN = res.getString("msg.not_log_in");
    public static final String ALREADY_BUY_THIS_TICKET = res.getString("msg.already_buy_ticket");
    public static final String WOOPS = res.getString("msg.woops");
    public static final String EVERYTHING_IS_FINE = res.getString("msg.everything_is_fine");
    public static final String NO_SUCH_COMMAND = res.getString("msg.no_such_command");
    public static final String WRONG_USERNAME = res.getString("msg.wrong_username");
    public static final String WRONG_INPUT = res.getString("msg.wrong_input");
    public static final String PASSWORD_DONOT_MATCH = res.getString("msg.password_donot_match");
    public static final String YOU_ALREADY_REG = res.getString("msg.you_already_reg");
    public static final String DATA_IS_SAVED = res.getString("msg.data_is_saved");


}

