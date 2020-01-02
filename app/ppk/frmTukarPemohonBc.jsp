<br/>
	<fieldset><legend><b>Semakan Nota Bicara</b></legend>
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!nofail" size="35" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemak" id="cmdSemak" value="Semak" type="button" onclick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan()"></td>
    		</tr>
    		
		</table>
	</fieldset>

#if($showInput=="yes")

	#if($dataNotaBicara.size()!=0)
		#foreach($data in $dataNotaBicara)
			#set($notaBicara = $data.nota_bicara)
		#end
	#else
		#set($notaBicara = "")
	#end
	
	#if($mode=="new" || $mode=="edit")
		#set($check = "")	
	#else
		#set($check = "disabled class='disabled'")	
	#end	
	
	<br/>
	<fieldset>
	<legend><b>Nota Bicara</b></legend>
		<table width="100%" align="center" border="0">
			<tr align="center">
				<td><textarea name="txtNotaBicara" id="txtNotaBicara" $check cols="100%" rows="15">$!notaBicara</textarea><br/></td>
			</tr>
		</table>
		<table width="750" align="center" border="0">
			<tr>
	 			<td><b>Jumlah Perkataan :&nbsp;</b>$!totalWordNotaBicara &nbsp;&nbsp;<b>Bayaran Nota Bicara :&nbsp;</b>RM$!bayaranNB &nbsp;<b></td>
	 		</tr>
	 	</table>	
	</fieldset>
	
	
	#if($mode=="new")   
    		<input type="hidden" id="contentoE" value="true">
    		<input type="hidden" id="designoE" value="on">
    #elseif($mode=="edit") 
    		<input type="hidden" id="contentoE" value="true">
    		<input type="hidden" id="designoE" value="on">
    #else
    		<input type="hidden" id="contentoE" value="false">
    		<input type="hidden" id="designoE" value="off">		
    #end
	
	<script> 
            var area = new FCKeditor("txtNotaBicara");
	      	area.BasePath = '/${appname}/library/fck/' ;
	      	area.Height = 300;
	      	area.Width = 750;
	      	area.ReplaceTextarea();     
	
   		 	var contentoE= document.getElementById("contentoE").value;
   		 	var designoE= document.getElementById("designoE").value;
	
    		var oEditor = FCKeditorAPI.GetInstance('txtNotaBicara');
    	
    		function FCKeditor_OnComplete(oEditor)
    		{
    			oEditor.EditorDocument.body.contentEditable=contentoE;
    			oEditor.EditorDocument.designMode=designoE; 	
    			if(contentoE=="false"){
    				oEditor.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}	
    		}
	</script> 
	
	
		<table width="100%" align="center" border="0">
			<tr align="center">
				<td>
					#if($mode=="new")
						<input name="cmdSimpan" id="cmdSimpan" value="Simpan" type="button" onclick="javascript:simpanNotaBicara()">
					#elseif($mode=="view")
						<input name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" type="button" onclick="javascript:kemaskiniNotaBicara()">
					#elseif($mode=="edit")	
						<input name="cmdUpdate" id="cmdUpdate" value="Simpan" type="button" onclick="javascript:updateNotaBicara('$id_notabicara')">
						<input name="cmdBatal" id="cmdBatal" value="Batal" type="button" onclick="javascript:batalNotaBicara('$!nofail')">
					#end
				</td>
			</tr>
		</table>
	
	
#elseif($showInput=="no")
	<fieldset>
		<table width="100%" align="center" border="0">
			<tr>
				<td>Tiada Rekod</td>
			</tr>
		</table>
	</fieldset>
#else	
#end

<input type="hidden" name="id_notabicara" value="$id_notabicara">
<input type="hidden" name="doaction2">
<input type="hidden" name="doaction">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function kosongkan() {
	doAjaxCall${formName}("kosongkan");
}
function search_data(){
	//doAjaxCall${formName}("cari");
	document.${formName}.command.value = "cari";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanNotaBicara(){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	//doAjaxCall${formName}("cari","doaction=simpanNotaBicara");
	document.${formName}.command.value = "cari";
	document.${formName}.doaction.value = "simpanNotaBicara";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kemaskiniNotaBicara(){
	//doAjaxCall${formName}("cari","doaction=kemaskiniNotaBicara");
	document.${formName}.command.value = "cari";
	document.${formName}.doaction.value = "kemaskiniNotaBicara";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function updateNotaBicara(idnotabicara){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	//doAjaxCall${formName}("cari","doaction=kemaskiniNotaBicara&doaction2=updateNotaBicara&id_notabicara="+idnotabicara);
	document.${formName}.command.value = "cari";
	document.${formName}.doaction.value = "kemaskiniNotaBicara";
	document.${formName}.doaction2.value = "updateNotaBicara";
	document.${formName}.id_notabicara.value = idnotabicara;
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function batalNotaBicara(nofail){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	//doAjaxCall${formName}("cari","txtNoFail="+nofail);
	document.${formName}.command.value = "cari";
	document.${formName}.txtNoFail.value = nofail;
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>