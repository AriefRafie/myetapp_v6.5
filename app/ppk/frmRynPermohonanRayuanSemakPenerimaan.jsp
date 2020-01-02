<br/>

<!-- paging -->
#set($no1enable="<img border='0' title='Senarai permohonan' src='../img/1enable.png'>")
#set($arrow="<img border='0' src='../img/arrowgaris.png'>")
#set($no2current="<img border='0' title='Senarai semak' src='../img/2current.png'>")
#set($no3enable="<img border='0' title='Maklumat permohonan rayuan' src='../img/3enable.png'>")
#set($no3disable="<img border='0' title='Maklumat permohonan rayuan' src='../img/3disable2.png'>")
#set($no4enable="<img border='0' title='Keputusan rayuan' src='../img/4enable2.png'>")
#set($no4disable="<img border='0' title='Keputusan rayuan' src='../img/4disable.png'>")

#set($perhatian="<i><font color=red style=font-size:10px>Perhatian</font>&nbsp;<font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>dipilih dan diisi.</font></i>")

<!-- paging -->	
#set($pagingPermohonanRayuanSS="CL - 01 - 181")
	
<table width="100%" border="0">
<tr>
	<td>
	<div align="right">
	<span>
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:kembaliList()" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
    #elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
    #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
    #elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
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
 	#set($tarikhMati=$data.tarikhMati)
 	#set($namaSimati=$data.namaSimati)
 	#set($namaSipemohon=$data.namaPemohon)
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

#if($newForm == "yes")
#set($nilaiBayaran="")
<fieldset>
	<legend><strong>SENARAI SEMAK PENERIMAAN BORANG K1</strong></legend>
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr><td colspan="3">&nbsp;</td></tr>
		<tr>
			<td width="1%" valign="top"><font color="red">*</font></td>
			<td width="3%"><input type="checkbox" value="121"  name="cbsemaks" id="cbsemaks1" ></td>
    		<td align="justify" width="96%">Borang K1 lengkap diisi.</td>
  		</tr>
  		<tr>
			<td valign="top"><font color="red">*</font></td>
			<td><input type="checkbox" $checkCb2 $checkCb1 value=""  name="parentcb" id="parentcb" onClick="ReadOnlyCheckBox(this);checkitChild()"></td>
    		<td align="justify">Notis Rayuan dikemukakan dalam </td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify"><input type="radio" value="122"  $checkCb1 name="radiosemaks" id="radiosemaks1" onClick="checkitParent()">Tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir.</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify"><input type="radio" value="123"   $checkCb2 name="radiosemaks" id="radiosemaks2" onClick="checkitParent()">Atau tempoh masa yang dibenarkan oleh Mahkamah Tinggi.</td>
  		</tr>
  		<tr>
  			<td valign="top"><font color="red">*</font></td>
			<td><input type="checkbox" value="124"  name="cbsemaks" id="cbsemaks2" onclick="checktxtFEE()" ></td>
    		<td align="justify">Fee Rayuan sebanyak RM50.00 telah dijelaskan.</td>
  		</tr>
  	</table>
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
    		<td align="justify" width="15%">No Resit</td>
    		<td width="78%">:&nbsp;<input type="text" id="txtNomborResitFee" name="txtNomborResitFee" size="25" maxlength="20" value="" onkeyup="checkitA()" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify">Tarikh Bayaran</td>
    		<td>:&nbsp;<input type="text" name="txdTarikhByrnFee" id="txdTarikhByrnFee" size="11" maxlength="10" value="" onkeyup="checkitB()" onblur="check_date(this);validateTarikh(this,this.value);checkitB();checkDenganTarikhSelesai()" >
    		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhByrnFee',false,'dmy')" >&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font></td>
  		</tr>
	</table>
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%" valign="top"><font color="red">*</font></td>
			<td width="3%"><input type="checkbox" value="125"  name="cbsemaks" id="cbsemaks3" onclick="checktxtDEPOSIT()" ></td>
    		<td align="justify" width="96%">Deposit bagi kos rayuan di atas nama Pentadbir telah dijelaskan.</td>
  		</tr>
  	</table>
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="6%">&nbsp;</td>
    		<td align="justify" width="23%">Jumlah Bayaran Deposit</td>
    		#if($nilaiBayaran=="" || $nilaiBayaran==0 || $nilaiBayaran==0.0)
               #set($n3="")
            #else
           
               	#set($n3=$nilaiBayaran)
            #end
            <td width="70%">:&nbsp;RM&nbsp;<input name="txtJumDeposit" style="text-align:right" type="text" id="txtJumDeposit" value="$n3"  size="8" maxlength="10" onkeyup="javascript:validateIC(event,this,this.value,'txtJumDeposit');checkitC()"   onblur="validateModal(this,this.value,'$n3');checkBayaran()" /></td>
        </tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify">No Resit</td>
    		<td>:&nbsp;<input type="text" name="txtNomborResitDeposit" id="txtNomborResitDeposit" size="25" onkeyup="checkitD()" maxlength="20" value="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify">Tarikh Bayaran</td>
    		<td>:&nbsp;<input type="text" name="txdTarikhByrnDeposit" size="11" maxlength="10" id="txdTarikhByrnDeposit" value="" onkeyup="checkitE()" onblur="check_date(this);validateTarikh(this,this.value);checkitE();checkDenganTarikhSelesai2()">
    		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhByrnDeposit',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font></td>
  		</tr>
  	</table>
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%" valign="top" align="right"><font color="red">*</font>&nbsp;</td>
			<td width="3%"><input type="checkbox" value="126"  name="cbsemaks" id="cbsemaks4"></td>
    		<td align="justify" width="96%">Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan.</td>
  		</tr>	
  	</table>
  	
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
  		<tr>
			<td width="1%">&nbsp;</td>
			<td width="6%" valign="top" align="right">&nbsp;</td>
    		<td align="justify" width="23%">Tarikh Terima Rayuan</td>
    		<td width="70%">:&nbsp;<input type="text" name="txdTarikhTerimaRayuan" size="11" maxlength="10" id="txdTarikhTerimaRayuan" value="" onblur="check_date(this);validateTarikh(this,this.value);checkitCD();checkDenganTarikhSelesai3()">
    		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaRayuan',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font></td>
  		</tr>
  		<tr><td colspan="4">&nbsp;</td></tr>
  	</table> 	
  	
  	
  	<br/>
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
    </table>	
