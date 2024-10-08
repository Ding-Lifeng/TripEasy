# 行易-AI旅行助手

## 介绍

TripEasy是一个基于 Spring Boot、Mybatis、Spring AI、Mysql、Redis、Vue2 和 Ollama 的前后端分离的AI旅行助手。

本项目由本人在软微学院第二学士学位在读期间敏捷软件开发课程大作业发展而来。

在敏捷开发的课程学习中，本人只是简单学习了Vue2+SpringBoot+AI API的基于AI的Web应用的开发流程，实现了旅行助手的基本功能。之后通过B站《苍穹外卖》、《黑马点评》的学习，本人决定尝试使用更规范的方法、更有效的工具和更优雅的代码完善此项目，提升个人能力。

## 主要模块

- 用户管理：
- 权限管理：
- 旅行规划生成：
- 旅行规划管理：
- 景点推荐管理：
- 

## 技术栈

- Springboot: 2.7.17
- Lombok: 1.18.20
- MyBatis: 2.2.0
- vue: 2.6.14
- vue-router: 3.5.1
- vuex: 3.6.2

## 部署说明



## 🚀 版本清单及说明

### 🌟 当前版本 v0.1

#### 🚀 New Features

- **后端**
  - 🔰 **Common 模块**: 整合公共功能，如统一返回处理、异常处理等。
  - 🔰 **Security 模块**: 提供`token` 生成、认证功能。
  - 🔰 **System 模块**: 管理用户、角色和权限等基础资源。
  - 🛠 **用户登录**: 实现后台管理用户登录及刷新 `token` 接口。

- **前端**
  - 🎨 **Layout 布局**: 定义了基础的页面布局和导航。
  - 🌐 **封装Axios**: 提供与后端 API 的通信功能。
  - 🔐 **用户认证**: 包括用户登录、登出和 token 管理。
  - 🚦 **路由守卫**: 控制页面的访问权限。

- **通用**
  - 🌐 **多环境支持**: 提供了开发、测试和生产环境的配置。
  - 🔧 **模块化设计**: 按功能模块组织代码，提高代码的可维护性。

#### 🐞 Bug Fixes

- 无。

#### 🧩 Dependency Upgrades

- **后端**
  - **MyBatis**: mybatis-spring-boot-starter 2.2.0
  - **Lombok**: 1.18.24
  - **Knife4j**: knife4j-openapi2-spring-boot-starter 4.3.0
  - **JWT**: jjwt 0.9.1
  - **BCrypt**: jbcrypt 0.4

- **前端**
  - **Element-UI**: 2.15.14
  - **Axios**: 1.6.0

#### 🗃 Database

- **MySQL**
  - 新增:
    - **用户表** (system_users)
    - 
  - 修改:
    - 无
