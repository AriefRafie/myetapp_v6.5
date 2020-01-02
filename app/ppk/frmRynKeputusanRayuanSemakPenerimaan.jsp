<br/>

<center>

#set($perhatian = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>dipilih dan diisi.</font></i>")
#set($perhatian1 = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>dipilih.</font></i>")
#set($perhatian2 = "<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>diisi.</font></i>")

<!-- paging -->
#set($no1enable="<img border='0' title='Senarai permohonan' src='../img/1enable.png'>")
#set($arrow="<img border='0' src='../img/arrowgaris.png'>")
#set($no2enable="<img border='0' title='Senarai semak' src='../img/2enable.png'>")
#set($no3enable="<img border='0' title='Maklumat permohonan rayuan' src='../img/3enable.png'>")
#set($no4current="<img border='0' title='Keputusan rayuan' src='../img/4current2.png'>")


	<!-- paging -->	
	#set($pagingKeputusanPegawai="CL - 01 - 185")
	#set($pagingMemorandumRayuan="CL - 01 - 186")
	#set($pagingRekodRayuan="CL - 01 - 187")
	#set($pagingKeputusanMahkamah="CL - 01 - 188")

<table width="100%" border="0">
<tr>
	<td >
	<div align="right">
	<span>
	
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:xxx()" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
	#elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
 	#elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
	#elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
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
#end

<input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon">

#if($!headerppk.size()>0 )
#parse("app/ppk/headerppk.jsp")
#end

<fieldset id="header_lama" style="display:none" >
	<legend><strong>KEPUTUSAN RAYUAN</strong></legend>
	
	<table width="100%">
		<tr>
			<td width="50%" valign="top">
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td width="36%" valign="top">NO FAIL</td>
					<td width="1%" valign="top">:&nbsp;</td>
					<td width="63%" valign="top"><font color="blue">$noFail</font></td>
				</tr>
				<tr>
					<td valign="top">NEGERI</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$negeri.toUpperCase()</font></td>
				</tr>
				<tr>
					<td valign="top">DAERAH / JAJAHAN</td>
					<td valign="top">:&nbsp;</td>
					<td valign="top"><font color="blue">$daerah.toUpperCase()</font></td>
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
					<td valign="top"><font color="blue">$namaSimati.toUpperCase()</font></td>
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
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);keputusanPegawai('$id_permohonan','$id_status')" >KEPUTUSAN PEGAWAI</li>
    #if($id_status=="164" || $id_status=="166" || $id_status=="167" || $id_status=="180")
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(1);memorandumRayuan('$id_permohonan','$id_status')" >MEMORANDUM RAYUAN</li>
    #end
    #if(($id_status=="164" || $id_status=="166" || $id_status=="167" || $id_status=="180" ) && $tab3show=="yes")
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(2);maklumatSerahanK1('$id_permohonan','$id_status')" >MAKLUMAT SERAHAN REKOD RAYUAN</li>
    #end
    #if(($id_status=="164" || $id_status=="166" || $id_status=="167" || $id_status=="180") && ($maklumatSerahanPenasihat.size()!=0 && $maklumatSerahanMahkamah.size()!=0))
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(3);keputusanMahkamah('$id_permohonan','$id_status')" >KEPUTUSAN MAHKAMAH</li>
  	#end 
  </ul>
  
  <div class="TabbedPanelsContentGroup">
  
<!-- START [ tabbed 1 ] -->  
    <div class="TabbedPanelsContent">
          
#if($editformSKR=="no")
	#set($mode = "readonly")
	#set($mode1 = "disabled")
	#set($Cmode = "class=disabled")
#elseif($editformSKR=="yes")
	#set($mode = "")
	#set($mode1 = "")
	#set($Cmode = "")
#elseif($editformSKR=="new")
	#set($mode1 = "")
	#set($mode = "")	
	#set($Cmode = "")
#end     


#if($viewformSKR=="yes")

	#if($onchangeKpts=="yes")
		#set($check1x=$check1x)	
		#set($check2x=$check2x)	
		#set($check3x=$check3x)	
	#else
		#foreach($data in $dataSemakKR)
			#if($data.id_semakansenarai=="141")
				#set($check1="checked")	
			#elseif($data.id_semakansenarai=="142")
				#set($check2="checked")
			#elseif($data.id_semakansenarai=="143")
				#set($check3="checked")
			#end
		#end
	#end	
	
	#if($onchangeKpts=="yes")
		#set($resit="$resitX")
		#set($tarikhB="$tarikhBX")
	#else
		#foreach($b in $bayaranKR)
			#set($resit = $b.no_resit)
			#set($tarikhB = $b.tarikh_bayaran)
		#end
	#end
	
		#foreach($rad in $maklumatRayuan)
		
		 #if($onchangeKpts=="yes")	 		 	
		 	#set($checkA = "$checkxA")
			#set($checkB = "$checkxB")		 	
		 #else
		 	
			#if($rad.id_keputusanpegawai=="164")
				#set($checkA = "checked")
				#set($checkB = "")
			#elseif($rad.id_keputusanpegawai=="165")
				#set($checkA = "")
				#set($checkB = "checked")
			#end			
			
		 #end
		 	
			#if($onchangeKpts=="yes")
				#set($catatan="$catatanX")
			#else
				#set($catatan = $rad.catatan_pegawai)
			#end	  
				  
		#end		
	
#else

	#set($check1="")
	#set($check2="")
	#set($check3="")
	
	#set($checkA="")
	#set($checkB="")

	
	#if($onchangeKpts=="yes")
		#set($catatan="$catatanX")
		#set($resit="$resitX")
		#set($tarikhB="$tarikhBX")
	#else
		#set($catatan="")
		#set($resit="")
		#set($tarikhB="")
	#end
	
