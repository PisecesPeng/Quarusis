# Quarusis


```
生活不总是像ins、朋友圈里那样绚丽多彩，偶尔也会夹杂着风霜雨雪.
确实，面对生活，我们应该充满阳光向上的态度，但是负能量也是能量啊...
```

也许，有时不能在朋友圈里'畅所欲言'，是因为总有些自己的'顾虑'.<br>
并不是人的问题、也不是话的问题，而是这些软件牵扯着我们的社会关系，可能会使得你放不开.<br>

>**Quarusis**希望可以尽可能的淡化社交与用户，而突出用户所表达的事物以及评论所展示的观点.

>**Quarusis**没有私信、没有好友、没有百万粉丝、没有满级大V、还想点赞？看见就是缘分(doge脸).

>**Quarusis**能够禁止评论，你可以记录生活中的点滴，既能与众人分享，又能不让人打扰(Quarusis本身也淡化社交).

>**Quarusis**也能够开放评论，无论什么梗，大家的神脑洞、神逻辑比'故事'还精彩(Quarusis眉头一皱，发现事情并不简单).

>**Quarusis**只有'write'或'glance'：<br>
>write自己、write评论、write群聊；<br>
>glance其他人的槽点、glance神一样的评论、glance群聊弹幕中的老司机如何稳稳的带节奏；<br>

蛤？写心情的人多愁善感？爱吐槽的人太过小气？吐苦水的人成不了事？<br>
抱歉，**Quarusis**没有心灵鸡汤！没有道德绑架！没有成功哲学！这不是不思进取，相反，这是心知肚明！<br>

![index](https://github.com/PisecesPeng/Quarusis/blob/master/DisplayPicture/1.png)

<hr align="left" width="15%">

> Quarusis的环境配置、开发测试均在Linux Mint下完成.
>>· 使用itchat，需微信扫码来实现登录(首次登录即注册);

>>· 使用maven进行Java项目管理;

>>· 使用Spring，对项目进行配置，包括IoC与事务管理;

>>· 使用SpringMVC，代码量缩减，结构更清晰;

>>· 使用Mybatis，操作MySQL数据库，SQL语句统一管理;

>>· 使用Druid，管理数据库连接池;

>>· 使用MySQL，负责记录'user数据、page数据'等;

>>· 使用npm进行Node.js的包管理;

>>· 使用webpack，负责Node.js的'资源管理'等功能;

>>· 使用Node.js，一为弹幕聊天功能的服务器，二为资源访问的服务器;

>>· 使用Express作为web框架，实现基础功能;

>>· 使用Socket.IO，核心部分，负责'即时通信'等功能;

>>· 使用MongoDB，负责记录'用户聊天记录'等;

>>· 使用JQuery，提升用户体验与网页事件效果;

>>· 使用Bootstrap，美化界面布局，提升用户体验;


以下是网站各页面与简要的功能介绍:
<h2>1.扫码登录</h2>

<ul>

<li>
用户扫码登录，python获得用户数据并返回给Java后台即可实现登录
</li>
<li>
首次登录自动注册，只需输入Quarusis的用户名即可('正则'限制用户输入)
</li>
<li>
'ajax'处理各个事件(检测该微信UIN是否已注册、检测用户名是否重复等)
</li>

</ul>

<h2>2.AllPage页</h2>

<ul>

<li>
遍历所有Page(页面每次追加9个Page，每次触底启动新追加事件)
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
遍历指定话题的所有Page(页面每次追加9个Page，每次触底启动新追加事件)
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
使用WebSocket，实时显示文章是否有新评论(每5s更新一次)
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
检测网页session，未登录则重定向到登录页
</li>

</ul>


<h2>8.注销</h2>

<ul>

<li>
移除当前用户session，重定向到登录页
</li>

</ul>
