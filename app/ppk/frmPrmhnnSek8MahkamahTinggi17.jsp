#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $idstatus = $data.get("id_status") )
#set ( $idFail = $data.get("id_fail") )

#foreach($list in $listSemak)
 	#set($noFail=$list.noFail)
 	#set($negeri=$list.pmNama_negeri)
 	#set($daerah=$list.namadaerah)
 	#set($unit=$list.namaPejabat)
 	#set($seksyen=$list.seksyen)
 	#set($statusFail=$list.keterangan)
 	#set($tarikhMohon=$list.tarikhMohon)
 	#set($namaSimati=$list.namaSimati)
 	#set($namaSipemohon=$list.namaPemohon)
#end

#if ( $action == "onChange" )

    #foreach($info in $infoPerintahTangguh)
        #set ($id_perintah = $info.id_perintah)
        #set ($jenisPerintah = $info.jenis_keluar_perintah)
        #set ($tarikh_perintah = $info.tarikh_perintah)
        #set ($nama_pegawai = $info.nama_pegawai)
        #set ($nama_negeri = $info.nama_negeri)
        #set ($nama_daerah = $info.nama_daerah)
        #set ($nama_pejabat = $info.nama_pejabat)
        #set ($alamat1 = $info.alamat1)
        #set ($alamat2 = $info.alamat2)
        #set ($alamat3 = $info.alamat3)
        #set ($poskod = $info.poskod)
        #set ($no_tel = $info.no_tel)
        #set ($no_fax = $info.no_fax)
    #end
    
#end

#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<fieldset id="header_lama" style="display:none" >
<legend>MAKLUMAT PERMOHONAN</legend>
		<table width="100%">
			<tr>
				<td width="50%">
				  <table width="101%"  cellpadding="1" cellspacing="1" border="0">
						<tr>
							<td width="33%" style="text-transform:uppercase"><div align="right">No Fail</div></td>
			                <td width="2%">:</td>
            				<td width="65%"><font color="blue">$noFail</font></td>
				    	</tr>
						<tr>
							<td><div align="right" style="text-transform:uppercase">Negeri</div></td>
							<td>:</td>
							<td><font color="blue">$negeri.toUpperCase()</font></td>
						</tr>
						<tr>
							<td valign="top"><div align="right" style="text-transform:uppercase">Daerah / Jajahan</div></td>
							<td valign="top">:</td>
							<td><font color="blue">$daerah.toUpperCase()</font></td>
						</tr>
						<tr>
							<td valign="top"><div align="right" style="text-transform:uppercase">Unit</div></td>
							<td valign="top">:</td>
							<td><font color="blue">$unit.toUpperCase()</font></td>
						</tr>
						<tr>
							<td><div align="right" style="text-transform:uppercase"></div></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
			  </td>
				
		  <td width="50%">
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
						<tr>
							<td width="38%" valign="top" style="text-transform:uppercase" valign="top"><div align="right">Status Fail</div></td>
						    <td width="2%" valign="top">:</td>
					      	<td width="60%"><font color="blue">$statusFail.toUpperCase()</font></td>
		 				</tr>
						<tr>
							<td><div align="right" style="text-transform:uppercase">Seksyen</div></td>
							<td>:</td>
							<td><font color="blue">$seksyen.toUpperCase()</font></td>
						</tr>
						<tr>
							<td><div align="right" style="text-transform:uppercase">Tarikh Mohon</div></td>
							<td>:</td>
							<td><font color="blue">$tarikhMohon</font></td>
						</tr>
						<tr>
							<td valign="top"><div align="right" style="text-transform:uppercase">Nama Simati</div></td>
							<td valign="top">:</td>
							<td><font color="blue">$namaSimati.toUpperCase()</font></td>
						</tr>
						<tr>
							<td valign="top"><div align="right"><span style="text-transform:uppercase">Nama Pemohon</span></div></td>
							<td valign="top">:</td>
							<td><font color="blue">$namaSipemohon.toUpperCase()</font></td>
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

##set ( $id = $dataPerbicaraan.get("idPermohonan") )
#set ( $idPemohon = $dataPerbicaraan.get("idPemohon") )
#set ( $id_perbicaraan = $dataPerbicaraan.get("id_perbicaraan") )
#set ( $tarikhBicara = $dataPerbicaraan.get("tarikh_bicara") )
#set ( $idUnitpsk = $dataPerbicaraan.get("id_unitpsk") )    


