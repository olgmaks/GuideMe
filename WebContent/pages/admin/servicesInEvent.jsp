<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/materialize.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var classname = document
								.getElementsByClassName("collapsible-header tooltipped");
						var classname2 = document
								.getElementsByClassName("collection with-header");
						var price = 0;
						$(classname).click(
								function() {
									var id = $(this).attr('id');
									var str = $(this).text();

									var serviceprice = parseFloat($(this).attr(
											'price'));

									if ($('#s' + id).length) {

									} else {
										str = str.substring(0, str.length - 5);
										/* 	alert($('.' + id).length); */

										$(classname2).append(
												"<div id='s"+id+"'><li class='collection-item'>"
														+ str + "</li></div>");
										price = price + serviceprice;
									}
									$('.black-text').text('bucket');
									$('.grey-text').text(
											'Price ' + price + ' $');

								});
					});
</script>
<ul class="collapsible" data-collapsible="accordion" style="width: 45%;">
	<c:forEach items="${servicesInEvent }" var="serviceinevent">
		<li>
			<div class="collapsible-header tooltipped"
				price="${serviceinevent.getService().getPrice() }"
				id="${serviceinevent.getService().getId() }" data-position="left"
				data-delay="50"
				data-tooltip="${serviceinevent.getService().getDescription() }">${serviceinevent.getService().getName() }
				${serviceinevent.getService().getPrice() }$ <a
					class="btn-floating btn-medium waves-effect waves-light green"><i
					class="material-icons">+</i></a>
			</div>
		</li>
	</c:forEach>
</ul>
<h4 class="green-text text-darken-4">bucket</h4>
<h4 class="grey-text text-darken-4">Price</h4>
<a class="waves-effect waves-light btn">Buy</a>

<ul class="collection with-header">



</ul>
