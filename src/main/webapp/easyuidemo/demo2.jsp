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

    <style type="text/css">
        .datagrid-toolbar, .datagrid-pager {
            background: #FFFFFF;
        }
        .datagrid-htable, .datagrid-header-row {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div style="width:95%; height:80%; margin:10px auto; padding:10px 0px 10px; ">
    <form id="myform" name="myform" method="post" action="saveApi.json">
        <input type="hidden" value="${apiConfigureForm.resdata}" id="resdata" name="resdata">
        <input type="hidden" value='${apiConfigureForm.id}' id="id" name="id">
        <input type="hidden" value="${apiConfigureForm.data}" id="data" name="data">
        <table border='0' class="tpd10" style="width:800px; margin:0px auto" border="0"
               cellpadding="0" cellspacing="0">
            <div id="neirong" style="padding: 5px;">
                <tr>
                    <td align="right"><font
                            color="red"> * </font>接口名称
                    </td>
                    <td><input type="text"
                               class="y-text easyui-validatebox"
                               data-
                               options="required:true" style="width:240px" id="apiChName"

                               name="apiChName" value="${apiConfigureForm.apiChName }"/>
                    </td>
                    <td align="right" valign="middle"><font
                            color="red">*</font>所属模块
                    </td>
                    <td>
                        <input type="text" data-options="required:true"
                               style="width:300px; border:solid 1px #e6e6e6;" id="apiModule" name="apiModule"
                               value="${apiConfigureForm.apiModule}"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle"><font
                            color="red">*</font>请求方式
                    </td>
                    <td>
                        <input type="text" data-options="required:true"
                               style="width:250px; border:solid 1px #e6e6e6;" id="method" name="method"
                               value="${apiConfigureForm.method}"/>
                    </td>
                    <td align="right" valign="middle"><font
                            color="red">*</font>结果类型
                    </td>
                    <td>
                        <input type="text" data-options="required:true"
                               style="width:300px; border:solid 1px #e6e6e6;" id="resultType" name="resultType"
                               value="${apiConfigureForm.resultType}"/>

                    </td>
                </tr>

                <tr>
                    <td align="right" valign="middle"><font
                            color="red">*</font>访问级别
                    </td>
                    <td>
                        <input type="text" data-options="required:true"
                               style="width:250px; border:solid 1px #e6e6e6;" id="apiLevel" name="apiLevel" value=""/>
                    </td>
                    <td align="right" valign="middle"><font
                            color="red">*</font>数据加密
                    </td>
                    <td>
                        <label style='cursor: pointer;'><input type='radio'
                                                               value='1' id='needEncrypt' name='needEncrypt'
                                                               <c:if test="${fn:trim(apiConfigureForm.needEncrypt ) eq '1'}"
                                                               >checked='checked'</c:if> /> 是</label>&nbsp;&nbsp;
                        <label style='cursor: pointer;'><input type='radio'
                                                               value='0' id='needEncrypt' name='needEncrypt'
                                                               <c:if test="${fn:trim(apiConfigureForm.needEncrypt ) eq '0'}"
                                                               >checked='checked'</c:if> /> 否</label>&nbsp;&nbsp;
                    </td>
                </tr>


                <tr>
                    <td align="right" valign="middle">
                        <font color="red">*</font>
                        访问地址
                    </td>
                    <td colspan="4">
                        <input type='text' class='y-text easyui-validatebox'
                               id="apiUrl" name="apiUrl" data-options="required:true" style="width:97%;"
                               value='${apiConfigureForm.apiUrl}'>

                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        <font color="red"></font>SQL语句<br/> <br/>
                        <a href="#" class="aLink" id="testsql"> 测试</a>
                    </td>
                    <td colspan="4">
						<textarea id="apiSql" name="apiSql" data-
                                  options="required:true" class="y-textarea"
                                  style="width:97%;height:80px;overflow-y:auto;">${apiConfigureForm.apiSql}
                        </textarea>

                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        参数设置
                    </td>
                    <td colspan="4">
                        <div id="divContainer"
                             style="height:300px ;
margin:0 auto;border:0px solid #d4d4d4;">
                            <table id="paramdata"
                                   fit="true">
                            </table>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td align="right" valign="middle">
                        返回参数设置
                    </td>
                    <td colspan="4">
                        <div id="divContainer"
                             style="height:400px;
margin:0 auto;border:0px solid #d4d4d4;">
                            <table id="resparamdata"
                                   fit="true">
                            </table>
                        </div>
                    </td>
                </tr>
                <tr height="70px" valign="bottom">
                    <td align="center"
                        colspan="4">
                        <input id="save"
                               class="iSolidBtn" type="submit" value="保存"/>
                        <!-- <input id="apitest"
class="iSolidBtn" type="submit" value="测试" /> -->
                        <input type="reset"
                               class="aEmptyBtn" style="margin-left: 20px;height:31px;line-height: 28px;"
                               id="J-from-reset" value="重　置"/>
                    </td>
                </tr>
            </div>
        </table>
    </form>
</div>
<script type="text/javascript">

    $("#testsql").click(function () {
        var sql = $('#apiSql').val();
        if (sql == null || sql == undefined || sql == '') {
            var tishi = layer.alert("请先填写SQL语句！", {
                    title: '信息提示',
                    icon: 2,
                    shadeClose: true,
                },
                function () {
                    layer.close(tishi);

                });

            return;
        }

        layer.open({
            type: 2,
            title: '测试sql',
            area: ['900px', '480px'],
            shade: [0.5, '#000000'],
            maxmin: true,
            resize: true,
            content: "testsql.htm"
        });
    });
    /*获取api类型下拉选*/
    $('#apiModule').combobox({
        height: 34,
        type: "post",
        required: true,
        multiple: false,
        editable: false,
        url: 'listModule.json',
        valueField: 'id',
        textField: 'name'
    });

    /* 获取请求方式下拉选 */
    $('#method').combobox({
        height: 34,
        type: "post",
        required: true,
        multiple: false,
        editable: false,
        url: 'requestModes.json',
        valueField: 'id',
        textField: 'name',
        onLoadSuccess: function () {
            $("#method").combobox("select", "get");
            $("#method").combobox("setValue", 'g');
        }
    });

    $('#resultType').combobox({
        height: 34,
        type: "post",
        required: true,
        multiple: false,
        editable: false,
        value: '${apiConfigureForm.resultType}',
        url: 'apiResultType.json',
        valueField: 'id',
        textField: 'name'
    });


    $('#apiLevel').combobox({
        height: 34,
        type: "post",
        required: true,
        multiple: false,
        editable: false,
        value: '${apiConfigureForm.apiLevel}',
        url: 'apiLevelTypes.json',
        valueField: 'id',
        textField: 'name'
    });


    //绑定数据	可编辑单元格
    var last;
    var lastIndex;
    var dataG;
    var dataR;
    $(document).ready(function () {
        dataG = $('#paramdata').datagrid({
            url: 'quaryParam.json?id=${apiConfigureForm.id}',
            toolbar: [{
                text: '添加',
                handler: function () {
                    $('#paramdata').datagrid('endEdit', lastIndex);
                    $('#paramdata').datagrid('appendRow', {
                        paramId: '',
                        paramName: '',
                        paramDataType: ''
                    });
                    lastIndex = $('#paramdata').datagrid('getRows').length - 1;
                    $('#paramdata').datagrid('selectRow', lastIndex);
                    $('#paramdata').datagrid('beginEdit', lastIndex);
                }
            }, {
                text: '删除',
                handler: function () {
                    var row = $('#paramdata').datagrid('getSelected');
                    if (row) {
                        var index = $('#paramdata').datagrid('getRowIndex', row);
                        $('#paramdata').datagrid('deleteRow', index);
                    }
                }
            }, {
                text: '删除全部',
                handler: function () {
                    var ll = $('#paramdata').datagrid('getRows').length - 1;
                    for (i = ll; i >= 0; i--) {
                        $('#paramdata').datagrid('deleteRow', i);
                    }
                },
            },

                {
                    text: '提取参数',
                    handler: function () {
                        removeallparams();
                        var sql = $('#apiSql').val();
                        if (sql == null || sql == undefined || sql == '') {
                            var tishi = layer.alert("请先填写SQL语句！", {
                                    title: '信息提示',
                                    icon: 2,
                                    shadeClose: true,
                                },
                                function () {
                                    layer.close(tishi);

                                });
                        } else {
                            lastIndex = $('#paramdata').datagrid('getRows').length - 1;
                            var resultType = $('#resultType').combobox('getValue');
                            $.ajax({
                                url: "getparams.json",
                                dataType: "json",
                                data:
                                    {sql: sql, id: '${apiConfigureForm.id}', resultType: resultType},
                                success: function (data) {
                                    $('#paramdata').datagrid("loadData", data);

                                }
                            });

                        }

                    }
                }
            ],
            fitColumns: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'paramId', title: '参数', editor: {type: 'text'},
                    align: 'center', hidden: 'true', width: 10
                },
                {
                    field: 'paramName', title: '参数名称', editor: {
                        type: 'text'
                    }, align: 'center', width: 20
                },
                {
                    field: 'paramDataType', title: '参数类型', editor: {
                        type: 'combobox',
                        options: {

                            data: [{'id': 'String', 'name': '字符串'}, {
                                'id': 'Double',
                                'name': '小数Double'
                            }, {'id': 'Integer', 'name': '整数'}, {
                                'id': 'Date', 'name': '日
                                期'}],

                                valueField: 'id',

                                textField: 'name',

                                panelHeight: 'auto'
                            }
                        }, align: 'center', width: 20
                    },
                {
                    field: 'paramdefaultvlaue', title: '默认值', width: 30, align: 'center',
                    editor: {type: 'text'}
                },
                {
                    field: 'paramRequest', title: '是否必填', width: 20, align: 'center',
                    editor: {
                        type: 'checkbox',
                        options: {
                            on: "是",
                            off: "否"
                        }
                    }
                },
            ]],
            onClickRow: function (rowIndex) {
                if (lastIndex != rowIndex) {
                    $('#paramdata').datagrid('beginEdit', rowIndex);
                    $('#paramdata').datagrid('endEdit', lastIndex);
                }
                lastIndex = rowIndex;
            },
            onLoadSuccess: function (data) {
                dataG.datagrid('acceptChanges');
                var validData = dataG.datagrid('getData');
                // 参数删除空行
                for (i = validData.rows.length - 1; i >= 0; i--) {
                    if (validData.rows[i].paramName == "" || validData.rows[i].paramName
                        == null) {
                        dataG.datagrid('deleteRow', i);
                    }
                }

            }
        });

        //返回值参数设置
        dataR = $('#resparamdata').datagrid({
            url: 'quaryResultParam.json?id=${apiConfigureForm.id}',
            toolbar: [{
                text: '添加',
                handler: function () {
                    $('#resparamdata').datagrid('endEdit', last);
                    $('#resparamdata').datagrid('appendRow', {
                        resId: '',
                        resName: '',
                        resDataType: '',
                        resComment: '',
                        resFormat: ''
                    });
                    last = $('#resparamdata').datagrid('getRows').length - 1;
                    $('#resparamdata').datagrid('selectRow', last);
                    $('#resparamdata').datagrid('beginEdit', last);
                }
            }, {
                text: '删除',
                handler: function () {
                    var row = $('#resparamdata').datagrid('getSelected');
                    if (row) {
                        var index = $('#resparamdata').datagrid('getRowIndex', row);
                        $('#resparamdata').datagrid('deleteRow', index);
                    }
                }
            }, {
                text: '删除全部',
                handler: function () {
                    var ll = $('#resparamdata').datagrid('getRows').length - 1;
                    for (i = ll; i >= 0; i--) {
                        $('#resparamdata').datagrid('deleteRow', i);
                    }
                },
            },
                {
                    text: '提取参数',
                    handler: function () {
                        removeResparams();
                        var state = $('#resultType').combobox('getValue');
                        var sql = $('#apiSql').val();
                        if (sql == null || sql == undefined || sql == '') {
                            var tishi = layer.alert("请先填写SQL语句！", {
                                    title: '信息提示',
                                    icon: 2,
                                    shadeClose: true,
                                },
                                function () {
                                    layer.close(tishi);
                                });
                        } else {
                            //解决当参数为一个的时候 点击提取按钮  单元格无法
                            进入编辑状态  , 同时
                            给last赋值
                            last = $('#resparamdata').datagrid('getRows').length - 1;
                            $.ajax({
                                url: "getresparams.json",
                                dataType: "json",
                                data: {apiSql: sql, id: '${apiConfigureForm.id}', state: state},
                                success: function (data) {
                                    $('#resparamdata').datagrid("loadData", data);
                                }
                            });
                        }
                    }
                }

            ],
            fitColumns: true,
            singleSelect: true,
            columns: [[
                {
                    field: 'resId', title: '返回参数', editor: {type: 'text'},
                    align: 'center', hidden: 'true', width: 10
                },
                {
                    field: 'resName', title: '返回参数名称', editor:
                        {type: 'text'}, align: 'center', width: 60
                },
                {
                    field: 'resDataType', title: '返回参数类型', editor:
                        {
                            type: 'combobox',
                            options: {
                                data: [{'id': 'String', 'name': '字符串'}, {'id': 'Double', 'name': '小数Double'}, {
                                    'id': 'Integer', 'name': '整
                                    数'},{'id':'Date','name':'日期'}],
                                    valueField: 'id',
                                    textField: 'name',
                                    panelHeight: 'auto'
                                }
                            }, align: 'center', width: 60
                        },
                {field: 'resComment', title: '返回参数描述', editor: {type: 'text'}, align: 'center', width: 65}
                /*{field : 'resFormat',title:'数据格式化',
                 editor:{ type: 'combobox',
                              options: {
                             //暂保留
                             //url : 'queryresFormat.json',
                                multiple:true,
                              valueField: 'id',
                              textField: 'name',
                              panelHeight: 'auto'  ,
                              checkbox:true,
                                  }
                                  } ,  align : 'center', width : 60}*/
            ]],
            onClickRow: function (rowIndex) {
                if (last != rowIndex) {
                    $('#resparamdata').datagrid('endEdit', last);
                    $('#resparamdata').datagrid('beginEdit', rowIndex);
                }
                last = rowIndex;
            },
            onLoadSuccess: function (data) {
                dataR.datagrid('acceptChanges');
                var validRData = dataR.datagrid('getData');
                // 参数删除空行
                for (i = validRData.rows.length - 1; i >= 0; i--) {
                    if (validRData.rows[i].resName == "" || validRData.rows[i].resName ==
                        null) {
                        dataR.datagrid('deleteRow', i);
                    }
                }

            }
        });

        //combox重写可编辑表格使用Combobox多选编辑时无法选择、新增时无法保存
        $.extend($.fn.datagrid.defaults.editors.combobox, {
            setValue: function (jq, value) {
                var opts = $(jq).combobox('options');
                if (opts.multiple && value.indexOf(opts.separator) != -1) {//多选且不只一个值
                    var values = value.split(opts.separator);
                    $(jq).combobox("setValues", values);
                }
                else
                    $(jq).combobox("setValue", value);
            }
        });

        $.extend($.fn.datagrid.defaults.editors.combobox, {
            getValue: function (jq) {
                var opts = $(jq).combobox('options');
                if (opts.multiple) {
                    var values = $(jq).combobox('getValues');
                    if (values.length > 0) {
                        if (values[0] == '' || values[0] == ' ') {
                            return values.join(',').substring(1);//新增的时候会把空白当成一个
                            值了，去掉
                        }
                    }
                    return values.join(',');
                }
                else
                    return $(jq).combobox("getValue");
            },
            setValue: function (jq, value) {
                var opts = $(jq).combobox('options');
                if (opts.multiple && value.indexOf(opts.separator) != -1) {//多选且不
                    只一个值
                    var values = value.split(opts.separator);
                    $(jq).combobox("setValues", values);
                }
                else
                    $(jq).combobox("setValue", value);
            }
        });


        function closeLayer() {
            layer.closeAll('iframe');
            $('#data').datagrid("reload");
        }

        //清空参数函数
        function removeallparams() {
            var ll = $('#paramdata').datagrid('getRows').length - 1;
            for (i = ll; i >= 0; i--) {
                $('#paramdata').datagrid('deleteRow', i);
            }
        }

        //清空返回参数的函数
        function removeResparams() {
            var ll = $('#resparamdata').datagrid('getRows').length - 1;
            for (i = ll; i >= 0; i--) {
                $('#resparamdata').datagrid('deleteRow', i);
            }
        }

        //表单重置
        $("#J-from-reset").click(function () {
            removeallparams();
            removeResparams();
            $("#myform").reset();
        });


        /*点击保存按钮校验数据进行保存*/
        $("#myform").validate({
            errorClass: "error",
            submitHandler: function (form) {
                dataG.datagrid('acceptChanges');
                dataR.datagrid('acceptChanges');
                if ($("#myform").form("validate")) {
                    var validData = dataG.datagrid('getData');

                    // 参数删除空行
                    for (i = validData.rows.length - 1; i >= 0; i--) {
                        if (validData.rows[i].paramName == "" || validData.rows[i].paramName
                            == null) {
                            dataG.datagrid('deleteRow', i);
                        }
                    }

                    var chengedData = dataG.datagrid('getData');
                    for (i = chengedData.rows.length - 1; i >= 0; i--) {
                        if (chengedData.rows[i].paramDataType == "" || chengedData.rows
                                [i].paramDataType == null) {
                            alert("请为参数添加对应数据类型。");
                            $('#paramdata').datagrid('beginEdit', i);
                            return;
                        }
                    }
                    var rr = dataR.datagrid('getData');
                    for (i = rr.rows.length - 1; i >= 0; i--) {
                        if (rr.rows[i].resName == "" || rr.rows[i].resName == null) {
                            dataR.datagrid('deleteRow', i);
                        }
                    }

                    var rData = dataR.datagrid('getData');
                    for (i = rData.rows.length - 1; i >= 0; i--) {
                        if (rData.rows[i].resDataType == "" || rData.rows[i].resDataType == null) {
                            alert("请为参数添加对应数据类型。");
                            $('#resparamdata').datagrid('beginEdit', i);
                            return;
                        }
                    }

                    var gridData = dataG.datagrid('getData');
                    var data = JSON.stringify(gridData);
                    $('#data').val(data);
                    var gridresData = dataR.datagrid('getData');
                    var redata = JSON.stringify(gridresData);
                    $('#resdata').val(redata);
                    $(form).ajaxSubmit({
                        type: "post",
                        url: "saveApi.json",
                        success: function (data) {
                            if (data.state) {
                                var tishi = layer.alert(data.msg, {
                                        title: '信息提示',
                                        icon: 1,
                                        shadeClose: true,
                                    },
                                    function () {
                                        layer.close(tishi);
                                        parent.closeLayer();

                                    });
                            } else {
                                var tishi = layer.alert(data.msg, {
                                        title: '信息提示',
                                        icon: 2,
                                        shadeClose: true,
                                    },
                                    function () {
                                        layer.close(tishi);

                                    });
                            }
                        }
                    });

                }
            }
        });

    });
    $('#paramdata').datagrid('reload');

    //返回主页面
</script>
</body>
</html>