#end


  <fieldset>  
  <legend>SENARAI SEMAK PENERIMAAN BORANG K3</legend>	
     <table width="65%"  cellpadding="1" cellspacing="1" border="0">
     	<tr>
     		<td width="1%" valign="top">#if($editformSKR=="new" || $editformSKR=="yes")<font color="red">*</font>#else&nbsp;#end</td>
     		<td width="4%"><input type="checkbox" $mode1 value="141"  $check1 $check1x name="cbsemaks" id="cbsemaks1"></td>
     		<td width="95%">K3 sempurna diisi</td>
     	</tr>
     	<tr>
     		<td valign="top">#if($editformSKR=="new" || $editformSKR=="yes")<font color="red">*</font>#else&nbsp;#end</td>
     		<td><input type="checkbox" value="142"  $mode1 name="cbsemaks" $check2 $check2x id="cbsemaks2" onclick="checktxtREKOD()"></td>
     		<td>Bayaran Penyediaan rekod dijelaskan</td>
     	</tr>
	 </table>
	 
	 <table width="65%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr>
			<td width="10%">&nbsp;</td>
     		<td width="25%">No.Resit</td>
     		<td width="65%">:&nbsp;<input type="text" $Cmode $mode name="txtNoResit" id="txtNoResit" value="$!resit" size="25" maxlength="20" style="text-transform:uppercase;" onkeyup="checkitA()" onBlur="this.value=this.value.toUpperCase();checkitA()" /></td>
     	</tr>
     	<tr>
 			<td>&nbsp;</td>
     		<td>Tarikh Bayaran</td>
     		<td>:&nbsp;<input type="text" $Cmode $mode name="txdTarikhBayaran" id="txdTarikhBayaran" value="$!tarikhB" size="11" maxlength="10" onkeyup="checkitB()" onblur="validateTarikh(this,this.value);checkitB();check_date(this)" />
     		#if($editformSKR=="yes" || $editformSKR=="new")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBayaran',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
     	</tr>
	 </table>
	 
	 <table width="65%"  cellpadding="1" cellspacing="1" border="0">
     	<tr>
     		<td width="1%" valign="top">#if($editformSKR=="new" || $editformSKR=="yes")<font color="red">*</font>#else&nbsp;#end</td>
     		<td width="4%"><input type="checkbox" $mode1 value="143"  name="cbsemaks" $check3 $check3x id="cbsemaks3"></td>
     		<td width="95%">K3 dikembalikan dalam tempoh 14 hari</td>
     	</tr>
     </table>	
 </fieldset>  


 <fieldset>    
     <table width="65%"  cellpadding="1" cellspacing="1" border="0">
     	<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
     	<tr>
     		<td width="1%" valign="top">#if($editformSKR=="new" || $editformSKR=="yes")<font color="red">*</font>#else&nbsp;&nbsp;#end</td>
     		<td width="34%">Keputusan Permohonan</td>
     		<td width="1%">:</td>
     		<td width="64%"><input type="radio" $checkxA $checkA $mode1 id="sorKeputusanPegawai" name="sorKeputusanPegawai" value="164" onclick="onclickKeputusanPegawai()" >Diteruskan
     						&nbsp;&nbsp;<input $checkxB $checkB $mode1 type="radio" id="sorKeputusanPegawai" name="sorKeputusanPegawai" value="165" onclick="onclickKeputusanPegawai()" >Ditolak</td>
     	</tr>
     	
     	<tr>
        
 <td valign="top" >
 #if($editformSKR=="new" || $editformSKR=="yes") 
 #if($mandatory=="yes")
 <font color="red">*</font>
 #else
 &nbsp;
 #end 
 #else
 &nbsp;
 #end 
 </td>
 
     		<td valign="top">Catatan</td>
     		<td valign="top">:</td>
     		<td><textarea name="txtCatatanPegawai" id="txtCatatanPegawai" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" $Cmode $mode cols="55%" rows="7" onKeyUp="textCounter(this.form.txtCatatanPegawai,this.form.remLen1,2000);" onKeyDown="textCounter(this.form.txtCatatanPegawai,this.form.remLen1,2000);" >$!catatan</textarea></td>
     	</tr>
     	#if($editformSKR=="new" || $editformSKR=="yes")
    	<tr>
    		<td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="2000"></td>
        </tr> 
        #end
     </table>	
     #if($editformSKR=="new" || $editformSKR=="yes")
     <br/>
     <br/>
     <table width="65%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
    </table>
    #end
  </fieldset>   
 	

	<!-- 	#if($id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
	#if($id_status!="169")
	 <table width="100%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr align="center">
	 		<td>
	 		#if($viewformSKR=="yes")
	 	
	 			#if($editformSKR=="no" && ($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N"))	 		
	 			<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniKeputusanPegawai()" />
				#end
				
				#if($editformSKR=="yes")
				<input type="button" name="cmdUpdate" value="Simpan" onClick="updateKeputusanPegawai()" />
				<input type="button" name="cmdBatal" value="Batal" onClick="batalsemakKeputusanRayuan('$id_permohonan','$id_status')" />
				#end
					
	 		#end
	 		
	 		#if($viewformSKR=="no" && ($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N"))	 		
	 			<input type="button" name="cmdUpdate" value="Simpan" onClick="updateKeputusanPegawai()" />	 		
	 		#end
	 		
	 		</td>
	 	</tr>
	 </table>
	 #end
	 	
	 <table width="98%"  cellpadding="1" cellspacing="1" border="0">
  		<tr>
  			<td align="right">$!pagingKeputusanPegawai</td>
  		</tr>
	</table>

    </div>
<!-- END [ tabbed 1 ] -->    
 
 
#if($id_status=="164" || $id_status=="166" || $id_status=="167" || $id_status=="180") 

#if($newformMR=="no")
	#if($editformMR=="no")
		#set($readonlyM="readonly")
		#set($readonlyMC="class=disabled")
	#else
		#set($readonlyM="")
		#set($readonlyMC="")
	#end
#else
	#set($readonlyM="")
	#set($readonlyMC="")
#end

#if($newformMR=="no")
	#foreach($memo in $maklumatRayuan)
		#set($perkaraMemo = $memo.perkara_rayu_memorandum)
		#set($alasanMemo = $memo.alasan_rayuan_memorandum)
		#set($notaBicara = $memo.nota_bicara)
	#end
#else
	#foreach($memo in $maklumatRayuan)
	
		#set($perkaraMemo = "$memo.perkara_rayu_memorandum")
	#end
	#set($alasanMemo = "")
	#set($notaBicara = "")
#end


