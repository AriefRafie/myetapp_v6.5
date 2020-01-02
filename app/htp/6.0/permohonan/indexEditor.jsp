



<textarea id="txt" placeholder="Enter text ..." style="width:100%; height: 200px"></textarea>		





<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/lib/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/lib/css/prettify.css"></link>
<link rel="stylesheet" type="text/css" href="../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.css"></link>

<style type="text/css" media="screen">
	.btn.jumbo {
		font-size: 20px;
		font-weight: normal;
		padding: 14px 24px;
		margin-right: 10px;
		-webkit-border-radius: 6px;
		-moz-border-radius: 6px;
		border-radius: 6px;
	}
</style>


<script src="../bootstrap-wysihtml5-master/lib/js/wysihtml5-0.3.0.js"></script>
<script src="../bootstrap-wysihtml5-master/lib/js/jquery-1.7.2.min.js"></script>
<script src="../bootstrap-wysihtml5-master/lib/js/prettify.js"></script>
<script src="../bootstrap-wysihtml5-master/lib/js/bootstrap.min.js"></script>
<script src="../bootstrap-wysihtml5-master/src/bootstrap-wysihtml5.js" ></script>




<script>
	$(function () {
    var words = ["hello", "world", "test word", "yay", "woot", "multiple words", "blue dog", "blue cat"];

    $('#txt').wysihtml5();

    var editor = $('#txt').data("wysihtml5").editor;

    editor.on('load', function () {
        var sm = new SuggestMe();
        sm.init(this.currentView.iframe, words);
    });
	});


</script>

<script type="text/javascript" charset="utf-8">
	$(prettyPrint);
</script>

