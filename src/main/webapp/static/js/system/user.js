/**
 * 系统用户
 */
require(['bootstrap','layui','admin.listen'],function(){
	layui.use(['table','form','element'], function(){
	  var table = layui.table;
	  var form = layui.form;
	  var element = layui.element;
	  table.render({
	    elem: '#table'
	    ,url:'/sys/user/list'
	    ,method:'post'
	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		,cols : [[
	      {field:'id', title: 'ID', sort: true}
	      ,{field:'username', title: '用户名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
	      ,{field:'sex', title: '性别', sort: true}
	      ,{field:'city', title: '城市'}
	      ,{field:'sign', title: '签名'}
	      ,{field:'classify', title: '职业', align: 'center'} //单元格内容水平居中
	      ,{field:'experience', title: '积分', sort: true, align: 'right'} //单元格内容水平居中
	      ,{field:'score', title: '评分', sort: true, align: 'right'}
	      ,{field:'wealth', title: '财富', sort: true, align: 'right'}
	    ]]
	  });
	});
})