
// 该组件主要用于拦截AJAX请求，返回模拟数据。
layui.define(['mockjsbase', 'utils', 'lodash'], function(exports) {
  var Mock = layui.mockjsbase,
    utils = layui.utils,
    _ = layui.lodash;
  var mockjs = {
    // 通过此方法注入配置，注入需要拦截的APIs
    inject: function(APIs) {
      if (typeof APIs !== 'object') {
        utils.error('mockjs inject error:APIs参数类型只能为object,请检查.');
        return;
      }
      var keys = _.keys(APIs);
      _.forEach(keys, function(item, index) {
        var key = item.split(' '),
          method = key[0].toLocaleLowerCase(),
          url = key[1];
        // 验证请求方式
        if (!utils.oneOf(method, ['get', 'post', 'put', 'delete'])) {
          utils.error('mockjs config error:请求方式只支持：[GET,POST,PUT,DELETE]');
          return;
        }
        // 验证模板类型
        var template = APIs[item];
        if (!utils.oneOf(typeof template, ['object', 'array', 'function'])) {
          utils.error('mockjs config error:template 只支持类型为：[object,array,function] 的处理方式.');
          return;
        }
        // Mock.mock(拦截地址，请求方式/get/post/put/delete,处理模版);  
        // 参考：https://github.com/nuysoft/Mock/wiki/Getting-Started
        //      https://www.cnblogs.com/zhenfei-jiang/p/7235339.html
        // 注册Mock拦截

        Mock.mock(url, method, template);

        /**
         * 在实际生产中，后端的接口往往是较晚才会出来，并且还要写接口文档，于是前端的许多开发都要等到接口给我们才能进行，这样对于
         * 前端来说显得十分的被动，于是有没有可以制造假数据来模拟后端接口呢，答案是肯定的。应该有人通过编写json文件来模拟后台数据，
         * 但是很局限，比如增删改查这些接口怎么实现呢。
         * 于是就出现了一款非常强大的插件Mock.js，可以非常方便的模拟后端的数据，也可以轻松的实现增删改查这些操作。
         *
         * Mock.mock(url, http method, args);
         */
      })
    }
  };
  //输出mockjs接口
  exports('mockjs', mockjs);
});