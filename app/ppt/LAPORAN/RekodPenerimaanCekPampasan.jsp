
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
     
    <fieldset>
    <legend>CARIAN PERMOHONAN</legend>
    <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" ><div align="left">No Fail</div></td>
    <td width="1%">:</td>
    <td width="70%">
      <label>
        <input name="txtNoFail" type="text" id="txtNoFail" size="70" maxlength="100" value="$!txtNoFail" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >
        <input name="txtNoRujJkptgNegeri" type="hidden" id="txtNoRujJkptgNegeri" size="70" maxlength="100" value="$!txtNoRujJkptgNegeri" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" >
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
    <td><div align="left">Nama Pejabat JKPTG</div></td>
    <td>:</td>
    <td>
      <label>
      
      <select name="socPejabat" id="socPejabat" class="autoselect">
      
      #if($!socPejabat == "")
      <option value="">SILA PILIH PEJABAT</option>
      #else
      #foreach($list_pejabat in $list_pejabat)
      #if($!socPejabat == $list_pejabat.ID_PEJABATJKPTG)    
      <option value="$list_pejabat.ID_PEJABATJKPTG">$list_pejabat.NAMA_PEJABAT - $list_pejabat.JENIS_PEJABAT</option>
      #end
      #end
      #end
        
       #foreach($list_pejabat in $list_pejabat)
      #if($!socPejabat != $list_pejabat.ID_PEJABATJKPTG)    
      <option value="$list_pejabat.ID_PEJABATJKPTG">$list_pejabat.NAMA_PEJABAT - $list_pejabat.JENIS_PEJABAT</option>
      #end     
       #end
       
       #if($!socPejabat!="")
        <option value="">SILA PILIH PEJABAT</option>
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
    <fieldset>
    <legend>SENARAI PERMOHONAN </legend>
    
    <!-- <input name="cmdPapar" type="button" value="Papar" onClick="papar()"> -->
    #if($listdepan_size!=0)
    #parse("app/utils/record_paging.jsp")
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="3%">Bil</td>
    <td width="15%">No Fail</td>
    <!--
    <td width="15%">No. Tarik Balik</td>
    <td width="10%">Tarikh Tarik Balik</td>   
    --> 
  <!--  <td width="15%">No. Jkptg Negeri</td> -->
    <td width="30%">Kementerian</td>
    <td width="15%">Urusan</td>
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
    <td class="$row" ><a href="javascript:laporan('$list.ID_FAIL')">
    <div class="style1" >$list.NO_FAIL</div>                                    
    </a>       </td>
    <!--
    <td class="$row" >$list.NO_PEMBATALAN</td>
    <td class="$row" >$list.TARIKH_PEMBATALAN</td>  
    -->  
  <!--  <td class="$row" >$list.NO_RUJUKAN_UPT</td>  -->
    <td class="$row" >$list.NAMA_KEMENTERIAN</td>
    <td class="$row" >$list.URUSAN</td>
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
 <input type="hidden" name="list_name" id="list_name" value="list_permohonan" />


<script>

function cari()
{	
	document.${formName}.action = "";
	document.${formName}.submit();
}


function open_new_window() 
{
var width  = 300;
 var height = 200;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();



new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body >");



new_window.document.write("<table width='100%'>");
new_window.document.write("<tr class='table_header'>");
new_window.document.write("<td width='3%'>Bil</td> <td width='15%'>No. Lot/PT</td>  <td width='15%'>Hakmilik atau Pendudukan</td>");
new_window.document.write("<td width='19%'>Tuan Punya Berdaftar atau Penduduk yang Direkodkan</td><td width='18%'>Bandar/Pekan/Mukim</td>");
new_window.document.write("<td width='18%'>Lebih Kurang Luas yang Hendak Diambil</td>");
new_window.document.write("</tr>#if($senarai_hakmilik_batal.size()!=0) #set($tab_lot = 0)");
new_window.document.write("#foreach($list in $senarai_hakmilik_batal)#set($tab_lot_tarik = $tab_lot+'TAB')");
new_window.document.write("#set( $i = $velocityCount ) #if ( ($i % 2) != 1 ) #set( $row = 'row2' ) #else #set( $row = 'row1' )#end");
new_window.document.write("<tr id='$list.BIL_DUM' class='$row' ><td  >$list.BIL</td><td id='$list.NO_PT' > ");
new_window.document.write("<a class='style1' id='hoverover' style='cursor:default; color:#0000FF' onClick='' >$list.NO_PT</a></td><td >$list.NO_HAKMILIK</td>");
new_window.document.write("<td >#set($count = 0) #foreach($list1 in $senarai_pihak_penting)#if($list1.ID_HAKMILIK == $list.ID_HAKMILIK ) ");    
new_window.document.write("#set($count=$count + 1) ");
new_window.document.write("#end #end #set($count_total = 0) #if($count == 1) #foreach($list1 in $senarai_pihak_penting) #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )");
new_window.document.write("$list1.NAMA_PB #end #end #elseif($count > 1) <select name=''  style='width:100%;' > #foreach($list1 in $senarai_pihak_penting)");
new_window.document.write("#if($list1.ID_HAKMILIK == $list.ID_HAKMILIK ) #set( $ix = $velocityCount ) #if ( ($ix % 2) != 1 ) #set( $rowx = 'row2' )");
new_window.document.write("#else #set( $rowx = 'row1' ) #end <option   > $list1.NAMA_PB </option> #end #end </select> #else #end </td><td >$list.NAMA_MUKIM</td>");
new_window.document.write("<td >$list.LUAS_AMBIL</td></tr> #end #else <tr> <td colspan='8'> Tiada Rekod  </td> </tr> #end </table>");


new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}


function laporan(id_fail)
{
   var url = "../servlet/ekptg.report.ppt.RekodPenerimaanCekPampasan?idFail="+id_fail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


</script>
