package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitBehaviorChain {
	public static String readFile(String filename) throws IOException {
		String message = null;
        
        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        message = br.readLine();
 
//        String str = null;
       
//        while((str = br.readLine()) != null) {
//              message=str;
//        }
        br.close();
        reader.close();
        return message;
	}
	
	public  static void runAlgorithm(String inputFile) throws Exception  {
		String ids = readFile(inputFile);
		String behaviorList[] = ids.split(",");
		ArrayList<String> behaviorList2= new ArrayList<>();
		
//		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < behaviorList.length; i++){
			behaviorList2.add(behaviorList[i]);
		}
		String inputString = ids.toString();
		System.out.println("behavior chain: "+ inputString);
		System.out.println("");
//		System.out.println("--------------scan the behavior chain-----------------");
		
		Vertex current;
		Vertex next;
		Vertex v;
		Edge edge;
		ArrayList<String> nameList = new ArrayList<>();
		
		Map<String, Vertex> map = new HashMap<String, Vertex>();
		
		Graph graph = new Graph();
			
		for(int i=0;i<behaviorList.length;i++){
			v = (Vertex) map.get(behaviorList[i]);
			if (v == null){
				v = new Vertex();	
				v.setName(behaviorList[i]);
				map.put(behaviorList[i], v);
				nameList.add(behaviorList[i]);
				
			}
		}
		
		for(int i=0;i<behaviorList.length-1;i++){
			current = (Vertex) map.get(behaviorList[i]);
			if(i!=0){
				current.addInNumber();
			}
			if(i!=behaviorList.length){
				current.addOutNumber();
			}
			next = (Vertex) map.get(behaviorList[i+1]);
		
			edge = new Edge(current, next);
			
			if(current.hasEdge(edge)!=null){
				current.hasEdge(edge).addWeight();
			}else {
				edge.addWeight();
				current.setEdge(edge);
				current.setNeighbour(next);
				current.setNeighbourName(behaviorList[i+1]);
			}
		}
		
		for(int i=0;i<map.size();i++){		
			v = (Vertex) map.get(nameList.get(i));
//			v.printVertex();
			graph.addVertex(v);
		}
		
		// 判断环		
		CycleDetection cycleDetection = new CycleDetection(graph);
		System.out.println("--------------DFS cycle detection-----------------");
		cycleDetection.findCycle();
//		System.out.println("Cycle exist: "+cycleDetection.findCycle());
		String start = cycleDetection.getStart();
		String end = cycleDetection.getEnd();
//		String start = "A";
//		String end = "E";
		System.out.println("cycle start: "+start);
		System.out.println("cycle end: "+end);
		
		//"X,Y,A,B,C,D,E,A,B,C,D,E,A,M,N,E,A,M,N,E,A,B,C,D,E,A,M,N,E"
//		List<ArrayList<Integer>> pairList = new ArrayList<ArrayList<Integer>>();
		
		
		
//        String targetString = inputString;  
//        StringBuilder outputStringBuilder = new StringBuilder(); 
//        String outputString;  
//        while (targetString.length() > 0 && targetString.indexOf(start,1) > 0) { 
//        	System.out.println(targetString.length());
//        	System.out.println(targetString.indexOf(start,1));
//            outputString = targetString.substring(0, targetString.indexOf(start,1));
//            targetString = targetString.substring(targetString.indexOf(start,1));
//            System.out.println(targetString);
//            outputStringBuilder.append(outputString);
//            outputStringBuilder.append("\n");
//        }
//        
//        System.out.println(outputStringBuilder.toString());
		System.out.println("--------------behavior split-----------------");
		ArrayList<Integer> pair= new ArrayList<>();
		ArrayList<Integer> nopair= new ArrayList<>();
		ArrayList<String> patt= new ArrayList<>();
		int tag=-1;
		ArrayList<ArrayList<String>> pattList = new ArrayList<ArrayList<String>>();
//		boolean match=f;
		int a=0;
		
		for(int i=0;i<behaviorList.length;i++){
				if(behaviorList[i].equals(start)){
//					System.out.println("("+(tag+1) + " "+i+")");				
					if(i!=tag+1){
						for(int j=i;j<tag+1;j++){
							patt.add(behaviorList[j]);
						}
						ArrayList<String> patt2= new ArrayList<>();
						patt2.addAll(patt);
						pattList.add(patt2);
						System.out.println("nopair: "+patt.toString());
						patt.clear();
					}
//					String string = teString.substring(pair.get(0), pair.get(1)+1);
					pair.add(i);	
				}
				
				if(behaviorList[i].equals(end)){
					pair.add(i);
					tag=i;
					a+=1;
//					System.out.println("pair: "+pair.toString());
					
					for(int j=pair.get(0);j<pair.get(1)+1;j++){
						patt.add(behaviorList[j]);
					}
					ArrayList<String> patt2= new ArrayList<>();
					patt2.addAll(patt);
					pattList.add(patt2);
					System.out.println(a+": "+patt.toString());
//					System.out.println("pattlist: "+pattList.toString());
					pair.clear();
					patt.clear();
				}
				
		}	
//		System.out.println(pattList.size());
//		System.out.println(pattList.get(0).size());
		String path="/Users/ling/Documents/Eclipseworkspace/Weka/test/src/tree/output.txt";
		writeFileContext(pattList,path);
		
		
		//path
//		DFS dfs = new DFS(graph);
//		List<String> path2 = new ArrayList<String>();
//		path2 = dfs.getPathFrom("A");
//		if(path2!=null){
//			for(int i=0;i<path2.size();i++){
//				System.out.println(path2.get(i));
//			}
//		}
	}
	public static void writeFileContext(List<ArrayList<String>>  pattList, String path) throws Exception {
		File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i=0;i<pattList.size();i++){
        	ArrayList<String> arrayList = pattList.get(i);
        	for(int j=0;j<arrayList.size();j++){
        		writer.write(arrayList.get(j) + " " + "-1"+" ");
        	}
        	writer.write("-2"+"\n");
            
        }
        writer.close();
    }

}
