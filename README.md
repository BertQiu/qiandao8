# 签到吧

签到吧签到系统

------

本项目基于Springboot2.1.8开发，前端页面由bootstrap3+jQuery编写，全部采用前后端分离的模式，前端发送ajax请求，后端返回json格式的数据。

### 如何部署

1. 最低java环境 jdk8 mysql5.7
2. 运行数据库脚本，创建数据库,默认为qiandao8,编码设置为utf8 
3. 在application.yml文件中配置数据库连接参数 （username，password等）
4. 修改resources/static/js/common/ConstProperties.js中的域名 （局域网使用下，在命令行使用ipconfig查看部署的电脑的ip地址，配置为域名，如192.168.1.1。如部署到服务器，设置为服务器域名即可。如部署错误会导致无法扫码，扫码不成功等问题）

### 二维码刷新频率设置
1、在static/js/signinBoard.js中 设置refreshTime 刷新时间
2、在application.properties文件中设置TokenCache.EXPIRE_DURATION token过期时间，要比刷新时间长一些，否则会导致扫码过期

### 一期功能：

1. 注册模块
2. 登录模块
3. 创建单次活动
4. 动态二维码签到
5. 对自己创建活动数据的分页查看
6. 对签到人员信息数据的查看

### 二期功能:(开发中)

1. 周期活动的开发（可以使用excel表来设定可以签到的人员信息）
2. 对签到人员信息使用excel表导出
3. 使用帮助，错误页面的编写

### 三期功能:(展望)

1. 互动墙拥有更多的互动模块，留言等
2. 有更多的互动形式
3. 更美观的UI
4. 等等。。。
