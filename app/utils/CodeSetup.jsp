<!--<a href="?_portal_module=ekptg.test.AjaxTest" 
title="FaNcY Box TesT" onclick="Modalbox.show(this.href, {title: this.title, height:400,width: 600}); 
return false;">FaNcY Box TesT</a>


http://wiki.github.com/madrobby/scriptaculous/effectscrollto
http://www.huddletogether.com/projects/lightbox2/
http://wiki.github.com/madrobby/scriptaculous/ajax-autocompleter

<a href="#" onclick="Effect.toggle('toggle_appear', 'appear'); return false;">Show/Hide</a>


/**
window.onload = function() {
  sayHi();
}
function sayHi()  { alert("HIII");}
**/

window.onresize = function() {
  setFooter();
}

<script>
window.onload = function() {
  goTo();
}
function goTo() {
Effect.ScrollTo('myid');
}
</script>
<a href="#" onclick="Effect.ScrollTo('myid'); return false;">
Click me to scroll to the top of the article</a>


<input type=text name=myid id="myid">
-->
<p></p>
<div id="toggle_appear">
	<div>
	<table>
	#set ($counter=0)
	#set ($displayColumn=4)
	#foreach ( $x in $list_of_tables )
		#set ($counter = $counter + 1)
		#set ($mod = ($counter % ($displayColumn)))
		#if ( $mod == 1 )
		<tr>
		#end
		<td 
		onMouseOut="this.className='row1'"
		onMouseOver="this.className='selected'"
		>
		<div class="code_setup_list">
		<p>
		$counter.
		<a href="javascript:doAjaxCall${formName}('record_listing','NiceTableName=$x')">$x</a><br>
		</p>
		</div>
		</td>
		#if ( $mod == 0 )
		</tr>
		#end
		
	#end 
	</table>
	</div>
</div>


