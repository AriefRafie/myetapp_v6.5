<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>


#set($txtSebabPembatalan = "")
#set($sorJenisPembatalan = "")
#set($txtNoRujSurat = "")
#set($txdTarikhTerimaSurat = "")
#set($txdTarikhSurat = "")
#set($txdTarikhPembatalan = "")
#set($txtNoPembatalan = "")
#set($id_pembatalan = "")

#foreach ( $mp in $maklumat_pembatalan )
#set($txtSebabPembatalan = $mp.SEBAB_PEMBATALAN)

#if($mp.JENIS_PEMBATALAN == 1)
#set($sorJenisPembatalan = "sebahagian")
#elseif($mp.JENIS_PEMBATALAN == 2)
#set($sorJenisPembatalan = "keseluruhan")
#else
#set($sorJenisPembatalan = "")
#end

#set($txtNoRujSurat = $mp.NO_RUJUKAN_SURAT)
#set($txdTarikhTerimaSurat = $mp.TARIKH_TERIMA_SURAT)
#set($txdTarikhSurat = $mp.TARIKH_SURAT)
#set($txdTarikhPembatalan = $mp.TARIKH_PEMBATALAN)
#set($txtNoPembatalan = $mp.NO_PEMBATALAN)
#set($id_pembatalan = $mp.ID_PEMBATALAN)
#end

#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end



