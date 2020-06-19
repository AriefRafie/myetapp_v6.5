<br/>

#foreach($list in $list2)
	#set($flag_cetak_PPBPP=$list.FLAG_PRINT_NILAIAN_HARTA)
	#set($setlistflaglistPPSPP = $flag_cetak_PPBPP)
#end
 
<textarea id="signedData" name="signedData" style="display:none;">$!signedData</textarea>

<input type="hidden" id="dateEvent">
#set($perhatian="<i><font color=red style=font-size:10px>Perhatian</font><font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;<font color=red style=font-size:10px>*</font>&nbsp;<font style=font-size:10px>diisi.</font></i>")
	#set($perhatian="<i><font color=red >Perhatian</font><font >: Sila pastikan label bertanda</font>&nbsp;<font color=red >*</font>&nbsp;<font >diisi.</font></i>") 

	<!-- paging --> 
	#set($pagingNotisPerbicaraan="CL - 01 - 85")
	#set($pagingLaporanNotis="CL - 01 - 90")
		
#foreach($list in $listSemak)
 	#set($NO_FAIL=$list.noFail)
 	#set($negeri=$list.pmNama_negeri)
 	#set($daerah=$list.namadaerah)
 	#set($unit=$list.namaPejabat)
 	#set($seksyen=$list.seksyen)
 	#set($statusFail=$list.keterangan)
 	#set($tarikhMohon=$list.tarikhMohon)
 	#set($namaSimati=$list.namaSimati)
 	#set($namaSipemohon=$list.namaPemohon)
#end

#if($edit=="no")
	#set($disability="readonly")
	#set($disability1="disabled")
	#set($disabilityCSS="class=disabled")
#else
	#set($disability="")
	#set($disability1="")
	#set($disabilityCSS="")
#end

#if ($selectedTab == "" || $selectedTab=="0")
	#set($tajuk="NOTIS PERBICARAAN")
#elseif ($selectedTab == "1")
	#set($tajuk="TINDAKAN PEGAWAI")
#elseif ($selectedTab == "2")
	#set($tajuk="LAPORAN PENYAMPAIAN NOTIS")
#end
    <center>
  #if($!headerppk.size()>0)
  #parse("app/ppk/headerppk.jsp")
  #end
  <fieldset id="header_lama" style="display:none" >
        <legend><strong>$tajuk</strong></legend>
        <table width="100%">
      <tr>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
                <td width="36%" valign="top">NO FAIL</td>
                <td width="1%" valign="top">:&nbsp;</td>
                <td width="63%" valign="top"><font color="blue">$NO_FAIL</font></td>
                <!-- <td width="63%" valign="top"><a href="javascript:kembaliSenaraiFail('$NO_FAIL')"><font color="blue"><b>$NO_FAIL</b></font></a></td> --> 
              </tr>
                <tr>
                <td valign="top">NEGERI</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$negeri</font></td>
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
              </table></td>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
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
              </table></td>
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
  #end <br/>
  
  <!-- START TAB -->
  
  <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
      #if($id_status=="53" || $id_status=="151")
      <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);NotisNO()">NOTIS PERBICARAAN</li>
      #elseif($id_status=="44" || $id_status=="173" || $id_status=="175" || $id_status=="177" || ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagForView == "" && $id_status=="166"))
      <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);TukarNotis()">NOTIS PERBICARAAN</li>
      #else
      <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);NotisWITH()">NOTIS PERBICARAAN</li>
      #end
   <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(1);TindakanPeg()">TINDAKAN PEGAWAI</li> 
     
      <li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(2);Laporan()">LAPORAN PENYAMPAIAN NOTIS</li>
    </ul>
        <div class="TabbedPanelsContentGroup"> 
       #if($flagPublicDate=="yes")
            #set($txdTarikhBicara="")
            #set($txdTarikhNotis="")
       #end
      
      <!-- Start Content 1 [new form] --> 
      #if($id_status!="44" && $id_status!="173" && $id_status!="175" && $id_status!="177" && $id_status!="166")
      
      #if($viewdata=="no")
      <div class="TabbedPanelsContent"> <br/>
            <fieldset>
            
            <input type="hidden" id="flag_cuti" name="flag_cuti" value="$flag_cuti">
          <!-- <input type="hidden" id="txdTarikhNotisHIDDEN" name="txdTarikhNotisHIDDEN">  -->
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
            <tr>
              <td width="1%" valign="top"><font color="red">*</font></td>
              <td width="20%">Tarikh Bicara</td>
              <td width="79%">:&nbsp;
                 <!--    <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" maxlength="10" value="$txdTarikhBicara" size="11" onblur="check_date(this);getTarikhNotis();validateTarikh(this,this.value);checkEventDate('bicaraNew','new');"  / -->
                    <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" maxlength="10" value="$txdTarikhBicara" size="11" onblur="check_date(this);getTarikhNotis();validateTarikh(this,this.value);checkCutiAm(this.value);"  />
                    <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBicara',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i></td>
            
            
            
            </tr>
            #if($flagPublicDate=="yes")
            <tr>
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah merupakan cuti umum. Sila pilih tarikh</font></td>
            </tr>
            #end
            
            
            <tr id="cutiAm_div">
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah cuti am. Sila Pilih tarikh lain</font></td>
            </tr>
            
			
            <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                   <!--  <input type="text" size="11" name="txdTarikhNotis" maxlength="10" id="txdTarikhNotis" value="$txdTarikhNotis" onblur="validateTarikh(this,this.value);check_date(this);checkEventDate('notisNew','new');" /> -->
                    <input type="text" size="11" name="txdTarikhNotis" class="disabled" maxlength="10" id="txdTarikhNotis" value="$txdTarikhNotis" onblur="validateTarikh(this,this.value);check_date(this);" />
                    <!-- <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhNotis',false,'dmy');">&nbsp; -->&nbsp;&nbsp;&nbsp;&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i></td>
            		 <input type="text" name="edityes" value=$edit>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Masa Bicara</td>
              <td>:&nbsp;
                  <input type="text" name="txtMasaBicara" id="txtMasaBicara" value="$txtMasaBicara" onkeyup="validateNumber(this,this.value);" onblur="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
                    
                    <!-- &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font> -->
                    
                 <select name="socJenisWaktu" id="socJenisWaktu" style="width:auto">
                 	#if($jenisWaktu=="")
                 		#set($_jenisWaktu="")
                 	#else
                 		#set($_jenisWaktu=$jenisWaktu)
                 	#end
                 	
                 	#if($_jenisWaktu=="1")
	                 <option value="1">PAGI</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="2">TENGAHARI</option>
	                 <option value="3">PETANG</option>
                	#elseif($_jenisWaktu=="2")
	                 <option value="2">TENGAHARI</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="3">PETANG</option>
                 	#elseif($_jenisWaktu=="3")
	                 <option value="3">PETANG</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="2">TENGAHARI</option>
                 	#else
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="2">TENGAHARI</option>
	                 <option value="3">PETANG</option>
                 	#end	
                </select>
                    &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)&nbsp;(Masa bicara 0830 pagi hingga 0430 petang)</i></font></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tempat Bicara</td>
              <td>:&nbsp;
                    <input type="radio" id="pKptg" $checkP1 checked name="jenisPejabat" value="1" onclick="onchangeJenisPejabat()" >
                    Cawangan JKPTG
                    &nbsp;&nbsp;
                    <input type="radio" $checkP2 id="pTanah" name="jenisPejabat" value="2" onclick="onchangeJenisPejabat()" >
                    Pejabat Tanah
                    &nbsp;&nbsp;
                    <input type="radio" $checkP3 id="pLain" name="jenisPejabat" value="0" onclick="onchangeJenisPejabat()" >
                    Lain - lain tempat</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;$selectBicara</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Alamat</td>
              <td>:&nbsp;
                    <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="txtAlamatBicara1" id="txtAlamatBicara1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input $!addressReadonly $!addressReadonlyClass type="text"  size="52" name="txtAlamatBicara2" id="txtAlamatBicara2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input type="text" $!addressReadonlyClass $!addressReadonly size="52" name="txtAlamatBicara3" id="txtAlamatBicara3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Poskod</td>
              <td>:&nbsp;
                    <input type="text" name="txtPoskod" id="txtPoskod" $!addressReadonlyClass $!addressReadonly onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" maxlength="5" size="5" value="$poskod" /></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Negeri</td>
              <td>:&nbsp;$selectNegeri</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Pegawai Pengendali</td>
              <td>:&nbsp;$selectPegawai</td>
            </tr>
              </table>
          <br/>
          <br/>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td>$!perhatian</td>
            </tr>
                <!--<tr>
        		<td>&nbsp;</td>
      		</tr> -->
                <tr>
              <td><!--<font color="blue"><u><strong>Nota</strong></u></font> <br>
			 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--> 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa</i> <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara</i> <br></td>
            </tr>
              </table>
        </fieldset>
            <br/>
            <table width="100%"  cellpadding="1" cellspacing="1" border="0">
          <tr align="center">
                <td>
              #if ($flag_cetak_PPBPP == "1")
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan()" />
              #end
              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
              
              <p align="center" valign="top"> 
				<b><font color="RED" size="2">Sila cetak Nilaian Harta oleh PPBPP sebelum meneruskan permohonan.</font>
				<br><font color="RED" size="1">Cetakan berada di tab Nilaian Harta.</font></b>
			  </p>
              
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
              #elseif ($flagFromSenaraiFailSek8 == "yes")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
              #elseif ($flagForView == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end </td>
              </tr>
        </table>
            <br/>
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($listOB_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">

              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listOB_size != '0')
                    <td width="1%"><input type="checkbox" name="allOB" onClick="doCheckAllOB()"></td>
                    #end
                    <td width="45%"><b>Nama Orang Berkepentingan</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status OB</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                    <td width="10%" align="center"><b>Serahan Tangan / Pos</b></td>
					<td width="10%" align="center"><b>Emel</b></td>
					<td width="10%" align="center"><b>PNMB</b></td>
                    
                  </tr>
		              #if($listOB_size!=0)
		              #foreach($ob in $listOB)
		              #set($id_ob=$ob.id_ob)
		              #set($nama_ob=$ob.nama_ob)
		              #set( $i = $velocityCount )
		              #if ( ($i % 2) != 1 )
		              #set( $row = "row2" )
		              #else
		              #set( $row = "row1" )
		              #end
		              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listOB_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listOB_size != '0')
                    <td class="$row"><input type="checkbox" value="$ob.id_ob" name="idsOB" onClick="doUpdateCheckAllOB()"/></td>
                    #end
                    <td class="$row">
                    $ob.nama_ob.toUpperCase()
                    <input type=hidden name=idob_hidden id=idob_hidden value="$ob.id_ob" size=20 />
                    
                    </td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                    
                     #if($ob.age < 18 || $ob.status_ob == 4)
  					  <td colspan=3 class="$row" >&nbsp;</td>
                   
                  	#else
                  	 <td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="1" onClick="doCheckAllOBserahTgn($ob.bil,'1')" ></td>
					
					#if($ob.emel != '')
					
					<td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="3" onClick="doCheckAllOBserahTgn($ob.bil,'3')" ></td>
					#else
					 <td class="$row" >&nbsp;</td>
					#end
					<td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="5" onClick="doCheckAllOBserahTgn($ob.bil,'5')" disabled></td>
                  	#end
                  
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listOB_size > 10) </div>
          #end
        </fieldset>
            <br/>
            <fieldset>
          <legend><strong>SENARAI PEMIUTANG / PENGKAVEAT</strong></legend>
          #if ($listPemiutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPemiutang_size != '0')
                    <td width="1%" class="$row"  ><input type="checkbox" name="allPemiutang" onClick="doCheckAllPemiutang()"></td>
                    #end
                    <td width="45%"><b>Nama</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPemiutang_size!=0)
              #foreach($ob in $listPemiutang)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listPemiutang_size != '0')
                    <td class="$row" >&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listPemiutang_size != '0')
                    <td class="$row" ><input type="checkbox" value="$ob.id_ob" name="idsPemiutang" onClick="doUpdateCheckAllPemiutang()"/></td>
                    #end
                    <td class="$row">$ob.nama_ob.toUpperCase()</td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPemiutang_size > 10) </div>
          #end
        </fieldset>
            <br/>
            
            <fieldset>
          <legend><strong>SENARAI PENGHUTANG</strong></legend>
          #if ($listPenghutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> 
          #end
            <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPenghutang_size != '0')
                    <td width="1%"><input type="checkbox" name="allPenghutang" onClick="doCheckAllPenghutang()"></td>
                    #end
                    <td width="45%"><b>Nama</b></td>
<!--                     <td width="10%"><b>Umur</b></td> -->
<!--                     <td width="15%"><b>Status</b></td> -->
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPenghutang_size!=0)
              #foreach($ohutang in $listPenghutang)
              ##set($id_ob=$ohutang.id_ob)
              #set($nama_Ob=$ohutang.nama_Ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ohutang.count</td>
                    #if($ohutang.jenishutang == 4)
	                    #if ($listPenghutang_size != '0')
	                    <td>&nbsp;</td>
	                    #end
                    	<td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_Ob)')"><font color="blue">$ohutang.nama_Ob.toUpperCase()</font></a></td>
                    #else
	                    #if ($listPenghutang_size != '0')
	                    <td><input type="checkbox" value="$ohutang.id_ob" name="idsPenghutang" onClick="doUpdateCheckAllPenghutang()"/></td>
	                    #end
                    	<td class="$row">$ohutang.nama_Ob.toUpperCase()</td>
                    #end
<!--                     <td class="$row">$ob.age</td> -->
<!--                     <td class="$row">$ob.keteranganOB</td> -->
                    <td class="$row">Penghutang</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPenghutang_size > 10) </div>
          #end
        </fieldset>
            <br/>
            #if($listPenjaga_size!=0)
            <fieldset>
          <legend><strong>MAKLUMAT PENJAGA</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="2" border="0">
                <tr class="table_header">
              <td align="center" width="4%"><b>No</b></td>
              <td><b>Nama Waris</b></td>
              <td><b>Nama Penjaga 1</b></td>
              <td><b>Nama Penjaga 2</b></td>
              <td><b>Nama Penjaga 3</b></td>
              <td><b>Nama Penjaga 4</b></td>
            </tr>
                #foreach($listP in $listPenjaga)
                #set($id_obpennew = $listP.id_ob)
                #set($nama_obpennew = $listP.nama_ob)
                #set( $i = $velocityCount )
                #if ( ($i % 2) != 1 )
                #set( $row = "row2" )
                #else
                #set( $row = "row1" )
                #end
                <tr>
              <td class="$row" align="center">$listP.bil</td>
              <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_obpennew','$Utils.escapeJavaScript($nama_obpennew)')"><font color="blue">$listP.nama_ob.toUpperCase()</font></a></td>
              <td class="$row">$listP.penjaga1.toUpperCase()</td>
              <td class="$row">$listP.penjaga2.toUpperCase()</td>
              <td class="$row">$listP.penjaga3.toUpperCase()</td>
              <td class="$row">$listP.penjaga4.toUpperCase()</td>
            </tr>
                #end
              </table>
        </fieldset>
            #end
            <table width="98%"  cellpadding="1" cellspacing="1" border="0">
          <tr>
                <td align="right">$!pagingNotisPerbicaraan</td>
              </tr>
        </table>
          </div>
      #end      
      
      
      #else 
      <!-- FORM status tangguh --> 
      
      #if($viewdata=="no")
      <div class="TabbedPanelsContent"> <br/>
            <fieldset>
             <input type="hidden" id="flag_cuti" name="flag_cuti" value="$flag_cuti">
          <!-- <input type="hidden" id="txdTarikhNotisHIDDEN" name="txdTarikhNotisHIDDEN">  -->
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%">Bil.Bicara</td>
              <td width="79%">:&nbsp;
                    <input type="text" class="disabled" readonly name="TGBilBicara" value="$previousBil" size="4" />
                    &nbsp;
                    <input type="button" name="cmdBicara" id="cmdBicara" value="Maklumat Bicara" onClick="SenaraiBicara('$id_permohonan','1')" /></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Bicara</td>
              <td>:&nbsp;
                    <!-- <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" value="$txdTarikhBicara" size="11" maxlength="10" onblur="check_date(this);getTarikhNotis();validateTarikh(this,this.value);checkEventDate('bicaraNew','new');"  /> -->
                    <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" value="$txdTarikhBicara" size="11" maxlength="10" onblur="check_date(this);getTarikhNotis();validateTarikh(this,this.value);checkCutiAm(this.value);"  />
                    <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBicara',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i></td>
            </tr>
            #if($flagPublicDate=="yes")
            <tr>
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah merupakan cuti umum. Sila pilih tarikh</font></td>
            </tr>
            #end
            
            <tr id="cutiAm_div">
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah cuti am bagi negeri. Sila Pilih tarikh lain</font></td>
			</tr>
			
            <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                   <!--  <input type="text" size="11" name="txdTarikhNotis" id="txdTarikhNotis" maxlength="10" value="$txdTarikhNotis" onblur="validateTarikh(this,this.value);check_date(this);checkEventDate('notisNew','new');" /> -->
                    <input type="text" size="11" name="txdTarikhNotis" class="disabled" id="txdTarikhNotis" maxlength="10" value="$txdTarikhNotis" onblur="validateTarikh(this,this.value);check_date(this);" />
                    <!-- <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhNotis',false,'dmy');">&nbsp; -->&nbsp;&nbsp;&nbsp;&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i></td>
            	 <input type="text" name="edityes" value=$edit>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Masa Bicara</td>
              <td>:&nbsp;
                    <input type="text" name="txtMasaBicara" id="txtMasaBicara" value="$txtMasaBicara" onblur="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" onkeyup="validateNumber(this,this.value);" maxlength="4" size="4" />
                    
                    <!-- &nbsp;<font color="blue" style="font-size:10px"><i>format 24 jam (HHMM)</i></font> -->
                    
                    <select name="socJenisWaktu" id="socJenisWaktu" style="width:auto">

                    	#if($jenisWaktu=="")
                    		#set($_jenisWaktu="")
                    	#else
                    		#set($_jenisWaktu=$jenisWaktu)
                    	#end

                    	#if($_jenisWaktu=="1")

                  <option value="1">PAGI</option>
                  <option value="0">SILA PILIH</option>
                  <option value="2">TENGAHARI</option>
                  <option value="3">PETANG</option>

                   		#elseif($_jenisWaktu=="2")

                  <option value="2">TENGAHARI</option>
                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="3">PETANG</option>

                    	#elseif($_jenisWaktu=="3")

                  <option value="3">PETANG</option>
                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="2">TENGAHARI</option>

                    	#else 

                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="2">TENGAHARI</option>
                  <option value="3">PETANG</option>

					#end	
