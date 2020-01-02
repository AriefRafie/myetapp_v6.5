#if($!role == "adminppk")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI AGENSI</b></legend>
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
          
        </tr>
        agensi = $agensi
        #set ($list = "")
        <!--#set ( $count = $startNumber )  -->
        #set ( $count = 0 )
        #set ( $count3a = 0 )
        #if ($agensi.size() > 0)
        #foreach ($list in $agensi)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        list.NAMA_PEJABAT = $!list.NAMA_PEJABAT
        <tr>
          <td class="$row" align="center">$count</td>
          <td class="$row"><a href="javascript:paparHutang('$!list.NAMA_PEJABAT')" class="style2">$!list.NAMA_PEJABAT</a></td>
          
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
#end
