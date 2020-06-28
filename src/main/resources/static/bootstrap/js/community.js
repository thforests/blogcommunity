/*
 * 提交回复
 */

function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容");
        return;
    }

    $.ajax({
        type:"POST",
        url:"/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": 1
        }),
        success:function(response){
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=2859958f9f059979ed3a&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType:"json"
    });
}

/*
* 展开二级评论
* */

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    console.log(id);
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
       // e.classList.remove("active");
    } else {
       /* $.getJSON("/comment/" + id, function (data) {

            e.classList.add("active");
        });*/
        //展开二级评论
        comments.addClass("in");
        // 标记二级评论展开状态
        e.setAttribute("data-collapse", "in");
    }
}