#if ($idFlag == "1")
	#foreach ($list in $infoMahkamah)
		#set ($nama_pejabat = $list.namapejabat)
		#set ($alamat1pejabat = $list.alamat1pejabat)
		#set ($alamat2pejabat = $list.alamat2pejabat)
		#set ($alamat3pejabat = $list.alamat3pejabat)
		#set ($poskodpejabat = $list.poskodpejabat)
		#set ($notel = $list.notel)
		#set ($nofax = $list.nofax)
	#end
#end

<p></p>

#if ( $addMode == "yes" )

 <fieldset>
  <legend>MAKLUMAT RUJUKAN MAHKAMAH TINGGI</legend>         
     <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
     
        #if ( $jenisPerintah == "PT" )
        <tr>
          <td width="25%">&nbsp;&nbsp;&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<select name="jenisPerintah" id="jenisPerintah">
                <option value="PT" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR TANAH</option>
                <option value="PD" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR DAERAH</option>
                <option value="PDS" style="text-transform:uppercase;" onblur="uppercase()">PEGAWAI DAERAH SARAWAK</option>
          	</select>
          </td>
   	   </tr> 
       #end
          
        #if ( $jenisPerintah == "PD" )
        <tr>
          <td width="25%">&nbsp;&nbsp;&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<select name="jenisPerintah" id="jenisPerintah">
                <option value="PD" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR DAERAH</option>
                <option value="PT" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR TANAH</option>
                <option value="PDS" style="text-transform:uppercase;" onblur="uppercase()">PEGAWAI DAERAH SARAWAK</option>
          	</select>
          </td>
   	   </tr> 
       #end       
          
         #if ( $jenisPerintah == "PDS" )
        <tr>
          <td width="25%">&nbsp;&nbsp;&nbsp;Kod Keluar Perintah</td>          
          <td width="75%">:&nbsp;<select name="jenisPerintah" id="jenisPerintah">
                <option value="PDS" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR DAERAH SARAWAK</option>
                <option value="PT" style="text-transform:uppercase;" onblur="uppercase()">PENTADBIR TANAH</option>
                <option value="PD" style="text-transform:uppercase;" onblur="uppercase()">PEGAWAI DAERAH</option>
          	</select>
          </td>
       </tr>
		#end
        
		#if ( $jenisPerintah == "" )      
        <tr>
          <td width="25%"><font color="red">*</font>&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<select name="jenisPerintah" id="jenisPerintah">
              <option value="">SILA PILIH</option>
              <option value="PT">PENTADBIR TANAH</option>
              <option value="PD">PEGAWAI DAERAH</option>
              <option value="PDS">PENTADBIR DAERAH SARAWAK</option>
            </select>
          </td>
       </tr>           
		#end               
       
        <tr>
          <td><font color="red">*</font>&nbsp;Tarikh Perintah</td>
          <td>:&nbsp;<input name="txdTarikhPerintahAdd" value="$!tarikhBicara" size="11" id="txdTarikhPerintahAdd" type="text" maxlength="10" onblur="getTarikhBicara();validateTarikh(this,this.value)" onkeyup="validateTarikh(this,this.value);" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintahAdd',false,'dmy');" />          </td>
       </tr>
        <tr>
          <td><font color="red">*</font>&nbsp;Negeri</td>
          <td>:&nbsp;$selectNegeri
          <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" /></td>
       </tr>
       <tr>
      <td><font color="red">*</font>&nbsp;Mahkamah</td>
      <td>:&nbsp;$selectBicara
      <input type="hidden" name="id_pejabat" id="id_pejabat" value="$!id_pejabat" /></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;Alamat</td>
      <td>:&nbsp;<input type="text" name="txtAlamat4" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
    </tr>
    <tr>
    
      <td></td>
      <td>:&nbsp;<input type="text" name="txtAlamat5" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
    </tr>
    <tr>
      <td></td>
      <td>:&nbsp;<input type="text" name="txtAlamat6" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
    </tr>
        <tr>
      <td>&nbsp;&nbsp;&nbsp;Poskod</td>
      <td>:&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" readonly class="disabled" /></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
      <td>:&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!notel" readonly class="disabled" ></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;No Fax</td>
      <td>:&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!nofax" readonly class="disabled" > </td>
    </tr>
    <tr>
      <td><font color="red">*</font>&nbsp;Nama Pegawai Pengendali</td>
      <td>:&nbsp;$selectPegawai</td>
   	</tr>    
   	 </table>	     
  <p></p>
      <div align="center">
        <input type="button" name="cmdSimpan" value="Simpan" onclick="Simpan('$idpermohonan')" />
        #if ( $idstatus == 18 )
        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrin2TangguhAdd('$idpermohonan','$id_perbicaraan');" />
        #end
        #if ( $idstatus == 47 )
        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrin2Tangguh('$idpermohonan','$id_perbicaraan');" />
        #end        
      </div> 
        <br/>
        <br/>	
        <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr><td>
        	<i><font color="red" style="font-size:10px">Perhatian</font> <font style="font-size:10px">: Sila pastikan label</font> <font color="red" style="font-size:10px">*</font> <font style="font-size:10px"> diisi.</font></i>
      		</td>
       	  </tr>
      	</table>      