<table width="100%">
  <tr>
    <td>#parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  <tr>
    <td><fieldset id="maklumat_tambahan">
    <legend>MAKLUMAT TAMBAHAN</legend>
 
    
    <table width="100%">
  #if($jenis_permohonan == "3")
 
  <tr>
    <td width="100%" valign="top"><table width="100%">
    #if($id_pembatalan != "")
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">No. Tarik Balik</td>
        <td width="1%">:</td>
        <td width="70%">
          <label>
         $txtNoPembatalan
          <input name="txtNoPembatalan" type="hidden" id="txtNoPembatalan" size="50" maxlength="50" value="$txtNoPembatalan" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" readonly class = "disabled"   >
          </label>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Kemasukan</td>
        <td>:</td>
        <td>
      $txdTarikhPembatalan
        <input name="txdTarikhPembatalan" type="hidden" id="txdTarikhPembatalan" size="10" maxlength="10"  value="$txdTarikhPembatalan" onBlur="validateTarikh(this,this.value);check_date(this)" onKeyUp="validateTarikh(this,this.value);" readonly class = "disabled" />
    <!--
      #if($readmode == "edit")
      <a href="javascript:displayDatePicker('txdTarikhPembatalan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/> 
      #end
      -->      </td>
      </tr>
      #else
       <input name="txtNoPembatalan" type="hidden" id="txtNoPembatalan" size="50" maxlength="50" value="$txtNoPembatalan" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" $readonlymode class = "$disabledmode" >
       <input name="txdTarikhPembatalan" type="hidden" id="txdTarikhPembatalan" size="10" maxlength="10"  value="$txdTarikhPembatalan" onBlur="validateTarikh(this,this.value);check_date(this)" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #end
      <tr>
        <td width="1%" >#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Tarikh Surat</td>
        <td width="1%">:</td>
        <td width="70%">
        <input name="txdTarikhSurat" type="text" id="txdTarikhSurat" size="10" maxlength="10"   value="$txdTarikhSurat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhSurat_check','yes','surat penarikan balik','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")  
      <a href="javascript:displayDatePicker('txdTarikhSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
       <span id="txdTarikhSurat_check" style="color:red" ></span>      </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>Tarikh Terima Surat</td>
        <td>:</td>
        <td>
        <input name="txdTarikhTerimaSurat" type="text" id="txdTarikhTerimaSurat" size="10" maxlength="10" value="$txdTarikhTerimaSurat" onBlur="validateTarikh(this,this.value);checking_validation(this,'txdTarikhTerimaSurat_check','yes','terima surat','tarikh')" onKeyUp="validateTarikh(this,this.value);" $readonlymode class = "$disabledmode" />
      #if($readmode == "edit")
      <a href="javascript:displayDatePicker('txdTarikhTerimaSurat',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
       <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      #end
      <span id="txdTarikhTerimaSurat_check" style="color:red" ></span>      </td>
      </tr>
      <tr>
        <td>#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td>No. Ruj Surat Tarik Balik</td>
        <td>:</td>
        <td><input name="txtNoRujSurat" type="text" id="txtNoRujSurat" size="50" maxlength="50" value="$txtNoRujSurat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat','normal')" $readonlymode class = "$disabledmode" 
        onkeyup="this.value=this.value.toUpperCase();checking_validation(this,'txtNoRujSurat_check','yes','no rujukan surat','normal')" >
        <div id="txtNoRujSurat_check" style="color:red" ></div>        </td>
      </tr>
      <tr>
        <td width="1%">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td width="28%">Pilihan Penarikan Balik</td>
        <td width="1%">:</td>
        <td width="70%">
          <label></label>
        #if($readmode == "view" )
        
        
        
        
        
              #if($sorJenisPembatalan == "sebahagian")
              #set($JenisPembatalan = "SEBAHAGIAN")
              #set($check1 = "checked")
              #set($check2 = "")
              #elseif($sorJenisPembatalan == "keseluruhan")
              #set($JenisPembatalan = "KESELURUHAN")
              #set($check1 = "")
              #set($check2 = "checked")
              #else
              #set($JenisPembatalan = "")
              #set($check1 = "")
              #set($check2 = "")
              #end
               <input name="JenisPembatalan" type="text" id="JenisPembatalan" value="$JenisPembatalan" $check1  readonly class = "disabled" onClick="doCheckAll_P()">
             
                <label id="divCheckbox"  style="visibility: hidden;">
                <input name="sorJenisPembatalan" type="radio" id="sorJenisPembatalan" value="sebahagian" $check1 $disabledmode  onclick="doCheckAll_P()">
                <input type="radio" name="sorJenisPembatalan" id="sorJenisPembatalan" value="keseluruhan" $check2 $disabledmode onClick="doCheckAll_P()">
                </label>
             
        
        #end
        
        #if($readmode == "edit" )
        
        
          <table width="100%">
            <tr>
              <td width="30%">
          
              #if($sorJenisPembatalan == "sebahagian")
              #set($check1 = "checked")
              #set($check2 = "")
              #elseif($sorJenisPembatalan == "keseluruhan")
              #set($check2 = "checked")
              #set($check1 = "")
              #else
              #set($check1 = "")
              #set($check2 = "")
              #end
             
              <label>                         
              <input name="sorJenisPembatalanD" type="radio" id="sorJenisPembatalanD" value="sebahagian" $check1 disabled  onclick="return false;" >
              Sebahagian     </label>
             
               <label id="divCheckbox" style="visibility: hidden;" >
              <input name="sorJenisPembatalan" type="radio" id="sorJenisPembatalan" value="sebahagian" $check1 $disabledmode   onclick="return false;" >
              </label>              </td>
              <td width="70%"><label>
     <input type="radio" name="sorJenisPembatalanD" id="sorJenisPembatalanD" value="keseluruhan" $check2 disabled onClick="return false;" >
              Keseluruhan</label>
               <label id="divCheckbox"  style="visibility: hidden;" >
              <input type="radio" name="sorJenisPembatalan" id="sorJenisPembatalan" value="keseluruhan" $check2 $disabledmode onClick="return false;" >
              </label>              </td>
            </tr>
          </table>
           #end
          <div id="sorJenisPembatalan_check" style="color:red;width:60%"  ></div>          </td>
      </tr>
      <tr>
        <td valign="top">#parse("app/ppt/mandatory_pembatalan.jsp")</td>
        <td valign="top">Alasan</td>
        <td valign="top">:</td>
        <td>
         <textarea name="txtSebabPembatalan" id="txtSebabPembatalan" cols="40"  rows="5" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();checking_validation(this,'txtSebabPembatalan_check','yes','alasan penarikan balik','normal')"  onKeyUp="checking_validation(this,'txtSebabPembatalan_check','yes','alasan penarikan balik','normal')" $readonlymode class = "$disabledmode" >$txtSebabPembatalan</textarea>     
         <div id="txtSebabPembatalan_check" style="color:red" ></div>    
        
                </td>
      </tr>
      
      
    </table></td>
  
  </tr>
  #end
  #if($jenis_permohonan == "1")
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">
    <div align="center" >
    #if($readmode == "view")
      <label>
      <input type="button" name="cmdKemaskini " id="cmdKemaskini " value="Kemaskini" onClick="kemaskini()">
      </label>
      <label>
      <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="hapus()">
      </label>
    #end
    #if($readmode == "edit")  
      <label>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan"  onClick="simpan()">
      </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()">
      </label>
    #end  
      <label>      
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali_senarai_depan()" >
      </label>
     </div>      </td>
  </tr>
