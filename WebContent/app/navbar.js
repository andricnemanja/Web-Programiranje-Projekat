Vue.component("navbar", {
	data: function () {
		    return {
				user:{}
		    }
	},
	 template: ` 
	 <nav class="navbar navbar-expand-sm navbar-dark  ">
		<div class="container-fluid">	  
		  <ul class="navbar-nav me-auto">
			<li class="nav-item">
			  <a class="nav-link active" href="#/sportFacility">Objekti</a>
			</li>
		   <li class="nav-item">
			  <a class="nav-link" href="#">Treninzi</a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" href="#">Članarina</a>
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


	}
});