
<!-- Checking id -->

#set($portal_role = "$!{session.getAttribute('_portal_role')}")

#if(($!id_pegawai=="") || ($!{session.getAttribute('_ekptg_user_id')} == $!id_pegawai) || ($portal_role == "(PPT)PengarahUnit" || $portal_role == "(PPT)PenolongPengarahUnit" || $portal_role == "adminppt"))
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#else
	<!-- set($editable="no")
	set($disOtherId="readonly")
	set($disOtherId1="disabled")
	set($disOtherIdx="class=disabled") -->
	#set($editable="yes")
	#set($disOtherId="")
	#set($disOtherId1="")
	#set($disOtherIdx="")
#end

<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

<input type="hidden" id="mode" name="mode" value="$!mode" >

	<fieldset id="middle">
	<legend>Laporan Tanah Terperinci Seksyen 8 - Bangunan</legend>
	
		#if($!mode=="new")
    	<table width="100%" border="0">
    		<tr>
    			<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
    			<td width="25%">No. Bangunan</td>
    			<td width="1%">:</td>
    			<td width="73%"><input type="text" $disOtherId $disOtherIdx name="txtNoBangunan" value="$!txtNoBangunan" id="txtNoBangunan" size="10" maxlength="20"   ></td>
    		</tr>

    		<tr>
    			<td>&nbsp;</td>
    			<td>Jenis Bangunan</td>
    			<td>:</td>
    			<td>$!selectBangunan</td>
    		</tr>

			#if($idJenisBangunan=="4")
			<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Lain-lain Bangunan</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disOtherId $disOtherIdx cols="37%" rows="4" name="txtLainBangunan" id="txtLainBangunan" >$!txtLainBangunan</textarea></td>
    		</tr>
    		#end
    		

    		#set($checkA="")
    		#set($checkB="")
    		
    		#if($sorSaiz=="1")
    			#set($checkA="checked")
    		#elseif($sorSaiz=="2")
    			#set($checkB="checked")
    		#end
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Saiz Bangunan</td>
    			<td>:</td>
    			<td><input type="text" style="text-align:right" $disOtherId $disOtherIdx name="txtSaizBangunan" id="txtSaizBangunan" value="$!txtSaizBangunan" size="7" maxlength="10"  onblur="validateNilai(this,this.value);total_bg()" onkeyup="validateNilai(this,this.value);total_bg()" >
    				&nbsp;<strong>X</strong>&nbsp;
    				<input type="text" style="text-align:right" $disOtherId $disOtherIdx name="txtSaizBangunanDua" id="txtSaizBangunanDua" value="$!txtSaizBangunanDua" size="7" maxlength="10" onblur="validateNilai(this,this.value);total_bg()" onkeyup="validateNilai(this,this.value);total_bg()"  >
                    
                    
    				<input type="radio" $disOtherId1 name="sorSaiz" id="sorKaki" $checkA value="1" onclick="total_bg()">Kaki Persegi
    				<input type="radio" $disOtherId1 name="sorSaiz" id="sorMeter" $checkB value="2" onclick="total_bg()" >Meter Persegi
                    <div id="total_bg"></div>
    			</td>
    		
    		</tr>
    		
    		#if($txtNilai=="")
               #set($nilai="")
            #else
               	#set($nilai=$txtNilai)
            #end

    		<tr>
    			<td>&nbsp;</td>
    			<td>Nilai (RM)</td>
    			<td>:</td>
    			<td><input type="text" $disOtherId $disOtherIdx name="txtNilai" id="txtNilai" value="$!nilai" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!nilai')"></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Lain-lain Perkara</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disOtherId $disOtherIdx cols="37%" rows="4" name="txtPerkara" id="txtPerkara" >$!txtPerkara</textarea></td>
    		</tr>
			
			<tr>
    			<td>&nbsp;</td>
    			<td>Kategori Tanah</td> <!-- penambahan yati -->
    			<td>:</td>
    			<td>$!selectTanah</td>
    		</tr>
			
    		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input type="text" $disOtherId $disOtherIdx name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td colspan="2">&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $disOtherId $disOtherIdx name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td colspan="2">&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $disOtherId $disOtherIdx name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input type="text" $disOtherId $disOtherIdx name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeri</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandar</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Ulasan</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disOtherId $disOtherIdx cols="37%" rows="4" name="txtUlasan" id="txtUlasan" >$!txtUlasan</textarea></td>
    		</tr>
    	</table>
    	#end
    	
    	#if($!mode=="view")
    	
    	#if($onchange=="no")
    	#foreach($data in $dataBangunan)
			#set($txtNoBangunan=$data.no_bangunan)
			#set($socBangunan=$data.jenis_bangunan)
			#set($txtLainBangunan=$data.lain_jenis_bangunan)
			#set($txtSaizBangunan=$data.saiz_bangunan)
			#set($txtNilai=$data.nilai_bangunan)
			#set($txtPerkara=$data.lain_perkara)
			#set($txtAlamat1=$data.alamat1)
			#set($txtAlamat2=$data.alamat2)
			#set($txtAlamat3=$data.alamat3)
			#set($txtPoskod=$data.poskod)
			#set($txtUlasan=$data.ulasan)
			#set($sorSaiz=$data.unit_saiz)
			#set($txtSaizBangunanDua=$data.saiz_bangunan_dua)
		#end
    	#end
    	
    	#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
		#else
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
		
    	<table width="100%" border="0">
    		<tr>
    			<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
    			<td width="25%">No. Bangunan</td>
    			<td width="1%">:</td>
    			<td width="73%"><input $disability $disabilityx type="text" name="txtNoBangunan" value="$!txtNoBangunan" id="txtNoBangunan" size="10" maxlength="20"   ></td>
    		</tr>
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Jenis Bangunan</td>
    			<td>:</td>
    			<td>$!selectBangunan</td>
    		</tr>
    			
    				#if($idJenisBangunan=="4")
    		<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Lain-lain Bangunan</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disability $disabilityx cols="37%" rows="4" name="txtLainBangunan" id="txtLainBangunan" >$!txtLainBangunan</textarea></td>
    		</tr>
    		#end
    	
    		
    		#set($checkA="")
    		#set($checkB="")
    		
    		#if($sorSaiz=="1")
    			#set($checkA="checked")
    		#elseif($sorSaiz=="2")
    			#set($checkB="checked")
    		#end
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Saiz Bangunan</td>
    			<td>:</td>
    			<td><input $disability $disabilityx style="text-align:right" type="text" name="txtSaizBangunan" id="txtSaizBangunan" value="$!txtSaizBangunan" size="7" maxlength="10" onblur="validateNilai(this,this.value);total_bg()" onkeyup="validateNilai(this,this.value);total_bg()"  >
    				&nbsp;<strong>X</strong>&nbsp;
    				<input $disability $disabilityx style="text-align:right" type="text" name="txtSaizBangunanDua" id="txtSaizBangunanDua" value="$!txtSaizBangunanDua" size="7" maxlength="10" onblur="validateNilai(this,this.value);total_bg()" onkeyup="validateNilai(this,this.value);total_bg()"  >
    				<input type="radio" $disability1 name="sorSaiz" id="sorKaki" onclick="total_bg()" $checkA value="1">Kaki Persegi
    				<input type="radio" $disability1 name="sorSaiz" id="sorMeter" onclick="total_bg()" $checkB value="2">Meter Persegi
                    
                    <div id="total_bg"></div>
    			</td> 		
    		</tr>
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Nilai (RM)</td>
    			<td>:</td>
    			#if($isEdit=="no")
    			<td><input $disability $disabilityx type="text" name="txtNilai" id="txtNilai" value="$!Utils.format2Decimal($txtNilai)" size="10" maxlength="11" style="text-align:right" ></td>
    			#else
    			<td><input $disability $disabilityx type="text" name="txtNilai" id="txtNilai" value="$!txtNilai" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtNilai')"></td>
    			#end
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Lain-lain Perkara</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disability $disabilityx cols="37%" rows="4" name="txtPerkara" id="txtPerkara" >$!txtPerkara</textarea></td>
    		</tr>
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Kategori Tanah</td> <!-- penambahan yati -->
    			<td>:</td>
    			<td>$!selectTanah</td>
    		</tr>
    		
    		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input $disability $disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td colspan="2">&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input $disability $disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td colspan="2">&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input $disability $disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="40" maxlength="80"   ></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input $disability $disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeri</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandar</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td valign="top">Ulasan</td>
    			<td valign="top">:</td>
    			<td valign="top"><textarea $disability $disabilityx cols="37%" rows="4" name="txtUlasan" id="txtUlasan" >$!txtUlasan</textarea></td>
    		</tr>
    	</table>
    	#end
    	
    </fieldset>
    
    
    <table width="100%" border="0">
		<tr align="center">
			<td>
			
				#if($mode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanInfoTanah('$!id_hakmilik','$!id_bangunan','$!mode')">
				#end
				
				#if($mode=="view" && $editable=="yes")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBangunan('$!id_bangunan')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="javascript:hapusBangunan('$!id_permohonan','$!id_hakmilik','$!id_bangunan')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanInfoTanah('$!id_hakmilik','$!id_bangunan','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalBangunan('$!id_bangunan')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
			</td>
		</tr>
	</table>
	
