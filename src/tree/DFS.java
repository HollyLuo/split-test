package tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import tree.Graph;

public class DFS {
    protected Graph graph;
    protected Set<String> visited;
    protected List<String> path;

    public DFS(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        path = new LinkedList<>();
    }

    public void search(String name) {
        visited.add(name);
        processVertex(name);

        for (String neighbor : graph.findVertex(name).getNeighborsName())
        {
            if (!visited.contains(neighbor)) {
                search(neighbor);
            }
        }
    }

    public void processVertex(String name) {
        path.add(name);
    }

    public void searchIterative(String source) {
        if (source == null || !graph.getVertices().contains(source)) return;

        visited.clear();
        path.clear();

        Stack<String> stack = new Stack<>();
        stack.add(source);

        while (!stack.isEmpty()){
            String v = stack.pop();

            visited.add(v);
            processVertex(v);

            for (String neighbor : graph.findVertex(v).getNeighborsName()) {
                if (!visited.contains(neighbor))
                    stack.add(neighbor);
            }
        }

    }

    public List<String> getPathFrom(String source) {
        if (source == null || graph.findVertex(source).getName()==null){
        	System.out.println("null");
            return null;}
        search(source);
        return path;
    }


}
