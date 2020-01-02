  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<fieldset>
  <legend>SENARAI FAIL MUAT TURUN CUKAI</legend>
  <fieldset><legend>TAPISAN LANJUTAN</legend>
    <table width="42%" border="0" align="center">
      <tr>
         <td width="20%">Negeri</td>
         <td width="2%">:</td>
         <td width="78%">$lblNegeri2</td>
      </tr>
       <tr>
         <td>Bandar/Daerah/Pekan</td>
         <td>:</td>
         <td>$lblDaerah2</td>
       </tr>
       <tr>
         <td>Mukim</td>
         <td>:</td>
         <td>$lblMukim2</td>
       </tr>
       <tr>
         <td>No. Hakmilik</td>
         <td>:</td>
         <td>
          
             <input onkeyup="this.value=this.value.toUpperCase();" type="text" name="txtNoHakmilik" id="txtNoHakmilik" size="43px"/>
        
   			</td>
       </tr>
       <tr>
         <td colspan="3">
         
             <input class="stylobutton" type="button" name="btnCariHakmilik" id="btnCariHakmilik" value="Cari" onclick="cariSenaraiTemp()"/>
       
        </td>
       </tr>
    </table>
  </fieldset>

  <fieldset><legend>SENARAI NEGERI : $infoNegeri.namaNegeri</legend>
  
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="1" cellpadding="2" border="0">
  <tr class="table_header">
 
  	<td width="3%">Bil#</td>
  	<td width="14%">Daerah</td>
  	<td width="14%">Mukim</td>
  	<td width="19%">No Hakmilik</td>
  	<td width="18%">No Lot</td>
  	<td width="24%">Kegunaan Tanah</td>
  	<td width="40%">Cukai Perlu di Bayar (RM)</td>
  	</tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $listtapis )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  	<td class="$row">$cnt.</td>
  	<td class="$row">$senarai.lblNamaDaerah</td>
  	<td class="$row">$senarai.lblNamaMukim</td>
    <td class="$row"><a href="javascript:uploadFail('$senarai.idpermohonan')" class="style1">$senarai.Nohakmilik</a></td>
    <td class="$row">$senarai.NoLot</td>
    <td class="$row">$senarai.KTanah</td>
    <td class="$row">$senarai.CUKAI_PERLU_BAYAR</td>
    </tr>
  #end
  
#if ($cnt == 0)
  	<tr> 
  		<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
#end
  </table>
  </fieldset>
<input type="hidden" name="idpermohonan" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
 <!-- </form> -->
</fieldset>




<script language="JavaScript"> 

//function doChangeDaerah1(){
//	doAjaxCall${formName}("doChangeDaerah1");
//}
//document.forms[0].no_fail.focus(); 
</script>