</fieldset>
#end

#if($newForm == "no")

#foreach($data in $dataSemakK2)

	#if($data.id_semakansenarai=="121")
		#set($check1="checked")	
	#elseif($data.id_semakansenarai=="122")
		#set($check2="checked")
	#elseif($data.id_semakansenarai=="123")
		#set($check3="checked")
	#elseif($data.id_semakansenarai=="124")
		#set($check4="checked")
	#elseif($data.id_semakansenarai=="125")
		#set($check5="checked")
	#elseif($data.id_semakansenarai=="126")
		#set($check6="checked")
	#end
#end

#foreach($bayaran in $bayaranK2)
	#if($bayaran.id_jenisbayaran=="3")
		#set($id_bayaranF = $bayaran.id_bayaran)
		#set($noresitF = $bayaran.no_resit)
		#set($tarikhBF = $bayaran.tarikh_bayaran)
	#elseif($bayaran.id_jenisbayaran=="4")
		#set($id_bayaranD = $bayaran.id_bayaran)
		#set($noresitD = $bayaran.no_resit)
		#set($tarikhBD = $bayaran.tarikh_bayaran)
		#set($jumlahD = $bayaran.jumlah_bayaran)
	#end	
#end

#if($editForm=="no")
	#set($mode = "disabled")
	#set($mode1 = "readonly")
	#set($mclass = "class=disabled")
#elseif($editForm=="yes")
	#set($mode = "")
	#set($mode1 = "")
	#set($mclass = "")
#end

<fieldset>
	<legend><strong>SENARAI SEMAK PENERIMAAN BORANG K1</strong></legend>
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="1%" valign="top">#if($editForm=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td width="3%"><input type="checkbox" value="121"  name="Ecbsemaks" id="Ecbsemaks1" $check1 $mode /></td>
    		<td align="justify" width="96%">Borang K1 lengkap diisi.</td>
  		</tr>
  		<tr>
			<td valign="top">#if($editForm=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td><input type="checkbox" value=""  name="Eparentcb" id="Eparentcb" $check2 $check3 $mode  onClick="ReadOnlyCheckBox(this);EcheckitChild()" /></td>
    		<td align="justify">Notis Rayuan dikemukakan dalam</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify"><input type="radio" value="122"  name="Eradiosemaks" id="Eradiosemaks1" $check2 $mode  onClick="EcheckitParent()"/>Tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir.</td>
  			#if($check2 == "checked")
  				#set ($checkradiobutton = 1)
  				
  			#end
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
			<td>&nbsp;</td>
    		<td align="justify"><input type="radio" value="123"   name="Eradiosemaks" id="Eradiosemaks2"  $check3 $mode  onClick="EcheckitParent()"/>Atau tempoh masa yang dibenarkan oleh Mahkamah Tinggi. $check3</td>
    		#if($check3 == "checked")
  				#set ($checkradiobutton = 2)
  				
  			#end
  		</tr>
  		
  		<tr>
  			<td valign="top">#if($editForm=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td><input type="checkbox" value="124"  name="Ecbsemaks" id="Ecbsemaks2" $check4 $mode onclick="EchecktxtFEE()"  /></td>
    		<td align="justify">Fee Rayuan sebanyak RM50.00 telah dijelaskan.</td>
  		</tr>
  	</table>
  	
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="6%">&nbsp;</td>
    		<td align="justify" width="15%">No Resit</td>
    		<td width="79%">:&nbsp;<input type="text" id="EtxtNomborResitFee" name="EtxtNomborResitFee" size="25" maxlength="20" value="$!noresitF" onkeyup="EcheckitA()" $mode1 $mclass style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
  		</tr>
  		<tr>
			<td>&nbsp;</td>
    		<td align="justify">Tarikh Bayaran</td>
    		<td>:&nbsp;<input type="text" name="EtxdTarikhByrnFee" id="EtxdTarikhByrnFee" size="11" maxlength="10" value="$!tarikhBF" $mode1 $mclass onkeyup="EcheckitB()" onblur="check_date(this);validateTarikh(this,this.value);EcheckitB()">
    		#if($editForm=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('EtxdTarikhByrnFee',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
  		</tr>
	</table>
	
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%" valign="top">#if($editForm=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td width="3%"><input type="checkbox" value="125"  name="Ecbsemaks" id="Ecbsemaks3" $check5 $mode onclick="EchecktxtDEPOSIT()" /></td>
    		<td align="justify" width="96%">Deposit bagi kos rayuan di atas nama Pentadbir telah dijelaskan.</td>
  		</tr>
  	</table>
  	
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="6%">&nbsp;</td>
    		<td align="justify" width="23%">Jumlah Bayaran Deposit</td>
    		#if($editForm=="no")
    		<td width="71%">:&nbsp;RM&nbsp;<input name="EtxtJumDeposit" $mclass $mode1 style="text-align:right" type="text" id="EtxtJumDeposit" value="$jumlahD"  size="8" maxlength="10" onkeyup="javascript:EcheckitC()" onblur="validateModal(this,this.value,'$!Util.formatDecimal($jumlahD)');" /></td>
            #else
            <td width="71%">:&nbsp;RM&nbsp;<input name="EtxtJumDeposit" $mclass $mode1 style="text-align:right" type="text" id="EtxtJumDeposit" value="$jumlahD"  size="8" maxlength="10" onkeyup="javascript:EcheckitC()" onblur="validateModal(this,this.value,'$!Util.formatDecimal($jumlahD)');EcheckBayaran()" /></td>
            #end
    	</tr>
  		<tr>
			<td>&nbsp;</td>
    		<td align="justify">No Resit</td>
    		<td>:&nbsp;<input type="text" name="EtxtNomborResitDeposit" id="EtxtNomborResitDeposit" size="25" maxlength="20" value="$!noresitD" onkeyup="EcheckitD()" $mclass $mode1 style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
  		</tr>
  		<tr>
			<td>&nbsp;</td>
    		<td align="justify">Tarikh Bayaran</td>
    		<td>:&nbsp;<input type="text" name="EtxdTarikhByrnDeposit" size="11" maxlength="10" id="EtxdTarikhByrnDeposit" value="$!tarikhBD" $mclass $mode1 onkeyup="EcheckitE()" onblur="check_date(this);validateTarikh(this,this.value);EcheckitE()" />
    		#if($editForm=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('EtxdTarikhByrnDeposit',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
  		</tr>
  	</table>
  	
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr>
			<td width="1%" valign="top">#if($editForm=="yes")<font color="red">*</font>#else&nbsp;#end</td>
			<td width="3%"><input type="checkbox" value="126"  name="Ecbsemaks" id="Ecbsemaks4" $check6 $mode /></td>
    		<td align="justify" width="96%">Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan.</td>
  		</tr>
  	</table>
  	
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
  		<tr>
			<td width="6%" valign="top" align="right">#if($editForm=="yes")&nbsp;#else&nbsp;#end</td>
    		<td align="justify" width="23%">Tarikh Terima Rayuan</td>
    		<td width="70%">:&nbsp;<input type="text" name="EtxdTarikhTerimaRayuan" $mclass $mode1 size="11" maxlength="10" id="EtxdTarikhTerimaRayuan" value="$!tarikh_rayuan" onblur="check_date(this);validateTarikh(this,this.value);EcheckitCD()">
    		#if($editForm=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('EtxdTarikhTerimaRayuan',false,'dmy');">&nbsp;<font color="blue" style="font-size:10px"><i>dd/mm/yyy</i></font>#end </td>
  		</tr>
  		<tr><td colspan="3">&nbsp;</td></tr>
  	</table> 
  	
  	#if($editForm=="yes")
  	<br/>
  	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	<td>$!perhatian</td>
        	</tr>
    </table>
    #end