</select>
                    &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)&nbsp;(Masa bicara 0830 pagi hingga 0430 petang)</i></font></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tempat Bicara</td>
              <td>:&nbsp;
                    <input type="radio" id="pKptg" $checkP1 checked name="jenisPejabat" value="1" onclick="onchangeJenisPejabat()" >
                    Cawangan JKPTG
                    &nbsp;&nbsp;
                    <input type="radio" $checkP2 id="pTanah" name="jenisPejabat" value="2" onclick="onchangeJenisPejabat()" >
                    Pejabat Tanah
                    &nbsp;&nbsp;
                    <input type="radio" $checkP3 id="pLain" name="jenisPejabat" value="0" onclick="onchangeJenisPejabat()" >
                    Lain - lain tempat</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;$selectBicara</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Alamat</td>
              <td>:&nbsp;
                    <input type="text" $!addressReadonlyClass $!addressReadonly size="52" name="TGAlamatBicara1" id="TGAlamatBicara1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara2" id="TGAlamatBicara2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara3" id="TGAlamatBicara3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Poskod</td>
              <td>:&nbsp;
                    <input type="text" $!addressReadonlyClass $!addressReadonly onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" name="TGPoskod" id="TGPoskod" maxlength="5" size="5" value="$poskod" /></td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Negeri</td>
              <td>:&nbsp;$!selectNegeri</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Pegawai Pengendali</td>
              <td>:&nbsp;$!selectPegawai</td>
            </tr>
              </table>
          <br/>
          <br/>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td>$!perhatian</td>
            </tr>
                <tr>
              <td><!--<font color="blue"><u><strong>Nota</strong></u></font> <br>
			 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--> 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa</i> <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara</i> <br></td>
            </tr>
              </table>
        </fieldset>
            <br/>
            <table width="100%"  cellpadding="1" cellspacing="1" border="0">
          <tr align="center">
                <td> #if($viewdata=="no")
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpanNotisTambah()" />
              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
              #elseif ($flagFromSenaraiFailSek8 == "yes")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
              #elseif ($flagForView == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end 
              
              #end
              
              
              
              #if($viewdata=="yes")
              
              #if($edit=="no")
              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
              #elseif ($flagFromSenaraiFailSek8 == "yes")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
              #elseif ($flagForView == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end
              <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()" />
              #end
              
              #if($edit=="yes")
              <input type="button" name="cmdUpdate" id="cmdUpdate" value="Simpan" onClick="updateNotis()" />
              <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_permohonan')" />
              #end
              
              #if($edit=="no")
              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
              #elseif ($flagFromSenaraiFailSek8 == "yes")
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
              #elseif ($flagForView == 'yes')
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end
              <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
              #end
              #end </td>
              </tr>
        </table>
            <br/>
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($listOB_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listOB_size != '0')
                    <td width="1%"><input type="checkbox" name="allOB" onClick="doCheckAllOB()"></td>
                    #end
                    <td width="45%"><b>Nama Orang Berkepentingan</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status OB</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                    <td width="10%" align="center"><b>Serahan Tangan / Pos</b></td>
					<td width="10%" align="center"><b>Emel</b></td>
					<td width="10%" align="center"><b>PNMB</b></td>
                    
                  </tr>
              #if($listOB_size!=0)
              #foreach($ob in $listOB)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listOB_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listOB_size != '0')
                    <td class="$row"><input type="checkbox" value="$ob.id_ob" name="idsOB" onClick="doUpdateCheckAllOB()"/></td>
                    #end
                    <td class="$row">
                    $ob.nama_ob.toUpperCase()
                     <input type=hidden name=idob_hidden id=idob_hidden value="$ob.id_ob" size=20 />
                    </td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                    
                    
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    
	                    <td colspan =3  class="$row">&nbsp;</td>
	                    
	                #else
	                
	                 
		                 #if ($listSerahanNotis.size() >0)
		                 #set($jenis_serah = "")
			                    #set($id_ob_notis = "")
			                #foreach($listTN in $listSerahanNotis)
			                    
				                #if($listTN.id_ob == $ob.id_ob)
					       			#set($jenis_serah=$listTN.jenis_serah)
					       			#set($id_ob_notis=$listTN.id_ob)				       			
				       			#end
		                    #end
	              		#end
	                    
	                     
				       			<td class="$row" align="center">
				       			<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="1" onClick="doCheckAllOBserahTgn()" 
				       			#if($jenis_serah=='1' && $id_ob_notis==$id_ob) checked #end	  >
				       			</td>
				       			
				       			
				       			<td class="$row" align="center">
				       			
				       			#if($ob.emel != '')
				       				<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="3" onClick="doCheckAllOBserahTgn()" 
				       				#if($jenis_serah=='3' && $id_ob_notis==$id_ob) checked #end	>
				       			#else
				       				&nbsp;
				       			#end
				       			</td>
				       			
				       			<td class="$row" align="center">
				       			<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="5" onClick="doCheckAllOBserahTgn()" 
				       			#if($jenis_serah=='5' && $id_ob_notis==$id_ob) checked #end	disabled> 
				       			</td>
	                
	                #end
                  
                 
                    
                    
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listOB_size > 10) </div>
          #end
        </fieldset>
            <br/>
            <fieldset>
          <legend><strong>SENARAI PEMIUTANG / PENGKAVEAT</strong></legend>
          #if ($listPemiutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPemiutang_size != '0')
                    <td width="1%"><input type="checkbox" name="allPemiutang" onClick="doCheckAllPemiutang()"></td>
                    #end
                    <td width="45%"><b>Nama</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPemiutang_size!=0)
              #foreach($ob in $listPemiutang)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listPemiutang_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listPemiutang_size != '0')
                    <td ><input type="checkbox" value="$ob.id_ob" name="idsPemiutang" onClick="doUpdateCheckAllPemiutang()"/></td>
                    #end
                    <td class="$row">$ob.nama_ob.toUpperCase()</td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPemiutang_size > 10) </div>
          #end
        </fieldset>
            <br/>
            
        <fieldset>
          <legend><strong>SENARAI PENGHUTANG</strong></legend>
          #if ($listPenghutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> 
          #end
            <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPenghutang_size != '0')
                    <td width="1%"><input type="checkbox" name="allPenghutang" onClick="doCheckAllPenghutang()"></td>
                    #end
                    <td width="45%"><b>Nama</b></td>
<!--                     <td width="10%"><b>Umur</b></td> -->
<!--                     <td width="15%"><b>Status</b></td> -->
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPenghutang_size!=0)
              #foreach($ohutang in $listPenghutang)
              ##set($id_ob=$ohutang.id_ob)
              #set($nama_Ob=$ohutang.nama_Ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ohutang.count</td>
                    #if($ohutang.jenishutang == 4)
	                    #if ($listPenghutang_size != '0')
	                    <td>&nbsp;</td>
	                    #end
                    	<td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_Ob)')"><font color="blue">$ohutang.nama_Ob.toUpperCase()</font></a></td>
                    #else
	                    #if ($listPenghutang_size != '0')
	                    <td><input type="checkbox" value="$ohutang.id_ob" name="idsPenghutang" onClick="doUpdateCheckAllPenghutang()" $penghutangDisabled $penghutangChecked/></td>
	                    #end
                    	<td class="$row">$ohutang.nama_Ob.toUpperCase()</td>
                    #end
<!--                     <td class="$row">$ob.age</td> -->
<!--                     <td class="$row">$ob.keteranganOB</td> -->
                    <td class="$row">Penghutang</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPenghutang_size > 10) </div>
          #end
        </fieldset>
            <br/>
            
            #if($listPenjaga_size!=0)
            <fieldset>
          <legend><strong>MAKLUMAT PENJAGA</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr class="table_header">
              <td align="center" width="4%"><b>No</b></td>
              <td><b>Nama Waris</b></td>
              <td><b>Nama Penjaga 1</b></td>
              <td><b>Nama Penjaga 2</b></td>
              <td><b>Nama Penjaga 3</b></td>
              <td><b>Nama Penjaga 4</b></td>
            </tr>
                #foreach($listP in $listPenjaga)
                #set($id_obpen=$listP.id_ob)
                #set($nama_obpen=$listP.nama_ob)
                #set( $i = $velocityCount )
                #if ( ($i % 2) != 1 )
                #set( $row = "row2" )
                #else
                #set( $row = "row1" )
                #end
                <tr>
              <td class="$row" align="center">$listP.bil</td>
              <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_obpen','$Utils.escapeJavaScript($nama_obpen)')"><font color="blue">$listP.nama_ob.toUpperCase()</font></a></td>
              <td class="$row">$listP.penjaga1.toUpperCase()</td>
              <td class="$row">$listP.penjaga2.toUpperCase()</td>
              <td class="$row">$listP.penjaga3.toUpperCase()</td>
              <td class="$row">$listP.penjaga4.toUpperCase()</td>
            </tr>
                #end
              </table>
        </fieldset>
            <br/>
            #end
            <table width="98%"  cellpadding="1" cellspacing="1" border="0">
          <tr>
                <td align="right">$!pagingNotisPerbicaraan</td>
              </tr>
        </table>
          </div>
      #end
      #end 
      <!-- END FORM status tangguh --> 
      
      <!-- End Content 1 [new form]--> 
      
      <!-- Start Content 1 [view form] --> 
      #if($viewdata=="yes")  
      
      #if($edit=="no")
      #foreach($data in $dataNotis)
      #set($id_perbicaraan = $data.id_perbicaraan)
      #set($bilB = $data.bil_bicara)
      #set($tarikhNotis = $data.tarikh_notis)
      #set($alamatb1 = $data.alamat_bicara1)
      #set($alamatb2 = $data.alamat_bicara2)
      #set($alamatb3 = $data.alamat_bicara3)
      #set($masaBicara = $data.masa_bicara)
      #set($tarikhBicara = $data.tarikh_bicara)
      #set($tempatBicara = $data.tempat_bicara)
      #set($poskodb = $data.poskod)
      #set($pegawaiPengendali = $data.peg_pengendali)
      #set($jenisWaktu = $data.jenis_masa_bicara)
      #set($tarikh_bicara_dahulu = $data.tarikh_bicara_dahulu)
      #set($sebab_pinda_tarikh = $data.sebab_pinda_tarikh)
      
      #end  
      #end
      <div class="TabbedPanelsContent"> <br/>
            <fieldset>
             <input type="hidden" id="flag_cuti" name="flag_cuti" value="$flag_cuti">
          <!-- <input type="text" id="txdTarikhNotisHIDDEN" name="txdTarikhNotisHIDDEN" value="$!tarikhNotis">  -->
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%">Bil.Bicara</td>
              <td width="79%">:&nbsp;
                    <input type="text" name="editBilBicara" value="$!bilB" size="4" class="disabled" readonly />
                    #if($showSenarai=="yes")#if($edit=="no")&nbsp;
                    <input type="button" name="cmdBicara" id="cmdBicara" value="Maklumat Bicara" onClick="SenaraiBicara('$id_permohonan','2')" />
                    #end #end </td>
            </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tarikh Bicara</td>
              <td>:&nbsp;
                  <!--   <input type="text" name="editTarikhBicara" id="editTarikhBicara" maxlength="10" value="$!tarikhBicara" size="11" $disabilityCSS $disability  onblur="check_date(this);getTarikhNotisUPDATE();validateTarikh(this,this.value);checkEventDate('bicaraEdit','edit');" /> -->
                    <input type="text" name="editTarikhBicara" id="editTarikhBicara" maxlength="10" value="$!tarikhBicara" size="11" $disabilityCSS $disability  onblur="check_date(this);getTarikhNotisUPDATE();validateTarikh(this,this.value);checkCutiAm(this.value);checkTarikhPinda();" />
                    #if($edit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('editTarikhBicara',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end 
                    <input type="hidden" name="editTarikhBicaraHidden" id="editTarikhBicaraHidden" maxlength="10" value="$!tarikhBicara" size="11" />
                     <input type="hidden" name="tarikh_bicara_dahulu" id="tarikh_bicara_dahulu" maxlength="10"  size="11" />
                    </td>
            </tr>
                        
                        
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                   <!--  <input type="text" size="11" name="editTarikhNotis" maxlength="10" id="editTarikhNotis" value="$!tarikhNotis" $disabilityCSS $disability  onblur="validateTarikh(this,this.value);check_date(this);checkEventDate('notisEdit','edit');" /> -->
                    <input type="text" size="11" name="editTarikhNotis" maxlength="10" id="editTarikhNotis" value="$!tarikhNotis"  class="disabled" readonly  onblur="validateTarikh(this,this.value);check_date(this);" />
                    <input type="hidden" name="edityes" value=$edit>
                    </td>
            </tr>
            #if($flagPublicDate=="yes")
            <tr>
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah merupakan cuti umum. Sila pilih tarikh</font></td>
            </tr>
            #end
            
            <tr id="cutiAm_div">
              <td width="1%" valign="top"></td>
              <td width="20%"></td>
              <td width="79%"><font color="red">Tarikh yang anda pilih adalah cuti am bagi negeri. Sila Pilih tarikh lain</font></td>
			</tr>

<!--                 <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                    <input type="text" size="11" name="editTarikhNotis" maxlength="10" id="editTarikhNotis" value="$!tarikhNotis" $disabilityCSS $disability  onblur="validateTarikh(this,this.value);check_date(this);checkEventDate('notisEdit','edit');" />
                    <input type="text" size="11" name="editTarikhNotis" maxlength="10" id="editTarikhNotis" value="$!tarikhNotis" $disabilityCSS $disability  onblur="validateTarikh(this,this.value);check_date(this);" />
                    #if($edit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('editTarikhNotis',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end </td>
            </tr> -->
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Masa Bicara</td>
              <td>:&nbsp;
                    <input type="text" id="editMasaBicara" name="editMasaBicara" value="$!masaBicara" maxlength="4" onblur="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" onkeyup="validateNumber(this,this.value);" size="4" $disabilityCSS $disability />
                    
                    <!-- &nbsp;<font color="blue" style="font-size:10px"><i>format 24 jam (HHMM)</i></font> -->
                    
                    <select name="socJenisWaktu" id="socJenisWaktu" style="width:auto" $disabilityCSS $disability1>
                  
                  
                  
                  
                  
                  
                  
                  
                  
        			
        			#if($jenisWaktu=="1")
        				
                  
                  
                  
                  
                  
                  
                  
                  
                  <option value="1">PAGI</option>
                  <option value="0">SILA PILIH</option>
                  <option value="2">TENGAHARI</option>
                  <option value="3">PETANG</option>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                   	#elseif($jenisWaktu=="2")
                    	
                  
                  
                  
                  
                  
                  
                  
                  
                  <option value="2">TENGAHARI</option>
                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="3">PETANG</option>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                    #elseif($jenisWaktu=="3")
                    	
                  
                  
                  
                  
                  
                  
                  
                  
                  <option value="3">PETANG</option>
                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="2">TENGAHARI</option>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                    #else
                    	
                  
                  
                  
                  
                  
                  
                  
                  
                  <option value="0">SILA PILIH</option>
                  <option value="1">PAGI</option>
                  <option value="2">TENGAHARI</option>
                  <option value="3">PETANG</option>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                    #end	
                    
                    
                    
                    
                
                
                
                
                
                
                
                
                </select>
                    &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)&nbsp;(Masa bicara 0830 pagi hingga 0430 petang)</i></font></td>
            </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tempat Bicara</td>
              <td>:&nbsp;
                    <input type="radio" id="pKptg" $checkP1 $disabilityCSS $disability1 name="editjenisPejabat" value="1" onclick="onchangeJenisPejabatUpdate()" >
                    Cawangan JKPTG
                    &nbsp;&nbsp;
                    <input type="radio" $checkP2  $disability1 id="pTanah" name="editjenisPejabat" value="2" onclick="onchangeJenisPejabatUpdate()" >
                    Pejabat Tanah
                    &nbsp;&nbsp;
                    <input type="radio" $checkP3  $disability1 id="pLain" name="editjenisPejabat" value="0" onclick="onchangeJenisPejabatUpdate()" >
                    Lain - lain tempat</td>
            </tr>
                <tr>
              <td><font color="red">&nbsp;</font></td>
              <td>&nbsp;</td>
              #if($edit=="no")
              <td>:&nbsp;$!showBicara</td>
              #else
              <td>:&nbsp;$!SELECTBicara</td>
              #end </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Alamat</td>
              <td>:&nbsp;
                    <input $addressReadonly $addressReadonlyClass type="text" size="52" name="editAlamatBicara1" id="editAlamatBicara1" value="$!alamatb1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input $addressReadonly $addressReadonlyClass type="text" size="52" name="editAlamatBicara2" id="editAlamatBicara2" value="$!alamatb2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>:&nbsp;
                    <input $addressReadonly $addressReadonlyClass type="text" size="52" name="editAlamatBicara3" id="editAlamatBicara3" value="$!alamatb3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Poskod</td>
              <td>:&nbsp;
                    <input type="text" $addressReadonlyClass $addressReadonly name="editPoskod" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" id="editPoskod" maxlength="5" size="5" value="$!poskodb"  /></td>
            </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Negeri</td>
              <td>:&nbsp;$!SELECTNegeri</td>
            </tr>
                <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Pegawai Pengendali</td>
              <td>:&nbsp;$!SELECTPegawai</td>
            
            
            
            
            
            
            
            
            </tr>
            #if($tarikh_bicara_dahulu != "")
            <tr>
              <td valign="top">#if($edit=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tarikh Bicara Terdahulu (Sebelum Pindaan)</td>
              <td>:&nbsp;<input type="text" id="tarikh_bicara_dahuluAppear" class="disabled" name="tarikh_bicara_dahuluAppear" value="$!tarikh_bicara_dahulu" /></td>
            </tr>
            #end
            #if($edit=="yes")
            <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Alasan Pindaan Tarikh</td>
              <td>:&nbsp;<textarea id="sebab_pinda_tarikh" $disability1 name="sebab_pinda_tarikh">$!sebab_pinda_tarikh</textarea></td>
            </tr>
            #elseif($sebab_pinda_tarikh != "")
            <tr>
              <td valign="top"></td>
              <td>Alasan Pindaan Tarikh</td>
              <td>:&nbsp;<textarea id="sebab_pinda_tarikh" class="disabled" name="sebab_pinda_tarikh">$!sebab_pinda_tarikh</textarea></td>
            </tr>
            #end
            
            
            
            
            
            
              </table>
          <br/>
          <br/>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                #if($edit=="yes")
                <tr>
              <td>$!perhatian</td>
            </tr>
                <tr> <!---->
              <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa</i> <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara</i> <br></td>
            </tr>
                #else
                <tr>
              <td>&nbsp;</td>
            </tr>
                #end
              </table>
        </fieldset>
            <br/>
            <table width="100%"  cellpadding="1" cellspacing="1" border="0">

          <tr align="center">
                <td>
             
              #if($edit=="no")
	              #if($id_status!="21"  && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
	              
	              #end
	            <!--   #if($SIGNED_TEXT.equals(""))
	             <input type="button" name="cmdKemaskini1"  id="cmdKemaskini1" value="Kemaskini" onClick="kemaskini()" /> -->
	              <!-- #if($!id_perbicaraan!="")
		              <span id="span_PNB" >
		              <input type="button" name="cmdHPNB1" id="cmdHPNB1" value="Hantar PNB (Borang D)" onClick="cetakBorangD('$!NO_FAIL','$!id_perbicaraan','$!id_fail')" />
		              </span>
		           #end  -->
		         <!--  #end -->
		          
	              
	          
              
              #end
              #if($edit=="yes")
	              <input type="button" name="cmdUpdate" id="cmdUpdate" value="Simpan" onClick="updateNotis()" />
	              <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal('$id_permohonan')" />
              #end
              #if($edit=="no")
                <input type="button" name="cmdKemaskini1"  id="cmdKemaskini1" value="Kemaskini" onClick="kemaskini()" /> 
              	<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
              	
	              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
	              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
	              #elseif ($flagFromKeputusanPermohonan == 'yes')
	              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
	              #elseif ($flagFromSenaraiFailSek8 == "yes")
	              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
	              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
	              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
	              #elseif ($flagForView == 'yes')
	              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
	              #end
	              
