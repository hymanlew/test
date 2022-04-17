package hyman.utils2;

import hyman.entity.Student;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.*;

public class ReflectTest {

    // 类型转换
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static String getDictCache(String key) {
        Object cacheObj = SpringUtils.getBean(StringConversUtil.class);
        if (StringConversUtil.isNotNull(cacheObj)) {
            return cast(cacheObj);
        }
        return null;
    }

    public static void t1(String propertyName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 转换第一个字母为大写，获取 get/set 方法
        String getterMethod = "get/set" + org.apache.commons.lang3.StringUtils.capitalize(propertyName);

        // 获取JavaBean的方法
        Object obj = new Object();
        Method method = obj.getClass().getMethod(getterMethod, new Class[]{});
        Object objec = method.invoke(obj, new Object[]{});


        //import org.apache.commons.lang3.reflect.FieldUtils;
        Field[] fields = FieldUtils.getAllFields(obj.getClass());


        // 类型转换（将参数数据类型转换为目标方法参数类型）
        Class<?>[] cs = method.getParameterTypes();
        //cs[i] == Double.class || = String.class || = Float.class || = Date.class


        //public class Person<T1,T2> {}
        //public class Student extends Person<Integer, Boolean> {}

        // 输出 class com.mycode.test.Person
        Student student = new Student();
        Class clazz = student.getClass().getSuperclass();

        // getGenericSuperclass() 获得带有泛型的父类
        // Type 是 Java 中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。

        // 输出 com.mycode.test.Person<java.lang.Integer, java.lang.Boolean>
        Type type = clazz.getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            // ParameterizedType参数化类型，即泛型
            ParameterizedType p = (ParameterizedType) type;

            // getActualTypeArguments 获取参数化类型的数组，泛型可能有多个
            // 输出 class java.lang.Integer
            Class c1 = (Class) p.getActualTypeArguments()[0];

            // 输出 class java.lang.Boolean
            Class c2 = (Class) p.getActualTypeArguments()[1];
        }

    }

    public static <E> E getFieldValue(final Object obj, final String fieldName) throws IllegalAccessException {
        Field ffield = null;
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                        || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
                    field.setAccessible(true);
                }
                ffield = field;
                break;
            } catch (NoSuchFieldException e) {
                continue;
            }
        }
        //如果字段不是静态字段，则要传入反射类的对象。传 null 会报NullPointerException
        // 如果字段是静态字段，则传入任何对象都是可以的，包括 null
        // 并且只能作用于 public 的字段，如果属性是非公有的，则会报IllegalAccessException，需要使用 method.invoke 获取值
        E result = (E) ffield.get(obj);
        return result;
    }


    public static <E> void setFieldValue(final Object obj, final String fieldName, final E value) {
        Field field = null;
        try {
            // 并且只能作用于 public 的字段，如果属性是非公有的，则会报IllegalAccessException，需要使用 method.invoke 获取值
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            //logger.error("不可能抛出的异常: {}", e.getMessage());
        }
    }


    public static Method getMethod(Object obj, String methodName, int argsNum) {

        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getParameterTypes().length == argsNum) {
                if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
                        && !method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            }
        }
        return null;
    }
}
