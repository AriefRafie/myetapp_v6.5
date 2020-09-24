<style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-style: italic;
}
-->
</style>
<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT BATAL BANTAHAN DIDAFTARKAN.</div>";
</script>
#end

#if ($clearForm=="yes")
	#set ($txdTkhTerimaSurat = "")
    #set ($txdTkhSurat = "")
    #set ($txtNoRujSurat = "")
    #set ($txtCatatanBatalMahkamah = "")
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
	#set ($status_bantahan=$senarai.status_bantahan)
    #set ($desc_status_bantahan=$senarai.desc_status_bantahan)
#end

#if ($flag=="semak")
    #foreach ( $senarai in $getMaklumatBatalMahkamah )
    	#set ($txdTkhTerimaSurat = $senarai.tarikh_terima_batalmahkamah)
        #set ($txdTkhSurat = $senarai.tarikh_surat_batalmahkamah)
        #set ($txtNoRujSurat = $senarai.no_rujukan_surat_batalmahkamah)  
        #set ($txtCatatanBatalMahkamah = $senarai.catatan_batal_mahkamah)      
    #end
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>


<!------------------------------------------ TAB SUSULAN BANTAHAN -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);bantahan()">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);deposit()">Deposit</li>
    #if($!idBorangO_check != "")
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);borangO()">Borang O</li>
    <li class="TabbedPanelsTab" tabindex="3" onclick="setSelectedTab(3);lanjutanTempoh()">Lanjutan Tempoh</li>
    <li class="TabbedPanelsTab" tabindex="4" onclick="setSelectedTab(4);susulanBantahan()">Perintah</li>
    <li class="TabbedPanelsTab" tabindex="5" onclick="setSelectedTab(5);pemulanganDeposit()">Pemulangan Deposit</li>
    <li class="TabbedPanelsTab" tabindex="6" onclick="setSelectedTab(6);tarikBalikBantahan()">Tarik Balik Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="7" onclick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT
    </li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div> 
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
<!------------------------------------ MAKLUMAT PEMBATALAN BANTAHAN ------------------------------------------>       

##if ($status_bantahan == "184")
<!--        <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
            <td>
        	<td>
        	<i>
            <font color="#FF0000" style="font-size:10px">PERHATIAN</font>
            <font style="font-size:10px">: PERMOHONAN TARIK BALIK BANTAHAN TIDAK DIBENARKAN KERANA PERINTAH MAHKAMAH TELAH KELUARKAN.</font>&nbsp;
            </i>
      		</td>
      		</td>
            </tr>
      	</table>-->
##end

<fieldset>
<legend>Maklumat Pembatalan Bantahan</legend>  
	<table width="100%" border="0">
    	<tr>
            <td width="1%"></td>
          <td width="21%">Tarikh Surat Diterima</td>
   	  	  <td width="1%">:</td>
       	  	<td width="78%">
            #if($mode=="disabled")
            <input type="text" name="txdTkhTerimaSurat" id="txdTkhTerimaSurat" value="$!txdTkhTerimaSurat" size="10" class="disabled" readonly />
          	#else
            <input type="text" name="txdTkhTerimaSurat" id="txdTkhTerimaSurat" value="$!txdTkhTerimaSurat" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="1" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaSurat',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            #end          </td>  
        </tr> 
    	<tr>
            <td width="1%"></td>
          <td width="21%">Tarikh Surat</td>
   	  	  <td width="1%">:</td>
       	  	<td width="78%">
            #if($mode=="disabled")
            <input type="text" name="txdTkhSurat" id="txdTkhSurat" value="$!txdTkhSurat" size="10" class="disabled" readonly />
          	#else
            <input type="text" name="txdTkhSurat" id="txdTkhSurat" value="$!txdTkhSurat" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="2" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhSurat',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            #end            </td>  
        </tr> 
    	<tr>
            <td width="1%">#if ($mode=="") <font color="red">*</font> #end</td>
          <td width="21%">
            
            No Ruj. Surat
                      </td>
       	  	<td width="1%">:</td>
       	  	<td width="78%">
            #if($mode=="disabled")
            <input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" class="disabled" readonly />
            #else
            <input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" tabindex="3" />
            #end            
            
            </td>  
        </tr>
        <tr>
            <!-- PPT-39 (i) -->
        	<!--  td colspan="3"><span class="style1">DEPOSIT : TIDAK DIKEMBALIKAN
            </span></td-->
            
			<!-- PPT-39 (ii) --> <!-- TEMP
            <td width="1%"></td>
            <td>Surat Pembatalan Bantahan</td>
            <td>:</td>
            <td><input $disOtherId1 $disOtherIdx id="fileupload" name="fileupload" type="file" size="60" /></td>-->
        </tr>
    	<tr>
          <td width="1%"></td>
    	  <td valign="top">Catatan</td>
    	  <td valign="top">:</td>
    	  <td>
          
          #if ($mode=="disabled")
          <textarea name="txtCatatanBatalMahkamah" id="txtCatatanBatalMahkamah" cols="85" rows="6" class="disabled" readonly>$!txtCatatanBatalMahkamah</textarea>
          #else
          <textarea name="txtCatatanBatalMahkamah" id="txtCatatanBatalMahkamah" cols="85" rows="6" onKeyDown="textCounter(this.form.txtCatatanBatalMahkamah,this.form.remLentxtCatatanBatalMahkamah,400);" onKeyUp="textCounter(this.form.txtCatatanBatalMahkamah,this.form.remLentxtCatatanBatalMahkamah,400);">$!txtCatatanBatalMahkamah</textarea>
          #end          
          
          </td>
  	  </tr> 
    	<tr>
          <td width="1%"></td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
    	  <td><input type="text" readonly class="disabled" name="remLentxtCatatanBatalMahkamah" size="3" maxlength="3" value="400"> Baki Aksara</td>
  	  </tr>                              
    </table>
