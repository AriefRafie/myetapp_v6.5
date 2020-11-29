<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

#set($saizTxtPerkara="1000")
#set($saizTxtCatatan="500")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="userId" type="hidden" id="userId" value="$userId"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="namaPemohon" type="hidden" id="namaPemohon" value="$namaPemohon"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idPermohonanPenawaran" type="hidden" id="idPermohonanPenawaran" value="$idPermohonanPenawaran"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  <input name="idPermohonanPelepasan" type="hidden" id="idPermohonanPelepasan" value="$idPermohonanPelepasan"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
  <input name="idLuasKegunaan" type="hidden" id="idLuasKegunaan" value="$idLuasKegunaan"/>
  <input name="idKementerian" type="hidden" id="idKementerian" value="$idKementerian"/>
  <input name="idAgensi" type="hidden" id="idAgensi" value="$idAgensi"/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  
  
 
</p>

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/frmPLPHeader.jsp") </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENAWARAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN</li>
          <!-- #if ($idStatus == '1610207' || $idStatus == '1610208')
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
          #end -->
        </ul>
        <div class="TabbedPanelsContentGroup">
        <!-- START TAB MAKLUMAT TANAH  -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
              <tr>
				<td width="50%" valign="top">
              <table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Pegangan Hakmilik</td>
                <td width="1%">:</td>
                <td width="70%"> #if ($mode == 'update')
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" onBlur="doChangePeganganHakmilik1();" />
                  <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanah('$idKementerian','$idAgensi')">
                  #else
                  <input type="text" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
                  #end
                  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
                  <span class="style1">$errorPeganganHakmilik</span> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Lot</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noLot</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Luas Lot</td>
                <td>:</td>
                <td>$beanMaklumatTanah.luasLot</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Hakmilik</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noHakmilik</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Warta</td>
                <td>:</td>
                <td>$beanMaklumatTanah.noWarta</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Warta</td>
                <td>:</td>
                <td>$beanMaklumatTanah.tarikhWarta</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Mukim</td>
                <td>:</td>
                <td>$beanMaklumatTanah.mukim</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Daerah</td>
                <td>:</td>
                <td>$beanMaklumatTanah.daerah</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$beanMaklumatTanah.negeri
                  <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah" />
                  <input type="hidden" name="kodNegeriTanah" id="kodNegeriTanah" value="$beanMaklumatTanah.kodNegeriTanah" />                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kementerian</td>
                <td>:</td>
                <td>$beanMaklumatTanah.kementerian
                  <input type="hidden" name="idKementerian" id="idKementerian" value="$beanMaklumatTanah.idKementerian" />
                  <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian" />                </td>
              </tr>
              <tr>
                <td height="31">&nbsp;</td>
                <td>Agensi</td>
                <td>:</td>
                <td>$beanMaklumatTanah.agensi</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kegunaan Tanah</td>
                <td>:</td>
                <td>$beanMaklumatTanah.kegunaanTanah</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              </table>
              </td>
            <td width="50%" valign="top">
			<table width="100%"  cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>Syarat Nyata</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.syarat
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
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Sekatan Kepentingan</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.sekatan
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
					<td>&nbsp;</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Kegunaan Tanah</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.kegunaanTanah
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
					<td>&nbsp;</td>
		        </tr>
				<!-- <tr>
					<td>&nbsp;</td>
					<td>Kementerian</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.kementerian
					</td>
		        </tr>
				<tr>
					<td>&nbsp;</td>
					<td>Agensi</td>
					<td>:</td>
					<td>
						$beanMaklumatTanah.agensi
					</td>
		        </tr> -->
			</table>
			</td>
			</tr>
              #if ($mode == 'update')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
              <td>&nbsp;</td>
              <td>
                 ##if ($idStatus == '')
                 #if ($mode == 'view')
                  #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
                  <input type="button" name="cmdKmskiniTnh" id="cmdKmskiniTnh" value="Kemaskini" onClick="doKemaskini()"/>
                  <input type="button" name="cmdHapus2" id="cmdHapus2" value="Hapus" onClick="doHapus()"/>
                  #end
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  ##end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
                  <input type="button" name="cmdBatalKemaskiniTnh" id="cmdBatalKemaskiniTnh" value="Batal" onClick="doBatalKemaskini()"/>
                  #end
                  #else
                  <input type="button" name="cmdSimpanKemaskiniTnh" id="cmdSimpanKemaskiniTnh" value="Simpan" onClick="doSimpanKemaskiniMaklumatTnh()"/>
                  <input type="button" name="cmdBatalKemaskiniTnh" id="cmdBatalKemaskiniTnh" value="Batal" onClick="doBatalKemaskini()"/>
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/> 
                 <!-- <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/> -->
                 #end  </td>
              </tr>
              #end
            </table>
          </div>
          <!-- END TAB MAKLUMAT TANAH  -->
          <!-- START TAB MAKLUMAT PENAWARAN  -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatPenawaran in $BeanMaklumatPenawaran)
              <tr>
                <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td width="28%" valign="top">Luas Kegunaan</td>
                <td width="1%">:</td>
                <td width="70%">$selectLuasKegunaan</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Keluasan Asal</td>
                <td>:</td>
                <td>$beanMaklumatPenawaran.luasAsal $beanMaklumatPenawaran.keteranganLuasAsal HEKTAR
                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatPenawaran.luasAsal"/></td>
              </tr>
              #if ($idLuasKegunaan == '2')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Unit Luas</td>
                <td>:</td>
                <td>#parse("app/php2/unit_luas.jsp") </td>
              </tr>
              #if ($idLuas != '99999' && $idLuas != '')
              <tr>
                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                <td>Luas Ditawarkan</td>
                <td>:</td>
                <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/ >
                  #elseif ($idLuas == '7')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPenawaran.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonly class="$inputTextClass">
                  #elseif ($idLuas == '8' || $idLuas == '4')
                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPenawaran.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatPenawaran.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/>
                  #end </td>
              </tr>
              #end
              #end
              <tr>
                <td>&nbsp;</td>
                <td>Luas Bersamaan</td>
                <td>:</td>
                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatPenawaran.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Baki Luas</td>
                <td>:</td>
                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatPenawaran.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
                  HEKTAR</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              #if ($mode == 'update')
              <tr>
                <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td> 
                ##if ($idStatus == '')
                #if ($mode == 'view')
                  #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
                  <!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/> -->
                  <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
                  #end
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  ##end
                  #if ($mode == 'update')
                  <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatPenawaran('$idLuas')"/>
                  <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
                  #end
                   #else
                   <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatPenawaran('$idLuas')"/>
                  <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                 #end </td>
              </tr>
              #end
            </table>
          </div>
          <!-- END TAB MAKLUMAT PENAWARAN  -->
		  <!-- START TAB SENARAI SEMAK  -->
				
