<table cellpadding="2" cellspacing="1" border="0" width="80%" align="center" >
 <tr class="line" >
  <td width="5%" valign="top"></td>
  <td  width="45%" ><img   align="top" src="../img/fb_msg.png" /> &nbsp; <font  size="2"><b>Pesanan</b></font> </td>
  <td  width="20%" align="center"  ><input type="button" name="cmdAddMesej"  id="cmdAddMesej" value="Tambah Mesej Baru" onClick="javascript:daftarMesej()" /></td>
  <td  width="30%" align="left"  >
  
  
  <ul id="sddmx">
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()">
        <img   height="22" width="22" align="top" src='../img/small_search.png' />
        &nbsp;
        </a>
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()">            
        <a href="javascript:pilihUnread()">Mesej Yang Belum Dibaca</a>
        <a href="javascript:pilihArkib()">Arkib</a>
        
       
        </div>
    </li>
   </ul>
  
  
  
  
   <textarea name="carian_main" type="text" id="carian_main" cols="25"   rows="1"  style="height:20" placeholder="Cari Mesej..." onkeypress="javascript:carian_enter(event,'carian_main')"    />$!carian_main</textarea> 
   
   </td>

  </tr>


</table>
<br />
#if($main_list_mesej.size() > 0)
#foreach($ur in $main_list_mesej)
#set($div_list = "div_list"+$ur.BIL)


<table cellpadding="2" cellspacing="1" border="0" width="80%" align="center" class="line"  >
  <tr class="line" >
  <td width="5%" valign="top"></td>
  <td  width="70%"  valign="top" >
  <a href="javascript:bukaMesej('$ur.id_maininbox')">
  <div  id="$div_list" style="width:100%;overflow:auto;" > 
  #if($ur.flag_read == "NO")
  <font  color="blue"><b>$ur.senaraiUser</b></font>
  #else
  <font  color="blue">$ur.senaraiUser</font>
  #end
  </div>
 
  <div style="width:70%;"> 
  <span>  
  #if($ur.id_sender == $pengguna_aktif)
  <img   title="Maklumbalas terakhir adalah daripada anda"  src="../img/reply_iconpng.png" /></img>
  #end 
   #if($ur.ada_attach == "ada")
  <img  align="top"  title="Ada Lampiran Pada Mesej Ini" width="15" height="15"  src="../img/attachment-icon.png" /></img>
  #end
  </span>
  <span>
  <font  color="grey" >  
  $ur.last_mesej </font>
  </span>
  </div>
  </a>  
  </td>
  <td  width="15%"  valign="top" ><font  color="grey">$ur.tarikh_kemaskini</font></td>
  <td  width="5%" align="center"  valign="top" > <a href="javascript:setUnread('$ur.id_maininbox')"><img  height="30" width="30"  align="top"  title="Tanda Sebagai Belum Dibaca" src="../img/mark_read.png" /></img></a></td>
  <td  width="5%" align="center" valign="top"  >
  
  #if($!openArkib == "Y")
  <a href="javascript:setUnArkib('$ur.id_maininbox')"><img align="middle" title="Keluarkan Daripada Arkib"  src="../img/archive_un.png" /></img></a>
  #else
  <a href="javascript:setArkib('$ur.id_maininbox')"><img align="middle" title="Arkib"  src="../img/archive.png" /></img></a>
  #end
  </td>
  </tr>
  </table>

#end

#else

<table cellpadding="2" cellspacing="1" border="0" width="80%" align="center" class="line"  >
  <tr class="line" >
  <td width="5%" valign="top"></td>
  <td  width="70%"  valign="top" >
  Tiada Rekod
</td>
  <td  width="15%"  ></td>
  <td  width="5%" align="center"  ></td>
  <td  width="5%" align="center"  ></td>
  </tr>
  </table>

#end



<script type="text/javascript" >
var xxx = parseInt('$main_list_mesej.size()');
for (x = 0; x < xxx; x++)
{
    var a = "div_list"+(x+1);
	var objDivAtas = document.getElementById(a);
	var var_total_atas = objDivAtas.clientHeight;
	if(var_total_atas > 55)
	{
	objDivAtas.style.height = 55;
	objDivAtas.style.width = '100%';
	objDivAtas.style.overflow = 'auto';
	}
	objDivAtas.scrollTop = objDivAtas.scrollHeight;
}
</script>
