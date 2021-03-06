
// 该组件设置左侧 menu 菜单导航的路径，
var urls = {
    'POST /api/user/login': {
        success: '@boolean',
        msg: '登录成功.',
        statusCode: 200,
        data: null
    },
    'GET /api/getUser': {
        id: 1,
        name: '@name',
        email: '@email',
        address: '@region',
        datetime: '@datetime'
    },
    'POST /api/tableData': {
        'list|10': [{
            'id|+1': 1,
            name: '@name',
            email: '@email',
            datetime: '@datetime',
            address: '@region',
        }]
    },
    'POST /api/submitForm': {
        success: '@boolean',
        msg: "提交成功.",
        count: 1000,
        data: {}
    },
    'POST /demo/table/user': {
        code: 0,
        msg: "xxx",
        count: 1000,
        'data|20': [{
            'id|+1': 1,
            username: '@name',
            sex: '@boolean',
            city: '@city',
            sign: '@csentence',
            experience: '@integer',
            score: '@integer',
            classify: '@word',
            wealth: '@integer',
            auth: '@boolean'
        }]
    },

    // 在 admin.js 中指定的需要动态渲染的菜单项。
    // 父级ID(重要)，一级菜单pid为0
    'POST /api/getmenus': [{
        id: 1,
        title: '表格',
        path: '#/user/table',
        icon: '&#xe631;',
        pid: 0,
        children: []
    }, {
        id: 1,
        title: '表格一',
        path: '#/user/table2',
        icon: '&#xe631;',
        pid: 0,
        children: []
    }, {
        id: 2,
        title: '一级菜单2',
        path: 'javascript:;',
        icon: '&#xe631;',
        pid: 0,
        children: [{
            id: 21,
            title: '表单',
            path: '#/user/form',
            icon: '&#xe620;',
            pid: 2,
            children: []
        }, {
            id: 22,
            title: '二级菜单二',
            path: '#/user/as',
            icon: '&#xe620;',
            pid: 2,
            children: [{
                id: 222,
                title: '百度一下',
                path: 'https://www.baidu.com',
                blank: true,
                icon: '&#xe62e;',
                pid: 22
            }]
        }, {
            id: 23,
            title: '二级菜单三',
            path: '#/user/aa',
            icon: '&#xe620;',
            pid: 2,
            children: []
        }]
    }, {
        id: 3,
        title: '数据表格3',
        path: '#/user/table3',
        icon: '&#xe631;',
        pid: 0,
        children: []
    }, {
        id: 4,
        title: 'API文档',
        path: '',
        icon: '&#xe631;',
        pid: 0,
        open: true,
        children: [{
            id: 33,
            title: '拦截器(Mockjs)',
            path: '#/docs/mockjs',
            icon: '&#xe60a;',
            pid: 4
        }, {
            id: 34,
            title: '左侧菜单(Menu)',
            path: '#/docs/menu',
            icon: '&#xe60a;',
            pid: 4
        }, {
            id: 35,
            title: '路由配置(Route)',
            path: '#/docs/route',
            icon: '&#xe60a;',
            pid: 4
        }, {
            id: 36,
            title: '选项卡(Tabs)',
            path: '#/docs/tabs',
            icon: '&#xe60a;',
            pid: 4
        }, {
            id: 37,
            title: '工具包(Utils)',
            path: '#/docs/utils',
            icon: '&#xe60a;',
            pid: 4
        }, {
            id: 38,
            title: '组件(Component)',
            path: 'javascript:;',
            icon: '&#xe60a;',
            pid: 4,
            open: true,
            children: [{
                id: 381,
                title: '主页导航栏(Nav)',
                path: baseU+'laydemo/index.jsp',
                icon: '&#xe60a;',
                pid: 38
            }]
        }]
    }]
};


