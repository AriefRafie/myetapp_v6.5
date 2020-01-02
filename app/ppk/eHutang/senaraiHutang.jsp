<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
   #set ($listJenisPenghutang = "")
   #foreach ($listJenisPenghutang in $maklumathutangPPK)
        #set ( $jenisPenghutang = $listJenisPenghutang.KATEGORI )
        #if ( $jenisPenghutang == "SIMATI")
        	#set ( $jenisPenghutang = "1")
        #end
        #if ( $jenisPenghutang == "PEMOHON")
        	#set ( $jenisPenghutang = "2")
        #end
        #if ( $jenisPenghutang == "")
        	#set ( $jenisPenghutang = "3")
        #end
  #end
    <td><fieldset>
      <legend><b>SENARAI HUTANG</b></legend>
      <table align="center" width="100%">
         <tr>
          <td colspan="5" scope="row">
          #if($!role != "adminppk")
          <input name="cmdDaftar" type="button" value="Tambah" onClick="doDivAjaxCall$formname('divMainForm','daftarHutang','');"/>
          #end
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>Nama / Agensi Pemiutang</strong></td>
          <td><strong>Jenis Hutang</strong></td>
          <td><strong>Nilai Hutang (RM)</strong></td>
          <td><strong>Baki Hutang (RM)</strong></td>
          #if(($!role == "adminppk") && ($!jenisPenghutang == "1"))
          <td><strong>Salin</strong></td>
          #end
        </tr>
        
        #set ($list = "")
        <!--#set ( $count = $startNumber )  -->
        #set ( $count = 0 )
        #set ( $count3a = 0 )
        #if ($listHutang.size() > 0)
        #foreach ($list in $listHutang)
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
          <td class="$row"><a href="javascript:paparHutang('$!list.idSenaraiHutang')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.jenisHutang</td>
          <td class="$row">$!list.nilaiHutang</td>
          <td class="$row">$!list.bakiHutang $!list.idPejabat</td>
          #if(($!role == "adminppk") && ($!jenisPenghutang == "1"))
          <td align="center" class="$row">
          #if (($list.flagSalin == '1') || ($list.statusHutang =="Y"))
            #set ($count3a = $count3a + 1)
          	&#10004;
          #else
           <input type="checkbox" name="salin" id="salin" value="'$!list.idSenaraiHutang'"><br>
          #end
          </td>
          #end
        </tr>
        #set ($id_senaraihutang = $!list.idSenaraiHutang)
        #end
        #if ($role != "(INTEGRASI)UsersAgensi")
        <tr>
        <td colspan="5" align="center">
        #if (($count != $count3a) && ($!jenisPenghutang == "1"))
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Salin" onclick="doDivAjaxCall$formname('divMainForm','SimpanDalamPenghutangSemua','count='+ $count);"/>
        <br/>
        #end
        #if (($count == $count3a) && ($!jenisPenghutang == "1"))
        <input type="button" disabled name="cmdSimpan2" id="cmdSimpan2" value="Salin" onclick="doDivAjaxCall$formname('divMainForm','SimpanDalamPenghutangSemua','count='+ $count);"/>
        #end
        
        <!--<input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Test" onclick="doDivAjaxCall$formname('divMainForm','SimpanDalamPenghutangSemua2','count='+ $count);"/>  -->
        </td>
        </tr>
        #end
        
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="4">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