</fieldset>

<!------------------------------------ END PEMBATALAN BANTAHAN  ------------------------------------------>
        <div align="center">   
          #if ($button=="view")        
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBatalBantahan()" /> 
          <!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />-->   
          #end
        
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpan_batalMahkamah()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
          #end                    
                  
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" />
           
        </div>    
    </div>
    
    
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB BANTAHAN ------------------------------------------->

<!-- PPT-39(i) -->
<!----------------------------------------- SENARAI DOKUMEN YANG DISERTAKAN --------------------------------------------->

<!-- :::upload -->
<!-- <fieldset id="senarai_dokumen" > -->
<!-- 	jenis dokumen = '$jenisDoc' -->
<!-- 	jenis skrin = '$nama_skrin' -->
<!-- 	listDokumen =  $listDokumen -->
	<legend>Senarai Dokumen Yang Disertakan</legend>
    
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" title="Sila klik untuk tambah dokumen" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumenMaster('$!readmode')" title="Sila tick untuk hapus dokumen" >
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="30%">Nama Dokumen</td>
    <td width="30%">Keterangan</td>
    <td width="30%">Dokumen Sokongan (Papar)</td>
     #if($listDokumen_size > 0)
      <td width="5%">
     
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
      </div>
      
      </td>
      #end
  </tr>
 
  
 #if($listDokumen_size > 0)
  #set ($cnt=0)
  #foreach($list1 in $listDokumen)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
	   #if($list1.JENIS_DOKUMEN == "pptpembatalanmt")   <!-- PPT-38 -->
	   #set ($cnt=1)
	  <tr>  
	    <td class="$row" >$list1.BIL</td>
	    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
	    <td class="$row" >$list1.KETERANGAN</td>
	    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>   
	    <td class="$row" ><div align="center">
	       <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
	     </div></td>
	  </tr>
	  #end
 	 #end
 	 #if($cnt==0)
	  <tr>  
	    <td colspan="5">Tiada Rekod</td>    
	  </tr>
	  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>
  
</fieldset> 
<!-- END PPT-39(ii) -->

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue"> Surat Makluman Bahawa Bantahan Ditarik Balik </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

</fieldset>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="txtTajuk" id="txtTajuk" value="Surat Pembatalan Bantahan" />
<input type="hidden" name="txtKeterangan" id="txtKeterangan" value="Surat Pembatalan Bantahan" />
<input type="hidden" name="nama_skrin" id="nama_skrin" value="pptpembatalanmt"  />

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

 function simpan_batalMahkamah(){
	if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No Ruj. Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;
	} 
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return; 
	document.${formName}.command.value = "simpan_batalMahkamah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
	}
}

function kemaskiniBatalBantahan(){
	document.${formName}.command.value = "kemaskiniBatalBantahan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )	{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )	{
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 ) {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

//PPT-39 (ii)
//:::upload
function tambahDokumen() {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var id_hakmilikpb = document.${formName}.id_hakmilikpb.value ;		
	var id_hakmilik = document.${formName}.id_hakmilik.value ;	
	var id_pihakberkepentingan = document.${formName}.id_pihakberkepentingan.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=tambah_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&id_hakmilik="+id_hakmilik+"&id_pihakberkepentingan="+id_pihakberkepentingan+"&location=maklumat_dokumen&point=txtnamadokumen&nama_skrin=pptpembatalanmt";	
  <!-- asal: jenisDoc=pptpembatalanmt -->
	document.${formName}.submit();
}

//:::upload
function view_Lampiran(id_dokumen) {
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

//:::upload
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//:::upload
function hapusDokumenMaster(r){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=hapusDokumenMasterPembatalan&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
}


// Checkbox Checkall
function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}

function doUpdateCheckAll1(){  
	var c = 0;
	if(document.${formName}.ids1.length > 1)	{     
		for (i = 0; i < document.${formName}.ids1.length; i++)	{
	      if (document.${formName}.ids1[i].checked == false)	{	 
		  	c++
	      }
		}  
	}	else	{
		if (document.${formName}.ids1.checked == false)	{	 
			c++;
	}	 	
}	 
	 

if(c>0)	{	  
	document.${formName}.all1.checked = false;
		 }	else	{
		 document.${formName}.all1.checked = true;
	}
}

<!--UTK DEFAULTKAN TAB KEPADA TAB BANTAHAN
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>