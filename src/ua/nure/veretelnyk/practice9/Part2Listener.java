package ua.nure.veretelnyk.practice9;

import ua.nure.veretelnyk.practice9.part2_2.SportRow;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String[] sports = servletContextEvent.getServletContext().getInitParameter("list").split(" ");

        List<SportRow> list2 = new ArrayList<>();
        for (String s : sports)
            list2.add(new SportRow(s));
        servletContextEvent.getServletContext().setAttribute("list2", list2);

        List<SportRow> list3 = new ArrayList<>();
        for (String s : sports)
            list3.add(new SportRow(s));
        servletContextEvent.getServletContext().setAttribute("list3", list3);

        Map<String, Integer> map = new HashMap<>();
        for (String s : sports)
            map.put(s,0);
        servletContextEvent.getServletContext().setAttribute("map", map);
        System.out.println("contextInit");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroy");
    }
}
