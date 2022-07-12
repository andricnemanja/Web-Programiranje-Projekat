Vue.component("login", {
	data: function () {
		    return {
				newCustomer:{firstName: "null", lastName:"null", username:null, gender:"MALE", dateOfBirth:"", email:"", password:null},
				error:false,
				currentUser:{}
		    }
	},
	template: ` 
	<div class="login-box">
		<img class="logo" src="images/logoE592A4.png">
			<input type="text" v-model="newCustomer.username" name="username" placeholder="Korisničko ime">
			<input type="password" v-model="newCustomer.password" name="password" placeholder="Lozinka">
			<button v-on:click="login()">Prijavi se</button>
			<p>Nemas nalog? <a href="#/registration">Registruj se</a></p>
			<p>ili</p>
			<p>Pogledaj <a href="#/sportFacility">naše objekte</a></p>
			<p id="error" v-bind:hidden="error==false">Pogrešno korisničko ime ili lozinka</p>
	</div>
`
	, 
	methods : {
		login : function() {
			axios
				.post('rest/login', this.newCustomer)
				.then(response => {
					if(!response.data){
						this.error = true;
						this.newCustomer.password = null;
						return;
					}
					this.newCustomer = response.data;

					window.location.href = "#/sportFacility";
					
					

				})
		} 
	},
	async mounted () {
		document.body.style.background = "url(\"images/Background.jpg\")";
		document.body.style.backgroundSize = "cover";

		await axios.get('rest/currentUser/')
			.then(response => this.currentUser = response.data);

		if(this.currentUser != ""){
			this.$router.push("/sportFacility");
		}

    },
	beforeCreate() {
		this.$nextTick(() => {
		  document.querySelector('body').style.background = "url(\"images/Background.jpg\")";
		})
	  },
	beforeDestroy() {
		document.querySelector('body').style.background = "";
	}
});