<br/>    
    
    <fieldset>
    <legend>Senarai Bangunan 
    #if($mode=="view")
    	<input type="button" name="cmdTambah" value="Tambah" onClick="javascript:viewInfoTanah('$!id_hakmilik')">
    #end
    </legend>
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianBangunan('$id_permohonan','$!id_hakmilik','skrin_list_bangunan_sek8')"><font color="blue">>> SKRIN CAPAIAN BANGUNAN</font></a>
    </td>
    </tr>
    </table>
    
    
    
    
    
    	<!--
    			
    		#if($saiz_bangunan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="15%"><b>&nbsp;No.Bangunan</b></td>
        			<td width="30%"><b>&nbsp;Jenis Bangunan</b></td>
        			<td width="51%"><b>&nbsp;Maklumat Pemilik/Penyewa/Pembeli/Lain-lain</b></td>
        		</tr>
        		
        		#if($saiz_bangunan!=0)
                    #foreach($list in $listMaklumatBangunan)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row">&nbsp;<a href="javascript:viewBangunan('$!list.id_bangunan')"><font color="blue">$!list.no_bangunan</font></a></td>
                   <td class="$row">&nbsp;$!list.jenis_bangunan</td>
                   <td class="$row">&nbsp;<input type="button" name="cmdPapar" value="Papar" onClick="javascript:tambahMaklumatBangunanPB('$!list.id_bangunan')"></td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_bangunan > 5)
                </div>
            #end
            -->
    </fieldset>
   
    		