</fieldset>	
#end
<fieldset>
	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
		<tr align="center">
			<td align="right" width="55%">
				#if($newForm == "yes")
				<input type="button" name="cmdSimpan" value="Simpan" onClick="simpanSemakanK2()" />
				#end
				#if($newForm == "no")
					#if($editForm=="no") 
						<!-- #if($id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")#end -->
						#if($id_status!="169")
								#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
								<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniSemakanK2()" />
								#end
					<input type="button" name="cmdCetak" value="Cetak" onClick="cetakSemakanK2('$noFail', '$namaSimati', '$tarikhMati', '$!noresitF','$!tarikhBF','$!Util.formatDecimal($jumlahD)','$!noresitD','$!tarikhBD','$!tarikh_rayuan','$checkradiobutton')" />
						#end
					#end
					#if($editForm=="yes")
					<input type="button" name="cmdUpdate" value="Simpan" onClick="updateSemakanK2()" />
					<input type="button" name="cmdBatal" value="Batal" onClick="semakRayuan('$id_permohonan','$id_status')" />
					#end
				#end
				
		<!-- #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '')
				<input type="button" name="cmdKembali" value="Kembali" onClick="kembaliList()" />      
				#end
				
				#if ($flagFromSenaraiFailSek8 == 'yes')
				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$noFail')"/>
				#end

				#if ($flagFromSenaraiPermohonanSek8 == 'yes')
				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
				#end  -->		
				
				
			</td>
			<td align="right" width="45%">$!pagingPermohonanRayuanSS</td>
		</tr>
	</table>
</fieldset>

		
<table width="100%" border="0">
<tr>
	<td >
	<div align="right">
	<span>
	
	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == "")
		<a href="javascript:kembaliList()" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end 
    #elseif ($flagFromSenaraiFailSek8 == 'yes')
		<a href="javascript:kembaliSenaraiFail('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
    #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
		<a href="javascript:kembaliSenaraiPermohonan('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
    #elseif ($flagForView == 'yes')
		<a href="javascript:ForView('$noFail')" >$!no1enable</a>$!arrow$!no2current$!arrow#if($newForm == "no")<a href="javascript:maklumatRayuan('$id_permohonan','$id_status')" >$!no3enable</a>#else$!no3disable#end$!arrow#if($id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="166" || $id_status=="167" || $id_status=="180")<a href="javascript:seterusnya('$id_permohonan','$id_status')" >$!no4enable</a>#else$!no4disable#end  
    #end 
    
    </span> 
    </div>
   	</td>
</tr>
</table>

</center>

<!-- <input type="hidden" name="command" /> -->

<input type="hidden" name="command2" />
<input type="hidden" name="command3" />
<input type="hidden" name="command4" />
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="id_bayaranF" value="$id_bayaranF">
<input type="hidden" name="id_bayaranD" value="$id_bayaranD">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail">
<input type="hidden" name="tarikh_selesai" value="$tarikhselesai">
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input type="hidden" name="flagRadio" value="$flagRadio">
<input type="hidden" name="id_perintah" value="$id_perintah">
<input name="flagForView" type="hidden" id="flagForView" value="$flagForView"/>

