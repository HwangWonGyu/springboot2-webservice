//중복된 함수들을 정의하는 경우 충돌이 날 수 있기 때문에 스코프를 일부러 index.js로 줄여서 혼동을 방지한다.
/*
자바스크립트의 작동 방식에 대해 알아봐야 할 것 같다. 절차적인 방식으로 진행되어서 해당 js파일로 들어오기 전에 명시된 함수들을 모두 사용할 수 있는 것일까?
 */
/*
REST규약 -> CRUD = PostGetPutDelete
 */
var main = {
    init : function (){
        /*
        this를 바로 사용하지 않고 굳이 var에 _this를 선언하고 사용하는 이유?
         */
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function (){
            _this.update();
        });

        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },

    save : function (){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType : 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; //글 등록이 성공했을 때 ("/")쪽으로 들어감(메인페이지 -> index.mustache)
        }).fail(function (error){
           alert(JSON.stringify(error));
           /*
           error가 튀는 경우 -> 에러에 대해 처리를 해줄 것인지? 아니면 에러에 대한 처리 없이 오류 메세지만 띄우고 넘어갈 것인지?
           */
            /*
            done의 경우에는 성공했을 경우에 해당 함수를 실행하는것으로 이해했는데
            fail의 경우에 실행할 함수의 error는 어떤 방식으로 받을 수 있는걸까?
            exception 같은 경우인가?
            */
        });
    },

    update : function (){
        var data = {
            title : $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType : 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    
    delete : function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType : 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();