<fieldset>

<table width="100%" border="0">

                #set($jenisPB = "")
                #set($nama = "")
                #set($notel = "")
                #set($nofax = "")
                #set($alamat1 = "")
                #set($alamat2 = "")
                #set($alamat3 = "")
                #set($poskod = "")
                #set($selectNegeri = $socNegeri)
                ##set($idPemaju = "")
                #foreach ($maklumatSyarikat in $MaklumatSyarikat)
                		#set($jenisPB = $maklumatSyarikat.jenisPB)
                        #set($nama = $maklumatSyarikat.nama)
                        #set($notel = $maklumatSyarikat.notel)
                        #set($nofax = $maklumatSyarikat.nofax)
                        #set($alamat1 = $maklumatSyarikat.alamat1)
                        #set($alamat2 = $maklumatSyarikat.alamat2)
                        #set($alamat3 = $maklumatSyarikat.alamat3)
                        #set($poskod = $maklumatSyarikat.poskod)
                        #set($selectNegeri = $socNegeri1)
                        #set($idPemaju = $maklumatSyarikat.idPemaju)
                        <input name="idPemaju" type="hidden" value="$maklumatSyarikat.idPemaju" />
                #end 
                
                
                

  <tr>
    <td width="22%" height="31%"><div align="right">Jenis/No. PB</div></td>
    <td width="259"><input name="txtjenisPB" type="text" id="txtjenisPB" value="$!jenisPB" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" /></td>
    <td width="16%"><div align="right">Alamat</div></td>
    <td width="31%"><label>
      <input name="txtalamat1" type="text" id="txtalamat1" value="$!alamat1" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();"  />
    </label></td>
  </tr>
  <tr>
  <tr>
    <td><div align="right">Nama</div></td>
    <td><label>
      <input name="txtnama" type="text" id="txtnama" value="$!nama" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right"></div></td>
    <td><label>
      <input name="txtalamat2" type="text" id="txtalamat2" value="$!alamat2" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">No. Telefon</div></td>
    <td><label>
      <input name="txtnotel" type="text" id="txtnotel" value="$!notel" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right"></div></td>
    <td><label>
      <input name="txtalamat3" type="text" id="txtalamat3" value="$!alamat3" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();"  />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">No. Fax</div>      <label></label></td>
    <td><label>
      <input name="txtnofax" type="text" id="txtnofax" value="$!nofax" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
    </label></td>
    <td><div align="right">Poskod</div></td>
    <td><label><input name="txtposkod" type="text" id="txtposkod" value="$!poskod" $readonly class="$inputTextClass" />
    </label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><div align="right">Negeri</div></td>
    <td><label>$selectNegeri</label></td>
   <!--<input name="txtnegeri" type="text" id="txtnegeri" value="$!selectNegeri" /> -->
  </tr>
  <tr>
    <td>&nbsp;</td>
    ##<td>$!mode</td>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
  </tr>
</table>
<fieldset><legend><strong>Lembaga Pengarah</strong></legend>
#if ($flagPopup == 'openPengarah')
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>#parse ("app/htp/frmpenswastaanTambahPengarah.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
#end

#if ($flagPopup =="")     
<input name="cmdtmbhpengarah" type="button" value="TAMBAH PENGARAH" onclick="tambahPengarah('$id_permohonan')"/>

#end

<table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="70%"><strong>Nama Ahli Lembaga Pengarah</strong></td>
              <td width="25%"><strong>No. K/P</strong></td>
            </tr>
                                    
          #set ($list = "")
          #if ($SenaraiPengarah.size() > 0)
          #foreach ($list in $SenaraiPengarah)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
            
          <tr>
            <td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:paparPengarah('$id_permohonan','$list.id_pengarah')" class="style1"><font color="blue">$list.namaPgrh</font></a></td>
            <td class="$row">$list.icPgrh</td>
  </tr>
          #end
          #else
          <tr>
            <td class="row1" colspan="3">Tiada Rekod</td>
  </tr>
          #end
          </tbody>
</table>

</fieldset>
</fieldset>

#if ($flagPopup =="")
<label>
<div align="center">
	#if($mode == "view")  
      <input type="button" name="txtkemaskini" id="txtkemaskini" value="Kemaskini" onclick="viewSyarikatUpdate('$id_permohonan')" />
      <input type="button" name="txtkembali2" id="txtkembali2" value="Kembali" onclick="backList('$id_permohonan')"/>
      <!--<input type="button" name="txtseterusnya" id="txtseterusnya" value="seterusnya" onclick ="screenMesyuarat111('$id_permohonan')"/> -->
      <input type="button" name="txtseterusnya" id="txtseterusnya" value="Seterusnya" onclick="screenPindahMilik('$id_permohonan')"/> 
  #end
    
#if($mode == "new")  
<input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" onclick="viewSyarikatSimpan('$id_permohonan')"/>
     <input type="button" name="txthapus" id="txthapus" value="Hapus" onclick ="reset()"/>
     <input type="button" name="txtkembali" id="txtkembali" value="Kembali" onclick="backList('$id_permohonan')" />
    #end
   
    #if($mode == "update")  
    <input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" onclick= "viewSyarikatSimpanUpdate('$id_permohonan')"/>
    <input type="button" name="txtbatal" id="txtbatal" value="Batal" />
    #end
</div>
</label>
#end



<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_negeri" value="$selectNegeri"/>
<script>



function reset() {
	doAjaxCall${formName}("reset");
	
}



</script>