</fieldset>
#end


#if ( $viewMode == "yes" )
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
 <fieldset>
  <legend>MAKLUMAT RUJUKAN MAHKAMAH TINGGI</legend>  
         
     <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
        #if ( $jenisPerintah == "PT" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PENTADBIR TANAH" readonly class="disabled"></td>
   	   </tr> 
        #end         
          
        #if ( $jenisPerintah == "PD" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PEGAWAI DAERAH" readonly class="disabled" ></td>
   	   </tr>
        #end         
          
         #if ( $jenisPerintah == "PDS" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>          
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PENTADBIR DAERAH SARAWAK" readonly class="disabled" ></td>
       </tr>
       	 #end       
   
        <tr>
          <td>Tarikh Perintah</td>
          <td>:&nbsp;<input name="txdTarikhPerintahAdd" value="$!tarikh_perintah" size="11" id="txdTarikhPerintahAdd" type="text" readonly class="disabled" /></td>
       </tr>
        <tr>
          <td>Negeri</td>
          <td>: $selectNegeri
          <input type="hidden" name="txtidnegeri" id="txtidnegeri" maxlength="50" size="6" value="$!txtidnegeri" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
        </tr>
       <tr>
      <td>Mahkamah</td>
      <td>:&nbsp;$selectBicara
      <input type="hidden" name="id_pejabat" id="id_pejabat" maxlength="50" size="6" value="$id_pejabat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td>Alamat</td>
      <td>:&nbsp;<input type="text" name="txtAlamat7" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>:&nbsp;<input type="text" name="txtAlamat8" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td></td>
      <td>:&nbsp;<input type="text" name="txtAlamat9" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>   
    <tr>
      <td>Poskod</td>
      <td>:&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>No Telefon</td>
      <td>:&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!no_tel" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>No Fax</td>
      <td>:&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!no_fax" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>Nama Pegawai Pengendali</td>
      <td>:&nbsp;$selectPegawai</td>
   	</tr>        
  </table>
   
      <table width="100%"  cellspacing="1" cellpadding="1" border="0">
      	<tr>
        	<td align="center" colspan="2">
        	  <input type="button" name="cmdKemaskini" value="Kemaskini" onclick="skrin_kemaskiniMT('$idpermohonan','$id_perbicaraan','$id_perintah')" />
        	  <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrin2TangguhEdit('$idpermohonan','$id_perbicaraan');" />
        	  <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
      	  </td>
        </tr>
      </table>     
      
</fieldset>      
#end

<p></p>

#if ( $viewEditMode == "yes" )
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
 <fieldset>
  <legend>MAKLUMAT RUJUKAN MAHKAMAH TINGGI</legend>         
     <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
     
     	#if ( $jenisPerintah == "PT" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PENTADBIR TANAH" class="disabled" readonly></td>
   	   </tr>
        #end
          
        #if ( $jenisPerintah == "PD" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>          
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PEGAWAI DAERAH" class="disabled" readonly></td>
   	   </tr>
          #end  
          
        #if ( $jenisPerintah == "PDS" )
        <tr>
          <td width="25%">Kod Keluar Perintah</td>            
          <td width="75%">:&nbsp;<input type="text" name="jenisPerintah" id="jenisPerintah" maxlength="50" size="25" value="PENTADBIR DAERAH SARAWAK" class="disabled" readonly></td>
   	   #end       </tr>
       
       
        <tr>
          <td>Tarikh Perintah</td>
          <td>:&nbsp;<input name="txdTarikhPerintahAdd" value="$!tarikh_perintah" size="11" id="txdTarikhPerintahAdd" type="text" readonly class="disabled" /></td>
       </tr>
        <tr>
          <td>Negeri</td>
          <td>: $selectNegeri
            <input type="hidden" name="txtidnegeriEdit" id="txtidnegeriEdit" maxlength="50" size="6" value="$!txtidnegeriEdit" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
        </tr>
       <tr>
      <td>Mahkamah</td>
      <td>:&nbsp;$selectBicara
      <input type="hidden" name="id_pejabat" id="id_pejabat" maxlength="50" size="6" value="$!id_pejabat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>       
    <tr>
      <td>Alamat</td>
      <td>:&nbsp;<input type="text" name="txtAlamat10" maxlength="50" size="50" value="$!alamat1" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>:&nbsp;<input type="text" name="txtAlamat11" maxlength="50" size="50" value="$!alamat2" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td></td>
      <td>:&nbsp;<input type="text" name="txtAlamat12" maxlength="50" size="50" value="$!alamat3" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
    </tr>
    
    <tr>
      <td>Poskod</td>
      <td>:&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>No Telefon</td>
      <td>:&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!no_tel" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>No Fax</td>
      <td>:&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!no_fax" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>Nama Pegawai Pengendali</td>
      <td>:&nbsp;$selectPegawai</td>
   	</tr>    
   	 </table>	     
  <p></p>
      #if ( $button == "viewEditMode" )
      <div align="center">
        <input type="button" name="cmdKemaskini" value="Kemaskini" onclick="skrin_kemaskiniMT('$idpermohonan','$id_perbicaraan','$id_perintah')" />
        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrin2TangguhEdit('$idpermohonan','$id_perbicaraan');" />
        <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
  	  </div> 
      #end
