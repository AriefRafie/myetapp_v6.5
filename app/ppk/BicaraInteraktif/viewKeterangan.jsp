
<div class="viewKeterangan" >
<table class="tableEditor classFade" width="100%"  >
<tr><td colspan="2">

      
        <h4>Keterangan Oleh : 
        
       			<!--
        
        	   #if($pr.UMUR_INT != 0 && $pr.UMUR_INT < 18)
               
               
               #if($pr.PENJAGA != "") 
               $pr.PENJAGA 
               <br />
               adalah <b>PENJAGA</b> kepada
               <br />               
               #else
               <i><span class="red">Perhatian </span>: Sila lantik penjaga pada skrin 
                <a href="javascript:papar_header('$mainID.ID_PERMOHONAN','18','','$mainID.ID_PERMOHONANSIMATI','$mainID.TARIKH_MOHON','','$mainID.SEKSYEN','$mainID.ID_SIMATI')" > <span class="blue"><u>'Notis Perbicaraan'</u></span></a>
               </i> 
               <br />
               #end
               
               <span class="blue">$pr.NAMA</span> 
               
               #else
               $pr.NAMA 
               #end
               -->
              
               #if($ID_OBPERMOHONANMINOR != "")
               	#if($LISTPENJAGA != "")
                 <span class="blue">
                	$LISTPENJAGA 
                    </span>
                    
                    sebagai <b>PENJAGA</b> kepada  <span class="blue">
               $NAMA   
        	   </span>
                #else
                	<i><span class="red">Perhatian </span>: Sila lantik penjaga pada skrin 
                        <a href="javascript:papar_header('$mainID.ID_PERMOHONAN','18','','$mainID.ID_PERMOHONANSIMATI','$mainID.TARIKH_MOHON','','$mainID.SEKSYEN','$mainID.ID_SIMATI')" > <span class="blue"><u>'Notis Perbicaraan'</u></span></a>
                       </i> 
                       <br />
                #end    
                          
               #else
               <span class="blue">
               $NAMA        
        	   </span>
               #end
               </h4>
