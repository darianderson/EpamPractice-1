package ua.nure.veretelnyk.part4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {
    private Set<Vertex> vertices;

    public Graph(){
        vertices = new HashSet<>();
    }
    public Graph(int n) { vertices = new HashSet<>(n); }

    public boolean add(int nodeNo, int[] connectedTo){
        Vertex vertex = new Vertex(nodeNo);

        vertices.add(vertex);
        for(Vertex v : vertices)
            if (v.equals(vertex))
                vertex = v;

        for(int connected : connectedTo)
            vertices.add(new Vertex(connected));

        for(Vertex v : vertices){
            for(int connected : connectedTo){
                if (v.getId() == connected) {
                    vertex.addConnected(v);
                    v.addConnected(vertex);
                }
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
