//package tree;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import sun.nio.fs.MacOSXFileSystemProvider;
//import tree.Vertex;
//import tree.Edge;
//
//
//public class test3 {
//	public static void main(String[] args) throws Exception {
//		String ids = "X,Y,A,B,C,D,E,A,M,N,E,A,M,N,E,A,M,N,E,A,B,C,D,E";
//		String behaviorList[] = ids.split(",");
//
//		System.out.println(behaviorList);
//		
//		Vertex pre;
//		Vertex v;
//		Map<String, Vertex> map = new HashMap<String, Vertex>();
//		List<Vertex> currentNeighbors  = new ArrayList<Vertex>();
//		
//		for(int i=0;i<behaviorList.length;i++){
//			pre = (Vertex) map.get(behaviorList[i]);
//			if (pre == null){
//				pre = new Vertex();	
//				pre.setName(behaviorList[i]);
//				map.put(behaviorList[i], pre);
//			}
//		}
//		System.out.println(map.size());//9
//		for(int i=0;i<map.size();i++){
//			System.out.println(behaviorList[i]);//X
//			v = (Vertex) map.get(behaviorList[i]);
//			System.out.println(behaviorList.length);//24
//			for(int j=0;j<behaviorList.length-1;j++){
//				System.out.println(behaviorList[j]);//X
//				System.out.println(v.getName());//X
//				
//				if(behaviorList[j]==v.getName()){
////					
////					Vertex vit;
////					vit = map.get(behaviorList[j+1]);
////					if (vit == null)
////						vit = new Vertex();
////					vit.setName(behaviorList[j+1]);
//					currentNeighbors.add(map.get(behaviorList[j+1]));
//				}
//			}
//		}
//	
//		for(int i=0;i<map.size();i++){
//			System.out.println(map.get(i).getName());
//		}
//		
//	}
//
//}
