<!-- Senarai Penghutang -->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama : </td>
          <td width="70%"><input name="findNama" id="findNama" type="text" value="$!findNama" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr >
          <td width="30%" height="24" scope="row" align="right">MyID Baru : </td>
          <td width="70%"><input name="findNoPengenalanBaru" id="findNoPengenalanBaru" type="text" value="$!findNoPengenalanBaru" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        #if($!role != "adminppk")
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="doDivAjaxCall$formname('divMainForm','carianPenghutang','');">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="doDivAjaxCall$formname('divMainForm','','');"></td>
        </tr>
        #else
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="doDivAjaxCall$formname('divMainForm','carianPenghutangolehPPK','');">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="doDivAjaxCall$formname('divMainForm','','');"></td>
        </tr>
        #end
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>

  <tr>
    <td><fieldset>
      <legend><b>SENARAI REKOD</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
         <tr>
          <td colspan="5" scope="row">
           #if($!role != "adminppk")
          <input name="cmdDaftar" type="button" value="Tambah" onclick="doDivAjaxCall$formname('divMainForm','daftarPenghutang','');"/>
          #end
          </td>
        </tr>
        #if($!role == "adminppk")
         <tr class="table_header">
          <td scope="row" width="2%" align="center"><strong>Bil</strong></td>
          <td width="22%"><strong>Nama</strong></td>
          <td width="8%"><strong>MyID Baru</strong></td>
          <td width="22%"><strong>Alamat</strong></td>
          <td width="8%"><strong>No. Telefon</strong></td>
          <!-- <td width="20%"><strong>Maklumat</strong></td>
          <td><strong>Alamat Pejabat</strong></td> -->
        </tr>
        
        #set ($list = "")
        #set ( $count = $startNumber )
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$count</td>
          #if ($list.simati != '')
          #set ($flagJenis = "1")
          #end
          #if ($list.pemohon != '')
          #set ($flagJenis = "2")
          #end
          #if (($list.simati == '') && ($list.pemohon == ''))
          #set ($flagJenis = "3")
          #end
          <td class="$row"><a href="javascript:paparPenghutang('$!list.idHutang$flagJenis')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.noPengenalanBaru </td>
          <td class="$row">$!list.alamat1 $!list.alamat2 $!list.alamat3 <br/>
          				   $!list.poskod $!list.bandar $!list.negeri</td>
          <td class="$row">$!list.noTelefon</td>
          <!-- 
          <td class="$row">$!list.simati $!list.pemohon</td>
          #if ($list.pejabat_PE != '')
          <td class="$row">
          #if ($list.pejabat_PE != '')$!list.pejabat_PE, #end 
          #if ($list.alamat1_PE != '')$!list.alamat1_PE, #end 
          #if ($list.alamat2_PE != '')$!list.alamat2_PE, #end 
          #if ($list.alamat3_PE != '')$!list.alamat3_PE, #end 
          $!list.poskod_PE  
          #if ($list.bandar_PE != '')$!list.bandar_PE, #end
          #if ($list.negeri_PE != '')$!list.negeri_PE. #end 
          #if ($list.no_tel_PE != '')<br/>No. Telefon : $!list.no_tel_PE #end
          #if ($list.no_fax_PE != '') <br/>No. Fax : $!list.no_fax_PE #end
          </td> 
          #end
          
          #if ($list.pejabat_S != '')
          <td class="$row">
          #if ($list.pejabat_S != '')$!list.pejabat_S, #end 
          #if ($list.alamat1_S != '')$!list.alamat1_S, #end 
          #if ($list.alamat2_S != '')$!list.alamat2_S, #end 
          #if ($list.alamat3_S != '')$!list.alamat3_S, #end 
          $!list.poskod_S  
          #if ($list.bandar_S != '')$!list.bandar_S, #end
          #if ($list.negeri_S != '')$!list.negeri_S. #end 
          #if ($list.no_tel_S != '')<br/>No. Telefon : $!list.no_tel_S #end
          #if ($list.no_fax_S != '') <br/>No. Fax : $!list.no_fax_S #end
          </td> 
          #end
          #if (($list.pejabat_S == '') && ($list.pejabat_PE == ''))
           <td class="$row"></td>
          #end
          -->
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="4">Tiada Rekod</td>
        </tr>
        #end
        #else
        <tr class="table_header">
          <td scope="row" width="2%" align="center"><strong>Bil</strong></td>
          <td width="22%"><strong>Nama</strong></td>
          <td width="8%"><strong>MyID Baru</strong></td>
          <td width="22%"><strong>Alamat</strong></td>
          <td width="8%"><strong>No. Telefon</strong></td>
          <!-- 
          <td width="20%"><strong>Maklumat</strong></td>
          <td><strong>Alamat Pejabat</strong></td>
           -->
         
        </tr>
        #set ($list = "")
        #set ( $count = $startNumber )
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$count</td>
          #if ($list.simati != '')
          #set ($flagJenis = "1")
          #end
          #if ($list.pemohon != '')
          #set ($flagJenis = "2")
          #end
          #if (($list.simati == '') && ($list.pemohon == ''))
          #set ($flagJenis = "3")
          #end
          <td class="$row"><a href="javascript:paparPenghutang('$!list.idHutang$flagJenis')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.noPengenalanBaru</td>
          <td class="$row">$!list.alamat1 $!list.alamat2 $!list.alamat3 <br/>
          				   $!list.poskod $!list.bandar $!list.negeri</td>
          <td class="$row">$!list.noTelefon</td>
          <!-- 
          <td class="$row">$!list.simati $!list.pemohon</td>
          #if ($list.pejabat_PE != '')
          <td class="$row">
          #if ($list.pejabat_PE != '')$!list.pejabat_PE, #end 
          #if ($list.alamat1_PE != '')$!list.alamat1_PE, #end 
          #if ($list.alamat2_PE != '')$!list.alamat2_PE, #end 
          #if ($list.alamat3_PE != '')$!list.alamat3_PE, #end 
          $!list.poskod_PE  
          #if ($list.bandar_PE != '')$!list.bandar_PE, #end
          #if ($list.negeri_PE != '')$!list.negeri_PE. #end 
          #if ($list.no_tel_PE != '')<br/>No. Telefon : $!list.no_tel_PE #end
          #if ($list.no_fax_PE != '') <br/>No. Fax : $!list.no_fax_PE #end
          </td> 
          #end
          
          #if ($list.pejabat_S != '')
          <td class="$row">
          #if ($list.pejabat_S != '')$!list.pejabat_S, #end 
          #if ($list.alamat1_S != '')$!list.alamat1_S, #end 
          #if ($list.alamat2_S != '')$!list.alamat2_S, #end 
          #if ($list.alamat3_S != '')$!list.alamat3_S, #end 
          $!list.poskod_S  
          #if ($list.bandar_S != '')$!list.bandar_S, #end
          #if ($list.negeri_S != '')$!list.negeri_S. #end 
          #if ($list.no_tel_S != '')<br/>No. Telefon : $!list.no_tel_S #end
          #if ($list.no_fax_S != '') <br/>No. Fax : $!list.no_fax_S #end
          </td> 
          #end
          
          #if (($list.pejabat_S == '') && ($list.pejabat_PE == ''))
          <td class="$row"></td>
          #end
          -->
         
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="4">Tiada Rekod</td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
