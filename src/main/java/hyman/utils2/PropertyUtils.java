package hyman.utils2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.io.*;
import java.util.*;

public class PropertyUtils implements BeanFactoryAware {

    public List<String> getList(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                list.add(properties.getProperty(key));
            }
        }
        return list;
    }

    public Set<String> getSet(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptySet();
        }
        Set<String> set = new TreeSet<String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                set.add(properties.getProperty(key));
            }
        }
        return set;
    }

    public Map<String, String> getMap(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        int len = prefix.length();
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                map.put(key.substring(len), properties.getProperty(key));
            }
        }
        return map;
    }

    public Properties getProperties(String prefix) {
        Properties props = new Properties();
        if (properties == null || prefix == null) {
            return props;
        }
        Enumeration<?> en = properties.propertyNames();
        String key;
        int len = prefix.length();
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                props.put(key.substring(len), properties.getProperty(key));
            }
        }
        return props;
    }

    public String getPropertiesString(String prefix) {
        String property = "";
        if (properties == null || prefix == null) {
            return property;
        }
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.equals(prefix)) {
                return properties.getProperty(key);
            }
        }
        return property;
    }

    public Map<String, Object> getBeanMap(String prefix) {
        Map<String, String> keyMap = getMap(prefix);
        if (keyMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>(keyMap.size());
        String key, value;
        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            resultMap.put(key, beanFactory.getBean(value, Object.class));
        }
        //resultMap.put("shiro", new ShiroTags());
        return resultMap;
    }

    public static Properties getProperties(File file) {
        Properties props = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(file);
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getPropertyValue(File file, String key) {
        Properties props = getProperties(file);
        return (String) props.get(key);
    }

    private BeanFactory beanFactory;
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
