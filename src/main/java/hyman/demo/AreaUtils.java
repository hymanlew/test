package hyman.demo;

import com.alibaba.druid.support.json.JSONUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AreaUtils {

	public static Map test1() throws Exception {
		ArrayList<Map> arrayList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("D:\\自由软件\\area.txt"));
		String line = null;
		while ((line = br.readLine()) != null) {
			String replaceAll = line.replaceAll(" ", "");
			String[] split = replaceAll.split(":");
			HashMap hashMap = new HashMap<>();
			int i = 0;
			for (String string : split) {
				if (i != 0) {
					String trim = string.trim();
					List list = (List) JSONUtils.parse(trim);
					for (int j = 0; j < list.size(); j++) {
						if (j == 0) {
							hashMap.put("name", (String) list.get(j));
						} else if (j == 1) {
							hashMap.put("parent", (String) list.get(j));
						}
					}
				} else {
					hashMap.put("code", string.trim());
				}
				i++;
			}
			arrayList.add(hashMap);
		}
		// 组织出来的所有的Map
		// 然后组织成父子关系
		HashMap<String, Map> map1 = new HashMap<>();
		HashMap<String, Map> map2 = new HashMap<>();
		HashMap<String, Map> map3 = new HashMap<>();
		for (Map map : arrayList) {
			String string = (String) map.get("parent");
			if ("1".equals(string)) {
				map1.put((String) map.get("code"), map);
			} else if ("810000".equals(string) || "820000".equals(string) || "710000".equals(string)) {
				map2.put((String) map.get("code"), map);
			} else {
				map3.put((String) map.get("code"), map);
			}
		}
		Set<String> keySet = map2.keySet();
		for (String string : keySet) {
			Map map = map2.get(string);
			String parent = (String) map.get("parent");
			Map map4 = map1.get(parent);
			List chiled = (List) map4.get("chiled");
			if (chiled == null) {
				chiled = new ArrayList<>();
				map4.put("chiled", chiled);
			}
			chiled.add(map);
		}
		Set<String> keySet2 = map3.keySet();
		for (String string : keySet2) {
			Map map = map3.get(string);
			String parent = (String) map.get("parent");
			Map map4 = map2.get(parent);
			List chiled = (List) map4.get("chiled");
			if (chiled == null) {
				chiled = new ArrayList<>();
				map4.put("chiled", chiled);
			}
			chiled.add(map);
		}
		return map1;

	}
}
