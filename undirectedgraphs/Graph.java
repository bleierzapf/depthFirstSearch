package undirectedgraphs;

import java.util.*;

public class Graph {

    private Map<Vertex, ArrayList<Vertex>> adjVertices = new HashMap<>();

    // add an element to the vertices set
    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    // remove an element from the vertices set
    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    // add an edge to the graph
    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    // remove an edge from the graph
    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null) {
            eV1.remove(v2);
        }
        if (eV2 != null) {
            eV2.remove(v1);
        }
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    Set<Vertex> genericSearch(Graph g, Vertex s) {
        Set<Vertex> foundHandled = new HashSet<>();
        Set<Vertex> foundNotHandled = new HashSet<>();
        foundNotHandled.add(s);

        // exit when foundNotHandled is empty
        Iterator<Vertex> it = foundNotHandled.iterator();
        while (it.hasNext()) {
            // let u be some node from foundNotHandled
            Vertex u = it.next();

            if (!foundHandled.contains(u)) {
                // for each v connected to u
                List<Vertex> vlist = adjVertices.get(u);
                for (Vertex v : vlist) {
                    foundNotHandled.add(v);
                }

                // move u from foundNotHandled to foundHandled
                foundHandled.add(u);
            }
            foundNotHandled.remove(u);
            it = foundNotHandled.iterator();
        }
        return foundHandled;
    }

    Set<Vertex> depthFirstSearch(Graph graph, Vertex v)
    {
        Set<Vertex> foundHandled = new HashSet<>(); // Holds Nodes completed during search
        Stack<Vertex> foundNotHandled = new Stack<>(); // Holds Nodes no completed during search
        foundNotHandled.push(v); // Push starting vertex onto stack

        // Continues looping until no values left in notHandled stack
        while (!foundNotHandled.empty())
        {
            // Pops top value off notHandled stack
            v = foundNotHandled.pop();

            // Checks if current Node is in found stack, if not adds to stack as it has been visited
            if(!foundHandled.contains(v)) {
                foundHandled.add(v);
            }

            // Collects all vertices attached to current Node
            List<Vertex> adj = getAdjVertices(v.toString());

            /* Iterates through vertices list and checks if that Node has been found.
             * If Node has not been found adds to found list.
             */
            for (int i = 0; i < adj.size(); i++) {
                if (!foundHandled.contains(adj.get(i))) {
                    foundNotHandled.push(adj.get(i));
                }
            }
        }
        // Returns found stack
        return foundHandled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Vertex, ArrayList<Vertex>> entry : adjVertices.entrySet()) {
            sb.append(entry.getKey()).append("->")
                    .append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
