
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta name="keywords" content="jstypeof" />
    <meta name="description" content="jstypeof" />
    <title>jstypeof</title>
</head>

<body>
<div></div>

</body>

<script type="text/javascript">
    <%--
        typeof 运算符返回一个用来表示表达式的数据类型的字符串，返回值有六种可能： "number," "string," "boolean," "object," "function," 和 "undefined"。

        可以使用typeof来获取一个变量是否存在，如if(typeof a!="undefined"){}，而不要去使用if(a)因为如果a不存在（未声明）则会出错，对于
        Array,Null 等特殊对象，使用 typeof 一律返回 object，这正是 typeof 的局限性。


        typeof 语法中的圆括号是可选项。

        if(document.mylist.length != “undefined” ) {} 这个用法有误，正确的是 if( typeof(document.mylist.length) != “undefined” ) {}

        或 if( !isNaN(document.mylist.length) ) {}


        typeof的运算数未定义,返回的就是 “undefined”.

        运算数为数字 typeof(x) = “number”

        字符串 typeof(x) = “string”

        布尔值 typeof(x) = “boolean”

        对象,数组和null typeof(x) = “object”

        函数 typeof(x) = “function”


        如果我们希望获取一个对象是否是数组，或判断某个变量是否是某个对象的实例则要选择使用instanceof。instanceof 用于判断一个变量是否某个对象
        的实例，如var a=new Array();alert(a instanceof Array);会返回true，同时 alert(a instanceof Object) 也会返回true;这是因为Array是object的子类。
        再如：function test(){};var a=new test();alert(a instanceof test)会返回true。

        a instanceof Object 得到true并不是因为 Array是Object的子对象，而是因为 Array的prototype属性构造于Object，Array的父级是Function。

    --%>

</script>
</html>
