
// 本 js 是对 menu 主菜单进行初始化设置，渲染数据到页面，并对链接指定请求，处理缓存。

/**
 * Axios 是一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中。主要功能有：
 *
 * 从浏览器中创建 XMLHttpRequests，
 * 从 node.js 创建 http 请求，
 * 支持 Promise API，
 * 拦截请求和响应，
 * 转换请求数据和响应数据，
 * 取消请求，
 * 自动转换 JSON 数据，
 * 客户端支持防御 XSRF
 */
// 提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
layui.define(['jquery', 'utils', 'axios'], function(exports) {
  var $ = layui.jquery,
    utils = layui.utils,
    _ = layui.lodash,
    axios = layui.axios,
    storage = utils.localStorage;

  var Menu = function() {

    // 自定义参数模式，即自定义相关配置参数
    this.config = {
      elem: undefined,      // 容器
      onClicked: undefined, // 点击后触发的回调
      dynamicRender: false, // 是否动态渲染 --如果此参数为true,则需要设置elem,[data或者remote]参数
      data: [],             // 数据
      remote: {             // 配置 axios 的 request（http）配置，文档地址：https://www.kancloud.cn/yunye/axios/234845
        url: undefined,
        method: 'get'
      },
      cached: false,        // 是否开启缓存,注：只缓存远程加载的数据。
      cacheKey: 'HYMANMENU', // 缓存key
      isJump: true,         //是否直接跳转页面
      onlyOne: true
    };
    this.version = '1.0.2';
  }

  var MENU = '.kit-menu',
    MENU_ITEM = '.kit-menu-item',
    MENU_CHILD = '.kit-menu-child',
    THIS = '.layui-this',
    SHOW = '.layui-show';

  // 设置参数
  Menu.prototype.set = function(options) {
    var that = this;

      /**
       * jQuery.extend() 函数用于将一个或多个对象的内容合并到目标对象。
       *
       * $.extend( target [, object1 ] [, objectN ] )
       *
       * 1. 如果只为 $.extend() 指定了一个参数，则意味着参数 target 被省略。此时 target 就是jQuery对象本身。通过这种方式，
       *  我们可以为全局对象 jQuery 添加新的函数。
       *
       * 2. 如果多个对象具有相同的属性，则后者会覆盖前者的属性值。
       *
       *
       * 指示是否深度合并：$.extend( [deep ], target, object1 [, objectN ] )  警告: 不支持第一个参数传递 false 。
       *
       * 参数	描述
       * deep	可选。 Boolean类型 指示是否深度合并对象，默认为false。如果该值为true，且多个对象的某个同名属性也都是对象，则该"属性对象"的属性也将进行合并。
       * target	Object类型 目标对象，其他对象的成员属性将被附加到该对象上。
       * object1	可选。 Object类型 第一个被合并的对象。
       * objectN	可选。 Object类型 第N个被合并的对象。
       *
       */
    $.extend(true, that.config, options);
    return that;
  };

  // 渲染
  Menu.prototype.render = function() {
    var that = this,
      config = that.config;
    // 是否需要动态渲染
    if (config.dynamicRender) {
      //本地数据优先级最高
      if (config.data.length > 0) {
        // 缓存数据
        storage.setItem(config.cacheKey, data);
        // 渲染DOM
        _private.renderHTML(config.elem, config.data, function() {
          // 绑定事件
          that.bind();
        });
      } else {
        //是否已经处理
        var isHandled = false;
        if (config.cached) {
          var data = storage.getItem(config.cacheKey);
          if (data !== null && data !== undefined) {
            isHandled = true;
            _private.renderHTML(config.elem, data, function() {
              that.bind();
            });
          }
        }
        if (!isHandled) {
          // 远程读取
          _private.loadData(config.remote, function(data) {
            // 缓存数据
            storage.setItem(config.cacheKey, data);
            // 渲染DOM
            _private.renderHTML(config.elem, data, function() {
              that.bind();
            });
          });
        }
      }
    } else {
      //绑定事件
      that.bind();
    }
    return that;
  };

  // 绑定事件，菜单栏跳转事件及样式的切换
  Menu.prototype.bind = function() {
    var that = this,
      config = that.config;

    // 遍历菜单栏项目
    $(MENU).find(MENU_ITEM).each(function() {
      var _item = $(this);
      var _a = _item.children('a');
      var isOneLevel = _item.parent()[0].className === 'kit-menu';
      if (isOneLevel) {
        _item.attr('lay-one', true);
      }
      var hasChild = _item.find('ul.kit-menu-child').length > 0;
      if (hasChild) {
        _a.addClass('child');
      }
      var layid = _item.attr('lay-id');
      if (layid === '' || layid === undefined) {
        layid = utils.randomCode();
        _item.attr('lay-id', layid);
      }
      _a.off('click').on('click', function(e) {
        layui.stope(e);
        if (!hasChild) {
          $(MENU).find(MENU_ITEM).removeClass('layui-this');
          _item.addClass('layui-this');
        } else {
          if (_item.hasClass('layui-show')) {
            _item.removeClass('layui-show');
          } else {
            _item.addClass('layui-show');
          }
          // 只展开一个
          if (config.onlyOne) {
            if (_item.attr('lay-one')) {
              _item.siblings().removeClass('layui-show');
            }
          }
        }
        utils.isFunction(config.onClicked) && config.onClicked({
          elem: _item,
          hasChild: hasChild,
          data: {
            href: _a.attr('href'),
            layid: layid
          }
        });
        // 拦截a标签的跳转
        if (!config.isJump) {
          return false;
        }
      });
    });
    if (location.hash !== undefined && location.hash !== '' && location.hash !== null) {
      // 默认选中，# 标注的地址
      var hash = '#' + layui.router(location.hash).href.split('?')[0];
      var _active = $(MENU).find('a[href="' + hash + '"]');
      if (_active.length > 0) {
        _active.parents('li').each(function() {
          var _that = $(this);
          if (!_that.hasClass('layui-show')) {
            _that.children('a').click();
          }
        });
      }
    }
    return that;
  };

  // 移除缓存
  Menu.prototype.removeCache = function(key) {
    var that = this,
      config = that.config;
    key = key || config.cacheKey;
    utils.localStorage.removeItem(key);
  };

  // 私有方法，渲染 HTML 页面
  var _private = {

    // 渲染HTML
    renderHTML: function(elem, data, callback) {
      var that = this;
      var temps = ['<ul class="kit-menu">'];

      // 一级菜单pid为0 ，这是一个约定
      var pid = 0;
      that.recursion(temps, data, pid);
      if (temps.length > 0) {
        // 闭合ul
        temps.push('</ul>');
        var _elem = $(elem);
        if (_elem.length === 0) {
          utils.error('Menu config error:请配置elem参数.');
          return;
        }
        _elem.html(temps.join(''));
        utils.isFunction(callback) && callback();
      }
    },

    // 递归组装 dom
    recursion: function(temp, datas, pid) {
      var that = this;
      var curr = [];
      _.forEach(datas, function(item, index) {
        if (item.pid === pid)
          curr.push(item);
      });
      if (curr.length > 0) {
        _.forEach(curr, function(item) {
          var open = item.open ? 'layui-show' : '';
          temp.push('<li class="kit-menu-item ' + open + '">');
          var href = _.isEmpty(item.path) ? 'javascript:;' : item.path;
          if (item.blank) {
            temp.push('<a href="' + href + '" target="_blank">');
          } else {
            temp.push('<a href="' + href + '">');
          }
          temp.push('<i class="layui-icon">' + item.icon + '</i> ');
          temp.push('<span>' + item.title + '</span>');
          temp.push('</a>');
          var children = item.children;
          if (children !== undefined && children !== null && children.length > 0) {
            temp.push('<ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">');
            that.recursion(temp, children, item.id);
            temp.push('</ul>');
          }
          temp.push('</li>');
        });
      }
    },

    // 远程加载数据：Axios 是一个基于 promise 的 HTTP 库，发出请求处理响应，解析 json 数据，防御 XSRF 网络攻击。
    loadData: function(options, callback) {
      axios(options)
        .then(function(res) {
          if (res.status === 500)
            throw new Error(res.statusText);
          return res.data;
        })
        .then(function(data) {
          callback(data);
        })
        .catch(function(error) {
          utils.error(error);
        });
    }
  };

  var menu = new Menu();

  // 输出 menu 接口
  exports('menu', menu);
});