<!-- START [ tabbed 2 ] --> 
 	<div class="TabbedPanelsContent">
 		
 	<fieldset>
	<legend>MEMORANDUM RAYUAN</legend>
    <table width="95%"  cellpadding="1" cellspacing="2" border="0">
    	<tr>
    		<td valign="top">#if($editformMR=="yes" || $editformMR=="new")<font color="red">*</font>#else&nbsp;#end</td>
    		<td valign="top" colspan="3">Perkara yang dirayu :</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td colspan="3"><textarea $readonlyM $readonlyMC name="txtPerkaraRayu" cols="85%" rows="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkaraRayu,this.form.remLen1,2000);" onKeyDown="textCounter(this.form.txtPerkaraRayu,this.form.remLen1,2000);">$!perkaraMemo</textarea></td>
    	</tr>
    	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><b>Jumlah Perkataan :</b> $!totalWordPerkaraRayuMemo</td>
	 	</tr>
    	<script> 
            var area = new FCKeditor("txtPerkaraRayu");
	      	area.BasePath = '/${appname}/library/fck/' ;
	      	area.Height = 200;
	      	area.Width = 600;
			area.ReplaceTextarea();             	
        </script>
        
        #if($editformMR=="yes" || $editformMR=="new")	
    		<input type="hidden" name="contentoE" value="true">
    		<input type="hidden" name="designoE" value="on">   		
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
    	
    	<!-- <tr>
    		<td>&nbsp;</td>
    		<td colspan="3" valign="top"><input type="button" value="Papar" onclick="displayHTML(this.form.txtPerkaraRayu)">&nbsp;#if($editformMR=="yes" || $editformMR=="new")Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="2000">#end</td>
        </tr> 
         -->
    	<tr><td colspan="4">&nbsp;</td></tr>
    	
        <tr>
        	<td valign="top">#if($editformMR=="yes" || $editformMR=="new")<font color="red">*</font>#else&nbsp;#end</td>
    		<td valign="top">Alasan rayuan :</td>
    		<td valign="top">&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td colspan="3"><textarea $readonlyM $readonlyMC name="txtAlasanRayuan" cols="85%" rows="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtAlasanRayuan,this.form.remLen2,2000);" onKeyDown="textCounter(this.form.txtAlasanRayuan,this.form.remLen2,2000);">$!alasanMemo</textarea></td>
    	</tr>
    	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><b>Jumlah Perkataan :</b> $!totalWordAlasanRayuan</td>
	 	</tr>
	 	
    	<script> 
            var areaAlasan = new FCKeditor("txtAlasanRayuan");
            areaAlasan.BasePath = '/${appname}/library/fck/' ;
            areaAlasan.Height = 200;
            areaAlasan.Width = 600;
            areaAlasan.ReplaceTextarea();             	
        </script>
        
        <script>

			var contentoE = document.${formName}.contentoE.value;
			var designoE = document.${formName}.designoE.value;

    		var oEditorAlasan = FCKeditorAPI.GetInstance('txtAlasanRayuan');
    		function FCKeditor_OnComplete(oEditorAlasan)
    		{
    			oEditorAlasan.EditorDocument.body.contentEditable=contentoE;
    			oEditorAlasan.EditorDocument.designMode=designoE;
    			if(contentoE=="false"){
    				oEditorAlasan.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    			}
    		}
    	</script>
    	
    	<!-- Tambah Maklumat Nota Bicara Mula -->
    	<!-- 
    	<tr><td colspan="4">&nbsp;</td></tr>
    	
        <tr>
        	<td valign="top">#if($editformMR=="yes" || $editformMR=="new")<font color="red">*</font>#else&nbsp;#end</td>
    		<td valign="top">Maklumat Nota Bicara :</td>
    		<td valign="top">&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>&nbsp;</td>
    		<td colspan="3"><textarea $readonlyM $readonlyMC name="txtNotaBicara" cols="85%" rows="12" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtNotaBicara,this.form.remLen2,2000);" onKeyDown="textCounter(this.form.txtNotaBicara,this.form.remLen2,2000);">$!notaBicara</textarea></td>
    	</tr>
    	<tr>
	 		<td>&nbsp;</td>
	 		<td colspan="2"><b>Jumlah Perkataan :</b> $!totalWordNotaBicara</td>
	 	</tr>
	 	 -->
    	<script> 
           // var areaNotaBicara = new FCKeditor("txtNotaBicara");
          //  areaNotaBicara.BasePath = '/${appname}/library/fck/' ;
          //  areaNotaBicara.Height = 200;
          //  areaNotaBicara.Width = 600;
          //  areaNotaBicara.ReplaceTextarea();             	
        </script>
        
        <script>

			//var contentoE = document.${formName}.contentoE.value;
			//var designoE = document.${formName}.designoE.value;

    		//var oEditorNotaBicara = FCKeditorAPI.GetInstance('txtNotaBicara');
    		//function FCKeditor_OnComplete(oEditorNotaBicara)
    		//{
    		//	oEditorNotaBicara.EditorDocument.body.contentEditable=contentoE;
    		//	oEditorNotaBicara.EditorDocument.designMode=designoE;
    		//	if(contentoE=="false"){
    		//		oEditorNotaBicara.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';	
    		//	}
    	//	}
    	</script>
    	
    	<!-- Tambah Maklumat Nota Bicara Akhir -->
    	
    	<!-- <tr>
    		<td>&nbsp;</td>
            <td colspan="3" valign="top"><input type="button" value="Papar" onclick="displayHTML(this.form.txtAlasanRayuan)">&nbsp;#if($editformMR=="yes" || $editformMR=="new")Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen2" size="3" maxlength="3" value="2000">#end</td>
        </tr> -->
    	
    </table>
    
 	</fieldset>
 	
 	<!-- #if($id_status!="166" && $id_status!="167" && $id_status!="180") #end-->
 	#if($id_status!="169")
 	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
   		<tr align="center">
   			<td>
   			
   				#if($newformMR=="yes")
   					<input type="button" name="cmdSimpan" value="Simpan" onClick="updateMemorandum()" />
   				#else
   					
   				  #if($editformMR=="no")	
   					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMemorandum()" />
   				  #else	
					<input type="button" name="cmdSimpan" value="Simpan" onClick="updateMemorandum()" />
					<input type="button" name="cmdBatal" value="Batal" onClick="batalMemo('$id_permohonan','$id_status')" />
				  #end
				  	
				#end
   				
			</td>
   		</tr>
    </table>
 	#end
 	
 	<table width="98%"  cellpadding="1" cellspacing="1" border="0">
  		<tr>
  			<td align="right">$!pagingMemorandumRayuan</td>
  		</tr>
	</table>
 	
 	</div>
<!-- END [ tabbed 2 ] -->  



<!-- START [ tabbed 3 ] -->      
    <div class="TabbedPanelsContent">

#if($onchange=="no")    
#if($size_P!=0)
	#foreach($p in $maklumatSerahanPenasihat)
		#set($id_serahanrayuanP = $p.id_serahanrayuan)
		#set($tarikhP=$p.tarikh_serahan)
		#set($namaP=$p.nama_penerima)
		#set($alamatP1=$p.alamat1)
		#set($alamatP2=$p.alamat2)
		#set($alamatP3=$p.alamat3)
		#set($poskodP=$p.poskod)
		#set($bandarP=$p.bandar)
	#end    
#else
		#set($id_serahanrayuanP = "")
		#set($tarikhP="")
		#set($namaP="")
		#set($alamatP1="")
		#set($alamatP2="")
		#set($alamatP3="")
		#set($poskodP="")
		#set($bandarP="")
#end	


#if($size_M!=0)
	#foreach($m in $maklumatSerahanMahkamah)
		#set($id_serahanrayuanM = $m.id_serahanrayuan)
		#set($id_mahkamahx = $m.id_mahkamah)
		#set($tarikhM=$m.tarikh_serahan)
		#set($namaM=$m.nama_penerima)
		#set($alamatM1=$m.alamat1)
		#set($alamatM2=$m.alamat2)
		#set($alamatM3=$m.alamat3)
		#set($poskodM=$m.poskod)
		#set($bandarM=$m.bandar)
	#end    
#else
		#set($id_serahanrayuanM = "")
		#set($id_mahkamahx = "")
		#set($tarikhM="")
		#set($namaM="")
		#set($alamatM1="")
		#set($alamatM2="")
		#set($alamatM3="")
		#set($poskodM="")
		#set($bandarM="")
#end	
#end
    
#if($editformSerahank1=="yes")
	#set($allmode = "")
	#set($allmode1 = "")
	#set($Callmode = "")
#elseif($editformSerahank1=="no")
	#set($allmode = "readonly")
	#set($allmode1 = "disabled")
	#set($Callmode = "class=disabled")
#elseif($editformSerahank1=="new")
	#set($allmode = "")	
	#set($allmode1 = "")
	#set($Callmode = "")
#end 
    
#foreach($listMK in $listMahkamah)
	<input type="hidden" name="id_" value="">
#end    

