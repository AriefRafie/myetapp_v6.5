<center>

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

<fieldset>
<legend>Maklumat Bangunan</legend>

#foreach($data in $dataBangunan)
	#set($txtNoBangunan=$data.no_bangunan)
	#set($txtJenisBangunan=$data.nama_bangunan)
	#set($txtKeteranganBangunan=$data.keterangan_bangunan)
	#set($txtAlamat1H=$data.alamat1)
	#set($txtAlamat2H=$data.alamat2)
	#set($txtAlamat3H=$data.alamat3)
	#set($txtPoskodH=$data.poskod)
	#set($nama_bandar=$data.nama_bandar)
	#set($nama_negeri=$data.nama_negeri)
#end

	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
		<tr>
			<td width="1%">&nbsp;</td>
            <td width="25%">No.Bangunan</td>
            <td width="1%">:</td>
            <td width="73%">$!txtNoBangunan</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
           	<td>Jenis Bangunan</td>
           	<td>:</td>
          	<td>$!txtJenisBangunan - $!txtKeteranganBangunan</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
           	<td>Alamat</td>
           	<td>:</td>
           	<td>$!txtAlamat1H</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td colspan="2">&nbsp;</td>
        	<td>$!txtAlamat2H</td>
        </tr> 
      	<tr>
      		<td>&nbsp;</td>
        	<td colspan="2">&nbsp;</td>
        	<td>$!txtAlamat3H</td>
        </tr>  
        <tr>
        	<td>&nbsp;</td>
        	<td>Poskod</td>
        	<td>:</td>
        	<td>$!txtPoskodH</td>
        </tr> 
        <tr>
        	<td>&nbsp;</td>
        	<td>Bandar</td>
        	<td>:</td>
        	<td>$!nama_bandar.toUpperCase()</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>Negeri</td>
        	<td>:</td>
        	<td>$!nama_negeri.toUpperCase()</td>
        </tr> 		
	</table>

</fieldset>
<br/>


	#if($PBmode=="new")
	
	#if($PBexist)
	<script>
		alert("Pihak Berkepentingan Telah Wujud di Hakmilik Yang Sama.");
	</script>
	#end
	
	<fieldset id="top">
	<legend><strong>Maklumat Pemilik/Penyewa/Pembeli/Lain-lain</strong></legend>
	<table width="100%" border="0">
		<tr>
    		<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
    		<td width="25%">No. Unit</td>
    		<td width="1%">:</td>
    		<td width="73%"><input type="text" $disOtherId $disOtherIdx name="txtNoUnit" value="$!txtNoUnit" id="txtNoUnit" size="10" maxlength="20"   ></td>
    	</tr>
    	#if($txtKadarSewa=="")
           #set($kadar="")
        #else
           #set($kadar=$txtKadarSewa)
        #end
    	<tr>
			<td>&nbsp;</td>
			<td>Kadar Sewaan (RM)</td>
			<td>:</td>
			<td><input type="text" $disOtherId $disOtherIdx name="txtKadarSewa" id="txtKadarSewa" value="$!kadar" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!kadar')"></td>
		</tr>
		
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
    		<td><input type="text" style="text-align:right" $disOtherId $disOtherIdx name="txtSaizBangunan" id="txtSaizBangunan" value="$!txtSaizBangunan" size="7" maxlength="10"  onblur="validateNilai(this,this.value)" onkeyup="validateNilai(this,this.value)" >
    			&nbsp;<strong>X</strong>&nbsp;
    			<input $disOtherId style="text-align:right" $disOtherIdx type="text" name="txtSaizBangunanDua" id="txtSaizBangunanDua" value="$!txtSaizBangunanDua" size="7" maxlength="10" onblur="validateNilai(this,this.value)" onkeyup="validateNilai(this,this.value)"  >
    			<input type="radio" $disOtherId1 name="sorSaiz" id="sorKaki" $checkA value="1">Kaki Persegi
    			<input type="radio" $disOtherId1 name="sorSaiz" id="sorMeter" $checkB value="2">Meter Persegi
    		</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td valign="top">Kegunaan Bangunan</td>
    		<td valign="top">:</td>
    		<td valign="top"><textarea $disOtherId $disOtherIdx cols="37%" rows="4" name="txtKegunaan" id="txtKegunaan">$!txtKegunaan</textarea></td>
    	</tr>
    </table>
    </fieldset>

    <fieldset>
    <table width="100%" border="0">	
    
    	#if($radioButton=="1")
    		#set($checkA = "checked")
    		#set($checkB = "")
    	#elseif($radioButton=="2")
    		#set($checkA = "")
    		#set($checkB = "checked")
    	#else
    		#set($checkA = "")
    		#set($checkB = "")
    	#end
    
    	<tr>
    		<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
    		<td width="25%">PB Terdahulu ?</td>
    		<td width="1%">:</td>
    		<td width="73%"><input $disOtherId1 type="radio" name="typePB" id="sorYA" value="1" onclick="pilihPB()" $NOpb $checkA >Ya
     			&nbsp;&nbsp;<input $disOtherId1 type="radio" name="typePB" value="2" onclick="pilihPB()" $checkB >Tidak</td>
    	</tr>
    	
    	#if($radioButton=="1")
    	<tr>
    		<td><font color="red">*</font></td>
			<td>Senarai PB</td>
			<td>:</td>
			<td>$!selectPB</td>
    	</tr>
    	#end
    	
    </table>
    </fieldset>


