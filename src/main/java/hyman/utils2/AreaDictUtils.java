package hyman.utils2;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 用户字典工具 */
public class AreaDictUtils {
	
	/** 字典缓存 */
	private static Map<String,List> dicts	=new HashMap();

	/** 地区 */
	private static String AREA="area";

	/**
	 * 获取字典
	 * @param dictType 字典类型
	 * @param dictCode 字典编码 
	 */
	public static Map getDict(String dictType,String dictCode) throws Exception{
		return getDict(dictCode, getDicts(dictType));
	}
	
	private static Map getDict(String dictCode,List<Map> dicts) throws Exception{

		for(Map dict:dicts) {
			if(dict.get("dictCode").equals(dictCode)) {
				return dict;
			}
			
			List children = (List)dict.get("children");
			if(CollectionUtils.isEmpty(children)) {
				continue;
			}
			
			Map rtn = getDict(dictCode, children);
			if(rtn!=null) {
				return rtn;
			}
		}
		return null;
	}
	
	
	/**
	 * 获取字典列表
	 * @throws Exception
	 */
	public static List getDicts(String dictType) throws Exception {

		if(!dicts.containsKey(dictType)) {

			synchronized (dicts) {

				// 加载地区字典集合，并放入缓存
				Map queryDictTree = new HashMap();
				List dictList=(List)queryDictTree.get("data");
				dicts.put(dictType, dictList);
			}
		}
		return dicts.get(dictType);
	}


	//字典对象中的地区编码
	private static final String areaCode="dictCode";

	//字段对象中的地区名称
	private static final String areaName="dictName";

	//字典对象中的子类
	private static final String dictClildren="children";

	/**
	 * 分析地址，生成国家编码、地区编码、村庄编码
	 * @param address 地区
	 */
	public static Map analyzeAddress(String address) throws Exception{

		//地区编码
		String areacode	= null;
		List provinceList = getDicts(AREA);

		//拿到省
		for (Object province : provinceList) {
			Map provinceMap=(Map) province;
			String dictMemo=(String) provinceMap.get(areaName);

			if(-1 != address.indexOf(dictMemo)|| -1 != address.indexOf(dictMemo.replace("省", ""))||-1 != address.indexOf(dictMemo.replace("市", ""))) {
				areacode = (String) provinceMap.get(areaCode);
				List cityList = (List) provinceMap.get(dictClildren);
				if(cityList==null) {
					break;
				}
				if(cityList.size()==1) {
					//是市辖区类似于北京
					Map cityMap= (Map) cityList.get(0);
					List areaList = (List) cityMap.get(dictClildren);
					if(areaList == null) {
						break;
					}
					for (Object area : areaList) {
						Map areaMap=(Map) area;
						String cityName=(String) areaMap.get(areaName);
						if(-1 != address.indexOf(cityName)|| -1 != address.indexOf(cityName.replace("市", ""))||-1 != address.indexOf(cityName.replace("区", ""))) {
							areacode = (String) areaMap.get(areaCode);
							break;
						}
						
					}
					break;
				}else {
					for (Object city : cityList) {
						Map cityMap=(Map) city;
						dictMemo=(String) cityMap.get(areaName);
						if(-1 != address.indexOf(dictMemo)|| -1 != address.indexOf(dictMemo.replace("市", ""))) {
							areacode = (String) cityMap.get(areaCode);
							List areaList = (List) cityMap.get(dictClildren);
							if(areaList == null) {
								break;
							}
							for (Object area : areaList) {
								Map areaMap=(Map) area;
								if(-1 != address.indexOf((String) areaMap.get(areaName))) {
									areacode = (String) areaMap.get(areaCode);
									break;
								}
							}
							break;
						}
					}
				}
				break;
			}
		}

		//返回一个地区编码
		Map map = new HashMap();
		map.put("areacode", areacode);
		return map;
	}
	
