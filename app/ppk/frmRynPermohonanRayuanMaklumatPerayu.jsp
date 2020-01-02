<br/>

<!-- paging -->
#set($no1enable="<img border='0' title='Senarai permohonan' src='../img/1enable.png'>")
#set($arrow="<img border='0' src='../img/arrowgaris.png'>")
#set($no2enable="<img border='0' title='Senarai semak' src='../img/2enable.png'>")
#set($no3current="<img border='0' title='Maklumat permohonan rayuan' src='../img/3current.png'>")
#set($no4enable="<img border='0' title='Keputusan rayuan' src='../img/4enable2.png'>")
#set($no4disable="<img border='0' title='Keputusan rayuan' src='../img/4disable.png'>")
#set ($jumlahfeeBorangA = 0)
#set ($jumlahfeeBorangP = 0)
#set ($Borang_A = 0)
#set ($Borang_1A = 0)
#set ($Borang_P = 0)
#set ($Borang_SA = 0)
#set ($Borang_DDA = 0)
#set ($FLampiran1 = 0)
#set ($F2Lampiran1 = 0)
#set ($QLampiran1 = 0)
#set ($FLampiran2 = 0)
#set ($F2Lampiran2 = 0)
#set ($QLampiran2 = 0)

#set ($FLampiran3 = 0)
#set ($F2Lampiran3 = 0)
#set ($QLampiran3 = 0)

#set ($FLampiran4 = 0)
#set ($F2Lampiran4 = 0)
#set ($QLampiran4 = 0)

#set ($FLampiran5 = 0)
#set ($F2Lampiran5 = 0)
#set ($QLampiran5 = 0)

#set ($FLampiran6 = 0)
#set ($F2Lampiran6 = 0)
#set ($QLampiran6 = 0)

#set ($FLampiran7 = 0)
#set ($F2Lampiran7 = 0)
#set ($QLampiran7 = 0)

#set ($FLampiran8 = 0)
#set ($F2Lampiran8 = 0)
#set ($QLampiran8 = 0)

#set ($nilaiBayaran = 0) 

#set ($JUMLAH_BAYARAN = 0)
	<!-- paging -->	
	#set($pagingMaklumatPP="CL - 01 - 182")
	#set($pagingMaklumatRayuan="CL - 01 - 183")
	#set($pagingMaklumatRekodRayuan="CL - 01 - 184")
	#set($pagingMaklumatSerahan="CL - 01 - 185")
	
#set($perhatian = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>dipilih.</font></i>")

#set($perhatian2 = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>diisi.</font></i>")

#set($perhatian3 = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>dipilih dan diisi.</font></i>")

<table width="100%" border="0">
<tr>
	<td >
	<div align="right">
	<span>
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:kembaliList()" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
  	#elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
 	#elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
	#elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
    #end
	
    </span> 
    </div>
   	</td>
</tr>
</table>

#foreach($data in $dataPemohon)
 	#set($noFail=$data.noFail)
 	#set($negeri=$data.pmNama_negeri)
 	#set($daerah=$data.namadaerah)
 	#set($unit=$data.namaPejabat)
 	#set($seksyen=$data.seksyen)
 	#set($statusFail=$data.keterangan)
 	#set($tarikhMohon=$data.tarikhMohon)
 	#set($namaSimati=$data.namaSimati)
 	#set($namaSipemohon=$data.namaPemohon)
 	#set($tarikhMati=$data.tarikhMati)
#end
<input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon">

<center>
#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<fieldset id="header_lama" style="display:none" >
	<legend><strong>PERMOHONAN RAYUAN</strong></legend>
	<table width="100%">
		<tr>
			<td width="50%" valign="top">
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td width="36%" valign="top">NO FAIL</td>
					<td width="1%" valign="top">:&nbsp;</td>
					<td width="63%" valign="top"><font color="blue">$noFail</font>
					<input type="hidden" name="tarikhMatihidden" value="$tarikhMati"></td>
				</tr>
				<tr>
					<td valign="top">NEGERI</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$negeri.toUpperCase()</font>
					<input type="hidden" name="namaNegerihidden" value="$negeri.toUpperCase()"></td>
				</tr>
				<tr>
					<td valign="top">DAERAH / JAJAHAN</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$daerah.toUpperCase()</font>
					<input type="hidden" name="namaDaerahhidden" value="$daerah.toUpperCase()">
					</td>
				</tr>
				<tr>
					<td valign="top">UNIT</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$unit.toUpperCase()</font></td>
				</tr>
			</table>
			</td>
				
			<td width="50%" valign="top">
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td width="34%" valign="top">STATUS FAIL</td>
					<td width="1%" valign="top">:&nbsp;</td>
					<td width="65%" valign="top"><font color="blue">$statusFail.toUpperCase()</font></td>
				</tr>
				<tr>
					<td valign="top">SEKSYEN</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$seksyen.toUpperCase()</font></td>
				</tr>
				<tr>
					<td valign="top">TARIKH MOHON</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$tarikhMohon</font></td>
				</tr>
				<tr>
					<td valign="top">NAMA PEMOHON</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$namaSipemohon.toUpperCase()</font></td>
				</tr>
				<tr>
					<td valign="top">NAMA SIMATI</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$namaSimati.toUpperCase()</font>
					<input type="hidden" name="namaSimatihidden" value="$namaSimati">
					</td>
					
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
</fieldset>

#if($!headerppk.size()>0)
#else
<script  type="text/javascript">
if(document.getElementById("header_lama").style.display=="none")
{
document.getElementById("header_lama").style.display="block";
}
</script>
#end


<br/>	

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);maklumatPerayu('$id_permohonan','$id_status')">MAKLUMAT PERAYU / PEGUAM</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(1);maklumatRayuan('$id_permohonan','$id_status')">MAKLUMAT RAYUAN</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(2);maklumatRekodRayuan('$id_permohonan','$id_status')">MAKLUMAT REKOD RAYUAN</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(3);maklumatSerahan('$id_permohonan','$id_status')">MAKLUMAT SERAHAN BORANG K2</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
<!-- Tab 1 START [Maklumat perayu / peguam] -->  
    <div class="TabbedPanelsContent">
    
<!-- START [new form] -->    	
#if($newform=="yes")    

#if($onchangePP=="no")
	#set($cnokp1="")
	#set($cnokp2="")
	#set($cnokp3="")
	#set($calamat1="")
	#set($calamat2="")
	#set($calamat3="")
	#set($cposkod="")
	#set($cbandar="")
	
	#set($cnamafirma="")
	#set($cnorujukan="")
	#set($calamatpeguam1="")
	#set($calamatpeguam2="")
	#set($calamatpeguam3="")
	#set($cposkodpeguam="")
	#set($cbandarpeguam="")
	#set($cnotel="")
	#set($cnofaks="")
	#set($cemel="")
#end

	<table width="100%"  cellpadding="2" cellspacing="2" border="0">
    	<tr>
    	<td width="50%" valign="top">
    	
    	<fieldset>
    	<legend><strong>MAKLUMAT PERAYU</strong></legend>
    		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    			<tr>
    				<td width="1%" valign="top"><font color="red">*</font></td>
    				<td width="26%">Nama Perayu</td>
    				<td width="1%">:</td>
    				<td width="72%"><select id="" name="socPerayu" onchange="changeGetAlamatPerayu('$id_permohonan','$id_status')" style="width:240">
							
							#if($onchangePP=="no")
                    	  	<option value="">SILA PILIH NAMA PERAYU</option>
 							#end
 							
 							#if($onchangePP=="yes")
 								#if($cidob!="")
 							<option value="$cidob">$cnamaob.toUpperCase()</option>						
 							<option value="">SILA PILIH NAMA PERAYU</option>
 								#else
 							<option value="">SILA PILIH NAMA PERAYU</option>	
 								#end
 							#end
 							
                    		#foreach($ob in $listOBatas18)
                    		#if($ob.id_ob != $cidob)	
                    		<option value="$ob.id_ob">$ob.nama_ob.toUpperCase()</option>
                           	#end	
                            #end 	
                            	
					</select></td>   				
    			</tr>
   #set($tic="readonly")
   #set($tic2="class=disabled") 			
    			<tr>
    				<td>&nbsp;</td>
    				<td>No.KP Perayu</td>
    				<td>:</td>
    				#if($nokpbaru!="")
    				<td><input type="text" $tic $tic2 name="txtNoKPBaru1" size="6" value="$!cnokp1" >-<input type="text" $tic $tic2 name="txtNoKPBaru2" size="1" value="$!cnokp2" >-<input type="text" $tic $tic2 name="txtNoKPBaru3" size="3" value="$!cnokp3" ></td>
    				<input type="hidden" name="whatkp" value="baru">
    				#elseif($nokplama!="")
    				<td><input type="text" $tic $tic2 name="txtNoKPLama" size="20" value="$!nokplama" ></td>
    				<input type="hidden" name="whatkp" value="lama">
    				#elseif($nokplain!="")
    				<td><input type="text" $tic $tic2 name="txtNoKPLain" size="20" value="$!nokplain" ></td>
    				<input type="hidden" name="whatkp" value="lain">
    				<input type="hidden" name="jenis_kp" value="$jenis_kp">
    				#else
    				<td><input type="text" $tic $tic2 name="" size="20" value="" ></td>
    				#end
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Alamat</td>
    				<td>:</td>
    				<td><input type="text" name="txtAlamatPerayu1" id="txtAlamatPerayu1" value="$!calamat1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td><input type="text" name="txtAlamatPerayu2" id="txtAlamatPerayu2" value="$!calamat2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"/></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td><input type="text" name="txtAlamatPerayu3" id="txtAlamatPerayu3" value="$!calamat3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"/></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Poskod</td>
    				<td>:</td>
    				<td><input type="text" name="txtPoskodPerayu" id="txtPoskodPerayu" value="$!cposkod" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Negeri</td>
    				<td>:</td>
    				<td>$!selectNegeriPerayu</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Bandar</td>
    				<td>:</td>
    				<td>$!selectBandarPerayu</td>
    			</tr> 
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    		</table>
    		
    	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
    	</table>
    	
    	</fieldset>
    	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    		</tr>
    	</table>
    	
		</td>
		
		<td width="50%" valign="top">
	
		<fieldset>
		<legend><strong>MAKLUMAT PEGUAM</strong></legend>
	
	#if($saiz_ft!=0)
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td><input type="radio" id="sorPeguam1" name="sorPeguam" value="1" $checkA CHECKED onclick="doCheckSOCTXT();" >Peguam terdahulu</td>
    		</tr>
    		<tr>
    			<td><input type="radio" id="sorPeguam2" name="sorPeguam" value="2" $checkB  onclick="doCheckSOCTXT();" >Peguam baru</td>
    		</tr>
    	</table>
    	<p/>
    #end	
    	
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    #if($saiz_ft!=0)		
    	#if($showTXT=="yes" && $showSOC=="no")	
    		<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input type="text" name="txtNamaFirma" id="txtNamaFirma" value="$!cnamafirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
    			</td>   
			</tr>
		#end	
		#if($showSOC=="yes" && $showTXT=="no")
			<tr>
    			<td width="25%">Nama Firma </td>
    			<td width="75%">:&nbsp;$!selectFirmaTerdahulu
    			</td>   
			</tr>
		#end	
	#else
			<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input type="text" name="txtNamaFirma" id="txtNamaFirma" value="$!cnamafirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
    			</td>   
			</tr>
			<input type="hidden" name="sorPeguam" value="0">
	#end	
			<tr>
    			<td>No.Rujukan</td>
    			<td>:&nbsp;<input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$!cnorujukan" size="35" maxlength="50" /></td>
    		</tr>
    		<tr>
    			<td>Alamat</td>
    			<td>:&nbsp;<input type="text" name="txtAlamatPeguam1" id="txtAlamatPeguam1" value="$!calamatpeguam1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input type="text" name="txtAlamatPeguam2" id="txtAlamatPeguam2" value="$!calamatpeguam2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input type="text" name="txtAlamatPeguam3" id="txtAlamatPeguam3" value="$!calamatpeguam3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>Poskod</td>
    			<td>:&nbsp;<input type="text" name="txtPoskodPeguam" id="txtPoskodPeguam" value="$!cposkodpeguam" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>Negeri</td>
    			<td>:&nbsp;$!selectNegeriPeguam</td>
    		</tr>
    		<tr>
    			<td>Bandar</td>
    			<td>:&nbsp;$!selectBandarPeguam</td>
    		</tr>
    		<!-- <tr>
    			<td>Bandar</td>
    			<td>:&nbsp;<input type="text" name="txtBandarPeguam" id="txtBandarPeguam" value="$cbandarpeguam" size="35" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr> -->
    		<tr>
    			<td>No.Telefon</td>
    			<td>:&nbsp;<input type="text" name="txtNoTelefon" id="txtNoTelefon" value="$!cnotel" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" />
    				<font color="blue" style="font-size:10px"><i>cth: 031234567</i></font></td>
    		</tr>
    		<tr>
    			<td>No.Faks</td>
    			<td>:&nbsp;<input type="text" name="txtNoFaks" id="txtNoFaks" value="$!cnofaks" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);"/></td>
    		</tr>
    		<tr>
    			<td valign="top">Emel</td>
    			<td>:&nbsp;<input type="text" name="txtEmel" id="txtEmel" value="$!cemel" size="35" maxlength="50" /><br/>
    				<font color="blue" style="font-size:10px">&nbsp;&nbsp;&nbsp;<i>cth: abc@email.com</i></font></td>
    		</tr>
    	</table>
    	
    	</fieldset>
    	
   		</td>
    	</tr>
	</table>
		
#end		
<!-- END [new form] -->

<!-- START [view form] -->    	
#if($newform=="no")    	

#if($onchangePP=="no" || $onchangeXX=="no")
#foreach($x in $maklumatRayuan)
	#set($namaPerayu = $x.nama_perayu)
	#set($nokpPerayu = $x.no_kp_baru)
	#set($nokpPerayu1 = $x.no_kp_baru1)
	#set($nokpPerayu2 = $x.no_kp_baru2)
	#set($nokpPerayu3 = $x.no_kp_baru3)
	#set($nokpLamaPerayu = $x.no_kp_lama)
	#set($nokpLainPerayu = $x.no_kp_lain)
	#set($alamatPerayu1 = $x.alamat_perayu1)
	#set($alamatPerayu2 = $x.alamat_perayu2)
	#set($alamatPerayu3 = $x.alamat_perayu3)
	#set($poskodPerayu = $x.poskod_perayu)
	#set($bandarPerayu = $x.bandar_perayu)
#end
#end

#if($onchangeXX=="yes")
	#set($alamatPerayu1 = $alamatPerayu1x)
	#set($alamatPerayu2 = $alamatPerayu2x)
	#set($alamatPerayu3 = $alamatPerayu3x)
	#set($poskodPerayu = $poskodPX)
	#set($bandarPerayu = $bandarPX)
#end



#if($editform=="no")
	#set($mode = "readonly")
	#set($mode1 = "disabled")
	#set($Cmode = "class=disabled")
#elseif($editform=="yes")
	#set($mode = "")
	#set($mode1 = "")
	#set($Cmode = "")
#end

<input type="hidden" name="onchangex" value="$onchangePP">


	<table width="100%"  cellpadding="2" cellspacing="2" border="0">
    	<tr>
    	<td width="50%" valign="top">
    	<fieldset>
    	<legend><strong>MAKLUMAT PERAYU</strong></legend>
    	
    		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    			<tr>
    				<td width="1%" valign="top">#if($editform=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    				<td width="26%">Nama Perayu</td>
    				<td width="1%">:</td>
    				<td width="72%">
    				#if($formTambah=="viewANDedit")
    				<select $Cmode  $mode1 id="" name="EsocPerayu" onchange="changeGetAlamatPerayuUPDATETambah('$id_permohonan','$id_status')" style="width:240">
					#else
    				<select $Cmode  $mode1 id="" name="EsocPerayu" onchange="changeGetAlamatPerayuUPDATE('$id_permohonan','$id_status')" style="width:240">
					#end
    						
							#if($onchangePP=="no")
                    	  	<option value="$namaPerayu">$namaPerayu.toUpperCase()</option>
 							#end
 							
 							#if($onchangePP=="yes")
 								#if($cidob!="")
 							<option value="$cidob">$cnamaob.toUpperCase()</option>						
 							
 								#else
 							<option value="">SILA PILIH NAMA PERAYU</option>	
 								#end
 							#end
 							
                    		#foreach($ob in $listOBatas18)
                    			#if($ob.nama_ob != $namaPerayu)
                    			#if($ob.id_ob != $cidob)	
                    			<option value="$ob.id_ob">$ob.nama_ob.toUpperCase()</option>
                           		#end
                           		#end
                            #end 	
                            	
					</select></td> 
    			</tr>
		
   #set($ti="readonly")
   #set($ti2="class=disabled") 			
    			<tr>
    				<td>&nbsp;</td>
    				<td>No.KP Perayu</td>
    				<td>:</td>
    				#if($nokpPerayu!="")
    				<td><input type="text" $ti $ti2 name="EtxtNoKPBaru1" size="6" value="$nokpPerayu1" >-<input type="text" $ti $ti2 name="EtxtNoKPBaru2" size="1" value="$nokpPerayu2" >-<input type="text" $ti $ti2 name="EtxtNoKPBaru3" size="3" value="$nokpPerayu3" ></td>
    				<input type="hidden" name="Ewhatkp" value="baru">
    				#elseif($nokpLamaPerayu!="")
    				<td><input type="text" $ti $ti2 name="EtxtNoKPLama" size="20" value="$!nokpLamaPerayu" ></td>
    				<input type="hidden" name="Ewhatkp" value="lama">
    				#elseif($nokpLainPerayu!="")
    				<td><input type="text" $ti $ti2 name="EtxtNoKPLain" size="20" value="$!nokpLainPerayu" ></td>
    				<input type="hidden" name="Ewhatkp" value="lain">
    				#else
    				<td><input type="text" $ti $ti2 name="" size="20" value="" ></td>
    				#end
    			</tr>	
    			
    			<tr>
    				<td>&nbsp;</td>
    				<td>Alamat</td>
    				<td>:</td>
    				<td><input $Cmode  $mode type="text" name="EtxtAlamatPerayu1" id="EtxtAlamatPerayu1" value="$!alamatPerayu1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td><input $Cmode  $mode type="text" name="EtxtAlamatPerayu2" id="EtxtAlamatPerayu2" value="$!alamatPerayu2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"/></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td><input $Cmode  $mode type="text" name="EtxtAlamatPerayu3" id="EtxtAlamatPerayu3" value="$!alamatPerayu3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"/></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Poskod</td>
    				<td>:</td>
    				<td><input type="text" $Cmode $mode name="EtxtPoskodPerayu" id="EtxtPoskodPerayu" value="$!poskodPerayu" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Negeri</td>
    				<td>:</td>
    				<td>$!viewNegeriPerayu</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>Bandar</td>
    				<td>:</td>
    				<td>$!viewBandarPerayu</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			#if($formTambah=="yes")
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    				<td>&nbsp;</td>
    			</tr>
    			#end
    		</table>
    		
    #if($editform=="yes")		
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian</td></tr>
    </table>
    #end 
    
    	</fieldset>
    	
    	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    		</tr>
    	</table>
    	
		</td>
		
		<td width="50%" valign="top">
