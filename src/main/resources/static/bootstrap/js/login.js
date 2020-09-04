$(function(){

    tabChange();
    // getTabContent1();

    /**
     * tabContent1点击提交
     * @param  {[type]} ){		var tabContent1   [description]
     * @return {[type]}          [description]
     */
    $('#tabSubmit1').click(function(){
        var tabContent1 = $('#tabContent1');
        var trs = tabContent1.find('tr');
        params = [];
        trs.each(function(index,tr){
            var obj = {
                title:tabContent1.find('tr').eq(index).children().eq(0).html(),
                content:tabContent1.find('tr').eq(index).children().eq(1).find('input').val()
            };
            params.push(obj);
        });
        console.log("params:====");
        console.log(params);
        $.post('',params,function(response){
            if(response.code === 10000){
                alert('更新成功');
            }else{
                alert('更新失败');
            }
        });
    });

    /**
     * tabContent2点击提交
     * @param  {[type]} ){		var tabContent1   [description]
     * @return {[type]}          [description]
     */
    $('#tabSubmit2').click(function(){
        var tabContent2 = $('#tabContent2');
        var trs = tabContent2.find('tr');
        params = [];
        trs.each(function(index,tr){
            var obj = {
                title:tabContent2.find('tr').eq(index).children().eq(0).html(),
                content:tabContent2.find('tr').eq(index).children().eq(1).find('input').val()
            };
            params.push(obj);
        });
        $.post('',params,function(response){
            if(response.code === 10000){
                alert('更新成功');
            }else{
                alert('更新失败');
            }
        });
    });

    /**
     * tabContent3点击提交
     * @param  {[type]} ){		var tabContent1   [description]
     * @return {[type]}          [description]
     */
    $('#tabSubmit3').click(function(){
        var tabContent3 = $('#tabContent3');
        var trs = tabContent3.find('tr');
        params = [];
        trs.each(function(index,tr){
            var obj = {
                title:tabContent3.find('tr').eq(index).children().eq(0).html(),
                content:tabContent3.find('tr').eq(index).children().eq(1).find('input').val()
            };
            params.push(obj);
        });
        $.post('',params,function(response){
            if(response.code === 10000){
                alert('更新成功');
            }else{
                alert('更新失败');
            }
        });
    });

    /**
     * tab切换
     * @return {[type]} [description]
     */
    function tabChange(){
        $('.nav-tabs li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            var _id = $(this).attr('data-id');
            $('.tabs-contents').find('#'+_id).addClass('active').siblings().removeClass('active');

            switch(_id){
                case "tabContent1":
                    getTabContent1();
                    break;
                case "tabContent2":
                    getTabContent2();
                    break;
                case "tabContent3":
                    getTabContent3();
                    break;
                default:
                    getTabContent1();
                    break;
            }
        });
    }

    /**
     * 获取tab1的内容
     * @return {[type]} [description]
     */
    function getTabContent1(){
        $.get('../json/table1.json',function(response){
            console.log("response:====");
            console.log(response);
            if(response.code === 10000){
                var data = response.data,
                    tableList = '';
                data.forEach(function(detail){
                    tableList += '<tr>'+
                        '<td>'+detail.title+'</td>'+
                        '<td><input type="text" value="'+detail.content+'" class="form-control" name="" placeholder="请输入内容"></td>'+
                        '</tr>';
                });
                $('#tabContent1').find('tbody').html(tableList);
            }
        });
    }

    /**
     * 获取tab1的内容
     * @return {[type]} [description]
     */
    function getTabContent2(){
        $.get('../json/table2.json',function(response){
            if(response.code === 10000){
                var data = response.data,
                    tableList = '';
                data.forEach(function(detail){
                    tableList += '<tr>'+
                        '<td>'+detail.title+'</td>'+
                        '<td><input type="text" value="'+detail.content+'" class="form-control" name="" placeholder="请输入内容"></td>'+
                        '</tr>';
                });
                $('#tabContent2').find('tbody').html(tableList);
            }
        });
    }

    /**
     * 获取tab1的内容
     * @return {[type]} [description]
     */
    function getTabContent3(){
        $.get('../json/table3.json',function(response){
            if(response.code === 10000){
                var data = response.data,
                    tableList = '';
                data.forEach(function(detail){
                    tableList += '<tr>'+
                        '<td>'+detail.title+'</td>'+
                        '<td><input type="text" value="'+detail.content+'" class="form-control" name="" placeholder="请输入内容"></td>'+
                        '</tr>';
                });
                $('#tabContent3').find('tbody').html(tableList);
            }
        });
    }
});
