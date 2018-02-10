var uin;
var name;
window.onload = function() {
    $.ajax({
        type : 'GET',
        contentType : 'application/json;charset=utf-8',
        url : "http://localhost:8080/Quarusis/getUserInfo",
        dataType : 'jsonp',
        jsonp: "jsonpCallback",
        success : function(data) {
            $("#username").text(data.name);
            $("#useruin").text(data.uin);
        },
        error : function() {
            name = "";
            uin = "";
        }
    });
    var inchat = new InChat();
    inchat.init();
};
var InChat = function() {
    this.socket = null;
};

InChat.prototype = {
    /**
     * init时开启的操作
     */
    init: function() {
        var that = this;
        this.socket = io.connect();

        /**
         * 连接成功事件
         */
        this.socket.on('connect', function() {
            name = $("#username").text();
            uin = $("#useruin").text();
            console.log("name:" + name);
            console.log("uin:" + uin);
            document.getElementById('messageInput').focus();
            that.socket.emit('login', name);
        });

        /**
         * 连接错误事件
         */
        this.socket.on('error', function(err) {
            document.getElementById('status').textContent = '连接失败';
        });

        /**
         * 发送各种消息事件
         */
        this.socket.on('system', function(nickName, type) {
            var msg = nickName + (type == 'login' ? ' 进入了房间' : ' 离开了房间');
            that._displayNewMsg('【系统消息】 ', msg);
        });
        this.socket.on('newMsg', function(user, msg) {
            that._displayNewMsg(user, msg);
        });

        document.getElementById('sendBtn').addEventListener('click', function() {
            var messageInput = document.getElementById('messageInput'),
                msg = messageInput.value;
            messageInput.value = '';
            messageInput.focus();
            if (msg.trim().length != 0) {
                that.socket.emit('postMsg', uin, msg);
                that._displayNewMsg(name, msg);
                return;
            };
        }, false);
        document.getElementById('messageInput').addEventListener('keyup', function(e) {
            var messageInput = document.getElementById('messageInput'),
                msg = messageInput.value;
            if (e.keyCode == 13 && msg.trim().length != 0) {
                messageInput.value = '';
                that.socket.emit('postMsg', uin, msg);
                that._displayNewMsg(name, msg);
            };
        }, false);
        that._initialEmoji();
        document.getElementById('emoji').addEventListener('click', function(e) {
            var emojiwrapper = document.getElementById('emojiWrapper');
            emojiwrapper.style.display = 'block';
            e.stopPropagation();
        }, false);
        document.body.addEventListener('click', function(e) {
            var emojiwrapper = document.getElementById('emojiWrapper');
            if (e.target != emojiwrapper) {
                emojiwrapper.style.display = 'none';
            };
        });
        document.getElementById('emojiWrapper').addEventListener('click', function(e) {
            var target = e.target;
            if (target.nodeName.toLowerCase() == 'img') {
                var messageInput = document.getElementById('messageInput');
                messageInput.focus();
                messageInput.value = messageInput.value + '[emoji:' + target.title + ']';
            };
        }, false);
    },

    /**
     * init时遍历gif图片
     */
    _initialEmoji: function() {
        var emojiContainer = document.getElementById('emojiWrapper'),
            docFragment = document.createDocumentFragment();
        for (var i = 69; i > 0; i--) {
            var emojiItem = document.createElement('img');
            emojiItem.src = '/static/emoji/' + i + '.gif';
            emojiItem.title = i;
            docFragment.appendChild(emojiItem);
        };
        emojiContainer.appendChild(docFragment);
    },

    /**
     * 显示新消息
     */
    _displayNewMsg: function(user, msg) {
        var msg = this._displayEmoji(msg);
        var text = user + ' : ' + msg;

        this.domList = [];
        this.dom = document.querySelector('#barrage');
        if (this.dom.style.position == '' || this.dom.style.position == 'static') {
            this.dom.style.position = 'relative';
        }
        this.dom.style.overflow = 'hidden';
        var rect = this.dom.getBoundingClientRect();
        this.domWidth = rect.right - rect.left;
        this.domHeight = rect.bottom - rect.top;

        var div = document.createElement('div');
        div.style.position = 'absolute';
        div.style.left = this.domWidth + 'px';
        div.style.top = (this.domHeight - 20) * + Math.random().toFixed(2) + 'px';
        div.style.whiteSpace = 'nowrap';
        div.style.color = '#' + Math.floor(Math.random() * 256).toString(10);
        div.innerHTML = text;
        this.dom.appendChild(div);

        var roll = function(timer) {
            var now = +new Date();
            roll.last = roll.last || now;
            roll.timer = roll.timer || timer;
            var left = div.offsetLeft;
            var rect = div.getBoundingClientRect();
            if (left < (rect.left - rect.right)) {
                this.dom.removeChild(div);
            } else {
                if (now - roll.last >= roll.timer) {
                    roll.last = now;
                    left -= 3;
                    div.style.left = left + 'px';
                }
                requestAnimationFrame(roll);
            }
        }
        roll(50 * +Math.random().toFixed(2));
    },
    
    /**
     * 显示表情
     */
    _displayEmoji: function(msg) {
        var match, result = msg,
            reg = /\[emoji:\d+\]/g,
            emojiIndex,
            totalEmojiNum = document.getElementById('emojiWrapper').children.length;
        while (match = reg.exec(msg)) {
            emojiIndex = match[0].slice(7, -1);
            if (emojiIndex > totalEmojiNum) {
                result = result.replace(match[0], 'XXX');
            } else {
                result = result.replace(match[0], '<img class="emoji" src="/static/emoji/' + emojiIndex + '.gif" />');
            };
        };
        return result;
    }

};
