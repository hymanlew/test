if (layui === undefined) {
  console.error('请先引用layui.js文件.');
} else {

<<<<<<< HEAD
  var modules = {
    admin: 'kit_modules/admin',
    axios: 'kit_modules/axios',
    lodash: 'kit_modules/lodash',
    menu: 'kit_modules/menu',
    mockjs: 'kit_modules/mockjs',
    mockjsbase: 'kit_modules/mockjsbase',
    route: 'kit_modules/route',
    tabs: 'kit_modules/tabs',
    utils: 'kit_modules/utils',
    component:'kit_modules/component',
    nprogress:'kit_modules/nprogress',
    kit:'kit_modules/kit',
    sidebar:'kit_modules/sidebar',
    select:'kit_modules/select',
    echarts:'kit_modules/echarts'
  };

  // 将外部扩展的模块注入成为 layui 的内置模块
  layui.injectModules(modules);
=======
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
>>>>>>> a577d1ec65e51a687a6470d87c9351cccf139485
}