#if($radioButton!="")    
    <fieldset>
    <table width="100%" border="0">	
   	 	<tr>
			<td width="1%"><font color="red">#if($editable=="yes")*#end</font></td>
			<td width="25%">Jenis PB</td>
			<td width="1%">:</td>
			<td width="73%">$!selectJenisPB</td>
		</tr>
    	<tr>
    		<td><font color="red">#if($editable=="yes")*#end</font></td>
    		<td>Nama</td>
    		<td>:</td>
    		<td><input $disOtherId $disOtherIdx type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
    	</tr>
    	<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20" onblur="checkExistPB()"  ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Perihal Jenis PB (lain-lain)</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtJenisPB" id="txtJenisPB" value="$!txtJenisPB" size="20" maxlength="100"   ></td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td>Bangsa</td>
			<td>:</td>
			<td>$!selectBangsa</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Warga</td>
			<td>:</td>
			<td>$!selectWarga</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
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
			<td>No. Telefon</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtNoTelefon" id="txtNoTelefon" value="$!txtNoTelefon" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon Bimbit</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtNoBimbit" id="txtNoBimbit" value="$!txtNoBimbit" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Faks</td>
			<td>:</td>
			<td><input $disOtherId $disOtherIdx type="text" name="txtNoFaks" id="txtNoFaks" value="$!txtNoFaks" size="14" maxlength="14" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
	</table>
	</fieldset>
#end	
#end



