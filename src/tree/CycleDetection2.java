package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tree.Graph;

/**
 * To find if the given graph has cycle , We need to maintain one additional set of exited vertices and do dfs.
 * In any step if iteration if a vertex is already in visited set and yet not exited from recusion stack, then
 * we can return true. We can optimize using one array with 3 different values.
 */
public class CycleDetection2 {
    protected Graph graph;
    protected Set<String> visited;
    protected Set<String> exited;
    protected List<String> path;
    protected String startVertex;
    protected String end;

    

    public CycleDetection2(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        exited = new HashSet<>();
        path = new LinkedList<>();
    }

    public boolean hasCycle() {
        for (String name : this.graph.getVerticesName()) {
        	//System.out.println("hasCycle: "+name);
            if (!visited.contains(name) && !dfs(name))
                return false;
        }
        
        return true;
    }

    private boolean dfs(String start) {
        visited.add(start);       
        System.out.println("visited: " +start);
        for (String neighbor : graph.findVertex(start).getNeighborsName()) {
        	System.out.println("neighbor: " +neighbor);
        	//已经访问过，并且。。。
            if (visited.contains(neighbor) && !exited.contains(neighbor)){
            	startVertex = neighbor;//起点，返回到的点
            	end = start;
            	System.out.println("start: "+startVertex);
            	System.out.println("end: "+end);
                return true;
            }
            if (!visited.contains(neighbor) && dfs(neighbor)){
            	System.out.println("2"+neighbor);
                return true;
            }
        }
        exited.add(start);
        System.out.println("exited: " +start);
        return false;
    }
}