<!-- 	          NOTE:disabled if user tak masukkan notis bicara dan laporan penghantar notis -->
	              #if($listPenerimaNotis_size1!=0)
	              <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$id_permohonan','$id_status','$id_simati')" />
	              #else
	              <input type="button" class="disabled" disabled="disabled" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$id_permohonan','$id_status','$id_simati')" />
	              #end
              #end 
              </td>
              
              </tr>
             
        </table>
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($listOB_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listOB_size != '0')
	                    #if($edit=="no")
	                    <td width="1%"><input type="checkbox" name="allOB" onClick="doCheckAllOB()" disabled="disabled"></td>
	                    #else
                    <td width="1%"><input type="checkbox" name="allOB" onClick="doCheckAllOB()" ></td>
                    #end
                    <td width="25%"><b>Nama Orang Berkepentingan</b></td>
                    <td width="5%"><b>Umur</b></td>
                    <td width="10%"><b>Status OB</b></td>
                    <td width="25%"><b>Taraf Kepentingan</b></td>
                   
					<td width="10%" align="center"><b>Serahan Tangan / Pos</b></td>
					<td width="10%" align="center"><b>Emel</b></td>
					<td width="10%" align="center"><b>PNMB</b></td>
                    #end

                  </tr>
                  
              #set($jumlah_ob = $listOB_size)    
              #if($listOB_size!=0)
              #foreach($ob in $listOB)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              
              #if($ob.flag == "Y")
              #set($obChecked="checked")
              #else
              #set($obChecked="")
              #end
              
              #if($edit=="yes")
              #set($obDisabled="")
              #else
              #set($obDisabled="disabled")
              #end
              
              
              
       		  
              
            
              <tr>
                    <td class="$row" align="center">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listOB_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listOB_size != '0')
                    <td class="$row"><input type="checkbox" value="$ob.id_ob" name="idsOB" onClick="doUpdateCheckAllOB()" $obDisabled $obChecked/></td>
                    #end
                    <td class="$row">$ob.nama_ob.toUpperCase()
                    <input type=hidden name=idob_hidden id=idob_hidden value="$ob.id_ob" size=20 />
                    </td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                   
                    #if ($listOB_size != '0')
                    
                    
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    
	                    <td colspan =3  class="$row">&nbsp;  </td>
	                    
	                #else
	                
	                 
		                 #if ($listSerahanNotis.size() >0)
		                 #set($jenis_serah = "")
			                    #set($id_ob_notis = "")
			                #foreach($listTN in $listSerahanNotis)
			                    
				                #if($listTN.id_ob == $ob.id_ob)
					       			#set($jenis_serah=$listTN.jenis_serah)
					       			#set($id_ob_notis=$listTN.id_ob)				       			
				       			#end
		                    #end
	              		#end
	                    
	                  
	                    
				       			<td class="$row" align="center">
				       <!--<input type=text name=jenisSerah_hidden id=jenisSerah_hidden value="$jenis_serah" size=20 /> -->
				       
				        <input type=hidden name=statusPNB_hidden id=statusPNB_hidden value="$statusPNB" size=10 />
				       <input type=hidden name=listPenerimaNotis_hidden id=listPenerimaNotis_hidden value="$listPenerimaNotis.size()" size=20 /> 
				       			<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="1" onClick="doCheckAllOBserahTgn()" 
				       			
				       			
				       			#if($jenis_serah=='1' && $id_ob_notis==$id_ob) checked #end	 #if($edit=='no' || $listPenerimaNotis.size()>0 || ($statusPNB=="yes") ) disabled #end >
				       			
				       			
				       			</td>
				       			
				       			<td class="$row" align="center">
				       			#if($ob.emel!='')
				       			<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="3" onClick="doCheckAllOBserahTgn()" 
				       			#if($jenis_serah=='3' && $id_ob_notis==$id_ob) checked #end	#if($edit=='no' || $listPenerimaNotis.size() >0) disabled #end >
				       			#end
				       			
				       			
				       			</td>
				       			
				       			<td class="$row" align="center">
				       			<input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="5" onClick="doCheckAllOBserahTgn()" 
				       			#if($jenis_serah=='5' && $id_ob_notis==$id_ob) checked #end	#if($edit=='no' || $listPenerimaNotis.size() >0) disabled #end disabled> 
				       			
				       			
				       			
				       			</td>
	                
	                #end
	                
	       
                   <!--  <td class="$row"><input type="checkbox" value="$ob.id_obserahtgn" name="idsOBserahTgn" onClick="doUpdateCheckAllOBserahTgn()" $obDisabled $obChecked/>  $obChecked</td>
                    <td class="$row">$ob.emel<input type="checkbox" value="$ob.id_obemel" name="idsOBemel" onClick="doUpdateCheckAllOBemel()" $obDisabled $obChecked/></td>
                    <td class="$row"><input type="checkbox" value="$ob.id_obpnmb" name="idsOBpnmb" onClick="doUpdateCheckAllOBpnmb()" $obDisabled $obChecked/></td> -->
                    #end

                    

                    </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listOB_size > 10) </div>
          #end
        </fieldset>
            <br/>
            <fieldset>
          <legend><strong>SENARAI PEMIUTANG / PENGKAVEAT</strong></legend>
          #if ($listPemiutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPemiutang_size != '0')
                    #if($edit=="no")
                    <td width="1%"><input type="checkbox" name="allPemiutang" onClick="doCheckAllPemiutang()" disabled="disabled"></td>
                    #else
                    <td width="1%"><input type="checkbox" name="allPemiutang" onClick="doCheckAllPemiutang()"></td>
                    #end
                    #end
                    <td width="45%"><b>Nama</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPemiutang_size!=0)
              #foreach($ob in $listPemiutang)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              
              #if($ob.flag == "Y")
              #set($pemiutangChecked="checked")
              #else
              #set($pemiutangChecked="")
              #end
              
              #if($edit=="yes")
              #set($pemiutangDisabled="")
              #else
              #set($pemiutangDisabled="disabled")
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listPemiutang_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listPemiutang_size != '0')
                    <td><input type="checkbox" value="$ob.id_ob" name="idsPemiutang" onClick="doUpdateCheckAllPemiutang()" $pemiutangDisabled $pemiutangChecked/></td>
                    #end
                    <td class="$row">$ob.nama_ob.toUpperCase()</td>
                    #end
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPemiutang_size > 10) </div>
          #end
        </fieldset>
        <br/>
        <fieldset>
          <legend><strong>SENARAI PENGHUTANG</strong></legend>
          #if ($listPenghutang_size > 10)
          <div style="width:100%;height:187;overflow:auto"> 
          #end
            <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    #if ($listPenghutang_size != '0')
                    #if($edit=="no")
                    <td width="1%"><input type="checkbox" name="allPenghutang" onClick="doCheckAllPenghutang()" disabled="disabled"></td>
                    #else
                    <td width="1%"><input type="checkbox" name="allPenghutang" onClick="doCheckAllPenghutang()"></td>
                    #end
                    #end
                    <td width="45%"><b>Nama</b></td>
<!--                     <td width="10%"><b>Umur</b></td> -->
<!--                     <td width="15%"><b>Status</b></td> -->
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                  </tr>
              #if($listPenghutang_size!=0)
              #foreach($ohutang in $listPenghutang)
              ##set($id_ob=$ohutang.id_ob)
              #set($nama_Ob=$ohutang.nama_Ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              
              #if($ohutang.flag == "Y")
              #set($pemiutangChecked="checked")
              #else
              #set($penghutangChecked="")
              #end
              
              #if($edit=="yes")
              #set($penghutangDisabled="")
              #else
              #set($penghutangDisabled="disabled")
              #end
              <tr>
                    <td class="$row" align="center" width="4%">$ohutang.count</td>
                    #if($ohutang.jenishutang == 4)
	                    #if ($listPenghutang_size != '0')
	                    <td>&nbsp;</td>
	                    #end
                    	<td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_Ob)')"><font color="blue">$ohutang.nama_Ob.toUpperCase()</font></a></td>
                    #else
	                    #if ($listPenghutang_size != '0')
	                    <td><input type="checkbox" value="$ohutang.id_ob" name="idsPenghutang" onClick="doUpdateCheckAllPenghutang()" $penghutangDisabled $penghutangChecked/></td>
	                    #end
                    	<td class="$row">$ohutang.nama_Ob.toUpperCase()</td>
                    #end