#if($PBmode=="view")
	
	#if($onchangePBupdate=="no")
	#foreach($data in $dataBangunanPB)
		#set($txtNoUnit=$data.no_unit_bangunan)
		#set($txtKadarSewa=$data.kadar_sewa)
		#set($txtSaizBangunan=$data.saiz_bangunan)
		#set($txtKegunaan=$data.kegunaan_bangunan)
		#set($txtNama=$data.nama_pb)
		#set($txtNoPB=$data.no_pb)
		#set($txtJenisPB=$data.jenis_lain2_pb)
		#set($txtAlamat1=$data.alamat1)
		#set($txtAlamat2=$data.alamat2)
		#set($txtAlamat3=$data.alamat3)
		#set($txtPoskod=$data.poskod)
		#set($txtNoTelefon=$data.no_tel_rumah)
		#set($txtNoBimbit=$data.no_handphone)
		#set($txtNoFaks=$data.no_fax)
		#set($sorSaiz=$data.unit_saiz)
		#set($txtSaizBangunanDua=$data.saiz_bangunan_dua)
	#end
	#end
	
	#if($PBisEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
	#else
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	
	<fieldset id="top">
	<legend><strong>Maklumat Pemilik/Penyewa/Pembeli/Lain-lain</strong></legend>
	<table width="100%" border="0">
		<tr>
    		<td width="1%">#if($PBisEdit=="yes")<font color="red">*</font>#end</td>
    		<td width="25%">No. Unit</td>
    		<td width="1%">:</td>
    		<td width="73%"><input $disability $disabilityx type="text" name="txtNoUnit" value="$!txtNoUnit" id="txtNoUnit" size="10" maxlength="20"   ></td>
    	</tr>
    	<tr>
			<td>&nbsp;</td>
			<td>Kadar Sewaan (RM)</td>
			<td>:</td>
			#if($PBisEdit=="no")
			<td><input type="text" $disability $disabilityx name="txtKadarSewa" id="txtKadarSewa" value="$!Utils.format2Decimal($txtKadarSewa)" size="10" maxlength="11" style="text-align:right" ></td>
			#else
			<td><input type="text" $disability $disabilityx name="txtKadarSewa" id="txtKadarSewa" value="$!txtKadarSewa" size="10" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtKadarSewa')"></td>
			#end
		</tr>
		
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
    		<td><input type="text" style="text-align:right" $disability $disabilityx name="txtSaizBangunan" id="txtSaizBangunan" value="$!txtSaizBangunan" size="7" maxlength="10" onblur="validateNilai(this,this.value)" onkeyup="validateNilai(this,this.value)"  >
    			&nbsp;<strong>X</strong>&nbsp;
    			<input $disability style="text-align:right" $disabilityx type="text" name="txtSaizBangunanDua" id="txtSaizBangunanDua" value="$!txtSaizBangunanDua" size="7" maxlength="10" onblur="validateNilai(this,this.value)" onkeyup="validateNilai(this,this.value)"  >
    			<input type="radio" $disability1 name="sorSaiz" id="sorKaki" $checkA value="1">Kaki Persegi
    			<input type="radio" $disability1 name="sorSaiz" id="sorMeter" $checkB value="2">Meter Persegi
    		</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td valign="top">Kegunaan Bangunan</td>
    		<td valign="top">:</td>
    		<td valign="top"><textarea $disability $disabilityx cols="37%" rows="4" name="txtKegunaan" id="txtKegunaan" >$!txtKegunaan</textarea></td>
    	</tr>
    </table>
    </fieldset>

   
    <fieldset>
    <table width="100%" border="0">	
    	<tr>
			<td width="1%">#if($PBisEdit=="yes")<font color="red">*</font>#end</td>
			<td width="25%">Kod Jenis PB</td>
			<td width="1%">:</td>
			<td width="73%">$!selectJenisPB</td>
		</tr>
    	<tr>
    		<td>#if($PBisEdit=="yes")<font color="red">*</font>#end</td>
    		<td>Nama</td>
    		<td>:</td>
    		<td><input $disability $disabilityx type="text" name="txtNama" id="txtNama" value="$!txtNama" size="50" maxlength="80"   ></td>
    	</tr>
    	<tr>
			<td>&nbsp;</td>
			<td>Kod No. PB</td>
			<td>:</td>
			<td>$!selectJenisNoPB</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. PB</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoPB" id="txtNoPB" value="$!txtNoPB" size="20" maxlength="20" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Perihal Jenis PB (lain-lain)</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtJenisPB" id="txtJenisPB" value="$!txtJenisPB" size="20" maxlength="100"   ></td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td>Bangsa</td>
			<td>:</td>
			<td>$!selectBangsa</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Warga</td>
			<td>:</td>
			<td>$!selectWarga</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Alamat</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<td><input type="text" $disability $disabilityx name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Poskod</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value);"></td>
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
			<td>No. Telefon</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoTelefon" id="txtNoTelefon" value="$!txtNoTelefon" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Telefon Bimbit</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoBimbit" id="txtNoBimbit" value="$!txtNoBimbit" size="14" maxlength="12" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Faks</td>
			<td>:</td>
			<td><input type="text" $disability $disabilityx name="txtNoFaks" id="txtNoFaks" value="$!txtNoFaks" size="14" maxlength="14" onkeyup="validateNumber(this,this.value);" ></td>
		</tr>
	</table>
	</fieldset>	
