if (layui === undefined) {
  console.error('请先引用layui.js文件.');
} else {

  console.log('自定义 js！');
  var modules = {
    admin: 'layjs/admin',
    axios: 'layjs/axios',
    lodash: 'layjs/lodash',
    menu: 'layjs/menu',
    mockjs: 'layjs/mockjs',
    mockjsbase: 'layjs/mockjsbase',
    route: 'layjs/route',
    tabs: 'layjs/tabs',
    utils: 'layjs/utils',
    component:'layjs/component',
    nprogress:'layjs/nprogress',
    kit:'layjs/kit',
    sidebar:'layjs/sidebar',
    select:'layjs/select',
    echarts:'../jssource/echarts'
  };

  console.log('加载自定义 js！');
  // 将外部扩展的模块注入成为 layui 的内置模块，layui 加载时自动执行。
  layui.injectModules(modules);

  console.log('加载自定义 js 完成！');
}