package tree;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.sun.glass.ui.TouchInputSupport;

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
//    protected List<String> path;
    protected String startVertex;
    protected String endVertex;
//    private List<String> candidatesName = new ArrayList<String>();
    private ArrayList<String> trace=new ArrayList<String>();//从出发节点到当前节点的轨迹
    private boolean hasCycle=false;  
    private int verticesNumber;
    private String oriString;
    

   
    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        exited = new HashSet<>();
        trace = new ArrayList<>();//路径
        verticesNumber = this.graph.getVerticesNumber();
    }
    public CycleDetection(String inputString,Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        exited = new HashSet<>();
        trace = new ArrayList<>();//路径
        verticesNumber = this.graph.getVerticesNumber();
        oriString = inputString;
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
    
//    public boolean hasCycle(String name) {
//    	if (!visited.contains(name) && !dfs(name)){
//            return false;
//        }
//    	return true;	
//	}
    
  
    public void hasCycle() {
        findCycle(this.graph.getVerticesName(0));
        System.out.println(this.graph.getVerticesName(0));
        if(!hasCycle)
            System.out.println("No Cycle.");
    }
    
    public void findCycle(String v)
    {
       int j=0;   
       if((j=this.trace.indexOf(v))!=-1)
       {
//    	  System.out.print("j:"+j);
           hasCycle=true;  
           String first  = trace.get(j);//1231
        	   ArrayList<String> cycle = new ArrayList<>();
//        	   String cycle="#";
        	   System.out.print("Cycle:");
        	   
        	   while(j<trace.size())
               {
                  System.out.print(trace.get(j)+" ");
                  cycle.add(trace.get(j));
//                  cycle+=trace.get(j);
//                  cycle +=",";
                  j++;
               }
//        	   convertToString(cycle);
               //calculate the trace number 
//               System.out.print(" size:"+trace.size());
//        	   System.out.print( " toFind: "+cycle.toString());
//        	   System.out.print(cycle);
        	   cycle.add(first);//1231
        	   System.out.print(" cycle number:" + countCycleNumber(convertToString(cycle)));
               System.out.println();
               return;
           
        }
        this.trace.add(v);
         
        for(int i=0;i<verticesNumber;i++)
        {
            if(graph.findVertex(v).hasNeighbour(this.graph.getVerticesName(i)))
                findCycle(this.graph.getVerticesName(i));
        }
        trace.remove(trace.size()-1);
    }
    
    private String convertToString(ArrayList<String> cycle) {
		// TODO Auto-generated method stub
    	String string = cycle.get(0);
    	for (int i=0;i<cycle.size();i++){
    		if(i==0){
    			string = string +",";
    		}else if(i==cycle.size()-1){
    			string += cycle.get(i);
    		}else {
    			string+=cycle.get(i);
        		string+=",";
			}
    	}
    	return string;
		
	}
	private int countCycleNumber(String sToFind) {
//    	String a[] = s.split("#");
//    	String sToFind = a[1];
//    	System.out.println("sToFind: " + sToFind);
        int num = 0;
        String newString = this.oriString;
//        System.out.println("newString: "+newString);
        while (newString.contains(sToFind)) {
//        	System.out.println("indexOf: "+this.oriString.indexOf(sToFind));
        	newString = newString.substring(newString.indexOf(sToFind) + sToFind.length());
//        	System.out.println("newString: "+newString);
            num ++;
        }
        return num;
    }
      

//	private void countCycleNumber(ArrayList<String> cycle) {
//		// TODO Auto-generated method stub
//		
//		
//	}
//    
//    public boolean findCycle() {
//        for (String name : this.graph.getVerticesName()) {
////        	System.out.println("IfhasCycle: "+name);
//            if(hasCycle(name)){
//            	return true;
//            }
//        } 
//        return true;
//    }
    
//    private boolean dfs(String start) {
//        visited.add(start); 
////        System.out.print("visited list: " );
////        for(String s: visited){	
////        	System.out.print(s +" ");
////        }
////        System.out.println();
//        for (String neighbor : graph.findVertex(start).getNeighborsName()) {       	
////        	System.out.println("neighbor: " +neighbor);
//        	//已经访问过，并且。。。
//            if (visited.contains(neighbor) && !exited.contains(neighbor)){
//            	startVertex = neighbor;//起点，返回到的点
//            	endVertex = start;
////            	System.out.println("start: "+startVertex);
////            	System.out.println("end: "+endVertex);
//                return true;
//            }
//            if (!visited.contains(neighbor) && dfs(neighbor)){
////            	System.out.println("2"+neighbor);
//                return true;
//            }
//        }
//        exited.add(start);
//        System.out.println("exited: " +start);
//        return false;
//    }
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
