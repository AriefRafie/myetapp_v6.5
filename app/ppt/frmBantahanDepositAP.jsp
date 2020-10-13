
<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT DEPOSIT DIDAFTARKAN.</div>";
</script>
#end

#if ($clearForm == "yes")  
	#set ($txtAmaunPampasan = "") 
    #set ($txtNoCekPampasan = "") 
    #set ($txtNoAkaunPampasan = "") 
    #set ($txtNamaBankPampasan = "")   
    #set ($txdTkhTerimaResit = "")		
    #set ($txdTkhResit = "")
    #set ($txtNoResit = "")
    #set ($txtNoAkaun = "")
    ##set ($txtAmaunResit = "")
    #set ($txtNoCek = "")
    #set ($txdTkhPulang = "")
    #set ($txdTkhHantar = "")
    #set ($txtNamaPenghantar = "")
    #set ($txtNamaBank = "")
    #set ($txtCatatanPlgDeposit = "")
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

#set ( $id_status = $statusFail.get("id_status") )

#foreach ( $senaraiPampasan in $getMaklumatBantahan )
    #set ($txtAmaunTuntutan=$senaraiPampasan.amaun_tuntutan)
#end

#foreach ( $senaraiPampasan in $getMaklumatPampasan)
    #set ($txtAmaunPampasan=$senaraiPampasan.amaun_bayaran)
#end


#foreach ( $senarai in $getMaklumatBantahan )    
    #set ($id_bantahan=$senarai.id_bantahan)
    #set ($id_siasatan=$senarai.id_siasatan) 
    #set ($desc_status_bantahan_ap=$senarai.desc_status_bantahan_ap)
    <input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
#end

#if ($flag == "semak")
    #foreach ( $senarai in $getMaklumatDeposit )
        #set ($txdTkhTerimaResit=$senarai.tarikh_terima_resit)
        #set ($txdTkhResit=$senarai.tarikh_resit)
        #set ($txtNoResit=$senarai.no_resit)
        #set ($txtAmaunResit=$senarai.amaun_deposit)       
        #set ($txtNoCek=$senarai.no_rujukan_bayaran)
        #set ($txdTkhPulang=$senarai.tarikh_surat_bayardeposit)
        #set ($txdTkhHantar=$senarai.tarikh_akhir_bayardeposit)
        #set ($txtNoAkaun=$senarai.no_akaun)
        #set ($socStatusPulang=$senarai.flag_terimadeposit)
        #set ($txtNamaPenghantar=$senarai.nama_penghantar)
        #set ($txtNamaBank=$senarai.nama_bank)
    #end    
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>
<!------------------------------------------- END HEADER --------------------------------------------->  

<!------------------------------------------ TAB DEPOSIT -------------------------------------------->

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
    <li class="TabbedPanelsTab" tabindex="7" onclick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
   
    <div class="TabbedPanelsContent">
<!------------------------------------------- MAKLUMAT PAMPASAN ------------------------------------------------>     
<fieldset>
<legend>Maklumat Pampasan</legend>
    <table width="100%" border="0">
        <tr>
          <td width="1%"></td>
          <td width="28%">Pampasan yang Ditawarkan&nbsp;(RM)</td>
       	  <td width="1%">:</td>
       	  <td width="70%"><input type="text" size="11" name="txtAmaunPampasan" id="txtAmaunPampasan" value="$!Util.formatDecimal($!txtAmaunPampasan)" maxlength="12" class="disabled" readonly /></td>
   	    </tr>
        <tr>
            <td width="1%"></td>
            <td width="29%">&nbsp;</td>
          	<td width="1%">&nbsp;</td>
          	<td width="70%">&nbsp;</td>
   	    </tr>                    
    </table>
</fieldset>
<br />
<!------------------------------------------- END MAKLUMAT PAMPASAN --------------------------------------------> 
    