</fieldset>      
#end
<p></p>

#if ( $editMode == "yes" )
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>

 <fieldset>
  <legend>MAKLUMAT RUJUKAN MAHKAMAH TINGGI</legend>         
     <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
     
     	#if ( $jenisPerintah == "PT" )
        <tr>
          <td width="25%"><font color="red">*</font>&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:
            <select name="jenisPerintah" id="jenisPerintah" class="mediumselect">
              <option value="PT">PENTADBIR TANAH</option>
              <option value="PD">PEGAWAI DAERAH</option>
              <option value="PDS">PENTADBIR DAERAH SARAWAK</option>
            </select></td>
       </tr>
       #end
       
      	#if ( $jenisPerintah == "PD" )
        <tr>
          <td width="25%"><font color="red">*</font>&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:
            <select name="jenisPerintah" id="jenisPerintah" class="mediumselect">
              <option value="PD">PEGAWAI DAERAH</option>
              <option value="PT">PENTADBIR TANAH</option>
              <option value="PDS">PENTADBIR DAERAH SARAWAK</option>
            </select></td>
       </tr>
       #end      

      	#if ( $jenisPerintah == "PDS" )
        <tr>
          <td width="25%"><font color="red">*</font>&nbsp;Kod Keluar Perintah</td>
          <td width="75%">:
            <select name="jenisPerintah" id="jenisPerintah" class="mediumselect">
              <option value="PDS">PENTADBIR DAERAH SARAWAK</option>
              <option value="PT">PENTADBIR TANAH</option>
              <option value="PD">PEGAWAI DAERAH</option>
            </select></td>
       </tr>
       #end 
              
        <tr>
          <td><font color="red">*</font>&nbsp;Tarikh Perintah</td>
          <td>:
            <input name="txdTarikhPerintahEdit" value="$!tarikh_perintah" size="11" id="txdTarikhPerintahEdit" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintahEdit',false,'dmy');" /></td>
       </tr>
        <tr>
          <td><font color="red">*</font>&nbsp;Negeri</td>
          <td>: $selectNegeri
          <input type="hidden" name="txtidnegeri" id="txtidnegeri" maxlength="50" size="6" value="$!txtidnegeri" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
        </tr>
       <tr>
      <td><font color="red">*</font>&nbsp;Mahkamah</td>
      <td>:&nbsp;$selectBicara
        <input type="hidden" name="id_pejabat" id="id_pejabat" maxlength="50" size="6" value="$!id_pejabat" class="disabled" readonly /></td>
    </tr>       
    <tr>
      <td>&nbsp;&nbsp;&nbsp;Alamat</td>
      <td>:      
      <input type="text" name="txtAlamat1" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly />
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>:      
      <input type="text" name="txtAlamat2" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td></td>
      <td>:&nbsp;<input type="text" name="txtAlamat3" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;Poskod</td>
      <td>:&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
      <td>:&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!no_tel" class="disabled" readonly></td>
    </tr>
    <tr>
      <td>&nbsp;&nbsp;&nbsp;No Fax</td>
      <td>:&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!no_fax" class="disabled" readonly></td>
    </tr>
    <tr>
      <td><font color="red">*</font>&nbsp;Nama Pegawai Pengendali</td>
      <td>:&nbsp;$selectPegawai</td>
   	</tr>        
   	 </table>	     
  <p></p>
      <div align="center">
        <input type="button" name="cmdSimpan" value="Simpan" onclick="simpan_editMT('$idpermohonan','$id_perbicaraan','$id_perintah')" />
        <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembali_skrin2TangguhEdit('$idpermohonan','$id_perbicaraan');" />
      </div> 
      
        <br/>
        <br/>	
        <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr>
        	  <td>
        	<i><font color="red" style="font-size:10px">Perhatian</font> <font style="font-size:10px">: Sila pastikan label</font> <font color="red" style="font-size:10px">*</font><font style="font-size:10px"> diisi.</font></i>      		
            </td>
       	  </tr>
      	</table> 
              