<textarea id="KETERANGAN_$ID_OBPERMOHONAN" name="KETERANGAN_$ID_OBPERMOHONAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$KETERANGAN</textarea>	
<div id="dummyDivResetupKETERANGAN_$ID_OBPERMOHONAN" style="display:none;" >$KETERANGAN</div>     
<div id="timer_KETERANGAN_$ID_OBPERMOHONAN" align="right" ></div>
		<script type="text/javascript">
		
		$jquery(document).ready(function () {
			$jquery('#KETERANGAN_$ID_OBPERMOHONAN').wysihtml5({ 
				"font-styles": true, // Font styling, e.g. h1, h2, etc.
				"emphasis": true, // Italics, bold, etc.
				"lists": true, // (Un)ordered lists, e.g. Bullets, Numbers.
				"html": false, // Button which allows you to edit the generated HTML.
				"link": false, // Button to insert a link.
				"image": false, // Button to insert an image.
				"color": true, // Button to change color of font
				"blockquote": true, // Blockquote	
				"size": "small"				  
			});
			
		});	
		
		var resizeIframe = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","FOCUS","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","BLUR","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_KETERANGAN_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";			
			document.getElementById('infobuttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			var iframeheight = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");			
		}
		
		$jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.on("load", function() {
		  fckeditor_word_count(document.getElementById("KETERANGAN_$ID_OBPERMOHONAN"),"wordKETERANGAN_$ID_OBPERMOHONAN");
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#KETERANGAN_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('KETERANGAN_$ID_OBPERMOHONAN'); 
		
		</script>
          <div id="infobuttonKeterangan$ID_OBPERMOHONAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td>
</tr>  
<tr >
<td align="left"  >
</td>
<td align="right">
<div id="wordKETERANGAN_$ID_OBPERMOHONAN"></div>
</td>
</tr>

<tr>
<td align="right" colspan="2" style="border-bottom: 1px  dotted #000;" >

</td>
</tr> 

<tr><td colspan="2">        
        <h4>Nota Pegawai : </h4>
<textarea id="NOTA_PEGAWAI_$ID_OBPERMOHONAN" name="NOTA_PEGAWAI_$ID_OBPERMOHONAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$NOTA_PEGAWAI</textarea>
<div id="dummyDivResetupNOTA_PEGAWAI_$ID_OBPERMOHONAN" style="display:none;" >$NOTA_PEGAWAI</div>    
<div id="timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN" align="right" ></div>
        
		<script type="text/javascript">
		
		$jquery(document).ready(function () {
			$jquery('#NOTA_PEGAWAI_$ID_OBPERMOHONAN').wysihtml5({ 
				"font-styles": true, // Font styling, e.g. h1, h2, etc.
				"emphasis": true, // Italics, bold, etc.
				"lists": true, // (Un)ordered lists, e.g. Bullets, Numbers.
				"html": false, // Button which allows you to edit the generated HTML.
				"link": false, // Button to insert a link.
				"image": false, // Button to insert an image.
				"color": true, // Button to change color of font
				"blockquote": true, // Blockquote	
				"size": "small"				  
			});
			
		});	
		
		var resizeIframe = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");								
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "";
			$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","BLUR","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "none";
			$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");	
			getTimeAutoSave("timer_NOTA_PEGAWAI_$ID_OBPERMOHONAN","$ID_OBPERMOHONAN","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeterangan$ID_OBPERMOHONAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_OBPERMOHONAN').style.display = "";			
			var iframeheight = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");			
		}
		
		$jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.on("load", function() {
		  	
		  fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_$ID_OBPERMOHONAN"),"wordNOTA_PEGAWAI_$ID_OBPERMOHONAN");
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#NOTA_PEGAWAI_$ID_OBPERMOHONAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('NOTA_PEGAWAI_$ID_OBPERMOHONAN'); 
		
		</script>
        
        
        
        <div id="infobuttonNotaPegawai$ID_OBPERMOHONAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td></tr>

<tr  >
<td align="left" >

</td>
<td align="right">
<div id="wordNOTA_PEGAWAI_$ID_OBPERMOHONAN"></div>
</td>
</tr>


<tr id="buttonKeterangan$ID_OBPERMOHONAN" >
<td align="left" colspan="2" style="border-top: 1px dotted #000;" >

<input type="button" id="cmdSimpanKeterangan$ID_OBPERMOHONAN" name="cmdSimpanKeterangan$ID_OBPERMOHONAN" value="Simpan Keterangan" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','simpanKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKeterangan$ID_OBPERMOHONAN" name="cmdSimpanKeterangan$ID_OBPERMOHONAN" value="Tutup" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','tutupKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanReset$ID_OBPERMOHONAN" name="cmdSimpanReset$ID_OBPERMOHONAN" value="Reset" onClick="doDivAjaxCall$formname('tdViewKeterangan$ID_OBPERMOHONAN','showKeterangan','ID_OBPERMOHONAN=$ID_OBPERMOHONAN&ID_OBPERMOHONAN_ASAL=$ID_OBPERMOHONAN_ASAL&NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKosong$ID_OBPERMOHONAN" name="cmdSimpanKosong$ID_OBPERMOHONAN" value="Kosongkan" onClick="$jquery('#KETERANGAN_$ID_OBPERMOHONAN').data('wysihtml5').editor.setValue('');$jquery('#NOTA_PEGAWAI_$ID_OBPERMOHONAN').data('wysihtml5').editor.setValue('');$jquery('#KETERANGAN_$ID_OBPERMOHONAN').data('wysihtml5').editor.focus();fckeditor_word_zero(document.getElementById('KETERANGAN_$ID_OBPERMOHONAN'),'wordKETERANGAN_$ID_OBPERMOHONAN');fckeditor_word_zero(document.getElementById('NOTA_PEGAWAI_$ID_OBPERMOHONAN'),'wordNOTA_PEGAWAI_$ID_OBPERMOHONAN');" >
</td>
</tr>

</table>
</div>

#if($div != "")
 <script>
 $jquery(document).ready(function () {
     //alert('x');
     //divToTop("view_keputusan");
     $jquery('#'+'$div').scrollView();
     //alert('x2');
 });	 
 </script>    
#end

<script>
var flagDisable = document.getElementById("flagDisable").value;
if(flagDisable == "Y")
{
	/*
	disableByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeterangan$ID_OBPERMOHONAN");
	*/
	
	
	hideByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeterangan$ID_OBPERMOHONAN");	
}
</script>
