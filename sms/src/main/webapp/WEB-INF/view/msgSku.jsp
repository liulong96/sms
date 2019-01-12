<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.css" type="text/css">
    <script src="${ctx}/static/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all"/>
    <script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
    <title>短信发送明细</title>
</head>
<body>
<%--<button class="btn btn-mini" style="margin-left: 100px;" type="button" onclick="returnIndex()">返回</button>--%>

<table id="main" lay-filter="test"></table>

<table id="mainSku"></table>


<script type="application/javascript">

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#main'
            , height: 260
            , url: '${ctx}/sendIndex/getMsg' //数据接口
            , page: true, //开启分页
            method: 'post',
            cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, fixed: 'left'}
                , {field: 'name', title: '用户名', width: 150}
                , {field: 'appKey', title: 'appKey', width: 200}
                , {field: 'total', title: '总数', width: 150}
                , {field: 'status', title: '状态', width: 177, templet: statusStr}
                , {field: 'createDate', title: '创建时间', width: 180}
                , {field: 'createUser', title: '创建人', width: 150}
            ]]
        });


        table.render({
            elem: '#mainSku',
            height: 312,
            id: 'listReload',
            method: 'post',
            cols: [[
                {field: 'id', title: 'ID', width: 80, fixed: 'left'},
                {field: 'tel', title: '手机号', width: 150},
                {field: 'content', title: '发送内容', width: 200},
                {field: 'createDate', title: '创建时间', width: 180}
            ]]
        });
        //监听工具条
        table.on('row(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var tr = obj.tr; //获得当前行 tr 的DOM对象
//            console.log(obj.tr); //得到当前行元素对象
//            console.log(obj.data);//得到当前行数据
            table.reload("listReload", {
                url: '${ctx}/sendIndex/getMsgSku',
                where: {pId: data.id}
            });

        });

    });

    function returnIndex() {
        window.location.href = "${ctx}/sendIndex";
    }

    function statusStr(d) {
        if (d.status == 0){
            return "启用";
        }else {
            return  "禁用";
        }
    }

</script>
</body>
</html>
