#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $idFail = $data.get("id_fail") )
#set ( $idstatus = $data.get("id_status") )
    <input type="hidden" name="id_status" id="id_status"  value="$idstatus" />

#foreach($list in $listSemak)
  #set($nofail=$list.noFail)
  #set($negeri=$list.pmNama_negeri)
  #set($daerah=$list.namadaerah)
  #set($unit=$list.namaPejabat)
  #set($seksyen=$list.seksyen)
  #set($statusFail=$list.keterangan)
  #set($tarikhMohon=$list.tarikhMohon)
  #set($namaSimati=$list.namaSimati)
  #set($namaSipemohon=$list.namaPemohon)
  #set($id_permohonan=$list.idPermohonan)
  ##set($idstatus=$list.id_Status)

    <!-- case utk butang Seterusnya ke Notis -->
	<input type="hidden" name="id_permohonan" value="$id_permohonan">   
#end

#foreach($data in $dataPerbicaraan)
    ##set ($idpermohonan = $data.idPermohonan)
    #set ($idPemohon = $data.idPemohon)
    #set ($id_perbicaraan = $data.id_perbicaraan)
    #set ($tarikhBicara = $data.tarikh_bicara)
    #set ($idUnitpsk = $data.id_unitpsk)
    #set ($namaPegawai = $data.nama_pegawai)
#end

#if ( $action == "onChange" ) 
    #foreach($data in $infoPermohonanROTS)
        #set ($id_borangj = $data.id_borangj)
        #set ($jenis_rujukan = $data.jenis_rujukan)
        #set ($tarikh_bicara = $data.tarikh_bicara)
        #set ($tarikh_mohon = $data.tarikh_mohon)
        #set ($id_mahkamah = $data.id_mahkamah)
        #set ($catatan1 = $data.catatan1)
        #set ($keputusan_mahkamah = $data.keputusan_mahkamah)
        #set ($namanegeri = $data.nama_negeri)
        #set ($namadaerah = $data.nama_daerah)
        #set ($nama_pejabat = $data.nama_pejabat)
        #set ($alamat1 = $data.alamat1)
        #set ($alamat2 = $data.alamat2)
        #set ($alamat3 = $data.alamat3)
        #set ($poskod = $data.poskod)
        #set ($no_tel = $data.no_tel)
        #set ($no_fax = $data.no_fax) 
        #set ($tarikh_hantar_borangj = $data.tarikh_hantar_borangj) 
    #end
#end

#if ( $action == "onChangeMufti" ) 
    #foreach($dataMufti in $infoPermohonanMufti)
    	#set ($id_borangj = $dataMufti.id_borangj)
        #set ($jenis_rujukan = $dataMufti.jenis_rujukan)
        #set ($tarikh_mohon = $dataMufti.tarikh_mohon)
        #set ($id_negeri = $dataMufti.id_negeri)
        #set ($catatan1 = $dataMufti.catatan1)
        #set ($keputusan_mahkamah = $dataMufti.keputusan_mahkamah)
        #set ($nama_pejabat = $dataMufti.nama_pejabat)
        #set ($alamat1 = $dataMufti.alamat1)
        #set ($alamat2 = $dataMufti.alamat2)
        #set ($alamat3 = $dataMufti.alamat3)
        #set ($poskod = $dataMufti.poskod)
        #set ($id_bandar = $dataMufti.id_bandar) 
        #set ($no_tel = $dataMufti.no_tel)
        #set ($no_fax = $dataMufti.no_fax) 
        #set ($flag_rujukan = $dataMufti.flag_rujukan) 
    #end
#end

<input type="hidden" name="command2">    
<input type="hidden" name="id_borangj" id="id_borangj" value="$id_borangj"/>
<input type="hidden" name="idpermohonan" id="idpermohonan" value="$idpermohonan"/>
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>

<input type="hidden" readonly="readonly" name="idsuburusanstatusfail" id="idsuburusanstatusfail" value="$idsuburusanstatusfail" />
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
<input type="hidden" name="sorReason" id="sorReason" value="5"/> 
<!-- value = 5 [pilihan radio button adalah Menunggu Keputusan Rujukan Mahkamah Syariah] -->
<input type="hidden" name="tabId" id="tabId" value="$selectedTab"/>   
<input type="hidden" name="nofail" id="nofail" value="$nofail">
<input type="hidden" name="idfail" value="$idFail" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon">
  
</p>
<p></p>
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
                        <td width="65%"><font color="blue">$nofail</font></td>
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
                        <td width="38%" valign="top" style="text-transform:uppercase"><div align="right">Status Fail</div></td>
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

<p></p>

#if ( $addMode == "yes" )

    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">PERMOHONAN</li>  
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">     
        
        #foreach($data in $dataPerbicaraan)
            ##set ($id = $data.idPermohonan)
            #set ($idPemohon = $data.idPemohon)
            #set ($id_perbicaraan = $data.id_perbicaraan)
            #set ($tarikhBicara = $data.tarikh_bicara)
            #set ($idUnitpsk = $data.id_unitpsk)
            #set ($namaPegawai = $data.nama_pegawai)
        #end      
        
        <p></p>
         <fieldset>
          <legend>PERMOHONAN RUJUKAN <em>RULER OF THE STATE</em> ATAU MAHKAMAH TINGGI</legend>         
<table width="100%"  cellspacing="1" cellpadding="1" border="0">
               
               <tr>
                 <td width="30%"><font color="red">*</font>&nbsp;Rujukan Kepada </td>
                 <td width="1%">:</td>
                 <td width="69%">
                 <input name="jenis_rujukan" id="jenis_rujukan"type="radio" value="1" $tempcheckedmahkamahtinggi onclick="openMT();" />   
                 Mahkamah Tinggi                 
                 </td>
               </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td><input name="jenis_rujukan" id="jenis_rujukan" type="radio" value="2" $tempcheckedrots onclick="openFlagRujukan();" />
                   <em>Ruler Of The State</em> </td>
               </tr>
             </table>
             
            #if ( $flagRujukan == "rulerofthestate" )
            <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
                <tr>
                  <td width="16%">&nbsp;</td>
                  <td width="10%">&nbsp;</td>
                  <td width="9%">&nbsp;</td>
                  <td width="21%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1" $tempcheckedms onclick="openROTS();" />
                  Mahkamah Syariah</td>
                  <td width="44%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2" $tempcheckedpejmufti onclick="openPejMufti();" />
                  Pejabat Mufti</td>
              </tr>
            </table>
            #end
 
         #if ( $button == "kembali" )
              <table width="100%">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">                        
                      #if ( $idstatus == "18" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                	  #end   
                                         
                      #if ( $idstatus == "44" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                	  #end                      
                      </div>
                      </td>
                	</tr>            
               </table>    
               #end
               
            #if ( $flag == "MT" ) 
            <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
            <tr>
               <td width="30%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="1%">:</td>
		       <td width="69%">&nbsp;<input name="txdTarikhRujukanAdd" value="$!txdTarikhRujukanAdd" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td>&nbsp;$selectNegeri
              <input type="hidden" name="txtidnegeri" id="txtidnegeri" maxlength="50" size="6" value="$!txtidnegeri" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly="true" class="disabled" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Mahkamah Tinggi </td>
              <td>:</td>
              <td>&nbsp;$selectBicara
              <input type="hidden" name="id_pejabat" id="id_pejabat" maxlength="50" size="6" value="$id_pejabat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Alamat</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>           
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>                
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!notel" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!nofax" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
              <td>:</td>
              <td>&nbsp;$selectPegawai</td>
            </tr>
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8" ></textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
              <!-- ADD MODE -->
              <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>
              
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8" ></textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
              <!-- ADD MODE -->
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>                 
                
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>       
             </table>
         </fieldset>
        <table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="">
        			</label></td>
        		</tr>	
        	</table>
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
          <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="3%" style="text-transform:uppercase"></td>
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                    #foreach ( $senarai in $dataListWaris )
                	
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                      
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                <tr>
                    <td class="$row"><input type="checkbox" name="cbsemaks" value="$senarai.id_ob"></td>
                    <td class="$row"><div align="center">$senarai.bil</div></td>
                    <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                    <td class="$row">$senarai.no_kp_baru</td>
                    <td class="$row">$senarai.keterangan</td>
               	</tr>
                    #end                  
              </table>  
              
              <table width="100%">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">
                        <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: getSimpanMahkamahROTS('$idpermohonan','$id_perbicaraan');" />
                        
                      #if ( $idstatus == "18" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                	  #end   
                                         
                      #if ($idstatus == "44" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                	  #end                      
                      </div>
                      </td>
                	</tr>            
              </table>             
                       
              #end <!-- close utk flag = MT -->               

        	
            #if ( $flag == "ROTS" ) 
            <table width="100%"  cellspacing="1" cellpadding="1" border="0">             

            
            #if ( $jenispejabat == "syariah" )
            <tr>
               <td width="31%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="1%">:</td>
		       <td width="69%">&nbsp;<input name="txdTarikhRujukanAdd" value="$!txdTarikhRujukanAdd" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
            </tr>            
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Mahkamah Syariah </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectBicara&nbsp;</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Alamat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="$!alamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>           
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" value="$!alamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" value="$!alamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" readonly class="disabled"></td>
            </tr>            
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!notel" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!nofax" readonly class="disabled"></td>
            </tr>
               <tr>
                 <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                 <td>:</td>
                 <td colspan="3">&nbsp;$selectPegawai</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8" ></textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
              <!-- ADD MODE -->
              <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>                 
 
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>                      
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8" >$!txtPendapatAdd</textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
              <!-- ADD MODE -->
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>  
                             
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>               
               <tr>
                 <td width="31%" valign="top">&nbsp;</td>
                 <td width="1%" valign="top">&nbsp;</td>
                 <td colspan="3" valign="top">&nbsp;</td>
              </tr>      
         #end <!-- close $jenispejabat == "syariah" -->
  
         
         
         #if ( $jenispejabat == "pejmufti" )
         <tr>
               <td width="31%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="1%">:</td>
		       <td width="69%">&nbsp;<input name="txdTarikhRujukanAdd" value="$!txdTarikhRujukanAdd" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
            </tr>         
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri</td>
              <td>:</td>
              <td>&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Nama Pejabat</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Alamat</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>            
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>           
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Bandar</td>
              <td>&nbsp;</td>
              <td>&nbsp;$selectBandar</td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" id="txtfax" onkeyup="validateNumber(this,this.value);" /></td>
            </tr>  
               <tr>
                 <td valign="top"><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                 <td valign="top">:</td>
                 <td valign="top">&nbsp;$selectPegawai</td>
               </tr>
               <tr>
                 <td valign="top" width="31%">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8" >$!txtFaktaGuamanAdd</textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
              <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>                 

               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top" width="31%">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8" >$!txtPendapatAdd</textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
              <!-- ADD MODE -->
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                              
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>                   
         </table>
         </fieldset>
         #end	<!-- close for $jenispejabat == "pejmufti" -->
         
         
         
        <table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="">
        			</label></td>
        		</tr>	
        	</table>
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
          <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="3%" style="text-transform:uppercase"></td>
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                    #foreach ( $senarai in $dataListWaris )
                	
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                <tr>
                    <td class="$row"><input type="checkbox" name="cbsemaks" value="$senarai.id_ob"></td>
                    <td class="$row"><div align="center">$senarai.bil</div></td>
                    <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                    <td class="$row">$senarai.no_kp_baru</td>
                    <td class="$row">$senarai.keterangan</td>
               	</tr>
                    #end                  
              </table>           
              
              <table width="100%">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">
                        #if ( $jenispejabat == "syariah" )
                        <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: getSimpanMahkamahSyariahROTS('$idpermohonan','$id_perbicaraan');" />
                        #end
                        #if ( $jenispejabat == "pejmufti" )
                        <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: getSimpanMufti('$idpermohonan','$id_perbicaraan');" />
                        #end
                        
                      #if ( $idstatus == "18" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                	  #end   
                                         
                      #if ( $idstatus == "44" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                	  #end                      
                      </div>
                      </td>
                	</tr>            
				#end <!-- close utk flag = ROTS -->    
               </table>   
                           
        <table width="100%"  cellpadding="1" cellspacing="1" border="0">
        	<tr><td>
        	<i><font color="red" style="font-size:10px">Perhatian</font> <font style="font-size:10px">: Sila pastikan label bertanda</font> <font color="red" style="font-size:10px">*</font> <font style="font-size:10px"> diisi.</font></i>
      		</td>
       	  </tr>
      	</table>   
                 