<input type="hidden" name="nama_M" value="$namaM">
<input type="hidden" name="id_serahanrayuanP" value="$id_serahanrayuanP">
<input type="hidden" name="id_serahanrayuanM" value="$id_serahanrayuanM">


    <fieldset>
    	<legend><strong>MAKLUMAT PENASIHAT UNDANG-UNDANG</strong></legend>
    	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
    		<tr>
    			<td width="1%" valign="top">#if($editformSerahank1=="new" || $editformSerahank1=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    			<td width="20%">Tarikh Serahan</td>
    			<td width="1%">:</td>
    			<td width="78%"><input type="text" $Callmode $allmode name="txdTarikhSerahanPenasihat" id="txdTarikhSerahanPenasihat" value="$!tarikhP" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)" />
    			#if($editformSerahank1=="yes" || $editformSerahank1=="new")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahanPenasihat',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyyy</i></font>#end </td>
    		</tr>
    		<tr>
    			<td valign="top">#if($editformSerahank1=="new" || $editformSerahank1=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    			<td>Penasihat Undang-Undang</td>
    			<td>:</td>
    			<!-- <td><input type="text" $Callmode $allmode name="txtNamaPenasihat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="txtNamaPenasihat" value="$!namaP" size="40" maxlength="" /></td> -->
    			<td>$!selectPejabatPenasihat</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatPenasihat1" id="txtAlamatPenasihat1" value="$!alamatP1" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatPenasihat2" id="txtAlamatPenasihat2" value="$!alamatP2" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatPenasihat3" id="txtAlamatPenasihat3" value="$!alamatP3" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input type="text" $Callmode $allmode name="txtPoskodPenasihat" id="txtPoskodPenasihat" value="$!poskodP" size="6" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeriPenasihat</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandarPenasihat</td>
    		</tr>
    	</table>
    </fieldset>
<br/>
	 <fieldset>
    	<legend><strong>MAKLUMAT MAHKAMAH</strong></legend>
     	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
    		<tr>
    			<td width="1%" valign="top">#if($editformSerahank1=="new" || $editformSerahank1=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    			<td width="20%">Tarikh Serahan</td>
    			<td width="1%">:</td>
    			<td width="79%"><input type="text" $Callmode $allmode name="txdTarikhSerahanMahkamah" id="txdTarikhSerahanMahkamah" value="$!tarikhM" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)" />
    			#if($editformSerahank1=="yes" || $editformSerahank1=="new")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahanMahkamah',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyyy</i></font>#end </td>
    		</tr>
    		<tr>
    			<td valign="top">#if($editformSerahank1=="new" || $editformSerahank1=="yes")<font color="red">*</font>#else&nbsp;#end</td>
    			<td>Mahkamah</td>
    			<td>:</td>
    		#if($saiz_listM!=0)   
    			<td><select id="socMahkamah" name="socMahkamah" $Callmode $allmode1 onchange="changeGetAlamatMahkamah()" style="width:300">	
  					
  							#if($onchange=="no")
  								#if($id_mahkamahx!="")
  								<option value="$id_mahkamahx">$namaM.toUpperCase()</option>  	
  								<option value="">SILA PILIH</option>
  								#else	
                    			<option value="">SILA PILIH</option>
                    			#end
                    		#end
                    		
                    		#if($onchange=="yes")
                    			#if($id_pejabatCH!="")
                    		<option value="$id_pejabatCH">$C_namaM.toUpperCase()</option>
                    		<option value="">SILA PILIH</option>
                    			#else
                    		<option value="">SILA PILIH</option>	
                    			#end                 			
                    		#end
                 
                #if($editformSerahank1=="no" ||$editformSerahank1=="new")
                		
                		#foreach($listMK in $listMahkamah)
                    			#if($listMK.id_pejabat != $id_pejabatCH )
                    		<option value="$listMK.id_pejabat">$listMK.nama_pejabat.toUpperCase()</option>
                            	#end
                        #end
                        
                #else 		
                    	#if($onchange=="no")
                    		#foreach($listMK in $listMahkamah)
                    			#set($zw = $listMK.id_pejabat)
                    			#if($listMK.id_pejabat != $id_mahkamahx )
                    		<option value="$listMK.id_pejabat">$listMK.nama_pejabat.toUpperCase()</option>
                            	#end
                            #end 
                        #else
                        	#foreach($listMK in $listMahkamah)
                    			#if($listMK.id_pejabat != $id_pejabatCH )
                    		<option value="$listMK.id_pejabat">$listMK.nama_pejabat.toUpperCase()</option>
                            	#end
                            #end 
                        #end
                #end
                            		
					</select></td>
			#else	
    			<td><input type="text" name="txtMahkamah" id="txtMahkamah" $Callmode $allmode value="$!namaM" size="40" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> 
    		#end
    			
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatMahkamah1" id="txtAlamatMahkamah1" value="$!alamatM1" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatMahkamah2" id="txtAlamatMahkamah2" value="$!alamatM2" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" $Callmode $allmode name="txtAlamatMahkamah3" id="txtAlamatMahkamah3" value="$!alamatM3" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input type="text" $Callmode $allmode name="txtPoskodMahkamah" id="txtPoskodMahkamah" value="$!poskodM" size="6" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeriMahkamah</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandarMahkamah</td>
    		</tr>
    		<tr><td>&nbsp;</td></tr>
    	</table>
    
    #if($editformSerahank1=="new" || $editformSerahank1=="yes")	
    <br/>
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	#if($saiz_listM!=0)  
        	<td>$!perhatian</td>
        	#else
        	<td>$!perhatian2</td>
        	#end
        	</tr>
    </table>
    #end 
    
     </fieldset>	
     
     <!-- #if($id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
 	#if($id_status!="169")
      <table width="100%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr align="center">
	 		<td>
	 		
	 		#if($viewformSerahank1=="no")
	 		<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanMaklumatSerahanK1()" />
			#end
	 		
	 		#if($viewformSerahank1=="yes")
	 			#if($editformSerahank1=="no")
	 		<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniMaklumatSerahan()" />
	 		<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
				#end
				#if($editformSerahank1=="yes")
			<input type="button" name="cmdUpdate" value="Simpan" onClick="updateMaklumatSerahan()" />
			<input type="button" name="cmdBatal" value="Batal" onClick="batalk1('$id_permohonan','$id_status')" />
				#end
			#end
			
	 		</td>
	 	</tr>
	  </table>
	  #else
	 <table width="100%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr align="center">
	 		<td>
	 		<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
	 		</td>
	 	</tr>
	 </table>
	  #end

	<table width="98%"  cellpadding="1" cellspacing="1" border="0">
  		<tr>
  			<td align="right">$!pagingRekodRayuan</td>
  		</tr>
	</table>
	
<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBuktiPenyampaianPenasihat('$id_fail')"><font color="blue">Bukti Penyampaian (Penasihat undang - undang)</font></a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBuktiPenyampaianMahkamah('$id_fail')"><font color="blue">Bukti Penyampaian (Mahkamah)</font></a></td>
      </tr>   
     </table>   
</fieldset> 

    </div>
<!-- END [ tabbed 3 ] -->    
#end    



