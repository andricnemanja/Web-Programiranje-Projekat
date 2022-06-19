Vue.component("login", {
	data: function () {
		    return {
				newCustomer:{firstName: "null", lastName:"null", username:null, gender:"MALE", dateOfBirth:"", email:"", password:null},
				error:false
		    }
	},
	template: ` 
	<div class="login-box">
		<img class="logo" src="images/logoE592A4.png">
			<input type="text" v-model="newCustomer.username" name="username" placeholder="Korisničko ime">
			<input type="password" v-model="newCustomer.password" name="password" placeholder="Lozinka">
			<button v-on:click="login()">Prijavi se</button>
			<p>Nemas nalog? <a href="#/registration">Registruj se</a></p>
			<p id="error" v-bind:hidden="error==false">Pogrešno korisničko ime ili lozinka</p>
			<p id="success" hidden="true"></p>
	</div>
`
	, 
	methods : {
		login : function() {
			axios
				.post('rest/customers/login', this.newCustomer)
				.then(response => {
					if(!response.data){
						this.error = true;
						this.newCustomer.password = null;
					}
					else{
						window.location.href = "#/registration";
					}

				})
		} 
	},
	mounted () {
		document.body.style.background = "url(\"images/Background.jpg\")"
    }
});