</table>

    </fieldset>    </td>
  </tr>
  
   #if($maklumat_pembatalan.size() > 0)
  
  <tr>
    <td><fieldset id="senarai_dokumen">
   
    <legend>SENARAI DOKUMEN YANG DISERTAKAN</legend>
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" >
     #if($listDokumen_size > 0)
    <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()">
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="30%">Nama Dokumen</td>
    <td width="30%">Keterangan</td>
    <td width="30%">Dokumen Sokongan</td>
     #if($listDokumen_size > 0)
      <td width="5%">
    
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onClick="doCheckAll1()" title="Semak untuk pilih semua" />
      </div>
      
      
      </td>
      #end
  </tr>
  
    #if($listDokumen_size > 0)
  #foreach($list1 in $listDokumen)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  <tr>  
    <td class="$row" >$list1.BIL</td>
    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
    <td class="$row" >$list1.KETERANGAN</td>
    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>   
    <td class="$row" ><div align="center">
       <input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
     </div></td>
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>

    </fieldset>    </td>
  </tr>
  
  #end
  
  <tr>
    <td><fieldset>
    <table width="100%">
      <tr>
        <td><div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab" tabindex="0" onClick="PembatalanAmbilTanahPT()" id="senarai_lot" >Senarai Lot</li>
             #if($maklumat_pembatalan.size() > 0)
            <li class="TabbedPanelsTab" tabindex="0" onClick="PembatalanAmbilTanahLotUnit()" id="lot_dibatal" >Lot Yang Dibatal</li>
            #end
          </ul>
        
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">
              <table width="100%">
              <tr>              
              <td colspan="8">
              
              <table width="100%" >
              <tr>
              <td width="1%">
              </td>
              <td align="left" width="59%" valign="top">
              DAERAH : $!nama_daerah
              </td>
              <td width="40%" align="right" valign="top">
              #if($senarai_hakmilik.size()!=0)
                No. Lot/PT : 
              
                    <label>
                    <input type="text" name="CariLot" id="CariLot"  />
                    </label>
                 
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="cari_lot(CariLot.value,'$hakmilik_belumbatal')" />
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="cari_lot1(CariLot.value)"  />
                      </label>
                      #if($readmode == "edit")
                      <div id="jumlahlot" ></div>
                      #end
                      
            #end      
                  </td>
              </tr>
              </table>
              
              
             </td>
             </tr>
                <tr>
                  <td>
                  #if($maklumat_pembatalan.size() > 0)
                  #if($senarai_hakmilik.size()!=0)
                  <!--
                  <input name="cmdPilihPembatalanLot" id="cmdPilihPembatalanLot" type="button" value="Pilih Pembatalan Lot" onClick="batalkan_lot()">
                  -->
                  #end
                  #end
                  <table width="100%">
                    <tr class="table_header">
                      <td width="3%">Bil</td>
                      <td width="15%">No. Lot/PT</td>
                      <td width="15%">Hakmilik atau Pendudukan</td>
                      <td width="19%">Tuan Punya Berdaftar atau Penduduk yang Direkodkan</td>
                      <td width="15%">Bandar/Pekan/Mukim</td>
                <!--      <td width="15%">Luas Lot</td> -->
                      <td width="15%">Lebih Kurang Luas yang Hendak Diambil</td>
                        <td width="15%">Lebih Kurang Luas yang Hendak Ditarik Balik</td>
                      
                       #if($maklumat_pembatalan.size() > 0)
					   #end
                       
                       #if($senarai_hakmilik.size()!=0)
                       
                       #if($readmode == "edit")
                      <td width="3%">
                      
                      <div align="center">
                        <input type="checkbox" name="all" id="all" onClick="doCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$hakmilik_sudahbatal');check_lot_tarik()" title="Semak untuk pilih semua" $disabledmode />
                      </div>                      </td>
                      #end
                       #end                    </tr>
                   
           #if($senarai_hakmilik.size()!=0)
           #foreach($list in $senarai_hakmilik) 
                 
           #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
               
                <tr >  
                <td colspan="7"> 
              

 <table width="100%" id="$list.BIL" style="visibility:collapse; display:none;"  class="$row"  > 
  <tr  >
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_PT</td>
  </tr>
  <tr >
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$list.NAMA_NEGERI</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$list.NAMA_DAERAH</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$list.NAMA_MUKIM</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$list.JENIS_HAKMILIK</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$list.KOD_JENIS_HAKMILIK</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
  </tr>
  <tr >
    <td>&nbsp;</td>
    <td>Pihak Kepentingan</td>
    <td>:</td>
    <td>
        #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""   class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end    </td>
  </tr>