<!--                     <td class="$row">$ob.age</td> -->
<!--                     <td class="$row">$ob.keteranganOB</td> -->
                    <td class="$row">Penghutang</td>
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listPenghutang_size > 10) </div>
          #end
        </fieldset>
        
        
        
            <br/>
            #if($listPenjaga_size!=0)
            <fieldset>
          <legend><strong>MAKLUMAT PENJAGA</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="2" border="0">
                <tr class="table_header">
              <td align="center" width="4%"><b>No</b></td>
              <td><b>Nama Waris</b></td>
              <td><b>Nama Penjaga 1</b></td>
              <td><b>Nama Penjaga 2</b></td>
              <td><b>Nama Penjaga 3</b></td>
              <td><b>Nama Penjaga 4</b></td>
            </tr>
                #foreach($listP in $listPenjaga)
                #set($id_obpen=$listP.id_ob)
                #set($nama_obpen=$listP.nama_ob)
                #set( $i = $velocityCount )
                #if ( ($i % 2) != 1 )
                #set( $row = "row2" )
                #else
                #set( $row = "row1" )
                #end
                <tr>
              <td class="$row" align="center">$listP.bil</td>
              <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_obpen','$Utils.escapeJavaScript($nama_obpen)')"><font color="blue">$listP.nama_ob.toUpperCase()</font></a></td>
              <td class="$row">$listP.penjaga1.toUpperCase()</td>
              <td class="$row">$listP.penjaga2.toUpperCase()</td>
              <td class="$row">$listP.penjaga3.toUpperCase()</td>
              <td class="$row">$listP.penjaga4.toUpperCase()</td>
            </tr>
                #end
              </table>
        </fieldset>
            <br/>
            #end
            <table width="98%"  cellpadding="1" cellspacing="1" border="0">
          <tr>
                <td align="right">$!pagingNotisPerbicaraan</td>
              </tr>
        </table>
          </div>
      #end 
      
      <!-- End Content 1 [view form]--> 
      
      <!-- Start Content Tindakan Pegawai -->
      
      <div class="TabbedPanelsContent"> <br/>
    		#parse("/app/ppk/tindakanPegawai.jsp")
      </div>
      
      <!-- End Content Tindakan Pegawai -->
      
      <!-- Start Content 2 -->
      <div class="TabbedPanelsContent"> <br/>
            #set($c5=0)         
            #foreach($ob in $selectedOB)           
            
            #set($c5=$c5+1)
            
            #end
            
            #if($c5==0)
            #set($disableZERO="readonly")
            #set($disableZERO1="disabled")
            #set($classZero="class=disabled")
            #else
            #set($disableZERO1="")
            #set($disableZERO="")	
            #set($classZero="")
            #end
            
            
            
            #if($viewNotis=="no")
            
            #if($id_status!="151"  && $id_status!="44" && $id_status!="173" && $id_status!="175" && $id_status!="177" && $id_status!="21"  && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
            #set($check1="")
            #set($check="")
            #set($checkClass="")
            #set($sta="sta18")
            #else
            #set($check1="disabled")
            #set($check="readonly")
            #set($checkClass="class=disabled")
            #set($sta="")
            #end
            <fieldset>
          <legend><strong>MAKLUMAT SERAHAN</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
            <tr>
              <td width="1%" valign="top">#if($sta=="sta18") #if($disableZERO!="readonly")<font color="red">*</font>#else&nbsp;#end #else&nbsp; #end</td>
              <td width="25%">Jenis Serahan</td>
              <td width="1%">:&nbsp;</td>
              <td width="73%">
              	<input type="radio" $serahA $disableZERO1  name="sorJenisSerah" $check1 value="1" onClick="setSerahTangan();enableT()" >
                    Serahan Tangan
              </td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" $statusA name="sorStatus" #if($disA=="no")disabled #end id="serah1"  value="1" onClick="enableT();hideAsterik()">
                    Bukti Penyampaian</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" $statusB name="sorStatus" #if($disA=="no")disabled #end id="serah2"  value="2"  onClick="disabledText('1')">
                    Surat Akuan Bersumpah(jika ada)</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><input type="radio" name="sorJenisSerah" $serahB $disableZERO1  value="2" $check1 onClick="setPOS();enableT();hideAsterik()">
                    Pos Berdaftar</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="sorStatus" #if($disB=="no")disabled #end id="pos1" $statusC value="3" onClick="enableText()">
                    Diterima &nbsp; &nbsp; No.Surat Daftar&nbsp;:&nbsp;
                    <input type="text" #if($txtDis=="yes")disabled #end $classZero name="txtNoDaftarPos" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="txtNoDaftarPos" value="$daftarPos" maxlength="20" $checkClass size="20"></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="sorStatus" #if($disB=="no")disabled #end id="pos2" $statusD value="4" onClick="disabledText('2')">
                    Dikembalikan</td>
            </tr>
            
              <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><input type="radio" name="sorJenisSerah" $serahD $disableZERO1  value="4" $check1 onClick="setPOS_2();enableT();hideAsterik()">
                    Pos Biasa</td>
            </tr>
           <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="sorStatus" #if($disD=="no")disabled #end id="pos1_2" $statusF value="6" onClick="enableText_2()">
                    Telah Dipos &nbsp; &nbsp; Tarikh Pos&nbsp;:&nbsp;
                    
                    
                    <input type="text" #if($txtDis_2=="yes")disabled #end $classZero name="txtNoDaftarPos_2" style="text-transform:uppercase;"  id="txtNoDaftarPos_2" value="$daftarPos_2" maxlength="20" onblur="validateTarikh(this,this.value);check_date(this)"  $checkClass size="10">
            		 <img src="../img/calendar.gif" onclick="displayDatePicker('txtNoDaftarPos_2',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>
            		 
            		
            		</td>
           
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
              <td><input type="radio" name="sorJenisSerah" $serahC $disableZERO1  value="3" $check1 onClick="setEMEL();hideAsterik()">
                    Emel</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    Tarikh Hantar&nbsp;:&nbsp;
                    <input type="text" name="txdTarikhHantar" id="txdTarikhHantar" #if($disC=="no")disabled #end $check $checkClass $disableZERO $classZero value="$!tarikhHantar" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  />
                    #if($sta=="sta18") #if($disableZERO!="readonly")<img id="imejCal" src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end #end
              </td>
            </tr>
               
               <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td> 
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
             <!--  close 30/08/2018 sebab pn syaida minta tutp email ilaunch --> 
                    Alamat Emel&nbsp;:&nbsp;
                    <input type="hidden" #if($disC=="no")disabled #end $classZero name="txtAlamatEmel" id="txtAlamatEmel" value="$!alamatEmel" maxlength="30" $checkClass size="20">
              </td>
            </tr> 
           <!--   aishahlatip 17052017 tambah pos -->
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
                <tr>
          
           
             <!--    <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="sorStatus" #if($disD=="no")disabled #end id="pos2_2" $statusG value="7" onClick="disabledText('2')">
                    Dikembalikan</td>
            </tr> -->
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="sorStatus" id="dummy" value="5" $statusE style="visibility: hidden;" />
                    </td>
            </tr>
                <tr>
              <td>&nbsp;</td>
            </tr>
              </table>
        </fieldset>
            <br/>
            <fieldset>
          <legend><strong>MAKLUMAT PENERIMAAN</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%" valign="top">#if($sta=="sta18") #if($disableZERO!="readonly")<font color="red">*</font>#else&nbsp;#end #else&nbsp;#end</td>
              <td width="25%">Nama Penghantar Notis</td>
              <td width="1%">:&nbsp;</td>
              <td width="73%"><select onchange="changeVal();" $disableZERO1 $classZero $check1 $checkClass name="txtNamaPenghantarNotis" style="width:320">
                 #if($onchangeMyList=="no")
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
                	&nbsp;&nbsp;&nbsp;
			<input type="text" id="lain2PeghantarNotis" name="lain2PeghantarNotis" value="$lain2PeghantarNotis" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
                
                </td>
              <!-- <td width="73%"><input type="text" $disableZERO $classZero name="txtNamaPenghantarNotis" $check $checkClass value="$namaPenghantar" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> --> 
            </tr>
                <tr>
              <td width="1%" valign="top">#if($sta=="sta18") #if($disableZERO!="readonly")<font color="red">*</font>#else&nbsp;#end #else&nbsp;#end</td>
              <td>Tarikh Serahan</td>
              <td>:&nbsp;</td>
              <td><input type="text" name="txdTarikhSerahan" id="txdTarikhSerahan" $check $checkClass $disableZERO $classZero value="$tarikhSerahan" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  />
                    #if($sta=="sta18") #if($disableZERO!="readonly")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerahan',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end #end</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>Nama Penerima</td>
              <td>:&nbsp;</td>
              <td>#if($sta=="sta18") #if($disableZERO!="readonly")
                  <select id="myList" name="myList" onchange="onchangemyList()" style="width:140">
                  <option value="">SILA PILIH&nbsp;</option>                 
                    	#foreach($listOB in $listOBatas18)
                    	<option value="$listOB.id_ob">$listOB.nama_ob.toUpperCase()</option>
                  		#end  
                   </select>
                    &nbsp;#end #end
                   <input type="text" id="namaPenerima" $check $disableZERO $classZero $checkClass name="txtNamaPenerima" value="$namaPenerima" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>No.KP Penerima</td>
              <td>:&nbsp;</td>
              <td> #if($jeniskp=="baru")
                    <input type="text" $check $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$kp1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" />
                    -
                    <input name="txtNoKPBaru2"  $check $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$kp2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>
                    -
                    <input name="txtNoKPBaru3" $check $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$kp3" size="3" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #elseif($jeniskp=="lama")
                    <input type="text" $check $checkClass $disableZERO $classZero size="11" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLama" value="$kplama" maxlength="10" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    #elseif($jeniskp=="lain")
                    <input type="text" $check $checkClass $disableZERO $classZero size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLain" value="$kplain" maxlength="20" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #else
                    <input type="text" $check $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$kp1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" />
                    -
                    <input name="txtNoKPBaru2"  $check $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$kp2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>
                    -
                    <input name="txtNoKPBaru3" $check $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$kp3" size="3" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #end </td>
            </tr>
                <tr>
              <td valign="top" id="nonAsterik">&nbsp;</td>
              <td valign="top" style="display:none" id="withAsterik"><font color="red">*</font></td>
              <td valign="top">Catatan</td>
              <td valign="top">:&nbsp;</td>
              <td><textarea  $disableZERO $checkClass $classZero name="txtCatatan" $check cols="60%" rows="7" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$catatan</textarea></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">Baki Aksara :&nbsp;
                    <input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
            </tr>
              </table>
          <br/>
          <br/>
          #if($sta=="sta18") #if($disableZERO!="readonly")
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td>$!perhatian</td>
            </tr>
              </table>
          #end #end
        </fieldset>
            #if($id_status!="44" && $id_status!="173" && $id_status!="175" && $id_status!="177" && $id_status!="166")        	
            
            
            #if($selectedOB_size==0 || ($id_status=="21"  || $id_status=="64" || $id_status=="163" || $id_status=="164" || $id_status=="165" || $id_status=="169" || $id_status=="166" || $id_status=="167" || $id_status=="180"))
            #set($modecbx="disabled")
            #else
            #set($modecbx="")
            #end <br/>
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($selectedOB_size > 10)
          <div style="width:100%;height:252;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td width="5%" align="center"><input type="checkbox" $check1 $modecbx title="Pilih Semua" name="checkall" onClick="this.value=checkALL(this.form.cbsemaks,$selectedOB_size)"></td>
                    <td width="60%"><b>Nama OB</b></td>
                    <td width="35%"><b>No.KP</b></td>
                    <td width="35%"><b>Emel</b></td>
                    
                  </tr>
              #set($c=0)
              #if($selectedOB_size!=0)
              
              #foreach($ob in $selectedOB)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set($sizeSO=$selectedOB.size())
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              
              
              #if($ob.age>=18 || $ob.age=="")	
              #set($c=$c+1)
              #end
              <tr>
                    <td class="$row" align="center"><input type="checkbox" $check1 name="cbsemaks" value="$ob.id_ob"></td>
                    <td class="$row">$ob.nama_ob.toUpperCase()</td>
                    #if($ob.no_kp_baru !="")
                    <td class="$row">$ob.no_kp_baru1 - $ob.no_kp_baru2 - $ob.no_kp_baru3</td>
                    #elseif($ob.no_kp_lama !="")
                    <td class="$row">$ob.no_kp_lama</td>
                    #elseif($ob.no_kp_lain !="")
                    <td class="$row">$ob.no_kp_lain</td>
                    ##elseif($ob.emel !="")
                    
                    #else
                    <td class="$row">&nbsp;</td>
                    #end 
                    <td class="$row">$ob.emel</td>
                    </tr>
              #end 				
              #else	
              
              #if($c==0 && $listPenerimaNotis_size==0)
              <tr>
                    <td colspan="4">Tiada Orang Berkepentingan</td>
                  </tr>
              #elseif($c==0 && $listPenerimaNotis_size!=0)
              <tr>
                    <td colspan="4">Notis telah dihantar</td>
                  </tr>
              #end
              #end
            </table>
                #if ($selectedOB_size > 10) </div>
          #end
        </fieldset>
            #end			 
            #end
            
            
            #if($viewNotis=="yes")
            
            #if($onchangeMyList=="no")
            #foreach($data in $dataOBNotis)
            #set($kodPenghantarNotis=$data.kod_penghantar_notis)
            #set($idPenghantarNotis=$data.id_penghantarnotis)
            #set($namaHantar=$data.nama_penghantar_notis)
            #set($tarikhSerah=$data.tarikh_serahan)
            #set($namaTerima=$data.nama_penerima)
            #set($kp1=$data.no_kp_baru1)
            #set($kp2=$data.no_kp_baru2)
            #set($kp3=$data.no_kp_baru3)
            #set($kplama=$data.no_kp_lama)
            #set($kplain=$data.no_kp_lain)
            #set($catatan=$data.catatan)
            
            #set($alamatEmel=$data.alamatEmel)
            #set($tarikhHantar=$data.tarikhHantar)
            
            #set($noSurat=$data.no_surat_daftar)
            
  ##ecahsini          
            #if($editNotis=="yes")
            
            #if($data.status_serah=="1")
            #set($disabledStatus1 = "")
            #set($disabledStatus2 = "")
            #set($disabledStatus3 = "disabled")
            #set($disabledStatus4 = "disabled")
            #set($disabledTxtSurat = "disabled")
            <input type="hidden" name="Asterik" id="Asterik" value="no">
            <input type="hidden" name="disablekembali" id="disablekembali" value="no">
            
           
            #set($txtNoDaftarPos_2 = "disabled")
            
            
            #elseif($data.status_serah=="2")
            #set($disabledStatus1 = "")
            #set($disabledStatus2 = "")
            #set($disabledStatus3 = "disabled")
            #set($disabledStatus4 = "disabled")
            #set($disabledTxtSurat = "disabled")
            <input type="hidden" name="Asterik" id="Asterik" value="yes">
            <input type="hidden" name="disablekembali" id="disablekembali" value="yes">
            
          
            #set($txtNoDaftarPos_2 = "disabled")
            
            #elseif($data.status_serah=="3")
            #set($disabledStatus1 = "disabled")
            #set($disabledStatus2 = "disabled")
            #set($disabledStatus3 = "")
            #set($disabledStatus4 = "")
            #set($disabledTxtSurat = "")
            <input type="hidden" name="Asterik" id="Asterik" value="no">
            <input type="hidden" name="disablekembali" id="disablekembali" value="no">
            
           
            #set($txtNoDaftarPos_2 = "disabled")
            
            #elseif($data.status_serah=="4")	
            #set($disabledStatus1 = "disabled")
            #set($disabledStatus2 = "disabled")
            #set($disabledStatus3 = "")
            #set($disabledStatus4 = "")
            #set($disabledTxtSurat = "disabled")
            <input type="hidden" name="Asterik" id="Asterik" value="no">
            <input type="hidden" name="disablekembali" id="disablekembali" value="yes">
            
           
            #set($txtNoDaftarPos_2 = "disabled")
            
            
            #elseif($data.status_serah=="5")	
            #set($disabledStatus1 = "disabled")
            #set($disabledStatus2 = "disabled")
            #set($disabledStatus3 = "disabled")
            #set($disabledStatus4 = "disabled")
            #set($disabledTxtSurat = "disabled")
            <input type="hidden" name="Asterik" id="Asterik" value="no">
            <input type="hidden" name="disablekembali" id="disablekembali" value="yes">
            
           
            #set($txtNoDaftarPos_2 = "disabled")
            
            #end
            
            #else
            
            #if($data.status_serah=="1" || $data.status_serah=="2")
            #set($disabledStatus1 = "disabled")
            #set($disabledStatus2 = "disabled")
            #set($disabledStatus3 = "disabled")
            #set($disabledStatus4 = "disabled")
            #set($disabledTxtSurat = "disabled")
            #elseif($data.status_serah=="3" || $data.status_serah=="4")
            #set($disabledStatus1 = "disabled")
            #set($disabledStatus2 = "disabled")
            #set($disabledStatus3 = "disabled")
            #set($disabledStatus4 = "disabled")
            #set($disabledTxtSurat = "disabled")
            #end
            
            #if($data.status_serah=="2")
            #set($showCetakSAB="yes")
            #else
            #set($showCetakSAB="no")
            #end	
            
            #end
            
            #end
            #end
            
            #if($onchangeMyList=="yes")
            #if($onchangeMyListDropdown=="yes")	
            #set($mode="")
            #set($namaHantar=$SnamaPntr) 	
            #set($kodPenghantarNotis=$SkodPntr)
            #set($idPenghantarNotis=$SidPntr)
            #else
            #set($mode="kosong")
            #end	
            #set($tarikhSerah=$Starikh)
            #set($namaTerima=$SnamaP)
            #set($kp1=$Skp1)
            #set($kp2=$Skp2)
            #set($kp3=$Skp3)
            #set($kplama=$Skplama)
            #set($kplain=$Skplain)
            #set($catatan=$Scatatan)
            #set($noSurat=$Sdftr)
            #end
            
            #if($editNotis=="yes")
            #set($disabledNotis = "")
            #set($disabledNotis1 = "")
            #set($disabledTxtTarikhEmel = "")
            #set($disabledPosBiasa = "")
            #set($dClass="")
            #else
            #set($disabledNotis = "readonly")
            #set($disabledNotis1 = "disabled")
            #set($dClass="class=disabled")
            #set($disabledTxtTarikhEmel = "disabled")
            #set($disabledPosBiasa = "disabled")
            #end
            
            
             #if($data.id_penghantarnotis=="99999")
             #set($disabledNamaLain = "disabled")
             #end
            
            <fieldset>
          <legend><strong>MAKLUMAT SERAHAN</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%" valign="top">#if($editNotis=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td width="25%">Jenis Serahan</td>
              <td width="1%">:&nbsp;</td>
              <td width="73%"><input type="radio"  name="EDITsorJenisSerah" value="1" onClick="setSerahTangan();enableTUpdate()" $disabledNotis1 $jenisSerah >
                    Serahan Tangan</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" $disabledStatus1 id="serah1" value="1" $serah1 onClick="enableTUpdate();hideAsterik()">
                    Bukti Penyampaian</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" $disabledStatus2 id="serah2" value="2" $serah2 onClick="disabledTextUpdate('1')" >
                    Surat Akuan Bersumpah(jika ada)</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><input type="radio" name="EDITsorJenisSerah" value="2"  onClick="setPOS();enableTUpdate();hideAsterik()" $disabledNotis1 $jenisPos >
                    Pos Daftar</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" $disabledStatus3 id="pos1" value="3" onClick="enableTextUpdate()" $pos1 >
                    Diterima &nbsp; &nbsp; No.Surat Daftar&nbsp;:&nbsp;
                    <input type="text" $disabledTxtSurat name="EDITtxtNoDaftarPos" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="txtNoDaftarPos" value="$noSurat" maxlength="20" size="20"></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" $disabledStatus4 id="pos2" value="4" onClick="disabledTextUpdate('2')" $pos2 >
                    Dikembalikan</td>
            </tr>
            
            <!-- aishahlatip 17052017 tambah pos-->
         
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><input type="radio" name="EDITsorJenisSerah" $serahD $disabledPosBiasa  value="4" $check1 onClick="setPOS_2();enableT();hideAsterik()" $jenisPosBiasa>
                    Pos Biasa</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
           
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" #if($disD=="no")disabled #end id="pos1_2" $statusF value="6" onClick="enableText_2()" $pos1_2>
                    Telah Dipos &nbsp; &nbsp; Tarikh Pos&nbsp;:&nbsp;
                    
                  
                    <input type="text" #if($txtDis_2=="yes")disabled #end $classZero name="txtNoDaftarPos_2" style="text-transform:uppercase;"  id="txtNoDaftarPos_2" value="$daftarPos_2" maxlength="20" onblur="validateTarikh(this,this.value);check_date(this)"  $checkClass size="10">
            		#if($sta=="sta18")  <img src="../img/calendar.gif" onclick="displayDatePicker('txtNoDaftarPos_2',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end 
            		
            		</td>
         
            </tr>
            
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><input type="radio" name="EDITsorJenisSerah" $disabledTxtTarikhEmel  value="3" $check1 onClick="setEMEL();hideAsterik()" $jenisEmel/>
                    Emel</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    Tarikh Hantar&nbsp;:&nbsp;
                    <input type="text" name="txdTarikhHantar" id="txdTarikhHantar" $disabledTxtTarikhEmel $check $checkClass $disableZERO $classZero value="$!tarikhHantar" size="11" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  />
                    #if($sta=="sta18") <img id="imejCal" src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end 
              </td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td> 
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                <!--  close 30/08/2018 sebab pn syaida minta tutp email ilaunch --> 
                    Alamat Emel&nbsp;:&nbsp;
                    <input type="hidden" $disabledTxtAlamatEmel  $classZero name="txtAlamatEmel" id="txtAlamatEmel" value="$!alamatEmel" maxlength="20" $checkClass size="20">
              </td>
            </tr> 
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" id="dummy" value="5" $emel1 style="visibility: hidden;" />
                    </td>
            </tr>
             
            
              <!--   <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                    <input type="radio" name="EDITsorStatus" #if($disD=="no")disabled #end id="pos2_2" $statusG value="7" onClick="disabledText('2')" $pos2_2>
                    Dikembalikan</td>
            </tr> -->
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;&nbsp;&nbsp; &nbsp;
                   <!--  <input type="radio" name="EDITsorStatus" id="dummy" value="5" $emel1 style="visibility: hidden;" /> -->
                    </td>
            </tr>
                <tr>
              <td>&nbsp;</td>
            </tr>
              </table>
        </fieldset>
            <br/>
            <fieldset>
          <legend><strong>MAKLUMAT PENERIMAAN</strong></legend>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%" valign="top">#if($editNotis=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td width="25%">Nama Penyampai Notis</td>
              <td width="1%">:&nbsp;</td>
              <td width="73%"><select onchange="changeVal();" $disabledNotis1 $dClass name="EDITtxtNamaPenghantarNotis" style="width:320">

		#if($onchangeMyList=="no")	
			<option value="$idPenghantarNotis">$kodPenghantarNotis - $namaHantar.toUpperCase()</option>
			<option value="">SILA PILIH&nbsp;</option>
		#foreach($dpn in $dataPenghantarNotis)
			<option value="$dpn.id_penghantarnotis">$dpn.kod_penghantar_notis - $dpn.nama.toUpperCase()</option>
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
			&nbsp;&nbsp;&nbsp;
			#if($editNotis=="yes")
			
			#end
			<input type="text" id="lain2PeghantarNotis" name="lain2PeghantarNotis" value="$lain2PeghantarNotis" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
			
			</td>
              <!-- <td width="73%"><input type="text" $disabledNotis $dClass name="EDITtxtNamaPenghantarNotis" value="$namaHantar" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> --> 
            </tr>
                <tr>
              <td valign="top">#if($editNotis=="yes")<font color="red">*</font>#else&nbsp;#end</td>
              <td>Tarikh Serahan</td>
              <td>:&nbsp;</td>
              <td><input type="text" $disabledNotis $dClass name="EDITtxdTarikhSerahan" id="EDITtxdTarikhSerahan" value="$tarikhSerah" maxlength="10" size="11" onblur="validateTarikh(this,this.value);check_date(this)"  />
                    #if($editNotis=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('EDITtxdTarikhSerahan',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>#end</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>Nama Penerima</td>
              <td>:&nbsp;</td>
              <td>#if($editNotis=="yes")
                    <select id="myList" name="EditMyList" onchange="onchangemyListUPDATE()" style="width:140">
                  <option value="">SILA PILIH&nbsp;</option>

                  #foreach($listOB in $listOBatas18)
                  <option value="$listOB.id_ob">$listOB.nama_ob.toUpperCase()</option>
			      #end                		
                  </select>
                    &nbsp;
                    #end
                    <input type="text" $disabledNotis $dClass id="namaPenerima" name="EDITtxtNamaPenerima" value="$namaTerima" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>No.KP Penerima</td>
              <td>:&nbsp;</td>
              <td> #if($jeniskpx=="baru")
                    <input type="text" $disabledNotis $dClass size="6" name="EDITtxtNoKPBaru1" id="txtNoKPBaru1" value="$kp1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" />
                    -
                    <input name="EDITtxtNoKPBaru2" $disabledNotis $dClass id="txtNoKPBaru2" type="text" value="$kp2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>
                    -
                    <input name="EDITtxtNoKPBaru3" $disabledNotis $dClass id="txtNoKPBaru3"  type="text" value="$kp3" size="3" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" />
                    <input type="hidden" name="EDITtxtNoKPLain" id="EDITtxtNoKPLain" value="">
                    <input type="hidden" name="EDITtxtNoKPLama" id="EDITtxtNoKPLama" value="">
                    #elseif($jeniskpx=="lama")
                    <input type="text" $disabledNotis $dClass size="11" name="EDITtxtNoKPLama" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" value="$kplama" maxlength="10"  onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="EDITtxtNoKPBaru1" id="EDITtxtNoKPBaru1" value="">
                    <input type="hidden" name="EDITtxtNoKPBaru2" id="EDITtxtNoKPBaru2" value="">
                    <input type="hidden" name="EDITtxtNoKPBaru3" id="EDITtxtNoKPBaru3" value="">
                    <input type="hidden" name="EDITtxtNoKPLain" id="EDITtxtNoKPLain" value="">
                    #elseif($jeniskpx=="lain")
                    <input type="text" $disabledNotis $dClass size="20" name="EDITtxtNoKPLain" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" value="$kplain" maxlength="20"  onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="EDITtxtNoKPBaru1" id="EDITtxtNoKPBaru1" value="">
                    <input type="hidden" name="EDITtxtNoKPBaru2" id="EDITtxtNoKPBaru2" value="">
                    <input type="hidden" name="EDITtxtNoKPBaru3" id="EDITtxtNoKPBaru3" value="">
                    <input type="hidden" name="EDITtxtNoKPLama" id="EDITtxtNoKPLama" value="">
                    #else
                    <input type="text" $disabledNotis $dClass size="6" name="EDITtxtNoKPBaru1" id="txtNoKPBaru1" value="$kp1" maxlength="6"  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" />
                    -
                    <input name="EDITtxtNoKPBaru2" $disabledNotis $dClass id="txtNoKPBaru2" type="text" value="$kp2"  size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')"/>
                    -
                    <input name="EDITtxtNoKPBaru3" $disabledNotis $dClass id="txtNoKPBaru3"  type="text" value="$kp3" size="3" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" />
                    <input type="hidden" name="EDITtxtNoKPLain" id="EDITtxtNoKPLain" value="">
                    <input type="hidden" name="EDITtxtNoKPLama" id="EDITtxtNoKPLama" value="">
                    #end </td>
            </tr>
                <tr>
              <td valign="top" id="nonAsterik">&nbsp;</td>
              <td valign="top" style="display:none" id="withAsterik"><font color="red">*</font></td>
              <td valign="top">Catatan</td>
              <td valign="top">:&nbsp;</td>
              <td><textarea $disabledNotis $dClass name="EDITtxtCatatan" cols="60%" rows="7" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.EDITtxtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.EDITtxtCatatan,this.form.remLen1,4000);" >$catatan</textarea></td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">&nbsp;</td>
              <td valign="top">Baki Aksara :&nbsp;
                    <input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
            </tr>
              </table>
          <br/>
          <br/>
          <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                #if($editNotis=="yes")
                <tr>
              <td>$!perhatian</td>
            </tr>
                #else
                <tr>
              <td>&nbsp;</td>
            </tr>
                #end
              </table>
        </fieldset>
            <br/>
            #if($selectedOB_size==0)
            #set($modecb="disabled")	
            #else
            #set($modecb="")		
            #end
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($selectedOB_size > 10)
          <div style="width:100%;height:252;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td width="5%" align="center"><input type="checkbox" disabled $modecb title="Pilih Semua" name="checkall" onClick="this.value=checkALL(this.form.cbsemaks,$selectedOB_size)"></td>
                    <td width="60%"><b>Nama OB</b></td>
                    <td width="35%"><b>No.KP</b></td>
                    <td width="35%"><b>Emel</b></td>
                    
                  </tr>
              #set($e=0)		
              #if($selectedOB_size!=0)
              
              #foreach($ob in $selectedOB)
              #set($id_ob=$ob.id_ob)
              #set($nama_ob=$ob.nama_ob)
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              
              
              #if($ob.age>=18 || $ob.age=="")	
              #set($e=$e+1)
              #end
              <tr>
                    <td class="$row" align="center"><input type="checkbox" disabled name="EDITcbsemaks" value="$ob.id_ob"></td>
                    <td class="$row">$ob.nama_ob.toUpperCase()</td>
                    #if($ob.no_kp_baru !="")
                    <td class="$row">$ob.no_kp_baru1 - $ob.no_kp_baru2 - $ob.no_kp_baru3</td>
                    #elseif($ob.no_kp_lama !="")
                    <td class="$row">$ob.no_kp_lama</td>
                    #elseif($ob.no_kp_lain !="")
                    <td class="$row">$ob.no_kp_lain</td>
                    #else
                    <td class="$row">&nbsp;</td>
                    #end 
                    <td class="$row">$ob.emel</td>
                    </tr>
              #end
              
              
              #else	
              #if($e==0 && $listPenerimaNotis_size==0)
              <tr>
                    <td colspan="4">Tiada Orang Berkepentingan</td>
                  </tr>
              #elseif($e==0 && $listPenerimaNotis_size!=0)
              <tr>
                    <td colspan="4">Notis telah dihantar</td>
                  </tr>
              #end
              #end
            </table>
                #if ($selectedOB_size > 10) </div>
          #end
        </fieldset>
            #end         	
            
            
            #if($viewNotis=="no")
            <table width="100%"  cellpadding="1" cellspacing="1" border="0">
            #if($setlistflaglistPPSPP == "1")
            
          <tr align="center">
                <td> #if($listPenerimaNotis_size!=0)
              #if($disableZERO!="readonly")
              #if($id_status!="44" && $id_status!="173" && $id_status!="175" && $id_status!="177" && $id_status!="21" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
              <input type="button" name="cmdSimpan" value="Simpan" onClick="simpanTambahanLaporan('$sizeSO')" />
              #end
              #end
              #end
             
              #if($sta=="sta18")
              #if($listPenerimaNotis_size==0) 
              #if($id_status!="21"  && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
              <input type="button" name="cmdSimpan" value="Simpan" onClick="simpanLaporan('$sizeSO')" />
              #end
              #end
              #end
              
              #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
            
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
             
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/>
              #elseif ($flagFromSenaraiFailSek8 == "yes")
             
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
             
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/>
              #elseif ($flagForView == 'yes')
            
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end 
              
              
               #if ($flagFromSenaraiFailSek8 == "" && $flagFromSenaraiPermohonanSek8 == "" && $flagFromKeputusanPermohonan == "" && $flagForView == "")
              <input name="cetak" type="button" value="Cetak Laporan Penghantar Notis" onclick="RPP('$id_fail')" />
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
              #elseif ($flagFromKeputusanPermohonan == 'yes')
              <input name="cetak" type="button" value="Cetak Laporan Penghantar Notis" onclick="RPP('$id_fail')" />
             <!--  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliKeputusanPermohonan('$id_permohonan','$idpermohonansimati','$tarikhMohon')"/> -->
              #elseif ($flagFromSenaraiFailSek8 == "yes")
              <input name="cetak" type="button" value="Cetak Laporan Penghantar Notis" onclick="RPP('$id_fail')" />
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$NO_FAIL')"/>
              #elseif ($flagFromSenaraiPermohonanSek8 == 'yes')
              <input name="cetak" type="button" value="Cetak Laporan Penghantar Notis" onclick="RPP('$id_fail')" />
             <!--  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$NO_FAIL')"/> -->
              #elseif ($flagForView == 'yes')
              <input name="cetak" type="button" value="Cetak Laporan Penghantar Notis" onclick="RPP('$id_fail')" />
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$NO_FAIL')"/>
              #end 
              
              
              #if($showButtonCetakAkuanSumpah=="yes")
              <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReportSAB')" />
              #end
              
              #if($sta=="sta18")
	              #if($dataNotis_size>0 && $listPenerimaNotis_size!=0)
	              <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$id_permohonan','$id_status','$id_simati')" />
	              #else
	              <input type="button" name="cmdTeruskan" class="disabled" disabled="disabled" value="Seterusnya" onClick="seterusnya('$id_permohonan','$id_status','$id_simati')" />
	              #end
              #end 
              
              <!-- <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable2('tableReport2')" /> --></td>
              </tr>
              #else
              <tr>
             		 <font color="red"><b>Sila cetak nilaian PPBPP terlebih dahulu.</b></font>
              </tr>
              #end
        </table>
            #end
            
            #if($viewNotis=="yes")
            <table width="100%"  cellpadding="1" cellspacing="1" border="0">
          <tr align="center">
                <td> #if($editNotis=="no")
              #if($id_status!="21" && $id_status!="64" && $id_status!="163" && $id_status!="164" && $id_status!="165" && $id_status!="169" && $id_status!="166" && $id_status!="167" && $id_status!="180")
              <input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskiniNotis('$id_notisobdtl')" />
              #end 
              
              <!-- #if($showCetakSAB=="yes")
      					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReportSAB')" />
      					#end --> 
              
              #end
              
              #if($editNotis=="yes")
              <input type="button" name="cmdSimpan" value="Simpan" onClick="updateLaporan()" />
              <input type="button" name="cmdBatal" value="Batal" onClick="batalKemaskiniNotis('$id_semaknotisobdtl')" />
              #end
              
              
              #if($editNotis=="no") 
              <!-- <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable2('tableReport2')" />-->
              
              <input type="button" name="cmdKembali" value="Kembali" onClick="Laporan()" />
              #end </td>
              </tr>
        </table>
            #end
            
         
            #if($id_status!="44" && $id_status!="173" && $id_status!="175" && $id_status!="177" && $id_status!="166")	
            #if($listPenerimaNotis_size!=0)
            <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN DAN PENERIMA NOTIS</strong></legend>
          #if($listPenerimaNotis_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">
              <tr class="table_header">
                    <td align="center" width="5%"><b>No</b></td>
                    <td width="45%"><b>Nama Orang Berkepentingan</b></td>
                    <td><b>Nama Penerima</b></td>
                  </tr>
              #foreach($listPN in $listPenerimaNotis)
              #set($id_notisobdtlPN=$listPN.id_notisobdtl)
              
              #set( $i = $velocityCount )
              #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
              #else
              #set( $row = "row1" )
              #end
              <tr>
                    <td class="$row" align="center">$listPN.bil</td>
                    <td class="$row"><a href="javascript:semakPenerimaNotis('$id_notisobdtlPN')"><font color="blue">$listPN.nama_ob.toUpperCase()</font></a></td>
                    <td class="$row">$listPN.nama_penerima.toUpperCase()</td>
                  </tr>
              #end
            </table>
                #if($listPenerimaNotis_size > 10) </div>
          #end
        </fieldset>
            #end   
            #end
            <table width="98%"  cellpadding="1" cellspacing="1" border="0">
          <tr>
                <td align="right">$!pagingLaporanNotis</td>
              </tr>
        </table>
            <fieldset id="tableReportSAB" style="display:none;">
          <legend><strong>SENARAI LAPORAN</strong></legend>
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
              <td ><a href="#" class="style2" onClick="javascript:cetakAkuanBersumpah('$id_fail','$id_perbicaraan')"><font color="blue">Surat Akuan Bersumpah</font></a></td>
            </tr>
              </table>
        </fieldset>
          </div>
    </div>
      </div>
  <!-- End content 2 --> 
  
  <!-- END TAB -->
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  <!--
<input type="hidden" name="command">
 -->
  
  <input type="hidden" name="command2">
  <input type="hidden" name="command3">
  <input type="hidden" name="id_permohonan" value="$id_permohonan">
  <input type="hidden" name="id_keputusanpermohonan" value="$id_keputusanpermohonan">
  <input type="hidden" name="idnegeri" value="$idnegeri">
  <input type="hidden" name="id_simati" value="$id_simati">
  <input type="hidden" name="id_ob" value="$id_ob">
  <input type="hidden" name="nama_ob" value="$nama_ob">
  <input type="hidden" name="id_status" value="$id_status">
  <input type="hidden" name="id_notisobdtl" value="$id_notisobdtl">
  <input type="hidden" name="id_semaknotisobdtl" value="$!id_semaknotisobdtl">
  <input type="hidden" name="id_perbicaraan" value="$id_perbicaraan">
  <input type="hidden" name="latestBil" value="$previousBil">
  <input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon">
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
  <input name="flagForView" type="hidden" id="flagForView" value="$flagForView"/>
  <input type="hidden" name="id_fail" value="$id_fail">
  <input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail">
  <input type="hidden" name="tarikhNotis" value="$tarikhNotis">
  <input type="hidden" name="jeniskp" value="$jeniskp">
  <input type="hidden" name="jeniskpx" value="$jeniskpx">
  <input type="hidden" id="alertBIL" name="alertBIL" value="$!alertBIL">
  <input type="hidden" id="NOalertBIL" name="NOalertBIL" value="$!NOalertBIL">
  <input type="hidden" id="idpejabatjkptg" name="idpejabatjkptg" value="$!idpejabatjkptg">
  <input name="flag_KP" type="hidden" id="flag_KP" value="$flagFromKeputusanPermohonan"/>
  <input type="hidden" id="idpermohonansimati" name="idpermohonansimati" value="$!idpermohonansimati">
  <input type="hidden" name="tarikh_mohon">
  <input type="hidden" name="idPermohonan">
  
  <!-- seterusnya -->
  <input type="hidden" name="idpermohonan">
  <input type="hidden" name="id_Simati">
  
  <!-- kemaskini fail -->
  <input type="hidden" name="flagFromMyJsp"/>
  
  <!-- checking tarikh bicara -->
  <input type="hidden" id="checkingTarikhBicara" name="checkingTarikhBicara" value="$!checkingTarikhBicara">
  #parse("app/ppk/headerppk_script.jsp")
