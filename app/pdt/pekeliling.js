function getDisableFieldDiv(div,mode) {
	
	//alert('masuk div == '+ div);
    var elements = document.getElementById(div);
	//alert('elements == '+ elements);
	var inputElements = elements.querySelectorAll("input, select, checkbox, radio, textarea");
	//alert("inputElements == " + inputElements);
    //var inputTypes = ['text', 'select', 'textarea','input'];
	
	if(mode=='View'){
		for (var i = 0; i < inputElements.length; i++) {
			//alert("t == "+inputElements[i].type);
			var elm = inputElements[i];
			//alert("t == "+inputElements[i].type+" elem : "+elm);
			elm.class = 'disabled';
			elm.disabled = 'disabled'; 
						
		}	
	}
	
}