</table>                </td> 
                </tr>
                
                    <tr id="$list.BIL_DUM"   class="$row"    >
                      <td >$list.BIL</td>
                      <td id="$list.NO_PT">
                      
                      <a class="style1" id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$list.BIL);" onMouseOut="HidePopup();" title="Klik untuk maklumat lengkap">$list.NO_PT</a>                      </td>
                      <td >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
                      <td >
                      #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""  style="width:100%" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  

                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end
                      <!--
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      #set($count_total=$count_total + 1)
                      
                      #if($count > 1 && $count != $count_total) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end
                                          
                      #end
                      #end  
                      -->                      </td>
                      <td >$list.NAMA_MUKIM</td>
                <!--      <td >$list.LUAS_LOT</td>  -->
                      <td >$list.LUAS_AMBIL
                       <input name="lot_ambil" id="lot_ambil"  style="width:100%" value="$list.LUAS_AMBIL_EDIT" type="hidden" >
                      </td>
                      <td >
                      
                      #if($readmode == "view")
                      $list.LUAS_LOT_TARIK
                      #else
                   
                      <input name="lot_tarik" id="lot_tarik"  style="width:100%;display:none;" value="$list.LUAS_LOT_TARIK_EDIT" type="text" onBlur="validateTarikh(this,this.value);check_lot_tarik()" onKeyUp="validateTarikh(this,this.value);check_lot_tarik()"  >                  
                       <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_HAKMILIK" type="hidden" >
                       #set($id_alertlot = "$list.ID_HAKMILIK"+"TARIK")
                      
                        <div id='$id_alertlot' style="color:red; background:#FFFFFF"  ></div> 
                    
                      #end
                      
                      
                      </td>
                       #if($maklumat_pembatalan.size() > 0)
                       #end
                        #if($readmode == "edit")
                      <td >
                        <div align="center">
                        <label>
                          <input type="checkbox" name="ids" id="ids" onClick="doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$hakmilik_sudahbatal');check_lot_tarik()" value="$list.ID_HAKMILIK" $disabledmode >
                        </label>
                        </div>                      </td>
                        #end                    </tr>
              #end
              
              #else
              <tr>
              <td colspan="8">
              Tiada Rekod              </td>
              </tr>
              #end
                  </table></td>
                </tr>
              </table>
            </div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
  
 
  
  
</table>

<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="screen_name" id="screen_name" value="s1" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
<input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
<input type="hidden" name="alert_message" id="alert_message" />
<input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
<input type="hidden" name="jumlah_semua" id="jumlah_semua" />

<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{

if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}

if(readmode == "edit")
{

if(jenis_permohonan == "3")
{
checking_validation(document.${formName}.txdTarikhSurat,'txdTarikhSurat_check','yes','surat penarikan balik','tarikh');
checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','yes','terima surat','tarikh');
}
if(jenis_permohonan == "1")
{
checking_validation(document.${formName}.txdTarikhSurat,'txdTarikhSurat_check','no','surat pembatalan','tarikh');
checking_validation(document.${formName}.txdTarikhTerimaSurat,'txdTarikhTerimaSurat_check','no','terima surat','tarikh');
}
checking_validation(document.${formName}.txtNoRujSurat,'txtNoRujSurat_check','yes','no rujukan surat','normal');
checking_validation(document.${formName}.txtSebabPembatalan,'txtSebabPembatalan_check','yes','alasan penarikan balik','normal');
doCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$hakmilik_sudahbatal');
doUpdateCheckAll('$hakmilikoverall','$hakmilik_belumbatal','$hakmilik_sudahbatal');




}
check_lot_tarik();
}