#if($formTambah=="no")

#if($onchangePP=="no" || $changeDATA=="default")
	#foreach($xp in $dataLatestPeguam)
 		#set($namaFirma = $xp.nama_firma)
		#set($noRujukan = $xp.no_rujukan_firma)
		#set($alamatPeguam1 = $xp.alamat1)
		#set($alamatPeguam2 = $xp.alamat2)
		#set($alamatPeguam3 = $xp.alamat3)
		#set($poskodPeguam = $xp.poskod)
		#set($bandarPeguam = $xp.bandar)
		#set($noTel = $xp.no_tel)
		#set($noFax = $xp.no_fax)
		#set($emel = $xp.emel)
	#end
#end		
	
#if($changeDATA=="clear")
 		#set($namaFirma = "")
		#set($noRujukan = "")
		#set($alamatPeguam1 = "")
		#set($alamatPeguam2 = "")
		#set($alamatPeguam3 = "")
		#set($poskodPeguam = "")
		#set($bandarPeguam = "")
		#set($noTel = "")
		#set($noFax = "")
		#set($emel = "")
#end	
	
#if($changeDATA=="databylist")
		#set($namaFirma = "$namaFirma_")
		#set($noRujukan = "$noRujukan_")
		#set($alamatPeguam1 = "$alamatPeguam1_")
		#set($alamatPeguam2 = "$alamatPeguam2_")
		#set($alamatPeguam3 = "$alamatPeguam3_")
		#set($poskodPeguam = "$poskodpeguam_")
		#set($bandarPeguam = "$bandarpeguam_")
		#set($noTel = "$notel_")
		#set($noFax = "$nofaks_")
		#set($emel = "$emel_")
#end
	
	#if($namaFirma=="" && $noRujukan=="" && $alamatPeguam1=="" && $alamatPeguam2=="" && $alamatPeguam3=="" && $poskodPeguam=="" && $bandarPeguam=="" && $noTel=="" && $noFax=="" && $emel=="")
		
		#if($editform=="no")	
			#set($showLIST = "off")
		#end	
		<input type="hidden" name="canEdit" value="yes">
	#else
		
		#if($editform=="no")
			#set($showLIST = "on")	
		#end
		
		#if($listFirma.size()>=2)
			<input type="hidden" name="canEdit" value="no">
		#else
			<input type="hidden" name="canEdit" value="yes">
		#end
		
	#end

<input type="hidden" name="onchangeXX" value="$onchangeXX">
		
		<fieldset>
		<legend><strong>MAKLUMAT PEGUAM</strong></legend>
		
	#if($editform=="yes")	
		#if($saiz_ft!=0)
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td><input type="radio" id="sorPeguamUpdate1" name="sorPeguamUpdate" value="1"  $checkAx onclick="doCheckSOCTXTUpdateTerkini();" >Peguam terdahulu</td>
    		</tr>
    		<tr>
    			<td><input type="radio" id="sorPeguamUpdate2" name="sorPeguamUpdate" value="2" $checkBx onclick="doCheckSOCTXTUpdateTerkini();" >Peguam baru</td>
    		</tr>
    	</table>
    	<p/>
    	#end
	 #end 		
 
		 
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
	
	#if($editform=="yes")		
		#if($saiz_ft!=0)					
    			#if($EshowTXT=="yes" && $EshowSOC=="no")	
    		<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input type="text" name="EtxtNamaFirma" id="EtxtNamaFirma" value="$!namaFirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
    			</td>   
			</tr>
				#end	
				#if($EshowSOC=="yes" && $EshowTXT=="no")
			<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;$!selectFirmaTerdahuluX
    			</td>   
			</tr>
				#end				
		#else
 			<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input type="text" name="EtxtNamaFirma" id="EtxtNamaFirma" value="$!namaFirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
 		#end
 	#else
 			<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input $Cmode $mode type="text" name="EtxtNamaFirma" id="EtxtNamaFirma" value="$!namaFirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
 	#end	
    		<tr>	
 			
    			<td>No.Rujukan</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtNoRujukan" id="EtxtNoRujukan" value="$!noRujukan" size="35" maxlength="50" /></td>
    		</tr>
    		<tr>
    			<td>Alamat</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtAlamatPeguam1" id="EtxtAlamatPeguam1" value="$!alamatPeguam1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input $Cmode $mode type="text" name="EtxtAlamatPeguam2" id="EtxtAlamatPeguam2" value="$!alamatPeguam2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input $Cmode $mode type="text" name="EtxtAlamatPeguam3" id="EtxtAlamatPeguam3" value="$!alamatPeguam3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>Poskod</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtPoskodPeguam" id="EtxtPoskodPeguam" value="$!poskodPeguam" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>Negeri</td>
    			<td>:&nbsp;$!viewNegeriPeguam</td>
    		</tr>
    		<tr>
    			<td>Bandar</td>
    			<td>:&nbsp;$!viewBandarPeguam</td>
    		</tr>
    		<!-- <tr>
    			<td>Bandar</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtBandarPeguam" id="EtxtBandarPeguam" value="$bandarPeguam" size="35" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr> -->
    		<tr>
    			<td>No.Telefon</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtNoTelefon" id="EtxtNoTelefon" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" value="$!noTel" size="13" maxlength="14">
    				#if($editform=="yes")<font color="blue" style="font-size:10px"><i>cth: 031234567</i></font>#end</td>
    		</tr>
    		<tr>
    			<td>No.Faks</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtNoFaks" id="EtxtNoFaks" value="$!noFax" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td valign="top">Emel</td>
    			<td>:&nbsp;<input type="text" $Cmode $mode name="EtxtEmel" id="EtxtEmel" value="$!emel" size="35" maxlength="50" /><br/>
    				#if($editform=="yes")<font color="blue" style="font-size:10px">&nbsp;&nbsp;&nbsp;<i>cth: abc@email.com</i></font>#end</td>
    		</tr>
    	</table>
   		
		</fieldset>
#end	

#if($formTambah=="yes")

	 
	<fieldset>
		<legend><strong>MAKLUMAT PEGUAM</strong></legend>
		#if($saiz_ft!=0)
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td><input type="radio" id="sorPeguam1" name="sorPeguam" value="1" $checkA CHECKED onclick="doCheckSOCTXTTambah();" >Peguam terdahulu</td>
    		</tr>
    		<tr>
    			<td><input type="radio" id="sorPeguam2" name="sorPeguam" value="2" $checkB  onclick="doCheckSOCTXTTambah();" >Peguam baru</td>
    		</tr>
    	</table>
    	
    	<p/>
		#end
		
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    	#if($saiz_ft!=0)	
    		#if($showTXT=="yes" && $showSOC=="no")	
    		<tr>
    			<td width="25%">Nama Firma </td>
    			<td width="75%">:&nbsp;<input type="text" name="txtNamaFirma" id="txtNamaFirma" value="$namaFirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>   
			</tr>
			#end	
			#if($showSOC=="yes" && $showTXT=="no")
			<tr>
    			<td width="25%">Nama Firma </td>
    			<td width="75%">:&nbsp;$selectFirmaTerdahulu</td>   
			</tr>
			#end
		#else
			<tr>
    			<td width="25%">Nama Firma </td>
    			<td width="75%">:&nbsp;<input type="text" name="txtNamaFirma" id="txtNamaFirma" value="$namaFirma" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>   
			</tr>
			<input type="hidden" name="sorPeguam" value="0">
		#end	
    		<tr>
    			<td>No.Rujukan </td>
    			<td>:&nbsp;<input type="text" name="txtNoRujukan" id="txtNoRujukan" value="$!xnoRujukan" size="35" maxlength="50" /></td>
    		</tr>
    		<tr>
    			<td>Alamat</td>
    			<td>:&nbsp;<input type="text" name="txtAlamatPeguam1" id="txtAlamatPeguam1" value="$!xalamatPeguam1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input type="text" name="txtAlamatPeguam2" id="txtAlamatPeguam2" value="$!xalamatPeguam2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input type="text" name="txtAlamatPeguam3" id="txtAlamatPeguam3" value="$!xalamatPeguam3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>Poskod</td>
    			<td>:&nbsp;<input type="text" name="txtPoskodPeguam" id="txtPoskodPeguam" value="$!xposkodPeguam" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>Negeri</td>
    			<td>:&nbsp;$!tambahNegeriPeguam</td>
    		</tr>
    		<tr>
    			<td>Bandar</td>
    			<td>:&nbsp;$!tambahBandarPeguam</td>
    		</tr>
    		<!-- <tr>
    			<td>Bandar</td>
    			<td>:&nbsp;<input type="text" name="txtBandarPeguam" id="txtBandarPeguam" value="$!xbandarPeguam" size="35" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr> -->
    		<tr>
    			<td>No.Telefon</td>
    			<td>:&nbsp;<input type="text" name="txtNoTelefon" id="txtNoTelefon" value="$!xnoTel" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);">
    				<font color="blue" style="font-size:10px"><i>cth: 031234567</i></font></td>
    		</tr>
    		<tr>
    			<td>No.Faks</td>
    			<td>:&nbsp;<input type="text" name="txtNoFaks" id="txtNoFaks" value="$!xnoFax" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);"></td>
    		</tr>
    		<tr>
    			<td valign="top">Emel</td>
    			<td>:&nbsp;<input type="text" name="txtEmel" id="txtEmel" value="$!xemel" size="35" maxlength="50"  /><br/>
    				<font color="blue" style="font-size:10px">&nbsp;&nbsp;&nbsp;<i>cth: abc@email.com</i></font></td>
    		</tr>
    	</table>
   		
		</fieldset>
		
		
#end	

#if($formTambah=="viewANDedit")

#if($onchangePP=="no" || $changeDATAx=="default")
#foreach($xot in $otherPeguam)
 	#set($namaF = $xot.nama_firma)
	#set($noR = $xot.no_rujukan_firma)
	#set($alamatP1 = $xot.alamat1)
	#set($alamatP2 = $xot.alamat2)
	#set($alamatP3 = $xot.alamat3)
	#set($poskodP = $xot.poskod)
	#set($bandarP = $xot.bandar)
	#set($noT = $xot.no_tel)
	#set($noF = $xot.no_fax)
	#set($emelO = $xot.emel)
#end		
#end

#if($changeDATAx=="datafromview")
		#set($namaF = "$namaFx")
		#set($noR = "$noRx")
		#set($alamatP1 = "$alamatP1x")
		#set($alamatP2 = "$alamatP2x")
		#set($alamatP3 = "$alamatP3x")
		#set($poskodP = "$poskodPx")
		#set($bandarPeguam = "$bandarPeguamx")
		#set($noT = "$noTx")
		#set($noF = "$noFx")
		#set($emelO = "$emelOx")
#end

#if($editTambah=="no")
	#set($modeX="readonly")
	#set($CmodeX="class=disabled")
#else
	#set($modeX="")
	#set($CmodeX="")
#end

		<fieldset>
		<legend><strong>MAKLUMAT PEGUAM</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		<tr>
    			<td width="25%">Nama Firma</td>
    			<td width="75%">:&nbsp;<input $CmodeX $modeX type="text" name="txtNamaFirma" id="txtNamaFirma" value="$namaF" size="35" maxlength="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>No.Rujukan</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtNoRujukan" id="txtNoRujukan" value="$noR" size="35" maxlength="50" /></td>
    		</tr>
    		<tr>
    			<td>Alamat</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtAlamatPeguam1" id="txtAlamatPeguam1" value="$alamatP1" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input $CmodeX $modeX type="text" name="txtAlamatPeguam2" id="txtAlamatPeguam2" value="$alamatP2" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td><font color="white">:</font>&nbsp;<input $CmodeX $modeX type="text" name="txtAlamatPeguam3" id="txtAlamatPeguam3" value="$alamatP3" size="35" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>Poskod</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtPoskodPeguam" id="txtPoskodPeguam" value="$poskodP" size="5" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>Negeri</td>
    			<td>:&nbsp;$!vneNegeriPeguam</td>
    		</tr>
    		<tr>
    			<td>Bandar</td>
    			<td>:&nbsp;$!vneBandarPeguam</td>
    		</tr>
    		<!-- <tr>
    			<td>Bandar</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtBandarPeguam" id="txtBandarPeguam" value="$bandarP" size="35" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr> -->
    		
    		<tr>
    			<td>No.Telefon</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtNoTelefon" id="txtNoTelefon" value="$noT" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);">
    				#if($editTambah=="yes")<font color="blue" style="font-size:10px"><i>cth: 031234567</i></font>#end</td>
    		</tr>
    		<tr>
    			<td>No.Faks</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtNoFaks" id="txtNoFaks" value="$noF" size="13" maxlength="14" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);"></td>
    		</tr>
    		<tr>
    			<td valign="top">Emel</td>
    			<td>:&nbsp;<input $CmodeX $modeX type="text" name="txtEmel" id="txtEmel" value="$emelO" size="35" maxlength="50"  /><br/>
    				#if($editTambah=="yes")<font color="blue" style="font-size:10px">&nbsp;&nbsp;&nbsp;<i>cth: abc@email.com</i></font>#end</td>
    		</tr>
    	</table>
   		
		</fieldset>
#end

#if($listFirma_size!=0)
	#if($showLIST == "on")
	<fieldset>	
	
	
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
    		#if($formTambah=="no" || $formTambah=="viewANDedit")
    			#if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
    			<tr>
    				#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
    				<td colspan="2" align="left"><input type="button"  name="cmdTambah" value="Tambah" onClick="tambahPeguam('$id_permohonan','$id_status')" /></td>
    				#else
    				<td colspan="2" align="left">&nbsp;</td>
    				#end
    			</tr>
    			#end
    		#end
    		
    		
    		<tr class="table_header">
    			<td width="10%" align="center"><b>No</b></td>
    			<td width="90%"><b>&nbsp;Nama Firma</b></td>
    		</tr>
    		   
           #foreach($listF in $listFirma)
           		#set($id_peguamList = $listF.id_peguam)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
    		
    		<tr>
    			<td align="center" class="$row">$listF.bil</td>
    			<td class="$row"><a href="javascript:semakFirma('$id_peguamList')"><font color="blue">&nbsp;$listF.nama_firma.toUpperCase()</font></a></td>
    		</tr> 
    		 
    	   #end	 
    		  		
    	</table>
    	
    	
	</fieldset>
	#end	
#end
    	
		</td>
    	</tr>
	</table>
    	
#end		
	
<!-- END [view form] -->
    			
	
#if($formTambah=="no")
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr align="center"><td>
		
		#if($newform=="yes" && ($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")) 
		<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanMaklumatPP()" />
		#end
		
		#if($newform=="no") 
			#if($editform=="no")
			
				#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
				<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180") #end-->
				#if($id_status!="169")
					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMaklumatPP('$id_permohonan','$id_pemohon','$id_status')" />
					#end
				<!-- <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" /> -->
				#end
				
		#end
			#if($editform=="yes")
		<input type="button" name="cmdSimpan" value="Simpan" onClick="updateMaklumatPP('$id_permohonan','$id_pemohon','$id_status')" />
		<input type="button" name="cmdBatal" value="Batal" onClick="batalMaklumatPP('$id_permohonan','$id_status')" />
			#end
		#end
		
		<!-- <input type="button" name="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan','$id_status')" />-->
		
		</td></tr>
	</table>
#end	

#if($formTambah=="yes")
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr align="center"><td>
		
		<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanTambahPeguam()" />
		<input type="button" name="cmdBatal" value="Batal" onClick="batalMaklumatPP('$id_permohonan','$id_status')" />
		<!-- <input type="button" name="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan','$id_status')" />-->
		</td></tr>
	</table>
#end
	
#if($formTambah=="viewANDedit")
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr align="center"><td>
		#if($editTambah=="no")
		<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
		
			#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
					#if($id_status!="169")
					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniX('$id_permohonan','$id_pemohon','$id_status','$id_peguamX')" />
					#end
			
				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" />
			#end
		<!--<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport')" /> -->
		#end
		#if($editTambah=="yes")
		<input type="button" name="cmdSimpan" value="Simpan" onClick="updateTambahPeguam('$id_permohonan','$id_status','$id_peguamX')" />
		<input type="button" name="cmdBatal" value="Batal" onClick="batalX('$id_permohonan','$id_status','$id_peguamX')" />
		#end
		<!-- <input type="button" name="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan','$id_status')" /> -->
		
		
		</td></tr>
	</table>
#end	

<table width="98%"  cellpadding="1" cellspacing="1" border="0">
  			<tr>
  				<td align="right">$!pagingMaklumatPP</td>
  			</tr>
</table>

