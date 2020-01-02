<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openNotis425')
  <tr>
    <td> #parse("app/php2/frmCRBMaklumatNotis425.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onClick="javascript:doDaftarMaklumatNotis425()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="26%"><strong>Nama Dokumen</strong></td>
          <td width="39%"><strong>PTD / PTG / JKPTG</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiNotis425.size() > 0)
        #foreach ($list in $SenaraiNotis425)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row"><a href="javascript:paparMaklumatNotis425('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatNotis425('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)</td>
          #end
          <td class="$row">  
          	#if($list.flagKJP == 'PTD' || $list.flagKJP == 'PTG')
            	$list.namaPejabatPTGPTD
            #elseif($list.flagKJP == 'JKPTG')
            	$list.namaPejabat
            #end</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row">$list.status</td>
       #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakCRBNotis425('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakCRBUlanganNotis425('$idFail','$list.idUlasanTeknikal','$list.bilUlangan')"><img border="0" src="../img/print.gif"/></a></td>
          #end                    
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