</fieldset>
              
     </div>	
  </div> 
  </div>  
#end

<!-- ------------------------------------------- VIEW MODE -------------------------------------------- -->

#if ( $viewMode == "yes" )


<div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">PERMOHONAN</li>
        <li class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">    
        <div class="TabbedPanelsContent">     
        
        #foreach($data in $dataPerbicaraan)
            ##set ($idpermohonan = $data.idPermohonan)
            #set ($idPemohon = $data.idPemohon)
            #set ($id_perbicaraan = $data.id_perbicaraan)
            #set ($tarikhBicara = $data.tarikh_bicara)
            #set ($idUnitpsk = $data.id_unitpsk)
            #set ($namaPegawai = $data.nama_pegawai)
        #end
        <p></p>
         <fieldset>
          <legend>PERMOHONAN RUJUKAN <em>RULER OF THE STATE</em> ATAU MAHKAMAH TINGGI</legend>         
<table width="100%"  cellspacing="1" cellpadding="1" border="0">               
               <tr>
                 <td width="30%">Tarikh Rujukan</td>
                 <td width="1%">:</td>
                 <td width="69%">&nbsp;<input name="txdTarikhRujukanAdd" value="$!tarikh_mohon" size="11" id="txdTarikhRujukanAdd" type="text" readonly class="disabled" /></td>
               </tr>
               <tr>
                 <td>Rujukan Kepada </td>
                 <td>:</td>
                 <td><input name="jenis_rujukan" type="radio" value="1" disabled $TEMPcheckedMahkamahTinggi />
                   Mahkamah Tinggi</td>
               </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td><input name="jenis_rujukan" type="radio" value="2" disabled $TEMPcheckedROTS />
                   <em>Ruler Of The State</em></td>
  </tr>
         </table>
              
              #if ( $flag == "MT" )
              
              <table  width="100%"  cellspacing="1" cellpadding="1" border="0">
                    <tr>
                      <td width="30%">Negeri </td>
                      <td width="1%">:</td>
                      <td width="69%">&nbsp;$selectNegeri
                      <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" /></td>
                </tr>
                    <tr>
              <td>Mahkamah Tinggi </td>
              <td>:</td>
              <td>&nbsp;$selectBicara
              <input type="hidden" name="id_pejabat" id="id_pejabat" maxlength="50" size="6" value="$id_pejabat" /></td>
            </tr>
            <tr>
              <td>Alamat</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat13" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>
            
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat14" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat15" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
                
            <tr>
              <td>Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly>      </td>
            </tr>
            
            <tr>
              <td>No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!notel" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td>No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!nofax" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td>Pegawai Pengendali</td>
              <td>:</td>
              <td>&nbsp;$selectPegawai</td>
            </tr>
               <tr>
                 <td valign="top">Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8" >$!catatan1</textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                </tr>
                
               <!-- ADD MODE --> 
               <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 				
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>
			                  
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top">Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8" >$!keputusan_mahkamah</textarea></td>
               </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
                 
                 <!-- VIEW MODE -->
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>
               </tr>
             </table>
         
<table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="$senarai.id_ob">
        			</label></td>
        		</tr>	
       	 </table>          
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
                 <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="6%"><b>No.</b></td>
                      <td width="31%"><b>Nama</b></td>
                      <td width="33%"><b>No KP Baru</b></td>
                      <td width="27%"><b>Talian dengan SiMati</b></td>
                   </tr> 
                    #foreach ( $dataWaris in $SenaraiWaris )  
                        #set ($id_borangj = $dataWaris.id_borangj)
                        #set ($id_perbicaraan = $dataWaris.id_perbicaraan)
                        #set ($id_ob = $dataWaris.id_ob)
                        #set ($nama_ob = $dataWaris.nama_ob)
                        #set ($no_kp_baru = $dataWaris.no_kp_baru)
                        #set ($keterangan = $dataWaris.keterangan)
                    
                    #if ($dataWaris.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($dataWaris.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                    <tr>
                        <td class="$row">$dataWaris.bil</td>
                        <td class="$row">$dataWaris.nama_ob.toUpperCase()</td>
                        <td class="$row">$dataWaris.no_kp_baru</td>
                        <td class="$row">$dataWaris.keterangan</td>
                   </tr>
                    #end
                </table>
                
              <p></p>
              <table width="100%">
              	<tr>
                	<td>
                      <div align="center">
                        <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="javascript: skrin_editROTS('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />                    
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />                    
                    </div></td>
                </tr>
         </table>
              #end <!-- CLOSE FOR FLAG = MT -->


            #if ( $flag == "ROTS" )      
            #if ( $jenispejabat == "syariah" )
             <table width="100%"  cellspacing="1" cellpadding="1" border="0">
              <tr>
                <td width="30%">&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td width="4%">&nbsp;</td>
                <td width="26%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1" disabled $tempcheckedms onclick="openROTS();" />
                  Mahkamah Syariah</td>
                <td width="39%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2" disabled $tempcheckedpejmufti onclick="openPejMufti();" />
                  Pejabat Mufti</td>
              </tr>
              <tr>
                <td><font color="red">*</font>&nbsp;Negeri </td>
                <td>:</td>
                <td colspan="3">&nbsp;$selectNegeri
                  <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" /></td>
              </tr>
              <tr>
                <td><font color="red">*</font>&nbsp;Mahkamah Syariah </td>
                <td>:</td>
                <td colspan="3">&nbsp;$selectBicara&nbsp;
                    <input type="hidden" name="id_pejabat" id="id_pejabat" value="$id_pejabat" /></td>
              </tr>
              <tr>
                <td><font color="red">*</font>&nbsp;Alamat</td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtAlamat4" id="txtAlamat4" value="$!alamat1" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td></td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtAlamat4" id="txtAlamat5" value="$!alamat2" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td></td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtAlamat4" id="txtAlamat6" value="$!alamat3" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td><font color="red">*</font>&nbsp;Poskod</td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtTelefon" id="txtTelefon" value="$!notel" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td>&nbsp;&nbsp;&nbsp;No Fax</td>
                <td>:</td>
                <td colspan="3">&nbsp;
                    <input type="text" name="txtfax" id="txtfax" value="$!nofax" readonly class="disabled" /></td>
              </tr>
              <tr>
                <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                <td>:</td>
                <td colspan="3">&nbsp;$selectPegawai</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                <td valign="top">:</td>
                <td colspan="3" valign="top">&nbsp;
                    <textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8">$!catatan1</textarea></td>
              </tr>
              <tr>
                <td colspan="5" valign="top">&nbsp;</td>
                
              <!-- VIEW MODE -->
              <script> 			  
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>                
               </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="3" valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                <td valign="top">:</td>
                <td colspan="3" valign="top">&nbsp;
                <textarea name="txtPendapatAdd" id="txtPendapatAdd"  cols="80%" rows="8" >$!keputusan_mahkamah</textarea></td>
              </tr>
              <tr>
                <td colspan="5" valign="top">&nbsp;</td>
                
              <!-- VIEW MODE -->   
              <script> 			  
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script> 
               </tr>                           
              </table>
              #end  	<!-- close jenispejabat == "syariah" -->
    
              
              #if ( $jenispejabat == "pejmufti" )
               <table width="100%"  cellspacing="1" cellpadding="1" border="0">                  
                  <tr>
                    <td width="30%">&nbsp;</td>
                    <td width="1%">&nbsp;</td>
                    <td width="4%">&nbsp;</td>
                    <td width="24%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1" disabled $tempcheckedms onclick="openROTS();" />
