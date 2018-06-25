package ua.nure.veretelnyk.web;

import ua.nure.veretelnyk.Message;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

/**
 * Tag , that is empty by default and
 * writing that everything is good
 */
public class AllGoodTag extends SimpleTagSupport{
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println(Message.EVERYTHING_IS_FINE);
    }
}
