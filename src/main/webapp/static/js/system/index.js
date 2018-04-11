/**
 * 本地搜索菜单
 */
function search_menu() {
	// 要搜索的值
	var text = $('input[name=q]').val();
	var $ul = $('.sidebar-menu');
	$ul.find("a.nav-link").each(function() {
		var $a = $(this).css("border", "");
		// 判断是否含有要搜索的字符串
		if ($a.children("span.menu-text").text().indexOf(text) >= 0) {
			// 如果a标签的父级是隐藏的就展开
			$ul = $a.parents("ul");
			if ($ul.is(":hidden")) {
				$a.parents("ul").prev().click();
			}
			// 点击该菜单
			$a.click().css("border", "1px solid");
		}
	});
}

$(function() {
	App.setbasePath("/");
	App.setGlobalImgPath("static/img/");
	addTabs({
		id : '10008',
		title : '欢迎页',
		close : false,
		url : 'welcome_iframe.html',
		urlType : "relative"
	});
	App.fixIframeCotent();
	$.post('/sys/menu/nav', {}, function(data) {
		var menus = [ {
			id : "9000",
			text : "导航菜单",
			icon : "",
			isHeader : true
		} ];
		var menuList = data.menuList;
		$('.sidebar-menu').sidebarMenu({
			data : menus.concat(menuList)
		});
	})
});