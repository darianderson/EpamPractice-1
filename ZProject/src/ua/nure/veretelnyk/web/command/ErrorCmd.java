package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCmd extends Command {

    private static final  Logger LOG = Logger.getLogger(ErrorCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("ErrorCmd starts");

        String error = Message.NO_SUCH_COMMAND;
        request.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE,  error);
        LOG.error(error);

        LOG.debug("ErrorCmd finished");

        return Path.PAGE_ERROR;
    }
}