</center>
    <br/>
    <fieldset id="tableReport1" style="display:none;">
  <legend><strong>SENARAI LAPORAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
       <!--  <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangD('$NO_FAIL','$id_perbicaraan','$id_fail')"> <font color="blue">Borang D</font> </a></td>
    </tr>
    
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangD_2('$NO_FAIL','$id_perbicaraan','$id_fail')"> <font color="blue">Borang D (Serahan Emel)</font> </a></td>
    </tr> -->
    #if($CHECK_SIGNED_TEXT != '')
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorandDByJenisSerahan('$NO_FAIL','$id_perbicaraan','$id_fail')"> <font color="blue">Borang D (BARU)</font> </a></td>
    </tr>
    #end
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangDD('$NO_FAIL','$id_perbicaraan','$idpermohonansimati','$id_simati')"><font color="blue">Borang DD</font></a></td>
    </tr>
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBuktiPenyampaian('$NO_FAIL','$!tarikhNotis','$id_fail','$id_perbicaraan')"><font color="blue">Bukti Penyampaian</font></a></td>
    </tr>
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakNotaPerbicaraan('$NO_FAIL','$id_perbicaraan','$id_fail')"><font color="blue">Nota Perbicaraan</font></a></td>
    </tr>
        #if($xidnegerix==11)
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanNotisTerengganu('$NO_FAIL','$idpejabatjkptg')"><font color="blue">Surat Iringan Notis(Terengganu)</font></a></td>
    </tr>
        #else
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanNotis('$NO_FAIL','$idpejabatjkptg')"><font color="blue">Surat Iringan Notis</font></a></td>
    </tr>
        #end
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratPanduanBicara('$id_fail','$id_permohonan')"><font color="blue">Panduan Perbicaraan</font></a></td>
    </tr>
        <tr>
      <td><a href="#" class="style2" onClick="javascript:cetakSlipPendengaran('$id_fail','$NO_FAIL')"><font color="blue">Slip Pendengaran</font></a></td>
    </tr>
      </table>
</fieldset>

    <!-- <fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBuktiPenyampaian('$NO_FAIL','$!tarikhNotis','$id_fail')"><font color="blue">Bukti Penyampaian</font></a></td>
      </tr> 
    </table>      
</fieldset>  -->










#if($open_pupop == "Y")

<script>
$jquery(document).ready(function () {

	cetakBorangD('$NO_FAIL','$id_perbicaraan','$idfail','$signedData');
	
	
	 
});
</script>
#end

<script>
/* document.${formName}.imejCal.style.visibility = 'hidden';
 */// document.${formName}.cmdHantarEmel.style.visibility = 'hidden';
 
// $jquery(document).ready(function () {

	
	
	 
//});
 

 
function refreshPageNew(idp) {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&id_permohonan="+idp+"&tabId=0";
	//document.${formName}.command.value = "refreshTambahMufti";
	//alert(idp);
	document.${formName}.submit();
}
function refreshPageView(idp) {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&id_permohonan="+idp+"&tabId=0";
	//document.${formName}.command.value = "refreshTambahMufti";
	document.${formName}.submit();
}
window.onload=function()
{
	
	//ubah sini
	 $jquery('#cutiAm_div').hide();
	 changeVal();
	

	var alertBIL  = document.getElementById("alertBIL").value;
	var NOalertBIL  = document.getElementById("NOalertBIL").value;
	
	if(alertBIL=="yes"){
		alert("Notis telah dikeluarkan sebanyak "+NOalertBIL+" kali");
	}
	
	var check  = document.getElementById("disablekembali").value;

	if(check == "yes"){
		disabledTextUpdate()
	}else{
		enableTextUpdate()
	}	

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
function seterusnya(id_permohonan,id_status,id_simati) 
{
	
	if(id_status == '18'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_notis";
	}
	else
	if(id_status == '47'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
	}
	else
	if(id_status == '174'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
	}	
	else
	if(id_status == '176'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
	}else{
		//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		//alert ("id_status = "+id_status);
		alert("Status permohonan tidak sah untuk keputusan perbicaraan");
		return;
	}	
	document.${formName}.idpermohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_Simati.value = id_simati;
	document.${formName}.submit();
}
function onchangeJenisPejabat() {
	//doAjaxCall${formName}("onchangeJenisPejabat");
	document.${formName}.command.value = "onchangeJenisPejabat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();	
}
function onchangeJenisPejabatUpdate() {
	document.${formName}.command.value = "onchangeJenisPejabatUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();	
}
function SenaraiBicara(id_permohonan,flag) {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8SenaraiBicara?id_permohonan="+id_permohonan+"&flag="+flag;
	var hWnd = window.open(url,'displayfile','width=1000,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function getTarikhNotis() {
 	//alert("awesome");
	var TB  = document.getElementById("txdTarikhBicara").value;
	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);   	
	var dt1   = parseInt(TB.substring(0,2),10)-7;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);
	var myDate=new Date(yr1, mon1, dt1);
	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();
	var tarikhNotis = "";
	if(month>=10){
		if(day>=10){
			tarikhNotis = day + "/" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhNotis = day + "/0" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	if(a!="" && b!="" && c!=""){
	
	var tt = tarikhNotis;
	var dt_dob   = parseInt(tt.substring(0,2),10);
    var mon_dob  = parseInt(tt.substring(3,5),10)-1;
    var yr_dob   = parseInt(tt.substring(6,10),10);
	var date_dob = new Date(yr_dob, mon_dob, dt_dob);	
	var currentTime = new Date(); 	
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	
	if(day < 10)
	{
	day = "0"+day;
	}
	if(month < 10)
	{
	month = "0"+month;
	}
	
	var currentDate = day + "/" + month + "/" + year;	
	
		if(date_dob < currentTime)
		{
		document.getElementById("txdTarikhNotis").value = tarikhNotis ;
		}
		else if(date_dob > currentTime)
		{
		document.getElementById("txdTarikhNotis").value = currentDate ;
		}
		else
		{
		document.getElementById("txdTarikhNotis").value = tarikhNotis ;
		}
		
	}else{
	document.getElementById("txdTarikhNotis").value = "" ;
	alert("Tarikh yang dipilih merupakan cuti umum. Sila pilih tarikh lain.");
	}
	
}
function getTarikhNotisUPDATE() {

	var TB  = document.getElementById("editTarikhBicara").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10)-7;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

	var myDate=new Date(yr1, mon1, dt1);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhNotis = "";
	if(month>=10){
		if(day>=10){
			tarikhNotis = day + "/" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhNotis = day + "/0" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	
	
	if(a!="" && b!="" && c!=""){
	//document.getElementById("editTarikhNotis").value = tarikhNotis ;
	//}else{
	//document.getElementById("editTarikhNotis").value = "" ;
	//}
		var tt = tarikhNotis;
		var dt_dob   = parseInt(tt.substring(0,2),10);
	    var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	    var yr_dob   = parseInt(tt.substring(6,10),10);
		var date_dob = new Date(yr_dob, mon_dob, dt_dob);	
		var currentTime = new Date(); 	
	    var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		
		if(day < 10)
		{
		day = "0"+day;
		}
		if(month < 10)
		{
		month = "0"+month;
		}
		
		var currentDate = day + "/" + month + "/" + year;	
		
			if(date_dob < currentTime)
			{
			document.getElementById("editTarikhNotis").value = tarikhNotis ;
			}
			else if(date_dob > currentTime)
			{
			document.getElementById("editTarikhNotis").value = currentDate ;
			}
			else
			{
			document.getElementById("editTarikhNotis").value = tarikhNotis ;
			}
			
		}else{
		document.getElementById("editTarikhNotis").value = "" ;
		alert("Tarikh yang dipilih merupakan cuti umum. Sila pilih tarikh lain.");
		}
}
function doChangeidTempatBicara() {
	document.${formName}.command.value = "doChangeidTempatBicara";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();
	
}
function onchangemyList() {
	document.${formName}.command.value = "semakDataLaporan";
	document.${formName}.command2.value = "onchangemyList";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();	
}
function onchangemyListUPDATE() {
	document.${formName}.command.value = "semakPenerimaNotis";
	document.${formName}.command2.value = "kemaskiniNotis";
	document.${formName}.command3.value = "onchangemyListUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();
	
}
function doChangeidTempatBicaraUPDATE() {
	document.${formName}.command.value = "doChangeidTempatBicaraUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();	
}

function simpan() {
//aishah
	var numbers = /^[-+]?[0-9]+$/; 
	var masa = document.${formName}.txtMasaBicara.value;
	var ValJam = masa.substring(0,2);
	var ValMinit = masa.substring(2,4);
	
	var str1  = document.getElementById("txdTarikhBicara").value;

	var a = str1.substring(0,2);
	var b = str1.substring(3,5);
   	var c = str1.substring(6,10);
   	
	var dt1x   = parseInt(str1.substring(0,2),10)-7;
   	var mon1x  = parseInt(str1.substring(3,5),10)-1;
   	var yr1x   = parseInt(str1.substring(6,10),10);

	var myDate=new Date(yr1x, mon1x, dt1x);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhNotis = "";
	
	if(document.${formName}.txtMasaBicara.value.match(numbers))  
    {  
   		
   		}
	else
	{
		alert("Sila masukkan hanya nombor sahaja di dalam medan \"Masa Bicara\".");
  		document.${formName}.txtMasaBicara.focus(); 
	return;  
	}
	
	if(month>=10){
		if(day>=10){
			tarikhNotis = day + "/" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhNotis = day + "/0" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/0" + month + "/" + year;	
		}
	}

	
	var dtH   = parseInt(tarikhNotis.substring(0,2),10);
   	var monH  = parseInt(tarikhNotis.substring(3,5),10);
   	var yrH   = parseInt(tarikhNotis.substring(6,10),10);
	
	var dat1=document.${formName}.txdTarikhBicara
	var dat2=document.${formName}.txdTarikhNotis	

	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   
   	var str2  = document.getElementById("txdTarikhNotis").value;
   	var str3  = document.getElementById("tarikhMohon").value;

   	//var txdTarikhNotisHIDDEN  = document.getElementById("txdTarikhNotisHIDDEN").value;

   	//var dtH   = parseInt(txdTarikhNotisHIDDEN.substring(0,2),10);
   	//var monH  = parseInt(txdTarikhNotisHIDDEN.substring(3,5),10);
   	//var yrH   = parseInt(txdTarikhNotisHIDDEN.substring(6,10),10);
   	
   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);

    var dateHidden = new Date(yrH, monH, dtH);
   	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(yr2, mon2-1, dt2+1);
   	var trMOHON = new Date(yr3, mon3-1, dt3);
   	
   	var ob = true;
	if ($jquery('input[name="idsOB"]').length > 0){
		
		if($jquery('input[name="idsOB"]:checked').length == 0){
			ob = false;
		}
	}
	
	
	
	
	
	//aishahlatip
	var jenisSerahChecked = true;
	if ($jquery('input[name="idob_hidden"]').length > 0){
		
		if($jquery('input:radio[name^=radioJenisSerah]:checked').length == 0){
			jenisSerahChecked = false;
		}
	}

   	
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(document.${formName}.txdTarikhBicara.value == ""){
		alert("Sila masukkan \"Tarikh Bicara\" terlebih dahulu.");
  		document.${formName}.txdTarikhBicara.focus(); 
	return;
	}
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh bicara tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhBicara.focus();
	 	return;
	} 
	else if(document.${formName}.txdTarikhNotis.value == ""){
		alert("Sila masukkan \"Tarikh Notis\" terlebih dahulu.");
  		document.${formName}.txdTarikhNotis.focus(); 
		return;
	}
	else if(date2 > date1){
   		alert("Sila pastikan tarikh notis tidak lebih dari tarikh bicara.");
	 	document.${formName}.txdTarikhNotis.focus();
	 	return;
	}  
	else if(date2 < currentTime){
		alert("Sila pastikan tarikh notis tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhBicara.focus();
		return;
	}
	else if(date2 > dateHidden){
   		alert("Sila pastikan tarikh notis tidak boleh dalam lingkungan 7 hari sebelum tarikh bicara.");
	 	document.${formName}.txdTarikhNotis.focus();
	 	return;
	}
	else if(date2 < trMOHON){
   		alert("Sila pastikan tarikh notis tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhNotis.focus();
	 	return;
	} 	
	else if(document.${formName}.txtMasaBicara.value == ""){
		alert("Sila masukkan \"Masa Bicara\" terlebih dahulu.");
  		document.${formName}.txtMasaBicara.focus(); 
	return;
	}

	//validation 4angka 
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan selengkapnya");
		document.${formName}.txtMasaBicara.focus();
	}
	//validation max & min jam
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam > 12) || (ValJam < 01))) {
		alert("Sila masukkan \"Masa Bicara\" dalam format 12jam");
		document.${formName}.txtMasaBicara.focus();
	}
	
	//validation max minit
	else if (document.${formName}.txtMasaBicara.value != "" && (ValMinit > 59)) {
		alert("Sila masukkan \"Masa Bicara\" dengan betul");
		document.${formName}.txtMasaBicara.focus();
	}
	
	//validation masa bicara 0830
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam == 05) || (ValJam == 06) || (ValJam == 07))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.txtMasaBicara.focus();
	}
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam == 08 && ValMinit < 30) || (ValJam == 04 && ValMinit > 30))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.txtMasaBicara.focus();
	}

	//jenis waktu
	else if(document.${formName}.socJenisWaktu.value == "0" || document.${formName}.socJenisWaktu.value == ""){
		alert("Sila pilih \"Jenis Waktu\" terlebih dahulu.");
  		document.${formName}.socJenisWaktu.focus(); 
	return;
	}

	//validation masa dan jenis (utk pagi)
	else if ((ValJam == 08 || ValJam == 09 || ValJam == 10 || ValJam == 11) && (document.${formName}.socJenisWaktu.value != "1") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}

	//validation masa dan jenis (utk tengahari)
	else if (ValJam == 12 && (document.${formName}.socJenisWaktu.value != "2") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}
	
	//validation masa dan jenis (utk petang)
	else if ((ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04) && (document.${formName}.socJenisWaktu.value != "3") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}

	
	/*	
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.charAt(0) == 2) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) > 2) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) < 8)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 8) && (document.${formName}.txtMasaBicara.value.charAt(2) < 3)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) > 6)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) > 3))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) == 3) && (document.${formName}.txtMasaBicara.value.charAt(3) > 0))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1) && (document.${formName}.txtMasaBicara.value.charAt(1) >9 ) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
	return; 
	}	
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 2) && (document.${formName}.txtMasaBicara.value.charAt(1) > 3) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
	return; 
	} 
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(2) > 5) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
	return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(3) > 9) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
	return; 
	}	
*/	
	else if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih/masukkan \"Tempat Bicara\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
	return;
	}
    
    /*
	else if(document.${formName}.txtAlamatBicara1.value == "" && document.${formName}.txtAlamatBicara2.value == "" && document.${formName}.txtAlamatBicara3.value == ""){
		alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
  		document.${formName}.txtAlamatBicara1.focus(); 
	return;
	}
    */
    
    else if (document.${formName}.jenisPejabat[2].checked == true    
         && (document.${formName}.txtAlamatBicara1.value == "" && document.${formName}.txtAlamatBicara2.value == "" && document.${formName}.txtAlamatBicara3.value == "")){
            alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
            document.${formName}.txtAlamatBicara1.focus(); 
        	return;
       }      

	else if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
	return;
	}	
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri Bicara\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
	return;
	}	
	else if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
	return;
	}	
	else if (ob==false){
		
		alert("Sila pilih orang berkepentingan");
		return;
		
	}else if (jenisSerahChecked==false){
	
	alert("Sila pilih jenis serahan Laporan Notis");
	return;
	}else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
	 	document.${formName}.command.value = "simpan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
		document.${formName}.submit(); 
	}
}
function hantarEmail(idpermohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "hantarEmail";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();	
}
function simpanNotisTambah() {
	var numbers = /^[-+]?[0-9]+$/; 
	var masa = document.${formName}.txtMasaBicara.value;
	var ValJam = masa.substring(0,2);
	var ValMinit = masa.substring(2,4);
	
	var str1  = document.getElementById("txdTarikhBicara").value;

	var a = str1.substring(0,2);
	var b = str1.substring(3,5);
   	var c = str1.substring(6,10);
   	
	var dt1x   = parseInt(str1.substring(0,2),10)-7;
   	var mon1x  = parseInt(str1.substring(3,5),10)-1;
   	var yr1x   = parseInt(str1.substring(6,10),10);

	var myDate=new Date(yr1x, mon1x, dt1x);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhNotis = "";
	if(document.${formName}.txtMasaBicara.value.match(numbers))  
    {  
   		
   		}
	else
	{
		alert("Sila masukkan hanya nombor sahaja di dalam medan \"Masa Bicara\".");
  		document.${formName}.txtMasaBicara.focus(); 
	return;  
	}
	
	if(month>=10){
		if(day>=10){
			tarikhNotis = day + "/" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhNotis = day + "/0" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/0" + month + "/" + year;	
		}
	}

	
	var dtH   = parseInt(tarikhNotis.substring(0,2),10);
   	var monH  = parseInt(tarikhNotis.substring(3,5),10);
   	var yrH   = parseInt(tarikhNotis.substring(6,10),10);
   	
	var dat1=document.${formName}.txdTarikhBicara
	var dat2=document.${formName}.txdTarikhNotis	
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   	//var str1  = document.getElementById("txdTarikhBicara").value;
   	var str2  = document.getElementById("txdTarikhNotis").value;
	var str3  = document.getElementById("tarikhMohon").value;

	//var txdTarikhNotisHIDDEN  = document.getElementById("txdTarikhNotisHIDDEN").value;

   	//var dtH   = parseInt(txdTarikhNotisHIDDEN.substring(0,2),10);
   	//var monH  = parseInt(txdTarikhNotisHIDDEN.substring(3,5),10);
   	//var yrH   = parseInt(txdTarikhNotisHIDDEN.substring(6,10),10);
   	
   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);

   	var dateHidden = new Date(yrH, monH, dtH);
   	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(yr2, mon2-1, dt2+1);
   	var trMOHON = new Date(yr3, mon3-1, dt3);
   	
   	var ob = true;
	if ($jquery('input[name="idsOB"]').length > 0){
		
		if($jquery('input[name="idsOB"]:checked').length == 0){
			ob = false;
		}
	}
	//aishahlatip
	var jenisSerahChecked = true;
	if ($jquery('input[name="idob_hidden"]').length > 0){
		
		if($jquery('input:radio[name^=radioJenisSerah]:checked').length == 0){
			jenisSerahChecked = false;
		}
	}
	
	if (document.${formName}.TGPoskod.value != "" && document.${formName}.TGPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.TGPoskod.focus();
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
		else if(document.${formName}.txdTarikhBicara.value == ""){
		alert("Sila masukkan \"Tarikh Bicara\" terlebih dahulu.");
  		document.${formName}.txdTarikhBicara.focus(); 
		return;
	}
		else if(date1 < trMOHON)
	{
	   	alert("Sila pastikan tarikh bicara tidak kurang dari tarikh mohon.");
		document.${formName}.txdTarikhBicara.focus();
		return;
	} 
	else if(document.${formName}.txdTarikhNotis.value == ""){
		alert("Sila masukkan \"Tarikh Notis\" terlebih dahulu.");
	  	document.${formName}.txdTarikhNotis.focus(); 
		return;
	}
	else if(date2 > date1){
	   	alert("Sila pastikan tarikh notis tidak lebih dari tarikh bicara.");
		document.${formName}.txdTarikhNotis.focus();
		return;
	}  
	/* else if (document.${formName}.edityes.value == "no"){//aishah tutup
		if(date2 < currentTime){
	   	alert("Sila pastikan tarikh notis tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhNotis.focus();
		return;
	} 
	}*/
	else if(date2 > dateHidden){
		alert("Sila pastikan tarikh notis tidak boleh dalam lingkungan 7 hari sebelum tarikh bicara.");
	 	document.${formName}.txdTarikhNotis.focus();
	 	return;
	}
	else if(date2 < trMOHON){
	   	alert("Sila pastikan tarikh notis tidak kurang dari tarikh mohon.");
		document.${formName}.txdTarikhNotis.focus();
		return;
	} 
	else if(document.${formName}.txtMasaBicara.value == ""){
		alert("Sila masukkan \"Masa Bicara\" terlebih dahulu.");
  		document.${formName}.txtMasaBicara.focus(); 
	return;
	}

	//validation 4angka 
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan selengkapnya");
		document.${formName}.txtMasaBicara.focus();
	}
	//validation max & min jam
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam > 12) || (ValJam < 01))) {
		alert("Sila masukkan \"Masa Bicara\" dalam format 12jam");
		document.${formName}.txtMasaBicara.focus();
	}
	
	//validation max minit
	else if (document.${formName}.txtMasaBicara.value != "" && (ValMinit > 59)) {
		alert("Sila masukkan \"Masa Bicara\" dengan betul");
		document.${formName}.txtMasaBicara.focus();
	}
	
	//validation masa bicara 0830
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam == 05) || (ValJam == 06) || (ValJam == 07))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.txtMasaBicara.focus();
	}
	else if (document.${formName}.txtMasaBicara.value != "" && ((ValJam == 08 && ValMinit < 30) || (ValJam == 04 && ValMinit > 30))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.txtMasaBicara.focus();
	}

	//jenis waktu
	else if(document.${formName}.socJenisWaktu.value == "0" || document.${formName}.socJenisWaktu.value == ""){
		alert("Sila pilih \"Jenis Waktu\" terlebih dahulu.");
  		document.${formName}.socJenisWaktu.focus(); 
	return;
	}

	//validation masa dan jenis (utk pagi)
	else if ((ValJam == 08 || ValJam == 09 || ValJam == 10 || ValJam == 11) && (document.${formName}.socJenisWaktu.value != "1") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}

	//validation masa dan jenis (utk tengahari)
	else if (ValJam == 12 && (document.${formName}.socJenisWaktu.value != "2") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}
	
	//validation masa dan jenis (utk petang)
	else if ((ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04) && (document.${formName}.socJenisWaktu.value != "3") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}
	
	/*	
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) < 8)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.charAt(0) == 2) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 8) && (document.${formName}.txtMasaBicara.value.charAt(2) < 3)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
		else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) > 6)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
		else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) > 3))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
		else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) == 3) && (document.${formName}.txtMasaBicara.value.charAt(3) > 0))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
*/	
/*	
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) > 2) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}	
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1) && (document.${formName}.txtMasaBicara.value.charAt(1) >9 ) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}	
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 2) && (document.${formName}.txtMasaBicara.value.charAt(1) > 3) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(2) > 5) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(3) > 9) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}	
*/
	
	else if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih/masukkan \"Tempat Bicara\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}

    
