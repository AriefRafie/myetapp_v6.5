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
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>Nama</strong></td>
          <td><strong>MyID Baru</strong></td>
          <td><strong>Alamat</strong></td>
          <td><strong>No. Telefon</strong></td>
           <td><strong>Maklumat</strong>
        </tr>
        #set ($list = "")
        #set ( $count = $startNumber )
        #if ($SenaraiFail2.size() > 0)
        #foreach ($list in $SenaraiFail2)
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
          <td class="$row"><a href="javascript:paparPenghutang('$!list.idHutang')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.noPengenalanBaru</td>
          <td class="$row">$!list.alamat1 $!list.alamat2 $!list.alamat3 <br/>
          				   $!list.poskod $!list.bandar $!list.negeri</td>
          <td class="$row">$!list.noTelefon</td>
           <td class="$row">$!list.simati $!list.pemohon</td>
           
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
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>Nama</strong></td>
          <td><strong>MyID Baru</strong></td>
          <td><strong>Alamat</strong></td>
          <td><strong>No. Telefon</strong></td>
          
         
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
          <td class="$row"><a href="javascript:paparPenghutang('$!list.idHutang')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.noPengenalanBaru</td>
          <td class="$row">$!list.alamat1 $!list.alamat2 $!list.alamat3 <br/>
          				   $!list.poskod $!list.bandar $!list.negeri</td>
          <td class="$row">$!list.noTelefon</td>
         
         
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