</script>
#if($jenis_permohonan == "3")
<script>

function kembali_senarai_depan()
{
	//document.${formName}.command.value = "PembatalanAmbilTanahPT";
	//document.${formName}.sub_command.value = "kembali_senarai_depan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}

function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";	
	document.${formName}.submit();
		
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
}


function simpan()
{

/*
if(document.${formName}.txdTarikhSurat.value == "")
{
alert("Sila masukkan tarikh surat");
document.${formName}.txdTarikhSurat.focus();
return;
}
else if(document.${formName}.txdTarikhTerimaSurat.value == "")
{
alert("Sila masukkan tarikh terima surat");
document.${formName}.txdTarikhTerimaSurat.focus();
return;
}
else if(document.${formName}.txtNoRujSurat.value == "")
{
alert("Sila masukkan no rujukan surat");
document.${formName}.txtNoRujSurat.focus();
return;
}

else if(document.${formName}.sorJenisPembatalan[0].checked == false && document.${formName}.sorJenisPembatalan[1].checked == false)
{
alert("Sila pastikan pilihan pembatalan adalah sebahagian atau keseluruhan");
return;
}
*/


var c = 0;

if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{
	
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
  input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
	
}	
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();	
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
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
      var strValidCharacters = "1234567890/.";
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


function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}	 	
}	 
 
	
	
	
	  if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
       
}

function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}


function doCheckAll_P(){    
    if (document.${formName}.sorJenisPembatalan[1].checked == true){
        if (document.${formName}.ids.length == null)
		{
            document.${formName}.ids.checked = true;
        } else 
		{
            for (i = 0; i < document.${formName}.ids.length; i++)
			{
                document.${formName}.ids[i].checked = true;
            }
        }
    } 
		
	 else if (document.${formName}.sorJenisPembatalan[0].checked == true)	
	 {
        if (document.${formName}.ids.length == null){
            document.${formName}.ids.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){
                document.${formName}.ids[i].checked = false;
            }
        }
    }
	
	
	
}


</script>

#end





<script>



function ReadOnly_CheckBox(v)
{
return false;
}



function ShowPopup(hoveritem,tab)
{
//alert("hp.style.display :"+hp.style.display+";hp.style.visibility :"+hp.style.visibility);
hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}



function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 1;	
 
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		//  DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	 //  DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	  // DateField.focus();
	   
	   }
	   
	   }
	   
	// 
	   if(jenis_field == "normal")
	   {
	    
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
}



function cari_lot(field,jl)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';

window.location.hash=field;
goTo(field);
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}

