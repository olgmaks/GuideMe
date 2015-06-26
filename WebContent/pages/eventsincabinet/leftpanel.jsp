<script type="text/javascript">
	var myEl = document.getElementById('#test');

	myEl.addEventListener('click', function() {
		alert('Hello world');
	}, false);

	myEl.addEventListener('click', function() {
		alert('Hello world again!!!');
	}, false);
</script>

<div class="collection" style="margin-top: 5%;">
	<a href="#!" class="collection-item" id="test">All</a> <a href="#!"
		class="collection-item active">Own</a> <a href="#!"
		class="collection-item">Resident</a>
</div>