</fieldset>      
#end

<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratPindahMTII('$noFail')">Surat Pindah Mahkamah Tinggi</a></td>
      </tr>           
    </table>
</fieldset> 

<input type="hidden"  name="mode" />
<input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" />   
<input type="hidden" name="noFail" id="noFail" value="$noFail"/>
<input type="hidden" name="idpermohonan" id="idpermohonan" value="$idpermohonan"/>
<!--<input type="hidden" name="id" id="id" value="$id"/>-->
<input type="hidden" readonly="readonly" name="idsuburusanstatusfail" id="idsuburusanstatusfail" value="$idsuburusanstatusfail" />
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>
<input type="hidden" name="sorReason" id="sorReason" value="3"/> 
<!-- value = 3 [pilihan radio button adalah Menunggu Keputusan Rujukan Mahkamah Tinggi] -->
<input type="hidden" readonly="readonly" name="id_status" id="id_status" value="$idstatus" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
    
#parse("app/ppk/headerppk_script.jsp") 
<script>
function setTable(noFail){
	if(document.getElementById(noFail).style.display=="none"){
		document.getElementById(noFail).style.display="block";
	}
	else if(document.getElementById(noFail).style.display=="block"){
		document.getElementById(noFail).style.display="none";
	}
}

function cetakSuratPindahMTII(noFail) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMTII&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
    document.${formName}.command.value="getDaftarStatus";		
	document.${formName}.action="";	
}

function Simpan(idpermohonan){

	var dat1=document.${formName}.txdTarikhPerintahAdd

	if(document.${formName}.jenisPerintah.value == ""){
		alert("Sila pilih \"Kod Keluar Perintah\" terlebih dahulu.");
  		document.${formName}.jenisPerintah.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhPerintahAdd.value == ""){
		alert("Sila pilih \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerintahAdd.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	else if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Nama Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.command.value="getSimpanMahkamah";
	document.${formName}.action="$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
	}
}
function simpan_editMT(idpermohonan,id_perbicaraan,id_perintah){

	var dat1=document.${formName}.txdTarikhPerintahEdit

	if(document.${formName}.jenisPerintah.value == ""){
		alert("Sila pilih \"Kod Keluar Perintah\" terlebih dahulu.");
  		document.${formName}.jenisPerintah.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhPerintahEdit.value == ""){
		alert("Sila pilih \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerintahEdit.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	else if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Nama Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	
	else{
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.command.value="simpan_editMT";
	document.${formName}.action="$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
	}
}
function kembali_skrin2TangguhEdit(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2TangguhEdit";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function kembali_skrin2Tangguh(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2Tangguh";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function kembali_skrin2TangguhAdd(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2TangguhAdd";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function getDaerah(){
	document.${formName}.command.value="getMahkamah";
	document.${formName}.action="$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();	
}
function getAddress(){
	document.${formName}.command.value="getMahkamahAddress";
	document.${formName}.action="$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();	
}
function skrin_kemaskiniMT(idpermohonan,id_perbicaraan,id_perintah){
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.command.value="skrin_kemaskiniMT";
	document.${formName}.action="$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();	
}
function doChangeidNegeri() {
	document.${formName}.command.value = "doChangeidNegeri";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();
	
}
function doChangeidNegeriUpdate() {
	document.${formName}.command.value = "doChangeidNegeriUpdate";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();
	
}
function doChangeidMahkamah() {
	document.${formName}.command.value = "doChangeidMahkamah";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();
	
}
function doChangeidMahkamahUpdate() {
	document.${formName}.command.value = "doChangeidMahkamahUpdate";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();
	
}
function getTarikhBicara() {

	var TB  = document.getElementById("tarikhBicara").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10)-14;
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
	document.getElementById("tarikhBicara").value = tarikhNotis ;
	}else{
	document.getElementById("tarikhBicara").value = "" ;
	}
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
</script>
