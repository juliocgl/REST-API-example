var REST_URI = 'http://localhost:8080';
var token = '';

function login() {
	$.ajax({
		url : REST_URI + '/login',
		type : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({ 'username':$('#user').val(), 'password':$('#password').val() }),
		dataType : 'text',
		success : function(data, textStatus, request){
			console.log('User authenticated!');
	        token = request.getResponseHeader('authorization');
	        listProducts();
		},
		error : function(request, status, error) {
			console.log(error);
			$('#list').hide();
			$('#detail').hide();
			if (request.status == 401) {
				alert('The username or password are not correct')
			}
		}
	});
}