<fieldset id="tableReport" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangK1('$noFail','$idnegerirayuan','$id_perbicaraan')"><font color="blue">Borang K3</font></a></td>
      </tr> 
    </table>      
</fieldset> 


	
    </div>    
<!-- Tab 1 END [Maklumat perayu / peguam] -->      


<!-- Tab 2 START [Maklumat rayuan] --> 
    <div class="TabbedPanelsContent">

#if($editable=="yes")
	#set($mode2 = "")
#elseif($editable=="no")
	#set($mode2 = "readonly")
#end

#if($editform=="no")
	#set($mode3 = "readonly")
	#set($Cmode3 = "class=disabled")
#elseif($editform=="yes")
	#set($mode3 = "")
	#set($Cmode3 = "")
#end


#if($maklumatRayuan_size!=0)
	#foreach($z in $maklumatRayuan)
		#set($tarikhRayuan = $z.tarikh_rayuan)
		#set($perkara = $z.perkara_rayu)
	#end
#else
		#set($tarikhRayuan = "$!tarikh_rayuan")
		#set($perkara = "")
#end

	<fieldset>
	<legend>MAKLUMAT RAYUAN</legend>
    <table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	<tr><td colspan="4">&nbsp;</td></tr>
    	<tr>
    		<td width="1%" valign="top">&nbsp;</td>
    		<td width="18%">Tarikh Rayuan</td>
    		<td width="1%">:&nbsp;</td>
    		<td width="80%"><font color="blue">$!tarikhRayuan</font></td>
    	</tr>
    	<tr><td colspan="4">&nbsp;</td></tr>
    	
    	<tr>
    		<td valign="top">#if($editform=="yes")<font color="red">*</font>#else&nbsp;#end </td>
    		<td valign="top" colspan="3">Perkara yang dirayu :</td>
    	</tr>
    	
    	<tr>
    		<td>&nbsp;</td>
    		<td colspan="3"><textarea name="txtPerkaraRayu" id="txtPerkaraRayu" $Cmode3 $mode3 $mode2 cols="85%" rows="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkaraRayu,this.form.remLen1,2000);" onKeyDown="textCounter(this.form.txtPerkaraRayu,this.form.remLen1,2000);">$!perkara</textarea></td>
    	</tr>
   		
   		<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><b>Jumlah Perkataan :</b> $!totalWordPerkaraRayu</td>
	 	</tr>
	 	
   		<!-- <tr>
   			<td>&nbsp;</td>
            <td colspan="3" valign="top"><div  class="disabled" id="word_count"></div></td>
        </tr> -->
   		
   		
   
    	<script> 
            var area = new FCKeditor("txtPerkaraRayu");
	      	area.BasePath = '/${appname}/library/fck/' ;
	      	area.Height = 200;
	      	area.Width = 600;
	      	area.ReplaceTextarea();     
		</script>
    	
    	#if($editable=="yes")   
    		#if($editform=="yes")	
    		<input type="hidden" name="contentoE" value="true">
    		<input type="hidden" name="designoE" value="on">
    		#else
    		<input type="hidden" name="contentoE" value="false">
    		<input type="hidden" name="designoE" value="off">	
    		#end
    	#else
    		<input type="hidden" name="contentoE" value="false">
    		<input type="hidden" name="designoE" value="off">		
    	#end
    		
    	<script>

			var contentoE = document.${formName}.contentoE.value;
			var designoE = document.${formName}.designoE.value;

    		var oEditor = FCKeditorAPI.GetInstance('txtPerkaraRayu');
    	
    		function FCKeditor_OnComplete(oEditor)
    		{
    			oEditor.EditorDocument.body.contentEditable=contentoE;
    			oEditor.EditorDocument.designMode=designoE; 	
    			if(contentoE=="false"){
    				oEditor.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}	
    		}
		</script>
    </table>
    
    <br/>
    
    #if($editform=="yes")
    <br/>
    <table width="92%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian2</td>
        	</tr>
    </table>
    #end 
    
	</fieldset>
<br/>

  #if($editable=="yes")
   <table width="100%"  cellpadding="1" cellspacing="2" border="0">
   		<tr align="center">
   			<td>
   		#if($buttonBatal!="no")
   			#if($editform=="no")
   				<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
   				#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
	   				#if($id_status!="169")
	   				<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniDataRayuan('$id_permohonan','$id_pemohon','$id_status','$id_rayuan')" />
					#end
				#else
					<input type="button" name="kembali" value="Kembali" onClick="history.back();" />
				#end
			#end
			#if($editform=="yes")
			<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanDataRayuan()" />
			<input type="button" name="cmdBatal" value="Batal" onClick="batalDataRayuan('$id_permohonan','$id_pemohon','$id_status')" />
			#end
			<!-- <input type="button" name="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan','$id_status')" /> -->
   			
   		#else
   			<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanDataRayuan()" />
			<!-- <input type="button" name="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan','$id_status')" /> -->
   		#end	
   			</td>
   		</tr>
   </table>
   #end
   
   <table width="98%"  cellpadding="1" cellspacing="1" border="0">
  			<tr>
  				<td align="right">$!pagingMaklumatRayuan</td>
  			</tr>
   </table>
    </div>   
<!-- Tab 2 END [Maklumat rayuan] -->   



<!-- Tab 3 START [Maklumat rekod rayuan] --> 
    <div class="TabbedPanelsContent">
	
#if($editable=="yes")
	#set($modeRR = "")
	#set($modeRR2 = "")
#elseif($editable=="no")
	#set($modeRR = "readonly class=disabled")
	#set($modeRR2 = "disabled")
#end	
	
#if($editform=="yes")
	#set($modeRRx = "")
	#set($modeRRx2 = "")
#else
	#set($modeRRx = "readonly class=disabled")
	#set($modeRRx2 = "disabled")
#end	

	
#if($maklumatRayuan_size!=0)
	#foreach($rr in $maklumatRayuan)
		#set($asasKeputusan = $rr.asas_keputusan)
		#set($notaBicara = $rr.nota_bicara)
		#set($Borang_A = $rr.Borang_A)
		#set($Borang_SA = $rr.Borang_SA)
		#set($Borang_DDA = $rr.Borang_DDA)
		#set($qBorang1P = $rr.qBorang1P)
		#set($qBorang1A = $rr.qBorang1A)
		#set($qBorang1SA = $rr.qBorang1SA)
		#set($qBorang1DDA = $rr.qBorang1DDA)
		#set($Borang_P = $rr.Borang_P)
		#set($JUMLAH_BAYARAN = $rr.JUMLAH_BAYARAN)
		#set($FLampiran1 = $rr.FLampiran1)
		#set($F2Lampiran1 = $rr.F2Lampiran1)
		#set($QLampiran1 = $rr.QLampiran1)
		#set($Lampiran1 = $rr.Lampiran1)
		#set($FLampiran2 = $rr.FLampiran2)
		#set($F2Lampiran2 = $rr.F2Lampiran2)
		#set($QLampiran2 = $rr.QLampiran2)
		#set($Lampiran2 = $rr.Lampiran2)
		
		#set($FLampiran3 = $rr.FLampiran3)
		#set($F2Lampiran3 = $rr.F2Lampiran3)
		#set($QLampiran3 = $rr.QLampiran3)
		#set($Lampiran3 = $rr.Lampiran3)
		
		#set($FLampiran4 = $rr.FLampiran4)
		#set($F2Lampiran4 = $rr.F2Lampiran4)
		#set($QLampiran4 = $rr.QLampiran4)
		#set($Lampiran4 = $rr.Lampiran4)
		
		#set($FLampiran5 = $rr.FLampiran5)
		#set($F2Lampiran5 = $rr.F2Lampiran5)
		#set($QLampiran5 = $rr.QLampiran5)
		#set($Lampiran5 = $rr.Lampiran5)
		
		#set($FLampiran6 = $rr.FLampiran6)
		#set($F2Lampiran6 = $rr.F2Lampiran6)
		#set($QLampiran6 = $rr.QLampiran2)
		#set($Lampiran6 = $rr.Lampiran6)
		
		#set($FLampiran7 = $rr.FLampiran7)
		#set($F2Lampiran7 = $rr.F2Lampiran7)
		#set($QLampiran7 = $rr.QLampiran7)
		#set($Lampiran7 = $rr.Lampiran7)
		
		#set($FLampiran8 = $rr.FLampiran8)
		#set($F2Lampiran8 = $rr.F2Lampiran8)
		#set($QLampiran8 = $rr.QLampiran8)
		#set($Lampiran8 = $rr.Lampiran8)
	#end
#else
		#set($asasKeputusan = "")
		#set($notaBicara = "")
#end
	
<fieldset>
<legend>MAKLUMAT REKOD RAYUAN</legend>	

	 <table width="95%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr><td colspan="3">&nbsp;</td></tr>
	 	<tr>
	 		<td width="1%">#if($editform=="yes")<font color="red">*</font>#else&nbsp;&nbsp;#end</td>
	 		<td colspan="2" width="99%">Asas - asas keputusan :</td>
	 	</tr>
	 	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><textarea $modeRR $modeRRx name="txtAsasKeputusan" cols="80%" rows="12" onKeyUp="textCounter(this.form.txtAsasKeputusan,this.form.remLen3,2000);" onKeyDown="textCounter(this.form.txtAsasKeputusan,this.form.remLen3,2000);">$!asasKeputusan</textarea></td>
	 	</tr>
	 	
	 	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="3"><b>Jumlah Perkataan :</b> $!totalWordAsasKeputusan</td>
	 	</tr>
	 	
	 	<script> 
            var areaAsas = new FCKeditor("txtAsasKeputusan");
            areaAsas.BasePath = '/${appname}/library/fck/' ;
            areaAsas.Height = 200;
            areaAsas.Width = 600;
            areaAsas.ReplaceTextarea();             	
        </script>
	 	
	 	#if($editable=="yes") 
    		#if($editform=="yes")
    		<input type="hidden" name="contentoEAsas" value="true">
    		<input type="hidden" name="designoEAsas" value="on">
    		#else
    		<input type="hidden" name="contentoEAsas" value="false">
    		<input type="hidden" name="designoEAsas" value="off">	
    		#end
    	#else
    		<input type="hidden" name="contentoEAsas" value="false">
    		<input type="hidden" name="designoEAsas" value="off">		
    	#end
	 	
	 	<script>
			var contentoEAsas = document.${formName}.contentoEAsas.value;
			var designoEAsas = document.${formName}.designoEAsas.value;

    		var oEditorAsas = FCKeditorAPI.GetInstance('txtAsasKeputusan');
    		function FCKeditor_OnComplete(oEditorAsas)
    		{
    			oEditorAsas.EditorDocument.body.contentEditable=contentoEAsas;
    			oEditorAsas.EditorDocument.designMode=designoEAsas;
    			if(contentoEAsas=="false"){
    				oEditorAsas.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}	
    		}
    	</script>
	 	#if($editform=="no")
	 	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2" align="center"><input type="button" value="Cetak Asas Keputusan" onclick="PrintAsasKeputusan()"></td>
        </tr>
	 	 #end	
    </table>

<br/>

	
    <table width="95%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr>
	 		<!-- <td width="1%">#if($editform=="yes")<font color="red">*</font>#else&nbsp;&nbsp;#end</td> -->
	 		<td width="1%">&nbsp;</td>
	 		<td colspan="2" width="99%">Senarai orang berkepentingan :</td>
	 	</tr>
	</table> 	 
	
	#if($listOB_size!=0)
		#set($ds="")
	#else
		#set($ds="disabled")	
	#end
	
	#if ($listOB_size > 10)
       	<div style="width:90%;height:252;overflow:auto"> 
       	#set($sizeList="100%")
    #else
    	#set($sizeList="93%")
    #end 
	
 	<table width="$sizeList"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
        	<td width="4%" align="center"><b>Bil</b></td>
        	<td width="6%" align="center"><input type="checkbox" $ds $modeRR2 $modeRRx2 title="Pilih Semua" name="checkall" onClick="this.value=checkALL(this.form.cbsemaks,$listOB_size)"></td>
        	<td width="90%"><b>Nama OB</b></td>
        </tr>
        
        #if($listOB_size!=0)
           #foreach($ob in $listOB)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         		#if($ob.flag!="0")
         			#set($cbcheck="checked")
         		#else
         			#set($cbcheck="")
         		#end
         		
        <tr>
        	<td class="$row" align="center">$!ob.bil</td>
        	<td class="$row" align="center"><input type="checkbox" $cbcheck $modeRR2 $modeRRx2 name="cbsemaks" value="$ob.id_ob"></td>
        	<td class="$row">$!ob.nama_ob.toUpperCase()</td>
        </tr>
        
        	#end
        <tr>
        	<td colspan="3">&nbsp;</td>
        </tr>	
  		#else		
  		<tr>
  			<td colspan="3" class="$row">Tiada Rekod</td>
  		</tr>
  		<tr>
        	<td colspan="3">&nbsp;</td>
        </tr>	
  		#end
  		
    </table>

	#if ($listOB_size > 10)
       </div>
    #end 