</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_bangunan" value="$!id_bangunan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_bangunanpb" >
<input type="hidden" name="idJenisBangunan" id="idJenisBangunan" value="$idJenisBangunan" />
<input type="hidden" name="idKategoriTanah" id="idKategoriTanah" value="$idKategoriTanah" />


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
total_bg();



function popupCarianBangunan(id_permohonan,id_hakmilik,flag_skrin)
{
        
        var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupBangunan?&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
        var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
           hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();
        hWnd.focus();		
        
}

function viewMaklumatBangunanPB(idBangunan,idBangunanPB) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.id_bangunanpb.value = idBangunanPB;
	document.${formName}.command.value = "viewMaklumatBangunanPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}


function total_bg()
{
	
	var a =	document.${formName}.txtSaizBangunan.value;
	var b = document.${formName}.txtSaizBangunanDua.value;
	var c = document.${formName}.sorSaiz[0].checked;
	var d = document.${formName}.sorSaiz[1].checked;
	var jenis_luas = '';
	//alert("C:"+c+" D:"+d);
	
	var luas = 0;
	if(a!="" && b!="")
	{
	luas = a*b;	
	
	if(c==true)
	{
		jenis_luas = 'Kaki Persegi';
	}
	if(d==true)
	{
		jenis_luas = 'Meter Persegi';
	}
	
	$jquery("#total_bg").html("<font color='blue'>Saiz Keseluruhan : <b>"+luas+"</b> "+jenis_luas+"</font>");	
	}
 
 
}


function viewInfoTanah(idHakmilik){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewInfoTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function tambahMaklumatBangunanPB(idBangunan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "tambahMaklumatBangunanPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function batalBangunan(idBangunan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function onchangeNegeriUpdate() {
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.command2.value = "kemaskiniBangunan";
	document.${formName}.command3.value = "onchangeNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function kemaskiniBangunan(idBangunan) {
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.command2.value = "kemaskiniBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function hapusBangunan(id_permohonan,id_hakmilik,idBangunan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "hapusBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function viewBangunan(idBangunan) {
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function simpanInfoTanah(id_hakmilik,idBangunan,mode) {

	if(document.${formName}.txtNoBangunan.value == ""){
		alert("Sila masukkan \" No. Bangunan \" terlebih dahulu.");
  		document.${formName}.txtNoBangunan.focus(); 
		return;
	}
	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
	else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "middle";
		document.${formName}.id_hakmilik.value = id_hakmilik;

		if(mode=="new"){
			document.${formName}.command.value = "viewInfoTanah";
			document.${formName}.command2.value = "simpanInfoTanah";
		}else{
			document.${formName}.id_bangunan.value = idBangunan;
			document.${formName}.command.value = "viewBangunan";
			document.${formName}.command2.value = "kemaskiniBangunan";
			document.${formName}.command3.value = "updateInfoTanah";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
		document.${formName}.submit();
	}
}
function viewListHM(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function onchangeNegeri() {
	//alert(idJenisBangunan);
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "viewInfoTanah";
	document.${formName}.command2.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();

}
//penambahan yati
function onchangeJenisBangunan(idJenisBangunan){
	//alert("xxxx");
	//alert(idJenisBangunan);
	document.${formName}.idJenisBangunan.value = idJenisBangunan;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewInfoTanah";
	document.${formName}.command2.value = "onchangeJenisBangunan";
	//document.${formName}.command3.value = "onchangeVal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan&idJenisBangunan="+idJenisBangunan;
	document.${formName}.submit();
}

//penambahan yati
function onchangeJenisBangunanUpdate(idJenisBangunan) {
	//alert("yyyy");
	//alert(idJenisBangunan);
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.idJenisBangunan.value = idJenisBangunan;
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.command2.value = "kemaskiniBangunan";
	document.${formName}.command3.value = "onchangeJenisBangunanUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}

function onchangeBangunan(idJenisBangunan) {
	//alert(idJenisBangunan);

	//document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.idJenisBangunan.value = idJenisBangunan;
	document.${formName}.command.value = "viewInfoTanah";
	document.${formName}.command2.value = "onchangeBangunan";
	//document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
	
	
	/*if(idJenisBangunan.value =="3"){
		alert("x3");
		jQuery.("#txtLainBangunan").hide();
	}
	if(idJenisBangunan =="4"){
		alert("x4");
		jQuery.("#txtLainBangunan").show();
	}
	*/
}

</script>


<script>
window.onload = submitForm;

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
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
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
}
function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
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
