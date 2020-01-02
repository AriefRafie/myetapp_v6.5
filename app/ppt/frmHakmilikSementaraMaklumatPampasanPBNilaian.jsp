<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPampasanPB.jsp") 
<p>


#foreach ( $dataAmaun in $paparAmaun )
	#set ( $txtAmaunNilaiSebln = $dataAmaun.NILAIAN_JPPH )
#end


<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   <li class="TabbedPanelsTab" tabindex="0"  onclick="tabTuntutan('$idFail','$id_permohonan','$idPihakBerkepentingan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$id_permohonan','$idPihakBerkepentingan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTerimaCek('$idFail','$id_permohonan','$idPihakBerkepentingan')">Penerimaan Cek</li>
     #if($idBayaran != '')
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSerahCek('$idFail','$id_permohonan','$idPihakBerkepentingan')">Penyerahan Cek</li>
    #end
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabEFT('$idFail','$id_permohonan','$idPihakBerkepentingan')">Bayaran Melalui EFT</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    
    <div class="TabbedPanelsContent">
	
         ##if($error)
<!--    <script>
parent.document.getElementById("error").innerHTML="<div class=\"error\">Sila daftar surat terima dari JPPH terlebih dahulu. Sila klik <a href=1246417702142?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH&action=viewMaklumat&id_permohonan=$id_permohonan <font color=\"blue\">di sini</font></a></div>";
</script>-->
    	##else
        
        <table width="100%">
  <tr>
    <td align="left" width="1%"><span class="style1"><span class="style2">#if($modeNilaian == 'baruNilaian')</span>*<span class="style2">#end</span></span></td>
    <td align="left" width="19%">No. Rujukan Surat</td>
    <td width="1%">:</td>
    <td width="27%"><label>
    
    #if($modeNilaian == 'baruNilaian')   
      <input name="txtNoRujSurat" type="text" id="txtNoRujSurat" value="$!NO_RUJUKANSURATJT" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" >
    #else 
      <input name="txtNoRujSurat" type="text" id="txtNoRujSurat" value="$!NO_RUJUKANSURATJT"  $readonlyNilaianA class = "$disabledNilaianA" $disabledNilaianA >
    #end
    
    </label></td>
    <td align="left" width="1%"><span class="style1"><span class="style2">#if($modeNilaian == 'baruNilaian')</span>*<span class="style2">#end</span></span></td>
    <td align="left" width="21%">Tarikh Terima Surat</td>
    <td width="1%">:</td>
    <td width="30%"><label>
    
    #if($modeNilaian == 'baruNilaian')
        <input name="txdTkhTerimaSurat" type="text" id="txdTkhTerimaSurat" value="$!txdTkhTerimaSurat" size="10" onblur="check_date(this);"  onkeyup="validateNumber(this,this.value);" >  <a href="javascript:displayDatePicker('txdTkhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a><i><font color='blue' style='font-size:10px'> dd/mm/yyyy</font></i>
    #else    
        <input name="txdTkhTerimaSurat" type="text" id="txdTkhTerimaSurat" value="$!TARIKH_TERIMAJT" size="10" $readonlynilaianA class = "$disabledNilaianA" $disablednilaiana onblur="check_date(this);"  onkeyup="validateNumber(this,this.value);" >  <a href="javascript:displayDatePicker('txdTkhTerimaSurat',false,'dmy');" /> <a href="javascript:displayDatePicker('txdTkhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a><i><font color='blue' style='font-size:10px'> dd/mm/yyyy</font></i>        
    #end
        

    
    
    
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1"><span class="style2">#if($modeNilaian == 'baruNilaian')</span>*<span class="style2">#end</span></span></td>
    <td align="left">Tarikh Surat</td>
    <td>:</td>
    <td><label>
    
    #if($modeNilaian == 'baruNilaian') 
      <input name="txdTkhSurat" type="text" id="txdTkhSurat" value="$!TARIKH_SURATJT" size="10" onblur="check_date(this);"  onkeyup="validateNumber(this,this.value);" >  <a href="javascript:displayDatePicker('txdTkhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a><i><font color='blue' style='font-size:10px'> dd/mm/yyyy</font></i>
    #else
      <input name="txdTkhSurat" type="text" id="txdTkhSurat" value="$!TARIKH_SURATJT" size="10" $readonlyNilaianA class = "$disabledNilaianA" $disabledNilaianA   onblur="check_date(this);"  onkeyup="validateNumber(this,this.value);" >  <a href="javascript:displayDatePicker('txdTkhSurat',false,'dmy');" > <img src="../img/calendar.gif" alt="" border="0"/></a><i><font color='blue' style='font-size:10px'> dd/mm/yyyy</font></i>
    #end  
      
    </label></td>
    <td width="1%" align="left"><span class="style1"><span class="style2">#if($modeNilaian == 'baruNilaian')</span>*<span class="style2">#end</span></span></td>
    <td align="left">Amaun Nilaian Sebulan (RM)</td>
    <td>:</td>
    <td><label>
    
          #if ($modeNilaian == 'baruNilaian')          
		  <input type="text" size="11" name="txtAmaunNilaiSebln" id="txtAmaunNilaiSebln" value="$!txtAmaunNilaiSebln" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunNilaiSebln')" onkeyup="validateNumber(this,this.value);" />