<br/> 

       
    <table width="95%"  cellpadding="1" cellspacing="1" border="0"> 
    	<tr>
	 		<td width="1%">#if($editform=="yes")<font color="red">*</font>#else&nbsp;&nbsp;#end</td>
	 		<td colspan="2" width="99%">Maklumat nota bicara :</td>
	 	</tr>   
        <tr>
        	<td>&nbsp;</td>
	 		<td colspan="2"><textarea $modeRR $modeRRx name="txtNotaBicara" cols="80%" rows="12" onKeyUp="textCounter(this.form.txtNotaBicara,this.form.remLen4,2000);" onKeyDown="textCounter(this.form.txtNotaBicara,this.form.remLen4,2000);">$!notaBicara</textarea></td>
	 	</tr>
	 	
	 	<script> 
            var areaNota = new FCKeditor("txtNotaBicara");
            areaNota.BasePath = '/${appname}/library/fck/' ;
            areaNota.Height = 200;
            areaNota.Width = 600;
            areaNota.ReplaceTextarea();             	
        </script>
        
        <script>
			var contentoEAsas = document.${formName}.contentoEAsas.value;
			var designoEAsas = document.${formName}.designoEAsas.value;

    		var oEditorNota = FCKeditorAPI.GetInstance('txtNotaBicara');
    		function FCKeditor_OnComplete(oEditorNota)
    		{
    			oEditorNota.EditorDocument.body.contentEditable=contentoEAsas;
    			oEditorNota.EditorDocument.designMode=designoEAsas;
    			if(contentoEAsas=="false"){
    				oEditorNota.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}	
    		}
    	</script>
        
	 	<!-- <tr>
	 		<td>&nbsp;</td>
            <td colspan="2"><input type="button" value="Papar" onclick="displayHTML(this.form.txtNotaBicara)">&nbsp;#if($editform=="yes")Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="2000">#end</td>
        </tr> -->
	 	
	 	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><b>Bayaran Rekod Rayuan :</b> RM $bayaranNB &nbsp;<b>Jumlah Perkataan :</b> $!totalWord</td>
	 	</tr>
	 	
     </table>
     
     <p align="left">
     Eksibit Dokumen :
     <br/>
     <table align ="left" width="80%"  cellpadding="0" cellspacing="0" border="1" id="POITable"> 
    	
        <tr class="table_header">
        	<td align="center" width="40%"><b>Jenis Dokumen</b></td>
	 		<td align="center" width="20%"><b>Salinan Pertama</b> </td>
	 		<td align="center" width="20%"><b>Salinan kedua dan seterusnya</b></td>
	 		<td align="center" width="20%"><b>Jumlah</b></td>
	 	</tr>
	 	<tr>
	 	<td>&nbsp;Borang A</td>
	 	#set ($jumlahfeeBorangA = $Borang_A * 10)
	 	
	 	#set ($jumlahfeeBorangA = $jumlahfeeBorangA + ($qBorang1A * 30) )
	 	
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" name="feeBorangA1" value="30" disabled> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="q1BorangA" size ="2" value="$qBorang1A" onkeypress='return event.charCode >= 48 && event.charCode <= 49 || event.charCode == 0'></td>
	 	
	 	#if($editable=="yes") 
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangA2"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qBorangA" size ="2" value="$Borang_A" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'></td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangA2"> x <input type="text" style="text-align:right;" name="qBorangA" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'></td>
	 	#end
	 	#if($editable=="yes") 
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeBorangA" name="jumlahfeeBorangA">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraBorangA" onClick="KiraBorangA()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeBorangA">&nbsp;&nbsp;<input type="button" name="cmdKiraBorangA" onClick="KiraBorangA()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	<tr>
	 	<td>&nbsp;Borang P</td>
	 	#set ($jumlahfeeBorangP = $Borang_P * 10)
	 	#set ($jumlahfeeBorangP = $jumlahfeeBorangP + ($qBorang1P * 30) )
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" name="feeBorangP1" value="30" disabled> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="q1BorangP" size ="2" value="$qBorang1P" onkeypress='return event.charCode >= 48 && event.charCode <= 49 || event.charCode == 0' ></td>
	 	#if($editable=="yes") 
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangP2"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qBorangP" size ="2" value="$Borang_P" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0' ></td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangP2"> x <input type="text" style="text-align:right;" name="qBorangP" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0' ></td>
	 	#end
	 	#if($editable=="yes") 
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeBorangP" name="jumlahfeeBorangP">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraBorangP" onClick="KiraBorangP()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeBorangP">&nbsp;&nbsp;<input type="button"  name="cmdKiraBorangP" onClick="KiraBorangP()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	<tr>
	 	<td>&nbsp;Borang DDA</td>
	 	#set ($jumlahfeeBorangDDA = $Borang_DDA * 10)
	 	#set ($jumlahfeeBorangDDA = $jumlahfeeBorangDDA + ($qBorang1DDA * 30) )
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" name="feeBorangDDA1" value="30" disabled> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="q1BorangDDA" size ="2" value="$qBorang1DDA" onkeypress='return event.charCode >= 48 && event.charCode <= 49 || event.charCode == 0'> </td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangDDA2"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qBorangDDA" size ="2" value="$Borang_DDA" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangDDA2"> x <input type="text" style="text-align:right;" name="qBorangDDA" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
		<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeBorangDDA" name="jumlahfeeBorangDDA">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraBorangDDA" onClick="KiraBorangDDA()" value="Kira"> </td>
		#else
		<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeBorangDDA">&nbsp;&nbsp;<input type="button"  name="cmdKiraBorangDDA" onClick="KiraBorangDDA()" value="Kira"> </td>
		#end
	 	</tr>
	 	
	 	<tr>
	 	<td>&nbsp;Surat Akuan Sumpah</td>
	 	#set ($jumlahfeeBorangSA = $Borang_SA * 10)
	 	#set ($jumlahfeeBorangSA = $jumlahfeeBorangSA  + ($qBorang1SA * 30) )
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" name="feeBorangSA1" value="30" disabled> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="q1BorangSA" size ="2" value="$qBorang1SA" onkeypress='return event.charCode >= 48 && event.charCode <= 49 || event.charCode == 0'></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangSA2"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qBorangSA" size ="2" value="$Borang_SA" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'></td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeBorangSA2"> x <input type="text" style="text-align:right;" name="qBorangSA" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'></td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeBorangSA" name="jumlahfeeBorangSA">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraBorangSA" onClick="KiraBorangSA()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeBorangSA">&nbsp;&nbsp;<input type="button"  name="cmdKiraBorangSA" onClick="KiraBorangSA()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	
	 	#if ($Lampiran1 != "" || $Lampiran1 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran1" name="textLain1" size="50"></td>
	 	#set ($jumlahfeeLampiran1 = $F2Lampiran1 * $QLampiran1)
	 	#set ($jumlahfeeLampiran1 = $FLampiran1 + $jumlahfeeLampiran1)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain1" value="$FLampiran1"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran1" name="feeLaina1"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain1" size ="2" value="$QLampiran1" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina1"> x <input type="text" style="text-align:right;" name="qfeeLain1" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran1" name="jumlahfeeLain1">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran1" onClick="KiraLain1()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain1">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran1" onClick="KiraLain1()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran2 != "" || $Lampiran2 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran2" name="textLain2" size="50"></td>
	 	#set ($jumlahfeeLampiran2 = $F2Lampiran2 * $QLampiran2)
	 	#set ($jumlahfeeLampiran2 = $FLampiran2 + $jumlahfeeLampiran2)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain2" value="$FLampiran2"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran2" name="feeLaina2"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain2" size ="2" value="$QLampiran2" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina2"> x <input type="text" style="text-align:right;" name="qfeeLain2" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran2" name="jumlahfeeLain2">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran2" onClick="KiraLain2()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain2">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran2" onClick="KiraLain2()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran3 != "" || $Lampiran3 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran3" name="textLain3" size="50"></td>
	 	#set ($jumlahfeeLampiran3 = $F2Lampiran3 * $QLampiran3)
	 	#set ($jumlahfeeLampiran3 = $FLampiran3 + $jumlahfeeLampiran3)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain3" value="$FLampiran3"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran3" name="feeLaina3"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain3" size ="2" value="$QLampiran3" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina3"> x <input type="text" style="text-align:right;" name="qfeeLain3" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran3" name="jumlahfeeLain3">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran3" onClick="KiraLain3()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain3">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran3" onClick="KiraLain3()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran4 != "" || $Lampiran4 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran4" name="textLain4" size="50"></td>
	 	#set ($jumlahfeeLampiran4 = $F2Lampiran4 * $QLampiran4)
	 	#set ($jumlahfeeLampiran4 = $FLampiran4 + $jumlahfeeLampiran4)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain4" value="$FLampiran4"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran4" name="feeLaina4"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain4" size ="2" value="$QLampiran4" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina4"> x <input type="text" style="text-align:right;" name="qfeeLain4" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran4" name="jumlahfeeLain4">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran4" onClick="KiraLain4()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain4">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran4" onClick="KiraLain4()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran5 != "" || $Lampiran5 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran5" name="textLain5" size="50"></td>
	 	#set ($jumlahfeeLampiran5 = $F2Lampiran5 * $QLampiran5)
	 	#set ($jumlahfeeLampiran5 = $FLampiran5 + $jumlahfeeLampiran5)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain5" value="$FLampiran5"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran5" name="feeLaina5"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain5" size ="2" value="$QLampiran5" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina5"> x <input type="text" style="text-align:right;" name="qfeeLain5" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran5" name="jumlahfeeLain5">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran5" onClick="KiraLain5()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain5">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran5" onClick="KiraLain5()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran6 != "" || $Lampiran6 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran6" name="textLain6" size="50"></td>
	 	#set ($jumlahfeeLampiran6 = $F2Lampiran6 * $QLampiran6)
	 	#set ($jumlahfeeLampiran6 = $FLampiran6 + $jumlahfeeLampiran6)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain6" value="$FLampiran6"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran6" name="feeLaina6"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain6" size ="2" value="$QLampiran6" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina6"> x <input type="text" style="text-align:right;" name="qfeeLain6" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran6" name="jumlahfeeLain6">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran6" onClick="KiraLain6()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain6">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran6" onClick="KiraLain6()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran7 != "" || $Lampiran7 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran7" name="textLain7" size="50"></td>
	 	#set ($jumlahfeeLampiran7 = $F2Lampiran7 * $QLampiran7)
	 	#set ($jumlahfeeLampiran7 = $FLampiran7 + $jumlahfeeLampiran7)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain7" value="$FLampiran7"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran7" name="feeLaina7"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain7" size ="2" value="$QLampiran7" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina7"> x <input type="text" style="text-align:right;" name="qfeeLain7" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran7" name="jumlahfeeLain7">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran7" onClick="KiraLain7()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain7">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran7" onClick="KiraLain7()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	
	 	#if ($Lampiran8 != "" || $Lampiran8 !='')
	 	<tr>
	 	<td>&nbsp;Lain-lain : <input type="text" $modeRR2 $modeRRx2 value="$Lampiran8" name="textLain8" size="50"></td>
	 	#set ($jumlahfeeLampiran8 = $F2Lampiran8 * $QLampiran8)
	 	#set ($jumlahfeeLampiran8 = $FLampiran8 + $jumlahfeeLampiran8)	 	
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" name="feeLain8" value="$FLampiran8"></td>
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" $modeRR2 $modeRRx2 style="text-align:right;" size ="2" value="$F2Lampiran8" name="feeLaina8"> x <input type="text" style="text-align:right;" $modeRR2 $modeRRx2 name="qfeeLain8" size ="2" value="$QLampiran8" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" size ="2" value="10" disabled name="feeLaina8"> x <input type="text" style="text-align:right;" name="qfeeLain8" size ="2" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 0'> </td>
	 	#end
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="$jumlahfeeLampiran8" name="jumlahfeeLain8">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKiraLampiran8" onClick="KiraLain8()" value="Kira"> </td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" disabled size ="2" value="" name="jumlahfeeLain8">&nbsp;&nbsp;<input type="button"  name="cmdKiraLampiran8" onClick="KiraLain8()" value="Kira"> </td>
	 	#end
	 	</tr>
	 	#end
	 	<tr>
	 	<td></td>
	 	<td align="center"></td>
	 	<td align="center">Jumlah</td>
	 	
	 	#if($editable=="yes")
	 	<td align="center">RM &nbsp;<input type="hidden" name="BRR" value="$bayaranNB"><input type="text" style="text-align:right;" name="jumlahAllfee" disabled size ="3" value="$JUMLAH_BAYARAN">&nbsp;&nbsp;<input type="button" $modeRR2 $modeRRx2 name="cmdKirajumlahAllfee" onClick="KirajumlahAllfee()" value="Kira"></td>
	 	#else
	 	<td align="center">RM &nbsp;<input type="text" style="text-align:right;" name="jumlahAllfee" disabled size ="3" value="">&nbsp;&nbsp;<input type="button"  name="cmdKirajumlahAllfee" onClick="KirajumlahAllfee()" value="Kira"></td>
	 	#end
	 	
	 	</tr>
	 	<tr>
	 	<td colspan="4" align="center"><input type="button" $modeRR2 $modeRRx2 name="addRow" id="addmorePOIbutton" name="TambahRow" value="Tambah" onclick="insertRow()"/></td>
	 	
	 	</tr>
        
	 	<!-- <tr>
	 		<td>&nbsp;</td>
            <td colspan="2"><input type="button" value="Papar" onclick="displayHTML(this.form.txtNotaBicara)">&nbsp;#if($editform=="yes")Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="2000">#end</td>
        </tr> -->
	 	
	 	
	 	
     </table>

<br/>


	 #if($editform=="yes")
	 <table width="93%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian3</td>
        	</tr>
     </table>
	 #end
	 
</fieldset>

	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
   		<tr align="center">
   			<td align="right" width="55%">
   			#if($editable=="yes")
   				#if($newform=="yes")
   					<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanRekodRayuan('simpanRekodRayuan','$listOB_size')" />
   				#else
   					#if($editform=="no")
   					<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
   						#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
	   						#if($id_status!="169")
	   						<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniRekodRayuan()" />		
	   						#end
	   					#else
	   					<input type="button" name="kembali" value="Kembali" onClick="history.back();" />	
	   					#end
   					
   					#else
					<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanRekodRayuan('updateRekodRayuan','$listOB_size')" />		
					<input type="button" name="cmdBatal" value="Batal" onClick="batalRekodRayuan()" />
					#end
   				#end
   			#else
   			&nbsp;
   			#end
			</td>
			<td align="right" width="45%">$!pagingMaklumatRekodRayuan</td>
   		</tr>
   	</table>
	
	</div> 
<!-- Tab 3 END [Maklumat rekod rayuan] --> 



<!-- Tab 4 START [Maklumat Serahan K3] -->   
    <div class="TabbedPanelsContent">
    
#if($editable=="no")
	#set($mode3="readonly")
	#set($Cmode3="class=disabled")
	#set($mode3x="disabled")
#elseif($editable=="yes")
	#set($mode3="")
	#set($Cmode3="")
	#set($mode3x="")
#end    


#if($viewformK3=="no")

#if($modeBayaran=="change")
  #set($nilaiBayaran="$bayaranR")	
  <input type="text" name="n4" value="$nilaiBayaran">
#else
  #set($nilaiBayaran="$bayaranNB")
  <input type="hidden" name="n4" value="$nilaiBayaran">
#end

			#if($nilaiBayaran=="" || $nilaiBayaran==0 || $nilaiBayaran==0.0)
               #set($n3="")
            #else     	
               #set($n3=$nilaiBayaran)
            #end
       
<fieldset>
<legend>MAKLUMAT SERAHAN BORANG K2</legend>
<input type="hidden" value=""  name="txtBayaranRekodhidden" id="txtBayaranRekodhidden"  maxlength="10" size="8">
    <table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	<tr><td colspan="3">&nbsp;</td></tr>
    	<tr>
    		<td width="1%" valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td width="30%">Bayaran Penyediaan Rekod Rayuan</td>
    		<td width="69%">:&nbsp;RM&nbsp;<input $Cmode3 $mode3 type="text" value="$bayaranNB" style="text-align:right" name="txtBayaranRekod" id="txtBayaranRekod"  maxlength="10" size="8" onkeyup="javascript:validateIC(event,this,this.value,'txtBayaranRekod')" onblur="validateModal(this,this.value,'$n3')" disabled>&nbsp;<input type="button" $Cmode3 $mode3 name="KiraJumlahBayaran" value="Kira" onClick="KiraJumlahBayaran2()"></td><!-- &nbsp;($!totalWord patah perkataan) -->
    	</tr>
    	<tr>
    		<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Tarikh Borang K2</td>
    		<td>:&nbsp;<input $Cmode3 $mode3 type="text" name="txdTarikhSerahan" id="txdTarikhSerahan" value="$!tarikh_serah" maxlength="10" size="11"  onblur="validateTarikh(this,this.value);check_date(this)">
    		#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahan',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
    	</tr>
    </table>	
</fieldset> 

<fieldset>
	<table width="95%"  cellpadding="1" cellspacing="1" border="0">
		<tr><td colspan="4">&nbsp;</td></tr>
		<tr>
			<td width="1%" valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td width="30%">Nama Penghantar</td>
			<td width="1%">:</td>
			<td width="68%"><select $mode3x name="txtNamaPenghantar" style="width:400">
			
        							#if($onchangeNegeri=="no")
        								<option value="">SILA PILIH&nbsp;</option>
        								#foreach($pn in $penghantarNotis)
                    					<option value="$pn.id_penghantarnotis">$pn.kod_penghantar_notis - $pn.nama.toUpperCase()</option>
	                    				#end  
	                    			#else
	                    				<option value="$idPenghantar">$kodPenghantar - $namaPenghantar.toUpperCase()</option>
	                    				<option value="">SILA PILIH&nbsp;</option>	
	                    				#foreach($pnoc in $penghantarNotisONCHANGE)
	                    				<option value="$pnoc.id_penghantarnotis">$pnoc.kod_penghantar_notis - $pnoc.nama.toUpperCase()</option>
	                    				#end
	                    			#end
	                    			
	                    	</select>
        	</td>
		</tr>
		<tr>
        	<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
        	<td>Tarikh Serahan</td>
        	<td>:&nbsp;</td>
        	<td><input type="text" name="tarikhS" $mode3 $Cmode3 id="tarikhS" value="$tarikhS" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  />
        	#if($editable=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('tarikhS',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end</td>
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
        	<td>Jenis Serahan</td>
        	<td>:&nbsp;</td>
        	<td><input type="radio" name="sorJenisSerah" id="sorJenisSerah1" $mode3x $jSerahCheck1 value="1" onClick="disablePOS();enableT()" >Serahan Tangan</td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="radio" $checkStatus1 $enableRadio1 name="sorStatus" id="serah1"  value="1" onClick="hideAsterik()" >Bukti Penyampaian</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="radio" $checkStatus2 $enableRadio1 name="sorStatus" id="serah2"  value="2"  onClick="showAsterik()">Surat Akuan Bersumpah(jika ada)</td> 
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td><input type="radio" name="sorJenisSerah" $mode3x $jSerahCheck2 value="2" onClick="disableSERAH();hideAsterik()">Pos Berdaftar</td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="radio" $checkStatus3 $enableRadio2 name="sorStatus" id="pos1" value="3" onClick="enableText()">Diterima &nbsp; &nbsp; No.Surat Daftar&nbsp;:&nbsp;<input type="text" name="txtNoDaftarPos" style="text-transform:uppercase;" $enableNoPos onBlur="this.value=this.value.toUpperCase();" id="txtNoDaftarPos" value="$!daftarPos" maxlength="10" size="11"></td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="radio" $checkStatus4 $enableRadio2 name="sorStatus" id="pos2" value="4" onClick="disabledText()">Dikembalikan</td> 
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td valign="top" id="nonAsterik">&nbsp;</td>
        	<td valign="top" style="display:none" id="withAsterik"><font color="red">*</font></td>
        	<td valign="top">Catatan</td>
        	<td valign="top">:</td>
        	<td><textarea $mode3 $Cmode3 name="txtCatatan" cols="60%" rows="7" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLenk3,2000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLenk3,2000);" >$!catatan</textarea></td>
        </tr>
        #if($editable=="yes")
        <tr>
        	<td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLenk3" size="3" maxlength="3" value="2000"></td>
           	</tr>  
        <tr>
        #end
        <td colspan="4">&nbsp;</td></tr>
      </table>	
</fieldset>

<br/>

<fieldset> 
<legend>MAKLUMAT PENERIMA</legend>
    <table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	
    	<tr>
    		<td width="1%" valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td width="30%">Nama Penerima</td>
    		<td width="69%">:&nbsp;<input type="text" $Cmode3 $mode3 name="txtNamaPenerima" id="txtNamaPenerima" value="$namaPER" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Alamat</td>
    		<td>:&nbsp;<input type="text" $Cmode3 $mode3 name="alamatSerah1" id="alamatSerah1" value="$alamatPER1" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="white">:</font>&nbsp;<input $Cmode3 $mode3 type="text" name="alamatSerah2" id="alamatSerah2" value="$alamatPER2" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="white">:</font>&nbsp;<input $Cmode3 $mode3 type="text" name="alamatSerah3" id="alamatSerah3" value="$alamatPER3" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Poskod</td>
    		<td>:&nbsp;<input type="text" $Cmode3 $mode3 name="txtPoskodSerah" id="txtPoskodSerah" value="$poskodPER" maxlength="5" size="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    	</tr>
    	<!-- <tr>
    		<td>#if($editable=="yes")<font color="red">#end Bandar #if($editable=="yes")</font>#end</td>
    		<td>:&nbsp;<input type="text" $Cmode3 $mode3 name="txtBandarSerah" id="txtBandarSerah" value="$bandarPER" maxlength="50" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr> -->
    	<tr>
    		<td valign="top">#if($editable=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Negeri</td>
    		<td>:&nbsp;$selectNegeri</td>
    	</tr>
    	<tr>
    		<td valign="top">&nbsp;</td>
    		<td>Bandar</td>
    		<td>:&nbsp;$selectBandar</td>
    	</tr>
    	<tr><td colspan="2">&nbsp;</td></tr>
    </table>
    #if($editable=="yes")
    <table width="93%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian3</td>
        	</tr>
    </table>
    #end
</fieldset> 

#end    

#if($viewformK3=="yes")

#if($editformK3=="no")
	#set($mode4 = "readonly")
	#set($Cmode4 = "class=disabled")
	#set($mode4x = "disabled")
#elseif($editformK3=="yes")
	#set($mode4 = "")
	#set($Cmode4 = "")
	#set($mode4x = "")
#end