Mahkamah Syariah</td>
                    <td width="41%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2" disabled $tempcheckedpejmufti onclick="openPejMufti();" />
Pejabat Mufti</td>
                 </tr>
                  <tr>
                    <td><font color="red">*</font>&nbsp;Negeri </td>
                    <td>:</td>
                    <td colspan="3">&nbsp;$selectNegeri</td>
                  </tr>
                  <tr>
                    <td><font color="red">*</font>&nbsp;Nama Pejabat</td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" value="$!nama_pejabat" maxlength="100" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td><font color="red">*</font>&nbsp;Alamat</td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtAlamat4" id="txtAlamat4" value="$!alamat1" maxlength="80" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtAlamat4" id="txtAlamat5" value="$!alamat2" maxlength="80" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtAlamat4" id="txtAlamat6" value="$!alamat3" maxlength="80" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td><font color="red">*</font>&nbsp;Poskod</td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td>&nbsp;&nbsp;&nbsp;Bandar</td>
                    <td>&nbsp;</td>
                    <td colspan="3">&nbsp;$selectBandar</td>
                  </tr>
                  <tr>
                    <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!no_tel" onkeyup="validateNumber(this,this.value);" readonly class="disabled" /></td>
                  </tr>
                  <tr>
                    <td>&nbsp;&nbsp;&nbsp;No Fax</td>
                    <td>:</td>
                    <td colspan="3">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!no_fax" onkeyup="validateNumber(this,this.value);" readonly class="disabled"  /></td>
                  </tr>
                  <tr>
                    <td valign="top"><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                    <td valign="top">:</td>
                    <td colspan="3" valign="top">&nbsp;$selectPegawai</td>
                  </tr>
                  <tr>
                    <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                    <td valign="top">:</td>
                    <td colspan="3" valign="top">&nbsp;
                    <textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8" >$!catatan1</textarea></td>
                  </tr>
                  <tr>
                    <td colspan="5" valign="top">&nbsp;</td>
                    
              <!-- VIEW MODE -->      
              <script> 			  
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td colspan="3" valign="top">&nbsp;</td>
                  </tr>
                  <tr>
                    <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                    <td valign="top">:</td>
                    <td colspan="3" valign="top">&nbsp;
                    <textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8" >$!keputusan_mahkamah</textarea></td>
                  </tr>
                  <tr>
                    <td colspan="5" valign="top">&nbsp;</td>
             
              <!-- VIEW MODE -->       
              <script> 			  
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>                    
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td colspan="3" valign="top">&nbsp;</td>
                  </tr>
            </table>
         #end	<!-- close for $jenispejabat == "pejmufti" -->
         
       
        <table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="">
        			</label></td>
        		</tr>	
       	 </table>
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
          		<table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <!--<td width="3%" style="text-transform:uppercase"></td>-->
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                    #foreach ( $senarai in $SenaraiWaris )
                	
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
            	<tr>
                   <!-- <td class="$row"><input type="checkbox" name="cbsemaks" value="$senarai.id_ob"></td>-->
              		<td class="$row"><div align="center">$senarai.bil</div></td>
                    <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                    <td class="$row">$senarai.no_kp_baru</td>
                    <td class="$row">$senarai.keterangan</td>
               	</tr>
                    #end                  
              </table>           
              
              <table width="100%">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">
                        #if ( $jenispejabat == "syariah" )
                        <input name="cmdINTJKSM" type="button" value="Semakan JKSM" onclick="javascript:checkJKSM('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="javascript: skrin_kemaskiniSyariah('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdHapus" type="button" value="Hapus" onclick="javascript:HapusSyariah('$idpermohonan','$id_perbicaraan','$id_borangj');" />
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />
                        #end
                        #if ( $jenispejabat == "pejmufti" )
                        <input name="cmdSimpan" type="button" value="Kemaskini" onclick="javascript: skrin_kemaskiniMufti ('$idpermohonan','$id_perbicaraan');" />
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />
                        #end
                        
                      #if ( $idstatus == "18" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                	  #end   
                                         
                      #if ( $idstatus == "44" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                	  #end                      
                      </div>
                      </td>
                	</tr>            				 
              </table>	
           </fieldset>
              			             
              #end <!-- close utk flag = ROTS -->              
     
     </fieldset>
   </div>  
     
     
 

        <div class="TabbedPanelsContent"> 
 
        	<fieldset>
        		<legend>KEPUTUSAN</legend>
             <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
                <tr>
                  <td width="31%">Tarikh Hantar Borang J</td>
                  <td width="1%">:</td>
                  <td width="68%"><input name="txdTarikhHantarAdd" value="" size="11" id="txdTarikhHantarAdd" type="text" readonly class="disabled" /></td>
                </tr>
                <tr>
                  <td>Tarikh Terima Keputusan</td>
                  <td>:</td>
                  <td><input name="txdTarikhTerimaAdd" value="" size="11" id="txdTarikhTerimaAdd" type="text" readonly class="disabled" /></td>
                </tr>
                <tr>
                  <td valign="top">Keputusan</td>
                  <td valign="top">:</td>
                  <td><textarea name="txtKeputusanAdd" id="txtKeputusanAdd" cols="80%" rows="8" ></textarea></td>
                </tr>                                             
                <tr>
                  <td colspan="3" valign="top">&nbsp;</td>
               </tr>
              
              <!-- VIEW MODE -->  
              <script> 			  
              	var area = new FCKeditor("txtKeputusanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtKeputusanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>                  
                
                <tr>
                  <td valign="top">&nbsp;</td>
                  <td valign="top">&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td valign="top">Catatan</td>
                  <td valign="top">:</td>
                  <td><textarea name="txtCatatanAdd" id="txtCatatanAdd" cols="80%" rows="8" ></textarea></td>
                </tr>
                <tr>
                  <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               
              <!-- VIEW MODE -->  
              <script> 			  
              	var area = new FCKeditor("txtCatatanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtCatatanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>               
             </table>	   	
         </fieldset>

         <p></p>
         <fieldset>
         <legend>SENARAI FAIL</legend>    	
            <table width="100%" border="0" cellspacing="0">
              <tr class="table_header">
                <td width="8%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
      			<td width="33%" style="text-transform:uppercase">Nama</td>
      			<td width="31%" style="text-transform:uppercase">Jenis</td>
                <td width="14%">Hapus</td>
              </tr>
              #set ($counter = 0)
              #foreach($lampiran in $SenaraiLampiran)
              #set ($counter = $counter + 1)
                #if ($lampiran.bil == '') 
                #set ($row = 'row1')
              #elseif ($lampiran.bil % 2 != 0)
                #set ($row = 'row1')
              #else 
                #set ($row = 'row2')
              #end 
              <tr>
                <td class="$row"><div align="center">$counter</div></td>               
                <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_lampiran','$lampiran.id_dokumen')" class="style2">$lampiran.tajuk_dokumen</a></td>               
                <td class="$row">$lampiran.jenis_mime</td>
                <td width="14%" class="$row"><div align="center">
                <a href="javascript: hapusFail('$lampiran.id_lampiran','$lampiran.id_dokumen');"><img src="../img/hapus.gif" alt="Hapus" border="0" width="18" height="18" /></a></div></td>
              </tr>
            #end
            </table>
          

            <table width="100%">
            	<tr>
                	<td><div align="center">
                	  <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="edit_keputusanROTS('$idpermohonan','$id_perbicaraan');" />                      
                	  <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" /> 
                       
                      #if ( $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )                  
              	  	  <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$id_status')" />
              	  	  #end
                      
                  </div></td>
                </tr>
            </table>
        	</div>
        </fieldset>    
         
      
  </div> 
  </div>  
#end


#if ( $editMode == "yes" )

#if ( $action == "onChange" )
    #foreach($dataROTS in $infoPerintahTangguhROTS)
        #set ($id_borangj = $dataROTS.id_borangj)
        #set ($flag_jenis_keputusan = $dataROTS.flag_jenis_keputusan)
        #set ($id_unitpsk = $dataROTS.id_unitpsk)
        #set ($nama_pegawai = $dataROTS.nama_pegawai)
        #set ($jenis_keluar_perintah = $dataROTS.jenis_keluar_perintah)
        #set ($tarikh_perintah = $dataROTS.tarikh_perintah)
        #set ($id_mahkamah = $dataROTS.id_mahkamah)
        #set ($id_negeri = $dataROTS.id_negeri)
        #set ($nama_negeri = $dataROTS.nama_negeri)
        #set ($id_daerah = $dataROTS.id_daerah)
        #set ($nama_daerah = $dataROTS.nama_daerah)
        #set ($nama_pejabat = $dataROTS.nama_pejabat)
        #set ($alamat1 = $dataROTS.alamat1)
        #set ($alamat2 = $dataROTS.alamat2)
        #set ($alamat3 = $dataROTS.alamat3)
        #set ($poskod = $dataROTS.poskod)
        #set ($no_tel = $dataROTS.no_tel)
        #set ($no_fax = $dataROTS.no_fax)  
        #set ($catatan1 = $dataROTS.catatan1)  
        #set ($keputusan_mahkamah = $dataROTS.keputusan_mahkamah)
        #set ($tarikh_hantar_borangj = $dataROTS.tarikh_hantar_borangj)
        #set ($tarikh_terima_borangj = $dataROTS.tarikh_terima_borangj)
        #set ($catatan2 = $dataROTS.catatan2) 
        #set ($catatan3 = $dataROTS.catatan3) 
        #set ($catatan4 = $dataROTS.catatan4) 
        #set ($catatan5 = $dataROTS.catatan5) 
        #set ($tarikh_mohon = $dataROTS.tarikh_mohon) 
    #end
#end


<input type="hidden" name="id_borangj" id="id_borangj" value="$id_borangj"/>

    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
      	#if ( $tab == "permohonan" )
        <li class="TabbedPanelsTab" tabindex="0">PERMOHONAN</li>
        #end
        
        #if ( $tab == "keputusan" )
        <li class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
        #end
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      
      #if ( $tab == "permohonan" )
        <div class="TabbedPanelsContent">     
        
        #foreach($data in $dataPerbicaraan)
            #set ($idPemohon = $data.idPemohon)
            #set ($id_perbicaraan = $data.id_perbicaraan)
            #set ($tarikhBicara = $data.tarikh_bicara)
            #set ($idUnitpsk = $data.id_unitpsk)
            #set ($namaPegawai = $data.nama_pegawai)
        #end
        <p></p>
         <fieldset>
          <legend>PERMOHONAN RUJUKAN <em>RULER OF THE STATE</em> ATAU MAHKAMAH TINGGI</legend>         
<table width="100%"  cellspacing="1" cellpadding="1" border="0">
               <tr>
                 <td width="30%"><font color="red">*</font>&nbsp;Rujukan Kepada</td>
                 <td width="1%">:</td>
                 <td width="69%"><input name="jenis_rujukan" type="radio" value="1"  $TEMPcheckedMahkamahTinggi  onclick="openMT();" />
                 Mahkamah Tinggi</td>
       </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td><input name="jenis_rujukan" type="radio" value="2"  $TEMPcheckedROTS  onclick="openFlagRujukan();"/>
                   <em>Ruler Of The State</em></td>
  </tr>
      </table>         
               
               
               
               #if ( $flag == "MT" )     
     <table width="100%"  cellspacing="1" cellpadding="1" border="0">          
               <tr>
                 <td><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
                 <td>:</td>
                 <td width="69%">&nbsp;<input name="txdTarikhRujukanAdd" value="$!tarikh_mohon" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
       </tr>
               <tr>
                 <td><font color="red">*</font>&nbsp;Negeri </td>
                 <td>:</td>
                 <td>&nbsp;$selectNegeri</td>
               </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Mahkamah Tinggi </td>
              <td>:</td>
              <td>&nbsp;$selectBicara
              <input type="hidden" name="id_pejabat" id="id_pejabat" size="6" value="$id_pejabat" readonly class="disabled" /></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Alamat</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat10" maxlength="50" size="50" value="$!alamat1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>
            
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat11" maxlength="50" size="50" value="$!alamat2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat12" maxlength="50" size="50" value="$!alamat3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" class="disabled" readonly /></td>
            </tr>
                
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly>      </td>
            </tr>
            
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" maxlength="50" size="15" value="$!notel" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" maxlength="50" size="15" value="$!nofax" class="disabled" readonly>      </td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
              <td>:</td>
              <td>&nbsp;$selectPegawai</td>
            </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td valign="top"><textarea name="txtFaktaGuamanAdd" cols="80%" rows="7" id="txtFaktaGuamanAdd">$!catatan1</textarea></td>
               </tr>     
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
       		   </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
			   </tr>
              
              <!-- EDIT MODE --> 
              <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>
              
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td width="30%" valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td width="1%" valign="top">:</td>
                 <td width="69%" valign="top"><textarea name="txtPendapatAdd" cols="80%" rows="8" id="txtPendapatAdd">$!keputusan_mahkamah</textarea></td>
       		   </tr>
               <tr>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>
                              
              <!-- EDIT MODE -->
			  <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                           
         </table>
         </fieldset>
  
            
            <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
            
                 <table width="100%" border="0" cellspacing="2">
                     <tr>
                     	<td colspan="5"><input type="button" name="cmdTambahWaris" id="cmdTambahWaris" value="Tambah" onclick="javascript:pilihWaris('$idpermohonan')" /></td>
                    </tr>
                    <tr class="table_header">
                      <td width="6%" style="text-transform:uppercase" align="center">No.</td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                      <td width="14%" style="text-transform:uppercase">Hapus</td>
                   </tr> 

                 #foreach ( $dataWaris in $SenaraiWaris )
                    
                    #if ($dataWaris.bil == '')
                    #set ($row = 'row1')                   
                    #elseif ($dataWaris.bil % 2 != 0)
                         #set ($row = 'row1')                        
                    #else 
                        #set ($row = 'row2')
                    #end                    
                    
                 <tr>
                    <td class="$row" align="center">$dataWaris.bil</td>
                    <td class="$row">$dataWaris.nama_ob.toUpperCase()</td>
                    <td class="$row">$dataWaris.no_kp_baru</td>
                    <td class="$row">$dataWaris.keterangan</td>
                    <td width="14%" class="$row"><div align="center">
                    <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript: hapusIDob();" >-->
                    <a href="javascript: hapusIDob();"><img src="../img/hapus.gif" alt="Hapus" border="0" width="18" height="18" /></a></div></td>
                 </tr>
                 
                    #end
                </table>
  </fieldset>           
                    
			  <br/>            
              <fieldset>
              <legend><strong>SENARAI WARIS YANG DIPILIH</strong></legend>
        
                 <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="3%" style="text-transform:uppercase"></td>
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>                      
                   </tr>                    
                  
                     #set ($listSelectedWaris = "")
                      #foreach ($listSelectedWaris in $ListSelectedWaris)
                        #if ($listSelectedWaris.bil == '')
                            #set( $row = "row1" )
                        #elseif (($listSelectedWaris.bil % 2) != 0)
                            #set( $row = "row1" )
                        #else 
                            #set( $row = "row2" )
                        #end
                    
                 <tr>
                    <td class="$row"><input type="checkbox" name="cbsemaks" $TEMPcheckbox value="$listSelectedWaris.id_ob" readonly ></td>
                    <td class="$row" align="center">$listSelectedWaris.bil</td>
                    <td class="$row">$listSelectedWaris.nama_ob.toUpperCase()</td>
                    <td class="$row">$listSelectedWaris.no_kp_baru</td>
                    <td class="$row">$listSelectedWaris.keterangan</td>
                 </tr>
                    #end                   
                </table>
          </fieldset>
            <table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="$listSelectedWaris.id_ob">
        			</label></td>
        		</tr>	
        	</table>
        	<p></p>
            <table width="100%">
            	<tr>
                	<td><div align="center">
                	  <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: simpan_editROTS('$idpermohonan','$id_perbicaraan');" />                	  
                	  <input name="cmdHapus" type="button" value="Hapus" onclick="javascript: hapusROTS('$idpermohonan','$id_perbicaraan','$id_borangj');" /> 
                	  <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
              	  </div></td>
                </tr>
            </table>             
			#end <!-- close flag == "MT"  -->
            
            
            
            #if ( $flag == "ROTS" )   
            <table width="100%"  cellspacing="1" cellpadding="1" border="0">             
            
            #if ( $jenispejabat == "syariah" )
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td width="3%">&nbsp;</td>
              <td width="21%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1"  $tempcheckedms onclick="openROTS();" />
Mahkamah Syariah</td>
              <td width="46%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2"  $tempcheckedpejmufti onclick="openPejMufti();" />
Pejabat Mufti</td>
            </tr>

            <tr>
               <td width="30%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="0">:</td>
		       <td colspan="3">&nbsp;<input name="txdTarikhRujukanAdd" value="$!tarikh_mohon" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
            </tr>            
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td colspan="5">&nbsp;$selectNegeri
              <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Mahkamah Syariah </td>
              <td>:</td>
              <td colspan="5">&nbsp;$selectBicara
              <input type="hidden" name="id_pejabat" id="id_pejabat" value="$id_pejabat" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Alamat</td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="$!alamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>           
              <td></td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" value="$!alamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" value="$!alamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" readonly class="disabled"></td>
            </tr>            
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!notel" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td colspan="5">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!nofax" readonly class="disabled"></td>
            </tr>
               <tr>
                 <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                 <td>:</td>
                 <td colspan="5">&nbsp;$selectPegawai</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td colspan="5" valign="top"><textarea name="txtFaktaGuamanAdd" cols="80%" rows="8" id="txtFaktaGuamanAdd" >$!catatan1</textarea></td>
               </tr>
               <tr>
                 <td colspan="7" valign="top">&nbsp;</td>
              </tr>
               <tr>
                 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr>  
              
              <!-- EDIT MODE -->              
              <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>                
               
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="5" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td colspan="5" valign="top"><textarea name="txtPendapatAdd" cols="80%" rows="8" id="txtPendapatAdd">$!keputusan_mahkamah</textarea></td>
               </tr>
               <tr>
                 <td colspan="7" valign="top">&nbsp;</td>
               </tr>
           	   <tr>
               	 <td></td>
                 <td></td>
              	 <td><div  class="disabled" id="word_count"></div></td>
               </tr> 
              
              <!-- EDIT MODE --> 
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                            
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="5" valign="top">&nbsp;</td>
               </tr>      
         #end <!-- close $jenispejabat == "syariah" -->
         
         
         
  
         
         
         #if ( $jenispejabat == "pejmufti" )
            <tr>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	        <td width="3%">&nbsp;</td>
	        <td width="21%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1"  $tempcheckedms onclick="openROTS();" />
	 Mahkamah Syariah</td>
	        <td width="46%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2"  $tempcheckedpejmufti onclick="openPejMufti();" />
	 Pejabat Mufti</td>
            </tr>
            <tr>
               <td width="30%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="0%">:</td>
		       <td colspan="3">&nbsp;<input name="txdTarikhRujukanAdd" value="$!tarikh_mohon" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhRujukanAdd',false,'dmy');" /></td>
            </tr>         
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Nama Pejabat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" value="$!nama_pejabat" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Alamat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="$!alamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>            
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" value="$!alamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" value="$!alamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>           
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Bandar</td>
              <td>&nbsp;</td>
              <td colspan="3">&nbsp;$selectBandar</td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!no_tel" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!no_fax" onkeyup="validateNumber(this,this.value);" /></td>
            </tr> 
               <tr>
                 <td><font color="red">*</font>&nbsp;Nama Pegawai Pengendali</td>
                 <td>:</td>
                 <td colspan="5">&nbsp;$selectPegawai</td>
               </tr>            
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtFaktaGuamanAdd" cols="80%" rows="8" id="txtFaktaGuamanAdd">$!catatan1</textarea></td>
               </tr>
               <tr>
                 <td colspan="5" valign="top">&nbsp;</td>
               </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td><div  class="disabled" id="word_count"></div></td>
                </tr>  
                             
              <!-- EDIT MODE -->
			  <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                             
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtPendapatAdd" cols="80%" rows="8" id="txtPendapatAdd" >$!keputusan_mahkamah</textarea></td>
               </tr>
               <tr>
                 <td colspan="5" valign="top">&nbsp;</td>
               </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td><div  class="disabled" id="word_count"></div></td>
                </tr> 
              
              <!-- EDIT MODE -->
              <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                                
         </table>
</fieldset>
         #end	<!-- close for $jenispejabat == "pejmufti" -->
         
         
         
         
         
         
         
        <table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="">
        			</label></td>
        		</tr>	
  </table>
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
          <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="3%" style="text-transform:uppercase"></td>
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                    #foreach ( $senarai in $dataSelectedWaris )
                	
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                <tr>
                        #if ( $senarai.flag == '1' )
                            #set ( $checked = "checked" )
                        #else
                           #set ( $checked = "" )
                        #end
                        
					<td class="$row"><input type="checkbox" name="cbsemaks" $checked value="$senarai.id_ob"></td>
                    <td class="$row"><div align="center">$senarai.bil</div></td>
                    <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                    <td class="$row">$senarai.no_kp_baru</td>
                    <td class="$row">$senarai.keterangan</td>
               	</tr>
                    #end                  
  		</table>           

              
              <table width="100%">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">
                        #if ( $jenispejabat == "syariah" )
                        <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript:simpanSyariah('$idpermohonan','$id_perbicaraan');" />
                        #end
                        #if ( $jenispejabat == "pejmufti" )
                        <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: simpanEditMufti('$idpermohonan','$id_perbicaraan');" />
                        #end
                        
                      #if ( $idstatus == "18" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                	  #end   
                                         
                      #if ( $idstatus == "44" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                	  #end                      
                      </div>
                      </td>
                	</tr>  
             </table>                 
				#end <!-- close utk flag = ROTS -->                  
               
     </div>
#end


#if ( $tab == "keputusan" )
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend>KEPUTUSAN</legend>
             <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
                <tr>
                  <td><font color="red">*</font>&nbsp;Tarikh Hantar Borang J</td>
                  <td>:</td>
                  <td><input name="txdTarikhHantarAdd" value="$!tarikh_hantar_borangj" size="11" id="txdTarikhHantarAdd" type="text"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
 				<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhHantarAdd',false,'dmy');" /></td>
                </tr>
                <tr>
                  <td><font color="red">*</font>&nbsp;Tarikh Terima Keputusan</td>
                  <td>:</td>
                  <td><input name="txdTarikhTerimaAdd" value="$!tarikh_terima_borangj" size="11" id="txdTarikhTerimaAdd" type="text"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                  <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhTerimaAdd',false,'dmy');" /></td>
                </tr>
                <tr>
                  <td valign="top">&nbsp;&nbsp;&nbsp;Keputusan</td>
                  <td valign="top">:</td>
                  <td><textarea name="txtKeputusanAdd" cols="80%" rows="8" id="txtKeputusanAdd">$!catatan4</textarea></td>
                </tr>
               	<tr>
               	  <td colspan="3" valign="top">&nbsp;</td>
           	   </tr>  
                <tr>
                  <td></td>
                  <td></td>
                  <td><div  class="disabled" id="word_count"></div></td>
                </tr> 
              
              <!-- EDIT MODE -->  
              <script> 
              	var area = new FCKeditor("txtKeputusanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script> 
                                                           
                <tr>
                  <td colspan="3" valign="top">&nbsp;</td>
                </tr>
                <tr>
                  <td width="31%" valign="top">&nbsp;&nbsp;&nbsp;Catatan</td>
                  <td width="1%" valign="top">:</td>
                  <td width="68%"><textarea name="txtCatatanAdd" cols="80%" rows="8" id="txtCatatanAdd">$!catatan5</textarea></td>
               </tr> 
               	<tr>
               	  <td colspan="3" valign="top">&nbsp;</td>
           	   </tr>
               <tr>
                  <td></td>
                  <td></td>
                  <td><div  class="disabled" id="word_count"></div></td>
               </tr> 
              
              <!-- EDIT MODE -->               
              <script> 
              	var area = new FCKeditor("txtCatatanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>                
               <!-- aishah tanda -->
               	<tr>
               	  <td valign="top">&nbsp;</td>
               	  <td valign="top">&nbsp;</td>
               	  <td valign="top">&nbsp;</td>
           	   </tr>                 
                <tr>
                  <td width="31%" valign="top">&nbsp;&nbsp;&nbsp;Muat Naik Fail</td>
                  <td width="1%" valign="top">:</td>
                  <td><input type="button" name="cmdUpload" id="cmdUpload" value="Muat Naik" onclick="OpenUploadFile('$idpermohonan','$idFail')" /></td>
               </tr>      
             </table>
             
         <p></p>
         <fieldset>
         <legend>SENARAI FAIL</legend>    	
            <table width="100%" border="0" cellpadding="1" cellspacing="2">
              <tr class="table_header">
                <td width="8%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
      			<td width="33%" style="text-transform:uppercase">Tajuk</td>
      			<td width="31%" style="text-transform:uppercase">Jenis</td>
                <td width="14%" style="text-transform:uppercase">Hapus</td>
              </tr>
              #set ($counter = 0)
              #foreach($lampiran in $SenaraiLampiran)
              #set ($counter = $counter + 1)
                #if ($lampiran.bil == '') 
                #set ($row = 'row1')
              #elseif ($lampiran.bil % 2 != 0)
                #set ($row = 'row1')
              #else 
                #set ($row = 'row2')
              #end 
              <tr>
                <td class="$row"><div align="center">$counter</div></td>
                <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_lampiran','$lampiran.id_dokumen')" class="style2">$lampiran.tajuk_dokumen</a></td>                          
                <td class="$row">$lampiran.jenis_mime</td>
                <td width="14%" class="$row">
                  <div align="center">                   
                   <a href="javascript: hapusFail('$lampiran.id_lampiran','$lampiran.id_dokumen');"><img src="../img/hapus.gif" alt="Hapus" border="0" width="18" height="18" /></a></div></td>
              </tr>
            #end
            </table>
          </fieldset>
             	   	
 </fieldset>	
            
            <p></p>
            <table width="100%">
            	<tr>
                	<td><div align="center">
                	  <input name="cmdSimpan" type="button" value="Simpan" onclick="javascript: edit_keputusan('$idpermohonan','$id_perbicaraan');" />                	  
                	  <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
              	  </div></td>
                </tr>
            </table>
            
        </div> 
#end        
             
  </div> 
  </div>  
#end

 <!---------------------------------- VIEWEDITMODE ------------------------------------->
 
#if ( $viewEditMode == "yes" )

#if ( $action == "onChange2" )
    #foreach($dataROTS in $infoPerintahTangguhROTS)
        #set ($id_borangj = $dataROTS.id_borangj)
        #set ($flag_jenis_keputusan = $dataROTS.flag_jenis_keputusan)
        #set ($id_unitpsk = $dataROTS.id_unitpsk)
        #set ($nama_pegawai = $dataROTS.nama_pegawai)
        #set ($jenis_keluar_perintah = $dataROTS.jenis_keluar_perintah)
        #set ($tarikh_perintah = $dataROTS.tarikh_perintah)
        #set ($id_mahkamah = $dataROTS.id_mahkamah)
        #set ($id_negerimahkamah = $dataROTS.id_negerimahkamah)
        #set ($nama_negeri = $dataROTS.nama_negeri)
        #set ($id_daerah = $dataROTS.id_daerah)
        #set ($nama_daerah = $dataROTS.nama_daerah)
        #set ($nama_pejabat = $dataROTS.nama_pejabat)
        #set ($alamat1 = $dataROTS.alamat1)
        #set ($alamat2 = $dataROTS.alamat2)
        #set ($alamat3 = $dataROTS.alamat3)
        #set ($poskod = $dataROTS.poskod)
        #set ($no_tel = $dataROTS.no_tel)
        #set ($no_fax = $dataROTS.no_fax)  
        #set ($catatan1 = $dataROTS.catatan1)  
        #set ($keputusan_mahkamah = $dataROTS.keputusan_mahkamah)        
        #set ($tarikh_hantar_borangj = $dataROTS.tarikh_hantar_borangj)  
        #set ($tarikh_terima_borangj = $dataROTS.tarikh_terima_borangj) 
        #set ($catatan2 = $dataROTS.catatan2)  
        #set ($catatan3 = $dataROTS.catatan3)  
        #set ($catatan4 = $dataROTS.catatan4)  
        #set ($catatan5 = $dataROTS.catatan5)  
        #set ($tarikh_mohon = $dataROTS.tarikh_mohon) 
    #end
#end

<input type="hidden" name="id_borangj" id="id_borangj" value="$id_borangj"/>

    <div id="TabbedPanels1" class="TabbedPanels" >
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">PERMOHONAN</li>
        <li class="TabbedPanelsTab" tabindex="0">KEPUTUSAN</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">     
        
        #foreach($data in $dataPerbicaraan)
            ##set ($id = $data.idPermohonan)
            #set ($idPemohon = $data.idPemohon)
            #set ($id_perbicaraan = $data.id_perbicaraan)
            #set ($tarikhBicara = $data.tarikh_bicara)
            #set ($idUnitpsk = $data.id_unitpsk)
            #set ($namaPegawai = $data.nama_pegawai)
        #end
        <p></p>
         <fieldset>
          <legend>PERMOHONAN RUJUKAN <em>RULER OF THE STATE</em> ATAU MAHKAMAH TINGGI</legend>         
<table width="100%"  cellspacing="1" cellpadding="1" border="0">           
               <tr>
                 <td width="30%"><font color="red">*</font>&nbsp;Rujukan Kepada </td>
                 <td width="1%">:</td>
                 <td width="69%"><input name="jenis_rujukan" type="radio" value="1" disabled $TEMPcheckedMahkamahTinggi />
                 Mahkamah Tinggi</td>
               </tr>
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td><input name="jenis_rujukan" type="radio" value="2" disabled $TEMPcheckedROTS />
                 <em>Ruler Of The State</em></td>
               </tr>
         </table>    
               
               
         #if ( $flag == "MT" )
             
		<table width="100%"  cellspacing="1" cellpadding="1" border="0">   
               <tr>
                 <td width="30%">Tarikh Rujukan</td>
                 <td width="1%">:</td>
                 <td colspan="3"><input name="txdTarikhPerintahAdd" value="$!tarikh_mohon" size="11" id="txdTarikhPerintahAdd" 
                 type="text" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td>Negeri </td>
                 <td>:</td>
                 <td colspan="5">$selectNegeri
                 <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" readonly class="disabled" /></td>
               </tr>
               <tr>
                 <td>Mahkamah Tinggi </td>
                 <td>:</td>
                 <td colspan="5">$selectBicara
                 <input type="hidden" name="id_pejabat" id="id_pejabat" value="$id_pejabat" /></td>
               </tr>
               <tr>
                 <td>Alamat</td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtAlamat1" maxlength="50" size="50" value="$!alamat1" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td></td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtAlamat2" maxlength="50" size="50" value="$!alamat2" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td></td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtAlamat3" maxlength="50" size="50" value="$!alamat3" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td>Poskod</td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtPoskod" maxlength="5" size="5" value="$!poskod" class="disabled" readonly /></td>
               </tr>
                           
               <tr>
                 <td>No Telefon</td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtTelefon" maxlength="50" size="15" value="$!no_tel" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td>No Fax</td>
                 <td>:</td>
                 <td colspan="5"><input type="text" name="txtfax" maxlength="50" size="15" value="$!no_fax" class="disabled" readonly /></td>
               </tr>
               <tr>
                 <td valign="top">Pegawai Pengendali</td>
                 <td valign="top">:</td>
                 <td colspan="5" valign="top">$selectPegawai</td>
               </tr>
               <tr>
                 <td valign="top">Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td colspan="5" valign="top"><textarea name="txtFaktaGuamanAdd" id="txtFaktaGuamanAdd" cols="80%" rows="8">$!catatan1</textarea></td>
               </tr>

              <!-- VIEWEDITMODE -->
			  <script> 
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>                 
               
               <tr>
                 <td valign="top">Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td colspan="5" valign="top"><textarea name="txtPendapatAdd" id="txtPendapatAdd" cols="80%" rows="8">$!keputusan_mahkamah</textarea></td>
               </tr>
         </table>
       
<table border="0" width="100%">
        		
        		<tr>
        		  <td>&nbsp;</td>
                  
              <!-- VIEWEDITMODE -->
			  <script> 
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>  
                 
      		  </tr>
        		<tr>
         			<td><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="$senarai.id_ob">
        			</label></td>
        		</tr>	
   	    </table>        
 </fieldset>        
            
<fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
              <p></p>
                 <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                #if($SenaraiWaris!=0)
                    #foreach ( $senarai in $SenaraiWaris )
                    
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                    
                    <tr>
                        <td class="$row">$senarai.bil</td>
                        <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                        <td class="$row">$senarai.no_kp_baru</td>
                        <td class="$row">$senarai.keterangan</td>
                   </tr>
                    #end
                #else   	
                    <tr>
                        <td colspan="4">Tiada Rekod</td>
                    </tr>
                #end                    
                </table>
                 <p></p>
              <table width="100%">
              	<tr>
                	<td>
                      <div align="center">
                        <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="javascript: skrin_editROTS('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />                        
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />
                  </div></td>
                </tr>
              </table>
              #end <!-- END FOR FLAG = MT -->
                
             
        
              
              
            #if ( $flag == "ROTS" ) 
            <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
            
            #if ( $jenispejabat == "syariah" )
            <tr>
              <td width="30%">&nbsp;</td>
              <td width="1%">&nbsp;</td>
              <td width="2%">&nbsp;</td>
              <td width="23%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="1" disabled $tempcheckedms />
Mahkamah Syariah</td>
              <td width="44%"><input name="flag_rujukan" id="flag_rujukan" type="radio" value="2" disabled $tempcheckedpejmufti />
Pejabat Mufti</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectNegeri
              <input type="hidden" name="txtidnegeri" id="txtidnegeri" value="$!txtidnegeri" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Mahkamah Syariah </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectBicara
              <input type="hidden" name="id_pejabat" id="id_pejabat" value="$id_mahkamah" /></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Alamat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="$!alamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>           
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" value="$!alamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" value="$!alamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" readonly class="disabled"></td>
            </tr>
            
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!notel" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!nofax" readonly class="disabled"></td>
            </tr>          
            <tr>

               <td><font color="red">*</font> Pegawai Pengendali</td>
              <td>:</td>
               <td colspan="3">&nbsp;$selectPegawai</td>
             </tr>
             <tr>
               <td valign="top">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
               <td valign="top">:</td>
               <td colspan="3" valign="top"><textarea name="txtFaktaGuamanAdd" cols="80%" rows="8" id="txtFaktaGuamanAdd">$!catatan1</textarea></td>
             </tr>           
              <tr>
                <td colspan="5" valign="top">&nbsp;</td>
              </tr>
              
              <!-- VIEWEDITMODE -->
              <script> 			  
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>                
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="3" valign="top">&nbsp;</td>
              </tr>
             <tr>
                <td valign="top">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                <td valign="top">:</td>
                <td colspan="3" valign="top"><textarea name="txtPendapatAdd" cols="80%" rows="8" id="txtPendapatAdd" >$!keputusan_mahkamah</textarea></td>
             </tr>             
              <tr>
                <td colspan="5" valign="top">&nbsp;</td>
              </tr>
              
              <!-- VIEWEDITMODE -->
              <script> 			  
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>   
                          
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="3" valign="top">&nbsp;</td>
              </tr>       
             </table>
        
         
#end <!-- close mahkamah syariah -->  
         
         

       #if ( $jenispejabat == "pejmufti" )
       <table width="100%">
			
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td width="3%">&nbsp;</td>
              <td width="24%"><input name="flag_rujukan" id="flag_rujukan3" type="radio" value="1" disabled="disabled" $tempcheckedms />
Mahkamah Syariah</td>
              <td width="42%"><input name="flag_rujukan" id="flag_rujukan4" type="radio" value="2" disabled="disabled" $tempcheckedpejmufti />
Pejabat Mufti</td>
         </tr>
            <tr>
               <td width="30%"><font color="red">*</font>&nbsp;Tarikh Rujukan</td>
               <td width="1%">:</td>
		       <td colspan="3">&nbsp;<input name="txdTarikhRujukanAdd" value="$!tarikh_mohon" maxlength="10" size="11" id="txdTarikhRujukanAdd" type="text" 
				onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" readonly class="disabled" /></td>
            </tr>         
            <tr>
              <td><font color="red">*</font>&nbsp;Negeri </td>
              <td>:</td>
              <td colspan="3">&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Nama Pejabat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" value="$!nama_pejabat" maxlength="100" size="50" style="text-transform:uppercase;" 
              onblur="this.value=this.value.toUpperCase();" readonly class="disabled" /></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Alamat</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="$!alamat1" maxlength="80" size="50" style="text-transform:uppercase;" 
              onBlur="this.value=this.value.toUpperCase();" readonly class="disabled"></td>
            </tr>
            <tr>            
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" value="$!alamat2" maxlength="80" size="50" style="text-transform:uppercase;" 
              onBlur="this.value=this.value.toUpperCase();" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" value="$!alamat3" maxlength="80" size="50" style="text-transform:uppercase;" 
              onBlur="this.value=this.value.toUpperCase();" readonly class="disabled" ></td>
            </tr>
            <tr>
              <td><font color="red">*</font>&nbsp;Poskod</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" value="$!poskod" maxlength="5" size="5" 
              readonly class="disabled" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>           
            <tr>
              <td>&nbsp;&nbsp;&nbsp;Bandar</td>
              <td>&nbsp;</td>
              <td colspan="3">&nbsp;$selectBandar</td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Telefon</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" value="$!no_tel" readonly class="disabled" 
              onkeyup="validateNumber(this,this.value);" ></td>
            </tr>
            <tr>
              <td>&nbsp;&nbsp;&nbsp;No Fax</td>
              <td>:</td>
              <td colspan="3">&nbsp;<input type="text" name="txtfax" id="txtfax" value="$!no_fax" readonly class="disabled" onkeyup="validateNumber(this,this.value);" /></td>
            </tr>  
               <tr>
                 <td valign="top"><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top">&nbsp;$selectPegawai</td>
               </tr>
               <tr>
                 <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Fakta Guaman</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtFaktaGuamanAdd" cols="80%" rows="8" id="txtFaktaGuamanAdd">$!catatan1</textarea></td>
               </tr>             
               <tr>
                 <td colspan="5" valign="top">&nbsp;</td>
         </tr>
         	  <!-- VIEWEDITMODE -->
              <script> 			  
              	var area = new FCKeditor("txtFaktaGuamanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtFaktaGuamanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>               
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top" width="30%">&nbsp;&nbsp;&nbsp;Pendapat / Keputusan / Arahan</td>
                 <td valign="top">:</td>
                 <td colspan="3" valign="top"><textarea name="txtPendapatAdd" cols="80%" rows="8" id="txtPendapatAdd">$!keputusan_mahkamah</textarea></td>
               </tr>
               <tr>
                 <td colspan="5" valign="top">&nbsp;</td>
               </tr>
               <tr>
                 <td valign="top">&nbsp;</td>
                 <td valign="top">&nbsp;</td>
                 <td colspan="3" valign="top">&nbsp;</td>
               </tr>
               
               <!--VIEWEDITMODE -->
               <script> 			  
              	var area = new FCKeditor("txtPendapatAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtPendapatAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>           
         </table>     
        </fieldset>   
         #end	<!-- close for $jenispejabat == "pejmufti" -->         
         

         
         
			<table border="0" width="100%">
        		<tr>
         			<td colspan="3"><label id="divCheckbox"  style="visibility: hidden;">
					<input type="checkbox" name="cbsemaks" value="">
        			</label></td>
        		</tr>	
        	</table>
            
              <fieldset>
              <legend><strong>SENARAI WARIS</strong></legend>
                   <p></p>
          <table width="100%" border="0" cellspacing="2">
                    <tr class="table_header">
                      <!--<td width="3%" style="text-transform:uppercase"></td>-->
                      <td width="6%" style="text-transform:uppercase"><div align="center">No.</div></td>
                      <td width="31%" style="text-transform:uppercase">Nama</td>
                      <td width="33%" style="text-transform:uppercase">No KP Baru</td>
                      <td width="27%" style="text-transform:uppercase">Talian dengan SiMati</td>
                   </tr> 
                    #foreach ( $senarai in $SenaraiWaris )
                	
                    #if ($senarai.bil == '')
                    #set ($row = 'row1')
                    
                    #elseif ($senarai.bil % 2 != 0)
                            #set ($row = 'row1')
                        
                    #else 
                        #set ($row = 'row2')
                    #end
                    
                <tr>
                    <!--<td class="$row"><input type="checkbox" name="cbsemaks" value="$senarai.id_ob"></td>-->
                    <td class="$row"><div align="center">$senarai.bil</div></td>
                    <td class="$row">$senarai.nama_ob.toUpperCase()</td>
                    <td class="$row">$senarai.no_kp_baru</td>
                    <td class="$row">$senarai.keterangan</td>
               	</tr>
                    #end                  
              </table>           
              
              <table width="100%">
                    <tr>
                      <td>&nbsp; </td>
                    </tr>
                    <tr>
                      <td>
                        <div align="center">
                        #if ( $jenispejabat == "syariah" )
                        <input name="cmdINTJKSM" type="button" value="Semakan JKSM" onclick="javascript:checkJKSM('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="javascript:skrin_kemaskiniSyariah('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdHapus" type="button" value="Hapus" onclick="javascript:HapusSyariah('$idpermohonan','$id_perbicaraan','$id_borangj');" />
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />
                        #end
                        #if ( $jenispejabat == "pejmufti" )
                        <input name="cmdKemaskini" type="button" value="Kemaskini" onclick="javascript:skrin_kemaskiniMufti ('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdHapus" type="button" value="Hapus" onclick="javascript:HapusMufti('$idpermohonan','$id_perbicaraan','$id_borangj');" />
                        <input type="button" name="button" id="button" value="Cetak" onclick="javascript:setTable('tableReport1')" />
                        #end
                        
                        #if ( $idstatus == "18" )
                        <input name="cmdKembali" type="button" value="Kembali" 
                        onclick="javascript: kembali_skrinROTSadd('$idpermohonan','$id_perbicaraan');" />
                        #end   
                                             
                        #if ( $idstatus == "44" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" )
                        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />
                        <input name="cmdSeterusnya" type="button" value="Seterusnya" onclick="javascript:goNotis('$idpermohonan','$id_status')" />
                        #end                      
                      </div>
                      </td>
                	</tr>            
				
               </table>  
            #end <!-- close utk flag = ROTS -->   
         </fieldset>  
     </div>
 

        #if ($tab == "keputusan")
        
#foreach($dataKeputusan in $infoPermohonanROTSkeputusan)
    #set ($tarikh_hantar_borangj = $dataKeputusan.tarikh_hantar_borangj)
    #set ($tarikh_terima_borangj = $dataKeputusan.tarikh_terima_borangj)
    #set ($catatan2 = $dataKeputusan.catatan2)
    #set ($catatan3 = $dataKeputusan.catatan3)
    #set ($catatan4 = $dataKeputusan.catatan4)
    #set ($catatan5 = $dataKeputusan.catatan5)
#end

        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend>KEPUTUSAN</legend>
             <table width="100%"  cellspacing="1" cellpadding="1" border="0"> 
                <tr>
                  <td width="31%">Tarikh Hantar Borang J</td>
                  <td width="1%">:</td>
                  <td width="68%"><input name="txdTarikhHantarAdd" value="$!tarikh_hantar_borangj" size="11" id="txdTarikhHantarAdd" type="text" readonly class="disabled" /></td>
                </tr>
                <tr>
                  <td>Tarikh Terima Keputusan</td>
                  <td>:</td>
                  <td><input name="txdTarikhTerimaAdd" value="$!tarikh_terima_borangj" size="11" id="txdTarikhTerimaAdd" type="text" readonly class="disabled" /></td>
                </tr>
                <tr>
                  <td valign="top">Keputusan</td>
                  <td valign="top">:</td>
                  <td><textarea name="txtKeputusanAdd" id="txtKeputusanAdd" cols="80%" rows="8" >$!catatan4</textarea></td>
                </tr>
                <tr>
                  <td colspan="3" valign="top">&nbsp;</td>
               </tr>
              
              <!-- VIEWEDITMODE -->  
              <script> 			  
              	var area = new FCKeditor("txtKeputusanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtKeputusanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>  
                            
                <tr>
                  <td valign="top">Catatan</td>
                  <td valign="top">:</td>
                  <td><textarea name="txtCatatanAdd" id="txtCatatanAdd" cols="80%" rows="8" >$!catatan5</textarea></td>
                </tr>
                <tr>
                  <td colspan="3" valign="top">&nbsp;</td>
              
              <!-- VIEWEDITMODE -->    
              <script> 			  
              	var area = new FCKeditor("txtCatatanAdd");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtCatatanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
				}				
			  </script>
               </tr>       
             </table>	   	
            
          <p></p>
         <fieldset>
         <legend>SENARAI FAIL</legend>    	
            <table width="100%" border="0" cellpadding="1" cellspacing="2">
              <tr class="table_header">
                <td width="8%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
      			<td width="33%" style="text-transform:uppercase">Nama Fail</td>
      			<td width="31%" style="text-transform:uppercase">Tajuk Fail</td>
              </tr>
              #set ($counter = 0)
              #foreach($lampiran in $SenaraiLampiran)
              #set ($counter = $counter + 1)
                #if ($lampiran.bil == '') 
                #set ($row = 'row1')
              #elseif ($lampiran.bil % 2 != 0)
                #set ($row = 'row1')
              #else 
                #set ($row = 'row2')
              #end 
              <tr>
                <td class="$row"><div align="center">$counter</div></td>
                #if ($lampiran.bil != '') 
                <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_lampiran','$lampiran.id_dokumen')" class="style2">
                $lampiran.tajuk_dokumen</a></td>
                #else
                <td class="$row">$lampiran.nama_fail</td>
                #end
                
                <td class="$row">$lampiran.tajuk_dokumen</td>
              </tr>
            #end
            </table>
             </fieldset>        
 #end <!-- close tab = keputusan -->           
            
            <p></p>
            <table width="100%">
            	<tr>
                	<td><div align="center">
                	  <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="edit_keputusanROTS('$idpermohonan','$id_perbicaraan');" />                      
                	  <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_skrinROTSedit('$idpermohonan','$id_perbicaraan');" />  
                      
                      #if ( $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )   
                      <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$id_status')" /> 					  #end
                                       
              	  </div></td>
                </tr>
            </table>
         </div> 
 </fieldset>	            
            	
  </div> 
  </div>  
#end


<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangJ('$nofail','$idFail','$id_perbicaraan')"><font color="blue"> Borang J </font></a></td>
      </tr>           
    </table>
</fieldset> 
 
 <input type=hidden name="idLampiran" />
 <input type=hidden name="idDokumen" />

#parse("app/ppk/headerppk_script.jsp") 
<script>
function goNotis(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function doChangeidNegeriPejabatMuftiUpdate() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan"; 
	document.${formName}.command.value = "doChangeidNegeriPejabatMuftiUpdate";
	document.${formName}.submit();
}
function doChangeidNegeriPejabatMufti() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidNegeriPejabatMufti";
	document.${formName}.submit();
}
function openMT(){	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "openMT";
	document.${formName}.submit();
}
function openROTS(){		
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "openROTS";
	document.${formName}.submit();
}

function openFlagRujukan(){	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "openFlagRujukan";
	document.${formName}.submit();
}

function openPejMufti(){	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "openPejMufti";
	document.${formName}.submit();
}

function hapusROTS(idpermohonan,id_perbicaraan,id_borangj){
	if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;	
		document.${formName}.id_borangj.value = id_borangj;	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "hapusROTS";
		document.${formName}.submit();
}
function HapusSyariah(idpermohonan,id_perbicaraan,id_borangj){
	if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;	
		document.${formName}.id_borangj.value = id_borangj;	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "HapusSyariah";
		document.${formName}.submit();
}
function HapusMufti(idpermohonan,id_perbicaraan,id_borangj){
	if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;	
		document.${formName}.id_borangj.value = id_borangj;	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "HapusMufti";
		document.${formName}.submit();
}
function hapusFail(idLampiran,idDokumen){
	if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.idLampiran.value = idLampiran;
		document.${formName}.idDokumen.value = idDokumen;	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "hapusFail";
		document.${formName}.submit();
}

function hapusIDob(){
if ( !window.confirm("Anda Pasti?") ) return;	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "hapusIDob";
	document.${formName}.submit();
}

function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.ppk.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}

function OpenUploadFile(idpermohonan,idFail) {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmUploadFail?idpermohonan="+idpermohonan+"&idFail="+idFail;
	var hWnd = window.open(url,'displayfile','width=600,height=600, resizable=yes,scrollbars=yes');	
}

function setTable(nofail){
	if(document.getElementById(nofail).style.display=="none"){
		document.getElementById(nofail).style.display="block";
	}
	else if(document.getElementById(nofail).style.display=="block"){
		document.getElementById(nofail).style.display="none";
	}
}

function cetakBorangJ(nofail,idFail,id_perbicaraan) {
	var url = "../servlet/ekptg.report.ppk.BorangJ?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function kembali_skrinROTSadd(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "kembali_skrinROTSadd";
	document.${formName}.submit();
}

function kembali_skrinROTSedit(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "kembali_skrinROTSedit";
	document.${formName}.submit();
}

function skrin_editROTS(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "skrin_editROTS";
	document.${formName}.submit();
}

function edit_keputusanROTS(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "edit_keputusanROTS";
	document.${formName}.submit();
}

function simpanEditMufti(idpermohonan,id_perbicaraan){

	var str1  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("tarikhMohon").value;
	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);	
	var trMOHON = new Date(yr2, mon2, dt2);	
	
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.txdTarikhRujukanAdd.value == 0){
		alert("Sila masukkan \"Tarikh Rujukan\" terlebih dahulu.");
  		document.${formName}.txdTarikhRujukanAdd.focus(); 
		return;
	}
	if(document.${formName}.socNegeri.value == 0){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}	
	if(document.${formName}.txtnamapej.value == 0){
		alert("Sila pilih \"Nama Pejabat\" terlebih dahulu.");
  		document.${formName}.txtnamapej.focus(); 
		return;
	}	
	if(document.${formName}.txtAlamat1.value == 0){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == 0){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.cbsemaks.length;  i++){
	if (${formName}.cbsemaks[i].checked)
	radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Waris\" terlebih dahulu.");
		return (false); 
	}					
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}		
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "simpanEditMufti";
	document.${formName}.submit();
	}
}

function simpanSyariah(idpermohonan,id_perbicaraan){

	var str1  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("tarikhMohon").value;
	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);	
	var trMOHON = new Date(yr2, mon2, dt2);	

	var radioSelected = false;	
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.socTempatBicara.value == 0){
		alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == 0){
		alert("Sila pilih \"Nama Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}	
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}	
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "simpanSyariah";
	document.${formName}.submit();
	}
}

