<table width="100%" border="0" cellspacing="2" cellpadding="0">
 <tr>
          <td width="21%">Nama Pegawai</td>
          <td width="1%" align="center" valign="top">:</td>
          <td width="78%">
       <input  size="50" type="text" id="NAMA_PEGAWAI_$viewPejabat.ID_PEJABAT" name="NAMA_PEGAWAI_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" value="$dataPegawai.USER_NAME">
       </td>
       </tr>
       
    <tr>
    <td width="21%">Kod Pegawai</td>
          <td width="1%" align="center" valign="top">:</td>
          <td width="78%"><input  size="3" type="text" id="KOD_PEGAWAI_$viewPejabat.ID_PEJABAT" name="KOD_PEGAWAI_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" ></td>
  </tr>
  
    <tr>
      <td>Jawatan</td>
      <td align="center" valign="top">:</td>
      <td><select id="JAWATAN_$viewPejabat.ID_PEJABAT"  
					name="JAWATAN_$viewPejabat.ID_PEJABAT" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJJAWATAN )		
							#set ( $selected_ruj = "" )
							#if($dataPegawai.ID_JAWATAN==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.KETERANGAN" >
							$ruj.KETERANGAN
							</option>
						#end
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
      <td><input  size="50" type="text" id="EMEL_$viewPejabat.ID_PEJABAT" name="EMEL_$viewPejabat.ID_PEJABAT" value="$dataPegawai.EMEL" ></td>
    </tr>
  
</table>