#parse("app/ppk/headerppk_script.jsp")
<script>
function checkBayaran()
{
	var deposit = parseInt("1500");
	
	if (document.${formName}.txtJumDeposit.value != "" && document.${formName}.txtJumDeposit.value > deposit){
		alert("Jumlah Bayaran Deposit tidak boleh melebihi RM1500");
		document.${formName}.txtJumDeposit.focus();
		return;
	}			
}
function EcheckBayaran()
{
	var deposit = parseInt("1500");
	
	if (document.${formName}.EtxtJumDeposit.value != "" && document.${formName}.EtxtJumDeposit.value > deposit){
		alert("Jumlah Bayaran Deposit tidak boleh melebihi RM1500");
		document.${formName}.EtxtJumDeposit.focus();  
		return;
	}		
}
function EcheckitParent()
{
	var radio1 = document.getElementById("Eradiosemaks1").checked;
	var radio2 = document.getElementById("Eradiosemaks2").checked;
	
	if (radio1==true || radio2==true){
		document.getElementById("Eparentcb").checked = true;
	}else{
		document.getElementById("Eparentcb").checked = false;
	}
		
}
function EcheckitChild()
{
	var cbparent = document.getElementById("Eparentcb").checked;
	
	if (cbparent==false){
		document.getElementById("Eradiosemaks1").checked = false;
		document.getElementById("Eradiosemaks2").checked = false;
	}
		
}
function checkitChild()
{
	var cbparent = document.getElementById("parentcb").checked;
	
	if (cbparent==false){
		document.getElementById("radiosemaks1").checked = false;
		document.getElementById("radiosemaks2").checked = false;
	}		
}
function checkitParent()
{
	var radio1 = document.getElementById("radiosemaks1").checked;
	var radio2 = document.getElementById("radiosemaks2").checked;

	if (radio1==true || radio2==true){
		document.${formName}.parentcb.checked = true;
	}else{
		document.${formName}.parentcb.checked = false;
	}

}
function seterusnya(id_permohonan,id_status) 
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan&tabId=0";
	document.${formName}.submit();
}
function EchecktxtDEPOSIT()
{
	var checkbox4 = document.getElementById("Ecbsemaks3").checked;

	if (checkbox4 == false)
	{
		document.getElementById("EtxtJumDeposit").value = "0.00" ;
		document.getElementById("EtxtNomborResitDeposit").value = "" ;
		document.getElementById("EtxdTarikhByrnDeposit").value = "" ;
	}
}
function EcheckitC()
{
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.EtxtJumDeposit.value != "")
	{ 
		document.getElementById("Ecbsemaks3").checked = true;
	}
		
	if(document.${formName}.EtxtJumDeposit.value == "" || document.${formName}.EtxtJumDeposit.value == zero || document.${formName}.EtxtJumDeposit.value == zero2 || document.${formName}.EtxtJumDeposit.value == zero3 || document.${formName}.EtxtJumDeposit.value == zero4)
	{
		if(document.${formName}.EtxtNomborResitDeposit.value == "" && document.${formName}.EtxdTarikhByrnDeposit.value == "")
		{
			document.getElementById("Ecbsemaks3").checked = false;
		}	
	}
}
function EcheckitD()
{
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.EtxtNomborResitDeposit.value != "")
	{ 
		document.getElementById("Ecbsemaks3").checked = true;
	}
		
	if(document.${formName}.EtxtNomborResitDeposit.value == "")
	{
		if((document.${formName}.EtxtJumDeposit.value == "" || document.${formName}.EtxtJumDeposit.value == zero || document.${formName}.EtxtJumDeposit.value == zero2 || document.${formName}.EtxtJumDeposit.value == zero3 || document.${formName}.EtxtJumDeposit.value == zero4) && document.${formName}.EtxdTarikhByrnDeposit.value == "")
		{
			document.getElementById("Ecbsemaks3").checked = false;
		}	
	}
}
function EcheckitE()
{
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.EtxdTarikhByrnDeposit.value != "")
	{ 
		document.getElementById("Ecbsemaks3").checked = true;
	}
		
	if(document.${formName}.EtxdTarikhByrnDeposit.value == "")
	{
		if((document.${formName}.EtxtJumDeposit.value == "" || document.${formName}.EtxtJumDeposit.value == zero || document.${formName}.EtxtJumDeposit.value == zero2 || document.${formName}.EtxtJumDeposit.value == zero3 || document.${formName}.EtxtJumDeposit.value == zero4) && document.${formName}.EtxtNomborResitDeposit.value == "")
		{
			document.getElementById("Ecbsemaks3").checked = false;
		}	
	}
}
function EchecktxtFEE()
{
	var checkbox3 = document.getElementById("Ecbsemaks2").checked;

	if (checkbox3 == false)
	{
		document.getElementById("EtxtNomborResitFee").value = "" ;
		document.getElementById("EtxdTarikhByrnFee").value = "" ;
	}
}
function EcheckitA()
{
	if(document.${formName}.EtxtNomborResitFee.value != "")
	{ 
		document.getElementById("Ecbsemaks2").checked = true;
	}
		
	if(document.${formName}.EtxtNomborResitFee.value == "")
	{
		if(document.${formName}.EtxdTarikhByrnFee.value == "")
		{
			document.getElementById("Ecbsemaks2").checked = false;
		}
		if(document.${formName}.EtxdTarikhByrnFee.value != "")
		{
			document.getElementById("Ecbsemaks2").checked = true;
		}
	}
}
function EcheckitB()
{
	if(document.${formName}.EtxdTarikhByrnFee.value != "")
	{ 
		document.getElementById("Ecbsemaks2").checked = true;
	}
	
	if(document.${formName}.EtxdTarikhByrnFee.value == "")
	{
		if(document.${formName}.EtxtNomborResitFee.value == "")
		{
			document.getElementById("Ecbsemaks2").checked = false;
		}
		if(document.${formName}.EtxtNomborResitFee.value != "")
		{
			document.getElementById("Ecbsemaks2").checked = true;
		}
    }
}


