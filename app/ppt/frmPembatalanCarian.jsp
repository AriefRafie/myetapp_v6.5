
<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>

<!--
#if($list_default == "yes")
#set($txtNoFail="")
#set($txtNoRujJkptgNegeri="")
#set($socKementerian="")
#set($socUrusan="")
#set($socStatus="")
#end
-->
<table width="100%">
  <tr>
    <td> 
     
    <fieldset><legend>CARIAN PERMOHONAN PEMBATALAN</legend>
    <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" ><div align="left">No Fail</div></td>
    <td width="1%">:</td>
    <td width="70%">
      <label>
        <input name="txtNoFail" type="text" id="txtNoFail" size="70" maxlength="100" value="$!txtNoFail" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" >
        
        <input name="txtNoRujJkptgNegeri" type="hidden" id="txtNoRujJkptgNegeri" size="70" maxlength="100" value="$!txtNoRujJkptgNegeri" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" >
        </label>      </td>
  </tr>
  <!--
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">No Ruj JKPTG Negeri</div></td>
    <td>:</td>
    <td>
      <label>
        
        </label>    </td>
  </tr>
  -->
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">Nama Kementerian</div></td>
    <td>:</td>
    <td>
      <label>
      
      <select name="socKementerian" id="socKementerian" class="autoselect">
      
      #if($!socKementerian == "")
      <option value="">SILA PILIH KEMENTERIAN</option>
      #else
      #foreach($listkementerian in $list_kementerian)
      #if($!socKementerian == $listkementerian.ID_KEMENTERIAN)    
      <option value="$listkementerian.ID_KEMENTERIAN">$listkementerian.KOD_KEMENTERIAN - $listkementerian.NAMA_KEMENTERIAN</option>
      #end
      #end
      #end
        
       #foreach($listkementerian in $list_kementerian)
       #if($!socKementerian != $listkementerian.ID_KEMENTERIAN)    
       <option value="$listkementerian.ID_KEMENTERIAN">$listkementerian.KOD_KEMENTERIAN - $listkementerian.NAMA_KEMENTERIAN</option>
       #end       
       #end
       
       #if($!socKementerian!="")
        <option value="">SILA PILIH KEMENTERIAN</option>
        #end
      </select>
      </label>       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">Urusan</div></td>
    <td>:</td>
    <td>
        <label>       
        <select name="socUrusan" id="socUrusan" class="autoselect">
        #if($!socUrusan=="")
        <option value="">SILA PILIH URUSAN</option>
        #else
        #foreach($listurusan in $list_urusan)
        #if($!socUrusan==$listurusan.ID_SUBURUSAN)
        <option value="$listurusan.ID_SUBURUSAN">$listurusan.KOD_SUBURUSAN - $listurusan.NAMA_SUBURUSAN</option>
        #end
        #end
        #end
      	#foreach($listurusan in $list_urusan)
        #if($!socUrusan!=$listurusan.ID_SUBURUSAN)
        <option value="$listurusan.ID_SUBURUSAN">$listurusan.KOD_SUBURUSAN - $listurusan.NAMA_SUBURUSAN</option>
        #end
        #end
        #if($!socUrusan!="")
        <option value="">SILA PILIH URUSAN</option>
        #end
        
        </select>
        </label>      
        </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="left">Status</div></td>
    <td>:</td>
    <td>
      <label>
        
         <select name="socStatus" id="socStatus" class="autoselect">
        #if($!socStatus=="")
        <option value="">SILA PILIH STATUS</option>
        #else
        #foreach($liststatus in $list_status)
        #if($!socStatus==$liststatus.ID_STATUS)
        <option value="$liststatus.ID_STATUS">$liststatus.KOD_STATUS - $liststatus.KETERANGAN</option>
        #end
        #end
        #end
        
        
      	#foreach($liststatus in $list_status)
        #if($!socStatus!=$liststatus.ID_STATUS)
        <option value="$liststatus.ID_STATUS">$liststatus.KOD_STATUS - $liststatus.KETERANGAN</option>
        #end
        #end
        #if($!socStatus!="")
        <option value="">SILA PILIH STATUS</option>
        #end
        </select>
        </label>       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="left"></div></td>
    <td>&nbsp;</td>
    <td>
      <label>
        <input type="button" name="cmdCari " id="cmdCari " value="Cari" onClick="cari()">
        </label>
      <label>
      <input  type="button" name="cmdIsiSemula  " id="cmdIsiSemula  " value="Batal" onClick="batal()">
      </label>    </td>
  </tr>
</table>

    </fieldset>
    </td>
  </tr>
  
  <tr>
    <td>
    <fieldset><legend>SENARAI PERMOHONAN PEMBATALAN</legend>
   <!-- <input name="cmdPapar" type="button" value="Papar" onClick="papar()"> -->
    #if($listdepan_size!=0)
    #parse("app/utils/record_paging.jsp")
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="3%">Bil</td>
    <td width="15%">No Fail</td>
    <!--
    <td width="15%">No Pembatalan</td>
    <td width="10%">Tarikh Batal</td>    
    -->
  <!--  <td width="15%">No. Jkptg Negeri</td> -->
    <td width="30%">Kementerian</td>
    <td width="15%">Daerah</td>
    <td width="12%">Status</td>
  </tr>
  
  #if($listdepan_size!=0)
           #foreach($list in $listdepan)         
           #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
  <tr>
    <td class="$row" >
    $list.BIL          
    </td>
    <td class="$row" ><a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_PEMBATALAN')">
    <div class="style1" >$list.NO_FAIL</div>                                    
    </a>       </td>
    <!--
    <td class="$row" >$list.NO_PEMBATALAN</td>
    <td class="$row" >$list.TARIKH_PEMBATALAN</td>    
    -->
 <!--   <td class="$row" >$list.NO_RUJUKAN_UPT</td> -->
    <td class="$row" >$list.NAMA_KEMENTERIAN</td>
    <td class="$row" >$list.NAMA_DAERAH</td>
    <td class="$row" >$list.KETERANGAN</td>
  </tr>
  #end
  #else   	
    		<tr>
    			<td colspan="8">Tiada Rekod</td>
    		</tr>
  #end
</table>

    </fieldset>
    </td>
  </tr>
  
</table>
<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="id_permohonan" id="id_permohonan" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" />


<script>
function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}
function cari()
{
	//document.${formName}.command.value = "senarai";
	//document.${formName}.sub_command.value = "cari";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function batal()
{
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoRujJkptgNegeri.value = "";
	document.${formName}.socKementerian.value = "";
	document.${formName}.socUrusan.value = "";
	document.${formName}.socStatus.value = "";
}
</script>
