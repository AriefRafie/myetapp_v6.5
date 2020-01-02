<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />

<!--<form id="f1" name="f1" method="post" action="">
<input name="command" type="hidden" value="$command" /> -->
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="$mode" />
<input name="agendaMsyrt" type="hidden" value="$agendaMsyrt" />
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">
   #foreach ($agenda in $AgendaMesyuarat)
 <tr>
   <!--<input name="idAgendamesyuarat" type="hidden" value="$agenda.id_Agendamesyuarat" />
    <td width="29%" align="left" scope="row"><div align="right" class="style1">No Fail</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">$agenda.noFail 
     <label>idFailLain
        <textarea name="txtAgenda" cols="44" id="txtAgenda" $readonly="$readOnly">$agenda.agenda_Mesyuarat</textarea>
        </label>        
        </td>-->
        <td><a href="javascript:kemaskini('$agenda.noFail','$agenda.idFailLain');">$agenda.noFail</a></td>
   </tr>
  #end 
  <tr>
    <td colspan="3" align="center" scope="row">&nbsp;</td>
    </tr>
   <tr>
   	   <td width="29%" align="left" scope="row" valign="top"><div align="right" class="style1">No. Fail</div></td>
    	<td width="1%" scope="row" valign="top"><div align="right">:</div></td>
    	<td width="70%"> 
     	<label>
        	<textarea name="txtAgenda" cols="44" id="txtAgenda" ></textarea>
        </label>        
        </td>
   </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
    	<input name="idAgendamesyuarat" type="hidden" />
    	<input name="idMesyuarat" type="hidden" value="$idFailLama" />
    	</td>
    </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
     #if ($mode == 'View')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
     #else
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"  />
     #end  
     <input type="button" name="cmdPapar" id="cmdPapar" value="Hapus" style="display:none" onclick="hapus()">     
   <!--<input type="button" name="cmdrefresh" value="F"  onclick="fungsiRefresh()">-->	     
     </td>
  </tr>
</table>
</fieldset>
<!--<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-21</strong></td>
  </tr>
</table> 
 </form>-->
<script>
function batal(){

	window.close();
}
function kembali(){

	window.close();
}
function simpanLama(){
if ( !window.confirm("Anda Pasti?") ) return;
	document.f1.action = "";
	document.f1.command1.value = "simpan";
	if (document.f1.idAgendamesyuarat.value == "" || document.f1.idAgendamesyuarat.value == 0){
		
		document.f1.mode.value = "tambahBaru";
	}
	else{
		document.f1.mode.value = "kemaskiniAgenda";
	}
	
	document.f1.submit();
	/*window.close();
	if (window.opener && !window.opener.closed) {
		window.opener.location.reload();
	}*/
	//parent.refresh12();
}



function simpan(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.command1.value = "simpan";
	if (document.${formName}.idAgendamesyuarat.value == "" || document.${formName}.idAgendamesyuarat.value == 0){		
		document.${formName}.mode.value = "tambahBaru";
	}else{		
		document.${formName}.command.value = "papar";		
		document.${formName}.mode.value = "kemaskiniAgenda";
	}
	
	document.${formName}.submit();
	fungsiRefresh();
}

function hapus(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.command1.value = "hapus";	
	doAjaxCall${formName}("hapus");
	fungsiRefresh();
}

function kemaskini(nofail,id){

	document.${formName}.txtAgenda.value=nofail;
	document.${formName}.idAgendamesyuarat.value=id;	
	document.${formName}.command.value = "papar";
	document.${formName}.cmdPapar.style.display = "";
	
}

function paparFailLain(id,command){

	var url = "../x/${securityToken}/ekptg.view.pfd.FrmFailLainKemaskini?agendaMsyrt="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
 
function fungsiRefresh(){
	if (window.opener && !window.opener.closed) {
		window.opener.papar(document.${formName}.idMesyuarat.value);
		}
}

</script>