function checktxtDEPOSIT()
{
	var checkbox4 = document.getElementById("cbsemaks3").checked;

	if (checkbox4 == false)
	{
		
		document.getElementById("txtJumDeposit").value = "0.00" ;
		document.getElementById("txtNomborResitDeposit").value = "" ;
		document.getElementById("txdTarikhByrnDeposit").value = "" ;
	}
}
function checkitC()
{
	//var str = "0.00";
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.txtJumDeposit.value != "")
	{ 
		document.getElementById("cbsemaks3").checked = true;
	}
	
	if(document.${formName}.txtJumDeposit.value == "" || document.${formName}.txtJumDeposit.value == zero || document.${formName}.txtJumDeposit.value == zero2 || document.${formName}.txtJumDeposit.value == zero3 || document.${formName}.txtJumDeposit.value == zero4)
	{
		if(document.${formName}.txtNomborResitDeposit.value == "" && document.${formName}.txdTarikhByrnDeposit.value == "")
		{
			document.getElementById("cbsemaks3").checked = false;
		}	
	}
}
function checkitD()
{
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.txtNomborResitDeposit.value != "")
	{ 
		document.getElementById("cbsemaks3").checked = true;
	}
		
	if(document.${formName}.txtNomborResitDeposit.value == "")
	{
		if((document.${formName}.txtJumDeposit.value == "" || document.${formName}.txtJumDeposit.value == zero || document.${formName}.txtJumDeposit.value == zero2 || document.${formName}.txtJumDeposit.value == zero3 || document.${formName}.txtJumDeposit.value == zero4)&& document.${formName}.txdTarikhByrnDeposit.value == "")
		{
			document.getElementById("cbsemaks3").checked = false;
		}	
	}
}
function checkitE()
{
	var zero = "0.00";
	var zero2 = ".00";
	var zero3 = "00";
	var zero4 = "0";
	
	if(document.${formName}.txdTarikhByrnDeposit.value != "")
	{ 
		document.getElementById("cbsemaks3").checked = true;
	}
		
	if(document.${formName}.txdTarikhByrnDeposit.value == "")
	{
		if((document.${formName}.txtJumDeposit.value == "" || document.${formName}.txtJumDeposit.value == zero || document.${formName}.txtJumDeposit.value == zero2 || document.${formName}.txtJumDeposit.value == zero3 || document.${formName}.txtJumDeposit.value == zero4) && document.${formName}.txtNomborResitDeposit.value == "")
		{
			document.getElementById("cbsemaks3").checked = false;
		}	
	}
}
function checktxtFEE()
{
	var checkbox3 = document.getElementById("cbsemaks2").checked;

	if (checkbox3 == false)
	{
		document.getElementById("txtNomborResitFee").value = "" ;
		document.getElementById("txdTarikhByrnFee").value = "" ;
	}
}
function checkitA()
{

	if(document.${formName}.txtNomborResitFee.value != "")
	{ 
		document.getElementById("cbsemaks2").checked = true;
	}
		
	if(document.${formName}.txtNomborResitFee.value == "")
	{
		if(document.${formName}.txdTarikhByrnFee.value == "")
		{
			document.getElementById("cbsemaks2").checked = false;
		}
		if(document.${formName}.txdTarikhByrnFee.value != "")
		{
			document.getElementById("cbsemaks2").checked = true;
		}
	}
}
function checkitB()
{

	if(document.${formName}.txdTarikhByrnFee.value != "")
	{ 
		document.getElementById("cbsemaks2").checked = true;
	}
	
	if(document.${formName}.txdTarikhByrnFee.value == "")
	{
		if(document.${formName}.txtNomborResitFee.value == "")
		{
			document.getElementById("cbsemaks2").checked = false;
		}
		if(document.${formName}.txtNomborResitFee.value != "")
		{
			document.getElementById("cbsemaks2").checked = true;
		}
    }
}

function checkitCD()
{

	if(document.${formName}.txdTarikhTerimaRayuan.value != "")
	{ 
		document.getElementById("cbsemaks4").checked = true;
	}
	
	if(document.${formName}.txdTarikhTerimaRayuan.value == "")
	{
			document.getElementById("cbsemaks4").checked = false;
    }
}

function EcheckitCD()
{

	if(document.${formName}.EtxdTarikhTerimaRayuan.value != "")
	{ 
		document.getElementById("Ecbsemaks4").checked = true;
	}
	
	if(document.${formName}.EtxdTarikhTerimaRayuan.value == "")
	{
			document.getElementById("Ecbsemaks4").checked = false;
    }
}


