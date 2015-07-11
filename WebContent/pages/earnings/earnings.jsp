<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<h4>Estimated Earnings</h4>

<div class="row">
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${todayEarnings }</span><br> <br>
			<span class="black-text">Today so far</span>
		</div>
	</div>
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${yesterdayEarnings }</span><br> <br>
			<span class="black-text">Yesteday</span>
		</div>
	</div>
</div>