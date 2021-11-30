var registerForm = {
    init : function (){
        $('.btn_save').on('click', function (){
            registerForm.save();
        });
    },
    save : function (){
        var data = {
            name: $('#user_name').val(),
            birth: $('#user_birth').val(),
            tel: $('#user_tel').val()
        };

        $.ajax({
            type: 'POST',
            url: '/register',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            date: JSON.stringify(data)
        }).done(function (){
            alert('가입을 축하드립니다.');
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }
};
registerForm.init();