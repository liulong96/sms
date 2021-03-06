<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.css" type="text/css">
    <script src="${ctx}/static/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/bootstrap.min.js"></script>
    <title>短信发送验证码</title>
</head>
<body>
<form>
    <table class="table" style="border: 1px">
        <tr>
            <td style="text-align: center">发送方式:</td>
            <td>
                <select id='model'>
                    <option value='0' selected>通知</option>
                    <option value='1'>营销</option>
                </select>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">签名:</td>
            <td><input id="sign" name="sign"/></td>
        </tr>
        <tr>
            <td style="text-align: center">内容:</td>
            <td><textarea id="content" name="content" style="height: 100px;width: 700px;"></textarea></td>
        </tr>
        <tr>
            <td style="text-align: center">手机号:</td>
            <td><textarea id="tels" name="tels" style="height: 100px;width: 700px;"></textarea></td>
        </tr>
        <tr>
            <td style="text-align: center">appkey:</td>
            <td><input id="appkey" name="appkey" /></td>
        </tr>
        <tr>
            <td style="text-align: center">验证码位数:</td>
            <td><input id="number" name="number"/></td>
        </tr>
    </table>
</form>
<button class="btn btn-mini" style="margin-left: 300px;" type="button" onclick="sendMsg()">发送</button>
<button class="btn btn-mini" style="margin-left: 30px;" type="button" onclick="msgSku()">短信明细</button>
<button class="btn btn-mini" style="margin-left: 30px;" type="button" onclick="getMoney()">查询余额</button>
<div id="result"></div>
<script type="application/javascript">
    function sendMsg() {
        var model = $("#model").val();
        var sign = $("#sign").val();
        var content = $("#content").val();
        var number = $("#number").val();
        var tels = $("#tels").val();
        var appkey = $("#appkey").val();
        //alert(model+sign+content+number);
        alert("发送成功");
        $.post('${ctx}/sendIndex/send',{model:model,sign:sign,content:content,number:number,appkey:appkey,tels:tels},
            function (r) {
                if (r.status) {
                    $("#result").html(r.result);
                }
        });
    }
    function msgSku() {
        window.location.href = "${ctx}/sendIndex/msgSku";
    }
    function getMoney() {
        $.post('${ctx}/sendIndex/getMoney', null, function (r) {
            alert("剩余金额:" + r + "元");
        });
    }
</script>
</body>
</html>
