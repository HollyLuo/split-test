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
public class CycleDetection {
    protected Graph graph;
    protected Set<String> visited;
    protected Set<String> exited;
    protected List<String> path;
    protected String startVertex;
    protected String endVertex;
    private List<String> candidatesName = new ArrayList<String>();
    private ArrayList<String> trace=new ArrayList<String>();//从出发节点到当前节点的轨迹
    private boolean hasCycle=false;  

   
    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        exited = new HashSet<>();
        path = new LinkedList<>();//路径
    }
    
    public String getStart() {
    	return this.startVertex;	
	}
    public String getEnd() {
    	return this.endVertex;	
	}
     
//    public List<String> exclude() {
//    	for(Vertex vertex : this.graph.getVertices()){
//    		if(vertex.getInNumber()>1 && vertex.getOutNumber()>1){
//    			candidatesName.add(vertex.getName());
//    		}
//    	}
//    	return candidatesName;
//	}
    
//    public boolean exclude(Vertex vertex) {
//    	if(vertex.getInNumber()<1 || vertex.getOutNumber()<1){
//    		return true;	
//    	}
//    	return false;
//    }  
    
    
//    public LinkedList<String> findCycle() {
//    	for(String name : this.graph.getVerticesName()){
//    		Vertex vertex = graph.findVertex(name);
//    		if(!exclude(vertex)){
//    			trace.add(name);
//    			startVertex = name;
//    		}
//    	}
//    	return null;
//		
//	}
    
    public boolean hasCycle(String name) {
    	if (!visited.contains(name) && !dfs(name)){
            return false;
        }
    	return true;	
	}
    
    public boolean findCycle() {
        for (String name : this.graph.getVerticesName()) {
//        	System.out.println("IfhasCycle: "+name);
            if(hasCycle(name)){
            	return true;
            }
        } 
        return true;
    }
    
    private boolean dfs(String start) {
        visited.add(start); 
//        System.out.print("visited list: " );
//        for(String s: visited){	
//        	System.out.print(s +" ");
//        }
//        System.out.println();
        for (String neighbor : graph.findVertex(start).getNeighborsName()) {       	
//        	System.out.println("neighbor: " +neighbor);
        	//已经访问过，并且。。。
            if (visited.contains(neighbor) && !exited.contains(neighbor)){
            	startVertex = neighbor;//起点，返回到的点
            	endVertex = start;
//            	System.out.println("start: "+startVertex);
//            	System.out.println("end: "+endVertex);
                return true;
            }
            if (!visited.contains(neighbor) && dfs(neighbor)){
//            	System.out.println("2"+neighbor);
                return true;
            }
        }
        exited.add(start);
        System.out.println("exited: " +start);
        return false;
    }
}

//void findCycle(String name)   //递归DFS  
//{  
//  if(visited.contains(name))  
//  {  
//      int j;  
//      if((j=trace.indexOf(name))!=-1)  
//      {  
//          hasCycle=true;  
//          System.out.print("Cycle:");  
//          while(j<trace.size())  
//          {  
//              System.out.print(trace.get(j)+" ");  
//              j++;  
//          }  
//          System.out.print("\n");  
//          return;  
//      }  
//      return;  
//  }  
//  visited.add(name);  
//  trace.add(name);  
//    
//  for(int i=0;i<n;i++)  
//  {  
//      if(e[v][i]==1)  
//          findCycle(i);  
//  }  
//  trace.remove(trace.size()-1);  
//}  