<!------------------------------------------- MAKLUMAT DEPOSIT ------------------------------------------------>     
<fieldset>
<legend>Maklumat Deposit Bantahan</legend>
    <table width="100%" border="0">
        <tr>
            <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
          <td width="29%">
            
           
            Tarikh Terima Resit
           
            </td>
          	<td width="1%">:</td>
          	<td width="24%">
            
            #if ($mode=="disabled")
            <input type="text" name="txdTkhTerimaResit" id="txdTkhTerimaResit" value="$!txdTkhTerimaResit" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />            
            #else
            <input type="text" name="txdTkhTerimaResit" id="txdTkhTerimaResit" value="$!txdTkhTerimaResit" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="1" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaResit',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            #end            
          </td>
            
       	  <td width="1%">&nbsp;</td>
          	<td width="13%">Cara Bayaran</td>
       	  <td width="1%">:</td>
       	  <td width="31%">$selectCaraBayar
   	      <input type="hidden" name="idcarabayar" id="idcarabayar" value="$!idcarabayar" /> </td>
        </tr>
        <tr>
            <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
          <td width="29%">
           
            Tarikh Resit
                                  
            
            </td>
          	<td width="1%">:</td>
          	<td width="24%">
            #if ($mode=="disabled")
            <input type="text" name="txdTkhResit" id="txdTkhResit" value="$!txdTkhResit" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />           
            #else
            <input type="text" name="txdTkhResit" id="txdTkhResit" value="$!txdTkhResit" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="2" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhResit',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          #end            </td>

          
         #if($idcarabayar!="1") 
            
          	<td width="1%">&nbsp;</td>
          	<td width="13%">No Cek</td>
          	<td width="1%">:</td>
       	  <td width="31%">
          #if ($mode=="disabled")
          <input type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" size="15" class="disabled" readonly />
          #else
          <input type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" size="15" tabindex="6" />
          #end          </td>
         
         #end        </tr>   
        <tr>
            <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
          <td width="29%">
            
           
            No Resit
           
            </td>
          	<td width="1%">:</td>
          	<td width="24%">
            #if ($mode=="disabled")
            <input type="text" name="txtNoResit" id="txtNoResit" value="$!txtNoResit" size="15" class="disabled" readonly />
            #else
            <input type="text" name="txtNoResit" id="txtNoResit" value="$!txtNoResit" size="15" tabindex="3" />
          #end            </td>
       	  <td width="1%">&nbsp;</td>
            
            
         #if($idcarabayar!="1") 
            
          	<td width="13%">No Akaun</td>
          	<td width="1%">:</td>
       	  	<td width="31%">
            
         	  #if ($mode=="disabled")
              <input type="text" name="txtNoAkaun" id="txtNoAkaun" value="$!txtNoAkaun" size="15" class="disabled" readonly/>
              #else
              <input type="text" name="txtNoAkaun" id="txtNoAkaun" value="$!txtNoAkaun" size="15" tabindex="7"/>
              #end              
          </td>
         
         #end         
        </tr>   
         
        <tr>
          <td width="1%"></td>
          <td>Amaun Tuntutan (RM)</td>
          <td>:</td>
          <td><input type="text" size="11" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" class="disabled" readonly /></td>
          <td>&nbsp;</td>
          
         #if($idcarabayar!="1")  
          
          <td>Nama Bank</td>
          <td>:</td>
          <td>
          #if($mode=="disabled")
          <input type="text" name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="35" class="disabled" readonly />
		  #else
		  <input type="text" name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="35" />
		  #end                   
          </td>
        #end
        </tr>
        
        <tr>
          <td width="1%"></td>
          <td>Deposit &nbsp;(RM)</td>
          <td>:</td>
          <td>
          
          #if ($mode=="disabled")
          <input type="text" size="11" name="txtAmaunResit" id="txtAmaunResit" value="$!Util.formatDecimal($!txtAmaunResit)" maxlength="12" class="disabled" readonly />
		  #else
		  <input type="text" size="11" name="txtAmaunResit" id="txtAmaunResit" value="$!txtAmaunResit" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunResit')" onkeyup="validateNumber(this,this.value);" />
<input type="hidden" name="txtAmaunResit2" id="txtAmaunResit2" value="$!txtAmaunResit" />
		  #end          
          </td>
          
         #if($idcarabayar!="1")  
          
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td></td>
          
       #end        
       </tr>
        
<!--        <tr>
          <td>&nbsp;&nbsp;&nbsp;Amaun Tuntutan</td>
          <td>:</td>
          <td><input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!Util.formatDecimal($!txtAmaunTuntutan)" maxlength="12" class="disabled" readonly />
x 10% </td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>-->
        <tr>
          <td width="1%"></td>
          <td width="29%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
   	  	  <td colspan="5">
          <i><font color="#FF0000" style="font-size:10px"> DEPOSIT = PAMPASAN YANG DITAWARKAN x 10% </font></i>          </td>
        </tr>                    
    </table>
</fieldset>

<!------------------------------------------- END MAKLUMAT DEPOSIT --------------------------------------------> 

