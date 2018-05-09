package ua.nure.veretelnyk.practice6.part4;

import java.util.*;

public class Graph {
    private Set<Vertex> vertices;

    public Graph(){
        vertices = new HashSet<>();
    }

    public boolean add(int nodeNo, int[] connectedTo){
        if (vertices.contains(new Vertex(nodeNo)))
            return false;
        Vertex vertex = new Vertex(nodeNo);
        for(int connected : connectedTo)
            vertices.add(new Vertex(connected));

        for(Vertex v : vertices){
            for(int connected : connectedTo){
                if (v.getId() == connected)
                    vertex.addConnected(v);
            }
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Vertex v : vertices){
            Iterator<Vertex> it = v.iterator();
            while (it.hasNext())
                sb.append(it.next().getId()).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
