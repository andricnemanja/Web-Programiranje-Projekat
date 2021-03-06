Vue.component("registration", {
	data: function () {
		    return {
		      newCustomer:{firstName: null, lastName:null, username:null, gender:null, dateOfBirth:null, email:null, password:null, userType:"CUSTOMER"}
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

</div>	  
`
	, 
	methods : {
		saveCustomer : async function () {
			await axios.post('rest/customers/register', this.newCustomer)

			
			this.$router.push("/sportFacility");

		}
	},
	mounted () {
		document.body.style.background = "url(\"images/RegistrationBackground.jpg\")";
		document.body.style.backgroundSize = "cover";
    },
	beforeDestroy() {
		document.body.style.background = ""
	  }
});