Vue.component("shopping-cart", {
	data: function () {
		    return {
		      sc: null,
		      total: 0
		    }
	},
	template: ` 
	
	
	<body>
		<div id="webShop">
		<h3>Primer WebShop aplikacije u Vue.js tehnologiji</h3>
			  <router-view></router-view>
		</div>
		
		<div>
		Proizvodi u korpi:
		<table border="1">
		<tr bgcolor="lightgrey">
			<th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th></tr>
			<tr v-for="i in sc">
			<td> {{i.product.name}}</td>
			<td> {{i.product.price}}</td>
			<td> {{i.count}} </td>
			<td> {{i.total}} </td>
			</tr>
		</table>
		<br /> 
		<button v-on:click="clearSc" >Obriši korpu</button>
		<p>
		Ukupno: {{total}} dinara.
		</p>
		<p>
			<a href="#/">Proizvodi</a>
		</p>
	
	</div>		  
		
		<script src="js/vue.js"></script>
		<script src="js/vue-router.js"></script>
		<script src="js/axios.js"></script>
		<script src="js/jquery.min.js"></script>
		<script src="js/toast.js"></script>
		<script src="app/webShop.js"></script>
		<script src="app/shoppingCart.js"></script>
		<script src="app/app.js"></script>

	</body>
	
	

`
	, 
	methods : {
		init : function() {
			this.sc = {};
			this.total = 0.0;
		}, 
		clearSc : function () {
			if (confirm('Da li ste sigurni?') == true) {
				axios
		          .post('rest/proizvodi/clearSc')
		          .then(response => (this.init()))
			}
		} 
	},
	mounted () {
        axios
          .get('rest/proizvodi/getJustSc')
          .then(response => (this.sc = response.data));
        axios
        .get('rest/proizvodi/getTotal')
        .then(response => (this.total = response.data));
    }
});