/*	
	else if(document.${formName}.TGAlamatBicara1.value == "" && document.${formName}.TGAlamatBicara2.value == "" && document.${formName}.TGAlamatBicara3.value == ""){
		alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
  		document.${formName}.TGAlamatBicara1.focus(); 
		return;
	}
*/
    
   	else if (document.${formName}.jenisPejabat[2].checked == true 
    && (document.${formName}.TGAlamatBicara1.value == "" && document.${formName}.TGAlamatBicara2.value == "" && 	
    	document.${formName}.TGAlamatBicara3.value == "")) {  

           alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
           document.${formName}.TGAlamatBicara1.focus(); 
           return;      
    }  
    
    
	else if(document.${formName}.TGPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.TGPoskod.focus(); 
	return;
	}	
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri Bicara\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
	return;
	}	
	else if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	
	}else if (ob==false){
		
		alert("Sila pilih orang berkepentingan");
		return;
		
	}else if (jenisSerahChecked==false){
	
		alert("Sila pilih jenis serahan Laporan Notis");
		return;
		
	}else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanNotisTambah";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
		document.${formName}.submit();
	}
}
function updateNotis() {
	var numbers = /^[-+]?[0-9]+$/; 
    
	var masa = document.${formName}.editMasaBicara.value;
	var ValJam = masa.substring(0,2);
	var ValMinit = masa.substring(2,4);

	var str1  = document.getElementById("editTarikhBicara").value;

	var a = str1.substring(0,2);
	var b = str1.substring(3,5);
   	var c = str1.substring(6,10);
   	
	var dt1x   = parseInt(str1.substring(0,2),10)-7;
   	var mon1x  = parseInt(str1.substring(3,5),10)-1;
   	var yr1x   = parseInt(str1.substring(6,10),10);

	var myDate=new Date(yr1x, mon1x, dt1x);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhNotis = "";
	if(document.${formName}.editMasaBicara.value.match(numbers))  
    {  
   		
   		}
	else
	{
		alert("Sila masukkan hanya nombor sahaja di dalam medan \"Masa Bicara\".");
  		document.${formName}.editMasaBicara.focus(); 
	return;  
	}

    
	if(month>=10){
		if(day>=10){
			tarikhNotis = day + "/" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhNotis = day + "/0" + month + "/" + year;	
		}else{
			tarikhNotis = "0"+ day + "/0" + month + "/" + year;	
		}
	}

	var dtH   = parseInt(tarikhNotis.substring(0,2),10);
   	var monH  = parseInt(tarikhNotis.substring(3,5),10);
   	var yrH   = parseInt(tarikhNotis.substring(6,10),10);


	
	var dat1=document.${formName}.editTarikhBicara
	var dat2=document.${formName}.editTarikhNotis	
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   	//var str1  = document.getElementById("editTarikhBicara").value;
   	var str2  = document.getElementById("editTarikhNotis").value;
   	var str3  = document.getElementById("tarikhMohon").value;

	//var txdTarikhNotisHIDDEN  = document.getElementById("txdTarikhNotisHIDDEN").value;

   	//var dtH   = parseInt(txdTarikhNotisHIDDEN.substring(0,2),10);
   	//var monH  = parseInt(txdTarikhNotisHIDDEN.substring(3,5),10);
   	//var yrH   = parseInt(txdTarikhNotisHIDDEN.substring(6,10),10);
   	
   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);
   
   	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);

   	var dateHidden = new Date(yrH, monH, dtH);
   	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(yr2, mon2-1, dt2+1);
	var trMOHON = new Date(yr3, mon3-1, dt3);
	
	//sini
	var ob = true;
	if ($jquery('input[name="idsOB"]').length > 0){
		
		if($jquery('input[name="idsOB"]:checked').length == 0){
			ob = false;
		}
	}
	
	//aishahlatip
	var statusPNB= $jquery('#statusPNB_hidden').val();
	var listPenerimaNotis = $jquery('#listPenerimaNotis_hidden').val();
	var jenisSerahChecked = true;	

	if ($jquery('input[name="idob_hidden"]').length > 0){
		
		if($jquery('input:radio[name^=radioJenisSerah]:checked').length == 0){
			
			if(statusPNB=="no"){
				if(listPenerimaNotis<0){
			
					jenisSerahChecked = false;
				}else{
					jenisSerahChecked = true;
				}
			}else{
				jenisSerahChecked = true;
			}
		}
	}
	
	
	if (document.${formName}.editPoskod.value != "" && document.${formName}.editPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.editPoskod.focus();
	}
	else if(document.${formName}.editTarikhBicara.value == ""){
		alert("Sila masukkan \"Tarikh Bicara\" terlebih dahulu.");
  		document.${formName}.editTarikhBicara.focus(); 
		return;
	}
	
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh bicara tidak kurang dari tarikh mohon.");
	 	document.${formName}.editTarikhBicara.focus();
	 	return;
	} 
	else if(document.${formName}.editTarikhNotis.value == ""){
		alert("Sila masukkan \"Tarikh Notis\" terlebih dahulu.");
  		document.${formName}.editTarikhNotis.focus(); 
	return;
	}
	else if(date2 > date1){
   		alert("Sila pastikan tarikh notis tidak lebih dari tarikh bicara.");
	 	document.${formName}.editTarikhNotis.focus();
	 	return;
	} 
	else if (document.${formName}.edityes.value == "yes" && date2 < currentTime){
		
	   	alert("Sila pastikan tarikh notis tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhNotis.focus();
		return;
	
	}
	else if(date2 > dateHidden){
		alert("Sila pastikan tarikh notis tidak boleh dalam lingkungan 7 hari sebelum tarikh bicara.");
	 	document.${formName}.editTarikhNotis.focus();
	 	return;
	}
	else if(date2 < trMOHON){
   		alert("Sila pastikan tarikh notis tidak kurang dari tarikh mohon.");
	 	document.${formName}.editTarikhNotis.focus();
	 	return;
	} 
	else if(document.${formName}.editTarikhBicara.value != document.${formName}.editTarikhBicaraHidden.value && document.${formName}.sebab_pinda_tarikh.value == ""){
		alert("Sila masukkan alasan pindaan tarikh perbicaraan.");
		document.${formName}.sebab_pinda_tarikh.focus();
		return
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(document.${formName}.editMasaBicara.value == ""){
		alert("Sila masukkan \"Masa Bicara\" terlebih dahulu.");
  		document.${formName}.editMasaBicara.focus(); 
	return;
	}

	//validation 4angka 
	else if (document.${formName}.editMasaBicara.value != "" && document.${formName}.editMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan selengkapnya");
		document.${formName}.editMasaBicara.focus();
	}
	//validation max & min jam
	else if (document.${formName}.editMasaBicara.value != "" && ((ValJam > 12) || (ValJam < 01))) {
		alert("Sila masukkan \"Masa Bicara\" dalam format 12jam");
		document.${formName}.editMasaBicara.focus();
	}
	
	//validation max minit
	else if (document.${formName}.editMasaBicara.value != "" && (ValMinit > 59)) {
		alert("Sila masukkan \"Masa Bicara\" dengan betul");
		document.${formName}.editMasaBicara.focus();
	}
	
	//validation masa bicara 0830
	else if (document.${formName}.editMasaBicara.value != "" && ((ValJam == 05) || (ValJam == 06) || (ValJam == 07))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.editMasaBicara.focus();
	}
	else if (document.${formName}.editMasaBicara.value != "" && ((ValJam == 08 && ValMinit < 30) || (ValJam == 04 && ValMinit > 30))) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.editMasaBicara.focus();
	}

	//jenis waktu
	else if(document.${formName}.socJenisWaktu.value == "0" || document.${formName}.socJenisWaktu.value == ""){
		alert("Sila pilih \"Jenis Waktu\" terlebih dahulu.");
  		document.${formName}.socJenisWaktu.focus(); 
	return;
	}

	//validation masa dan jenis (utk pagi)
	else if ((ValJam == 08 || ValJam == 09 || ValJam == 10 || ValJam == 11) && (document.${formName}.socJenisWaktu.value != "1") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}

	//validation masa dan jenis (utk tengahari)
	else if (ValJam == 12 && (document.${formName}.socJenisWaktu.value != "2") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}
	
	//validation masa dan jenis (utk petang)
	else if ((ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04) && (document.${formName}.socJenisWaktu.value != "3") ) {
		alert("Sila pastikan \"Masa Bicara\" dari 0830 pagi hingga 0430 petang");
		document.${formName}.socJenisWaktu.focus();
	}
	

/*	
		else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.editMasaBicara.value.charAt(1) < 8)) {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
		else if (document.${formName}.editMasaBicara.value != "" && document.${formName}.editMasaBicara.value.charAt(0) == 2) {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
		else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.editMasaBicara.value.charAt(1) == 8) && (document.${formName}.editMasaBicara.value.charAt(2) < 3)) {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
		else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.editMasaBicara.value.charAt(1) > 6)) {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
		else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.editMasaBicara.value.charAt(1) == 6) && (document.${formName}.editMasaBicara.value.charAt(2) > 3))  {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
		else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.editMasaBicara.value.charAt(1) == 6) && (document.${formName}.editMasaBicara.value.charAt(2) == 3) && (document.${formName}.editMasaBicara.value.charAt(3) > 0))  {
			alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
			document.${formName}.editMasaBicara.focus();
		return; 
		}
	else if (document.${formName}.editMasaBicara.value != "" && document.${formName}.editMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	} 
	else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) > 2) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	}	
	else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 1) && (document.${formName}.editMasaBicara.value.charAt(1) >9 ) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	}	
	else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(0) == 2) && (document.${formName}.editMasaBicara.value.charAt(1) > 3) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(2) > 5) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.editMasaBicara.value != "" && (document.${formName}.editMasaBicara.value.charAt(3) > 9) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		editMasaBicara.focus();
		return; 
	}
*/		
	else if(document.${formName}.editTempatBicara.value == ""){
		alert("Sila pilih/masukkan \"Tempat Bicara\" terlebih dahulu.");
  		document.${formName}.editTempatBicara.focus(); 
		return;
	}
	
/*	
	else if(document.${formName}.editAlamatBicara1.value == "" && document.${formName}.editAlamatBicara2.value == "" && document.${formName}.editAlamatBicara3.value == ""){
		alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
  		document.${formName}.editAlamatBicara1.focus(); 
	return;
	}
	
*/

    else if (document.${formName}.editjenisPejabat[2].checked == true && (document.${formName}.editAlamatBicara1.value == "" && document.${formName}.editAlamatBicara2.value == "" && document.${formName}.editAlamatBicara3.value == "") ) {
            alert("Sila masukkan \"Alamat Bicara\" terlebih dahulu.");
            document.${formName}.editAlamatBicara1.focus(); 
            return;      
    } 
	

	else if(document.${formName}.editPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.editPoskod.focus(); 
	return;
	}	
	else if(document.${formName}.editNegeri.value == ""){
		alert("Sila pilih \"Negeri Bicara\" terlebih dahulu.");
  		document.${formName}.editNegeri.focus(); 
	return;
	}	
	else if(document.${formName}.editPegawai.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.editPegawai.focus(); 
	return;
	}
	else if (ob==false){
		
		alert("Sila pilih orang berkepentingan");
		return;
		
	}else if (jenisSerahChecked==false){
		
		alert("Sila pilih jenis serahan Laporan Notis");
		return;
		
	}
	
	else
	{

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "updateNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
	}
}
function kemaskini() {
	document.${formName}.command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();
}
function kemaskiniNotis() {
	document.${formName}.command.value = "semakPenerimaNotis";
	document.${formName}.command2.value = "kemaskiniNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis"; 
	document.${formName}.submit();
}
function maklumatPenjagaAdd(id_ob,nama_ob) {
	document.${formName}.id_ob.value = id_ob;
	document.${formName}.nama_ob.value = nama_ob;
	document.${formName}.command.value = "maklumatPenjagaAdd";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
}
function batalKemaskiniNotis(id_semaknotisobdtl) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_semaknotisobdtl.value = id_semaknotisobdtl;
	document.${formName}.command.value = "semakPenerimaNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
}
function semakPenerimaNotis(id_semaknotisobdtl) {
	document.${formName}.id_semaknotisobdtl.value = id_semaknotisobdtl;
	document.${formName}.command.value = "semakPenerimaNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
}
function batal(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakWithData";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "kembali";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();
}
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}
function NotisNO() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.command.value = "semakNoData";
	document.${formName}.submit();
}
function NotisWITH() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.command.value = "semakWithData";
	document.${formName}.submit();
}
function TukarNotis() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.command.value = "tukarNotis";
	document.${formName}.submit();
}
function Laporan() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.command.value = "semakDataLaporan";
	document.${formName}.submit();
}
function TindakanPeg() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.command.value = "tindakanPeg";
	document.${formName}.submit();
}
function setSerahTangan(){
	document.getElementById("pos1").disabled=true;
	document.getElementById("pos2").disabled=true;
	document.getElementById("pos1").checked=false;
	document.getElementById("pos2").checked=false;
	document.${formName}.txtNoDaftarPos.value = "";
	document.${formName}.txtNoDaftarPos.disabled = true;
	
	document.${formName}.txdTarikhHantar.value = "";	
	document.${formName}.txtAlamatEmel.value = "";
	document.${formName}.txdTarikhHantar.disabled = true;	
	document.${formName}.txtAlamatEmel.disabled = true;
	
	document.${formName}.imejCal.style.visibility = 'hidden';
// 	document.${formName}.cmdHantarEmel.style.visibility = 'hidden';

	document.getElementById("serah1").disabled=false;
	document.getElementById("serah2").disabled=false;
	
	document.getElementById("dummy").checked=false;
	
	document.getElementById("pos1_2").disabled=true;
	//document.getElementById("pos2_2").disabled=true;
	document.getElementById("pos1_2").checked=false;
	//document.getElementById("pos2_2").checked=false;
	document.${formName}.txtNoDaftarPos_2.value = "";
}
function setPOS(){
	

	document.getElementById("serah1").disabled=true;
	document.getElementById("serah2").disabled=true;
	document.getElementById("serah1").checked=false;
	document.getElementById("serah2").checked=false;
	
	//document.${formName}.txtNoDaftarPos.disabled = false;
	document.${formName}.txdTarikhHantar.value = "";	
	document.${formName}.txtAlamatEmel.value = "";
	document.${formName}.txdTarikhHantar.disabled = true;	
	document.${formName}.txtAlamatEmel.disabled = true;

	document.${formName}.imejCal.style.visibility = 'hidden';
// 	document.${formName}.cmdHantarEmel.style.visibility = 'hidden';

	document.getElementById("pos1").disabled=false;
	document.getElementById("pos2").disabled=false;
	
	document.getElementById("dummy").checked=false;

	
	//aishah
	document.getElementById("pos1_2").disabled=true;
	//document.getElementById("pos2_2").disabled=true;
	document.getElementById("pos1_2").checked=false;
	//document.getElementById("pos2_2").checked=false;
	

	document.${formName}.txtNoDaftarPos_2.disabled=true;
	document.${formName}.txtNoDaftarPos_2.value = "";
}