function kembaliList(){
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function kemaskiniSemakanK2(){
	document.${formName}.command.value = "semakRayuan";
	document.${formName}.command3.value = "kemaskiniSemakanK2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}

function cetakSemakanK2(noFail,namaSimati,tarikhMati,no_resit,EtxdTarikhByrnFee,EtxtJumDeposit,EtxtNomborResitDeposit,EtxdTarikhByrnDeposit,EtxdTarikhTerimaRayuan,checkradiobutton)
	{
		
		var url = "../x/${securityToken}/ekptg.view.ppk.CetakSenaraiSemakRayuan?noFail="+noFail+"&namaSimati="+namaSimati+"&tarikhMati="+tarikhMati+"&no_resit="+no_resit+"&EtxdTarikhByrnFee="+EtxdTarikhByrnFee+"&EtxtJumDeposit="+EtxtJumDeposit+"&EtxtNomborResitDeposit="+EtxtNomborResitDeposit+"&EtxdTarikhByrnDeposit="+EtxdTarikhByrnDeposit+"&EtxdTarikhTerimaRayuan="+EtxdTarikhTerimaRayuan+"&checkradiobutton="+checkradiobutton;
		var hWnd = window.open(url,'printuser','width=700,height=400, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();		
		
	}

function updateSemakanK2(){

	var xdat1=document.${formName}.EtxdTarikhTerimaRayuan.value;
	var adat2=document.${formName}.tarikh_selesai.value;
	var ydat1=document.${formName}.EtxdTarikhByrnDeposit.value;
	var zdat1=document.${formName}.EtxdTarikhByrnFee.value;	
   	
	var xdt1   = parseInt(xdat1.substring(0,2),10);
   	var xmon1  = parseInt(xdat1.substring(3,5),10);
   	var xyr1   = parseInt(xdat1.substring(6,10),10);
   	
	var ydt1   = parseInt(ydat1.substring(0,2),10);
   	var ymon1  = parseInt(ydat1.substring(3,5),10);
   	var yyr1   = parseInt(ydat1.substring(6,10),10);
   	
	var zdt1   = parseInt(zdat1.substring(0,2),10);
   	var zmon1  = parseInt(zdat1.substring(3,5),10);
   	var zyr1   = parseInt(zdat1.substring(6,10),10);
   
   	var adt2   = parseInt(adat2.substring(8,10),10);
   	var amon2  = parseInt(adat2.substring(5,7),10);
   	var ayr2   = parseInt(adat2.substring(0,4),10);
   	
   	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
   	var xfirstDate = new Date(xyr1,xmon1,xdt1);
   	var yfirstDate = new Date(yyr1,ymon1,ydt1);
   	var zfirstDate = new Date(zyr1,zmon1,zdt1);
   	var secondDate = new Date(ayr2,amon2,adt2);

   	var diffDays = Math.round((xfirstDate.getTime() - secondDate.getTime())/(oneDay));
   	var ydiffDays = Math.round((yfirstDate.getTime() - secondDate.getTime())/(oneDay));
   	var zdiffDays = Math.round((zfirstDate.getTime() - secondDate.getTime())/(oneDay));
	
	var deposit = parseInt("1500");
	
	var dat1=document.${formName}.EtxdTarikhByrnFee
	var dat2=document.${formName}.EtxdTarikhByrnDeposit
	var dat3=document.${formName}.EtxdTarikhTerimaRayuan
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	
	var str1  = document.getElementById("EtxdTarikhByrnFee").value;
   	var str2  = document.getElementById("tarikhMohon").value;
	var str3  = document.getElementById("EtxdTarikhByrnDeposit").value;
	var str4  = document.getElementById("EtxdTarikhTerimaRayuan").value;
	
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);

   	var dt4   = parseInt(str4.substring(0,2),10);
   	var mon4  = parseInt(str4.substring(3,5),10);
   	var yr4   = parseInt(str4.substring(6,10),10);
   	
	var date1 = new Date(yr1, mon1, dt1);
	var date2 = new Date(yr3, mon3, dt3);
	var date3 = new Date(yr4, mon4, dt4);
   	var trmohon = new Date(yr2, mon2, dt2);

   	var Eradio1 = document.getElementById('Eradiosemaks1');
	var Eradio2 = document.getElementById('Eradiosemaks2');
	
	var Echeckbox1 = document.getElementById('Ecbsemaks1');
	var Echeckbox2 = document.getElementById('Ecbsemaks2');
	var Echeckbox3 = document.getElementById('Ecbsemaks3');
	var Echeckbox4 = document.getElementById('Ecbsemaks4');

	var currentDate = new Date(year, month, day);
	
	if (Echeckbox1.checked == false)
	{
		alert("Borang K1 perlu dilengkapkan terlebih dahulu");
		Echeckbox1.focus(); 
		return;
	}
	
	else if ((diffDays > 14) && document.${formName}.Eradiosemaks.checked == true)
   	{
   		alert("Sila pastikan  Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (diffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
	
	else if ((ydiffDays > 14) && document.${formName}.Eradiosemaks.checked == true)
   	{
   		alert("ila pastikan Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (ydiffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
	
	else if ((zdiffDays > 14) && document.${formName}.Eradiosemaks.checked == true)
   	{
   		alert("Sila pastikan Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (zdiffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
	
	else if (Eradio1.checked == false && Eradio2.checked == false)
	{
		alert("Sila pilih tempoh notis rayuan dikemukakan");
		Echeckbox2.focus(); 
		return;
	}
	else if(Echeckbox2.checked == false)
	{
		alert("Fee rayuan perlu dijelaskan terlebih dahulu");
		Echeckbox2.focus(); 
		return;
	}
	else if(Echeckbox2.checked == true && document.${formName}.EtxtNomborResitFee.value == "")
	{
		alert("Sila masukkan \"No Resit\"  fee  rayuan");
		document.${formName}.EtxtNomborResitFee.focus(); 
		return;
	}
	else if(Echeckbox2.checked == true && document.${formName}.EtxdTarikhByrnFee.value == "")
	{
		alert("Sila masukkan \"Tarikh Bayaran\"  fee  rayuan");
		document.${formName}.EtxdTarikhByrnFee.focus(); 
		return;
	}
	else if(date1 < trmohon){
   		alert("Sila pastikan \"Tarikh Bayaran\" fee tidak kurang dari tarikh mohon.");
	 	document.${formName}.EtxdTarikhByrnFee.focus();
	 	return;
	} 
	else if(date1 > currentDate){
   		alert("Sila pastikan \"Tarikh Bayaran\" fee tidak lebih dari tarikh hari ini.");
	 	document.${formName}.EtxdTarikhByrnFee.focus();
	 	return;
	} 
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(Echeckbox3.checked == false)
	{
		alert("Deposit bagi kos rayuan perlu dijelaskan terlebih dahulu");
		Echeckbox3.focus(); 
		return;
	}
	else if(Echeckbox3.checked == true && document.${formName}.EtxtJumDeposit.value == "" || document.${formName}.EtxtJumDeposit.value == 0.00)
	{
		alert("Sila masukkan \"Jumlah Bayaran Deposit\" bagi kos rayuan");
		document.${formName}.EtxtJumDeposit.focus();  
		return;
	}
	else if(document.${formName}.EtxtJumDeposit.value != "" && document.${formName}.EtxtJumDeposit.value > deposit)
	{
		alert("Jumlah Bayaran Deposit tidak boleh melebihi RM1500");
		document.${formName}.EtxtJumDeposit.focus();  
		return;
	}
	else if(Echeckbox3.checked == true && document.${formName}.EtxtNomborResitDeposit.value == "")
	{
		alert("Sila masukkan \"No Resit\" bagi kos rayuan");
		document.${formName}.EtxtNomborResitDeposit.focus();  
		return;
	}
	else if(Echeckbox3.checked == true && document.${formName}.EtxdTarikhByrnDeposit.value == "")
	{
		alert("Sila masukkan \"Tarikh Bayaran\" bagi kos rayuan");
		document.${formName}.EtxdTarikhByrnDeposit.focus();  
		return;
	}
	else if(Echeckbox4.checked == false)
	{
		alert("Sila pastikan ruangan Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan dipilih terlebih dahulu");
		Echeckbox4.focus(); 
		return;
	}
	else if(date2 < trmohon){
   		alert("Sila pastikan \"Tarikh Bayaran\" deposit tidak kurang dari tarikh mohon.");
	 	document.${formName}.EtxdTarikhByrnDeposit.focus();
	 	return;
	} 
	else if(date2 > currentDate){
   		alert("Sila pastikan \"Tarikh Bayaran\" deposit tidak lebih dari tarikh hari ini.");
	 	document.${formName}.EtxdTarikhByrnDeposit.focus();
	 	return;
	} 
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(document.${formName}.EtxdTarikhTerimaRayuan.value == "")
	{
		alert("Sila masukkan Tarikh Terima Rayuan");
		document.${formName}.EtxdTarikhTerimaRayuan.focus(); 
		return;
	}
	else if(date3 < trmohon){
   		alert("Sila pastikan \"Tarikh Terima Rayuan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.EtxdTarikhTerimaRayuan.focus();
	 	return;
	} 
	else if(date3 > currentDate){
   		alert("Sila pastikan \"Tarikh Terima Rayuan\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.EtxdTarikhTerimaRayuan.focus();
	 	return;
	} 
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else
	{
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "semakRayuan";
	document.${formName}.command3.value = "kemaskiniSemakanK2";
	document.${formName}.command4.value = "updateSemakanK2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();

	}
}
function simpanSemakanK2(){
	
	var xdat1=document.${formName}.txdTarikhTerimaRayuan.value;
	var adat2=document.${formName}.tarikh_selesai.value;
	var ydat1=document.${formName}.txdTarikhByrnDeposit.value;
	var zdat1=document.${formName}.txdTarikhByrnFee.value;	
   	
	var xdt1   = parseInt(xdat1.substring(0,2),10);
   	var xmon1  = parseInt(xdat1.substring(3,5),10);
   	var xyr1   = parseInt(xdat1.substring(6,10),10);
   	
	var ydt1   = parseInt(ydat1.substring(0,2),10);
   	var ymon1  = parseInt(ydat1.substring(3,5),10);
   	var yyr1   = parseInt(ydat1.substring(6,10),10);
   	
	var zdt1   = parseInt(zdat1.substring(0,2),10);
   	var zmon1  = parseInt(zdat1.substring(3,5),10);
   	var zyr1   = parseInt(zdat1.substring(6,10),10);
   
   	var adt2   = parseInt(adat2.substring(8,10),10);
   	var amon2  = parseInt(adat2.substring(5,7),10);
   	var ayr2   = parseInt(adat2.substring(0,4),10);
   	
   	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
   	var xfirstDate = new Date(xyr1,xmon1,xdt1);
   	var yfirstDate = new Date(yyr1,ymon1,ydt1);
   	var zfirstDate = new Date(zyr1,zmon1,zdt1);
   	var secondDate = new Date(ayr2,amon2,adt2);

   	var diffDays = Math.round((xfirstDate.getTime() - secondDate.getTime())/(oneDay));
   	var ydiffDays = Math.round((yfirstDate.getTime() - secondDate.getTime())/(oneDay));
   	var zdiffDays = Math.round((zfirstDate.getTime() - secondDate.getTime())/(oneDay));
	
	
	var deposit = parseInt("1500");
	
	var dat1=document.${formName}.txdTarikhByrnFee
	var dat2=document.${formName}.txdTarikhByrnDeposit
	var dat3=document.${formName}.txdTarikhTerimaRayuan
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	
	var str1  = document.getElementById("txdTarikhByrnFee").value;
   	var str2  = document.getElementById("tarikhMohon").value;
	var str3  = document.getElementById("txdTarikhByrnDeposit").value;
	var str4  = document.getElementById("txdTarikhTerimaRayuan").value; 
   	
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);

   	var dt4   = parseInt(str4.substring(0,2),10);
   	var mon4  = parseInt(str4.substring(3,5),10);
   	var yr4   = parseInt(str4.substring(6,10),10);

	/*utk tarikh rayuan lpas 14hari
	var dt14   = parseInt(str4.substring(0,2),10)+14;
   	var mon14  = parseInt(str4.substring(3,5),10);
   	var yr14   = parseInt(str4.substring(6,10),10);

	var tarikh14 = new Date(yr14, mon14, dt14);
   	*/
   	
	var date1 = new Date(yr1, mon1, dt1);
	var date2 = new Date(yr3, mon3, dt3);
	var date3 = new Date(yr4, mon4, dt4);
   	var trmohon = new Date(yr2, mon2, dt2);
   	var currentDate = new Date(year, month, day);
   	
	
	var radio1 = document.getElementById('radiosemaks1');
	var radio2 = document.getElementById('radiosemaks2');
	
	var checkbox1 = document.getElementById('cbsemaks1');
	var checkbox2 = document.getElementById('cbsemaks2');
	var checkbox3 = document.getElementById('cbsemaks3');
	var checkbox4 = document.getElementById('cbsemaks4');
	
	if (checkbox1.checked == false)
	{
		alert("Borang K1 perlu dilengkapkan terlebih dahulu");
		checkbox1.focus(); 
		return;
	}
	
	else if (radio1.checked == false && radio2.checked == false)
	{
		alert("Sila pilih tempoh notis rayuan dikemukakan");
		parentcb.focus(); 
		return;
	}
	/*
	else if (radio1.checked == true && (date3 > tarikh14))
	{
		alert("Tarikh Terima Rayuan telah melebihi tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir");
		radio1.focus(); 
		return;
	}
	*/
	
	else if ((diffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("Sila pastikan  Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (diffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
	
	else if ((ydiffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("ila pastikan Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (ydiffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
	
	else if ((zdiffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("Sila pastikan Tarikh Terima Rayuan tidak melebihi 14 hari dari tarikh keputusan.");
   		return;
   	}
	else if (zdiffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   		return;
   	}
   	
	else if(checkbox2.checked == false)
	{
		alert("Fee rayuan perlu dijelaskan terlebih dahulu");
		checkbox2.focus(); 
		return;
	}
	else if(checkbox2.checked == true && document.${formName}.txtNomborResitFee.value == "")
	{
		alert("Sila masukkan \"No Resit\"  fee  rayuan");
		document.${formName}.txtNomborResitFee.focus(); 
		return;
	}
	else if(checkbox2.checked == true && document.${formName}.txdTarikhByrnFee.value == "")
	{
		alert("Sila masukkan \"Tarikh Bayaran\" fee  rayuan");
		document.${formName}.txdTarikhByrnFee.focus(); 
		return;
	}
	else if(date1 < trmohon){
   		alert("Sila pastikan \"Tarikh Bayaran\" fee tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhByrnFee.focus();
	 	return;
	} 
	else if(date1 > currentDate){
   		alert("Sila pastikan \"Tarikh Bayaran\" fee tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhByrnFee.focus();
	 	return;
	} 
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(checkbox3.checked == false)
	{
		alert("Deposit bagi kos rayuan perlu dijelaskan terlebih dahulu");
		checkbox3.focus(); 
		return;
	}
	else if(checkbox3.checked == true && document.${formName}.txtJumDeposit.value == "" || document.${formName}.txtJumDeposit.value == 0.00)
	{
		alert("Sila masukkan \"Jumlah Bayaran Deposit\" bagi kos rayuan");
		document.${formName}.txtJumDeposit.focus();  
		return;
	}
	else if(document.${formName}.txtJumDeposit.value != "" && document.${formName}.txtJumDeposit.value > deposit)
	{
		alert("Jumlah Bayaran Deposit tidak boleh melebihi RM1500");
		document.${formName}.txtJumDeposit.focus();  
		return;
	}
	else if(checkbox3.checked == true && document.${formName}.txtNomborResitDeposit.value == "")
	{
		alert("Sila masukkan \"No Resit\" bagi kos rayuan");
		document.${formName}.txtNomborResitDeposit.focus();  
		return;
	}
	else if(checkbox3.checked == true && document.${formName}.txdTarikhByrnDeposit.value == "")
	{
		alert("Sila masukkan \"Tarikh Bayaran\" bagi kos rayuan");
		document.${formName}.txdTarikhByrnDeposit.focus();  
		return;
	}
	else if (checkbox4.checked == false)
	{
		alert("Sila pastikan ruangan Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan dipilih terlebih dahulu");
		checkbox4.focus(); 
		return;
	}
	else if(date2 < trmohon){
   		alert("Sila pastikan \"Tarikh Bayaran\" deposit tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhByrnDeposit.focus();
	 	return;
	} 
	else if(date2 > currentDate){
   		alert("Sila pastikan \"Tarikh Bayaran\" deposit tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhByrnDeposit.focus();
	 	return;
	} 
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(document.${formName}.txdTarikhTerimaRayuan.value == "")
	{
		alert("Sila masukkan Tarikh Terima Rayuan");
		document.${formName}.txdTarikhTerimaRayuan.focus(); 
		return;
	}
	else if(date3 < trmohon){
   		alert("Sila pastikan \"Tarikh Terima Rayuan\" tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhTerimaRayuan.focus();
	 	return;
	} 
	else if(date3 > currentDate){
   		alert("Sila pastikan \"Tarikh Terima Rayuan\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhTerimaRayuan.focus();
	 	return;
	} 
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "semakRayuan";
		document.${formName}.command2.value = "simpanSemakanK2";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
		document.${formName}.submit(); 
	}

}
function maklumatRayuan(id_permohonan,id_status) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "maklumatRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function semakRayuan(id_permohonan,id_status) 
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
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

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}

function checkDenganTarikhSelesai()
{
	
	var dat1=document.${formName}.txdTarikhByrnFee.value;
	var dat2=document.${formName}.tarikh_selesai.value;
   	
	var dt1   = parseInt(dat1.substring(0,2),10);
   	var mon1  = parseInt(dat1.substring(3,5),10);
   	var yr1   = parseInt(dat1.substring(6,10),10);
   
   	var dt2   = parseInt(dat2.substring(8,10),10);
   	var mon2  = parseInt(dat2.substring(5,7),10);
   	var yr2   = parseInt(dat2.substring(0,4),10);
   	
   	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
   	var firstDate = new Date(yr1,mon1,dt1);
   	var secondDate = new Date(yr2,mon2,dt2);

   	var diffDays = Math.round((firstDate.getTime() - secondDate.getTime())/(oneDay));
   	if ((diffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("Ralat! Tarikh Bayaran Fee Rayuan melebihi 14 hari dari tarikh keputusan.");
   	}
   	if (diffDays < 0) 
   	{
   		alert("Ralat! Tarikh Bayaran Fee Rayuan kurang dari tarikh keputusan.");
   	}
   	
   	
	return false;
	
	
	}
	
function checkDenganTarikhSelesai2()
{
	
	var dat1=document.${formName}.txdTarikhByrnDeposit.value;
	var dat2=document.${formName}.tarikh_selesai.value;
   	
	var dt1   = parseInt(dat1.substring(0,2),10);
   	var mon1  = parseInt(dat1.substring(3,5),10);
   	var yr1   = parseInt(dat1.substring(6,10),10);
   
   	var dt2   = parseInt(dat2.substring(8,10),10);
   	var mon2  = parseInt(dat2.substring(5,7),10);
   	var yr2   = parseInt(dat2.substring(0,4),10);
   	
   	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
   	var firstDate = new Date(yr1,mon1,dt1);
   	var secondDate = new Date(yr2,mon2,dt2);

   	var diffDays = Math.round((firstDate.getTime() - secondDate.getTime())/(oneDay));
   	if ((diffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("Ralat! Tarikh Bayaran Deposit melebihi 14 hari dari tarikh keputusan.");
   	}
   	if (diffDays < 0)
   	{
   		alert("Ralat! Tarikh Bayaran Deposit kurang dari tarikh keputusan.");
   	}
   	
   	
	return false;
	
	
	}
	
function checkDenganTarikhSelesai3()
{
	
	var dat1=document.${formName}.txdTarikhTerimaRayuan.value;
	var dat2=document.${formName}.tarikh_selesai.value;
   	
	var dt1   = parseInt(dat1.substring(0,2),10);
   	var mon1  = parseInt(dat1.substring(3,5),10);
   	var yr1   = parseInt(dat1.substring(6,10),10);
   
   	var dt2   = parseInt(dat2.substring(8,10),10);
   	var mon2  = parseInt(dat2.substring(5,7),10);
   	var yr2   = parseInt(dat2.substring(0,4),10);
   	
   	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
   	var firstDate = new Date(yr1,mon1,dt1);
   	var secondDate = new Date(yr2,mon2,dt2);

   	var diffDays = Math.round((firstDate.getTime() - secondDate.getTime())/(oneDay));
   	if ((diffDays > 14) && document.${formName}.radiosemaks1.checked == true)
   	{
   		alert("Ralat! Tarikh Terima Rayuan melebihi 14 hari dari tarikh keputusan.");
   	}
   	if (diffDays < 0)
   	{
   		alert("Ralat! Tarikh Terima Rayuan kurang dari tarikh keputusan.");
   	}
   	
   	
	return false;
	
	
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

/* function showHideText(box,id,resit,tarikh)  
{ 
 	var x = document.getElementById(id)  
 	x.style.display = box.checked? "inline":"none" 	

 	var tar = document.getElementById(tarikh)
 	tar.value = ""

 	var res = document.getElementById(resit)
 	res.value = "" 	
 	  	
}
function showHideText2(box,id,jumlah,resit,tarikh)  
{ 
 	var x = document.getElementById(id)  
 	x.style.display = box.checked? "inline":"none" 	

 	var jum = document.getElementById(jumlah)
 	jum.value = "" 	
			
 	var tar = document.getElementById(tarikh)
 	tar.value = ""

 	var res = document.getElementById(resit)
 	res.value = "" 	
 	  	
} */
function ForView(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.${formName}.submit();
}
</script>