$(function() {
    // console.log(window.location.protocol+"//"+window.location.host);
    var hostname = window.location.protocol+"//"+window.location.hostname;
    var str = "" +
        "<div class=\"container-fluid\">\n" +
        "   <div class=\"navbar-header\" style=\"border-bottom:1px solid #ffffff;\">\n" +
        "       <span class=\"navbar-brand\"><b>Quarusis</b></span>\n" +
        "   </div>\n" +
        "   <div>\n" +
        "       <ul class=\"nav navbar-nav\">\n" +
        "           <li><a href=\"" + hostname + ":8080/Quarusis/indexpage\"><span class=\"glyphicon glyphicon-list-alt\"></span> Index </a></li>\n" +
        "       <li><a data-container=\"body\" data-toggle=\"popover\" data-placement=\"bottom\"\n" +
        "                      data-content='\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E7%2594%259F%25E6%25B4%25BB\"><h4># 生活 #</h4></a>\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E4%25BA%25A7%25E5%2593%2581\"><h4># 产品 #</h4></a>\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E7%25A7%2591%25E6%258A%2580\"><h4># 科技 #</h4></a>\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E8%2581%258C%25E5%259C%25BA\"><h4># 职场 #</h4></a>\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E5%259B%25BD%25E9%2599%2585\"><h4># 国际 #</h4></a>\n" +
        "                  <a href=\"" + hostname + ":8080/Quarusis/topicpage?topic=%25E5%2585%25B4%25E8%25B6%25A3\"><h4># 兴趣 #</h4></a>\n" +
        "                  '>\n" +
        "               <span class=\"glyphicon glyphicon-tags\"></span> Topic\n" +
        "               <div class=\"caret\"></div>\n" +
        "           </a>\n" +
        "           </li>\n" +
        "           <li><a href=\"" + hostname + ":8081/Quarusis_InChat\"><span class=\"glyphicon glyphicon-bullhorn\"></span> InChat</a></li>\n" +
        "       </ul>\n" +
        "   </div>\n" +
        "   <ul class=\"nav navbar-nav navbar-right\">\n" +
        "       <div class=\"navbar-form navbar-left\" role=\"search\">\n" +
        "           <input id=\"searchInput\" type=\"text\" class=\"form-control\" placeholder=\"暂不支持Search\" disabled/>\n" +
        "       </div>\n" +
        "   <li><a\n" +
        "              data-container=\"body\" data-toggle=\"popover\" data-placement=\"bottom\"\n" +
        "                   data-content='\n" +
        "                   <h5><a href=\"" + hostname + ":8080/Quarusis/homepage\">\n" +
        "                   <span class=\"glyphicon glyphicon-home\"></span> Homepage\n" +
        "                   </a></h5>\n" +
        "                   <h5><a href=\"" + hostname + ":8080/Quarusis/homepage\">\n" +
        "                   <span class=\"glyphicon glyphicon-cog\"></span> Setting\n" +
        "                   </a></h5>\n" +
        "                   <h5><a href=\"" + hostname + ":8080/Quarusis/homepage\">\n" +
        "                   <span class=\"glyphicon glyphicon-log-out\"></span> Logout\n" +
        "                   </a></h5>'\n" +
        "           >\n" +
        "           <span id=\"useruin\" style=\"display:none;\"></span>\n" +
        "           <span class=\"glyphicon glyphicon-user\"></span><span id=\"username\"></span>\n" +
        "           <div class=\"caret\"></div>\n" +
        "       </a></li>\n" +
        "   </ul>\n" +
        "</div>";
    $("#nav").html(str);
});
$(function () {
    $("[data-toggle='popover']").popover({trigger: "click", html: true, animation: false})
        .on("click", function () {
            var _this = this;   // 这里的this触发的dom,需要存起来 否则在下面 .popover的逻辑中this会变为弹出的dom
            $(this).popover("show");
            $(".popover").on("mouseleave", function () {
                $(_this).popover('hide');
            });
        }).on("mouseleave", function () {
        var _this = this;
        setTimeout(function () {
            if (!$(".popover:hover").length) {
                $(_this).popover("hide");
            }
        }, 500);
    });
});