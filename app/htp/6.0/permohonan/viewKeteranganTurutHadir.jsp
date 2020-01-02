

<div class="viewKeterangan" >
<table class="tableEditor classFade" width="100%"  >
<tr><td colspan="2">

      
        <h4>Keterangan Oleh : <span class="blue"><span class="">$NAMA</span></span></h4>
<textarea id="KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN" name="KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$KETERANGAN</textarea>	
<div id="dummyDivResetupKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN" style="display:none;" >$KETERANGAN</div>  
<div id="timer_KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN" align="right" ></div>    

		<script type="text/javascript">
		
		$jquery(document).ready(function () {
			$jquery('#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN').wysihtml5({ 
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
			fckeditor_word_count(document.getElementById("KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"),"wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN");								
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "none";
			document.getElementById('infobuttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "";
			$jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"),"wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN");	
			getTimeAutoSave("timer_KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN","","BLUR","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "";
			document.getElementById('infobuttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "none";
			$jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"),"wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN");	
			getTimeAutoSave("timer_KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN","","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "none";
			document.getElementById('infobuttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "";			
			var iframeheight = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"),"wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN");			
		}
		
		$jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.on("load", function() {
		  fckeditor_word_count(document.getElementById("KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"),"wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN");
		  $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN'); 
		
		</script>
          <div id="infobuttonKeteranganTurutHadir$ID_BIKEHADIRAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td>
</tr>  
<tr >
<td align="left"  >
</td>
<td align="right">
<div id="wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN"></div>
</td>
</tr>

<tr>
<td align="right" colspan="2" style="border-bottom: 1px  dotted #000;" >

</td>
</tr> 

<tr><td colspan="2">        
        <h4>Nota Pegawai : </h4>
<textarea id="NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN" name="NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"  placeholder="Masukkan Keterangan..." style="width:100%;" spellcheck="false" >$NOTA_PEGAWAI</textarea>	
<div id="dummyDivResetupNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN" style="display:none;" >$NOTA_PEGAWAI</div>  
<div id="timer_NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN" align="right" ></div>  
        
		<script type="text/javascript">
		
		$jquery(document).ready(function () {
			$jquery('#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN').wysihtml5({ 
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
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"),"wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN");								
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_BIKEHADIRAN').style.display = "";
			$jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";			
		}
		
		var resizeIframeBlur = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"),"wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN");
			getTimeAutoSave("timer_NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN","","BLUR","$ID_BIKEHADIRAN");	
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "";
			document.getElementById('infobuttonNotaPegawai$ID_BIKEHADIRAN').style.display = "none";
			$jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";										
		}		
		
		var resizeIframeKeyUp = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"),"wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN");	
			getTimeAutoSave("timer_NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN","","KEYUP","$ID_BIKEHADIRAN");
			document.getElementById('buttonKeteranganTurutHadir$ID_BIKEHADIRAN').style.display = "none";
			document.getElementById('infobuttonNotaPegawai$ID_BIKEHADIRAN').style.display = "";			
			var iframeheight = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height;
			var scrollheight = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight - 1;
			if(parseInt(scrollheight) > parseInt(iframeheight))
			{
				$jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";
			}								
		}
		
		var wordCount = function() {
			fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"),"wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN");			
		}
		
		$jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.on("load", function() {
		  	
		  fckeditor_word_count(document.getElementById("NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"),"wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN");
		  $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.iframe.style.height = $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.scrollHeight + "px";	
		  $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("keyup", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("keydown", resizeIframeKeyUp , false);
		  $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("blur", resizeIframeBlur, false);
		  $jquery("#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN").data("wysihtml5").editor.composer.element.addEventListener("focus", resizeIframe, false);		  	 
		});	
		
		reAssignFieldEditorContent('NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN'); 
		
		</script>
        
        
        
        <div id="infobuttonNotaPegawai$ID_BIKEHADIRAN" style="display:none"><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keterangan'</font> akan dipaparkan.</i></div>
</td></tr>

<tr  >
<td align="left" >

</td>
<td align="right">
<div id="wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN"></div>
</td>
</tr>


<tr id="buttonKeteranganTurutHadir$ID_BIKEHADIRAN" >
<td align="left" colspan="2" style="border-top: 1px dotted #000;" >
<input type="button" id="cmdSimpanKeterangan$ID_BIKEHADIRAN" name="cmdSimpanKeterangan$ID_BIKEHADIRAN" value="Simpan Keterangan" onClick="doDivAjaxCall$formname('tdViewKeteranganTurutHadir$ID_BIKEHADIRAN','simpanTurutHadirKeterangan','NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKeterangan$ID_BIKEHADIRAN" name="cmdSimpanKeterangan$ID_BIKEHADIRAN" value="Tutup" onClick="doDivAjaxCall$formname('tdViewKeteranganTurutHadir$ID_BIKEHADIRAN','tutupTurutHadirKeterangan','NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanReset$ID_BIKEHADIRAN" name="cmdSimpanReset$ID_BIKEHADIRAN" value="Reset" onClick="doDivAjaxCall$formname('tdViewKeteranganTurutHadir$ID_BIKEHADIRAN','showKeterangan','NAMA=$NAMA&ID_BIKEHADIRAN=$ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation())" >
<input type="button" id="cmdSimpanKosong$ID_BIKEHADIRAN" name="cmdSimpanKosong$ID_BIKEHADIRAN" value="Kosongkan" onClick="$jquery('#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN').data('wysihtml5').editor.setValue('');$jquery('#NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN').data('wysihtml5').editor.setValue('');$jquery('#KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN').data('wysihtml5').editor.focus();fckeditor_word_zero(document.getElementById('KETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN'),'wordKETERANGAN_TURUTHADIR_$ID_BIKEHADIRAN');fckeditor_word_zero(document.getElementById('NOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN'),'wordNOTA_PEGAWAI_TURUTHADIR_$ID_BIKEHADIRAN');" >

</td>
</tr>

</table>
</div>

#if($div != "")
 <script>
 $jquery(document).ready(function () {
    $jquery('#'+'$div').scrollView();    
 });	 
 </script>    
#end

<script>
var flagDisable = document.getElementById("flagDisable").value;
if(flagDisable == "Y")
{
	hideByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeteranganTurutHadir$ID_OBPERMOHONAN");	
}
</script>
























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
	disableByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("buttonKeteranganTurutHadir$ID_BIKEHADIRAN");
}
</script>


