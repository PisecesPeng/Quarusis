!function(e){function t(o){if(n[o])return n[o].exports;var i=n[o]={i:o,l:!1,exports:{}};return e[o].call(i.exports,i,i.exports,t),i.l=!0,i.exports}var n={};t.m=e,t.c=n,t.d=function(e,n,o){t.o(e,n)||Object.defineProperty(e,n,{configurable:!1,enumerable:!0,get:o})},t.n=function(e){var n=e&&e.__esModule?function(){return e.default}:function(){return e};return t.d(n,"a",n),n},t.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},t.p="/",t(t.s=0)}([function(e,t){var n,o;window.onload=function(){$.ajax({type:"GET",contentType:"application/json;charset=utf-8",url:"http://localhost:8080/Quarusis/getUserInfo",dataType:"jsonp",jsonp:"jsonpCallback",success:function(e){$("#username").text(e.name),$("#useruin").text(e.uin)},error:function(){o="",n=""}});(new i).init()};var i=function(){this.socket=null};i.prototype={init:function(){var e=this;this.socket=io.connect(),this.socket.on("connect",function(){o=$("#username").text(),n=$("#useruin").text(),console.log("name:"+o),console.log("uin:"+n),document.getElementById("messageInput").focus(),e.socket.emit("login",o)}),this.socket.on("error",function(e){document.getElementById("status").textContent="连接失败"}),this.socket.on("system",function(t,n){var o=t+("login"==n?" 进入了房间":" 离开了房间");e._displayNewMsg("【系统消息】 ",o)}),this.socket.on("newMsg",function(t,n){e._displayNewMsg(t,n)}),document.getElementById("sendBtn").addEventListener("click",function(){var t=document.getElementById("messageInput"),i=t.value;if(t.value="",t.focus(),0!=i.trim().length)return e.socket.emit("postMsg",n,i),void e._displayNewMsg(o,i)},!1),document.getElementById("messageInput").addEventListener("keyup",function(t){var i=document.getElementById("messageInput"),s=i.value;13==t.keyCode&&0!=s.trim().length&&(i.value="",e.socket.emit("postMsg",n,s),e._displayNewMsg(o,s))},!1),e._initialEmoji(),document.getElementById("emoji").addEventListener("click",function(e){document.getElementById("emojiWrapper").style.display="block",e.stopPropagation()},!1),document.body.addEventListener("click",function(e){var t=document.getElementById("emojiWrapper");e.target!=t&&(t.style.display="none")}),document.getElementById("emojiWrapper").addEventListener("click",function(e){var t=e.target;if("img"==t.nodeName.toLowerCase()){var n=document.getElementById("messageInput");n.focus(),n.value=n.value+"[emoji:"+t.title+"]"}},!1)},_initialEmoji:function(){for(var e=document.getElementById("emojiWrapper"),t=document.createDocumentFragment(),n=69;n>0;n--){var o=document.createElement("img");o.src="/static/emoji/"+n+".gif",o.title=n,t.appendChild(o)}e.appendChild(t)},_displayNewMsg:function(e,t){var n=e+" : "+(t=this._displayEmoji(t));this.domList=[],this.dom=document.querySelector("#barrage"),""!=this.dom.style.position&&"static"!=this.dom.style.position||(this.dom.style.position="relative"),this.dom.style.overflow="hidden";var o=this.dom.getBoundingClientRect();this.domWidth=o.right-o.left,this.domHeight=o.bottom-o.top;var i=document.createElement("div");i.style.position="absolute",i.style.left=this.domWidth+"px",i.style.top=(this.domHeight-20)*+Math.random().toFixed(2)+"px",i.style.whiteSpace="nowrap",i.style.color="#"+Math.floor(256*Math.random()).toString(10),i.innerHTML=n,this.dom.appendChild(i);var s=function(e){var t=+new Date;s.last=s.last||t,s.timer=s.timer||e;var n=i.offsetLeft,o=i.getBoundingClientRect();n<o.left-o.right?this.dom.removeChild(i):(t-s.last>=s.timer&&(s.last=t,n-=3,i.style.left=n+"px"),requestAnimationFrame(s))};s(50*+Math.random().toFixed(2))},_displayEmoji:function(e){for(var t,n,o=e,i=/\[emoji:\d+\]/g,s=document.getElementById("emojiWrapper").children.length;t=i.exec(e);)o=(n=t[0].slice(7,-1))>s?o.replace(t[0],"XXX"):o.replace(t[0],'<img class="emoji" src="/static/emoji/'+n+'.gif" />');return o}}}]);