<fieldset>
<legend>SENARAI SEMAK</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    	<tr class="row2">
		<td width="3%"></td>
		<td width="82%"><b>Keterangan</b></td>
		<td width="15%"><b>Dokumen</b></td>
	</tr>  
	
	#if ($SenaraiSemak.size() > 0)
        #set ($list = "")
        #foreach ($list in $SenaraiSemak)
          	#set( $i = $velocityCount )
       		#if ( ($i % 2) == 0 )
   	        	#set( $row = "row2" )
            #else
               	#set( $row = "row1" )
          	#end
                	
        #if($list.flag == 'Y')
        	#set($checked = 'checked')
        	#set($disabled = 'disabled')
        #else
        	#set($checked = '')
        #end
        
        #if ($mode == 'update')
	        <tr class="$row">
	          <td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked /></td>
	          <td class="$row" width="82%">$i. $list.keterangan</td>
	          	<td class="$row" width="15%">
	          	$!list.lampirans
	        	</td>
	        </tr>
	      #end
	      #if ($mode == 'view')
	      	<tr class="$row">
	          <td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" id="idsSenaraiSemak"$list.idSenaraiSemak $checked $disabled /></td>
	          <td class="$row" width="82%">$i. $list.keterangan</td>
	          <td class="$row" width="15%">
	          $!list.lampirans
	          </td>
	        </tr>
	      #end      
        #end
        #else
        <tr>
          <td class="$row" width="3%">&nbsp;</td>
          <td class="$row" colspan="2" width="95%">Tiada Rekod</td>
        </tr>
        #end
      </table></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">#if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSenaraiSemak()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalProjek()"/>
      #end
      #if ($mode == 'view')
      #if ($!statussemasa.equals("1") || $!statussemasa.equals("2"))
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniPermohonan()"/>
      <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
      #end
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end
</table>
</fieldset>


		  <!-- END TAB SENARAI SEMAK  -->
		  <!-- START KEPUTUSAN -->
          <div class="TabbedPanelsContent">
          
          	##parse("app/php2/online/frmPNWImejan.jsp")
          	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           				<td  colspan="4">
						#if(($semakMode == "update") || ($semakMode == "xupdate")) 
						
		    			#if(($!idjawatan.equals("20")||$!idjawatan.equals("24"))&& $!statussemasa.equals("1") || $!statussemasa.equals("11")) 
		    				<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<p align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
		    				<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
						#elseif ($!idjawatan.equals("9") && $!statussemasa.equals("2"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<p align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Pengesahan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
							<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
						#elseif ($!idjawatan.equals("4")&& $!statussemasa.equals("3"))
							<p><input type="checkbox" id="checkme"/><a>&nbsp;Saya, <b>$namaPemohon</b> dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   							<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</a></p>
		    				<p><b><font color="blue" size="2"><span class="blink">
   							&nbsp&nbsp&nbsp&nbsp Ambil Perhatian: Sila pastikan maklumat diisi pada permohonan adalah TEPAT dan MUKTAMAD.
   							<br/>&nbsp&nbsp&nbsp&nbsp Permohonan yang telah dihantar TIDAK DIBENARKAN untuk dipinda/dikemaskini.
   							</p></b></span>
		    				<p align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" $buttonSend value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan2')" />
		    				<input type="button" name="cmdPindaan" id="cmdPindaan" value="Kembalikan kepada penyedia" onclick="doAjaxCall${formName}('simpanpengesahan3')" />
                			<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/></p>
                		
                		
                 	<!-- <td align="center"><input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/></td> -->
                 	                	
		    		#else

           			<td>
                	<input type="checkbox" name="pengesahan" id="pengesahan" checked disabled>&nbsp&nbsp

        			Saya, <b>$!namaPemohon</b>, $!kadPengenalanPemohon dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka
   					<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp tanpa sebarang keraguan dan paksaan dari mana-mana pihak.
   					
      				<p align="center">
           			<input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
            		<input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
            		</p>
               		<!-- <p align="center"><input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/> -->
		    		#end
		    		#end
		    </td>
           	</tr>
           	</table>
          </div>
           ##if ($idStatus == '1610207' || $idStatus == '1610208')
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend>KEPUTUSAN</legend>
                  <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
                    <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">Keputusan</td>
                      <td width="1%">:</td>
                      <td width="70%">LULUS</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Tarikh Hantar Surat</td>
                      <td>:</td>
                      <td>$beanMaklumatKeputusan.tarikhKeputusan
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
                      <td><input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/></td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              ##if ($idStatus == '1610207') 
              <tr>
                <td><fieldset>
                  <legend><strong>SENARAI KEMENTERIAN / AGENSI YANG MENERIMA</strong></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td colspan="5" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                    </tr>
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="35%"><strong>Kementerian</strong></td>
                      <td width="35%"><strong>Agensi</strong></td>
                      <td width="25%" align="center"><strong>Luas (Hektar)</strong></td>
                    </tr>
                    #set ($senaraiAgensi = "")
                    #if ($SenaraiAgensi.size() > 0)
                    #foreach ($senaraiAgensi in $SenaraiAgensi)
                    #if ($senaraiAgensi.bil == '')
                    #set( $row = "row1" )
                    #elseif (($senaraiAgensi.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end
                    <tr>
                      <td class="$row" align="center">$senaraiAgensi.bil</td>
                      <td class="$row">$senaraiAgensi.kementerian</td>
                      <td class="$row">$senaraiAgensi.agensi</td>
                      <td class="$row" align="center">$senaraiAgensi.luas
                        <input name="ids" type="hidden" value="$senaraiAgensi.idPenawaranKJP">
                      </td>
                    </tr>
                    #end
                    <tr class="table_header">
                      <td width="5%">&nbsp;</td>
                      <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                      <td align="center" width="35%">$txtJumlahLuas
                      </td>
                    </tr>
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
              ##end
              <tr>
                <td align="center"></td>
              </tr>
            </table>
          </div>
          ##end
           <!-- START LAMPIRAN -->
           <div class="TabbedPanelsContent">
          	#parse("app/php2/online/frmPNWImejan.jsp")
          </div>
           ##if ($idStatus == '1610207' || $idStatus == '1610208')
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend>LAMPIRAN</legend>
                  <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
                    <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">Keputusan</td>
                      <td width="1%">:</td>
                      <td width="70%">LULUS</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Tarikh Hantar Surat</td>
                      <td>:</td>
                      <td>$beanMaklumatKeputusan.tarikhKeputusan
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
                      <td><input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
                  <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/></td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              ##if ($idStatus == '1610207') 
              <tr>
                <td><fieldset>
                  <legend><strong>SENARAI KEMENTERIAN / AGENSI YANG MENERIMA</strong></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td colspan="5" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                    </tr>
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="35%"><strong>Kementerian</strong></td>
                      <td width="35%"><strong>Agensi</strong></td>
                      <td width="25%" align="center"><strong>Luas (Hektar)</strong></td>
                    </tr>
                    #set ($senaraiAgensi = "")
                    #if ($SenaraiAgensi.size() > 0)
                    #foreach ($senaraiAgensi in $SenaraiAgensi)
                    #if ($senaraiAgensi.bil == '')
                    #set( $row = "row1" )
                    #elseif (($senaraiAgensi.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end
                    <tr>
                      <td class="$row" align="center">$senaraiAgensi.bil</td>
                      <td class="$row">$senaraiAgensi.kementerian</td>
                      <td class="$row">$senaraiAgensi.agensi</td>
                      <td class="$row" align="center">$senaraiAgensi.luas
                        <input name="ids" type="hidden" value="$senaraiAgensi.idPenawaranKJP">
                      </td>
                    </tr>
                    #end
                    <tr class="table_header">
                      <td width="5%">&nbsp;</td>
                      <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                      <td align="center" width="35%">$txtJumlahLuas
                      </td>
                    </tr>
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
              ##end
              <tr>
                <td align="center"></td>
              </tr>
            </table>
          </div>
          ##end
          <!-- END LAMPIRAN -->
        </div>
      </div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"> Pengesahan Permohonan </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>

