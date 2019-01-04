package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CountDuplicatedList {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("a");
		l.add("a");
		l.add("b");
		l.add("b");
		l.add("b");
		l.add("c");
		l.add("d");
		l.add("d");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String item : l) {
			if (map.containsKey(item)) {
				map.put(item, map.get(item).intValue() + 1);
			} else {
				map.put(item, new Integer(1));
			}
		}
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			System.out.print(key + ":" + map.get(key).intValue() + ", ");
		}
	}
}
