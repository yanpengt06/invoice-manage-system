import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import tableRouter from './modules/table'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/mdyPwd',
    component: () => import('@/views/login/mdfPwd'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '主页',
        meta: { title: '主页', icon: 'dashboard', affix: true }
      }
    ]
  },

  {
    path: '/guide',
    component: Layout,
    redirect: '/guide/index',
    meta: { title: '操作指南', icon: 'guide' },
    children: [
      {
        path: 'index',
        component: () => import('@/views/guide/index'),
        name: 'Guide',
        meta: { title: '操作指南', icon: 'guide', noCache: true }
      },
      {
        path: 'test',
        component: () => import('@/views/guide/test'),
        name: 'test',
        meta: { title: '测试用', icon: 'guide' },
        hidden: true
      }
    ]
  },
  {
    path: '/order',
    component: Layout,
    redirect: '/order/detail/:id',
    children: [
      {
        path: 'detail/:id',
        component: () => import('@/views/order/detail'),
        name: 'show order details',
        meta: { title: '订单详情', icon: 'guide' },
        hidden: true
      }
    ]
  },

  {
    path: '/good',
    component: Layout,
    redirect: '/good/detail/:id',
    children: [
      {
        path: 'detail/:id',
        component: () => import('@/views/good/detail'),
        name: 'show order details',
        meta: { title: '货品详情', icon: 'guide' },
        hidden: true
      }
    ]
  },



]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

  /** when your routing map is too long, you can split it into small modules **/

  //信息查询
  {
    path: '/query',
    component: Layout,
    redirect: '/query/allOrder',
    name: 'Example',
    meta: {
      title: '信息查询',
      icon: 'el-icon-s-help',
      roles: ['店长', '销售员', '仓库管理员']
    },
    children: [
      {
        path: 'allOrder',
        component: () => import('@/views/saler/showOrder'),
        name: 'show all Order',
        meta: { title: '订单显示', icon: 'edit', roles: ['销售员', '店长'] }
      },
      {
        path: 'allCustomers',
        component: () => import('@/views/saler/showAllCustomers'),
        name: 'show all Customer',
        meta: { title: '所有客户', icon: 'user', roles: ['店长', '销售员'] }
      },
      {
        path: 'allRep',
        component: () => import('@/views/leader/showAllRep'),
        name: 'show all warehouse',
        meta: { title: '查看仓库', icon: 'list', roles: ['店长', '仓库管理员'] },
      },
      {
        path: 'allEplys',
        component: () => import('@/views/leader/showAllEplys'),
        name: 'show all eplys',
        meta: { title: '所有员工', icon: 'user', roles: ['店长'] },
      },
      {
        path: 'queryByWh',
        component: () => import('@/views/wm/queryByWh'),
        name: 'query By warehouse ID',
        meta: { title: '分仓查询', icon: 'list', roles: ['店长', '仓库管理员'] }
      },
    ]
  },

  //信息录入
  {
    path: '/input',
    component: Layout,
    redirect: '/input/addCustomer',
    name: 'Example',
    meta: {
      title: '信息录入',
      icon: 'edit',
      roles: ['店长', '销售员', '仓库管理员']
    },
    children: [
      {
        path: 'addCustomer',
        component: () => import('@/views/saler/addCustomer'),
        name: 'add Customer',
        meta: { title: '添加客户信息', icon: 'list', roles: ['店长', '销售员'] }
      },
      {
        path: 'setPrice',
        component: () => import('@/views/leader/setPrice'),
        name: 'set Price',
        meta: { title: '商品定价', icon: 'edit', roles: ['店长'] },
      },
      {
        path: 'addWh',
        component: () => import('@/views/leader/addWarehouse'),
        name: 'add a warehouse',
        meta: { title: '添加仓库', icon: 'el-icon-plus', roles: ['店长'] },
      },
      {
        path: 'setPos',
        component: () => import('@/views/leader/setPos'),
        name: 'set position',
        meta: { title: '设定职位', icon: 'edit', roles: ['店长'] },
      },
      {
        path: 'addEply',
        component: () => import('@/views/leader/addEply'),
        name: 'add an employee',
        meta: { title: '添加员工', icon: 'el-icon-circle-plus', roles: ['店长'] },
      },
      {
        path: 'addGood',
        component: () => import('@/views/wm/addGood'),
        name: 'add a good',
        meta: { title: '添加货品', icon: 'el-icon-circle-plus', roles: ['店长', '仓库管理员'] }
      },
    ]
  },

  //订单处理
  {
    path: '/order',
    component: Layout,
    redirect: '/order/makeOrder',
    name: 'Example',
    meta: {
      title: '订单处理',
      icon: 'el-icon-shopping-cart-full',
      roles: ['店长', '销售员', '仓库管理员', '收银员']
    },
    children: [
      {
        path: 'makeOrder',
        component: () => import('@/views/saler/makeOrder'),
        name: 'make Order',
        meta: { title: '开销售单', icon: 'edit', roles: ['店长', '销售员'] },
      },
      {
        path: 'makeOrder/:id',
        component: () => import('@/views/saler/makeOrder'),
        name: 'make Order',
        meta: { title: '开销售单', icon: 'edit', roles: ['店长'] },
        hidden: true
      },
      {
        path: 'check',
        component: () => import('@/views/leader/check'),
        name: 'check Order',
        meta: { title: '审核订单', icon: 'el-icon-view', roles: ['店长'] }
      },
      {
        path: 'checkout',
        component: () => import('@/views/cashier/checkout'),
        name: '收银',
        meta: { title: '开单收银', icon: 'el-icon-coin', roles: ['店长', '收银员'] }
      },
      {
        path: 'cash',
        component: () => import('@/views/cashier/cash'),
        name: 'cash',
        meta: { title: '零售收银', icon: 'el-icon-coin', roles: ['店长', '收银员'] },
      },
      {
        path: 'returnOrder',
        component: () => import('@/views/saler/returnOrder'),
        name: 'return Order',
        meta: { title: '整单退货', icon: 'el-icon-sold-out', roles: ['店长', '销售员'] },
      },
      {
        path: 'alloc',
        component: () => import('@/views/wm/alloc'),
        name: 'allocate goods',
        meta: { title: '配货', icon: 'el-icon-sell', roles: ['店长', '仓库管理员'] }
      },
    ]
  },

  //统计盘点

  {
    path: '/stat',
    component: Layout,
    redirect: '/stat/check',
    name: 'Example',
    meta: {
      title: '统计盘点',
      icon: 'list',
      roles: ['店长', '仓库管理员']
    },
    children: [
      {
        path: 'saleStatistics',
        component: () => import('@/views/leader/saleStatistics'),
        name: 'sale statistics',
        meta: { title: '销售统计', icon: 'list', roles: ['店长'] },
      },
      {
        path: 'saleStatistics/:id',
        component: () => import('@/views/leader/saleStatistics'),
        name: 'sale statistics',
        meta: { title: '销售统计', icon: 'list', roles: ['店长'] },
        hidden: true
      },
      {
        path: 'fundStatistics',
        component: () => import('@/views/leader/fundStatistics'),
        name: 'fund statistics',
        meta: { title: '资金统计', icon: 'list', roles: ['店长'] },
      },
      {
        path: 'check',
        component: () => import('@/views/wm/check'),
        name: 'check goods in the warehouse',
        meta: { title: '库存盘点', icon: 'el-icon-document-checked', roles: ['店长', '仓库管理员'] }
      },
      {
        path: 'stat',
        component: () => import('@/views/wm/stat'),
        name: 'statistic goods in the warehouse',
        meta: { title: '库存统计', icon: 'list', roles: ['店长', '仓库管理员'] }
      },
    ]
  },
  //货品管理
  {
    path: '/good',
    component: Layout,
    redirect: '/good/deployStock',
    name: 'Example',
    meta: {
      title: '货品管理',
      icon: 'el-icon-goods',
      roles: ['店长']
    },
    children: [
      {
        path: 'deployStock',
        component: () => import('@/views/wm/deployStock'),
        name: 'deploy',
        meta: { title: '调配库存', icon: 'el-icon-truck', roles: ['店长', '仓库管理员'] }
      },
      {
        path: 'input',
        component: () => import('@/views/wm/inputGood'),
        name: 'input',
        meta: { title: '进货', icon: 'el-icon-plus', roles: ['店长', '仓库管理员'] }
      },
    ]
  },
  //公告栏          ...不想写了，学别的去了
  {
    path: '/example',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    meta: {
      title: '公告',
      icon: 'el-icon-s-help',
    },
    hidden: true,
    children: [
      {
        path: 'create',
        component: () => import('@/views/example/create'),
        name: 'CreateArticle',
        meta: { title: '发布公告', icon: 'edit', roles: ['店长'] }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/example/edit'),
        name: 'EditArticle',
        meta: { title: 'Edit Article', noCache: true, activeMenu: '/example/list', roles: ['店长'] },
      },
      {
        path: 'list',
        component: () => import('@/views/example/list'),
        name: 'ArticleList',
        meta: { title: '公告列表', icon: 'list' }
      }
    ]
  },

  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'Error Pages',
      icon: '404'
    },
    children: [
      {
        path: '401',
        component: () => import('@/views/error-page/401'),
        name: 'Page401',
        meta: { title: '401', noCache: true }
      },
      {
        path: '404',
        component: () => import('@/views/error-page/404'),
        name: 'Page404',
        meta: { title: '404', noCache: true }
      },
      {
        path: 'test',
        component: () => import('@/views/error-page/test'),
        name: 'Page404',
        meta: { title: 'test', noCache: true }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