<!-- START [ tabbed 4 ] -->
       
#if(($id_status=="164" || $id_status=="166" || $id_status=="167" || $id_status=="180") && ($maklumatSerahanPenasihat.size()!=0 && $maklumatSerahanMahkamah.size()!=0))      

    <div class="TabbedPanelsContent">

#if($editformMK=="no")
	#set($modeMK = "disabled")
	#set($modeMK1 = "readonly")
	#set($modeMK1Class = "class=disabled")
#elseif($editformMK=="yes")
	#set($modeMK = "")
	#set($modeMK1 = "")
	#set($modeMK1Class = "")
#elseif($editformMK=="new")
	#set($modeMK = "")	
	#set($modeMK1 = "")
	#set($modeMK1Class = "")
#end     


#if($viewformMK=="yes")   

	#if($onchangeKM=="no")
		#foreach($mk in $maklumatRayuan)
		#set($catatanMahkamah = $mk.catatan)

			#if($mk.id_keputusanmahkamah=="166")
				#set($checkAM = "checked")
				#set($checkBM = "")
				#set($checkCM = "checked")
				#set($checkDM = "")
			#elseif($mk.id_keputusanmahkamah=="167")
				#set($checkAM = "")
				#set($checkBM = "checked")
				#set($checkCM = "")
				#set($checkDM = "")
			#elseif($mk.id_keputusanmahkamah=="180")
				#set($checkAM = "checked")
				#set($checkBM = "")
				#set($checkCM = "")
				#set($checkDM = "checked")
			#end	
			
		#end 
	#else
		#set($catatanMahkamah="$!catatanM")
		#set($checkAM="$checkx1")	
		#set($checkBM="$checkx2")
		#set($checkCM = "")
		#set($checkDM = "")
	#end
	
#else

	#if($onchangeKM=="no")
		#set($catatanMahkamah="")	
		#set($checkAM="")	
		#set($checkBM="")	
		#set($checkCM = "")
		#set($checkDM = "")
	#else
		#set($catatanMahkamah="$!catatanM")	
		#set($checkAM="$checkx1")	
		#set($checkBM="$checkx2")
		#set($checkCM = "")
		#set($checkDM = "")
	#end	
	
#end


  <fieldset>   
  <legend>KEPUTUSAN MAHKAMAH</legend>
     <table width="100%"  cellpadding="1" cellspacing="1" border="0">
     	<tr>
     		<td width="19%">&nbsp;</td>
     		<td width="1%">#if($editformMK=="yes" || $editformMK=="new")<font color="red">*</font>#else&nbsp;#end</td>
     		<td width="15%">Keputusan</td>
     		<td width="1%">:&nbsp;</td>
     		<td width="64%"><input type="radio" $modeMK name="sorKeputusan"  $checkAM value="166" id="diterima" onclick="onclickKeputusanMahkamah()"/>Diterima</td>
     	</tr>
     	#if($showSubTerima=="yes")
     	<tr>
     		<td colspan="4">&nbsp;</td>
     		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sorTerima" $modeMK $checkCM value="166" id="bicaraSemula" />Perbicaraan semula&nbsp;
     			<input type="radio" name="sorTerima" $modeMK $checkDM value="180" id="lainPerintah" />Lain - lain perintah</td>
     	</tr>
     	#end
     	<tr>
     		<td colspan="4">&nbsp;</td>
     		<td><input type="radio" $modeMK name="sorKeputusan"  $checkBM value="167" id="ditolak"  onclick="onclickKeputusanMahkamah()"/>Ditolak</td>
     	</tr>
     	
     	<tr>
     		<td>&nbsp;</td>
     		<td>&nbsp;</td>
     		<td valign="top">Catatan</td>
     		<td valign="top">:&nbsp;</td>
     		<td><textarea name="txtCatatanMahkamah" $modeMK1 $modeMK1Class cols="50%" rows="6" id="txtCatatanMahkamah" style="text-transform:uppercase;" onKeyUp="textCounter(this.form.txtCatatanMahkamah,this.form.remLen2,2000);" onKeyDown="textCounter(this.form.txtCatatanMahkamah,this.form.remLen2,2000);" onBlur="this.value=this.value.toUpperCase();" />$!catatanMahkamah</textarea></td>
     	</tr>
     	#if($editformMK=="yes" || $editformMK=="new")
     	<tr>
     		<td valign="top" colspan="4">&nbsp;</td>
    		<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen2" size="3" maxlength="3" value="2000"></td>
        </tr> 
        #end
        #if($editformMK=="yes" || $editformMK=="new")
     	<tr>
     		<td>&nbsp;</td>
     		<td>&nbsp;</td>
     		<td valign="top">Muatnaik Keputusan Mahkamah</td>
     		<td valign="top">:&nbsp;</td>
     		<td><input id="fileupload" name="fileupload" type="file" size="40"></td>
     	</tr>
     	#else
     	<tr>
     		<td>&nbsp;</td>
     		<td>&nbsp;</td>
     		<td valign="top"></td>
     		<td valign="top"></td>
     		<td><input name="cetak" type="button" value="Muat turun Keputusan Mahkamah" onclick="doOpen($id_rayuan)" /></td>
     	</tr>
     	#end	
     	<tr><td>&nbsp;</td></tr>
     	
     	
     </table>
     
     #if($editformMK=="yes" || $editformMK=="new")
     <table width="100%"  cellpadding="1" cellspacing="1" border="0">
       	<tr><td colspan="2">&nbsp;</td></tr>
        <tr>
        	<td width="19%">&nbsp;</td>
			<td width="81%">$!perhatian1</td>
        </tr>
     </table>
     #end
    
  </fieldset>  	
  
   	 <table width="100%"  cellpadding="1" cellspacing="1" border="0">
	 	<tr align="center">
	 	
	 	
	 		<td width="57%" align="right">
	 		#if($viewformMK=="yes")  
	 			#if($editformMK=="no")  
	 				<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniKeputusanMahkamah()" />
	 				#if($id_status=="166")
					<!--<input type="button" name="cmdNotis" value="Notis Perbicaraan" onClick="goNotis('$id_permohonan','$id_status')" />-->
<!--
	::: 
    $!headerppk.ID_FAIL
    $!headerppk.NO_FAIL
    $id_permohonan
    $!catatanMahkamah -->
    
    <input type="button" name="cmdNotis" value="Notis Perbicaraan" onClick="cmdSimpan_flag('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL','$id_permohonan','','1','$!catatanMahkamah')" />
    
                    #end
	 			#end
	 			#if($editformMK=="yes")  
	 				<input type="button" name="cmdSimpan" value="Simpan" onClick="updateKeputusanMahkamah()" />
					<input type="button" name="cmdBatal" value="Batal" onClick="batalKemaskiniKeputusanMahkamah('$id_permohonan','$id_status')" />
				#end
	
	 		#end
	 		
	 		#if($viewformMK=="no")
	 			<input type="button" name="cmdSimpan" value="Simpan" onClick="updateKeputusanMahkamah()" />
			#end  
			
			</td>
	 		<td width="43%" align="right">
	 		$!pagingKeputusanMahkamah
	 		</td>
	 	</tr>
	 </table>
	
    </div>
    
