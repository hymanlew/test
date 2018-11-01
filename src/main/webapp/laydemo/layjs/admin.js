/**
 * 此文件为项目 layJS 的主入口：
 * element，Tab的切换功能，切换事件监听等，需要依赖element模块
 * sidebar，页面顶部工具条
 * mockjs，配置解析自定义的 url 导航链接，用于拦截AJAX请求，返回模拟数据。
 * select，
 * tabs，页面标签配置，生成页面标签
 * menu，左侧主菜单初始化设置，渲染
 * utils，本地存储，缓存的设置
 * route，页面路由渲染，即切换页面
 * component，页面顶部菜单栏
 * kit，标签页标签相关设置及操作
 *
 * kitadmin，将自定义的模块 js 注入到 layui系统中，方便调用操作
 * mainmenu，自定义的菜单栏及指定对应的页面配置
 */
var mods = [
  'element', 'sidebar', 'mockjs', 'select',
  'tabs', 'menu', 'route', 'utils', 'component', 'kit'
];

/*
 * layui.define([mods], callback)
 * layui 自定义模块，参数mods是可选的（数组类型即依赖于一组模块），用于声明该模块所依赖的模块。callback 即为模块加载完毕的
 * 回调函数，它返回一个exports参数，用于输出该模块的接口。
 */
