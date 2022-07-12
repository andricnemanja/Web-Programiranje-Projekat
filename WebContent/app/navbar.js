Vue.component("navbar", {
	data: function () {
		    return {
				page:"",
				currentUser:{}
		    }
	},
	 template: ` 
	 <nav class="navbar navbar-expand-sm navbar-dark">
		<div class="container">	 
			<a class="navbar-brand text-center" href="#/">
				<img class="logo" src="/FatPass/images/logoWhite.png">
			</a>	 
		  <ul class="navbar-nav me-auto">
			<li class="nav-item" v-if="currentUser != ''">
			  <a class="nav-link active" href="#/sportFacility">Objekti</a>
			</li>
		   <li class="nav-item" v-if="currentUser != ''">
			  <a class="nav-link active" href="#/workoutHistory">Treninzi</a>
			</li>
			<li v-if="currentUser.userType == 'CUSTOMER'" class="nav-item">
			  <a class="nav-link active" href="#/membership">Članarina</a>
			</li>
		  </ul>

		  <ul class="navbar-nav ms-auto">
			<li class="nav-item" v-if="currentUser != ''">
			  <a class="nav-link active" v-on:click="logout()">Odjavi se</a>
			</li>
			<li class="nav-item" v-if="currentUser == ''">
			  <a class="nav-link active" href="#/">Prijavi se</a>
			</li>
		  </ul>
		</div>
	  </nav>
    	`,
	
	methods: {
		logout: function(){
			axios.post('rest/logout')
			this.$router.push("/");
		}
	},
	mounted() {
		page = window.location.hash;
		axios.get('rest/currentUser/')
			.then(response => this.currentUser = response.data);
	}
});