#end      
<!-- END [ tabbed 4 ]  -->

  </div>
</div>

<table width="100%" border="0">
<tr>
	<td >
	<div align="right">
	<span>
	
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:xxx()" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
	#elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
 	#elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
	#elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow<a href="javascript:permohonanSenaraiSemak('$id_permohonan','$id_status')" >$!no2enable</a>$!arrow<a href="javascript:permohonanRayuan('$id_permohonan','$id_status')" >$!no3enable</a>$!arrow$!no4current 
    #end
	
    </span> 
    </div>
   	</td>
</tr>
</table>

<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/> 
<!-- <input type="hidden" name="command" /> -->
<input type="hidden" name="command2" />
<input type="hidden" name="command3" />
<input type="hidden" name="command4" />
<input type="hidden" name="onchange" />
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_status" value="$id_status" />
<input type="hidden" name="id_pemohon" value="$id_pemohon" />
<input type="hidden" name="id_rayuan" value="$id_rayuan" />
<input type="hidden" name="id_fail" value="$id_fail" />
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail" />
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input type="hidden" name="mandatory" id="mandatory" value="$mandatory" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input name="flagForView" type="hidden" id="flagForView" value="$flagForView"/>



<input type="hidden" name="id_permohonan_kebenaran" />
<input type="hidden" name="user_id_kebenaran_edit" />
<input type="hidden" name="flag_kebenaran_edit" />
<input type="hidden" name="catatan_batal" />

  
#if($saiz_listM!=0)
<input type="hidden" name="validateM" value="0"/>	
#else
<input type="hidden" name="validateM" value="1"/>		
#end		
		
</center>

#parse("app/ppk/headerppk_script.jsp")

<script>
function onclickKeputusanPegawai() {

	var checkbox1 = document.getElementById("cbsemaks1").checked;
	var checkbox2 = document.getElementById("cbsemaks2").checked;
	var checkbox3 = document.getElementById("cbsemaks3").checked;
	
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&cb1="+checkbox1+"&cb2="+checkbox2+"&cb3="+checkbox3;
	document.${formName}.command.value = "semakKeputusanRayuan";
	document.${formName}.onchange.value = "onclickKeputusanPegawai";
	document.${formName}.submit();
	
}
function checkitA()
{
	if(document.${formName}.txtNoResit.value != "")
	{ 
    document.${formName}.cbsemaks[1].checked = true;
	}
		
	if(document.${formName}.txtNoResit.value == "")
	{
		if(document.${formName}.txdTarikhBayaran.value == "")
		{
    	document.${formName}.cbsemaks[1].checked = false;
		}
		if(document.${formName}.txdTarikhBayaran.value != "")
		{
	    document.${formName}.cbsemaks[1].checked = true;
		}
	}
}
function checkitB()
{
	if(document.${formName}.txdTarikhBayaran.value != "")
	{ 
    document.${formName}.cbsemaks[1].checked = true;
	}
	
	if(document.${formName}.txdTarikhBayaran.value == "")
	{
		if(document.${formName}.txtNoResit.value == "")
		{
		document.${formName}.cbsemaks[1].checked = false;
		}
		if(document.${formName}.txtNoResit.value != "")
		{
		document.${formName}.cbsemaks[1].checked = true;
		}
    }
}
function checktxtREKOD()
{
	var checkbox2 = document.getElementById("cbsemaks2").checked;

	if (checkbox2 == false)
	{
		document.getElementById("txtNoResit").value = "" ;
		document.getElementById("txdTarikhBayaran").value = "" ;
	}
}
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}
function xxx() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.command.value = "";
	document.${formName}.submit();
}
function simpanMaklumatSerahanK1() {

	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	
	var tsp=document.${formName}.txdTarikhSerahanPenasihat
	var tsm=document.${formName}.txdTarikhSerahanMahkamah

	var str1  = document.getElementById("txdTarikhSerahanPenasihat").value;
	var str2  = document.getElementById("txdTarikhSerahanMahkamah").value;
	var str3  = document.getElementById("tarikhMohon").value;
	
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);
   	
   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
   	
   	var date1 = new Date(yr1, mon1, dt1);
	var date2 = new Date(yr2, mon2, dt2);
   	var trMOHON = new Date(yr3, mon3, dt3);
   	var currentDate = new Date(year, month, day);
   	
	if(document.${formName}.txdTarikhSerahanPenasihat.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" pada maklumat penasihat");
		document.${formName}.txdTarikhSerahanPenasihat.focus();  
		return;
	}
	else if (isDate(tsp.value)==false){
		tsp.focus()
		return;
	}
	else if(date1 < trMOHON){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahanPenasihat.focus();
	 	return;
	} 
/*	else if(date1 > currentDate){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.txdTarikhSerahanPenasihat.focus();
	 	return;
	} 
*/	else if(document.${formName}.txtNamaPenasihat.value == "")
	{
		alert("Sila masukkan \"Penasihat\" pada maklumat penasihat");
		document.${formName}.txtNamaPenasihat.focus();  
		return;
	}
	else if(document.${formName}.txdTarikhSerahanMahkamah.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" pada maklumat mahkamah");
		document.${formName}.txdTarikhSerahanMahkamah.focus();  
		return;
	}
	else if (isDate(tsm.value)==false){
		tsm.focus()
		return;
	} 
	else if(date2 < trMOHON){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahanMahkamah.focus();
	 	return;
	} 
	else if (document.${formName}.txtPoskodPenasihat.value != "" && document.${formName}.txtPoskodPenasihat.value.length < 5) {
		alert("Sila masukkan nombor poskod penasihat dengan selengkapnya");
		document.${formName}.txtPoskodPenasihat.focus();
	}
