#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($!portal_user=="(PPT)PengarahUnit")
	#set($labelList="Hakmilik Yang Telah Diagihkan")
	#set($tabPgh="")
#else
	#set($labelList="Hakmilik Yang Telah Diagihkan Oleh Pengarah")
	#set($tabPgh="style='display:none'")
#end

<fieldset id="top">
<legend><strong>Agihan Tugas</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")


<br/>
	
	<input name="tabId" type="hidden" id="tabId" value="$!selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
      
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" $tabPgh onClick="javascript:setSelected(0);screenAgihan('$!id_permohonan')" tabIndex="1" >Agihan Pengarah</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);AgihanLayer2('$!id_permohonan')" tabIndex="1" >Agihan Pentadbir Tanah</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      
      		<!-- START TAB 1 -->
	        <div class="TabbedPanelsContent">
	        	#parse("app/ppt/AgihanS8/frmTABAgihanPengarah.jsp")
	        </div>
	        
	        <!-- START TAB 2 -->
	        <div class="TabbedPanelsContent">
	        	#parse("app/ppt/AgihanS8/frmTABAgihanPenolong.jsp")
	        </div>
	        
      </div>
      
  	</div>
	
			
</fieldset>


<input type="hidden" name="id_permohonan" value="$!id_permohonan">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>

