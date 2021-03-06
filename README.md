# Quarusis - :new_moon_with_face:希望淡化社交的观点，必须依赖社交的网站:full_moon_with_face:

>**Quarusis**希望尽可能'淡化社交'，而突出'用户表达的事物'以及'评论展示的观点'.

>**Quarusis**没有私信、没有好友、没有粉丝、没有大V，能够看到点个赞就是缘分(doge脸).

>**Quarusis**能禁止评论，可以记录生活点滴，既能与众人分享，又能不让人打扰(Quarusis本身也淡化社交).

>**Quarusis**也能开放评论，无论什么梗，大家的神脑洞、神逻辑比'故事'还精彩(Quarusis眉头一皱，发现事情并不简单).

>**Quarusis**只有'write'或'glance'：<br>
>write自己、write评论、write群聊；<br>
>glance其他人的槽点、glance神一样的评论、glance群聊弹幕中的老司机如何稳稳的带节奏；<br>

![index](https://github.com/PisecesPeng/Quarusis/blob/master/DisplayPicture/1.png)

<hr align="left" width="15%">

> Quarusis的环境搭建、开发测试均在Linux Mint下完成.
>>· 使用Docker搭建需要的服务(MySQL、MongoDB..);

>>· 使用itchat(python微信库)，需微信扫码来实现登录(首次登录即注册);

>>· 使用Maven进行Java项目管理;

>>· 使用Spring，对项目进行配置，包括IoC与事务管理等;

>>· 使用SpringMVC，代码量缩减，开发结构更加清晰;

>>· 使用Mybatis，操作MySQL数据库，SQL语句统一管理;

>>· 使用Druid，管理数据库连接池;

>>· 使用MySQL，负责记录'user数据、page数据'等;

>>· 使用NPM进行Node.js的包管理;

>>· 使用webpack，负责Node.js的'资源管理'等功能;

>>· 使用Node.js，一为弹幕聊天功能的服务器，二为资源访问的服务器;

>>· 使用Express作为web框架，实现基础功能;

>>· 使用Socket.IO，弹幕聊天的核心部分，负责'即时通信'等功能;

>>· 使用MongoDB，负责记录'用户聊天记录'等;

>>· 使用JQuery，处理前端事件，提升用户体验;

>>· 使用Bootstrap，美化界面布局，提升用户体验;

<br>
<b>以下是网站各页面与简要的功能介绍:</b>
<h2>1.扫码登录</h2>

<ul>

<li>
微信扫码登录，python获取用户数据并返回给Java后台即可实现登录
</li>
<li>
首次登录自动注册，只需输入Quarusis的用户名即可('正则'限制用户输入规则)
</li>
<li>
'ajax'处理各个事件(检测该微信UIN是否已注册、检测用户名是否重复等)
</li>

</ul>

<h2>2.AllPage页</h2>

<ul>

<li>
遍历Page(页面每次追加9个Page，每次触底启动新追加事件)
</li>
<li>
页面右侧为评论最热门的文章TOP10
</li>
<li>
支持关键字模糊搜索，ajax动态刷新page列表
</li>

</ul>

<h2>3.TopicPage页</h2>

<ul>

<li>
遍历指定话题的Page(页面每次追加9个Page，每次触底启动新追加事件)
</li>
<li>
页面右侧为评论最热门的该话题文章TOP10
</li>
<li>
支持关键字模糊搜索(默认搜索该话题的Page)，ajax动态刷新page列表
</li>

</ul>

<h2>4.UserCenter页</h2>

<ul>

<li>
遍历用户所有Page
</li>
<li>
页面右侧为用户曾经评论过的Page历史
</li>
<li>
用户添加Page操作(Page由话题、标题、内容、图片、是否允许评论等组成)
</li>
<li>
使用WebSocket，实时显示Page是否有新评论(每5s实时刷新一次)
</li>

</ul>

<h2>5.ShowPage页</h2>

<ul>

<li>
展示Page内容
</li>
<li>
用户添加评论操作
</li>
<li>
增加评论热度(每条评论只能被每位用户增加一次热度)
</li>
<li>
遍历评论热度最高的前三条评论置顶
</li>
<li>
删除Page功能(仅能删除用户自己的Page)
</li>

</ul>

<h2>6.在线弹幕聊天</h2>

<ul>

<li>
UIN与用户名是从Java后端服务器跨域传输至Node.js服务器(使用jsonp实现)
</li>
<li>
弹幕聊天，可输入内容与内置表情包(弹幕位置高低、弹幕速度快慢、弹幕字体颜色均为随机产生)
</li>

</ul>

<h2>7.过滤器</h2>

<ul>

<li>
检测网页session，未登录用户则重定向到登录页
</li>

</ul>


<h2>8.注销</h2>

<ul>

<li>
移除当前用户session，重定向到登录页
</li>

</ul>
