var do_insert = function(db, data,callback) {  
    //连接到表recode
    var collection = db.collection('recode');
    //插入文档
    collection.insert(data, function(err, result) { 
        if(err) {
            console.log('Error:'+ err);
            return;
        }
        console.log("\n" + "   mongodb--数据已保存" + "\n");
        callback(result);
    });
}

exports.do_insert = do_insert;