var viewPage = {
    // name: 'kitadmin',
    r: [{
        path: '/user/index',
        component: baseU+'laydemo/views/user/index.html',
        name: '用户列表',
        children: [{
            path: '/user/create',
            component: baseU+'laydemo/views/user/create.html',
            name: '新增用户',
        }, {
            path: '/user/edit',
            component: baseU+'laydemo/views/user/edit.html',
            name: '编辑用户',
        }]
    }],
    routes: [{
        path: '/layui/other',
        component: baseU+'laydemo/views/others.html',
        name: 'Others'
    }, {
        path: '/layui/fly',
        component: 'https://fly.layui.com/',
        name: 'Fly',
        iframe: true
    }, {
        path: '/layui',
        component: 'http://www.layui.com/',
        name: 'Layui',
        iframe: true
    }, {
        path: '/baidu',
        component: 'https://www.baidu.com/',
        name: '百度一下',
        iframe: true
    }, {
        path: '/user/index',
        component: baseU+'laydemo/views/user/index.html',
        name: '用户列表'
    }, {
        path: '/user/create',
        component: baseU+'laydemo/views/user/create.html',
        name: '新增用户',
    }, {
        path: '/user/edit',
        component: baseU+'laydemo/views/user/edit.html',
        name: '编辑用户',
    }, {
        path: '/cascader',
        component: baseU+'laydemo/views/cascader/index.html',
        name: 'Cascader'
    }, {
        path: '/',
        component: baseU+'laydemo/views/app.html',
        name: '主页'
    }, {
        path: '/user/my',
        component: baseU+'laydemo/views/profile.html',
        name: '用户中心'
    }, {
        path: '/inputnumber',
        component: baseU+'laydemo/views/inputnumber.html',
        name: 'InputNumber'
    }, {
        path: '/layui/grid',
        component: baseU+'laydemo/views/layui/grid.html',
        name: 'Grid'
    }, {
        path: '/layui/button',
        component: baseU+'laydemo/views/layui/button.html',
        name: '按钮'
    }, {
        path: '/layui/form',
        component: baseU+'laydemo/views/layui/form.html',
        name: '表单'
    }, {
        path: '/layui/nav',
        component: baseU+'laydemo/views/layui/nav.html',
        name: '导航/面包屑'
    }, {
        path: '/layui/tab',
        component: baseU+'laydemo/views/layui/tab.html',
        name: '选项卡'
    }, {
        path: '/layui/progress',
        component: baseU+'laydemo/views/layui/progress.html',
        name: 'progress'
    }, {
        path: '/layui/panel',
        component: baseU+'laydemo/views/layui/panel.html',
        name: 'panel'
    }, {
        path: '/layui/badge',
        component: baseU+'laydemo/views/layui/badge.html',
        name: 'badge'
    }, {
        path: '/layui/timeline',
        component: baseU+'laydemo/views/layui/timeline.html',
        name: 'timeline'
    }, {
        path: '/layui/table-element',
        component: baseU+'laydemo/views/layui/table-element.html',
        name: 'table-element'
    }, {
        path: '/layui/anim',
        component: baseU+'laydemo/views/layui/anim.html',
        name: 'anim'
    }, {
        path: '/layui/layer',
        component: baseU+'laydemo/views/layui/layer.html',
        name: 'layer'
    }, {
        path: '/layui/laydate',
        component: baseU+'laydemo/views/layui/laydate.html',
        name: 'laydate'
    }, {
        path: '/layui/table',
        component: baseU+'laydemo/views/layui/table.html',
        name: 'table'
    }, {
        path: '/layui/laypage',
        component: baseU+'laydemo/views/layui/laypage.html',
        name: 'laypage'
    }, {
        path: '/layui/upload',
        component: baseU+'laydemo/views/layui/upload.html',
        name: 'upload'
    }, {
        path: '/layui/carousel',
        component: baseU+'laydemo/views/layui/carousel.html',
        name: 'carousel'
    }, {
        path: '/layui/laytpl',
        component: baseU+'laydemo/views/layui/laytpl.html',
        name: 'laytpl'
    }, {
        path: '/layui/flow',
        component: baseU+'laydemo/views/layui/flow.html',
        name: 'flow'
    }, {
        path: '/layui/util',
        component: baseU+'laydemo/views/layui/util.html',
        name: 'util'
    }, {
        path: '/layui/code',
        component: baseU+'laydemo/views/layui/code.html',
        name: 'code'
    }, {
        path: '/user/table',
        component: baseU+'laydemo/views/table/teble.html',
        name: 'Table'
    }, {
        path: '/user/table2',
        component: baseU+'laydemo/views/table/teble2.html',
        name: '数据表格2'
    }, {
        path: '/user/table3',
        component: baseU+'laydemo/views/table/teble3.html',
        name: '数据表格3'
    }, {
        path: '/user/form',
        component: baseU+'laydemo/views/form/index.html',
        name: 'Form'
    }, {
        path: '/docs/mockjs',
        component: baseU+'laydemo/docs/mockjs.html',
        name: '拦截器(Mockjs)'
    }, {
        path: '/docs/menu',
        component: baseU+'laydemo/docs/menu.html',
        name: '左侧菜单(Menu)'
    }, {
        path: '/docs/route',
        component: baseU+'laydemo/docs/route.html',
        name: '路由配置(Route)'
    }, {
        path: '/docs/tabs',
        component: baseU+'laydemo/docs/tabs.html',
        name: '选项卡(Tabs)'
    }, {
        path: '/docs/utils',
        component: baseU+'laydemo/docs/utils.html',
        name: '工具包(Utils)'
    }, {
        path: '/views/select',
        component: baseU+'laydemo/views/select/index.html',
        name: 'Select'
    }, {
        path: '/exception/403',
        component: baseU+'laydemo/views/exception/403.html',
        name: '403'
    }, {
        path: '/exception/404',
        component: baseU+'laydemo/views/exception/404.html',
        name: '404'
    }, {
        path: '/exception/500',
        component: baseU+'laydemo/views/exception/500.html',
        name: '500'
    }]
};