#if($onchangebandar=="no")
	#foreach($a in $maklumatSerahan)
		#set($tarikhSerah = $a.tarikh_serahan)
		#set($namaPenerima = $a.nama_penerima)
		#set($alamat1Serah = $a.alamat1)
		#set($alamat2Serah = $a.alamat2)
		#set($alamat3Serah = $a.alamat3)
		#set($poskodSerah = $a.poskod)
		#set($bandarSerah = $a.bandar)
	#end
	
	#foreach($b in $bayaranKR)
		#set($bayaranR = $b.jumlah_bayaran)
	#end

	<!-- 02122009 -->
	#if($dataMstRayuan_size!=0)	
		#foreach($data in $dataMstRayuan)
	
			#set($kodPenghantarNotis=$data.kod_penghantar_notis)
			#set($idPenghantarNotis=$data.id_penghantarnotis)
			#set($namaHantar=$data.nama_penghantar_notis)
			#set($catatan=$data.catatan)
			#set($noSurat=$data.no_surat_daftar)
			
			<!-- 11122009 -->
			#set($tarikhS = $data.tarikhS)
			
			#if($editformK3=="yes")
				#if($data.status_serah=="1")
					#set($disabledStatus1 = "")
					#set($disabledStatus2 = "")
					#set($disabledStatus3 = "disabled")
					#set($disabledStatus4 = "disabled")
					#set($disabledTxtSurat = "disabled")
					
					#set($showAkaunSumpah="no")
					
					<input type="hidden" name="Asterik" id="Asterik" value="no">	
				#elseif($data.status_serah=="2")
					#set($disabledStatus1 = "")
					#set($disabledStatus2 = "")
					#set($disabledStatus3 = "disabled")
					#set($disabledStatus4 = "disabled")
					#set($disabledTxtSurat = "disabled")
					
					#set($showAkaunSumpah="yes")
						
					<input type="hidden" name="Asterik" id="Asterik" value="yes">	
				#elseif($data.status_serah=="3")
					#set($disabledStatus1 = "disabled")
					#set($disabledStatus2 = "disabled")
					#set($disabledStatus3 = "")
					#set($disabledStatus4 = "")
					#set($disabledTxtSurat = "")
					
					#set($showAkaunSumpah="no")
					
					<input type="hidden" name="Asterik" id="Asterik" value="no">	
				#elseif($data.status_serah=="4")	
					#set($disabledStatus1 = "disabled")
					#set($disabledStatus2 = "disabled")
					#set($disabledStatus3 = "")
					#set($disabledStatus4 = "")
					#set($disabledTxtSurat = "disabled")
					
					#set($showAkaunSumpah="no")
					
					<input type="hidden" name="Asterik" id="Asterik" value="no">	
				#else
				
					#set($showAkaunSumpah="no")
					
					<input type="hidden" name="Asterik" id="Asterik" value="no">	
					
				#end
			
			#else
			
				#if($data.status_serah=="2")
					#set($showAkaunSumpah="yes")
				#else
					#set($showAkaunSumpah="no")	
				#end
					
				#set($disabledStatus1 = "disabled")
				#set($disabledStatus2 = "disabled")
				#set($disabledStatus3 = "disabled")
				#set($disabledStatus4 = "disabled")
				#set($disabledTxtSurat = "disabled")
			#end
		#end
	
	#else

			#set($kodPenghantarNotis="")
			#set($idPenghantarNotis="")
			#set($namaHantar="")
			#set($catatan="")
			#set($noSurat="")
		
			<!-- 11122009 -->
			#set($tarikhS = "")
		
			#set($disabledStatus1 = "disabled")
			#set($disabledStatus2 = "disabled")
			#set($disabledStatus3 = "disabled")
			#set($disabledStatus4 = "disabled")
			#set($disabledTxtSurat = "disabled")
	#end	
	<!-- 02122009 -->
	
	
<!-- onchangebandar==yes -->	
#else
		
		
		<!-- 02122009 -->
		#if($onchangeMyListDropdown=="yes")	
   			#set($mode="")
			#set($namaHantar=$SnamaPntr) 	
			#set($kodPenghantarNotis=$SkodPntr)
			#set($idPenghantarNotis=$SidPntr)
  		#else
  			#set($mode="kosong")
  		#end	
  
  
  		<!-- 11122009 -->
		#set($tarikhS = "$ONtarikhS")
			
			
		#set($catatan="$ONcatatan")
		#set($noSurat="$ONnoDaftar")
		
		#if($ONstatus_serah=="1")
			#set($disabledStatus1 = "")
			#set($disabledStatus2 = "")
			#set($disabledStatus3 = "disabled")
			#set($disabledStatus4 = "disabled")
			#set($disabledTxtSurat = "disabled")
			
			#set($showAkaunSumpah="no")
					
			<input type="hidden" name="Asterik" id="Asterik" value="no">
		#elseif($ONstatus_serah=="2")
			#set($disabledStatus1 = "")
			#set($disabledStatus2 = "")
			#set($disabledStatus3 = "disabled")
			#set($disabledStatus4 = "disabled")
			#set($disabledTxtSurat = "disabled")	
			
			#set($showAkaunSumpah="yes")
					
			<input type="hidden" name="Asterik" id="Asterik" value="yes">
		#elseif($ONstatus_serah=="3")
			#set($disabledStatus1 = "disabled")
			#set($disabledStatus2 = "disabled")
			#set($disabledStatus3 = "")
			#set($disabledStatus4 = "")
			#set($disabledTxtSurat = "")
			
			#set($showAkaunSumpah="no")
					
			<input type="hidden" name="Asterik" id="Asterik" value="no">
		#elseif($ONstatus_serah=="4")
			#set($disabledStatus1 = "disabled")
			#set($disabledStatus2 = "disabled")
			#set($disabledStatus3 = "")
			#set($disabledStatus4 = "")
			#set($disabledTxtSurat = "disabled")
			
			#set($showAkaunSumpah="no")
					
			<input type="hidden" name="Asterik" id="Asterik" value="no">
		#else
			#set($showAkaunSumpah="no")
					
			<input type="hidden" name="Asterik" id="Asterik" value="no">
		#end
		<!-- 02122009 -->
		
		
		#set($tarikhSerah = $tarikh_serah)
		#set($namaPenerima = $namaPER)
		#set($alamat1Serah = $alamatPER1)
		#set($alamat2Serah = $alamatPER2)
		#set($alamat3Serah = $alamatPER3)
		#set($poskodSerah = $poskodPER)

		#set($bayaranR = $bayaranRx)
#end

<input type="hidden" name="id_bayaran" value="$!id_bayaran"> 

<fieldset>
<legend>MAKLUMAT SERAHAN BORANG K2</legend>
<input type="hidden" value=""  name="txtBayaranRekodhidden" id="txtBayaranRekodhidden"  maxlength="10" size="8">
<table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	<tr><td colspan="3">&nbsp;</td></tr>
    	<tr>
    		<td width="1%" valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td width="30%">Bayaran Penyediaan Rekod Rayuan </td>
    		#if($editformK3=="no")
    		<td width="69%">:&nbsp;RM&nbsp;<input type="hidden" name="n4" value="$BRR"><input type="text" disabled style="text-align:right" name="txtBayaranRekod" id="txtBayaranRekod" value="$jumlah_bayaran_semua" maxlength="10" size="8" onkeyup="javascript:validateIC(event,this,this.value,'txtBayaranRekod')" onblur="validateModal(this,this.value,'$bayaranR')">&nbsp;<!-- <input type="button" $Cmode3 $mode3 name="KiraJumlahBayaran" value="Update" onClick="KiraJumlahBayaran2()">  --></td><!-- &nbsp;($!totalWord patah perkataan) -->
    		#else
    		<td width="69%">:&nbsp;RM&nbsp;<input type="hidden" name="n4" value="$n4"><input $Cmode4 $mode4 type="text" style="text-align:right" name="txtBayaranRekod" id="txtBayaranRekod" value="$!Util.formatDecimal($bayaranR)" maxlength="10" size="8" onkeyup="javascript:validateIC(event,this,this.value,'txtBayaranRekod')" onblur="validateModal(this,this.value,'$bayaranR')" >&nbsp;<input type="button" $Cmode3 $mode3 name="KiraJumlahBayaran" value="Update" onClick="KiraJumlahBayaran2()"></td><!-- &nbsp;($!totalWord patah perkataan) -->
    		<!--  <td width="69%">:&nbsp;RM&nbsp;<input $Cmode4 $mode4 type="text" style="text-align:right" name="txtBayaranRekod" id="txtBayaranRekod2" value="$!bayaranNB" maxlength="10" size="8" onkeyup="javascript:validateIC(event,this,this.value,'txtBayaranRekod')" onblur="validateModal(this,this.value,'$bayaranR')"></td>-->
    		#end
    	</tr>
    	<tr>
    		<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Tarikh Borang K2</td>
    		<td>:&nbsp;<input $Cmode4 $mode4 type="text" name="txdTarikhSerahan" id="txdTarikhSerahan" value="$tarikhSerah" maxlength="10" size="11"  onblur="validateTarikh(this,this.value);check_date(this)">
    		#if($editformK3=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahan',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
    	</tr>
    </table>	
</fieldset>  

<fieldset>
	<table width="95%"  cellpadding="1" cellspacing="1" border="0">
		<tr><td colspan="4">&nbsp;</td></tr>
		<tr>
			<td width="1%" valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td width="30%">Nama Penghantar</td>
			<td width="1%">:</td>
			<td width="68%"><select $mode4x name="txtNamaPenghantar" style="width:400">
			
        							#if($onchangebandar=="no")	
        								#if($dataMstRayuan_size!=0)
	                    					<option value="$idPenghantarNotis">$kodPenghantarNotis - $namaHantar.toUpperCase()</option>
	                    					<option value="">SILA PILIH&nbsp;</option>	
	                    					#foreach($dpn in $penghantarNotis)
	                    						<option value="$dpn.id_penghantarnotis">$dpn.kod_penghantar_notis - $dpn.nama.toUpperCase()</option>
	                    					#end
	                    				#else
	                    					<option value="">SILA PILIH&nbsp;</option>	
	                    					#foreach($dpn in $penghantarNotis)
	                    						<option value="$dpn.id_penghantarnotis">$dpn.kod_penghantar_notis - $dpn.nama.toUpperCase()</option>
	                    					#end
	                    				#end
	                    				
	                   				#else
	                   								
	                   					#if($mode=="kosong")
	                   							<option value="">SILA PILIH&nbsp;</option>	
	                    						#foreach($pn in $penghantarNotis)
	                    						<option value="$pn.id_penghantarnotis">$pn.kod_penghantar_notis - $pn.nama.toUpperCase()</option>
	                    						#end
	                   						#else
	                   							<option value="$idPenghantarNotis">$kodPenghantarNotis - $namaHantar.toUpperCase()</option>
	                    						<option value="">SILA PILIH&nbsp;</option>	
	                    						#foreach($pno in $penghantarNotisONCHANGE)
	                    						<option value="$pno.id_penghantarnotis">$pno.kod_penghantar_notis - $pno.nama.toUpperCase()</option>
	                    						#end
	                   						#end
	                   								
	                   				 #end
	                    			
	                    	</select>
        	</td>
		</tr>
		<tr>
        	<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
        	<td>Tarikh Serahan</td>
        	<td>:&nbsp;</td>
        	<td><input type="text" name="tarikhS" id="tarikhS" $Cmode4 $mode4 value="$tarikhS" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  />
        	#if($editformK3=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('tarikhS',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end</td>
        </tr>
		<tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
        	<td>Jenis Serahan</td>
        	<td>:&nbsp;</td>
        	<td><input $mode4x type="radio" $jenisSerah1 id="sorJenisSerah1" name="sorJenisSerah" value="1" onClick="disablePOS();enableT()" >Serahan Tangan</td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input $disabledStatus1 $serah1 type="radio" name="sorStatus" id="serah1"  value="1" onClick="hideAsterik()">Bukti Penyampaian</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input $disabledStatus2 $serah2 type="radio" name="sorStatus" id="serah2"  value="2"  onClick="showAsterik()">Surat Akuan Bersumpah(jika ada)</td> 
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td><input $mode4x type="radio" $jenisSerah2 name="sorJenisSerah" value="2" onClick="disableSERAH();hideAsterik()">Pos Berdaftar</td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input $disabledStatus3 $pos1 type="radio" name="sorStatus" id="pos1" value="3" onClick="enableText()">Diterima &nbsp; &nbsp; No.Surat Daftar&nbsp;:&nbsp;<input $disabledTxtSurat type="text" name="txtNoDaftarPos" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="txtNoDaftarPos" value="$!noSurat" maxlength="10" size="11"></td> 
        </tr>
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>&nbsp;&nbsp;&nbsp; &nbsp;<input $disabledStatus4 $pos2 type="radio" name="sorStatus" id="pos2" value="4" onClick="disabledText()">Dikembalikan</td> 
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
        <tr>
        	<td valign="top" id="nonAsterik">&nbsp;</td>
        	<td valign="top" style="display:none" id="withAsterik"><font color="red">*</font></td>
        	<td valign="top">Catatan</td>
        	<td valign="top">:</td>
        	<td><textarea name="txtCatatan" cols="60%" $Cmode4 $mode4 rows="7" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLenk3e,2000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLenk3e,2000);" >$!catatan</textarea></td>
        </tr>
        #if($editformK3=="yes")
        <tr>
        	<td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLenk3e" size="3" maxlength="3" value="2000"></td>
           	</tr>  
        <tr>
        #end
        <td colspan="4">&nbsp;</td></tr>
      </table>	
</fieldset>

<br/>

<fieldset>
<legend>MAKLUMAT PENERIMA</legend>
    <table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	<tr>
    		<td width="1%" valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td width="30%">Nama Penerima</td>
    		<td width="69%">:&nbsp;<input type="text" $Cmode4 $mode4 name="txtNamaPenerima" id="txtNamaPenerima" value="$namaPenerima" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Alamat</td>
    		<td>:&nbsp;<input type="text" $Cmode4 $mode4 name="alamatSerah1" id="alamatSerah1" value="$alamat1Serah" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="white">:</font>&nbsp;<input $Cmode4 $mode4 type="text" name="alamatSerah2" id="alamatSerah2" value="$alamat2Serah" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td><font color="white">:</font>&nbsp;<input $Cmode4 $mode4 type="text" name="alamatSerah3" id="alamatSerah3" value="$alamat3Serah" maxlength="80" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr>
    	<tr>
    		<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Poskod</td>
    		<td>:&nbsp;<input type="text" $Cmode4 $mode4 name="txtPoskodSerah" id="txtPoskodSerah" value="$poskodSerah" maxlength="5" size="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    	</tr>
    	<!-- <tr>
    		<td>#if($editformK3=="yes")<font color="red">#end Bandar #if($editformK3=="yes")</font>#end </td>
    		<td>:&nbsp;<input type="text" $Cmode4 $mode4 name="txtBandarSerah" id="txtBandarSerah" value="$bandarSerah" maxlength="50" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    	</tr> -->
    	<tr>
    		<td valign="top">#if($editformK3=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    		<td>Negeri</td>
    		<td>:&nbsp;$!selectNegeri</td>
    	</tr>
    	<tr>
    		<td valign="top">&nbsp;</td>
    		<td>Bandar</td>
    		<td>:&nbsp;$!selectBandar</td>
    	</tr>
    	<tr><td colspan="2">&nbsp;</td></tr>
    </table>
    
    #if($editformK3=="yes")
    <table width="93%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian3</td>
        	</tr>
    </table>
    #end
    
</fieldset>    
#end    

	#if($editable=="yes")
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
   		<tr align="center">
   			<td width="52%" align="right">
   			#if($viewformK3=="no")
   			<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanSerahan()" />
   			#end
   			
   			#if($viewformK3=="yes")
   				#if($editformK3=="no")
   					<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
   					#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
	   					#if($id_status!="169")
	   					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniSerahan()" />
						#end
					
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
					<!-- <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$id_permohonan','$id_status')" /> -->
					
					#else
						<input type="button" name="kembali" value="Kembali" onClick="history.back();" />
					#end
					
				#end
				#if($editformK3=="yes")
			<input type="button" name="cmdSimpan" value="Simpan" onClick="updateSerahan()" />
			<input type="button" name="cmdBatal" value="Batal" onClick="batalUpdateSerahan('$id_permohonan','$id_status','$id_rayuan')" />
				#end
				
			#end
			
			</td>
			<td width="48%" align="right">$!pagingMaklumatSerahan</td>
   		</tr>
   	</table>
   	#else
   	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
   		<tr>
   			<td align="right">$!pagingMaklumatSerahan</td>
   		</tr>
   	</table>    
   	#end
 
<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	 <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangK3('$noFail','$idnegerirayuan','$id_perbicaraan')"><font color="blue">Borang K2</font></a></td>
      </tr> 
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangK1('$noFail','$idnegerirayuan','$id_perbicaraan')"><font color="blue">Borang K3</font></a></td>
      </tr> 
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBuktiPenyampaian('$id_fail')"><font color="blue">Bukti Penyampaian</font></a></td>
      </tr> 
      #if($showAkaunSumpah=="yes")
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakAkuanBersumpah('$id_fail','$id_perbicaraan')"><font color="blue">Surat Akuan Bersumpah</font></a></td>
      </tr> 
      #end 
      
    </table>      
</fieldset> 

    </div>
<!-- Tab 4 END [Maklumat Serahan K3] -->  
   
   
   
   
<!-- Tab 5 START [Memorandum Rayuan] --> 

<!-- Tab 5 END [Memorandum Rayuan] -->      
    
    
    
  </div>
</div>

<table width="100%" border="0">
<tr><td>&nbsp;</td></tr>
<tr>
	<td >
	<div align="right">
	<span>
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:kembaliList()" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
  	#elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
 	#elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
	#elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:kembali('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow$!no3current$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
    #end
    
    </span> 
    </div>
   	</td>
</tr>
</table>

</center>

