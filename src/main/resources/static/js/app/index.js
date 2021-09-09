//index 클래스로 묶어서 내부에 index.init()을 호출하는 것은 save라는 함수의 범위를 지정해서 namespace를 분리하는 효과를 가질 수 있다.

var index = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';  // (1)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

index.init();