<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPNWHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610210' && $idStatus != '1610201')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
          #if($keputusan ==  'L')
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">KEMENTERIAN / AGENSI</li>
       	  #end 
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            #parse("app/php2/frmPNWKeputusanDetail.jsp")
          </div>
           #if($keputusan ==  'L')
          <div class="TabbedPanelsContent">
           #parse("app/php2/frmPNWKeputusanDetailAgensi.jsp")
          </div>
          #end
        </div>
      </div></td>
  </tr>
  #end
</table>

<script type="text/javascript">
#if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610210' && $idStatus != '1610201')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	if (content != ''){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	}	
}
function calculateTotal() {
	url = "../servlet/ekptg.view.php2.FrmPNWServlet?command=calculateTotal";
	actionName = "calculateTotal";
	target = "calculateTotal_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function setTotalLuas(total){
	document.${formName}.txtJumlahLuas.value = total;
}
function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function simpanKemaskiniAgensi() {
	var luasTawar = document.${formName}.txtLuasTawar.value * 1;
	var jumlahLuas = document.${formName}.txtJumlahLuas.value * 1;
	if (jumlahLuas > luasTawar){
		alert('Jumlah luas yang dimasukkan telah melebihi luas yang ditawarkan.')
		return;
	} else if (jumlahLuas < luasTawar) {
		alert('Jumlah luas yang dimasukkan kurang daripada luas yang ditawarkan.')
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniAgensi";
	doAjaxCall${formName}("");
}

function simpanKeputusan(idStatus) {
	if(document.${formName}.txtTarikhKeputusan.value == ""){
		alert('Sila masukkan Tarikh Hantar Surat');
  		document.${formName}.txtTarikhKeputusan.focus(); 
		return; 
	}
	
	if ($idStatus == '1610206'){
		if(document.${formName}.socKeputusan.value == ""){
			alert('Sila pilih Keputusan');
			document.${formName}.socKeputusan.focus(); 
			return; 
		}
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKeputusan";
	doAjaxCall${formName}("");
}

function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>