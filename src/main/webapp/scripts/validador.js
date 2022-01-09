/**
 * validação
 */
 function validar(){
	         //formulário.campo.valor
	let nome = frmContato.nome.value
	let telefone = frmContato.telefone.value
	
	if(nome === ""){
		alert('Preencha o campo nome')
		frmContato.nome.focus()
		return false
	} else if(telefone === ""){
		alert('Preencha o campo telefone')
		frmContato.telefone.focus()
		return false
	}else{
		//vai submeter os dados para a camada controller
		document.forms["frmContato"].submit()
	}
}

//O problema estava aqui - erro de digitação