	/**
	 * 根据地区编码解析地址信息
	 * @param code 地区编码
	 * @return
	 * @throws Exception
	 */
	public static String analyzeCode(String code) throws Exception {

		if(StringUtils.isEmpty(code)) {
			return "";
		}
		String address= "";
		List provinceList = getDicts(AREA);

		if(!StringUtils.isEmpty(code)) {
			//城市编码
			String cityCode = replacelastlength(code,2);
			//省编码
			String provinceCode = replacelastlength(code,4);

			if(provinceList != null) {
				for (Object province : provinceList) {
					Map provinceMap=(Map) province;
					if(provinceCode.equals((String) provinceMap.get(areaCode))) {
						//是这个省的
						String provinceAddress = (String) provinceMap.get(areaName);
						address+=provinceAddress;
						List cityList = (List)provinceMap.get(dictClildren);
						if(cityList!=null) {
							for (Object city : cityList) {
								Map cityMap=(Map) city;
								if(cityCode.equals((String)cityMap.get(areaCode))) {
									String cityAddress = (String)cityMap.get(areaName);
									address+=cityAddress;
									List areaList = (List)cityMap.get(dictClildren);
									if(areaList != null) {
										for (Object area : areaList) {
											Map areaMap=(Map) area;
											if(code.equals((String)areaMap.get(areaCode))) {
												String areaAddress = (String) areaMap.get(areaName);
												address+=areaAddress;
												break;
											}
										}
									}
									break;
								}
							}
						}
						break;
					}
				}
			}
		}
		return address;
	}
	
	/**
	 * 替换最后字符串为0
	 * @param str
	 * @param length
	 * @return
	 */
	private static String replacelastlength(String str , int length) {
		if(str.length()<length+2) {
			return "";
		}
		String substring = str.substring(0, str.length()-length);
		return leftAdd(substring,length,"0");
	}
	
	/**
	 * 右侧补齐
	 * @param str
	 * @param i
	 * @param addStr
	 * @return
	 */
	private static String leftAdd(String str,int i,String addStr) {
		for (int j = 0; j < i; j++) {
			str+=addStr;
		}
		return str;
	}

	/**
	 * 
	 * @param codeList  字典编码code的list集合
	 * @param dictType  字典类型
	 * @param hasChildren  是否要子
	 * @return  字典集合
	 * @throws Exception
	 */
	public static ArrayList<Map> getDictsByCodes(List<String> codeList,String dictType,boolean hasChildren) throws Exception {
		ArrayList<Map> arrayList = new ArrayList<>();
		
		List<Map> dicts = getDicts(dictType);
		getDictsByCodes(arrayList, codeList, dicts,hasChildren);
		return arrayList;
	}

	//递归查询字典
	public static void getDictsByCodes(ArrayList<Map> arrayList,List<String> codeList,List<Map> dicts, boolean hasChildren) {
		for (Map dict : dicts) {
			String dictCode = (String)dict.get("dictCode");
			boolean contains = codeList.contains(dictCode);
			if(contains) {
				if(hasChildren) {
					arrayList.add(dict);
				}else {
					Map newMap = new HashMap<>();
					newMap.putAll(dict);
					newMap.remove("children");
					arrayList.add(newMap);
				}
			}
			List children=(List)dict.get("children");
			if(!CollectionUtils.isEmpty(children)) {
				getDictsByCodes(arrayList, codeList, children,hasChildren);
			}
			if(arrayList.size()==codeList.size()) {
				continue;
			}
		}
	}
	
	/**
	 * 根据名称模糊查询字典数据
	 * @param dictType  字典类型
	 * @param dictName	字典名称
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<Map> likeDictsByName(String dictType,String dictName) throws Exception {
		if(StringUtils.isEmpty(dictType)) {
			return null;
		}else {
			ArrayList<Map> resoutList = new ArrayList<>();
			likeDict(dictName,getDicts(dictType),resoutList);
			return resoutList;
		}
	}
	
	/**
	 * 模糊查询子类的字典数据
	 * @param dictName
	 * @param dicts
	 * @param resoutList
	 * @throws Exception
	 */
	private static void likeDict(String dictName,List<Map> dicts,List resoutList) throws Exception{
		for(Map dict:dicts) {
			String name = (String)dict.get("dictName");
			if(name.indexOf(dictName)!= -1 || StringUtils.isEmpty(dictName)) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("dictName",name);
				map.put("dictCode", dict.get("dictCode"));
				resoutList.add(map);
			}
			List children=(List)dict.get("children");
			if(CollectionUtils.isEmpty(children)) {
				continue;
			}
			likeDict(dictName, children,resoutList);
		}
	}
	
}
