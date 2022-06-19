Vue.component("registration", {
	data: function () {
		    return {
		      newCustomer:{firstName: null, lastName:null, username:null, gender:null, dateOfBirth:null, email:null, password:null}
		    }
	},
	template: ` 
<div class="registration-box">
	<img class="logo" src="images/logoE592A4.png">
		<input type="text" v-model="newCustomer.firstName" name="firstName" placeholder="Ime">
		<input type="text" v-model="newCustomer.lastName" name="lastName" placeholder="Prezime">
		<input type="text" v-model="newCustomer.username" name="username" placeholder="Korisničko ime">
		<br>
		<label>Žena</label>
		<input type="radio" v-model="newCustomer.gender" name="gender" value="FEMALE">
		<label>Muškarac</label>
		<input type="radio" v-model="newCustomer.gender" name="gender" value="MALE">
		<input type="date" v-model="newCustomer.dateOfBirth" name="dateOfBirth">
		<input type="email" v-model="newCustomer.email" name="email" placeholder="E-mail">
		<input type="password" v-model="newCustomer.password" name="password" placeholder="Lozinka">
		<button v-on:click = "saveCustomer()">Registruj se</button>

		<p id="error" hidden="true"></p>
		<p id="success" hidden="true"></p>
</div>	  
`
	, 
	methods : {
		saveCustomer : function () {
			axios
			.post('rest/customers/register', this.newCustomer)
			.then(response => (toast('Product ' + product.name + " added to the Shopping Cart")))
		}
	},
	mounted () {
		document.body.style.background = "url(\"images/RegistrationBackground.jpg\")"
    },
});