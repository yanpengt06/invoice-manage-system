# 进销存管理系统

## 概述

- 本系统分三版本迭代完成，前后端分离开发.
- 开发模式采用Vue+Springboot+MybatisPlus
- 组件库为elementUI
- 后台管理模板为vue-element-admin
- 用户手册在back-v3/src/main/resources/public/UserBook.docx，内有详细使用指南及环境配置保姆级教程

## 使用环境配置

1. 运行后台src/main/java自建包目录下xxxApplication.java
2. 配置src/main/resources/application.properties连接数据库
3. 在vue-element-admin下命令行输入npm命令:npm run dev,运行开发环境,没有依赖模块(正常是有的)按照提示npm install,可能需要换源
4. 权限及账号密码数据库内可见
5. 前后端数据库同时运行即可正常使用系统

### 数据库配置说明

1. mysql版本：5.7.36，自行下载并安装，默认运行在3306端口
2. navicat连接数据库，默认3306端口，输入安装时的用户名与密码
3. 连接上后在navicate右键数据库，运行转储好的sql文件，数据库配置完毕
4. 系统异常处理不完善，仍有一些bug，不要用不存在的客户开单、开不存在的物品等等，先新增再使用。系统目前已有客户、仓库等信息可在信息查询中获取，已有货品可在库存统计中查看

## 项目部署

- 本项目已部署至腾讯云服务器，公网IP：xxx

## 分工

- 前端：@yanpengt06
- 后端：@whdsn123
