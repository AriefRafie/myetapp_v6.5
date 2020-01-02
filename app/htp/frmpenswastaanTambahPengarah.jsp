
                #set($namaPgrh = "")
                #set($icPgrh = "")
                #foreach ($maklumatPengarah in $MaklumatPengarah) 
                		#set($namaPgrh = $maklumatPengarah.namaPgrh)
                        #set($icPgrh = $maklumatPengarah.icPgrh)
             #end

<table width="100%" border="0" cellpadding="2" cellspacing="2">
  <tr>
    <td width="15%">Nama</td>
    <td width="35%">
      <input type="text" name="txtnamaPgrh" id="txtnamaPgrh" value="$namaPgrh"  $readonlyPopup class="$inputTextClassPopup" onkeyup="this.value=this.value.toUpperCase();" />    </td>
    <td width="15%">No KP</td>
    <td width="35%">
      <input type="text" name="txticPgrh" id="txticPgrh" value="$icPgrh" $readonlyPopup class="$inputTextClassPopup" onkeyup="this.value=this.value.toUpperCase();"  />    </td>
  </tr>
   <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center">
    #if ($modePopup =="new")
    <input name="cmdsimpan2" type="button" value="Simpan" onclick = "simpanPengarah ('$id_permohonan','$id_pengarah')"/>
    <input name="cmdkembali" type="button" value="Kembali" />
    #end
    
    #if ($modePopup =="view")
    <input name="cmdkemaskini" type="button" value="Kemaskini" onclick ="viewKemaskiniPengarah('$id_permohonan','$id_pengarah')" />
    #end
    
    #if ($modePopup =="update")
    <input name="cmdsimpan" type="button" value="simpan" onclick= "simpanKemaskiniPengarah('$id_permohonan','$id_pengarah')"/>
    <input name="cmdkembali" type="button" value="Kembali" onclick= "kembaliListPengarah ('$id_permohonan','$id_pengarah')"/>
      <input name="cmdhapus" type="button" value="Hapus" onclick= "hapusPengarah ('$id_permohonan','$id_pengarah')"/>
    #end    	</td>
    
    
  </tr>
</table>
<input type="hidden" name="id_pemaju" value="$idPemaju"/>
