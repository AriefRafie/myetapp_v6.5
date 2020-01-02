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

<!--
.kaler_biru {color: #0000FF}
-->
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
    
    #set ($tujuan=$senarai.tujuan)
    #set ($tujuan_upper=$senarai.tujuan_upper)
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
    #set ($no_ptg = $senarai.NO_RUJUKAN_PTG)
    
#end 


<fieldset id="header">
	<legend><!-- $!tajuk_header -->MAKLUMAT PERMOHONAN.</legend>

<!------------------------------------------- HEADER ---------------------------------------------->

     <table width="100%" border="0">
           
       <tr>
             <td ></td>
             <td style="text-transform:uppercase">No. Fail Permohonan</td>
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
         <a href="javascript:paparPerjalananFail('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','${session.getAttribute('myrole')}')" >$list.STATUS</a>
            #end
           
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
            
             
             
             </td>
       </tr>
       
       #if($no_rujukan_ptg!="")   
       <tr>
             <td ></td>
            <td style="text-transform:uppercase">No. Rujukan PTG</td>
             <td>:</td>
             <td class="kaler_biru">$!no_rujukan_ptg</td>
           </tr>
       #end 
       
       #if($no_rujukan_ptd!="")
       <tr>
           	<td ></td>
            <td style="text-transform:uppercase">No. Rujukan PTD</td>
             <td>:</td>
             <td class="kaler_biru">$!no_rujukan_ptd</td>
       </tr>        
       #end 
           
       #if($no_rujukan_upt!="")   
       <tr>
             <td ></td>
            <td style="text-transform:uppercase">No. Rujukan UPT</td>
             <td>:</td>
             <td class="kaler_biru">$!no_rujukan_upt</td>
           </tr>
       #end  
           
       <tr>	
             <td width="1%"></td>
             <td width="28%" style="text-transform:uppercase">Kementerian </td>
             <td width="1%">:</td>
             <td width="70%"><span class="kaler_biru">$!nama_kementerian</span></td>
       </tr>    
            <tr>
             <td></td>
             <td > <a href="javascript:bukak_header('detail_header')"><font color="blue">Maklumat Lengkap Permohonan</font></a></td>
             <td></td>
             <td><span class="kaler_biru"></span></td>
       </tr>
          
          <tr> 
          <td  colspan="4">
          
          <fieldset id="detail_header"  style=" display:none" >
         <table width="100%" > 
         <tr>
             <td width="1%" ></td>
             <td width="27.5%" style="text-transform:uppercase">No. Permohonan </td>
             <td width="1%" >:</td>
             <td width="70.5%" class="kaler_biru" >$!no_permohonan</td>
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">Tarikh Terima</td>
             <td>:</td>
             <td class="kaler_biru">$!tarikh_terima</td>           
           </tr>
           <tr>
             <td ></td>
             <td style="text-transform:uppercase">PROJEK Negeri</td>
             <td>:</td>
             <td class="kaler_biru">$!projek_negeri</td>           
           </tr>
           <tr>
               <td ></td>
             <td style="text-transform:uppercase">Daerah / Jajahan</td>
             <td>:</td>
             <td class="kaler_biru">$!nama_daerah</td>            
           </tr>
           <tr>
             <td ></td>
             <td  valign="top" style="text-transform:uppercase">Tujuan Pengambilan</td>
             <td valign="top">:</td>
             <td valign="top" class="kaler_biru"    >$!tujuan_upper</td>            
           </tr>          
           <tr>
           <td ></td>
           <td style="text-transform:uppercase">Agensi</td>
             <td>:</td>
             <td class="kaler_biru">$!nama_agensi</td>
           </tr>           
           <tr>
             <td ></td>
           <td style="text-transform:uppercase">Tarikh Dikehendaki</td>
             <td>:</td>
             <td class="kaler_biru">$!tarikh_kehendaki</td>
           </tr>
           <tr>
             <td ></td>
           <td style="text-transform:uppercase">No. Rujukan Kementerian</td>
             <td>:</td>
             <td class="kaler_biru">$!no_rujukan_surat</td>
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
             <td class="kaler_biru">$!keterangan</td>
           </tr>
          </table>
          </fieldset>          </td>
          </tr>
 	 </table>
</fieldset>
     <input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" >
     <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
     <input type="hidden" name="id_fail" id="id_fail" value="$id_fail" >
     <input type="hidden" name="id_suburusanstatus" id="id_suburusanstatus" value="$!id_suburusanstatus" >
     <input type="hidden" name="id_suburusanstatusfail" id="id_suburusanstatusfail" value="$!id_suburusanstatusfail" >
     
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