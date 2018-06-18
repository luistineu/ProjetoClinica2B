jQuery('a.btn.animais').click(function(){
	event.preventDefault();
	var url = jQuery(this).attr('href');
	
	
	jQuery.getJSON('./GetAnimais', function(data){
		console.log(data);
	    this.retorno = '';
	    for(i in data.animais){
	        this.retorno += '<div id="' + data.animais[i].id + '" class="item"> Nome: ' + data.animais[i].nome + ' | Nascimento: ' + data.animais[i].nascimento + ' | Espécie: ' + data.animais[i].especie.nome + '</div> <br />';

	        this.retorno += '<br />';
	    }                       

	    jQuery('#resultado').html(this.retorno);
	});
});

jQuery('a.btn.especies').click(function(){
	event.preventDefault();
	var url = jQuery(this).attr('href');
	
	
	jQuery.getJSON('./GetEspecies', function(data){
		console.log(data);
	    this.retorno = '';
	    for(i in data.especies){
	        this.retorno += '<div id="' + data.especies[i].id + '"> Nome: ' + data.especies[i].nome + ' | Descrição: ' + data.especies[i].descricao + '</div> <br />';

	        this.retorno += '<br />';
	    }                       

	    jQuery('#resultado').html(this.retorno);
	});
});

jQuery('a.btn.tipo').click(function(){
	event.preventDefault();
	var url = jQuery(this).attr('href');
	
	
	jQuery.getJSON('./GetTipoAnimais', function(data){
		console.log(data);
	    this.retorno = '';
	    for(i in data.tipoAnimais){
	        this.retorno += '<div id="' + data.tipoAnimais[i].acronimo + '" > Acrônimo: ' + data.tipoAnimais[i].acronimo + ' | Nome: ' + data.tipoAnimais[i].nome + ' | Descrição: ' + data.tipoAnimais[i].descricao + '</div> <br />';

	        this.retorno += '<br />';
	    }                       

	    jQuery('#resultado').html(this.retorno);
	});
});

jQuery('.item').click(function(){
	jQuery('.selected').removeClass('selected');
	jQuery(this).addClass('selected');
});