function getSimpanMahkamahROTS(idpermohonan,id_perbicaraan){

	var txtFaktaGuamanAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;
	if(oEditor) { 
		txtFaktaGuamanAdd = oEditor.GetXHTML(true); 
	} 

	var txtPendapatAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;
	if(oEditor) { 
		txtPendapatAdd = oEditor.GetXHTML(true); 
	} 
	
	var str1  = document.getElementById("tarikhMohon").value;
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);	
	var trMOHON = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10);
    var yr2   = parseInt(str2.substring(6,10),10);   
    var date2 = new Date(yr2, mon2, dt2);		

	var radioSelected = false;	
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.socTempatBicara.value == 0){
		alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == 0){
		alert("Sila pilih \"Nama Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	if(date2 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}			
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "getSimpanMahkamahROTS";
	document.${formName}.submit();
	}
}

function getSimpanMufti(idpermohonan,id_perbicaraan){ // guna untuk add mode pejabat mufti nnt

	var str1  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("tarikhMohon").value;
	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);	
	var trMOHON = new Date(yr2, mon2, dt2);	
			
	var txtFaktaGuamanAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtFaktaGuamanAdd = oEditor.GetXHTML(true); 		
	}
	
	var txtPendapatAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtPendapatAdd = oEditor.GetXHTML(true); 		
	}
	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.socNegeri.value == 0){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	if(document.${formName}.socPegawaiPengendali.value == 0){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawaiPengendali.focus(); 
		return;
	}
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}	
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "getSimpanMufti";
	document.${formName}.submit();
	}
}

