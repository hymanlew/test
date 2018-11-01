package hyman.entity;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;
import sun.reflect.annotation.ExceptionProxy;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 自定义一个“万能实体”，什么参数都能接受，感觉就不要什么实体了。
 */
public class SuperEntity  extends HashMap implements Map{

    // 此时不能再实现接口 implements Serializable，原因未知
    private static final Long serialVersionUID = 11L;

    private Map map;
    private HttpServletRequest request;

    public SuperEntity(HttpServletRequest request){

        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();

        Map.Entry entry;
        String name = "";
        String value = "";

        while (entries.hasNext()){
            entry = (Map.Entry)entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();

            if(null==valueObj){
                value = "";
            }else if(valueObj instanceof String[]){

                String[] values = (String[]) valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i]+",";
                }
                value = value.substring(0,value.length()-1);
            }else{
                value = valueObj.toString();
            }

            returnMap.put(name,value);
        }
        map = returnMap;
    }

    public SuperEntity(){
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if(map.get(key) instanceof Object[]){
            Object[] arr = (Object[]) map.get(key);

            // 连续的三目运算
            obj = request == null ? arr : (request.getParameter((String)key) == null ? arr : arr[0]);

        }else{
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key){
        // 调用自身上一个方法
        return (String) get(key);
    }


    /**
     *  J2SE 提供的最后一个批注是 @SuppressWarnings。该批注的作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。
     *
     *  关键字         用途
     *  deprecation   使用了不赞成使用的类或方法时的警告
     *  unchecked     执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型。
     *  fallthrough   当 Switch 程序块直接通往下一种情况而没有 Break 时的警告。
     *  path          在类路径、源文件路径等中有不存在的路径时的警告。
     *  serial        当在可序列化的类上缺少 serialVersionUID 定义时的警告。
     *  finally       任何 finally 子句不能正常完成时的警告。
     *  all           关于以上所有情况的警告。
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {

        /**
         * Oarcle 数据库中的 LOB 类型：
         * 在 Oracle 中，LOB（Large Object，大型对象）类型的字段现在用得越来越多了。因为这种类型的字段，容量大（最多能容纳4GB的数据），
         * 且一个表中可以有多个这种类型的字段，很灵活，适用于数据量非常大的业务领域（如图象、档案等）。
         *
         * LOB 类型分为 BLOB 和 CLOB 两种：
         * BLOB 即二进制大型对象（Binary Large Object），适用于存贮非文本的字节流数据（如程序、图象、影音等）。
         * CLOB，即字符型大型对象（Character Large Object），则与字符集相关，适于存贮文本型的数据（如历史档案、大部头著作等）。
         *
         * ClobProxyImpl，则是 alibaba 对 oracle clob类型数据操作的方法封装。
         */
        if(value instanceof ClobProxyImpl){
            try{
                // 读取 oracle Clob 类型数据
                ClobProxyImpl cpi = (ClobProxyImpl) value;

                // 获取流
                Reader is = cpi.getCharacterStream();
                BufferedReader br = new BufferedReader(is);
                String str = br.readLine();

                StringBuffer sb = new StringBuffer();

                //循环读取数据拼接到字符串
                while (str!=null){
                    sb.append(str);
                    sb.append("\n");
                    str = br.readLine();
                }
                value = sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map.put(key,value);
    }
}