#end


	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($PBmode=="new" && $editable=="yes")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanMaklumatBangunanPB('$!id_bangunan','$!id_bangunanpb','$!PBmode')">
				#end
				
				#if($PBmode=="view" && $editable=="yes")
				#if($PBisEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBangunanPB('$!id_bangunanpb')">
				<input type="button" name="cmdHapus" value="Hapus" onClick="javascript:hapusBangunanPB('$!id_bangunan','$!id_bangunanpb')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanMaklumatBangunanPB('$!id_bangunan','$!id_bangunanpb','$!PBmode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalBangunanPB('$!id_bangunan','$!id_bangunanpb')">
				#end
				#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewBangunan('$!id_bangunan')">
			</td>
		</tr>
	</table>

<br/>    
    
    <fieldset>
    <legend>Senarai Maklumat Pemilik/Penyewa/Pembeli/Lain-lain  #if($PBmode=="view")
    	<input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahMaklumatBangunanPB('$!id_bangunan')">
    
    	#end
    </legend>
    
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianBangunan('$id_permohonan','$!id_hakmilik','skrin_pb_bangunan_sek8','$!id_bangunan')"><font color="blue">>> SKRIN CAPAIAN BANGUNAN</font></a>
    </td>
    </tr>
    </table>
    <!--
    	#if($PBmode=="view")
    	<table width="100%" border="0">   
        	<tr>
               	<td><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahMaklumatBangunanPB('$!id_bangunan')"></td>
    		</tr>
    	</table>
    	#end
   
    
    		#if($saiz_maklumatpb > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="15%"><b>&nbsp;No.Unit</b></td>
        			<td width="40%"><b>&nbsp;Nama</b></td>
        			<td width="41%"><b>&nbsp;Jenis PB</b></td>
        		</tr>
        		
        		#if($saiz_maklumatpb!=0)
                    #foreach($list in $listMaklumatPB)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row">&nbsp;<a href="javascript:viewMaklumatBangunanPB('$!list.id_bangunan','$!list.id_bangunanpb')"><font color="blue">$!list.no_unit_bangunan</font></a></td>
                   <td class="$row">&nbsp;$!list.nama_pb</td>
                   <td class="$row">&nbsp;$!list.jenis_pb</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_maklumatpb > 5)
                </div>
            #end
    -->         
    </fieldset>	

</center>

<input type="hidden" name="idExistPB" value="$!idExistPB">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_bangunan" value="$!id_bangunan">
<input type="hidden" name="id_bangunanpb" value="$!id_bangunanpb">
<input type="hidden" name="id_pihakberkepentingan" value="$!id_pihakberkepentingan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>

function popupCarianBangunan(id_permohonan,id_hakmilik,flag_skrin,id_bangunan)
{
        
        var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupBangunan?&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&ID_BANGUNAN_SELECT="+id_bangunan;
        var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
           hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();
        hWnd.focus();		
        
}

