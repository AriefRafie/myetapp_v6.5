<style type="text/css">


#sddmheader
{	margin: 0;
	padding: 0;
	z-index: 30}

#sddmheader li
{	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
	
	}

#sddmheader li a
{	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddmheader li a:hover
{	
background: #E0F2F7;
}

#sddmheader div
{	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;	
	border: 1px solid #5970B2;
	z-index:2;
	}

	#sddmheader div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 2.5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 11px arial;
		z-index:2;
		}

	#sddmheader div a:hover
	{	background: #49A3FF;
		color: #FFFFFF;
		}

</style>



#foreach ( $senarai in $header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($nama_kementerian1=$senarai.nama_kementerian1)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($nama_daerah1=$senarai.nama_daerah1)
    #set ($id_status = $senarai.id_status)
    #set ($tujuan=$senarai.tujuan)
    #set ($tujuan1=$senarai.tujuan_mmk)    
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan) 
    #set ($nama_agensi=$senarai.nama_agensi)  
    #set ($id_permohonan=$senarai.id_permohonan) 
    
    #set ($ID_NEGERIPROJEK=$senarai.ID_NEGERI) 
    
    
    #set ($id_fail=$senarai.id_fail) 
    #set ($id_suburusanstatus=$senarai.ID_SUBURUSANSTATUS) 
    #set ($id_suburusanstatusfail=$senarai.ID_SUBURUSANSTATUSFAIL) 
    
#end 


#if($willShowAlertSelesai=="yes")

#set($totalHakmilikBorangK = 0)
#set($totalHakmilik = 0)

#foreach($header in $dataHeader)
#set($totalHakmilikBorangK=$header.totalHakmilikBorangK)
#set($totalHakmilik=$header.totalHakmilik)
#end

#end

<fieldset id="header">
	<legend><!-- $!tajuk_header -->MAKLUMAT PERMOHONAN.</legend>

<!------------------------------------------- HEADER ---------------------------------------------->
		
		#if($willShowAlertSelesai=="yes")
		#if(($totalHakmilik - $totalHakmilikBorangK == 0) && $totalHakmilik != 0)
		<table width="100%" border="0" >
			<tr>
				<td align="center" bgcolor="#FF8040"><font size="4"><b>:: PERMOHONAN SELESAI ::</b></font></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>
		#end
		#end
		
     <table width="100%" border="0">
           <tr>	
             <td width="1%"></td>
             <td width="28%" style="text-transform:uppercase">Kementerian </td>
             <td width="1%">:</td>
             <td width="70%"><font color="blue">$!nama_kementerian</font></td>
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">No. Rujukan JKPTG Negeri</td>
             <td>:</td>
             <td>
             
             <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!no_fail</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >   
          
            
           #foreach ($list in $list_sub_header)
         <a href="javascript:paparPerjalananFail('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','${session.getAttribute('_portal_role')}')" >$list.STATUS</a>
            #end
           
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
            
             
             
             </td>
           </tr>
           
            <tr>
             <td></td>
             <td > <a href="javascript:bukak_header('detail_header')"><font color="blue">Maklumat Lengkap Permohonan</font></a></td>
             <td></td>
             <td></td>
           </tr>
          
          <tr> 
          <td  colspan="4">
          
          <fieldset id="detail_header" style=" display:none"  >
         <table width="100%" > 
         <tr>
             <td width="1%" ></td>
             <td width="27.5%" style="text-transform:uppercase">No. Permohonan </td>
             <td width="1%" >:</td>
             <td width="70.5%" ><font color="blue">$!no_permohonan</font></td>
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">Tarikh Terima</td>
             <td>:</td>
             <td><font color="blue">$!tarikh_terima</font></td>           
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">PROJEK Negeri</td>
             <td>:</td>
             <td><font color="blue">$!projek_negeri</font></td>           
           </tr>
           <tr>
               <td ></td>
             <td style="text-transform:uppercase">Daerah / Jajahan</td>
             <td>:</td>
             <td><font color="blue">$!nama_daerah</font></td>            
           </tr>
           <tr>
             <td ></td>
             <td  valign="top" style="text-transform:uppercase">Tujuan Pengambilan</td>
             <td valign="top">:</td>
             <td valign="top"><font color="blue">$!tujuan</font></td>            
           </tr>          
           <tr>
           <td ></td>
           <td style="text-transform:uppercase">Agensi</td>
             <td>:</td>
             <td><font color="blue">$!nama_agensi</font></td>
           </tr>           
           <tr>
             <td ></td>
           <td style="text-transform:uppercase">Tarikh Dikehendaki</td>
             <td>:</td>
             <td><font color="blue">$!tarikh_kehendaki</font></td>
           </tr>
           <tr>
             <td ></td>
           <td style="text-transform:uppercase">No. Rujukan Kementerian</td>
             <td>:</td>
             <td><font color="blue">$!no_rujukan_surat</font></td>
           </tr>         
           <tr>
             <td ></td>
            <td style="text-transform:uppercase">No. Rujukan PTD</td>
             <td>:</td>
             <td><font color="blue">$!no_rujukan_ptd</font></td>
           </tr>           
           <tr>
             <td ></td>
            <td style="text-transform:uppercase">No. Rujukan PTG</td>
             <td>:</td>
             <td><font color="blue">$!no_rujukan_ptg</font></td>
           </tr>
           <!--           
           <tr>
             <td ></td>
            <td style="text-transform:uppercase">No. Rujukan JKPTG Negeri</td>
             <td>:</td>
             <td>$!no_rujukan_upt</td>
           </tr> 
           -->          
           <tr>
             <td ></td>
           <td style="text-transform:uppercase">Status Permohonan</td>
             <td>:</td>
             <td><font color="blue">$!keterangan</font></td>
           </tr>
          </table>
          </fieldset>
          </td>
          </tr>
          
          
 	 </table>
     </fieldset>
     
     <input type="hidden" name="ScreenLocation" id="ScreenLocation"  >
     <input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" >
     <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
     <input type="hidden" name="id_fail" id="id_fail" value="$id_fail" >
     <input type="hidden" name="id_suburusanstatus" id="id_suburusanstatus" value="$!id_suburusanstatus" >
     <input type="hidden" name="id_suburusanstatusfail" id="id_suburusanstatusfail" value="$!id_suburusanstatusfail" >
     
     <!-- ADDED BY ELLY 15 JUNE 2010 -->
     <input type="hidden" name="id_status" id="id_status" value="$!id_status" >
     
     <script>
	 function bukak_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		//window.location.hash=id;
        //goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
	 </script>
<!------------------------------------------- END HEADER ---------------------------------------------->  
#parse("app/ppt/frmPPTHeader_script.jsp")