/*	else if(date2 < currentDate){
   		alert("Sila pastikan \"Tarikh Serahan\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.txdTarikhSerahanMahkamah.focus();
	 	return;
	} 
*/	else if(document.${formName}.validateM.value == 0 && document.${formName}.socMahkamah.value == ""){
		alert("Sila pilih \"Mahkamah\" pada maklumat mahkamah");
		document.${formName}.socMahkamah.focus();  
		return;
	}
	else if(document.${formName}.validateM.value == 1 && document.${formName}.txtMahkamah.value == ""){
		alert("Sila masukkan \"Mahkamah\" pada maklumat mahkamah");
		document.${formName}.txtMahkamah.focus();  
		return;
	}
	else if (document.${formName}.txtPoskodMahkamah.value != "" && document.${formName}.txtPoskodMahkamah.value.length < 5) {
		alert("Sila masukkan nombor poskod mahkamah dengan selengkapnya");
		document.${formName}.txtPoskodMahkamah.focus();
	}
	else
	{ 
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
		document.${formName}.command.value = "simpanMaklumatSerahanK1";
		document.${formName}.submit();
	}
}
function batalk1(id_permohonan,id_status) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "maklumatSerahanK1";
	document.${formName}.submit();
}
function kemaskiniMaklumatSerahan() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "maklumatSerahanK1";
	document.${formName}.command3.value = "kemaskiniMaklumatSerahan";
	document.${formName}.submit();
}
function kemaskiniKeputusanMahkamah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "keputusanMahkamah";
	document.${formName}.command3.value = "kemaskiniKeputusanMahkamah";
	document.${formName}.submit();
}
function kemaskiniKeputusanPegawai() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "semakKeputusanRayuan";
	document.${formName}.command3.value = "kemaskiniKeputusanPegawai";
	document.${formName}.submit();
}
function updateMaklumatSerahan() {

	var tsp=document.${formName}.txdTarikhSerahanPenasihat
	var tsm=document.${formName}.txdTarikhSerahanMahkamah

	var str1  = document.getElementById("txdTarikhSerahanPenasihat").value;
	var str2  = document.getElementById("txdTarikhSerahanMahkamah").value;
	var str3  = document.getElementById("tarikhMohon").value;
	
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);
   	
   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
   	
   	var date1 = new Date(yr1, mon1, dt1);
	var date2 = new Date(yr2, mon2, dt2);
   	var trMOHON = new Date(yr3, mon3, dt3);
	
	if(document.${formName}.txdTarikhSerahanPenasihat.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" pada maklumat penasihat");
		document.${formName}.txdTarikhSerahanPenasihat.focus();  
		return;
	}
	else if (isDate(tsp.value)==false){
		tsp.focus()
		return;
	}
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh serahan tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahanPenasihat.focus();
	 	return;
	} 
	else if(document.${formName}.txtNamaPenasihat.value == "")
	{
		alert("Sila masukkan \"Penasihat\" pada maklumat penasihat");
		document.${formName}.txtNamaPenasihat.focus();  
		return;
	}
	else if (document.${formName}.txtPoskodPenasihat.value != "" && document.${formName}.txtPoskodPenasihat.value.length < 5) {
		alert("Sila masukkan nombor poskod penasihat dengan selengkapnya");
		document.${formName}.txtPoskodPenasihat.focus();
	}
	else if(document.${formName}.txdTarikhSerahanMahkamah.value == "")
	{
		alert("Sila masukkan \"Tarikh Serahan\" pada maklumat mahkamah");
		document.${formName}.txdTarikhSerahanMahkamah.focus();  
		return;
	}
	else if (isDate(tsm.value)==false){
		tsm.focus()
		return;
	}
	else if(date2 < trMOHON){
   		alert("Sila pastikan tarikh serahan tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahanMahkamah.focus();
	 	return;
	} 
	else if(document.${formName}.validateM.value == 0 && document.${formName}.socMahkamah.value == ""){
		alert("Sila pilih \"Mahkamah\" pada maklumat mahkamah");
		document.${formName}.socMahkamah.focus();  
		return;
	}
	else if(document.${formName}.validateM.value == 1 && document.${formName}.txtMahkamah.value == ""){
		alert("Sila masukkan \"Mahkamah\" pada maklumat mahkamah");
		document.${formName}.txtMahkamah.focus();  
		return;
	}
	else if (document.${formName}.txtPoskodMahkamah.value != "" && document.${formName}.txtPoskodMahkamah.value.length < 5) {
		alert("Sila masukkan nombor poskod mahkamah dengan selengkapnya");
		document.${formName}.txtPoskodMahkamah.focus();
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
		document.${formName}.command.value = "maklumatSerahanK1";
		document.${formName}.command3.value = "kemaskiniMaklumatSerahan";
		document.${formName}.command4.value = "updateMaklumatSerahan";
		document.${formName}.submit();
	}
}
function updateKeputusanMahkamah() {
	/* in case upload document wajib diisi
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".pdf", ".png"];
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	*/
	var sor1 = document.getElementById("diterima").checked;
	var sor2 = document.getElementById("ditolak").checked;

	var radioSelected = false;
	
	for (i = 0;  i < ${formName}.sorKeputusan.length;  i++){
		if (${formName}.sorKeputusan[i].checked)
		radioSelected = true;
	}
	
	if(sor1==true){
		var sor1a = document.getElementById("bicaraSemula").checked;
		var sor1b = document.getElementById("lainPerintah").checked;

		var radioTerima = false;
		for (i = 0;  i < ${formName}.sorTerima.length;  i++){
			if (${formName}.sorTerima[i].checked)
			radioTerima = true;
		}
	}

	if(!radioSelected)
	{
		alert("Sila pilih \"Keputusan\" terlebih dahulu.");
		return (false);
	}
	else
	if((sor1==true) && (!radioTerima))
	{
		alert("Sila pilih \"Perbicaraan semula\" atau \"Lain - lain perintah\" terlebih dahulu.");
		return (false);	
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var txtCatatanMahkamah2 = document.${formName}.txtCatatanMahkamah.value;
		var sorKeputusan = document.${formName}.sorKeputusan.value;
		var sorTerima = document.${formName}.sorTerima.value;
		var id_permohonan2 = document.${formName}.id_permohonan.value;
		var id_fail2 = document.${formName}.id_fail.value;
		
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=keputusanMahkamah&command2=updateKeputusanMahkamah&id_status="+$id_status+"&id_rayuan="+$id_rayuan+"&txtCatatanMahkamah="+txtCatatanMahkamah2+"&sorKeputusan="+sorKeputusan+"&sorTerima="+sorTerima+"&id_permohonan="+id_permohonan2+"&id_fail="+id_fail2;
		document.${formName}.submit();
	}
}
function updateKeputusanPegawai() {

	var mandatory = document.getElementById("mandatory").value;
	var tb=document.${formName}.txdTarikhBayaran

	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	
	var str1  = document.getElementById("txdTarikhBayaran").value;
	var str3  = document.getElementById("tarikhMohon").value;
	
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
   	
   	var date1 = new Date(yr1, mon1, dt1);
   	var trMOHON = new Date(yr3, mon3, dt3);
	var currentDate = new Date(year, month, day);
	
	var checkbox1 = document.getElementById('cbsemaks1');
	var checkbox2 = document.getElementById('cbsemaks2');
	var checkbox3 = document.getElementById('cbsemaks3');

	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorKeputusanPegawai.length;  i++){
	if (${formName}.sorKeputusanPegawai[i].checked)
	radioSelected = true;
	}
	
	if (checkbox1.checked == false)
	{
		alert("Borang K1 hendaklah diisi dengan sempurna terlebih dahulu");
		checkbox1.focus(); 
		return;
	}
	else if (checkbox2.checked == false)
	{
		alert("Bayaran Penyediaan rekod hendaklah dijelaskan terlebih dahulu");
		checkbox2.focus(); 
		return;
	}
	else if(checkbox2.checked == true && document.${formName}.txtNoResit.value == "")
	{
		alert("Sila masukkan \"No Resit\" bayaran penyediaan rekod");
		document.${formName}.txtNoResit.focus();  
		return;
	}
	else if(checkbox2.checked == true && document.${formName}.txdTarikhBayaran.value == "")
	{
		alert("Sila masukkan \"Tarikh Bayaran\" penyediaan rekod");
		document.${formName}.txdTarikhBayaran.focus();  
		return;
	}
	else if (isDate(tb.value)==false){
		tb.focus()
		return;
	}
	else if(date1 < trMOHON){
   		alert("Sila pastikan \"Tarikh Bayaran\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhBayaran.focus();
	 	return;
	} 
	else if(date1 > currentDate){
   		alert("Sila pastikan \"Tarikh Bayaran\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhBayaran.focus();
	 	return;
	} 
	else if (checkbox3.checked == false)
	{
		alert("Borang K1 hendaklah dikembalikan dalam tempoh 14 hari");
		checkbox3.focus(); 
		return;
	}
	else if (!radioSelected)
	{
		alert("Sila pilih \"Keputusan Permohonan\" terlebih dahulu.");
		return (false);
	}
	else if(mandatory=="yes" && (document.${formName}.txtCatatanPegawai.value == ""))
	{
		alert("Sila masukkan \"Catatan\" keputusan pegawai");
		document.${formName}.txtCatatanPegawai.focus();  
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
		document.${formName}.command.value = "semakKeputusanRayuan";
		document.${formName}.command2.value = "updateKeputusanPegawai";
		document.${formName}.submit();
	}
}
function batalKemaskiniKeputusanMahkamah(id_permohonan,id_status) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "keputusanMahkamah";
	document.${formName}.submit();
}
function batalsemakKeputusanRayuan(id_permohonan,id_status) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "semakKeputusanRayuan";
	document.${formName}.submit();
}
function keputusanPegawai(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "semakKeputusanRayuan";
	document.${formName}.submit();
}
function changeGetAlamatMahkamah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "maklumatSerahanK1";
	document.${formName}.command2.value = "changeGetAlamatMahkamah";
	document.${formName}.submit();
}
function onchangeBandarByNegeri() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "onchangeBandarByNegeri";
	document.${formName}.submit();
}
function keputusanMahkamah(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "keputusanMahkamah";
	document.${formName}.submit();
}
function maklumatSerahanK1(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "maklumatSerahanK1";
	document.${formName}.submit();
}
function memorandumRayuan(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "memorandumRayuan";
	document.${formName}.submit();
}
function batalMemo(id_permohonan,id_status) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "memorandumRayuan";
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
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function permohonanSenaraiSemak(id_permohonan,id_status) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
	//document.${formName}.command.value = "semakRayuan";
	document.${formName}.submit();
}
function permohonanRayuan(id_permohonan,id_status) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	//document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=maklumatRayuan&tabId=0";
	document.${formName}.submit();
}
function displayHTML(field) 
{
	var inf = field.value;
	win = window.open('', 'popup', 'resizable=yes,scrollbars=yes,toolbar=no,status=no');
	win.document.write("" + inf + "");
}
function onclickKeputusanMahkamah() 
{
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value="keputusanMahkamah";
	document.${formName}.command2.value="onclickKeputusanMahkamah";
	document.${formName}.submit();
}
function updateMemorandum() {


	var txtPerkaraRayu = null; 
	var oEditor = FCKeditorAPI.GetInstance('txtPerkaraRayu') ;
	if(oEditor) { 
		txtPerkaraRayu = oEditor.GetXHTML(true); 
	}
	var txtAlasanRayuan = null; 
	var oEditorAlasan = FCKeditorAPI.GetInstance('txtAlasanRayuan') ;
	if(oEditorAlasan) { 
		txtAlasanRayuan = oEditorAlasan.GetXHTML(true); 
	}

	if(txtPerkaraRayu == "" || txtPerkaraRayu == null)
	{
		alert("Sila masukkan \"Perkara yang dirayu\" terlebih dahulu");
		document.${formName}.txtPerkaraRayu.focus();  
		return;
	}
	else if(txtAlasanRayuan == "" || txtAlasanRayuan == null)
	{
		alert("Sila masukkan \"Alasan rayuan\" terlebih dahulu");
		document.${formName}.txtAlasanRayuan.focus();  
		return;
	}

	
/*	
	if(document.${formName}.txtPerkaraRayu.value =="")
	{
		alert("Sila masukkan \"Perkara yang dirayu\" terlebih dahulu");
		document.${formName}.txtPerkaraRayu.focus();  
		return;
	}
	else if(document.${formName}.txtAlasanRayuan.value =="")
	{
		alert("Sila masukkan \"Alasan rayuan\" terlebih dahulu");
		document.${formName}.txtAlasanRayuan.focus();  
		return;
	}
*/	
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
		document.${formName}.command.value = "memorandumRayuan";
		document.${formName}.command3.value = "updateMemorandum";
		document.${formName}.submit();
	}
}
function kemaskiniMemorandum() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "memorandumRayuan";
	document.${formName}.command2.value = "kemaskiniMemorandum";
	document.${formName}.submit();
}
function goNotis(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function cmdSimpan_flag(id_fail_carian,txtNoFailSub,id_permohonan_kebenaran,user_id_kebenaran_edit,flag_kebenaran_edit,catatan_batal)
{
	/*alert("id_fail_carian :"+id_fail_carian);
	alert("txtNoFailSub :"+txtNoFailSub);
	alert("id_permohonan_kebenaran :"+id_permohonan_kebenaran);
	alert("user_id_kebenaran_edit :"+user_id_kebenaran_edit);
	alert("flag_kebenaran_edit :"+flag_kebenaran_edit);
	alert("catatan_batal :"+catatan_batal);
	alert("ssss");*/
	
	
	//alert("id_fail_carian :"+document.${formName}.id_fail_carian.value);
	//alert("txtNoFailSub :"+document.${formName}.txtNoFailSub.value);
	//alert("id_permohonan_kebenaran :"+document.${formName}.id_permohonan_kebenaran.value);
	//alert("user_id_kebenaran_edit :"+document.${formName}.user_id_kebenaran_edit.value);
	//alert("flag_kebenaran_edit :"+document.${formName}.flag_kebenaran_edit.value);
	//alert("catatan_batal :"+document.${formName}.catatan_batal.value);
	//alert("ssss");
	//document.${formName}.id_fail_carian.value = id_fail_carian;
	//document.${formName}.txtNoFailSub.value = txtNoFailSub;
	document.${formName}.id_permohonan_kebenaran.value = id_permohonan_kebenaran;
	document.${formName}.user_id_kebenaran_edit.value = user_id_kebenaran_edit;
	document.${formName}.flag_kebenaran_edit.value = flag_kebenaran_edit;
	document.${formName}.catatan_batal.value = catatan_batal;
	//alert("xxxx");
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanFlag";
	document.${formName}.action="$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmBatalkanPermohonan&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
	document.${formName}.submit();

}
function cetakBuktiPenyampaianPenasihat(idfail) {
	var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianRayuanPUU?idfail="+idfail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaianMahkamah(idfail) {
	var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianRayuanMT?idfail="+idfail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function changeGetAlamatPenasihat() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.command.value = "maklumatSerahanK1";
	document.${formName}.command2.value = "changeGetAlamatPenasihat";
	document.${formName}.submit();
}
function ForView(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.${formName}.submit();
}

function doOpen(id) {
	
    var url = "../servlet/ekptg.view.ppk.DisplayBlobKeputusanMahkamah?id="+id;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>