function setPOS_2(){
	document.getElementById("serah1").disabled=true;
	document.getElementById("serah2").disabled=true;
	document.getElementById("serah1").checked=false;
	document.getElementById("serah2").checked=false;
	
	//document.${formName}.txtNoDaftarPos.disabled = false;
	document.${formName}.txdTarikhHantar.value = "";	
	document.${formName}.txtAlamatEmel.value = "";
	document.${formName}.txdTarikhHantar.disabled = true;	
	document.${formName}.txtAlamatEmel.disabled = true;
	
	document.${formName}.imejCal.style.visibility = 'hidden';
// 	document.${formName}.cmdHantarEmel.style.visibility = 'hidden';

	document.getElementById("pos1").disabled=true;
	document.getElementById("pos2").disabled=true;
	
	document.getElementById("dummy").checked=false;
	
	
	//aishah
	document.getElementById("pos1_2").disabled=false;
	//document.getElementById("pos2_2").disabled=false;
	
	
	document.${formName}.txtNoDaftarPos.disabled=true;
	//document.${formName}.txtNoDaftarPos_2.disabled=false;
	
	
}

function setEMEL(){
	document.getElementById("serah1").disabled=true;
	document.getElementById("serah2").disabled=true;
	document.getElementById("serah1").checked=false;
	document.getElementById("serah2").checked=false;
	
	document.${formName}.txdTarikhHantar.disabled = false;	
	document.${formName}.txtAlamatEmel.disabled = false;
	
	document.${formName}.imejCal.style.visibility = 'visible';
	
// 	document.${formName}.cmdHantarEmel.style.visibility = 'visible';
	
	document.getElementById("pos1").disabled=true;
	document.getElementById("pos2").disabled=true;
	document.getElementById("pos1").checked=false;
	document.getElementById("pos2").checked=false;
	document.${formName}.txtNoDaftarPos.value = "";
	document.${formName}.txtNoDaftarPos.disabled = true;
	
	document.getElementById("dummy").checked=true;
	
	
	document.${formName}.txtNoDaftarPos_2.value = "";
	
}
function enableT()
{
	document.${formName}.txtNamaPenerima.disabled = false;
	document.${formName}.myList.disabled = false;
	document.${formName}.txtNoKPBaru1.disabled = false;
	document.${formName}.txtNoKPBaru2.disabled = false;
	document.${formName}.txtNoKPBaru3.disabled = false;	

	document.${formName}.txtNoKPLama.disabled = false;
	
	document.${formName}.txtNoKPLain.disabled = false;
}
function enableText()
{
	document.${formName}.txtNoDaftarPos.disabled = false;
	document.${formName}.txtNoDaftarPos_2.disabled = true;

	document.${formName}.txtNamaPenerima.disabled = false;
	document.${formName}.myList.disabled = false;
	document.${formName}.txtNoKPBaru1.disabled = false;
	document.${formName}.txtNoKPBaru2.disabled = false;
	document.${formName}.txtNoKPBaru3.disabled = false;

	document.${formName}.txtNoKPLama.disabled = false;
	
	document.${formName}.txtNoKPLain.disabled = false;
	
}

function enableText_2()
{
	
	
	document.${formName}.txtNoDaftarPos.disabled = true;
	document.${formName}.txtNoDaftarPos_2.disabled = false;

	
	document.${formName}.txtNamaPenerima.disabled = false;
	document.${formName}.myList.disabled = false;
	document.${formName}.txtNoKPBaru1.disabled = false;
	document.${formName}.txtNoKPBaru2.disabled = false;
	document.${formName}.txtNoKPBaru3.disabled = false;

	document.${formName}.txtNoKPLama.disabled = false;
	
	document.${formName}.txtNoKPLain.disabled = false;
	
}


function enableTUpdate()
{
	document.${formName}.EDITtxtNamaPenerima.disabled = false;
	document.${formName}.EditMyList.disabled = false;
	document.${formName}.EDITtxtNoKPBaru1.disabled = false;
	document.${formName}.EDITtxtNoKPBaru2.disabled = false;
	document.${formName}.EDITtxtNoKPBaru3.disabled = false;

	document.${formName}.EDITtxtNoKPLama.disabled = false;
	document.${formName}.EDITtxtNoKPLain.disabled = false;
}
function enableTextUpdate()
{
	document.${formName}.txtNoDaftarPos.disabled = false;
	
	document.${formName}.EDITtxtNamaPenerima.disabled = false;
	document.${formName}.EditMyList.disabled = false;
	document.${formName}.EDITtxtNoKPBaru1.disabled = false;
	document.${formName}.EDITtxtNoKPBaru2.disabled = false;
	document.${formName}.EDITtxtNoKPBaru3.disabled = false;

	document.${formName}.EDITtxtNoKPLama.disabled = false;
	document.${formName}.EDITtxtNoKPLain.disabled = false;
	
}
function disabledTextUpdate(flag)
{
	
	if(flag=="1"){

		var nonAsterik = document.getElementById("nonAsterik"); 
		var withAsterik = document.getElementById("withAsterik"); 

		nonAsterik.style.display = "none"; 
		withAsterik.style.display = "inline"; 
	}
	
	document.${formName}.EDITtxtNoDaftarPos.disabled = true;
	document.${formName}.EDITtxtNoDaftarPos.value = "";

	document.${formName}.EDITtxtNamaPenerima.disabled = true;
	document.${formName}.EDITtxtNamaPenerima.value = "";

	document.${formName}.EditMyList.disabled = true;
	document.${formName}.EditMyList.value = "";

	document.${formName}.EDITtxtNoKPBaru1.disabled = true;
	document.${formName}.EDITtxtNoKPBaru1.value = "";
	document.${formName}.EDITtxtNoKPBaru2.disabled = true;
	document.${formName}.EDITtxtNoKPBaru2.value = "";
	document.${formName}.EDITtxtNoKPBaru3.disabled = true;
	document.${formName}.EDITtxtNoKPBaru3.value = "";

	document.${formName}.EDITtxtNoKPLama.disabled = true;
	document.${formName}.EDITtxtNoKPLama.value = "";

	document.${formName}.EDITtxtNoKPLain.disabled = true;
	document.${formName}.EDITtxtNoKPLain.value = "";
	
}
function disabledText(flag)
{
	
	if(flag=="1"){

		var nonAsterik = document.getElementById("nonAsterik"); 
		var withAsterik = document.getElementById("withAsterik"); 

		nonAsterik.style.display = "none"; 
		withAsterik.style.display = "inline"; 
	}
	
	document.${formName}.txtNoDaftarPos.disabled = true;
	document.${formName}.txtNoDaftarPos.value = "";

	document.${formName}.txtNamaPenerima.disabled = true;
	document.${formName}.txtNamaPenerima.value = "";

	document.${formName}.myList.disabled = true;
	document.${formName}.myList.value = "";
	
	document.${formName}.txtNoDaftarPos_2.disabled = true;
	document.${formName}.txtNoDaftarPos_2.value = "";


	
	document.${formName}.txtNoKPBaru1.disabled = true;
	document.${formName}.txtNoKPBaru1.value = "";
	document.${formName}.txtNoKPBaru2.disabled = true;
	document.${formName}.txtNoKPBaru2.value = "";
	document.${formName}.txtNoKPBaru3.disabled = true;
	document.${formName}.txtNoKPBaru3.value = "";

	document.${formName}.txtNoKPLama.disabled = true;
	document.${formName}.txtNoKPLama.value = "";

	document.${formName}.txtNoKPLain.disabled = true;
	document.${formName}.txtNoKPLain.value = "";

}
function simpanLaporan(param){

	var jeniskp = document.${formName}.jeniskp.value;
	
//	var negeri_code = document.${formName}.txtNoKPBaru2.value;
	
	var dob_code = document.${formName}.txtNoKPBaru1.value;
	if(dob_code.charAt(0)<3)
		{
		 var dum = "20";
		}
		else
		{
		var dum = "19";
		}

		
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		 var dt_dob   = parseInt(tt.substring(0,2),10);
	     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	     var yr_dob   = parseInt(tt.substring(6,10),10);
		 var date_dob = new Date(yr_dob, mon_dob, dt_dob);

	
			
	var dat1=document.${formName}.txdTarikhSerahan
	
	var str1  = document.getElementById("txdTarikhSerahan").value;
	var str3  = document.getElementById("tarikhMohon").value;
	
   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;

	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(year, month, day);
	var trMOHON = new Date(yr3, mon3, dt3);

	var checkSelected=false;

 	if(param>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.cbsemaks[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cbsemaks.checked)
    	{
			//total++;
			checkSelected=true; 
    	}
	}
 	
	var jenisSerahanValue="";
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorStatus.length;  i++){
	if (${formName}.sorStatus[i].checked)
	radioSelected = true;
	jenisSerahanValue=${formName}.sorStatus[i].value;
	}
	
	//alert(jenisSerahanValue);
	if (!radioSelected){
	alert("Sila pilih Jenis serahan terlebih dahulu.");
	return;
	}
	
	else if((document.getElementById("pos1").checked) && (document.${formName}.txtNoDaftarPos.value ==""))
		{
		alert("Sila masukkan No.surat daftar terlebih dahulu.");
		document.${formName}.txtNoDaftarPos.focus(); 
		return (false);
		}
	//nama penghantar notis tidak perlu dimasuukan kalau jenis serahan adalah by email
	else if(document.${formName}.txtNamaPenghantarNotis.value == "" && jenisSerahanValue!="5")
		{
		alert("Sila masukkan Nama Penghantar Notis terlebih dahulu.");
		document.${formName}.txtNamaPenghantarNotis.focus(); 
		return;
		}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if(document.${formName}.txdTarikhSerahan.value == "" && jenisSerahanValue!="5")
		{
		alert("Sila masukkan Tarikh Serahan terlebih dahulu.");
		document.${formName}.txdTarikhSerahan.focus(); 
		return;
		}
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh serahan tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru1.value==""))
		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
		return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru2.value==""))
 		{
 	
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
		return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru3.value==""))
 		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
 		document.${formName}.txtNoKPBaru3.focus();
		return; 
		}	
	else if (document.${formName}.txtNoKPBaru1.value!=""  && document.${formName}.txtNoKPBaru1.value.length < 6 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
		return; 
		}
	else if (document.${formName}.txtNoKPBaru2.value!="" && document.${formName}.txtNoKPBaru2.value.length < 2 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
		return; 
		}
	else if (document.${formName}.txtNoKPBaru3.value!="" && document.${formName}.txtNoKPBaru3.value.length < 4) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru3.focus();
		return; 
		}
	
/*	
	else if(date1 > date2){
   		alert("Sila pastikan tarikh serahan tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 	
*/	
		
	else if (document.${formName}.txtNoKPBaru1.value != "" && isIc(tt)==false){
		document.${formName}.txtNoKPBaru1.focus()
		return;
	}
/*
	else if(document.${formName}.txtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && 
			negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && 
			negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && 
			negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && 
			negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && 
			negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && 
			negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && 
			negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && 
			negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && 
			negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
			negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 	negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  	negeri_code!="15" && negeri_code!="58" &&
		    negeri_code!="16" && 
		    negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	    
	{
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.txtNoKPBaru2.focus()
		return;
	
	}
*/
	else if((document.getElementById("serah2").checked) && (document.${formName}.txtCatatan.value ==""))
	{
		alert("Sila masukkan catatan untuk Surat Akuan Bersumpah terlebih dahulu.");
		document.${formName}.txtCatatan.focus(); 
		return;
	}
	else if(!checkSelected)
	{
	alert("Sila pilih orang berkepentingan terlebih dahulu.");
	return;
	}
	else{	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "simpanLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();

	}

}

function simpanTambahanLaporan(param){
	//alert("sini");

	var dat1=document.${formName}.txdTarikhSerahan

	var str1  = document.getElementById("txdTarikhSerahan").value;
	var str3  = document.getElementById("tarikhMohon").value;

	//var hiddencb  = document.getElementById("hiddencbsemak").value;

   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

   	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;

	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(year, month, day);
	var trMOHON = new Date(yr3, mon3, dt3);

//	var negeri_code = document.${formName}.txtNoKPBaru2.value;
	var dob_code = document.${formName}.txtNoKPBaru1.value;
	if(dob_code.charAt(0)<3)
		{
		 var dum = "20";
		}
		else
		{
		var dum = "19";
		}

		
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		 var dt_dob   = parseInt(tt.substring(0,2),10);
	     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	     var yr_dob   = parseInt(tt.substring(6,10),10);
		 var date_dob = new Date(yr_dob, mon_dob, dt_dob);

			
	var checkSelected=false;

 	if(param>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.cbsemaks[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cbsemaks.checked)
    	{
			//total++;
			checkSelected=true; 
    	}
	}

 	var jenisSerahanValue="";
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorStatus.length;  i++){
	if (${formName}.sorStatus[i].checked)
	radioSelected = true;
	jenisSerahanValue=${formName}.sorStatus[i].value;
	}

	if (!radioSelected){
	alert("Sila pilih Jenis serahan terlebih dahulu.");
	return;
	}
	
	else if((document.getElementById("pos1").checked) && (document.${formName}.txtNoDaftarPos.value ==""))
		{
		alert("Sila masukkan No.surat daftar terlebih dahulu.");
		document.${formName}.txtNoDaftarPos.focus(); 
		return (false);
		}
	else if(document.${formName}.txtNamaPenghantarNotis.value == "" && jenisSerahanValue!="5")
		{
		alert("Sila masukkan Nama Penghantar Notis terlebih dahulu.");
		document.${formName}.txtNamaPenghantarNotis.focus(); 
		return;
		}
	else if(document.${formName}.txdTarikhSerahan.value == "" && jenisSerahanValue!="5")
		{
		alert("Sila masukkan Tarikh Serahan terlebih dahulu.");
		document.${formName}.txdTarikhSerahan.focus(); 
		return;
		}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru1.value==""))
		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
		return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru2.value==""))
 		{
 	
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
		return; 
		} 
	else if ((document.${formName}.txtNoKPBaru1.value!="" || document.${formName}.txtNoKPBaru2.value!="" || document.${formName}.txtNoKPBaru3.value!="") && (document.${formName}.txtNoKPBaru3.value==""))
 		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
 		document.${formName}.txtNoKPBaru3.focus();
		return; 
		}	
	else if (document.${formName}.txtNoKPBaru1.value!=""  && document.${formName}.txtNoKPBaru1.value.length < 6 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru1.focus();
		return; 
		}
	else if (document.${formName}.txtNoKPBaru2.value!="" && document.${formName}.txtNoKPBaru2.value.length < 2 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru2.focus();
		return; 
		}
	else if (document.${formName}.txtNoKPBaru3.value!="" && document.${formName}.txtNoKPBaru3.value.length < 4) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.txtNoKPBaru3.focus();
		return; 
		}
	else if (document.${formName}.txtNoKPBaru1.value != "" && isIc(tt)==false){
		document.${formName}.txtNoKPBaru1.focus()
		return;
	}
/*	else if(document.${formName}.txtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && 
			negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && 
			negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && 
			negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && 
			negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && 
			negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && 
			negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && 
			negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && 
			negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && 
			negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
			negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 	negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  	negeri_code!="15" && negeri_code!="58" &&
		    negeri_code!="16" && 
		    negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.txtNoKPBaru2.focus()
		return;
	
	}
*/	else if((document.getElementById("serah2").checked) && (document.${formName}.txtCatatan.value ==""))
	{
		alert("Sila masukkan catatan untuk Surat Akuan Bersumpah terlebih dahulu.");
		document.${formName}.txtCatatan.focus(); 
		return;
	}
	else if(!checkSelected)
		{
		alert("Sila pilih orang berkepentingan terlebih dahulu.");
		return;
		}
	else if ((document.${formName}.txtNamaPenghantarNotis.value != "") && (document.${formName}.txtNamaPenghantarNotis.value == "99999" ) && (document.${formName}.lain2PeghantarNotis.value =="")){
		
		alert("Sila pilih masukkan Nama lain-lain bagi Nama Penghantar Notis.");
		document.${formName}.lain2PeghantarNotis.focus()
		return;

	}

/*	else if(date1 > date2){
   		alert("Sila pastikan tarikh serahan tidak lebih dari tarikh hari ini.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 	
*/	
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh serahan tidak kurang dari tarikh mohon.");
	 	document.${formName}.txdTarikhSerahan.focus();
	 	return;
	} 
	else{
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "simpanTambahanLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();

	}

}

/*
function getName()
{
var mylist=document.getElementById("myList");
document.getElementById("namaPenerima").value=mylist.options[mylist.selectedIndex].text;
document.${formName}.myList.value = "";
}
*/
function updateLaporan(){
	
	//alert("EDITsorJenisSerah==="+document.${formName}.EDITsorJenisSerah.value);

	var dat1=document.${formName}.EDITtxdTarikhSerahan
	
	var str1  = document.getElementById("EDITtxdTarikhSerahan").value;
	var str3  = document.getElementById("tarikhMohon").value;

   	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);

 	var dt3   = parseInt(str3.substring(0,2),10);
   	var mon3  = parseInt(str3.substring(3,5),10);
   	var yr3   = parseInt(str3.substring(6,10),10);
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;

	var date1 = new Date(yr1, mon1, dt1);
   	var date2 = new Date(year, month, day);
   	var trMOHON = new Date(yr3, mon3, dt3);