<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/> 
<!-- <input type="hidden" name="command" /> -->
<input type="hidden" name="command2" />
<input type="hidden" name="command2a" />
<input type="hidden" name="command3" />
<input type="hidden" name="commandx" />
<input type="hidden" name="commandxx" />
<input type="hidden" name="commandxxx" />
<input type="hidden" name="commandoc" />
<input type="hidden" name="commando" />
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_pemohon" value="$id_pemohon" />
<input type="hidden" name="id_status" value="$id_status" />
<input type="hidden" name="id_peguam" value="$id_peguam" />
<input type="hidden" name="id_peguamX" value="$id_peguamX" />
<input type="hidden" name="id_perayu" value="$id_perayu" />
<input type="hidden" name="id_rayuan" value="$id_rayuan" />
<input type="hidden" name="id_serahanrayuan" value="$id_serahanrayuan" />
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail">
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input type="hidden" name="idnegerirayuan" value="$idnegerirayuan">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input type="hidden" name="txdTarikhRayuan" id="txdTarikhRayuan" value="$!tarikhRayuan">
<input name="flagForView" type="hidden" id="flagForView" value="$flagForView"/>
<input name="jumlahh" type="hidden"/>
<input name="textLainHidden" type="hidden"/>
<input name="textfeeLainHidden" type="hidden"/>
<input name="textLainHidden2" type="hidden"/>
<input name="textfeeLainHidden2" type="hidden"/>
<input name="feeLaina2a" type="hidden"/>
<input name="qfeeLain2a" type="hidden"/>

<input name="textLainHidden3" type="hidden"/>
<input name="textfeeLainHidden3" type="hidden"/>
<input name="feeLaina3a" type="hidden"/>
<input name="qfeeLain3a" type="hidden"/>

<input name="textLainHidden4" type="hidden"/>
<input name="textfeeLainHidden4" type="hidden"/>
<input name="feeLaina4a" type="hidden"/>
<input name="qfeeLain4a" type="hidden"/>

<input name="textLainHidden5" type="hidden"/>
<input name="textfeeLainHidden5" type="hidden"/>
<input name="feeLaina5a" type="hidden"/>
<input name="qfeeLain5a" type="hidden"/>

<input name="textLainHidden6" type="hidden"/>
<input name="textfeeLainHidden6" type="hidden"/>
<input name="feeLaina6a" type="hidden"/>
<input name="qfeeLain6a" type="hidden"/>

<input name="textLainHidden7" type="hidden"/>
<input name="textfeeLainHidden7" type="hidden"/>
<input name="feeLaina7a" type="hidden"/>
<input name="qfeeLain7a" type="hidden"/>

<input name="textLainHidden8" type="hidden"/>
<input name="textfeeLainHidden8" type="hidden"/>
<input name="feeLaina8a" type="hidden"/>
<input name="qfeeLain8a" type="hidden"/>


#parse("app/ppk/headerppk_script.jsp")
<script>
window.onload=function()
{
	var asterik  = document.getElementById("Asterik").value;
	var nonAsterik = document.getElementById("nonAsterik"); 
	var withAsterik = document.getElementById("withAsterik"); 
	
	if(asterik == "yes"){
		nonAsterik.style.display = "none"; 
		withAsterik.style.display = "inline";
	}else{
		nonAsterik.style.display = "inline"; 
		withAsterik.style.display = "none";
	}		 
}

function KiraJumlahBayaran2()
{
	//alert ("KiraJumlahBayaran2");
	var n4 = document.${formName}.n4.value;
	//alert ("n4 = " + n4);
	var jumlahAllfee = document.${formName}.jumlahAllfee.value;
	//alert ("jumlahAllfee = " + jumlahAllfee);
	var kenabayar = parseFloat(jumlahAllfee) + parseFloat(n4);
	//alert ("kenabayar = " + kenabayar);
	document.${formName}.txtBayaranRekod.value = parseFloat(kenabayar).toFixed(2);
}

function disablePOS(){
	document.getElementById("pos1").disabled=true
	document.getElementById("pos2").disabled=true
	document.getElementById("pos1").checked=false
	document.getElementById("pos2").checked=false
	document.${formName}.txtNoDaftarPos.value = "";
	document.${formName}.txtNoDaftarPos.disabled = true;	

	document.getElementById("serah1").disabled=false
	document.getElementById("serah2").disabled=false
}

function KiraBorangA()
{
	var A = document.${formName}.feeBorangA1.value;
	var B = document.${formName}.q1BorangA.value;
	var C = document.${formName}.feeBorangA2.value;
	var D = document.${formName}.qBorangA.value;
	if (parseInt(D) > 0 && parseInt(B) == 0)
		{
		alert ("Sila masukkan bilangan salinan pertama Borang A");
		document.${formName}.jumlahfeeBorangA.value = 0;
		document.${formName}.q1BorangA.focus(); 
		return;
		}
	var jumlahfeeBorangA = (parseInt(A) * parseInt(B))+(parseInt(C)*parseInt(D));
	document.${formName}.jumlahfeeBorangA.value = jumlahfeeBorangA;
}

function KiraBorangP()
{
	var A = document.${formName}.feeBorangP1.value;
	var B = document.${formName}.q1BorangP.value;
	var C = document.${formName}.feeBorangP2.value;
	var D = document.${formName}.qBorangP.value;
	if (parseInt(D) > 0 && parseInt(B) == 0)
	{
	alert ("Sila masukkan bilangan salinan pertama Borang P");
	document.${formName}.jumlahfeeBorangP.value = 0;
	document.${formName}.q1BorangP.focus(); 
	return;
	}
	var jumlahfeeBorangP = (parseInt(A) * parseInt(B))+(parseInt(C)*parseInt(D));
	document.${formName}.jumlahfeeBorangP.value = jumlahfeeBorangP;
}

function KiraBorangDDA()
{
	var A = document.${formName}.feeBorangDDA1.value;
	var B = document.${formName}.q1BorangDDA.value;
	var C = document.${formName}.feeBorangDDA2.value;
	var D = document.${formName}.qBorangDDA.value;
	if (parseInt(D) > 0 && parseInt(B) == 0)
	{
	alert ("Sila masukkan bilangan salinan pertama Borang DDA");
	document.${formName}.jumlahfeeBorangDDA.value = 0;
	document.${formName}.q1BorangDDA.focus(); 
	return;
	}
	var jumlahfeeBorangDDA = (parseInt(A) * parseInt(B))+(parseInt(C)*parseInt(D));
	document.${formName}.jumlahfeeBorangDDA.value = jumlahfeeBorangDDA;
}

function KiraBorangSA()
{
	var A = document.${formName}.feeBorangSA1.value;
	var B = document.${formName}.q1BorangSA.value;
	var C = document.${formName}.feeBorangSA2.value;
	var D = document.${formName}.qBorangSA.value;
	if (parseInt(D) > 0 && parseInt(B) == 0)
	{
	alert ("Sila masukkan bilangan salinan pertama Surat Akuan Sumpah");
	document.${formName}.jumlahfeeBorangSA.value = 0;
	document.${formName}.q1BorangSA.focus(); 
	return;
	}
	var jumlahfeeBorangSA = (parseInt(A) * parseInt(B))+(parseInt(C)*parseInt(D));
	document.${formName}.jumlahfeeBorangSA.value = jumlahfeeBorangSA;
}

function KiraLain1()
{
	var A = document.${formName}.feeLain1.value;
	var B = document.${formName}.feeLaina1.value;
	var C = document.${formName}.qfeeLain1.value;
	var jumlahfeeLain1 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain1.value = jumlahfeeLain1;
}

function KiraLain2()
{
	//alert("index = "+index);
	var A = document.${formName}.feeLain2.value;
	var B = document.${formName}.feeLaina2.value;
	var C = document.${formName}.qfeeLain2.value;
	var jumlahfeeLain2 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain2.value = jumlahfeeLain2;
}

function KiraLain3()
{
	var A = document.${formName}.feeLain3.value;
	var B = document.${formName}.feeLaina3.value;
	var C = document.${formName}.qfeeLain3.value;
	var jumlahfeeLain1 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain3.value = jumlahfeeLain1;
}

function KiraLain4()
{
	//alert("index = "+index);
	var A = document.${formName}.feeLain4.value;
	var B = document.${formName}.feeLaina4.value;
	var C = document.${formName}.qfeeLain4.value;
	var jumlahfeeLain2 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain4.value = jumlahfeeLain2;
}

function KiraLain5()
{
	var A = document.${formName}.feeLain5.value;
	var B = document.${formName}.feeLaina5.value;
	var C = document.${formName}.qfeeLain5.value;
	var jumlahfeeLain1 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain5.value = jumlahfeeLain1;
}

function KiraLain6()
{
	//alert("index = "+index);
	var A = document.${formName}.feeLain6.value;
	var B = document.${formName}.feeLaina6.value;
	var C = document.${formName}.qfeeLain6.value;
	var jumlahfeeLain2 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain6.value = jumlahfeeLain2;
}

function KiraLain7()
{
	var A = document.${formName}.feeLain7.value;
	var B = document.${formName}.feeLaina7.value;
	var C = document.${formName}.qfeeLain7.value;
	var jumlahfeeLain1 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain7.value = jumlahfeeLain1;
}

function KiraLain8()
{
	//alert("index = "+index);
	var A = document.${formName}.feeLain8.value;
	var B = document.${formName}.feeLaina8.value;
	var C = document.${formName}.qfeeLain8.value;
	var jumlahfeeLain2 = parseInt(A)+(parseInt(B)*parseInt(C));
	//alert ("jumlahfeeBorangA = "+ jumlahfeeBorangA);
	document.${formName}.jumlahfeeLain8.value = jumlahfeeLain2;
}

function KirajumlahAllfee()
{
	if (document.${formName}.qBorangA.value > 0 && document.${formName}.q1BorangA.value == 0)
		{
		alert ("Sila masukkan bilangan salinan pertama Borang A");
		document.${formName}.jumlahAllfee.value = 0;
		document.${formName}.q1BorangA.focus(); 
		return;
		}
	
	if (document.${formName}.qBorangP.value > 0 && document.${formName}.q1BorangP.value == 0)
		{
		alert ("Sila masukkan bilangan salinan pertama Borang P");
		document.${formName}.jumlahAllfee.value = 0;
		document.${formName}.q1BorangP.focus(); 
		return;
		}
	
	if (document.${formName}.qBorangDDA.value > 0 && document.${formName}.q1BorangDDA.value == 0)
		{
		alert ("Sila masukkan bilangan salinan pertama Borang DDA");
		document.${formName}.jumlahAllfee.value = 0;
		document.${formName}.q1BorangDDA.focus(); 
		return;
		}
	
	if (document.${formName}.qBorangSA.value > 0 && document.${formName}.q1BorangSA.value == 0)
		{
		alert ("Sila masukkan bilangan salinan pertama Surat Akuan Sumpah");
		document.${formName}.jumlahAllfee.value = 0;
		document.${formName}.q1BorangSA.focus(); 
		return;
		}
		
		var A = document.${formName}.jumlahfeeLain1; 
		if (A === undefined || document.${formName}.jumlahfeeLain1.value =="") {
			A = 0;
			}
		else
			{
				A = document.${formName}.jumlahfeeLain1.value;
			}

		var B = document.${formName}.jumlahfeeLain2;
		if (B === undefined || document.${formName}.jumlahfeeLain2.value =="") {
			B = 0;}
		    else
			{
		    	B = document.${formName}.jumlahfeeLain2.value;	
			}
		
	var C = document.${formName}.jumlahfeeBorangSA.value;
	var D = document.${formName}.jumlahfeeBorangDDA.value;
	var E = document.${formName}.jumlahfeeBorangP.value;
	var F = document.${formName}.jumlahfeeBorangA.value;
	

	var G = document.${formName}.jumlahfeeLain3; 
	if (G === undefined || document.${formName}.jumlahfeeLain3.value =="") {
		G = 0;}
	    else
		{
	    	G = document.${formName}.jumlahfeeLain3.value;	
		}
	
	

	var H = document.${formName}.jumlahfeeLain4;
	if (H === undefined || document.${formName}.jumlahfeeLain4.value =="") {
		H = 0;}
	    else
		{
	    	H = document.${formName}.jumlahfeeLain4.value;	
		}
	
	
	var I = document.${formName}.jumlahfeeLain5; 
	if (I === undefined || document.${formName}.jumlahfeeLain5.value =="") {
		I = 0;}
	    else
		{
	    	I = document.${formName}.jumlahfeeLain5.value;	
		}
	
	
	var J = document.${formName}.jumlahfeeLain6;
	if (J === undefined || document.${formName}.jumlahfeeLain6.value =="") {
		J = 0;}
	    else
		{
	    	J = document.${formName}.jumlahfeeLain6.value;	
		}
	
	
	var K = document.${formName}.jumlahfeeLain7; 
	if (K === undefined || document.${formName}.jumlahfeeLain7.value =="") {
		K = 0;}
	    else
		{
	    	K = document.${formName}.jumlahfeeLain7.value;	
		}
	
	
	var L = document.${formName}.jumlahfeeLain8;
	if (L === undefined || document.${formName}.jumlahfeeLain8.value =="") {
		L = 0;}
	    else
		{
	    	L = document.${formName}.jumlahfeeLain8.value;	
		}
	
	
	
	var jumlahAllfee = parseInt(A) + parseInt(B) + parseInt(C) + parseInt(D) + parseInt(E) + parseInt(F);
		jumlahAllfee = jumlahAllfee + parseInt(G) + parseInt(H) + parseInt(I) + parseInt(J) + parseInt(K) + parseInt(L);
	var	jumlahAllfee2 = String(jumlahAllfee);
	document.${formName}.jumlahAllfee.value = jumlahAllfee2;
	
	
}

var index;
if (document.${formName}.feeLain1.value !="")
	{
	index = 2;
	}
if (document.${formName}.feeLain2.value !="")
{
index = 3;
}
if (document.${formName}.feeLain3.value !="")
{
index = 4;
}
if (document.${formName}.feeLain4.value !="")
{
index = 5;
}
if (document.${formName}.feeLain5.value !="")
{
index = 6;
}
if (document.${formName}.feeLain6.value !="")
{
index = 7;
}
if (document.${formName}.feeLain7.value !="")
{
index = 8;
}
if (document.${formName}.feeLain8.value !="")
{
index = 9;
document.getElementById("addmorePOIbutton").disabled = true;

}
function insertRow(){
	//alert ("index = "+index);
	if (index == 8)
	{
		document.getElementById("addmorePOIbutton").disabled = true; 
	}
            var table=document.getElementById("POITable");
            var row=table.insertRow(table.rows.length-2);
            var cell1=row.insertCell(0);
            var t2b = document.createTextNode("\u00A0");
            var t2z = document.createTextNode("\u00A0");
            var t1=document.createElement("input");
           		t1.setAttribute("type", "text");
           		t1.size = "50";
           		t1.id = "textLain"+index;
            var t1a = document.createTextNode("Lain-lain : ");
            	cell1.appendChild(t2z);    
            	cell1.appendChild(t1a);
                cell1.appendChild(t1);
                
            var cell2=row.insertCell(1);
           		cell2.setAttribute("align","center");
            var t2=document.createElement("input");
           		t2.setAttribute("type", "text");
           		//t2.onkeypress = isNumberKeyDecimal;
           		t2.setAttribute("onkeypress", "return isNumberKeyDecimal(event)");
           		t2.size = "2";
           		t2.style="text-align:right;"
            var t2a = document.createTextNode("RM ");
            
                t2.id = "feeLain"+index;
                cell2.appendChild(t2a);
                cell2.appendChild(t2b);
                cell2.appendChild(t2);
                
            var cell3=row.insertCell(2);
            	cell3.setAttribute("align","center");
           	var t3a = document.createTextNode("RM ");
           	var t3b = document.createTextNode("\u00A0");
           	var t3c = document.createTextNode(" x ");
            var t3	= document.createElement("input");
            var t3e	= document.createElement("input");
           		t3.setAttribute("type", "text");
           		t3.setAttribute("onkeypress", "return isNumberKeyDecimal(event)");
       			t3.size = "2";
       			t3.style="text-align:right;"
                t3.id = "feeLaina"+index;
                
       			t3e.setAttribute("type", "text");
       			t3e.setAttribute("onkeypress", "return isNumberKeyDecimal(event)");
       			t3e.size = "2";
       			t3e.style="text-align:right;"
                t3e.id = "qfeeLain"+index;
       			
       			cell3.appendChild(t3a);
                cell3.appendChild(t3b);
                cell3.appendChild(t3);
                cell3.appendChild(t3c);
                cell3.appendChild(t3e);
                
            var t4a = document.createTextNode("RM\u00A0 ");
            var t4b = document.createTextNode("\u00A0");
            var t4z = document.createTextNode("\u00A0");
            var cell4=row.insertCell(3);
           		cell4.setAttribute("align","center");
            var t4=document.createElement("input");
           		t4.setAttribute("type", "text");
           		t4.setAttribute('disabled', true);
   				t4.size = "2";
   				t4.style="text-align:right;"
                t4.id = "jumlahfeeLain"+index;
   			var t4c=document.createElement("input");
   				t4c.setAttribute('type','button'); 
   				t4c.setAttribute('value','Kira'); 
   				t4c.setAttribute('onclick','KiraLain'+index+'();'); // for FF
   				//t4c.onclick = function() {KiraLain1();}; // for IE
   
                cell4.appendChild(t4a);
                cell4.appendChild(t4b);
                cell4.appendChild(t4z);
                cell4.appendChild(t4);
                cell4.appendChild(t4b);
                cell4.appendChild(t4z);
                cell4.appendChild(t4c);
      index++;

}

function isNumberKeyDecimal(event)
{
	//alert("event.charCode = "+event.charCode);
	return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 0;
	}

