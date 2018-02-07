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