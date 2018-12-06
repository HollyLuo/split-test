//package tree;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import tree.Vertex;
//import tree.Edge;
//
//
//public class test2 {
//	public static void main(String[] args) throws Exception {
//		String ids = "X,Y,A,B,C,D,E,A,M,N,E,A,M,N,E,A,M,N,E,A,B,C,D,E";
//		String behaviorList[] = ids.split(",");
////		
//		System.out.println(behaviorList);
//		
//		Vertex pre;
//		Map<String, Vertex> map = new HashMap<String, Vertex>();
//		
//		for(int i=0;i<behaviorList.length;i++){
//			pre = (Vertex) map.get(behaviorList[i]);
//			if (pre == null){
//				pre = new Vertex();	
//				pre.setName(behaviorList[i]);
//				System.out.println(behaviorList[i]);
//				map.put(behaviorList[i], pre);
//			}
//		}
//		
//		for(int i=0;i<map.size();i++){
//			System.out.println(map.get(i));
//		}
//		
//	}
//
//}
//
//
//for(int i=0;i<map.size();i++){
//	v = (Vertex) map.get(behaviorList[i]);
//	System.out.println(v.getName());//X	
//	for(int j=0;j<behaviorList.length-1;j++){
//		System.out.println(behaviorList[j]);//X	
//		if(behaviorList[j]==v.getName()){
//			System.out.println(v.getName());//X	
//			currentNeighbors.add(map.get(behaviorList[j+1]));
//			v.setNext(map.get(behaviorList[j+1]));
//		}
//	}
//}
