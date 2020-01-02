<table width="98%" border="0" cellspacing="2" cellpadding="2">
	
	<tr>
		<td>
#parse('app/htp/pembelian/fail/paging.jsp')

		</td>
    </tr>

  <tr>
    <td>
    	
    
<fieldset>
<legend><strong>RUNDINGAN HARGA</strong> </legend><table width="100%">
<tr>
		<td colspan="4" align="center">
			#parse ("app/htp/pembelian/fail/fileInfo.jsp")
		</td>
    </tr>
<tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
            
              <ul class="TabbedPanelsTabGroup"> 

                          
                   <li class="TabbedPanelsTab" title="Draf Pembelian" tabindex="0" onclick="javascript:setSelected(0,'detail','drafview',0)"><strong><font size="1">RUNDINGAN PEMBELIAN</font></strong></li>
                   

                
              </ul>
              
              <div class="TabbedPanelsContentGroup">
    
                <div class="TabbedPanelsContent">
				
          		#if($selectedTab == '0')
                	
                    	#parse("app/htp/pembelian/rundingan/rundinganDetail.jsp")
               		
               	#end

                </div> 
                </div>
               </div>
              </td>
             </tr>
</table>
</fieldset>
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>

	</td>
</tr>
<script>
// melalui page number
// STEP 4
function simpanRundingan(){
	if(document.${formName}.keputusan.value == ""){
		alert('Sila pilih " Keputusan " terlebih dahulu.');
  		document.${formName}.keputusan.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanRundingan");
}
function updateRundingan(){
	doAjaxCall${formName}("updateRundingan");
}
function kemaskiniRundingan(){
	doAjaxCall${formName}("kemaskiniRundingan");
}

	function textCounter(field, countfield, maxlimit) {
		if (field.value.length > maxlimit) // if too long...trim it!
			field.value = field.value.substring(0, maxlimit);
			// otherwise, update 'Baki Aksara' counter
		else 
			countfield.value = maxlimit - field.value.length;
	}
	
	function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	
	}


//paging number

function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=perakuanPembelian&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step2(idPermohonan){
	//doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=maklumatTanah&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step1(idPermohonan,idhtp){
	//doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
</script>