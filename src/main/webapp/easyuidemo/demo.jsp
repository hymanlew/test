<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/6/006
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script type="text/javascript" src="${base}js/jquery-1.11.1.min.js"></script>
</head>
<body>
<h2>日期格式化</h2>
<div style="margin: 10px 0;"></div>


<table id="dg" title="test" class="easyui-datagrid" style="width:550px;height:250px" url="请求 url，返回json"
       toolbar="#toolbar" （工具栏） rownumbers="true" （行号） fitColumns="true" （自适应网格宽度） singleSelect="true" （单选）>

    <thead>
    <tr>
        <th field="firstname" width="50" editor="{type:'validatebox',options:{required:true}}">First Name</th>
        <th field="lastname" width="50" editor="{type:'validatebox',options:{required:true}}">Last Name</th>
        <th field="phone" width="50" editor="text">Phone</th>
        <th field="email" width="50" editor="{type:'validatebox',options:{validType:'email'}}">Email</th>
    </tr>
    </thead>
</table>

<%--
addRow，saveRow 等等都是关键字。iconCls 是 easyUI默认图标。 plain="true" 是否显示边框。
--%>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid
  		('addRow')">New</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid

	('destroyRow')">Destroy</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid
	('saveRow')">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid
	('cancelRow')">Cancel</a>
</div>


<%--创建或编辑用户，closed="true" （关闭按钮）--%>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" method="post">
        <div class="fitem">
            <label>First Name:</label>
            <input name="firstname" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>Last Name:</label>
            <input name="lastname" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>Phone:</label>
            <input name="phone">
        </div>
        <div class="fitem">
            <label>Email:</label>
            <input name="email" class="easyui-validatebox" validType="email">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>


<script type="text/javascript">
    <%--
    datagrid：	数据网格，向用户展示列表数据。
    dialog：	    创建或编辑列的用户信息。
    form：		用于提交表单数据。
    messager：	显示一些操作信息。
    --%>

    // 当创建用户时，打开一个对话框并清空表单数据：
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', 'New User');
        $('#fm').form('clear');
        url = 'save_user url';
    }


    // 当编辑用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据（getSelected，open，setTitle，load 都是关键字）：
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#dlg').dialog('open').dialog('setTitle', 'Edit User');
        $('#fm').form('load', row);
        url = 'update_user?id=' + row.id;
    }


    // 保存用户数据，在提交表单之前，'onSubmit' 函数将被调用，该函数用来验证表单字段值。当表单字段值提交成功，关闭对话框并重新加载 datagrid 数据：
    function saveUser() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.errorMsg) {
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#dlg').dialog('close'); // close the dialog，关闭对话框
                    $('#dg').datagrid('reload'); // reload the user data，重新加载表格
                }
            }
        });
    }


    // 删除一个用户：
    function destroyUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm', 'Are you sure you want to destroy this user?', function (r) {
                if (r) {
                    $.post('destroy_user', {id: row.id}, function (result) {
                        if (result.success) {
                            $('#dg').datagrid('reload'); // reload the user data
                        } else {
                            $.messager.show({ // show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    }, 'json');
                }
            });
        }
    }


    // 使用可编辑的数据网格（DataGrid）：
    $(function () {
        $('#dg').edatagrid({
            url: 'get_users',   //（自定义的请求路径）
            saveUrl: 'save_user',
            updateUrl: 'update_user',
            destroyUrl: 'destroy_user'
        });
    });
</script>

</body>
</html>
