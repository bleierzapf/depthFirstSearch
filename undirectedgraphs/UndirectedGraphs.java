package undirectedgraphs;

public class UndirectedGraphs {
    public static void main(String[] args) {
        Graph g = createGraph();
        System.out.println(g);

        System.out.println(g.depthFirstSearch(g, new Vertex("Maria")));

        //System.out.println("the found and handled vertices:");
        //System.out.println(g.genericSearch(g, new Vertex("Bob")));
    }

    private static Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addVertex("Anthony");
        graph.addVertex("George");
        
        graph.addEdge("Anthony", "George");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        graph.addEdge("Bob", "George");
        graph.addEdge("Maria", "Anthony");
        return graph;
    }
}
