Vue.component("sportFacility", {
	data: function () {
		    return {
		      facilities: {},
			  backupFacilities:{},
			  cities:[],
			  nameSearhField:"",
			  citySearchOption:"",
			  facilityTypeOption:"",
			  ratingOption:""
		    }
	},
	 template: ` 
	<div class="sport-facilities-wrapper">
		<input v-model="nameSearhField" v-on:input="filterNameBackend()" type="text" placeholder="Pretraži po imenu">

		<select v-model="citySearchOption" v-on:change="filterCityBackend()">
			<option value="">Svi gradovi</option>
			<option value="Novi Sad">Novi Sad</option>
			<option value="Beograd">Beograd</option>
			<option value="Sabac">Šabac</option>
		</select>

		<select v-model="ratingOption" v-on:change="filterRatingBackend()">
			<option value="">Sve ocene</option>
			<option value="6">Veće od 6</option>
			<option value="7">Veće od 7</option>
			<option value="8">Veće od 8</option>
			<option value="9">Veće od 9</option>
		</select>
		<select v-model="facilityTypeOption" v-on:change="filterTypeBackend()">
			<option value="">Discpline</option>
			<option value="GYM">Teretane</option>
			<option value="SPA">SPA</option>
			<option value="POOL">Bazeni</option>
		</select>

		<div class="one-sport-facility" v-for="f in facilities">
			<img class="facility-image" v-bind:src="f.imageName"></img>
			<p class="average-rating">{{f.averageRating}}</p>			
			<h4 class="facility-name">{{f.name}}</h4>
			<p class="facility-address">{{f.location.streetName}} {{f.location.houseNumber}}, {{f.location.city}}</p>
			<hr>
			<p class="facility-type">{{f.facilityType}}</p>
		</div>
	</div>  
    	`,
	
	methods: {
		filterName : function() {
			if(this.nameSearhField == ""){
				this.facilities = {... this.backupFacilities};
			}
			else{
				this.facilities = this.facilities.filter((f) => f.name.toLowerCase().includes(this.nameSearhField.toLowerCase()));
			}
		},
		filterNameBackend : function() {
			if(this.nameSearhField == ""){
				axios.get('rest/facilities/')
					.then(response => (this.facilities = response.data));
			}
			else{
				axios.get('rest/facilities/name/' + this.nameSearhField)
					.then(response => (this.facilities = response.data));
			}
		},
		filterCityBackend : function() {
			if(this.citySearchOption == ""){
				axios.get('rest/facilities/')
					.then(response => (this.facilities = response.data));
			}
			else{
				axios.get('rest/facilities/city/' + this.citySearchOption)
					.then(response => (this.facilities = response.data));
			}
		},
		filterTypeBackend : function() {
			if(this.facilityTypeOption == ""){
				axios.get('rest/facilities/')
					.then(response => (this.facilities = response.data));
			}
			else{
				axios.get('rest/facilities/type/' + this.facilityTypeOption)
					.then(response => (this.facilities = response.data));
			}
		},
		filterRatingBackend : function() {
			if(this.ratingOption == ""){
				axios.get('rest/facilities/')
					.then(response => (this.facilities = response.data));
			}
			else{
				axios.get('rest/facilities/rating/' + this.ratingOption)
					.then(response => (this.facilities = response.data));
			}
		} 
	
	},
	mounted() {
		axios.get('rest/facilities/')
			.then(response => (this.facilities = response.data, this.backupFacilities = response.data));

		document.body.style.background = "#cccccc"

		console.log(this.facilities);
		for(let f in this.facilities){
			this.cities.push(f.location.city);
			console.log(f.location.city);
		}

		searhField="Mega";
	}
});