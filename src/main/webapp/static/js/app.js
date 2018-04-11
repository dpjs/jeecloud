/**
 * requireJS配置文件
 */
// 当前资源URL目录
var baseUrl = (function () {
    var scripts = document.scripts, src = scripts[scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

require.config({
	baseUrl: baseUrl,
	waitSeconds: 0,
	map: {'*': {css: '/plugin/require/require.css.js'}},
    paths: {
        jquery:['https://cdn.bootcss.com/jquery/1.12.4/jquery.min'],
        'bootstrap':['https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min','/plugin/bootstrap/dist/js/bootstrap.min'],
        layui:'/plugin/layui/layui',
        'jquery.form':['https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min'],
        'jquery.validate':'https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.min',
        'jquery.validate.messages':'https://cdn.bootcss.com/jquery-validate/1.17.0/localization/messages_zh.min',
        'adminLTE':'/plugin/adminlte/js/adminlte',
        'cloud':'/static/js/cloud',
        'demo':'/plugin/adminlte/js/demo',
        'app_iframe':'/static/js/app_iframe',
        'admin.plugs': ['plugs'],
        'admin.listen': ['listen'],
    },
    shim: {
        'jquery.form': {deps: ['jquery']},
        'jquery.validate': {deps: ['jquery']},
        'jquery.validate.messages': {deps: ['jquery.validate']},
        'cloud': {deps: ['jquery']},
        'bootstrap':{deps: ['jquery']},
        'adminLTE': {deps: ['jquery']},
        'demo':{deps: ['adminLTE']},
        'app_iframe': {deps: ['jquery','adminLTE']},
        'admin.plugs': {deps: ['jquery', 'layui']},
        'admin.listen': {deps: ['jquery', 'admin.plugs']},
    },
    deps: ['css!' + '/plugin/font-awesome/css/font-awesome.min.css'],
    // 开启debug模式，不缓存资源
   //urlArgs: "ver=" + (new Date()).getTime()
});