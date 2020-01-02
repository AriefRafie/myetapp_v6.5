#if ($flagPopup == 'openPopupPemohon')
  <tr>
     <td><fieldset>
       <legend><b>MAKLUMAT PEMOHON</b></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">#if ($mode != 'viewPemohon')<span class="style1">*</span>#end</td>
    <td width="28%">Kategori Pemohon</td>
    <td>:</td>
    <td width="70%">$selectKategoriPemohon</td>
  </tr>
  #if($idKategoriPemohon == '8')
  <tr>
    <td>#if ($mode != 'viewPemohon')<span class="style1">*</span>#end</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$selectNegeri</td>
  </tr>
  <tr>
    <td>#if ($mode != 'viewPemohon')<span class="style1">*</span>#end</td>
    <td>Pejabat</td>
    <td>:</td>
    <td>$selectPejabat</td>
  </tr>
  #foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
  <tr>
    <td>&nbsp;</td>
    <td>Nama Pejabat</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.namaPejabat</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Alamat</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.alamat1</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.alamat2</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.alamat3</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Poskod</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.poskod</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.bandar</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.negeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Telefon</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.noTel</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. Fax</td>
    <td>:</td>
    <td>$beanMaklumatPejabat.noFax</td>
  </tr>
  #end
  #end
  #if($idKategoriPemohon == '3')
  <tr>
    <td>#if ($mode != 'viewPemohon')<span class="style1">*</span>#end</td>
    <td>Kementerian</td>
    <td>:</td>
    <td>$selectKementerian</td>
  </tr>
  <tr>
    <td>#if ($mode != 'viewPemohon')<span class="style1">*</span>#end</td>
    <td>Agensi</td>
    <td>:</td>
    <td>$selectAgensi</td>
  </tr>
  #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
  <tr>
    <td>&nbsp;</td>
    <td>Nama</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.namaAgensi</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Alamat</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.alamat1</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.alamat2</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.alamat3</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Poskod</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.poskod</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$beanMaklumatAgensi.negeri</td>
  </tr>
  #end        
  #end
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
      #if ($mode == 'addNewPemohon')
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSimpanMaklumatPemohon()"/>
			<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBatalMaklumatPemohon()"/>      
      #end     
      #if ($mode == 'updatePemohon')
	      <input type="button" name="cmdSimpanKemaskini2" id="cmdSimpanKemaskini2" value="Simpan" onclick="doSimpanKemaskiniMaklumatPemohon()"/>
	      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskiniMaklumatPemohon()"/>
      #end
      #if ($mode == 'viewPemohon')
	      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniMaklumatPemohon()"/>
	      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapusMaklumatPemohon()"/>	      
	      <input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onClick="doBatalMaklumatPemohon()"/>    
	      
<!-- 		      #if($idStatus == '1610198') -->
<!-- 			      <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/> -->
<!-- 			      <input type="button" name="cmdBatalPermohonan2" id="cmdBatalPermohonan2" value="Batal Permohonan" onclick="doBatalPermohonan()"/> -->
<!-- 		      #end -->
<!-- 	      <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport')"/> -->
      #end </td>
  </tr>
</table>
       </fieldset></td>
   </tr>

  #end

  <tr>
     <td><fieldset>
       <legend><b>SENARAI PEMOHON</b></legend>
       <table align="center" width="100%">
         <tr>
           <td colspan="7" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruPemohon()"/></td>
         </tr>
         <tr class="table_header">
           <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
           <td width="15%"><strong>Pegangan Hakmilik</strong></td>
         </tr>
         #set ($senaraiMaklumatPemohon = "")
         #if ($BeanListMaklumatPemohon.size() > 0)
         #foreach ($senaraiMaklumatPemohon in $BeanListMaklumatPemohon)
         #if ($senaraiMaklumatPemohon.bil == '')
         #set( $row = "row1" )
         #elseif (($senaraiMaklumatPemohon.bil % 2) != 0)
         #set( $row = "row1" )
         #else 
         #set( $row = "row2" )
         #end
         <tr>
           <td class="$row" align="center">$senaraiMaklumatPemohon.bil</td>
           <td class="$row"><a href="javascript:doPaparPemohon('$senaraiMaklumatPemohon.idPemohon')" class="style2">$senaraiMaklumatPemohon.nama</a></td>
         </tr>
         #end
         #else
         <tr>
           <td class="row1" align="center">&nbsp;</td>
           <td class="row1">Tiada Rekod</td>
         </tr>
         #end
         <tr>
           <td colspan="7">&nbsp;</td>
         </tr>
       </table>
       </fieldset></td>
   </tr>