layui.define(mods, function(exports) {
  var element = layui.element,
    utils = layui.utils,
    $ = layui.jquery,
    _ = layui.lodash,
    route = layui.route,
    tabs = layui.tabs,
    layer = layui.layer,
    menu = layui.menu,
    component = layui.component,
    kit = layui.kit;

  var Admin = function() {
    // 自定义参数模式，即自定义相关配置参数
    this.config = {
      elem: '#app',
      loadType: 'SPAN'
    };
    this.version = '1.0.0';
  };

  Admin.prototype.ready = function(callback) {
    var that = this,
      config = that.config;

    // 初始化加载方式。本地存储是对 localStorage 和 sessionStorage 的友好封装，可更方便地管理本地数据。
    var getItem = utils.localStorage.getItem;
    var setting = getItem("HYMAN_SETTING_LOADTYPE");
    if (setting !== null && setting.loadType !== undefined) {
      config.loadType = setting.loadType;
    }

    kit.set({
      type: config.loadType
    }).init();


  /**
   * ==， 两边值类型相同时，等同于===；不同的时候，要先进行类型转换，再比较。
   * ===，不做类型转换，类型不同的一定不等。
   *
   * 具体分析：
   * 先说 ===，判断两个值是否相等：
   *  1、如果类型不同，就[不相等]
   *  2、如果两个都是数值，并且是同一个值，那么[相等]；(！例外)的是，如果其中至少一个是NaN，那么[不相等]。（判断一个值是否是NaN，只能用isNaN()来判断）
   *  3、如果两个都是字符串，每个位置的字符都一样，那么[相等]；否则[不相等]。
   *  4、如果两个值都是true，或者都是false，那么[相等]。
   *  5、如果两个值都是null，或者都是undefined，那么[相等]。
   *  6、引用类型比较，进行“指针地址”比较，如果两个值都引用同一个对象或函数，那么[相等]；否则[不相等]。
   *
   * 再说 ==，规则如下：
   *  1、如果两个值类型相同，进行 === 比较。
   *  2、如果两个值类型不同，他们可能相等。根据下面规则进行类型转换再比较：
   *     a、如果一个是null、一个是undefined，那么[相等]，其他如”（空字符串），false，0都不等。
   *     b、如果一个是字符串，一个是数值，把字符串转换成数值再进行比较。
   *     c、如果任一值是 true，把它转换成 1 再比较；如果任一值是 false，把它转换成 0 再比较。
   *     d、如果一个是对象，另一个基本类型，把对象转换成基础类型的值再比较。对象转换成基础类型，利用它的toString或者valueOf方法。
   *     js核心内置类，会尝试valueOf先于toString；例外的是Date，Date利用的是toString转换。非js核心的对象，令说（比较麻烦，我也不大懂）
   */


    // 初始化路由
    _private.routeInit(config);
    // 初始化选项卡
    if (config.loadType === 'TABS') {
      _private.tabsInit();
    }
    // 初始化左侧菜单   -- 请先初始化选项卡再初始化菜单
    _private.menuInit(config);
    // 跳转至首页
    if (location.hash === '') {
      utils.setUrlState('主页', '#/');
    }

    // 处理 sidebar（工具条）
    layui.sidebar.render({
      elem: '#ccleft',
      //content:'', 
      title: '这是左侧打开的栗子',
      shade: true,
      // shadeClose:false,
      direction: 'left',
      dynamicRender: true,
      url: baseU+'laydemo/views/table/teble2.html',
      width: '50%', //可以设置百分比和px
    });

    $('#cc').on('click', function() {
      var that = this;
      layui.sidebar.render({
        elem: that,
        //content:'', 
        title: '这是表单盒子',
        shade: true,
        // shadeClose:false,
        // direction: 'left'
        dynamicRender: true,
        url: baseU+'laydemo/views/form/index.html',
        width: '50%', //可以设置百分比和px
      });
    });

    // 监听头部右侧 nav
    component.on('nav(header_right)', function(_that) {
      var target = _that.elem.attr('kit-target');
      if (target === 'setting') {
        // 绑定sidebar
        layui.sidebar.render({
          elem: _that.elem,
          //content:'', 
          title: '设置',
          shade: true,
          // shadeClose:false,
          // direction: 'left'
          dynamicRender: true,
          url: baseU+'laydemo/views/setting.html',
          // width: '50%', //可以设置百分比和px
        });
      }
      if (target === 'help') {
        layer.alert('自定义帮助信息！');
      }
    });

    // 注入 url 请求路径列表
    layui.mockjs.inject(urls);

    // 初始化渲染
    if (config.loadType === 'SPAN') {
      route.render();
    }

    // 执行回调函数
    typeof callback === 'function' && callback();
  }

  // 该方法提供左侧导航功能相关配置
  var _private = {
    // 初始化路由，指定 menu 菜单导航的页面路径
    routeInit: function(config) {
      var that = this;

      // route.set({
      //   beforeRender: function (route) {
      //     if (!utils.oneOf(route.path, ['/user/table', '/user/table2', '/'])) {
      //       return {
      //         id: new Date().getTime(),
      //         name: 'Unauthorized',
      //         path: '/exception/403',
      //         component: baseU+'laydemo/views/exception/403.html'
      //       };
      //     }
      //     return route;
      //   }
      // });

      // 配置路由，指定到具体的页面
      var routeOpts = viewPage;

      if (config.loadType === 'TABS') {
        routeOpts.onChanged = function() {
          // 如果当前hash不存在选项卡列表中
          if (!tabs.existsByPath(location.hash)) {
            // 新增一个选项卡
            that.addTab(location.hash, new Date().getTime());
          } else {
            // 切换到已存在的选项卡
            tabs.switchByPath(location.hash);
          }
        }
      }
      // 设置路由
      route.setRoutes(routeOpts);
      return this;
    },
    addTab: function(href, layid) {
      var r = route.getRoute(href);
      if (r) {
        tabs.add({
          id: layid,
          title: r.name,
          path: href,
          component: r.component,
          rendered: false,
          icon: '&#xe62e;'
        });
      }
    },

    // 菜单栏初始化设置
    menuInit: function(config) {
      var that = this;

        console.log("============"+flag);
      // 定义是否动态渲染
      // 配置menu
      menu.set({
        /**
         * 是否动态渲染：
         * 如果此参数为true,则需要设置 elem,还需要设置 data或者 remote参数，它会自动渲染名称及链接路径。
         * 如果此参数为false,则只会加载页面中定义好的菜单项。
         */
        dynamicRender: flag,
        isJump: config.loadType === 'SPAN',

        onClicked: function(obj) {
          if (config.loadType === 'TABS') {
            if (!obj.hasChild) {
              var data = obj.data;
              var href = data.href;
              var layid = data.layid;
              that.addTab(href, layid);
            }
          }
        },

        // 配置menu渲染的目标容器
        elem: '#menu-box',
        // 请求路径地址指定远程加载，先要设置dynamicRender为true
        remote: {
          url: '/api/getmenus',
          method: 'post'
        },
        // 是否开启缓存？,注：只缓存远程加载的数据。
        cached: false
      }).render();
      return that;
    },

    tabsInit: function() {
      tabs.set({
        onChanged: function(layid) {
          // var tab = tabs.get(layid);
          // if (tab !== null) {
          //   utils.setUrlState(tab.title, tab.path);
          // }
        }
      }).render(function(obj) {
        // 如果只有首页的选项卡
        if (obj.isIndex) {
          route.render('#/');
        }
      });
    }
  }

  var admin = new Admin();
  admin.ready(function() {
    console.log('Init successed.');
  });

  /*
   * exports('demo', function(){});
   * exports是一个函数，它接受两个参数，第一个参数为模块名，第二个参数为模块接口，当你声明了上述的一个模块后，你就可以在外部
   * 使用了，demo 就会注册到 layui 对象下，即可通过 layui.demo() 去执行该模块的接口。
   *
   * 输出admin接口，注意这里是模块输出的核心，模块名必须与主入口 use时的模块名一致
   */
    exports('admin', {});
});