var Analiseskillus = Analiseskillus || {};

Analiseskillus.UploadFoto = (function() {
	
	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		this.novaFoto = $('input[name=novaFoto]');
		
		this.htmlFotoUsuarioTemplate = $('#foto-usuario').html();
		this.template = Handlebars.compile(this.htmlFotoUsuarioTemplate);
		
		this.containerFotoUsuario = $('.js-container-foto-usuario');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoUsuario.data('url-fotos'),
			complete: onUploadCompleto.bind(this),
			beforeSend: adicionarCsrfToken
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);

		if (this.inputNomeFoto.val()) {
			renderizarFoto.call(this, { nome: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}
	}
	
	function onUploadCompleto(resposta) {
		this.novaFoto.val('true');
		renderizarFoto.call(this, resposta);
	}
	
	function renderizarFoto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		this.uploadDrop.addClass('hidden');
		
		var foto = '';
		if(this.novaFoto.val() == 'true') {
			foto = 'temp/';
			foto += resposta.nome;
		}
		
		var htmlFotoUsuario = this.template({foto: resposta.nome});
		this.containerFotoUsuario.append(htmlFotoUsuario);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-usuario').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	function adicionarCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}
	
	return UploadFoto;
	
})();

$(function() {
	var uploadFoto = new Analiseskillus.UploadFoto();
	uploadFoto.iniciar();
});