function onchangeNegeriUpdate(idBangunan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "viewMaklumatBangunanPB";
	document.${formName}.command2.value = "kemaskiniBangunanPB";
	document.${formName}.command3.value = "onchangeNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function batalBangunanPB(idBangunan,idBangunanPB) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.id_bangunanpb.value = idBangunanPB;
	document.${formName}.command.value = "viewMaklumatBangunanPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function kemaskiniBangunanPB(idBangunanPB) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunanpb.value = idBangunanPB;
	document.${formName}.command.value = "viewMaklumatBangunanPB";
	document.${formName}.command2.value = "kemaskiniBangunanPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function hapusBangunanPB(idBangunan,idBangunanPB) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.id_bangunanpb.value = idBangunanPB;
	document.${formName}.command.value = "hapusBangunanPB";
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
function viewMaklumatBangunanPB(idBangunan,idBangunanPB) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.id_bangunanpb.value = idBangunanPB;
	document.${formName}.command.value = "viewMaklumatBangunanPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function getPBfromDropdown() {

	var idpb = document.getElementById("socPB").value;

	if(idpb!=""){
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "tambahMaklumatBangunanPB";
		document.${formName}.command2.value = "getPBfromDropdown";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
		document.${formName}.submit();
	}
}
function pilihPB() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahMaklumatBangunanPB";
	document.${formName}.command2.value = "pilihPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function simpanMaklumatBangunanPB(idBangunan,idBangunanPB,mode) {

	if(mode=="new"){
	
		var radioSelected = false;
		for (i = 0;  i < ${formName}.typePB.length;  i++){
			if (${formName}.typePB[i].checked)
			radioSelected = true;
		}	
	
		var value1 = document.getElementById("sorYA").checked;

		if(!radioSelected)
		{
			alert("Sila pilih adakah PB terdahulu atau tidak ");
			return(false);
		}
		else
		if(value1==true && (radioSelected && document.${formName}.socPB.value == "")){
			alert("Sila pilih salah satu PB terlebih dahulu.");
	  		document.${formName}.socPB.focus(); 
			return;
		} 
		else if (document.${formName}.socJenisNoPB.value!="" && (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length < 12) || (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length > 12)) {
			alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
			document.${formName}.txtNoPB.focus(); 
			return;
		}
		else
		if(document.${formName}.txtNoUnit.value == ""){
			alert("Sila masukkan \" No. Unit \" terlebih dahulu.");
  			document.${formName}.txtNoUnit.focus(); 
			return;
		}

	}else{

		if(document.${formName}.txtNoUnit.value == ""){
			alert("Sila masukkan \" No. Unit \" terlebih dahulu.");
  			document.${formName}.txtNoUnit.focus(); 
			return;
		}
		
	}
		
	
	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \" Nama \" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
/*	else
	if(document.${formName}.socJenisNoPB.value == ""){
		alert("Sila pilih \" Kod No. PB \" terlebih dahulu.");
	  	document.${formName}.socJenisNoPB.focus(); 
		return;
	}
	else
	if(document.${formName}.txtNoPB.value == ""){
		alert("Sila masukkan \" No. PB \" terlebih dahulu.");
		document.${formName}.txtNoPB.focus(); 
		return;
	}
*/	else if (document.${formName}.socJenisNoPB.value!="" && (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length < 12) || (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length > 12)) {
		alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
		document.${formName}.txtNoPB.focus(); 
		return;
	}
	else
	if(document.${formName}.socJenisPB.value == ""){
		alert("Sila pilih \" Jenis PB \" terlebih dahulu.");
		document.${formName}.socJenisPB.focus(); 
		return;
	}
	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
	else{	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bangunan.value = idBangunan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahMaklumatBangunanPB";
			document.${formName}.command2.value = "simpanMaklumatBangunanPB";
		}else{
			document.${formName}.id_bangunanpb.value = idBangunanPB;
			document.${formName}.command.value = "viewMaklumatBangunanPB";
			document.${formName}.command2.value = "kemaskiniBangunanPB";
			document.${formName}.command3.value = "updateMaklumatBangunanPB";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
		document.${formName}.submit();
	}
}
function checkExistPB() {

	if (document.${formName}.typePB[1].checked == true && document.${formName}.socJenisNoPB.value != "" && document.${formName}.txtNoPB.value.length > 3) {

		if ((document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length < 12) || (document.${formName}.socJenisNoPB.value == "1" && document.${formName}.txtNoPB.value.length > 12)) {
			alert("Sila pastikan No.Kad Pengenalan baru diisi dengan betul");
			document.${formName}.txtNoPB.focus(); 
			return;
		}else{
			document.${formName}.ScreenLocation.value = "top";	
			document.${formName}.command.value = "tambahMaklumatBangunanPB";	
			document.${formName}.command2.value = "checkExistPB";		
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
			document.${formName}.submit();		
			document.${formName}.socJenisPB.focus();	
		}	
	}
}
function viewBangunan(idBangunan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "viewBangunan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
}
function onchangeNegeri(idBangunan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bangunan.value = idBangunan;
	document.${formName}.command.value = "tambahMaklumatBangunanPB";
	document.${formName}.command2.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8InfoTanahTerperinciBangunan";
	document.${formName}.submit();
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