function autoagih_PT()
{

if(document.${formName}.start_PT.value == "" )
{
alert("Sila masukkan nilai mula")
document.${formName}.start_PT.focus();
}
else if(document.${formName}.end_PT.value == "" )
{
alert("Sila masukkan nilai akhir")
document.${formName}.end_PT.focus();
}
else if(parseInt(document.${formName}.start_PT.value) < 0 )
{
alert("Sila pastikan nilai mula melebih dari 0")
document.${formName}.start_PT.focus();
}
else if(parseInt(document.${formName}.end_PT.value) < 0 )
{
alert("Sila pastikan nilai akhir melebih dari 0")
document.${formName}.end_PT.focus();
}
else if(parseInt(document.${formName}.start_PT.value) > parseInt(document.${formName}.end_PT.value)  )
{
alert("Sila pastikan nilai mula lebih kecil ataupun sama dengan nilai akhir")
document.${formName}.start_PT.focus();
}
else if(parseInt(document.${formName}.end_PT.value) > parseInt('$saiz_listTanah'))
{
alert("Sila pastikan nilai akhir lebih kecil ataupun sama dengan jumlah lot")
document.${formName}.end_PT.focus();
}
else
{
var start = parseInt(document.${formName}.start_PT.value)-1;
var end = parseInt(document.${formName}.end_PT.value)-1;
if(document.${formName}.cbsemaks.length > 1)
{  

      for (k = 0; k < document.${formName}.cbsemaks.length; k++)
	  {	
      document.${formName}.cbsemaks[k].checked = false;	  
	  }  

   
	  for (i = start; i <= end; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = true;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	
}

}

function autoagihX_PT()
{


if(document.${formName}.cbsemaks.length > 1)
{     
	  for (i = 0; i <= document.${formName}.cbsemaks.length; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = false;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	

}

function autoagih()
{

if(document.${formName}.start.value == "" )
{
alert("Sila masukkan nilai mula")
document.${formName}.start.focus();
}
else if(document.${formName}.end.value == "" )
{
alert("Sila masukkan nilai akhir")
document.${formName}.end.focus();
}
else if(parseInt(document.${formName}.start.value) < 0 )
{
alert("Sila pastikan nilai mula melebih dari 0")
document.${formName}.start.focus();
}
else if(parseInt(document.${formName}.end.value) < 0 )
{
alert("Sila pastikan nilai akhir melebih dari 0")
document.${formName}.end.focus();
}
else if(parseInt(document.${formName}.start.value) > parseInt(document.${formName}.end.value)  )
{
alert("Sila pastikan nilai mula lebih kecil ataupun sama dengan nilai akhir")
document.${formName}.start.focus();
}
else if(parseInt(document.${formName}.end.value) > parseInt('$saiz_listTanah'))
{
alert("Sila pastikan nilai akhir lebih kecil ataupun sama dengan jumlah lot")
document.${formName}.end.focus();
}
else
{
var start = parseInt(document.${formName}.start.value)-1;
var end = parseInt(document.${formName}.end.value)-1;
if(document.${formName}.cbsemaks.length > 1)
{  

      for (k = 0; k < document.${formName}.cbsemaks.length; k++)
	  {	
      document.${formName}.cbsemaks[k].checked = false;	  
	  }  

   
	  for (i = start; i <= end; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = true;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	
}

}

function autoagihX()
{


if(document.${formName}.cbsemaks.length > 1)
{     
	  for (i = 0; i <= document.${formName}.cbsemaks.length; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = false;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	

}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
	}
}

function simpanAgihanLayer2() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "AgihanLayer2";
	document.${formName}.command2.value = "simpanAgihanLayer2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function onChangeLayer2() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "AgihanLayer2";
	document.${formName}.command2.value = "onChangeLayer2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function AgihanLayer2(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "AgihanLayer2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function screenAgihan(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "screenAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function simpanAgihanLayer1() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "screenAgihan";
	document.${formName}.command2.value = "simpanAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function onChangePP() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "screenAgihan";
	document.${formName}.command2.value = "onChangePP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8";
	document.${formName}.submit();
}
</script>


<!-- FOR CHECKBOX -->
<script language="javascript">
//var checked = false;
function checkALL() {
	 if (document.${formName}.checkall.checked == true){
	
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	 } else {

	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;
	            }
	        }
	 }
}
function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	}
	else{
		document.${formName}.checkall.checked = true;
	}       
}










function checkALLX() {
	 if (document.${formName}.checkallX.checked == true){
	
	        if (document.${formName}.cbsemaksX.length == null){
	            document.${formName}.cbsemaksX.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaksX.length; i++){
	                document.${formName}.cbsemaksX[i].checked = true;
	            }
	        }
	 } else {

	        if (document.${formName}.cbsemaksX.length == null){
	            document.${formName}.cbsemaksX.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaksX.length; i++){
	                document.${formName}.cbsemaksX[i].checked = false;
	            }
	        }
	 }
}
function doUpdateCheckAllX(){  

	var c = 0;
	if(document.${formName}.cbsemaksX.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaksX.length; i++){
	      if (document.${formName}.cbsemaksX[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaksX.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkallX.checked = false;
	}
	else{
		document.${formName}.checkallX.checked = true;
	}       
}




function cetakLaporanSiasatan() {

	if(document.${formName}.socPejabatHeader.value == ""){
		alert("Sila pilih \"Pejabat JKPTG\" terlebih dahulu.");
  		document.${formName}.socPejabatHeader.focus(); 
		return;
	}
	else 
	if(document.${formName}.socTahunHeader.value == ""){
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
  		document.${formName}.socTahunHeader.focus(); 
		return;
	}
	else 
	if(document.${formName}.socBulanHeader.value == ""){
		alert("Sila pilih \"Bulan (sehingga)\" terlebih dahulu.");
  		document.${formName}.socBulanHeader.focus(); 
		return;
	}
	else{

		var url = "../servlet/ekptg.report.ppt.LaporanJadualSiasatan?BULAN="+document.${formName}.socBulanHeader.value+"&TAHUN="+document.${formName}.socTahunHeader.value+"&ID_PEJABAT="+document.${formName}.socPejabatHeader.value+"&ID_DAERAH="+document.${formName}.socDaerahHeader.value+"";
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
}
</script>

<script>
window.onload = submitForm;
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}

	if('$openDetail' == "yes"){
		document.getElementById("showHeader1").style.display="";
		document.getElementById("showHeader2").style.display="";
	}
	
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>