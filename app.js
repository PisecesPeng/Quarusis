var express = require('express'),
    app = express(),
    server = require('http').createServer(app),
    io = require('socket.io').listen(server),
    fs = require("fs");
    do_mongo = require('./common_res/js/mongo_do');
    config = require('./config.default');
    MongoClient = require('mongodb').MongoClient;
    DB_CONN_STR = config.db;
    users = [];
    
/**
 * 指定需使用文件的路径
 */
app.use('/Quarusis_InChat', express.static(__dirname + '/views'));
app.use('/', express.static(__dirname));

/**
 * 绑定端口
 */
server.listen(process.env.PORT || 8081);

/**
 * 配置socket事件
 */
io.sockets.on('connection', function(socket) {
    /**
     * 用户登录事件
     */
    socket.on('login', function(nickname) {
        socket.nickname = nickname;
        io.sockets.emit('system', nickname, 'login');
    });
    /**
     * 消息处理事件
     */
    socket.on('postMsg', function(useruin, msgData) {
        socket.broadcast.emit('newMsg', socket.nickname, msgData);
        console.log("新消息",useruin,msgData);

        //插入数据
        var data = [{
            "uin":useruin,
            "msgData":msgData,
            "imgURL":null
        }];
        //保存数据
        MongoClient.connect(DB_CONN_STR, function(err, db) {
            console.log("---------\n" + "   mongodb--连接成功");
            do_mongo.do_insert(db, data, function(result) {
                console.log(result);
                db.close();
            });
        });
    });

    /**
     * 用户登出事件
     */
    socket.on('disconnect', function() {
        socket.broadcast.emit('system', socket.nickname, 'logout');
    });
});
