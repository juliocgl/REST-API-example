// JS library that holds all operations related to products
// Needs auth.js
var PRODUCTS_URI = REST_URI + '/products';

function listProducts() {
	$('#products>tbody').find('tr').remove();
	$.ajax({
		url : PRODUCTS_URI,
		type : 'GET',
		dataType : 'json',
		headers : {
			'Authorization' : token
		},
		success : function(data) {
			console.log('reach!');
			$.each(data, function(i, product) {
				$('#products>tbody:last-child').append('<tr><th>' + product.id + '</th><td>' + product.name + '</td><td>' + product.available + '</td><td>'
						+ product.price + '</td><td><div class="btn-group pull-right" role="group" aria-label="Product options"><button type="button" class="btn btn-default btn-xs" title="Edit product" onclick="editProduct(&quot;'
						+ product.id + '&quot;);"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></button><button type="button" class="btn btn-default btn-xs" title="Delete product" onclick="deleteProduct(&quot;'
						+ product.id + '&quot;);"><span class="glyphicon glyphicon-trash" aria-hidden="true"></button></div></td></tr>');
			});
			$('#list').show();
		},
		error : function(request, status, error) {
			console.log(error);
			$('#list').hide();
			$('#detail').hide();
		}
	});
}

function newProduct() {
	clearProduct();
	$('#detail').show();
}

function editProduct(id) {
	$.ajax({
		url : PRODUCTS_URI + '/' + id,
		type : 'GET',
		dataType : 'json',
		headers : {
			'Authorization' : token
		},
		success : function(data) {
			console.log('Edition mode');
			$.each(data,
					function(i, product) {
						$('#id').val(data.id);
						$('#name').val(data.name);
						$('#available').prop('checked', data.available);
						$('#price').val(data.price);
						$('#description').val(data.description);
						$('#dateCreated').val(new Date(data.dateCreated).toUTCString());
					});
			$('#detail').show();
		},
		error : function(request, status, error) {
			console.log(error);
			$('#detail').hide();
		}
	});
}

function deleteProduct(id) {
	if (confirm("Are you sure to delete the product?")) {
		$.ajax({
			url : PRODUCTS_URI + '/' + id,
			type : 'DELETE',
			contentType : 'application/json',
			dataType : 'json',
			headers : {
				'Authorization' : token
			},
			success : function(data) {
				console.log('Product ' + id + ' deleted!');
			},
			error : function(request, status, error) {
				console.log(error);
				if (request.status == 403) {
					alert('The user does not have sufficient permissions to execute this action')
				}
			},
			complete : function(data) {
				listProducts();
			}
		});
	}
}

function saveProduct() {
	var id = $('#id').val();
	var type = id.replace(/\s/g, '').length < 1 ? 'POST' : 'PUT';
	var dataJson = JSON.stringify({
		'id' : id,
		'name' : $('#name').val(),
		'available' : $('#available').prop('checked'),
		'price' : $('#price').val(),
		'description' : $('#description').val()
	});
	$.ajax({
		url : PRODUCTS_URI,
		type : type,
		contentType : 'application/json',
		data : dataJson,
		dataType : 'json',
		headers : {
			'Authorization' : token
		},
		success : function(data) {
			console.log('Product saved!');
			$('#detail').hide();
		},
		error : function(request, status, error) {
			console.log(error);
			if (request.status == 403) {
				$('#detail').hide();
				alert('The user does not have sufficient permissions to execute this action')
			}
		},
		complete : function(data) {
			listProducts();
		}
	});
}

function cancelEdition() {
	$('#detail').hide();
}

function clearProduct() {
	$('#id').val('');
	$('#name').val('');
	$('#available').prop('checked', false);
	$('#price').val('');
	$('#description').val('');
	$('#dateCreated').val('');
}
