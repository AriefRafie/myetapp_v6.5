<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT SUSULAN BANTAHAN DIDAFTARKAN.</div>";
</script>
#end

<!-- #if ($statusPerintah == "true")
    <div class="warning">
    Sila proses permohonan. Tempoh Bayaran Mahkamah Hampir Luput.
    </div>
#end -->


#if ($clearForm == "yes")
	#set ($txdTarikhLanjutanOB = "")
    #set ($txdTarikhLanjutanPT = "")
#end

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan)
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)    
#end 

#foreach ( $senarai in $getMaklumatBantahan )
    #set ($desc_status_bantahan=$senarai.desc_status_bantahan)
#end

#if($flag == "semakOb")
	#foreach ( $senarai in $getMaklumatSusulan)
    	#set ($txdTarikhLanjutanOB = $senarai.tarikh_lanjutan_mahkamah_ob)
    #end
#end

#if($flag == "semakPT")
	#foreach ( $senarai in $getMaklumatSusulan)
        #set ($txdTarikhLanjutanPT = $senarai.tarikh_lanjutan_mahkamah_pt)
    #end
#end

#if($flag == "semakBoth")
	#foreach ( $senarai in $getMaklumatSusulan)
        #set ($txdTarikhLanjutanOB = $senarai.tarikh_lanjutan_mahkamah_ob)
        #set ($txdTarikhLanjutanPT = $senarai.tarikh_lanjutan_mahkamah_pt)
    #end
#end

#if($flag == "")
	#foreach ( $senarai in $getMaklumatSusulan)
        #set ($txdTarikhLanjutanOB = $senarai.tarikh_lanjutan_mahkamah_ob)
        #set ($txdTarikhLanjutanPT = $senarai.tarikh_lanjutan_mahkamah_pt)
    #end
#end


#set ( $id_award = $id_award.get("id_award") )

#set ( $txtKeteranganPampasan = $getKeteranganPampasan.get("keterangan_pampasan") )

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>
<!------------------------------------------- END HEADER --------------------------------------------->  

<!------------------------------------------ TAB SUSULAN BANTAHAN -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="setSelectedTab(0);bantahan()">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="1" onClick="setSelectedTab(1);deposit()">Deposit</li>
    #if($!idBorangO_check != "")
    <li class="TabbedPanelsTab" tabindex="2" onClick="setSelectedTab(2);borangO()">Borang O</li>
    <li class="TabbedPanelsTab" tabindex="3" onClick="setSelectedTab(3);lanjutanTempoh()">Lanjutan Tempoh
    </li>
    
    <li class="TabbedPanelsTab" tabindex="4" onClick="setSelectedTab(4);susulanBantahan()">Perintah</li>
    <li class="TabbedPanelsTab" tabindex="5" onClick="setSelectedTab(5);pemulanganDeposit()">Pemulangan Deposit</li>
    <li class="TabbedPanelsTab" tabindex="6" onClick="setSelectedTab(6);tarikBalikBantahan()">Tarik Balik Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="7" onClick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div> 
     
    
    <div class="TabbedPanelsContent">
    
      #if ($mode=="disabledOb")
		#set($disabilityOb = "readonly")
		#set($disabilityxOb = "class=disabled")
		#else
		#set($disabilityOb = "")
		#set($disabilityxOb = "")
		#end
    
     #if ($mode=="disabledPT")
		#set($disabilityPT = "readonly")
		#set($disabilityxPT = "class=disabled")
		#else
		#set($disabilityPT = "")
		#set($disabilityxPT = "")
		#end
      
      #if ($mode=="disabledBoth")
		#set($disabilityPT = "readonly")
		#set($disabilityxPT = "class=disabled")
        #set($disabilityOb = "readonly")
		#set($disabilityxOb = "class=disabled")
		#else
        #set($disabilityOb = "")
		#set($disabilityxOb = "")
		#set($disabilityPT = "")
		#set($disabilityxPT = "")
		#end
 <!------------------------------------ MAKLUMBALAS MAHKAMAH ------------------------------------------>       

<!------------------------------------ END MAKLUMBALAS MAHKAMAH ------------------------------------------>
 
<!---------------------------------------- PERINTAH MAHKAMAH --------------------------------------------->

<br />
<fieldset>
<legend>Maklumat Lanjutan Tempoh</legend>
 <fieldset>
    	<legend>Orang Berkepentingan</legend>
	<table width="100%" border="0">
	   
		<tr>
            <td width="1%"></td>
			<td width="22%">Tarikh Lanjutan Mahkamah</td>
       	  	<td width="1%">:</td>
   	  	  	<td width="77%"><input type="text" $disabilityOb $disabilityxOb name="txdTarikhLanjutanOB" id="txdTarikhLanjutanOB" value="$!txdTarikhLanjutanOB" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="1" />
            #if ($mode!="disabledOB" || $mode!="disabledBoth")
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhLanjutanOB',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
        	#end
        	</td>  
		</tr>
     </table>
   </fieldset>
   <fieldset>
   		<legend>Pentadbir Tanah</legend>
     <table width="100%">
		<tr>
            <td width="1%"></td>
			<td width="22%">Tarikh Lanjutan Mahkamah</td>
       	  	<td width="1%">:</td>
   	  	  	<td width="77%"><input type="text" $disabilityPT $disabilityxPT name="txdTarikhLanjutanPT" id="txdTarikhLanjutanPT" value="$!txdTarikhLanjutanPT" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="1" />
            #if ($mode!="disabledPT" || $mode!="disabledBoth")
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhLanjutanPT',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
        	#end
        	</td>  
		</tr>                                                               
    </table>
    </fieldset>
</fieldset>

<!--------------------------------------- END PERINTAH MAHKAMAH ------------------------------------------>

          <div align="center">
          #if ($button=="view")
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniLanjutan()" />
          #end
          
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanLanjutan()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
          #end
          
          
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" />
              
        </div>   
    </div>
    
    
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB BANTAHAN ------------------------------------------->

</fieldset>



<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="id_award" id="id_award" value="$id_award" />

<script type="text/javascript">


function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetaksuratKepadaAPSupayaMembayarPampasanTambahan(id_fail,id_bantahan,id_permohonan) {
	//var url = "../servlet/ekptg.report.ppt.suratKepadaAPSupayaMembayarPampasanTambahan?idFail="+id_fail+"&id_bantahan="+id_bantahan;
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&report=suratKepadaAPSupayaMembayarPampasanTambahan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
	
	
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}
function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function simpanLanjutan(){
	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanLanjutan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();
	
}

function kemaskiniLanjutan(){
	document.${formName}.command.value = "kemaskiniLanjutan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}


function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
function validateDate(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumericDate(content);
		return;
	}
}
function RemoveNonNumericDate( strString )
{
      var strValidCharacters = "1234567890/";
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
<!--UTK DEFAULTKAN TAB KEPADA TAB BANTAHAN
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>
