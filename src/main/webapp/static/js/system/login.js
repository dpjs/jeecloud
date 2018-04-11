/**
 * 系统登录
 */
if (window.top !== window.self) {
	window.top.location = window.location;
}
$(function() {
	$('.loginbox').css({
		'position' : 'absolute',
		'left' : ($(window).width() - 692) / 2
	});
	$(window).resize(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
	})
});
layui.use([ 'layer' ], function() {
	window.layer = layui.layer;
})
$.validator.setDefaults({
	submitHandler : function() {
		var ajaxFormOption = {
			type : "post", // 提交方式
			dataType : "json", // 数据类型
			data : $("#commentForm").serialize(),// 自定义数据参数，视情况添加
			url : "/login", // 请求url
			success : function(data) { // 提交成功的回调函数
				console.log(data)
				if (data.code == 0) {
					location.href = '/index.html';
				} else {
					layer.alert(data.msg);
				}
			}
		};
		$("#commentForm").ajaxSubmit(ajaxFormOption);
	},
	// 重写showErrors
	showErrors : function(errorMap, errorList) {
		var msg = "";
		$.each(errorList, function(i, v) {
			// 在此处用了layer的方法,显示效果更美观
			layer.tips(v.message, v.element, {
				tips : [ 2, '#FF6600' ],
				time : 4000
			});
			return false;
		});
	}
});
$().ready(function() {
	$("#commentForm").validate();
});