function doCheckAll(jumlah_lot,belumbatal,sudahbatal){ 
    var valid = "yes"
	var c = 0; 
	var p = 0; 	 
	var jl = jumlah_lot;
	var bb = belumbatal;
	var sb = sudahbatal;
	
	if(jl == 0)
	{
	 document.${formName}.alert_message.value  = "Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	else
	{
	
    if (document.${formName}.all.checked == true){
		
        if (document.${formName}.ids.length == null){		
             document.${formName}.ids.checked = true;
			 document.${formName}.lot_tarik.style.display="block";	 	 
	        // document.${formName}.lot_tarik[i].value="";	
			 c++;	 	
        } else {
        for (i = 0; i < document.${formName}.ids.length; i++){		
               document.${formName}.ids[i].checked = true;
			   document.${formName}.lot_tarik[i].style.display="block";	 	 
	         //  document.${formName}.lot_tarik[i].value="";		
			   c++;	   	
        }
		}	
	
	   document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = belumbatal;	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	
	
	
	
	  if(c == jl)
	  {	
	   
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  else if((parseInt(c)+parseInt(sb)) == jl )
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  else if(c < jl && c != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
	  
	  else if(c == 0 && sb == 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  valid = "no";
	  }
	  
	  else if(c == 0 && sb > 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  
			
			
        		
		
    } else {
        if (document.${formName}.ids.length == null){		
            document.${formName}.ids.checked = false;
			 document.${formName}.lot_tarik.style.display="none";	 	 
	         document.${formName}.lot_tarik.value="";		
			p++;			
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){			
                document.${formName}.ids[i].checked = false;
				document.${formName}.lot_tarik[i].style.display="none";	 	 
	            document.${formName}.lot_tarik[i].value="";			
				p++;			
            }
			
		}	
		
		
	   document.${formName}.jumlah_dipilih.value  = 0;	
	   document.${formName}.jumlah_semua.value  = belumbatal;	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
		
			
		 if(p == jl)
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  
	  valid = "no";
	
	  }
	  else if(p < jl && p != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
			
      
    }
	
	
	if(valid == "no")
	{
	
	   document.${formName}.alert_message.value  = "Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	else
	{
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	
	
	}
	
}



function doUpdateCheckAll(jumlah_lot,belumbatal,sudahbatal){ 
var valid = "yes";
var c = 0;
var semua = 0;
var jl = jumlah_lot;
var bb = belumbatal;
var sb = sudahbatal;

//alert("jumlah_lot:"+jl+";belumbatal:"+bb+";sudahbatal:"+sb);

if(jl == 0)
	{
	 document.${formName}.alert_message.value  = "Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	else
	{


if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {
	  semua++
      if (document.${formName}.ids[i].checked == true)
	  {	 
	  document.${formName}.lot_tarik[i].style.display="block";
	  c++
      }
	  else
	  {	 
	  document.${formName}.lot_tarik[i].style.display="none";	 	 
	  document.${formName}.lot_tarik[i].value="";		 
      }
	  }  
}
else
{
	semua = 1
	if (document.${formName}.ids.checked == true)
	{	
	document.${formName}.lot_tarik.style.display="block"; 
	c++;
	}	
	else
	{	 
	document.${formName}.lot_tarik.style.display="none";	 
	document.${formName}.lot_tarik.value="";	 
    } 	
}	 
 
	   document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = belumbatal;	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	
	
	  if(c==semua)
	  {	  
	  document.${formName}.all.checked = true;	
	  }
	  else
	  {
	  document.${formName}.all.checked = false;	
	  }
	  
	   
	   if(c == jl)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  
	  else if((parseInt(c)+parseInt(sb)) == jl )
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  
	  else if(c < jl && c != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
	  
	  else if(c == 0 && sb == 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  valid = "no";
	  }
	  
	  else if(c == 0 && sb > 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true;
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
	  
	
	   if(valid == "no")
	{
	
	   document.${formName}.alert_message.value  = "Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	else
	{
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	}
	   
	}   
}
function check_lot_tarik()
{

	  if(document.${formName}.id_lot_tarik.length > 1)
      {     
	  for (i = 0; i < document.${formName}.id_lot_tarik.length; i++)
	  {
	 
	
      if (document.${formName}.lot_tarik[i].value == "" && document.${formName}.ids[i].checked == true)
	  {	 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   	  
      }
	  else if((parseFloat(document.${formName}.lot_tarik[i].value) > parseFloat(document.${formName}.lot_ambil[i].value)) && document.${formName}.ids[i].checked == true)
	  {	 
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   	 
      }
	  
	  else if((parseFloat(document.${formName}.lot_tarik[i].value) <= 0) && document.${formName}.ids[i].checked == true)
	  {	
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		    
      }
	  else 
	  {	 
	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	 
      }  
	  
	  }  
}
else
{

//alert("LALAL"+document.${formName}.id_lot_tarik.length);
	if (document.${formName}.lot_tarik.value == "" && document.${formName}.ids.checked == true)
	  {	 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   	  
      }
	  else if((parseFloat(document.${formName}.lot_tarik.value) > parseFloat(document.${formName}.lot_ambil.value)) && document.${formName}.ids.checked == true)
	  {	 
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   	 
      }
	  
	  else if((parseFloat(document.${formName}.lot_tarik.value) <= 0) && document.${formName}.ids.checked == true)
	  {	
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		    
      }
	  else 
	  {	 
	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	 
      } 	
}	 
 
	  	   

}


</script>