var checker = document.getElementById('checkme');
var sendbtn = document.getElementById('cmdSimpan');
var pindaanbtn = document.getElementById('cmdPindaan',true);
// when unchecked or checked, run the function
checker.onchange = function(){
	
	var listcheckbox=document.getElementsByName('idsSenaraiSemak');
	for (i = 0; i < listcheckbox.length; i++) 
	{
		if(listcheckbox[i].checked==false){
			alert('Sila Lengkapkan Senarai Semak.');
			sendbtn.disabled = true;
			checker.checked=false;
			return;
		}
	}
	
sendbtn.disabled = true;
if(pindaanbtn !=null){
pindaanbtn.disabled = true;
}

if(this.checked){
   sendbtn.disabled = false;
   if(pindaanbtn !=null){
   	pindaanbtn.disabled = false;
   }
} else {
   sendbtn.disabled = true;
   if(pindaanbtn !=null){
   	pindaanbtn.disabled = true;
   }
}

}
function doChangeTabUpper(tabId) {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function pilihTanah(idKementerian,idAgensi) {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPNWPopupSenaraiTanahOnlineView?idKementerian="+idKementerian+"&idAgensi="+idAgensi;
	
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensiPopup.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik1() {
	doAjaxCall${formName}("doChangePeganganHakmilik1");
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doKemaskini() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "update";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatPenawaran(idLuas) {
	
	/* if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	} */
	if(document.${formName}.socLuasKegunaan.value == "2"){
		if(document.${formName}.socLuas.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuas.focus(); 
			return; 
		}
	
		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Ditawarkan .');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Ditawarkan.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Ditawarkan.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Ditawarkan.');
				document.${formName}.txtLuasMohon3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Ditawarkan.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Ditawarkan.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
		} 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPenawaran";
	document.${formName}.submit();
}
function doHantar(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantar";
	document.${formName}.submit();
}

function kiraLuas(idLuas){

  var jenisLuas = idLuas;
  
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasK = document.${formName}.txtLuasMohon1.value*1;
		}
		var luasH = luasK*100;
		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "2"){ //HEKTER
  	
		var luasH = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasH = document.${formName}.txtLuasMohon1.value*1;
		}
  		
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasM = document.${formName}.txtLuasMohon1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasR = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasP = document.${formName}.txtLuasMohon3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	
		if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		 	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuasMohon1.value != ''){
	  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			document.${formName}.txtBakiLuas.value = bakiLuas;
		}
  	  
   } else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  	var luasE = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasE = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasD = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasD = document.${formName}.txtLuasMohon2.value*1;
		}
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}

   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	 	var luasR = 0;
		if (document.${formName}.txtLuasMohon1.value != ''){
			luasR = document.${formName}.txtLuasMohon1.value*1;
		}
	  	var luasJ = 0;
		if (document.${formName}.txtLuasMohon2.value != ''){
			luasJ = document.${formName}.txtLuasMohon2.value*1;
		}
	  	var luasK = 0;
		if (document.${formName}.txtLuasMohon3.value != ''){
			luasK = document.${formName}.txtLuasMohon3.value*1;
		}
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  
	  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
			alert('Luas dipohon telah melebihi luas asal.')
			document.${formName}.txtLuasMohon1.value = "";
			document.${formName}.txtLuasMohon2.value = "";
			document.${formName}.txtLuasMohon3.value = "";
			document.${formName}.txtLuasBersamaan.value = "";
			document.${formName}.txtBakiLuas.value = "";
		} else {
			document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
		  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
		  	document.${formName}.txtBakiLuas.value = bakiLuas;
		}
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function doBacklist() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatTnh() {

	if(document.${formName}.txtPeganganHakmilik1.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "seterusnya";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatTnh";
	document.${formName}.submit();
}
function doHantarSemakan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarSemakan";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHantarKelulusan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarKelulusan";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function papar(idFail,idStatus) {
	//alert('baca papar');
		document.${formName}.idFail.value = idFail;
		document.${formName}.idStatus.value = idStatus;
		document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
		document.${formName}.actionOnline.value = "seterusnya";
		document.${formName}.method="POST";
		document.${formName}.submit();
	}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.actionOnline.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoPermohonan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socJenisLot.value = "";
	document.${formName}.txtNoLot.value = "";
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";
	doAjaxCall${formName}("");
}
function carian(){
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}
function daftarBaru(){
	document.${formName}.actionOnline.value = "daftarBaru";
	document.${formName}.submit();
}
function doHantarEmel(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHapus(){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionOnline.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPNWOnlineKJPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakPengesahanPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PNWPengesahanPermohonanOnline?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
<!-- SENARAI SEMAK -->
function doSimpanKemaskiniSenaraiSemak() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
function kemaskiniPermohonan() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
}
function batalProjek() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
// 	doAjaxCall${formName}("");
}
</script>
$javascriptLampiran