function enableT()
{
	document.${formName}.txtNamaPenerima.disabled = false;
}
function disableSERAH(){
	document.getElementById("serah1").disabled=true
	document.getElementById("serah2").disabled=true
	document.getElementById("serah1").checked=false
	document.getElementById("serah2").checked=false
	
	//document.${formName}.txtNoDaftarPos.disabled = false;	

	document.getElementById("pos1").disabled=false
	document.getElementById("pos2").disabled=false	
}
function enableText()
{
	document.${formName}.txtNoDaftarPos.disabled = false;
	//document.${formName}.txtNamaPenerima.disabled = false;	
}
function disabledText()
{
	document.${formName}.txtNoDaftarPos.disabled = true;
	document.${formName}.txtNoDaftarPos.value = "";

	/*
	document.${formName}.txtNamaPenerima.disabled = true;
	document.${formName}.txtNamaPenerima.value = "";
	*/
}
function seterusnya(id_permohonan,id_status) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan&tabId=0";
	document.${formName}.submit();
}
function doCheckSOCTXT() 
{
	document.${formName}.command.value = "doCheckSOCTXT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function dochangeBandarByNegeri() 
{
	document.${formName}.command.value = "maklumatSerahan";
	document.${formName}.command2.value = "dochangeBandarByNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPeguamByNegeri() 
{
	document.${formName}.command.value = "getBandarPeguamByNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function dochangeBandarByNegeriUpdate() 
{
	document.${formName}.command.value = "maklumatSerahan";
	document.${formName}.command2.value = "kemaskiniSerahan";
	document.${formName}.command3.value = "dochangeBandarByNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function doCheckSOCTXTUpdateTerkini() 
{
	document.${formName}.command.value = "doCheckSOCTXTUpdateTerkini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getDetailFirmaX() 
{
	document.${formName}.command.value = "doCheckSOCTXTUpdateTerkini";
	document.${formName}.command2.value = "getDetailFirmaX";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function doCheckSOCTXTTambah() 
{
	document.${formName}.command.value = "doCheckSOCTXTTambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function changeGetAlamatPerayuUPDATETambah(id_permohonan,id_status) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.commandxx.value = "kemaskiniX";
	document.${formName}.commandoc.value = "changeGetAlamatPerayuUPDATETambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPerayuByNegeriVNE() 
{
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.commandxx.value = "kemaskiniX";
	document.${formName}.commandoc.value = "getBandarPerayuByNegeriVNE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPeguamByNegeriVNE() 
{
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.commandxx.value = "kemaskiniX";
	document.${formName}.commandoc.value = "getBandarPeguamByNegeriVNE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function updateTambahPeguam(id_permohonan,id_status,id_peguamX) 
{
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmel;

	if(document.${formName}.EsocPerayu.value == "")
	{
		alert("Sila pilih \"Nama Perayu\" terlebih dahulu");
		document.${formName}.EsocPerayu.focus();  
		return;
	}	
	else if (document.${formName}.EtxtPoskodPerayu.value != "" && document.${formName}.EtxtPoskodPerayu.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.EtxtPoskodPerayu.focus();
	}
	else if (document.${formName}.txtPoskodPeguam.value != "" && document.${formName}.txtPoskodPeguam.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat peguam dengan selengkapnya");
		document.${formName}.txtPoskodPeguam.focus();
	}
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah");		
		document.${formName}.em.focus();
		return;
	}
	else if(document.${formName}.txtEmel.value == "" && document.${formName}.txtNoFaks.value == ""
			&& document.${formName}.txtNoTelefon.value == "" && document.${formName}.txtBandarPeguam.value == ""
			&& document.${formName}.txtPoskodPeguam.value == "" && document.${formName}.txtAlamatPeguam1.value == ""
			&& document.${formName}.txtAlamatPeguam2.value == "" && document.${formName}.txtAlamatPeguam3.value == ""
			&& document.${formName}.txtNoRujukan.value == "" && document.${formName}.socNegeriPeguam.value == "" && document.${formName}.txtNamaFirma.value == "")
		{
			alert("Tiada data peguam yang hendak disimpan");
			document.${formName}.txtNamaFirma.focus();  
			return;
		}	
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_peguam.value = id_peguamX;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.commandxx.value = "kemaskiniX";
	document.${formName}.commandxxx.value = "updateTambahPeguam";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
	}
}
function batalX(id_permohonan,id_status,id_peguamX) 
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_peguam.value = id_peguamX;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kemaskiniX(id_permohonan,id_pemohon,id_status,id_peguamX) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_peguamX.value = id_peguamX;
	document.${formName}.id_pemohon.value = id_pemohon;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.commandxx.value = "kemaskiniX";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function semakFirma(id_peguam)
{
	document.${formName}.id_peguam.value = id_peguam;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "semakFirma";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getDetailFirma()
{
	document.${formName}.command.value = "getDetailFirma";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPerayuByNegeriUpdate()
{
	document.${formName}.command.value = "getBandarPerayuByNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPeguamByNegeriUpdate()
{
	document.${formName}.command.value = "getBandarPeguamByNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPerayuByNegeri()
{
	document.${formName}.command.value = "getBandarPerayuByNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getDetailFirmaUPDATE()
{
	document.${formName}.command.value = "getDetailFirmaUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getDetailFirmaTambah()
{
	document.${formName}.command.value = "getDetailFirmaTambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function simpanTambahPeguam()
{
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmel;

	for (var i=0; i < document.${formName}.sorPeguam.length; i++)
	   {
	   if (document.${formName}.sorPeguam[i].checked)
	      {
	      var validate = document.${formName}.sorPeguam[i].value;
	      }
	   }

	if(validate== "1"){
		if(document.${formName}.txtEmel.value == "" && document.${formName}.txtNoFaks.value == ""
			&& document.${formName}.txtNoTelefon.value == "" && document.${formName}.txtBandarPeguam.value == ""
			&& document.${formName}.txtPoskodPeguam.value == "" && document.${formName}.txtAlamatPeguam1.value == ""
			&& document.${formName}.txtAlamatPeguam2.value == "" && document.${formName}.txtAlamatPeguam3.value == ""
			&& document.${formName}.txtNoRujukan.value == "" && document.${formName}.socNegeriPeguam.value == "" && document.${formName}.socNamaFirma.value == "")
		{
			alert("Tiada data peguam yang hendak disimpan");
			document.${formName}.socNamaFirma.focus();  
			return;
		}
	}else{
		if(document.${formName}.txtEmel.value == "" && document.${formName}.txtNoFaks.value == ""
			&& document.${formName}.txtNoTelefon.value == "" && document.${formName}.txtBandarPeguam.value == ""
			&& document.${formName}.txtPoskodPeguam.value == "" && document.${formName}.txtAlamatPeguam1.value == ""
			&& document.${formName}.txtAlamatPeguam2.value == "" && document.${formName}.txtAlamatPeguam3.value == ""
			&& document.${formName}.txtNoRujukan.value == "" && document.${formName}.socNegeriPeguam.value == "" && document.${formName}.txtNamaFirma.value == "")
		{
			alert("Tiada data peguam yang hendak disimpan");
			document.${formName}.txtNamaFirma.focus();  
			return;
		}		
	}
	
	if (document.${formName}.txtPoskodPeguam.value != "" && document.${formName}.txtPoskodPeguam.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat peguam dengan selengkapnya");
		document.${formName}.txtPoskodPeguam.focus();
	}
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah");		
		document.${formName}.em.focus();
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanTambahPeguam";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit();
	}
}


	
	
function tambahPeguam(id_permohonan,id_status)
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "tambahPeguam";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function getBandarPeguamByNegeriTambah()
{
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "tambahPeguam";
	document.${formName}.command2a.value = "getBandarPeguamByNegeriTambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function changeGetAlamatPerayu(id_permohonan,id_status)
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.commandx.value = "changeGetAlamatPerayu";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function updateSerahan()
{

	//11122009
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	var tsh = document.getElementById("tarikhS").value;
	var dts   = parseInt(tsh.substring(0,2),10);
   	var mons  = parseInt(tsh.substring(3,5),10);
   	var yrs   = parseInt(tsh.substring(6,10),10);

   	var tarikhserah = new Date(yrs, mons, dts);
   	//11122009
   	
   	
	var str1  = document.getElementById("txdTarikhSerahan").value;
   	var str2  = document.getElementById("tarikhMohon").value;

   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

	var date1 = new Date(yr1, mon1, dt1);
   	var trmohon = new Date(yr2, mon2, dt2);
	
	var ts=document.${formName}.txdTarikhSerahan

	var jenisserah  = document.getElementById("sorJenisSerah1");
   	
   	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorStatus.length;  i++){
	if (${formName}.sorStatus[i].checked)
	radioSelected = true;
	}
	
	if(document.${formName}.txtBayaranRekod.value == "" || document.${formName}.txtBayaranRekod.value == 0.00)
	{
		alert("Sila masukkan \"Bayaran Penyediaan Rekod Rayuan\" ");
		document.${formName}.txtBayaranRekod.focus();  
		return;
	}
	else if(document.${formName}.txdTarikhSerahan.value == "")
	{
		alert("Sila masukkan \"Tarikh Borang K3\" terlebih dahulu");
		document.${formName}.txdTarikhSerahan.focus();  
		return;
	}
	else if(date1 < trmohon){
   		alert("Sila pastikan \"Tarikh Borang K3\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 
	else if (isDate(ts.value)==false){
		ts.focus()
		return;
	}
	else if(document.${formName}.txtNamaPenghantar.value == "")
	{
		alert("Sila masukkan \"Nama Penghantar\" terlebih dahulu");
		document.${formName}.txtNamaPenghantar.focus();  
		return;
	}

	 //11122009
	else if(document.${formName}.tarikhS.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu");
		document.${formName}.tarikhS.focus();  
		return;
	}
	else if(tarikhserah > currentDate){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.tarikhS.focus();
	 	return;
	} 
	else if(tarikhserah < trmohon){
		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.tarikhS.focus();
	 	return;
	} 
	else if (isDate(tsh)==false){
		tsh.focus()
		return;
	}
	//11122009
	
	else if (!radioSelected){
   		alert("Sila pilih Jenis serahan terlebih dahulu.");
   		jenisserah.focus();
   		return;
   	}
	else if((document.getElementById("serah2").checked) && (document.${formName}.txtCatatan.value ==""))
	{
		alert("Sila masukkan catatan untuk Surat Akuan Bersumpah terlebih dahulu.");
		document.${formName}.txtCatatan.focus(); 
		return;
	}
   	else if((document.getElementById("pos1").checked) && (document.${formName}.txtNoDaftarPos.value ==""))
	{
		alert("Sila masukkan No.surat daftar terlebih dahulu.");
		document.${formName}.txtNoDaftarPos.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaPenerima.value == "") {
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu");
		document.${formName}.txtNamaPenerima.focus();
	}
	else if (document.${formName}.alamatSerah1.value == "") {
		alert("Sila masukkan \"Alamat\" terlebih dahulu");
		document.${formName}.alamatSerah1.focus();
	}
	else if (document.${formName}.txtPoskodSerah.value == "") {
		alert("Sila masukkan \"Poskod\" terlebih dahulu");
		document.${formName}.txtPoskodSerah.focus();
	}
	else if (document.${formName}.txtPoskodSerah.value != "" && document.${formName}.txtPoskodSerah.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" dengan selengkapnya");
		document.${formName}.txtPoskodSerah.focus();
	}
	else if (document.${formName}.socNegeri.value == "") {
		alert("Sila pilih \"Negeri\" terlebih dahulu");
		document.${formName}.socNegeri.focus();
	}
	/*
	else if (document.${formName}.txtBandarSerah.value == "") {
		alert("Sila masukkan \"Bandar\" terlebih dahulu");
		document.${formName}.txtBandarSerah.focus();
	}	
	*/
	else
	{	//alert("txtBayaranRekod = "+document.${formName}.txtBayaranRekod.value);
		document.${formName}.txtBayaranRekodhidden.value = document.${formName}.txtBayaranRekod.value;
		//alert("txtBayaranRekodhidden = "+document.${formName}.txtBayaranRekodhidden.value);
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "maklumatSerahan";
		document.${formName}.command2.value = "kemaskiniSerahan";
		document.${formName}.command3.value = "updateSerahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit();
	}
}
function kemaskiniSerahan()
{
	document.${formName}.command.value = "maklumatSerahan";
	document.${formName}.command2.value = "kemaskiniSerahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function batalUpdateSerahan(id_permohonan,id_status,id_rayuan)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_rayuan.value = id_rayuan;
	document.${formName}.command.value = "maklumatSerahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function simpanSerahan() 
{	

	//11122009
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	var tsh = document.getElementById("tarikhS").value;
	var dts   = parseInt(tsh.substring(0,2),10);
   	var mons  = parseInt(tsh.substring(3,5),10);
   	var yrs   = parseInt(tsh.substring(6,10),10);

   	var tarikhserah = new Date(yrs, mons, dts);
   	//11122009
   	
   	
	var str1  = document.getElementById("txdTarikhSerahan").value;
   	var str2  = document.getElementById("tarikhMohon").value;

   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

	var date1 = new Date(yr1, mon1, dt1);
   	var trmohon = new Date(yr2, mon2, dt2);

   	var ts=document.${formName}.txdTarikhSerahan

   	var jenisserah  = document.getElementById("sorJenisSerah1");
   	
   	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorStatus.length;  i++){
	if (${formName}.sorStatus[i].checked)
	radioSelected = true;
	}
	
   	if(document.${formName}.txtBayaranRekod.value == "" || document.${formName}.txtBayaranRekod.value == 0.00)
	{
		alert("Sila masukkan \"Bayaran Penyediaan Rekod Rayuan\" ");
		document.${formName}.txtBayaranRekod.focus();  
		return;
	}
   	else if(document.${formName}.txdTarikhSerahan.value == "")
	{
		alert("Sila masukkan \"Tarikh Borang K3\" terlebih dahulu");
		document.${formName}.txdTarikhSerahan.focus();  
		return;
	}
   	else if(document.${formName}.txtNamaPenghantar.value == "")
	{
		alert("Sila masukkan \"Nama Penghantar\" terlebih dahulu");
		document.${formName}.txtNamaPenghantar.focus();  
		return;
	}

   	
  //11122009
	else if(document.${formName}.tarikhS.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" terlebih dahulu");
		document.${formName}.tarikhS.focus();  
		return;
	}
	else if(tarikhserah > currentDate){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.tarikhS.focus();
	 	return;
	} 
	else if(tarikhserah < trmohon){
		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.tarikhS.focus();
	 	return;
	} 
	else if (isDate(tsh)==false){
		tsh.focus()
		return;
	}
	//11122009
	
	
	
   	else if (!radioSelected){
   		alert("Sila pilih Jenis serahan terlebih dahulu.");
   		jenisserah.focus();
   		return;
   	}
   	else if((document.getElementById("serah2").checked) && (document.${formName}.txtCatatan.value ==""))
	{
		alert("Sila masukkan catatan untuk Surat Akuan Bersumpah terlebih dahulu.");
		document.${formName}.txtCatatan.focus(); 
		return;
	}
   	else if((document.getElementById("pos1").checked) && (document.${formName}.txtNoDaftarPos.value ==""))
	{
		alert("Sila masukkan No.surat daftar terlebih dahulu.");
		document.${formName}.txtNoDaftarPos.focus(); 
		return;
	}
	else if(date1 < trmohon){
   		alert("Sila pastikan \"Tarikh Borang K3\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 
	else if (isDate(ts.value)==false){
		ts.focus()
		return;
	}
	else if (document.${formName}.txtNamaPenerima.value == "") {
		alert("Sila masukkan \"Nama Penerima\" terlebih dahulu");
		document.${formName}.txtNamaPenerima.focus();
	}
	else if (document.${formName}.alamatSerah1.value == "") {
		alert("Sila masukkan \"Alamat\" terlebih dahulu");
		document.${formName}.alamatSerah1.focus();
	}
	else if (document.${formName}.txtPoskodSerah.value == "") {
		alert("Sila masukkan \"Poskod\" terlebih dahulu");
		document.${formName}.txtPoskodSerah.focus();
	}
	else if (document.${formName}.txtPoskodSerah.value != "" && document.${formName}.txtPoskodSerah.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" dengan selengkapnya");
		document.${formName}.txtPoskodSerah.focus();
	}
	else if (document.${formName}.socNegeri.value == "") {
		alert("Sila pilih \"Negeri\" terlebih dahulu");
		document.${formName}.socNegeri.focus();
	}
	/*
	else if (document.${formName}.txtBandarSerah.value == "") {
		alert("Sila masukkan \"Bandar\" terlebih dahulu");
		document.${formName}.txtBandarSerah.focus();
	}
	*/
	else
	{
		
		document.${formName}.txtBayaranRekodhidden.value = document.${formName}.txtBayaranRekod.value;
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "maklumatSerahan";
		document.${formName}.command2.value = "simpanSerahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit();	
	}
}
function simpanDataRayuan()
{
/*
	var tr=document.${formName}.txdTarikhRayuan

	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	//var cDate = day + "/" + month + "/" + year;
	
   	var str1  = document.getElementById("txdTarikhRayuan").value;
   	var str2  = document.getElementById("tarikhMohon").value;

   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var currentDate = new Date(year, month, day);
	var date1 = new Date(yr1, mon1, dt1);
   	var trmohon = new Date(yr2, mon2, dt2);

	if(document.${formName}.txdTarikhRayuan.value == "")
	{
		alert("Sila masukkan \"Tarikh Rayuan\" terlebih dahulu");
		document.${formName}.txdTarikhRayuan.focus();  
		return;
	}
	
	else if (isDate(tr.value)==false){
		tr.focus()
		return false
	}
	else if(date1 < trmohon){
   		alert("Sila pastikan \"Tarikh Rayuan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhRayuan.focus();
	 	return;
	} 
	else if(date1 < currentDate){
   		alert("Sila pastikan \"Tarikh Rayuan\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.txdTarikhRayuan.focus();
	 	return;
	} 
*/	

var txtPerkaraRayu = null; 
var oEditor = FCKeditorAPI.GetInstance('txtPerkaraRayu') ;
if(oEditor) { 
	txtPerkaraRayu = oEditor.GetXHTML(true); 
}

	if(txtPerkaraRayu == "" || txtPerkaraRayu == null)
	{
		alert("Sila masukkan \"Perkara Yang Dirayu\" terlebih dahulu");
		document.${formName}.txtPerkaraRayu.focus();  
		return;
	}
	
/*	
	if(document.${formName}.txtPerkaraRayu.value == "")
	{
		alert("Sila masukkan \"Perkara Yang Dirayu\" terlebih dahulu");
		document.${formName}.txtPerkaraRayu.focus();  
		return;
	}
*/
	
/*
	else if(document.${formName}.txtAlasanRayuan.value == "")
	{
		alert("Sila masukkan \"Alasan rayuan\" terlebih dahulu");
		document.${formName}.txtAlasanRayuan.focus();  
		return;
	}
*/
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "dataRayuan";
		document.${formName}.command2.value = "kemaskiniDataRayuan";
		document.${formName}.command3.value = "simpanDataRayuan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit();
	}
}
function batalDataRayuan(id_permohonan,id_pemohon,id_status)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pemohon.value = id_pemohon;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "dataRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kemaskiniDataRayuan(id_permohonan,id_pemohon,id_status,id_rayuan)
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pemohon.value = id_pemohon;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_rayuan.value = id_rayuan;
	document.${formName}.command.value = "dataRayuan";
	document.${formName}.command2.value = "kemaskiniDataRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function simpanMaklumatPP()
{	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmel;
	
	if(document.${formName}.socPerayu.value == "")
	{
		alert("Sila pilih \"Nama Perayu\" terlebih dahulu");
		document.${formName}.socPerayu.focus();  
		return;
	}
	else if (document.${formName}.txtPoskodPerayu.value != "" && document.${formName}.txtPoskodPerayu.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.txtPoskodPerayu.focus();
	}
	else if (document.${formName}.txtPoskodPeguam.value != "" && document.${formName}.txtPoskodPeguam.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat peguam dengan selengkapnya");
		document.${formName}.txtPoskodPeguam.focus();
	}
	
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah");		
		document.${formName}.em.focus();
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanMaklumatPP";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit();
	}
}
function updateMaklumatPP(id_permohonan,id_pemohon,id_status) 
{

	var canedit = document.${formName}.canEdit.value;
	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.EtxtEmel;

	
	if(document.${formName}.EsocPerayu.value == "")
	{
		alert("Sila pilih \"Nama Perayu\" terlebih dahulu");
		document.${formName}.EsocPerayu.focus();  
		return;
	}	
	else if (document.${formName}.EtxtPoskodPerayu.value != "" && document.${formName}.EtxtPoskodPerayu.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.EtxtPoskodPerayu.focus();
	}
	else if (document.${formName}.EtxtPoskodPeguam.value != "" && document.${formName}.EtxtPoskodPeguam.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat peguam dengan selengkapnya");
		document.${formName}.EtxtPoskodPeguam.focus();
	}
	else if(!em.value.match(emailExp) && em.value!=""){
		
		alert("Alamat email tidak sah");		
		document.${formName}.em.focus();
		return;
	}
	else if(canedit=="no" && (document.${formName}.EtxtEmel.value == "" && document.${formName}.EtxtNoFaks.value == ""
			&& document.${formName}.EtxtNoTelefon.value == "" && document.${formName}.EtxtBandarPeguam.value == ""
			&& document.${formName}.EtxtPoskodPeguam.value == "" && document.${formName}.EtxtAlamatPeguam1.value == ""
			&& document.${formName}.EtxtAlamatPeguam2.value == "" && document.${formName}.EtxtAlamatPeguam3.value == ""
			&& document.${formName}.EtxtNoRujukan.value == "" && document.${formName}.EsocNegeriPeguam.value == "" && document.${formName}.EtxtNamaFirma.value == ""))
	{
			alert("Tiada data peguam yang hendak disimpan");
			document.${formName}.EtxtNamaFirma.focus();  
			return;
	
	}
	else
	{
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pemohon.value = id_pemohon;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "kemaskiniMaklumatPP";
	document.${formName}.command3.value = "updateMaklumatPP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
	
	}
}
function changeGetAlamatPerayuUPDATE(id_permohonan,id_status) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "kemaskiniMaklumatPP";
	document.${formName}.command3.value = "changeGetAlamatPerayuUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kemaskiniMaklumatPP(id_permohonan,id_pemohon,id_status) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pemohon.value = id_pemohon;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.command2.value = "kemaskiniMaklumatPP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function batalMaklumatPP(id_permohonan,id_status) 
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kembali(id_permohonan,id_status) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
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
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}
function maklumatPerayu(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.submit();
}
function maklumatRayuan(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "dataRayuan";
	document.${formName}.submit();
}
function maklumatSerahan(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "maklumatSerahan";
	document.${formName}.submit();
}
/*
function memorandumRayuan(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "memorandumRayuan";
	document.${formName}.submit();
} */
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}


function isDate(dtStr){
	var dtCh= "/";
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}

function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
/*
function doCheck1() {

	var data1elem = document.getElementById('txtNamaFirma');
	var data2elem = document.getElementById('socNamaFirma');
	var mySelectelem = document.getElementById('sorPeguam1');

	if (mySelectelem.value == '1') {
	data1elem.style.display = 'inline'; 
	data2elem.style.display = 'none';
	} else {
	data1elem.style.display = 'none';
	data2elem.style.display = 'inline'; 
	}

	document.${formName}.txtNamaFirma.value = "";
	document.${formName}.socNamaFirma.value = "";
	document.${formName}.txtNoRujukan.value = "";
	document.${formName}.txtAlamatPeguam1.value = "";
	document.${formName}.txtAlamatPeguam2.value = "";
	document.${formName}.txtAlamatPeguam3.value = "";
	document.${formName}.txtPoskodPeguam.value = "";
	document.${formName}.txtBandarPeguam.value = "";
	document.${formName}.txtNoTelefon.value = "";
	document.${formName}.txtNoFaks.value = "";
	document.${formName}.txtEmel.value = "";
	document.${formName}.socNegeriPeguam.value = "";
}
function doCheck2() {

	var data1elem = document.getElementById('txtNamaFirma');
	var data2elem = document.getElementById('socNamaFirma');
	var mySelectelem = document.getElementById('sorPeguam2');

	if (mySelectelem.value == '1') {
	data1elem.style.display = 'inline'; 
	data2elem.style.display = 'none';
	} else {
	data1elem.style.display = 'none';
	data2elem.style.display = 'inline'; 
	}

	document.${formName}.txtNamaFirma.value = "";
	document.${formName}.socNamaFirma.value = "";
	document.${formName}.txtNoRujukan.value = "";
	document.${formName}.txtAlamatPeguam1.value = "";
	document.${formName}.txtAlamatPeguam2.value = "";
	document.${formName}.txtAlamatPeguam3.value = "";
	document.${formName}.txtPoskodPeguam.value = "";
	document.${formName}.txtBandarPeguam.value = "";
	document.${formName}.txtNoTelefon.value = "";
	document.${formName}.txtNoFaks.value = "";
	document.${formName}.txtEmel.value = "";
	document.${formName}.socNegeriPeguam.value = "";
}
*/
function cetakBorangK1(noFail,idnegeri,idperbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idperbicaraan+"&idnegeri="+idnegeri+"&report=BorangK1&flagReport=B";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK3(noFail,idnegeri,idperbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idperbicaraan+"&idnegeri="+idnegeri+"&report=BorangK3&flagReport=B";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function kembaliList(){
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kembaliSenaraiFail(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.${formName}.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.${formName}.method="POST";
	document.${formName}.tarikhMohon.value = "";
	document.${formName}.submit();
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
function displayHTML(field) 
{
	var inf = field.value;
	win = window.open('', 'popup', 'resizable=yes,scrollbars=yes,toolbar=no,status=no');
	win.document.write("" + inf + "");
}
function maklumatRekodRayuan(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "rekodRayuan";
	document.${formName}.submit();
}
function simpanRekodRayuan(submitmode,param) {

 	var txtAsasKeputusan = null; 
 	var txtNotaBicara = null; 
 	var oEditorAsas = FCKeditorAPI.GetInstance('txtAsasKeputusan') ;
 	var oEditorNota = FCKeditorAPI.GetInstance('txtNotaBicara') ;
 	
 	
 	
 	if(oEditorAsas) { 
 		txtAsasKeputusan = oEditorAsas.GetXHTML(true); 
 	}
 	if(oEditorNota) { 
 		txtNotaBicara = oEditorNota.GetXHTML(true); 
 	}

 	
 	if(txtAsasKeputusan == "" || txtAsasKeputusan == null)
 	{
 		alert("Sila masukkan \"Asas-asas keputusan\" terlebih dahulu.");
 		document.${formName}.txtAsasKeputusan.focus(); 
 		return;
 	}
/* 	else
 	 if(!checkSelected){
 		alert("Sila pilih \"Orang berkepentingan\" terlebih dahulu.");
 		return;
 	}
*/ 	else
 	 if(txtNotaBicara == "" || txtNotaBicara == null){
 	 	alert("Sila masukkan \"Nota Bicara\" terlebih dahulu.");
 		document.${formName}.txtNotaBicara.focus(); 
 		return;
 	 }

	else{
		
		document.${formName}.jumlahh.value = document.${formName}.jumlahAllfee.value;
		if (document.${formName}.textLain2 !== undefined )
			{
			document.${formName}.feeLaina2a.value = document.${formName}.feeLaina2.value;
			document.${formName}.qfeeLain2a.value = document.${formName}.qfeeLain2.value;
			var textLain2 = document.${formName}.textLain2.value
			document.${formName}.textLainHidden2.value = textLain2;
			var feeLain2 = document.${formName}.feeLain2.value
			document.${formName}.textfeeLainHidden2.value = feeLain2;
			}
		
		if (document.${formName}.textLain1 !== undefined)
			{
			var textLain1 = document.${formName}.textLain1.value
			document.${formName}.textLainHidden.value = textLain1;
			var feeLain1 = document.${formName}.feeLain1.value
			document.${formName}.textfeeLainHidden.value = feeLain1;
			}
		
		if (document.${formName}.textLain3 !== undefined)
		{
			document.${formName}.feeLaina3a.value = document.${formName}.feeLaina3.value;
			document.${formName}.qfeeLain3a.value = document.${formName}.qfeeLain3.value;
			var textLain3 = document.${formName}.textLain3.value
			document.${formName}.textLainHidden3.value = textLain3;
			var feeLain3 = document.${formName}.feeLain3.value
			document.${formName}.textfeeLainHidden3.value = feeLain3;
		}
		
		if (document.${formName}.textLain4 !== undefined)
		{
			document.${formName}.feeLaina4a.value = document.${formName}.feeLaina4.value;
			document.${formName}.qfeeLain4a.value = document.${formName}.qfeeLain4.value;
			var textLain4 = document.${formName}.textLain4.value
			document.${formName}.textLainHidden4.value = textLain4;
			var feeLain4 = document.${formName}.feeLain4.value
			document.${formName}.textfeeLainHidden4.value = feeLain4;
		}
		
		if (document.${formName}.textLain5 !== undefined)
		{
			document.${formName}.feeLaina5a.value = document.${formName}.feeLaina5.value;
			document.${formName}.qfeeLain5a.value = document.${formName}.qfeeLain5.value;
			var textLain5 = document.${formName}.textLain5.value
			document.${formName}.textLainHidden5.value = textLain5;
			var feeLain5 = document.${formName}.feeLain5.value
			document.${formName}.textfeeLainHidden5.value = feeLain5;
		}
		
		if (document.${formName}.textLain6 !== undefined)
		{
			document.${formName}.feeLaina6a.value = document.${formName}.feeLaina6.value;
			document.${formName}.qfeeLain6a.value = document.${formName}.qfeeLain6.value;
			var textLain6 = document.${formName}.textLain6.value
			document.${formName}.textLainHidden6.value = textLain6;
			var feeLain6 = document.${formName}.feeLain6.value
			document.${formName}.textfeeLainHidden6.value = feeLain6;
		}
		
		if (document.${formName}.textLain7 !== undefined)
		{
			document.${formName}.feeLaina7a.value = document.${formName}.feeLaina7.value;
			document.${formName}.qfeeLain7a.value = document.${formName}.qfeeLain7.value;
			var textLain7 = document.${formName}.textLain7.value
			document.${formName}.textLainHidden7.value = textLain7;
			var feeLain7 = document.${formName}.feeLain7.value
			document.${formName}.textfeeLainHidden7.value = feeLain7;
		}
		
		if (document.${formName}.textLain8 !== undefined)
		{
			document.${formName}.feeLaina8a.value = document.${formName}.feeLaina8.value;
			document.${formName}.qfeeLain8a.value = document.${formName}.qfeeLain8.value;
			var textLain8 = document.${formName}.textLain8.value
			document.${formName}.textLainHidden8.value = textLain8;
			var feeLain8 = document.${formName}.feeLain8.value
			document.${formName}.textfeeLainHidden8.value = feeLain8;
		}

		
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.command.value = "rekodRayuan";
		document.${formName}.command2.value = submitmode;	
		document.${formName}.submit();
	}
}
function kemaskiniRekodRayuan() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "rekodRayuan";
	document.${formName}.command2.value = "kemaskiniRekodRayuan";	
	document.${formName}.submit();
}
function batalRekodRayuan() {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "rekodRayuan";
	document.${formName}.command2.value = "batalRekodRayuan";	
	document.${formName}.submit();
}
</script>

<script language="javascript">
var checked = false;
function checkALL(field,size) {

  if (!checked) { 

	 if(size>1){
    	for (i = 0; i < field.length; i++) 
    	{ 
      		field[i].checked = true;
   		}
    		checked = true;
	 }else{
	      document.${formName}.cbsemaks.checked = true;
	   	  checked = true;
	 }
	 
  } else {

	 if(size>1){
    	for (i = 0; i < field.length; i++) 
    	{ 
     		field[i].checked = false;
    	}
    		checked = false;
	 }else{
		 document.${formName}.cbsemaks.checked = false;
	   	 checked = false;
	 }	
	 	
  }
  
}

/*
var oEditorPrayu = FCKeditorAPI.GetInstance('txtPerkaraRayu');

	function FCKeditor_OnComplete( oEditorPrayu ){

		if (oEditorPrayu.Name == "txtPerkaraRayu") {
			oEditorPrayu.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);
		}
	}

	function fckeditor_word_count() {
		 //var count = editorInstance.GetHTML().replace('&#160','').length
		 //var editorInstance = FCKeditorAPI.GetInstance('txtPerkaraRayu');	
		 var oEditorPrayu = FCKeditorAPI.GetInstance('txtPerkaraRayu');
		 
		 var count = oEditorPrayu.GetHTML().length;
		 document.getElementById('word_count').innerHTML = (2000 - count) + " Baki Aksara";
		
		 if (count > 2000) {
		 alert('Jumlah aksara tidak boleh melebihi 2000');
		 //editorInstance.EditorDocument.body.contentEditable='false';
		 //editorInstance.EditorDocument.designMode='off';
		 }
		 
}
*/
function cetakBuktiPenyampaian(idfail) {

	if(document.${formName}.txtNamaPenghantar.value == "")
	{
		alert("Sila lengkapkan maklumat serahan terlebih dahulu");
		document.${formName}.txtNamaPenghantar.focus();  
		return;
	}
	else{
		var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianRayuanBK3?idfail="+idfail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}
function hideAsterik()
{
	var nonAsterik = document.getElementById("nonAsterik"); 
	var withAsterik = document.getElementById("withAsterik"); 

	nonAsterik.style.display = "inline"; 
	withAsterik.style.display = "none"; 
}
function showAsterik()
{
	var nonAsterik = document.getElementById("nonAsterik"); 
	var withAsterik = document.getElementById("withAsterik"); 

	nonAsterik.style.display = "none"; 
	withAsterik.style.display = "inline"; 
}
function cetakAkuanBersumpah(idfail,idperbicaraan) {
	var url = "../servlet/ekptg.report.ppk.SuratAkuanBersumpahRayuan?idperbicaraan="+idperbicaraan;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function ForView(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.${formName}.submit();
}

function PrintAsasKeputusan()
{

		var asaskeputusan = document.${formName}.txtAsasKeputusan.value;
		var EsocPerayu = document.${formName}.EsocPerayu.value;
		var ic1 = document.${formName}.EtxtNoKPBaru1.value;
		var ic2 = document.${formName}.EtxtNoKPBaru2.value;
		var ic3 = document.${formName}.EtxtNoKPBaru3.value;
		var alamat1 = document.${formName}.EtxtAlamatPerayu1.value;
		var alamat2 = document.${formName}.EtxtAlamatPerayu2.value;
		var alamat3 = document.${formName}.EtxtAlamatPerayu3.value;
		var poskod = document.${formName}.EtxtPoskodPerayu.value;
		var bandar = document.${formName}.EtxtBandarPerayu.value;
		var negeri = document.${formName}.socNegeriPerayu.value;
		var namaSimati = document.${formName}.namaSimatihidden.value;
		var negeriPermohonan = document.${formName}.namaNegerihidden.value;
		var daerahPermohonan = document.${formName}.namaDaerahhidden.value;
		var tarikhMati = document.${formName}.tarikhMatihidden.value;
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanCetakanAsasKeputusan?asaskeputusan="+asaskeputusan+"&namaPerayu="+EsocPerayu+"&ic1="+ic1+"&ic2="+ic2+"&ic3="+ic3+"&alamat1="+alamat1+"&alamat2="+alamat2+"&alamat3="+alamat3+"&poskod="+poskod+"&bandar="+bandar+"&negeri="+negeri+"&namaSimati="+namaSimati+"&negeriPermohonan="+negeriPermohonan+"&daerahPermohonan="+daerahPermohonan+"&tarikhMati="+tarikhMati;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
	
	
}

</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>
