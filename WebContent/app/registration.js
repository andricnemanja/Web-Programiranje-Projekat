Vue.component("registration", {
	data: function () {
		    return {
		      products: null
		    }
	},
	template: ` 
<div class="registration-box">
	<img class="logo" src="images/logoE592A4.png">
	<form id="forma">

			<input type="text" name="firstName" placeholder="Ime">
			<input type="text" name="lastName" placeholder="Prezime">
			<input type="text" name="username" placeholder="Korisničko ime">
			<br>
			<label>Žena</label>
			<input type="radio" name="gender" value="FEMALE">
			<label>Muškarac</label>
			<input type="radio" name="gender" value="MALE">
			<input type="date" name="dateOfBirth">
			<input type="password" name="password" placeholder="Lozinka">
			<input type="email" name="email" placeholder="E-mail">
			<input type="submit" v:onclick = "editStudent(s)" value="Registruj se">

		<p id="error" hidden="true"></p>
		<p id="success" hidden="true"></p>
	</form>
</div>	  
`
	, 
	methods : {
		addToCart : function (product) {
			axios
			.post('rest/proizvodi/add', {"id":''+product.id, "count":parseInt(product.count)})
			.then(response => (toast('Product ' + product.name + " added to the Shopping Cart")))
		}
	},
	mounted () {
		document.body.style.background = "url(\"images/RegistrationBackground.jpg\")"
        axios
          .get('rest/proizvodi/getJustProducts')
          .then(response => (this.products = response.data))
    },
});