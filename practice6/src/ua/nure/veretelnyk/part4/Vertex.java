package ua.nure.veretelnyk.part4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Vertex {
    private int id;
    private Set<Vertex> connected;
    public Vertex(int id) {
        connected = new HashSet<>();
        this.id = id;
    }

    public void addConnected(Vertex v) { connected.add(v); }

    public Iterator<Vertex> iterator(){ return connected.iterator(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ( !(obj instanceof Vertex)) return false;

        return ((Vertex) obj).id == this.id;
    }

    @Override
    public int hashCode() { return id; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