<!------------------------------------ BUTIRAN PEMULANGAN DEPOSIT --------------------------------------------->  
<br />
<fieldset>
<legend>Butiran Pemulangan Deposit</legend>
	<table width="100%" border="0">
    	<tr>
            <td width="1%"></td>
            <td width="20%">Tarikh Pemulangan</td>
          	<td width="1%">:</td>
          	<td width="24%">
            #if ($mode=="disabled")
            <input type="text" name="txdTkhPulang" id="txdTkhPulang" value="$!txdTkhPulang" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
          	#else
          	<input type="text" name="txdTkhPulang" id="txdTkhPulang" value="$!txdTkhPulang" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="8" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhPulang',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          	#end
          </td>
       	  <td width="1%">&nbsp;</td>
       	  <td width="21%">Status Pemulangan</td>
       	  <td width="1%">:</td>
       	  <td width="32%"><select name="socStatusPulang" tabindex="9" id="socStatusPulang" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >
                    
			#if($socStatusPulang=="1")                                        
            <option value="1">01-Ya</option>
            <option value="2">02-Tidak</option>
            
            #elseif($socStatusPulang=="2")                                     
            <option value="2">02-Tidak</option>
            <option value="1">01-Ya</option>                                             
            
	        #else                                         
            <option value="1">01-Ya</option>
            <option value="2">02-Tidak</option>
            #end 
                                 
          </select></td>
        </tr>  
    	<tr>
          <td width="1%"></td>
    	  <td valign="top">Catatan</td>
    	  <td valign="top">:</td>
    	  <td colspan="5">
          #if ($mode=="disabled")
          <textarea name="txtCatatanPlgDeposit" id="txtCatatanPlgDeposit" cols="70" rows="6" class="disabled" readonly>$!txtCatatanPlgDeposit</textarea>
          #else          
          <textarea name="txtCatatanPlgDeposit" id="txtCatatanPlgDeposit" cols="70" rows="6" onkeydown="textCounter(this.form.txtCatatanPlgDeposit,this.form.remLentxtCatatanPlgDeposit,400);" onkeyup="textCounter(this.form.txtCatatanPlgDeposit,this.form.remLentxtCatatanPlgDeposit,400);">$!txtCatatanPlgDeposit</textarea>
          #end          
          </td>
   	    </tr>         
    </table>
</fieldset>

<!---------------------------------------------- END BUTIRAN PEMULANGAN DEPOSIT -------------------------------->  

<!----------------------------------------- BUTIRAN PENGHANTARAN DEPOSIT --------------------------------------->
<br />
<fieldset>
<legend>Butiran Penghantaran Deposit</legend>
	<table width="100%" border="0">
    	<tr>
            <td width="1%"></td>
            <td width="20%">Tarikh Hantar</td>
          	<td width="1%">:</td>
          	<td width="23%">
            #if ($mode=="disabled")
            <input type="text" name="txdTkhHantar" id="txdTkhHantar" value="$!txdTkhHantar" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
           #else
           <input type="text" name="txdTkhHantar" id="txdTkhHantar" value="$!txdTkhHantar" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="10" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhHantar',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
           #end       
          </td>
       	  <td width="1%">&nbsp;</td>
          <td width="22%">Nama Penghantar</td>
          <td width="1%">:</td>
       	  <td width="32%">
          #if ($mode=="disabled")
          <input type="text" name="txtNamaPenghantar" id="txtNamaPenghantar" value="$!txtNamaPenghantar" size="35" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly />
          #else
          <input type="text" name="txtNamaPenghantar" id="txtNamaPenghantar" value="$!txtNamaPenghantar" size="35" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="11" />
          #end
          </td>
        </tr>   
    </table> 
</fieldset> 

<!----------------------------------------- END BUTIRAN PENGHANTARAN DEPOSIT ------------------------------------>
        <div align="center"> 
          #if ($button=="view")        
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniDeposit()" /> 
          <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
          #end
          
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_deposit()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_deposit()" />           
          #end
          
          #if ($button=="add")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_deposit()" /> 
          #end
          
          
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" />
              
        </div>       
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>    
    
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB DEPOSIT ------------------------------------------->

</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratMintaDepositDalam30HariAP('$id_fail','$id_hakmilik','$id_siasatan','$id_bantahan','$id_permohonan')"><font color="blue"> Surat Makluman Kemukakan Bukti Bayaran - Makluman kepada Pembantah supaya kemukakan bukti bayaran deposit </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$!id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$!id_fail" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$!id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$!status_bantahan" />
<input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />
<input type="hidden" name="id_warta" id="id_warta" value="$!id_warta" />

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

