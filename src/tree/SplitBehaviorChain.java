package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
	
	public  static void runAlgorithm(String inputFile, float support) throws Exception  {
		String ids = readFile(inputFile);
		String behaviorList[] = ids.split(",");
		ArrayList<String> behaviorList2= new ArrayList<>();
//		String oriInputString = 
		
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
			
			if(current.getEdge(edge)!=null){
				current.getEdge(edge).addWeight();
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
//		System.out.println(graph.getVerticesName());
		// 判断环		
		
		System.out.println("--------------Cycle Detection-----------------");
		ArrayList<Cycle> cycleList = new ArrayList<>();
		CycleDetection cycleDetection = new CycleDetection(inputString,graph);
		cycleDetection.hasCycle();
		String start  = cycleDetection.getStart();
		
		System.out.println("--------------Behavior split-----------------");
		ArrayList<List<String>> pattList = new ArrayList<List<String>>();
	    pattList =  SplitInputStringByStartVertex(inputString,start);
	    int size = pattList.size();
	    
	    System.out.println("--------------All Pattern -----------------");
	    Map<List<String>, Integer> map2 = new HashMap<List<String>, Integer>();
	    map2 = CountDuplicatedList(pattList);
//	    System.out.println("\nMap排序-以key排序");

	    List<Map.Entry<List<String>, Integer>> list = new ArrayList<Map.Entry<List<String>, Integer>>(map2.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<List<String>, Integer>>() {
			@Override
			public int compare(Entry<List<String>, Integer> o1, Entry<List<String>, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
        });
        printList(list);
        System.out.println();
        System.out.println("--------------Frequecy Pattern -----------------");
        Map<List<String>, Integer> map3 = new HashMap<List<String>, Integer>();
        map3 = getFrequencyPattern(map2,size,support);
        printMap(map3);
		
		String path="/Users/ling/Documents/Eclipseworkspace/Weka/pattern/ca/pfv/spmf/test/contextPrefixSpan3.txt";
//		String path="/Users/ling/Documents/Eclipseworkspace/Weka/test/src/test/behavor2.txt";
		writeFileContext(pattList,path);	
	}
	
	private static Map<List<String>, Integer> getFrequencyPattern(Map<List<String>, Integer> map2, int size, float support) {
		Map<List<String>, Integer> map = new HashMap<List<String>, Integer>();
//		System.out.println(size);
//		System.out.println(support);
		
		for (Map.Entry<List<String>, Integer> entry : map2.entrySet()) { 
//			System.out.println(entry.getValue());
			float a= 0.0f;
			a = (float)entry.getValue()/(float)size;
			if(a >= support){
				map.put(entry.getKey(), entry.getValue());
			}
		} 
		
		return map;
	}

	

	private static void printMap(Map<List<String>, Integer> map) {
		for (Map.Entry<List<String>, Integer> entry : map.entrySet()) { 
			   System.out.println("Pattern = " + entry.getKey() + ", Weight = " + entry.getValue()); 
			 } 
		
	}
	private static void printList(List<Entry<List<String>, Integer>> list) {
      for(Map.Entry<List<String>,Integer> mapping:list){ 
    	  System.out.println("Pattern = " + mapping.getKey() + ", Weight = " + mapping.getValue()); 
      } 
	}

	private static Map<List<String>, Integer> CountDuplicatedList(ArrayList<List<String>> pattList) {
		Map<List<String>, Integer> map2 = new HashMap<List<String>, Integer>();
		for (List<String> item : pattList) {
			if (map2.containsKey(item)) {
				map2.put(item, map2.get(item).intValue() + 1);
			} else {
				map2.put(item, new Integer(1));
			}
		}
		Iterator<List<String>> keys = map2.keySet().iterator();
		while (keys.hasNext()) {
			List<String> key = keys.next();
//			System.out.println(key + ":" + map2.get(key).intValue() + ", ");
		}
		return map2;
	}

	private static ArrayList<List<String>> SplitInputStringByStartVertex(String oriString, String start) {
		 ArrayList<List<String>> pattList = new ArrayList<List<String>>();
		  String behaviorList2[] = oriString.split(",");
		  
		  ArrayList<String> behaviorList= new ArrayList<>();
		  
		  
		  for(int i = 0; i < behaviorList2.length; i++){
			 behaviorList.add(behaviorList2[i]);
		  }

		  ArrayList<Integer> findStart = new ArrayList<>();

		  for(int i=0;i<behaviorList.size();i++){
			  if(behaviorList.get(i).equals(start)){			
				  findStart.add(i);
//				  System.out.println(i);
			  }
		  }	  

		  for(int i=0;i<findStart.size()-1;i++){
			  List<String> behavior = new ArrayList<>();
			  if(i==0 && (findStart.get(i)!=0)){
				  behavior = behaviorList.subList(0, findStart.get(i));
				  pattList.add(behavior);
				  System.out.println(behavior);
			  }
			  behavior = behaviorList.subList(findStart.get(i), findStart.get(i+1));
			  pattList.add(behavior);
			  System.out.println(behavior);
		  }
		  if(findStart.get(findStart.size()-1) < behaviorList.size()){
			  List<String> behavior = new ArrayList<>();
			  behavior = behaviorList.subList(findStart.get(findStart.size()-1),behaviorList.size());
			  pattList.add(behavior);
			  System.out.println(behavior);
		  }
		  return pattList;
	}
	
	public static void writeFileContext(ArrayList<List<String>> pattList, String path) throws Exception {
		File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i=0;i<pattList.size();i++){
        	List<String> arrayList = pattList.get(i);
        	for(int j=0;j<arrayList.size();j++){
        		writer.write(arrayList.get(j) + " " + "-1"+" ");
        	}
        	writer.write("-2"+"\n");
            
        }
        writer.close();
    }
		
		
		
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
//		System.out.println("--------------behavior split-----------------");
//		ArrayList<Integer> pair= new ArrayList<>();
//		ArrayList<Integer> nopair= new ArrayList<>();
//		ArrayList<String> patt= new ArrayList<>();
//		int tag=-1;
//		ArrayList<ArrayList<String>> pattList = new ArrayList<ArrayList<String>>();
////		boolean match=f;
//		int a=0;
//		
//		
//		for(int i=0;i<behaviorList.length;i++){
//
//				if(behaviorList[i].equals(start)){
////					System.out.println("("+(tag+1) + " "+i+")");				
//					if(i!=tag+1){
//						for(int j=tag+1;j<i;j++){
//							patt.add(behaviorList[j]);
//						}
//						ArrayList<String> patt2= new ArrayList<>();
//						patt2.addAll(patt);
//						pattList.add(patt2);
//						a+=1;
//						System.out.println(a+": "+patt.toString());
//						patt.clear();
//					}
////					String string = teString.substring(pair.get(0), pair.get(1)+1);
//					pair.add(i);	
//				}
//				
//				if(behaviorList[i].equals(end)){
//					pair.add(i);
//					tag=i;
//					a+=1;
////					System.out.println("pair: "+pair.toString());
//					
//					for(int j=pair.get(0);j<pair.get(1)+1;j++){
//						patt.add(behaviorList[j]);
//					}
//					ArrayList<String> patt2= new ArrayList<>();
//					patt2.addAll(patt);
//					pattList.add(patt2);
//					System.out.println(a+": "+patt.toString());
////					System.out.println("pattlist: "+pattList.toString());
//					pair.clear();
//					patt.clear();
//				}
//				
//				
//				
//		}	
////		System.out.println(pattList.size());
////		System.out.println(pattList.get(0).size());
//		String path="/Users/ling/Documents/Eclipseworkspace/Weka/test/src/tree/output.txt";
//		writeFileContext(pattList,path);
//		
//		
//		//path
////		DFS dfs = new DFS(graph);
////		List<String> path2 = new ArrayList<String>();
////		path2 = dfs.getPathFrom("A");
////		if(path2!=null){
////			for(int i=0;i<path2.size();i++){
////				System.out.println(path2.get(i));
////			}
////		}
//	}
//	public static void writeFileContext(List<ArrayList<String>>  pattList, String path) throws Exception {
//		File file = new File(path);
//        //如果没有文件就创建
//        if (!file.isFile()) {
//            file.createNewFile();
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
//        for (int i=0;i<pattList.size();i++){
//        	ArrayList<String> arrayList = pattList.get(i);
//        	for(int j=0;j<arrayList.size();j++){
//        		writer.write(arrayList.get(j) + " " + "-1"+" ");
//        	}
//        	writer.write("-2"+"\n");
//            
//        }
//        writer.close();
//    }

}