function skrin_kemaskiniSyariah(idpermohonan,id_perbicaraan){
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "skrin_kemaskiniSyariah";
	document.${formName}.submit();
}

function skrin_kemaskiniMufti (idpermohonan,id_perbicaraan){
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "skrin_kemaskiniMufti";
	document.${formName}.submit();
}

function getSimpanMahkamahSyariahROTS(idpermohonan,id_perbicaraan){
	
	var txtFaktaGuamanAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtFaktaGuamanAdd = oEditor.GetXHTML(true); 		
	}	
	var txtPendapatAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtPendapatAdd = oEditor.GetXHTML(true); 		
	}	
	
	var str1  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("tarikhMohon").value;
	var dt2   = parseInt(str2.substring(0,2),10);
   	var mon2  = parseInt(str2.substring(3,5),10);
   	var yr2   = parseInt(str2.substring(6,10),10);	
	var trMOHON = new Date(yr2, mon2, dt2);	
		
	var radioSelected = false;
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.socTempatBicara.value == 0){
		alert("Sila pilih \"Mahkamah\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == 0){
		alert("Sila pilih \"Nama Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}	
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}		
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "getSimpanMahkamahSyariahROTS";
	document.${formName}.submit();
	}
}

function doChangeidNegeriMSupdate() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidNegeriMSupdate";
	document.${formName}.submit();	
}
function doChangeidMahkamahMSupdate() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidMahkamahMSupdate";
	document.${formName}.submit();	
}

function doChangeidNegeriMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidNegeriMS";
	document.${formName}.submit();	
}
function doChangeidMahkamahMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidMahkamahMS";
	document.${formName}.submit();	
}

function doChangeidNegeriROTS() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan"; 
	document.${formName}.command.value = "doChangeidNegeriROTS";
	document.${formName}.submit();	
}
function doChangeidNegeriROTSupdate() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidNegeriROTSupdate";
	document.${formName}.submit();	
}

function doChangeidMahkamahROTS() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidMahkamahROTS";
	document.${formName}.submit();	
}

function doChangeidMahkamahROTSupdate() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "doChangeidMahkamahROTSupdate";
	document.${formName}.submit();	
}

function edit_keputusan(idpermohonan,id_perbicaraan){
	if(document.${formName}.txdTarikhHantarAdd.value == 0){
		alert("Sila masukkan \"Tarikh Hantar BorangJ\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantarAdd.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhTerimaAdd.value == 0){
		alert("Sila masukkan \"Tarikh Terima BorangJ\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaAdd.focus(); 
		return;
	}
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan"; 
	document.${formName}.command.value = "edit_keputusan";
	document.${formName}.submit();	
	}
}

function simpan_editROTS(idpermohonan,id_perbicaraan,id_perintah){	

	var str1  = document.getElementById("tarikhMohon").value;
	var dt1   = parseInt(str1.substring(0,2),10);
   	var mon1  = parseInt(str1.substring(3,5),10);
   	var yr1   = parseInt(str1.substring(6,10),10);	
	var trMOHON = new Date(yr1, mon1, dt1);	
	
	var str2  = document.getElementById("txdTarikhRujukanAdd").value;   
    var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10);
    var yr2   = parseInt(str2.substring(6,10),10);   
    var date2 = new Date(yr2, mon2, dt2);
	
	var radioSelected = false;		
	for (i = 0;  i < ${formName}.jenis_rujukan.length;  i++){
		if (${formName}.jenis_rujukan[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Rujukan kepada\" terlebih dahulu.");
		return (false);
	}
	if(document.${formName}.socNegeri.value == 0){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	if(document.${formName}.socPegawai.value == 0){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	if(date2 < trMOHON){
   		alert("Sila pastikan Tarikh Rujukan tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhRujukanAdd.focus();
	 	return;	
	}	
	else{
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan"; 
	document.${formName}.command.value = "simpan_editROTS";
	document.${formName}.submit();	
	}
}

function TambahMufti(idpermohonan,id_perbicaraan) {
	var url = "../x/${securityToken}/ekptg.view.ppk.PopupTambahMufti?idpermohonan="+idpermohonan+"&id_perbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function TambahMuftiIDP(idpermohonan,id_perbicaraan,id_perintah) {
	var url = "../x/${securityToken}/ekptg.view.ppk.PopupTambahMufti?idpermohonan="+idpermohonan+"&id_perbicaraan="+id_perbicaraan+"&id_perintah="+id_perintah;
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function pilihWaris(id_perbicaraan) {
	var url = "../x/${securityToken}/ekptg.view.ppk.PopupTambahWaris?id_perbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function refreshHA() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";	
	document.${formName}.command.value = "skrin_editROTS";
	document.${formName}.submit();
}

function refreshUploadPage() {
alert("Dokumen Telah Berjaya Di Muat Naik.");
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "refreshTambahMufti";
	document.${formName}.submit();
}

function refreshTambahMufti() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "refreshTambahMufti";
	document.${formName}.submit();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString ){
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

//<!-- Begin
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
//// End -->


</script>

<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>


<script>

function checkJKSM(ID_PERMOHONAN, ID_PERBICARAAN) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=viewBorangJ&ID_PERMOHONAN=" + ID_PERMOHONAN;
	document.${formName}.submit();
}

</script>