function cetakSuratMintaDepositDalam30HariAP(id_fail,id_hakmilik,id_siasatan,id_bantahan,id_permohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_bantahan="+id_bantahan+"&id_hakmilik="+id_hakmilik+"&id_siasatan="+id_siasatan+"&id_permohonan="+id_permohonan+"&report=SuratMintaDepositDalam30HariAP&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

/*	var url = "../servlet/ekptg.report.ppt.SuratMintaDepositDalam30HariAP?idFail="+id_fail+"&idHakmilik="+id_hakmilik+"&idSiasatan="+id_siasatan+"&id_bantahan="+id_bantahan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	*/
	
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}
function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function batal_deposit(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function add_deposit(){

	if(document.${formName}.txdTkhTerimaResit.value == ""){
		alert("Sila masukkan \"Tarikh Terima Resit\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaResit.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhResit.value == ""){
		alert("Sila pilih \"Tarikh Resit\" terlebih dahulu.");
  		document.${formName}.txdTkhResit.focus(); 
		return;		
	}
	if(document.${formName}.txtNoResit.value == ""){
		alert("Sila pilih \"No Resit\" terlebih dahulu.");
  		document.${formName}.txtNoResit.focus(); 
		return;		
	}
/*	if(document.${formName}.txtAmaunResit.value == ""){
		alert("Sila pilih \"Amaun (RM)\" terlebih dahulu.");
  		document.${formName}.txtAmaunResit.focus(); 
		return;		
	}	
	if(document.${formName}.socCaraBayar.value == ""){
		alert("Sila pilih \"Cara Bayaran\" terlebih dahulu.");
  		document.${formName}.socCaraBayar.focus(); 
		return;		
	}
	
	if (idcarabayar != '1' ){
		
		if(document.${formName}.txtNoCek.value == ""){
			alert("Sila pilih \"No Cek\" terlebih dahulu.");
			document.${formName}.txtNoCek.focus(); 
			return;		
		}	
		if(document.${formName}.txtNoAkaun.value == ""){
			alert("Sila pilih \"No Akaun\" terlebih dahulu.");
			document.${formName}.txtNoAkaun.focus(); 
			return;		
		}		
		if(document.${formName}.socBank.value == ""){
			alert("Sila pilih \"Nama Bank\" terlebih dahulu.");
			document.${formName}.socBank.focus(); 
			return;		
		}	
	}*/	
					
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "add_deposit";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
		document.${formName}.submit();
	}
}

function update_deposit(){

	if(document.${formName}.txdTkhTerimaResit.value == ""){
		alert("Sila masukkan \"Tarikh Terima Resit\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaResit.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhResit.value == ""){
		alert("Sila pilih \"Tarikh Resit\" terlebih dahulu.");
  		document.${formName}.txdTkhResit.focus(); 
		return;		
	}
	if(document.${formName}.txtNoResit.value == ""){
		alert("Sila pilih \"No Resit\" terlebih dahulu.");
  		document.${formName}.txtNoResit.focus(); 
		return;		
	}
	if(document.${formName}.txtAmaunResit.value == ""){
		alert("Sila pilih \"Amaun (RM)\" terlebih dahulu.");
  		document.${formName}.txtAmaunResit.focus(); 
		return;		
	}	
/*	if(document.${formName}.socCaraBayar.value == ""){
		alert("Sila pilih \"Cara Bayaran\" terlebih dahulu.");
  		document.${formName}.socCaraBayar.focus(); 
		return;		
	}	
	
	if (idcarabayar != '1' ){		
		if(document.${formName}.txtNoCek.value == ""){
			alert("Sila pilih \"No Cek\" terlebih dahulu.");
			document.${formName}.txtNoCek.focus(); 
			return;		
		}	
		if(document.${formName}.txtNoAkaunPampasan.value == ""){
			alert("Sila pilih \"No Akaun\" terlebih dahulu.");
			document.${formName}.txtNoAkaunPampasan.focus(); 
			return;		
		}		
		if(document.${formName}.txtNamaBank.value == ""){
			alert("Sila pilih \"Nama Bank\" terlebih dahulu.");
			document.${formName}.txtNamaBank.focus(); 
			return;		
		}
		
	}*/
					
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
			document.${formName}.command.value = "update_deposit";
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
			document.${formName}.submit();
		}
}
function kemaskiniDeposit(){
	document.${formName}.command.value = "kemaskiniDeposit";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function doChangeCaraBayaran() {
	document.${formName}.command.value = "doChangeCaraBayaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();		
}
function doChangeCaraBayaranUpdate() {
	document.${formName}.command.value = "doChangeCaraBayaranUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
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
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>
