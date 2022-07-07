Vue.component("navbar", {
	data: function () {
		    return {
				page:"",
				currentUser:{}
		    }
	},
	 template: ` 
	 <nav class="navbar navbar-expand-sm navbar-dark">
		<div class="container-fluid">	 
			<a class="navbar-brand text-center" href="#/">
				<img class="logo" src="/FatPass/images/logoWhite.png">
			</a>	 
		  <ul class="navbar-nav me-auto">
			<li class="nav-item">
			  <a v-bind:class="['nav-link', (page == '#/sportFacility' ? 'active' : '')]" href="#/sportFacility">Objekti</a>
			</li>
		   <li class="nav-item">
			  <a v-bind:class="['nav-link', (page == '#/workoutHistory' ? 'active' : '')]" href="#/workoutHistory">Treninzi</a>
			</li>
			<li v-if="currentUser.userType == 'CUSTOMER'" class="nav-item">
			  <a class="nav-link" href="#/membership">Članarina</a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" href="#">Profil</a>
			</li>
		  </ul>
		</div>
	  </nav>
    	`,
	
	methods: {
		filterName : function() {
			if(this.nameSearhField == ""){
				this.facilities = {... this.backupFacilities};
			}
			else{
				this.facilities = this.facilities.filter((f) => f.name.toLowerCase().includes(this.nameSearhField.toLowerCase()));
			}
		}
	},
	mounted() {
		page = window.location.hash;
		axios.get('rest/currentUser/')
			.then(response => this.currentUser = response.data);
	}
});