/**
 * 系统菜单 
 */
require(['bootstrap','layui','admin.listen'],function(){
	layui.use(['table','element'], function(){
	  var table = layui.table;
	  var element = layui.element;
	  var tableWidth = $("#table").width();
	  table.render({
	    elem: '#table'
	    ,url:'/sys/menu/list'
    	,method:'post'
    	,request: {
		  pageName: 'pageNum' //页码的参数名称，默认：page
		  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
		}
	  	,page: true //开启分页
	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
	    ,cols : [[
	      {field:'id', title: '菜单ID', sort: true}
	      ,{field:'name', title: '菜单名称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
	      ,{field:'parentName', title: '上级菜单'}
	      ,{field:'icon', title: '图标'}
	      ,{field:'type', title: '类型'}
	      ,{field:'sort', title: '排序号'} //单元格内容水平居中
	      ,{field:'url', title: '菜单URL'} //单元格内容水平居中
	      ,{field:'perms', title: '授权标识'}
	    ]]
	  });
	});
})