//   	var negeri_code = document.${formName}.EDITtxtNoKPBaru2.value;
	var dob_code = document.${formName}.EDITtxtNoKPBaru1.value;
	if(dob_code.charAt(0)<3)
		{
		 var dum = "20";
		}
		else
		{
		var dum = "19";
		}

		
	var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		 var dt_dob   = parseInt(tt.substring(0,2),10);
	     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
	     var yr_dob   = parseInt(tt.substring(6,10),10);
		 var date_dob = new Date(yr_dob, mon_dob, dt_dob);

	var radioSelected = false;
	for (i = 0;  i < ${formName}.EDITsorStatus.length;  i++){
	if (${formName}.EDITsorStatus[i].checked)
	radioSelected = true;
	}

	if (!radioSelected){
	alert("Sila pilih Jenis serahan terlebih dahulu.");
	return;
	}
	
	else if((document.getElementById("pos1").checked) && (document.${formName}.EDITtxtNoDaftarPos.value ==""))
		{
		alert("Sila masukkan No.surat daftar terlebih dahulu.");
		document.${formName}.EDITtxtNoDaftarPos.focus(); 
		return (false);
		}
	else if(document.${formName}.EDITtxtNamaPenghantarNotis.value == "")
		{
		alert("Sila masukkan Nama Penghantar Notis terlebih dahulu.");
		document.${formName}.EDITtxtNamaPenghantarNotis.focus(); 
		return;
		}
	else if(document.${formName}.EDITtxdTarikhSerahan.value == "")
		{
		alert("Sila masukkan Tarikh Serahan terlebih dahulu.");
		document.${formName}.EDITtxdTarikhSerahan.focus(); 
		return;
		}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru1.value==""))
		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.EDITtxtNoKPBaru1.focus();
		return; 
		} 
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru2.value==""))
 		{
 	
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.EDITtxtNoKPBaru2.focus();
		return; 
		} 
	else if ((document.${formName}.EDITtxtNoKPBaru1.value!="" || document.${formName}.EDITtxtNoKPBaru2.value!="" || document.${formName}.EDITtxtNoKPBaru3.value!="") && (document.${formName}.EDITtxtNoKPBaru3.value==""))
 		{
 		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
 		document.${formName}.EDITtxtNoKPBaru3.focus();
		return; 
		}	
	else if (document.${formName}.EDITtxtNoKPBaru1.value!=""  && document.${formName}.EDITtxtNoKPBaru1.value.length < 6 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.EDITtxtNoKPBaru1.focus();
		return; 
		}
	else if (document.${formName}.EDITtxtNoKPBaru2.value!="" && document.${formName}.EDITtxtNoKPBaru2.value.length < 2 ) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.EDITtxtNoKPBaru2.focus();
		return; 
		}
	else if (document.${formName}.EDITtxtNoKPBaru3.value!="" && document.${formName}.EDITtxtNoKPBaru3.value.length < 4) 
		{
		alert("Sila masukkan nombor kad pengenalan penjaga sepenuhnya");
		document.${formName}.EDITtxtNoKPBaru3.focus();
		return; 
		}
	else if(date1 < trMOHON){
   		alert("Sila pastikan tarikh serahan tidak kurang dari tarikh mohon.");
	 	document.${formName}.EDITtxdTarikhSerahan.focus();
	 	return;
	} 
	else if (document.${formName}.EDITtxtNoKPBaru1.value != "" && isIc(tt)==false){
		document.${formName}.EDITtxtNoKPBaru1.focus()
		return;
	}
	else if ((document.${formName}.EDITtxtNamaPenghantarNotis.value != "") && (document.${formName}.EDITtxtNamaPenghantarNotis.value == "99999" ) && (document.${formName}.lain2PeghantarNotis.value =="")){
		
		alert("Sila pilih masukkan Nama lain-lain bagi Nama Penghantar Notis.");
		document.${formName}.lain2PeghantarNotis.focus()
		return;

	}
/*	else if(document.${formName}.EDITtxtNoKPBaru2.value != "" &&(negeri_code!="01" && negeri_code!="21" && 
			negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && 
			negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && 
			negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && 
			negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && 
			negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && 
			negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && 
			negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && 
			negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && 
			negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" &&
			negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && 
		 	negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" &&
		  	negeri_code!="15" && negeri_code!="58" &&
		    negeri_code!="16" && 
		    negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99"))
	{
		alert("Sila masukkan kod negeri yang sah");
		document.${formName}.EDITtxtNoKPBaru2.focus()
		return;
	
	}
*/	else if((document.getElementById("serah2").checked) && (document.${formName}.EDITtxtCatatan.value ==""))
	{
		alert("Sila masukkan catatan untuk Surat Akuan Bersumpah terlebih dahulu.");
		document.${formName}.EDITtxtCatatan.focus(); 
		return;
	}
	else{
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "semakPenerimaNotis";
	document.${formName}.command2.value = "kemaskiniNotis";
	document.${formName}.command3.value = "updateLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.submit();

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

function checkTarikhPinda() {	
	if (document.${formName}.editTarikhBicara.value != document.${formName}.editTarikhBicaraHidden.value)
		{
		document.${formName}.tarikh_bicara_dahulu.value = document.${formName}.editTarikhBicaraHidden.value;
		}
		
	
}
function checkEventDate(dateEvent,types){
// 	var dat1=document.${formName}.txdTarikhBicara
// 	var dat2=document.${formName}.txdTarikhNotis
	if(types=='new'){
		document.${formName}.txdTarikhBicara.value = document.getElementById("txdTarikhBicara").value;
		document.${formName}.txdTarikhNotis.value = document.getElementById("txdTarikhNotis").value;
	}else{
		document.${formName}.editTarikhBicara.value = document.getElementById("editTarikhBicara").value;
		document.${formName}.editTarikhNotis.value = document.getElementById("editTarikhNotis").value;
	}
	document.${formName}.dateEvent.value = dateEvent;
// 	doAjaxCall${formName}("doValidatePublicDate");
	
	document.${formName}.command.value = "doValidatePublicDate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&dateEvent="+dateEvent; 
	document.${formName}.submit();
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
function cetakSuratIringanNotisTerengganu(NO_FAIL,idpejabatjkptg) {
	
	var idpegawai = document.${formName}.editPegawai.value;
	
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpegawai="+idpegawai+"&idpejabatjkptg="+idpejabatjkptg+"&report=SuratIringanNotisTerengganu&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratIringanNotis(NO_FAIL,idpejabatjkptg) {

	var idpegawai = document.${formName}.editPegawai.value;
	
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpegawai="+idpegawai+"&idpejabatjkptg="+idpejabatjkptg+"&report=SuratIringanNotis&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBuktiPenyampaian(NO_FAIL,tarikhnotis,idfail,idperbicaraan) {
    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaian?nofail="+NO_FAIL+"&idperbicaraan="+idperbicaraan+"&idfail="+idfail+"&tarikhnotis="+tarikhnotis;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratPanduanBicara(idfail,idpermohonan) {
    var url = "../servlet/ekptg.report.ppk.SuratPanduanBicara?idpermohonan="+idpermohonan+"&idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSlipPendengaran(idfail,NO_FAIL) {

	
	
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idfail="+idfail+"&report=SlipPendengaran";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakNotaPerbicaraan(NO_FAIL,id_perbicaraan,idfail) {

	var tarikhmohon = document.getElementById("tarikhMohon").value; 

	var dt1   = parseInt(tarikhmohon.substring(0,2),10);
   	var mon1  = parseInt(tarikhmohon.substring(3,5),10);
   	var yr1   = parseInt(tarikhmohon.substring(6,10),10);
   
	var dt2 = parseInt("01",10);
	var mon2 = parseInt("09",10);
	var yr2 = parseInt("2009",10);
	 	
   	var date1 = new Date(yr1, mon1, dt1);
    var tarikhsmbln = new Date(yr2, mon2, dt2);

    var flag = "";
	if(date1<tarikhsmbln){
		flag = 0;
	}else{
		flag = 1;
	}
	
	var tajuk = "PERMOHONAN DI BAWAH SEKSYEN 8 (APPK(1998))";
    var url = "../servlet/ekptg.report.ppk.NotaPerbicaraan?nofail="+NO_FAIL+"&flag="+flag+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&tajuk="+tajuk;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangD(NO_FAIL,id_perbicaraan,idfail,signedData) {

	//alert("NO_FAIL : "+NO_FAIL+" id_perbicaraan : "+id_perbicaraan+" idfail :"+idfail );
	document.getElementById("cmdHPNB1").disabled = true;
	document.getElementById("signedData").value = signedData;//signedData
	
	var url = "../servlet/ekptg.report.ppk.BorangD?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&signedData="+signedData
	+"&flagVersion=hantarPNB";
    var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangD_PDF(NO_FAIL,id_perbicaraan,idfail) {
	//alert("NO_FAIL : "+NO_FAIL+" id_perbicaraan : "+id_perbicaraan+" idfail :"+idfail );
	
	var url = "../servlet/ekptg.report.ppk.BorangD?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&flagVersion=popupPNB";
    var hWnd = window.open(url,'Cetak','width=1200,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangD_X(NO_FAIL,id_perbicaraan,idfail,signedData) {

document.getElementById("signedData").value = signedData;
document.getElementById("cmdHPNB1").disabled = true;
console.log("cetakBorangD_X signedData :"+signedData);

	 document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&NO_FAIL="+NO_FAIL+"&id_perbicaraan="+id_perbicaraan+"&idfail="+idfail
	// +"&signedData="+signedData
	 +"&open_pupop=Y";
	document.${formName}.command.value = "tindakanPeg";
	document.${formName}.submit();

    
}
function cetakBorangDD(NO_FAIL,id_perbicaraan,idpermohonansimati,idsimati) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idsimati="+idsimati+"&idperbicaraan="+id_perbicaraan+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
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
function setTable2(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
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
function kembaliSenaraiFail(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.${formName}.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.${formName}.method="POST";
	document.${formName}.tarikhMohon.value = "";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function displayHTML(field) 
{
	var inf = field.value;
	win = window.open('', 'popup', 'resizable=yes,scrollbars=yes,toolbar=no,status=no');
	win.document.write("" + inf + "");
}
function setEventId(flag) {
	document.f.eventId.value = flag;
	}
/*
function trans_date1(t_d)
{if(t_d.length == 8){
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);
var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
document.${formName}.txdTarikhBicara.value = trans;
}else{
return;}
}
*/
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
function cetakAkuanBersumpah(idfail,idperbicaraan) {
	var url = "../servlet/ekptg.report.ppk.SuratAkuanBersumpah?idperbicaraan="+idperbicaraan;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function kembaliKeputusanPermohonan(idpermohonan,idpermohonansimati,tarikhmohon) {
	document.${formName}.action = "?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
	document.${formName}.method="POST";
	document.${formName}.idPermohonan.value = idpermohonan;
	document.${formName}.idpermohonansimati.value = idpermohonansimati;
	//document.${formName}.idSimati.value = idSimati;
	document.${formName}.tarikh_mohon.value = tarikhmohon;
	document.${formName}.submit();
}
function hideAsterik()
{
	var nonAsterik = document.getElementById("nonAsterik"); 
	var withAsterik = document.getElementById("withAsterik"); 

	nonAsterik.style.display = "inline"; 
	withAsterik.style.display = "none"; 
}
function validateJenisWaktu(elmnt,content) {

	var ValJam = content.substring(0,2);

	if(ValJam!=""){	
		
		if(ValJam >= 08 && ValJam <= 11){
			document.getElementById("socJenisWaktu").value = "1" ;
		}else if(ValJam == 12){
			document.getElementById("socJenisWaktu").value = "2" ;
		}else if(ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04){
			document.getElementById("socJenisWaktu").value = "3" ;
		}else{
			document.getElementById("socJenisWaktu").value = "0" ;
		}
		
	}else{
		document.getElementById("socJenisWaktu").value = "0" ;
	}	
	return;

}
function ForView(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.${formName}.submit();
}
function kemaskiniFail(idstatus,noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.${formName}.flagFromMyJsp.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function RPP(id_fail) 
{
   // var url = "../servlet/ekptg.report.ppk.RPP?nofail="+noFail;
  
   var id_perbicaraan = document.${formName}.id_perbicaraan.value;
   
    var url = "../servlet/ekptg.report.ppk.LaporanPenghantarNotis?nofail="+id_fail+ "&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script> 
    <script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script> 
    <script>
function doCheckAllOB(){    
    if (document.${formName}.allOB.checked == true){
        if (document.${formName}.idsOB.length == null){
            document.${formName}.idsOB.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idsOB.length; i++){
                document.${formName}.idsOB[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idsOB.length == null){
            document.${formName}.idsOB.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idsOB.length; i++){
                document.${formName}.idsOB[i].checked = false;
            }
        }
    }
}

function doUpdateCheckAllOB(){  
	var c = 0;
	if (document.${formName}.idsOB.length > 1){
		for (i = 0; i < document.${formName}.idsOB.length; i++){
			if (document.${formName}.idsOB[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsOB.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allOB.checked = false;
	} else {
		document.${formName}.allOB.checked = true;
	}				         
}
function doCheckAllPemiutang(){    
    if (document.${formName}.allPemiutang.checked == true){
        if (document.${formName}.idsPemiutang.length == null){
            document.${formName}.idsPemiutang.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idsPemiutang.length; i++){
                document.${formName}.idsPemiutang[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idsPemiutang.length == null){
            document.${formName}.idsPemiutang.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idsPemiutang.length; i++){
                document.${formName}.idsPemiutang[i].checked = false;
            }
        }
    }
}

function doCheckAllPenghutang(){    
    if (document.${formName}.allPenghutang.checked == true){
        if (document.${formName}.idsPenghutang.length == null){
            document.${formName}.idsPenghutang.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idsPenghutang.length; i++){
                document.${formName}.idsPenghutang[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idsPenghutang.length == null){
            document.${formName}.idsPenghutang.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idsPenghutang.length; i++){
                document.${formName}.idsPenghutang[i].checked = false;
            }
        }
    }
}

function doUpdateCheckAllPemiutang(){  
	var c = 0;
	if (document.${formName}.idsPemiutang.length > 1){
		for (i = 0; i < document.${formName}.idsPemiutang.length; i++){
			if (document.${formName}.idsPemiutang[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsPemiutang.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allPemiutang.checked = false;
	} else {
		document.${formName}.allPemiutang.checked = true;
	}				         
}

function doUpdateCheckAllPenghutang(){  
	var c = 0;
	if (document.${formName}.idsPenghutang.length > 1){
		for (i = 0; i < document.${formName}.idsPenghutang.length; i++){
			if (document.${formName}.idsPenghutang[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsPenghutang.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allPenghutang.checked = false;
	} else {
		document.${formName}.allPenghutang.checked = true;
	}				         
}


function checkCutiAm(datePick){


	var flagCuti = $jquery('#flag_cuti').val();	
	
	var array = new Array();
	array = datePick.split('/');
	var myDate = (array[1] + "/" + array[0] + "/" + array[2]);

	var d = new Date(myDate);
	var n = d.getDay();
	 
	 if(flagCuti==1){//jumaat,sabtu
		 if(n==5){//jumaat
			 $jquery('#cutiAm_div').show();
		 }else if(n==6){//sabtu
			 $jquery('#cutiAm_div').show();
		 }else{
			 $jquery('#cutiAm_div').hide();
		 }
	 
	 }
	 
	 if(flagCuti==2){//sabtu,ahad
		 if(n==0){//ahad
			 $jquery('#cutiAm_div').show();
		 }else if(n==6){//sabtu
			 $jquery('#cutiAm_div').show();
		 }else{
			 $jquery('#cutiAm_div').hide();
		 }
	 
	 }
	


}

	function sendDGcert(NO_FAIL,id_perbicaraan,idfail,id_permohonan,idpermohonansimati){		
		//commandw kegunaan dashboard
		//var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&commandw=sendDGCert";
		var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&command=sendDGCert";
	    /* var url = "../servlet/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&command=sendDGCert"; */
		var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	function verifyDGcert(NO_FAIL,id_perbicaraan,idfail,id_permohonan,idpermohonansimati){
		//commandw kegunaan dashboard
	    //var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&commandw=verify";
	    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&command=verify";
	    /* var url = "../servlet/ekptg.view.ppk.FrmIntegrasiDGCert?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&command=sendDGCert"; */
		var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	}
	
/* function semakMTPermohonan() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus(); 
}
 */
	

</script>

<script>

/*-----------------------------EMEL----------------------------*/
function doCheckAllOBemel(){    
    if (document.${formName}.allOBemel.checked == true){
        if (document.${formName}.idsOBemel.length == null){
            document.${formName}.idsOBemel.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idsOBemel.length; i++){
                document.${formName}.idsOBemel[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idsOBemel.length == null){
            document.${formName}.idsOBemel.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idsOBemel.length; i++){
                document.${formName}.idsOBemel[i].checked = false;
            }
        }
    }
}

function doUpdateCheckAllOBemel(){  
	var c = 0;
	if (document.${formName}.idsOBemel.length > 1){
		for (i = 0; i < document.${formName}.idsOBemel.length; i++){
			if (document.${formName}.idsOBemel[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsOBemel.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allOBemel.checked = false;
	} else {
		document.${formName}.allOBemel.checked = true;
	}				         
}

/*-----------------------------PNMB----------------------------*/
function doCheckAllOBpnmb(){    
    if (document.${formName}.allOBpnmb.checked == true){
        if (document.${formName}.idsOBpnmb.length == null){
            document.${formName}.idsOBpnmb.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idsOBpnmb.length; i++){
                document.${formName}.idsOBpnmb[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idsOBpnmb.length == null){
            document.${formName}.idsOBpnmb.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idsOBpnmb.length; i++){
                document.${formName}.idsOBpnmb[i].checked = false;
            }
        }
    }
}

function doUpdateCheckAllOBpnmb(){  
	var c = 0;
	if (document.${formName}.idsOBpnmb.length > 1){
		for (i = 0; i < document.${formName}.idsOBpnmb.length; i++){
			if (document.${formName}.idsOBpnmb[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsOBpnmb.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allOBpnmb.checked = false;
	} else {
		document.${formName}.allOBpnmb.checked = true;
	}				         
}

//----------------end PNMB--------------------------------------

//------------------serahan tangan------------------------------

function doCheckAllOBserahTgn(i,jenisSerah){    
   
	
	
}

function doUpdateCheckAllOBserahTgn(){  
	var c = 0;
	if (document.${formName}.idsOBserahTgn.length > 1){
		for (i = 0; i < document.${formName}.idsOBserahTgn.length; i++){
			if (document.${formName}.idsOBserahTgn[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idsOBserahTgn.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.allOBserahTgn.checked = false;
	} else {
		document.${formName}.allOBserahTgn.checked = true;
	}				         
}

//------------------end serahan tangan---------------------------------



//----------Borang D (emel)-------------


 function cetakBorangD_2(NO_FAIL,id_perbicaraan,idfail)  {
	    var url = "../servlet/ekptg.report.ppk.BorangD_2?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	
 function cetakBorandDByJenisSerahan(NO_FAIL,id_perbicaraan,idfail) {
		
	    
		var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupBorangDBySerahan?noFail="+NO_FAIL+"&idfail="+idfail+"&id_perbicaraan="+id_perbicaraan; 
		
		var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
</script>

 #if($viewNotis=="no")

<script>
 
 //alert("no");
function changeVal(){
	
	var idpenghantar = document.${formName}.txtNamaPenghantarNotis.value;
	
	
	//alert("idpenghantar=="+idpenghantar);
	if(idpenghantar == '99999'){
		document.${formName}.lain2PeghantarNotis.show();
		
	}else{
		document.${formName}.lain2PeghantarNotis.hide();
	}
	
}

</script>
#end


#if($viewNotis=="yes" && $editNotis=="yes")

<script>

//alert("yes");
	function changeVal(){

		var idpenghantar = document.${formName}.EDITtxtNamaPenghantarNotis.value;
		//alert("idpenghantar=="+idpenghantar);
		if(idpenghantar == '99999'){
			document.${formName}.lain2PeghantarNotis.show();
			
		}else{
			document.${formName}.lain2PeghantarNotis.hide();
		
			
		}
		
	}
	

</script>
#elseif($viewNotis=="yes")
<script>

//alert("yes");
	function changeVal(){

		var idpenghantar = document.${formName}.EDITtxtNamaPenghantarNotis.value;
		//alert("idpenghantar=="+idpenghantar);
		if(idpenghantar == '99999'){
			document.${formName}.lain2PeghantarNotis.show();
			document.${formName}.lain2PeghantarNotis.disabled = "disabled";
			
		}else{
			document.${formName}.lain2PeghantarNotis.hide();
		
			
		}
		
	}
	

</script>
#end





