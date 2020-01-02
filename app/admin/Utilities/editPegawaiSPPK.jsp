

<tr >
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Tambah Maklumat Pegawai</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="0">

    <tr>
         <td width="14%">Pilihan Pegawai</td>
        <td width="1%" align="center" valign="top">:</td>
        <td width="85%">
      <!--  #if ($viewPegawaiPPK.USER_ID == "")
        <br />
        *Sila daftarkan Pegawai PPK ini ke dalam senarai pengguna dalaman* <br />
        #end-->
          <select id="USER_ID_$viewPejabat.ID_PEJABAT"  name="USER_ID_$viewPejabat.ID_PEJABAT" style="width: 500px;"
          onchange="doDivAjaxCall$formname('dataPegawai$ID_PEJABAT','getPegawaiData','ID_PEJABAT=$viewPejabat.ID_PEJABAT&JENISPEJ=$viewPejabat.JENIS_PEJ&USER_ID='+this.value);" >
			<option value = "" >SILA PILIH</option>
			#foreach ( $listPeg in $listPegawaiPPK )		
				#set ( $selected_Peg = "" )
                #if ($viewPegawaiPPK.USER_ID == $listPeg.USER_ID)
                #set ($selected_Peg = "selected" )
                #end 
			<option $selected_Peg value="$listPeg.USER_ID" >$listPeg.DETAILS_PEGAWAI</option>
			#end
		</select>
          <input  size="50" type="hidden" id="USER_ID_$viewPejabat.ID_PEJABAT" name="USER_ID_$viewPejabat.ID_PEJABAT" value="$viewPegawaiPPK.USER_ID" >
        <input  size="50" type="hidden" id="USER_PPK_$viewPejabat.ID_PEJABAT" name="USER_PPK_$viewPejabat.ID_PEJABAT" value="$viewPegawaiPPK.USER_PPK" >
        </td>
      </tr>
      
       <tr>
       <td colspan="10">
      <div id="dataPegawai$viewPejabat.ID_PEJABAT">
      <table>
       <tr>
          <td width="21%">Nama Pegawai</td>
          <td width="1%" align="center" valign="top">:</td>
          <td width="78%">
       <input  size="50" type="text" id="NAMA_PEGAWAI_$viewPejabat.ID_PEJABAT" name="NAMA_PEGAWAI_$viewPejabat.ID_PEJABAT" value="$viewPegawaiPPK.NAMA_PEGAWAI" >
       </td>
       </tr>
       
      #if ($viewPegawaiPPK.USER_ID == "")
      <tr>
      <td width="99">No Kp </td>
      <td width="3" align="center" valign="top">:</td>
      <td width="457"><input  size="50" maxlength="12"  type="text" id="USER_LOGIN_$viewPejabat.ID_PEJABAT" name="USER_LOGIN_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" value="" ></td>
  </tr>
      #end
      
       <tr>
      <td width="99">Kod Pegawai</td>
      <td width="3" align="center" valign="top">:</td>
      <td width="457"><input  size="3" type="text" id="KOD_PEGAWAI_$viewPejabat.ID_PEJABAT" name="KOD_PEGAWAI_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" value="$viewPegawaiPPK.KOD" ></td>
  </tr>
  
    <tr>
      <td>Jawatan</td>
      <td align="center" valign="top">:</td>
      <td> 
      <select id="JAWATAN_$viewPejabat.ID_PEJABAT"  
					name="JAWATAN_$viewPejabat.ID_PEJABAT" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJJAWATAN )		
							#set ( $selected_ruj = "" )
							#if($idJawatanPegawai==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.KETERANGAN" >
							$ruj.KETERANGAN
                            #end
							</option>
						
		</select>
        
      </td>
    </tr>
    <tr>
      <td>Keterangan Unit</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="KETERANGAN_UNIT_$viewPejabat.ID_PEJABAT" name="KETERANGAN_UNIT_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" ></td>
    </tr>
    <tr>
      <td>Pejabat</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="NAMA_PEJABAT_$viewPejabat.ID_PEJABAT" name="NAMA_PEJABAT_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.NAMA_PEJABAT" ></td>
    </tr>
    <tr>
      <td>Alamat</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="ALAMAT1_$viewPejabat.ID_PEJABAT" name="ALAMAT1_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.ALAMAT1" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td><input  size="50" type="text" id="ALAMAT2_$viewPejabat.ID_PEJABAT" name="ALAMAT2_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.ALAMAT2" ></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td><input  size="50" type="text" id="ALAMAT3_$viewPejabat.ID_PEJABAT" name="ALAMAT3_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.ALAMAT3" ></td>
    </tr>
    <tr>
      <td>Poskod</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="POSKOD_$viewPejabat.ID_PEJABAT" name="POSKOD_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.POSKOD" ></td>
    </tr>
    <tr>
      <td>Bandar</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="BANDAR_$viewPejabat.ID_PEJABAT" name="BANDAR_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.BANDAR" >
      <input  size="50" type="hidden" id="ID_BANDAR_$viewPejabat.ID_PEJABAT" name="ID_BANDAR_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.ID_BANDAR" >
      </td>
    </tr>
    <tr>
      <td>Negeri</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="NAMA_NEGERI_$viewPejabat.ID_PEJABAT" name="NAMA_NEGERI_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.NAMA_NEGERI" >
      <input  size="50" type="hidden" id="ID_NEGERI_$viewPejabat.ID_PEJABAT" name="ID_NEGERI_$viewPejabat.ID_PEJABAT" value="$viewPejabat.ID_NEGERI" ></td>
    </tr>
    <tr>
      <td>No. Tel </td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="NO_TEL_$viewPejabat.ID_PEJABAT" name="NO_TEL_$viewPejabat.ID_PEJABAT" readonly value="$viewPejabat.NO_TEL" ></td>
    </tr>
    <tr>
      <td>Extension</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="EXTENSION_$viewPejabat.ID_PEJABAT" name="EXTENSION_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"  ></td>
    </tr>
    <tr>
      <td>Emel</td>
      <td align="center" valign="top">:</td>
      <td><input  size="50" type="text" id="EMEL_$viewPejabat.ID_PEJABAT" name="EMEL_$viewPejabat.ID_PEJABAT" value="$viewPegawaiPPK.EMEL" > </td>
    </tr>
    </table>
		</div></td></tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td><input type="button" name="button" id="button" value="Simpan" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','simpanPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT&JENISPEJ=$viewPejabat.JENIS_PEJ&ID_RUJPPK=$viewPegawaiPPK.ID_UNITPSK');">
            <input type="button" name="button2" id="button2" value="Batal" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','showDisplayPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT');"></td>
        </tr>
        
      </table>
</fieldset>


</td>		
</tr>

<script>



</script>