<input type="hidden" name="txtAmaunNilaiSebln2" id="txtAmaunNilaiSebln2" value="$!txtAmaunNilaiSebln" />                    
		  #else
<!--          <input type="text" size="11" name="txtAmaunNilaiSebln" id="txtAmaunNilaiSebln" value="$!Util.formatDecimal($!txtAmaunNilaiSebln)" maxlength="12" $readonlyNilaianA class = "$disabledNilaianA" $disabledNilaianA  />  -->   

          <input type="text" size="11" name="txtAmaunNilaiSebln" id="txtAmaunNilaiSebln" value="$!txtAmaunNilaiSebln" maxlength="12" $readonlyNilaianA class = "$disabledNilaianA" $disabledNilaianA  />                         
		  #end
      
    </label></td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
    <td colspan="8">
    #if($modeNilaian == 'paparNilaian')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniNilaian()" />
     <!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"  />-->
	#end
    #if($modeNilaian == 'kemaskiniNilaian')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateNilaian()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewNilaian()" />
    #end
    #if($modeNilaian == 'baruNilaian')
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateNilaian()" />
    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewNilaian()" />
    #end
    <!--
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliNilaian()" />
    -->
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutanNew('$!idFail','$!id_permohonan')" />
    </td>
        </tr>
</table>
    
    ##end
    
    
    
    
    
    
    
    </div>
    
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>

<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakMMK()"><font color="blue">Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<input type="hidden" name="id_fail" id="id_fail" value="$idFail" />
<!--<input type="hidden" name="id_permohonan" value="$id_permohonan" />-->
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input name="idPB" type="hidden" value="$idPihakBerkepentingan" />
<input name="id_hakmilik" type="hidden" value="$id_hakmilik" />
<input name="idSiasatan" type="hidden" value="$ID_SIASATAN" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idTanah" type="hidden" value="$idTanah" />
<input name="idBayaran" type="hidden" value="$idBayaran" />
<input name="idBayaranEFT" type="hidden" value="$idBayaranEFT" />
<input type="hidden" name="id_status" value="$!id_status">


<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
</script>
<script>
function kembaliTuntutanNew(id_fail,id_permohonan){
	
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTuntutan";
	document.${formName}.submit();
}
function tabTerimaCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTerimaCek";
	document.${formName}.submit();
}
function tabSerahCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabSerahCek";
	document.${formName}.submit();
}
function tabEFT(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
	document.${formName}.submit();
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();
}
function kemaskiniNilaian(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=kemaskiniNilaian";
	document.${formName}.submit();
}
function simpanNilaian(){

	if(document.${formName}.txtAmaunNilaiSebln.value == ""){
		alert("Sila masukkan \"Amaun Nilaian Sebulan\" terlebih dahulu.");
  		document.${formName}.txtAmaunNilaiSebln.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=simpanNilaian";
	document.${formName}.submit();
}
function updateNilaian(){

	if(document.${formName}.txtAmaunNilaiSebln.value == ""){
		alert("Sila masukkan \"Amaun Nilaian Sebulan\" terlebih dahulu.");
  		document.${formName}.txtAmaunNilaiSebln.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=updateNilaian";
	document.${formName}.submit();
}
function kembaliNilaian() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=newPerundingan";
	document.${formName}.submit();

}
function batalViewNilaian() {

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();

}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
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
</script>