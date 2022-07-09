Vue.component("sportFacility", {
	data: function () {
		    return {
		      facilities: {},
			  backupFacilities:{},
			  nameSearhField:"",
			  citySearchOption:"",
			  facilityTypeOption:"",
			  ratingOption:"",


			  url: 'https://tile.thunderforest.com/atlas/{z}/{x}/{y}.png?apikey=14681ea1598f4acba24e168748f298ef',
			  attribution:
				'&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
			  zoom: 10,
			  center: [44.9667, 20.0679],
			  bounds: null

		    }
	},
	 template: ` 
	 <div class="container-fluid h-100 sport-facilities-wrapper">
	 	<div class="row h-100 justify-content-center">
		 	<div class="col h-100 text-center facilities-column">

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

				<div class="one-sport-facility" v-for="f in facilities" v-on:click="showSelectedFacility(f)">
					<img class="facility-image" v-bind:src="f.imageName"></img>
					<p class="average-rating">{{f.averageRating}}</p>			
					<h4 class="facility-name">{{f.name}}</h4>
					<p class="facility-address">{{f.location.streetName}} {{f.location.houseNumber}}, {{f.location.city}}</p>
					<hr>
					<p class="facility-type">{{f.facilityType}}</p>
					<div class="fa-solid fa-location-dot" @mouseover="zoomUpdated(17); centerUpdated([f.location.latitude, f.location.longitude])" 
					@mouseleave="zoomUpdated(10); centerUpdated([44.9667, 20.0679])"></div>
				</div>
			</div>
			<div class="col h-100 text-center map-div">
				<l-map
				style="height: 1000px; width: auto"
				:zoom="zoom"
				:center="center"
				@update:zoom="zoomUpdated"
				@update:center="centerUpdated"
				@update:bounds="boundsUpdated">
					<l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
					<l-marker v-for="facility in facilities" :lat-lng="getMarkerLocation(facility.location.latitude, facility.location.longitude)"></l-marker>
				</l-map>
			</div>
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
		},
		showSelectedFacility : function(selectedFacility){
			this.$router.push({ name: "oneSportFacility", params: {selectedFacility : selectedFacility}});
		},
		zoomUpdated : function (zoom) {
			this.zoom = zoom;
		},
		centerUpdated : function(center) {
			this.center = center;
		},
		boundsUpdated : function(bounds) {
			this.bounds = bounds;
		},
		getMarkerLocation : function(lat, long){
			return L.latLng(lat, long);
		}
	
	},
	mounted() {
		axios.get('rest/facilities/')
			.then(response => (this.facilities = response.data, this.backupFacilities = response.data));
	},
	beforeCreate() {
		this.$nextTick(() => {
		  document.querySelector('body').style.background = "linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%)";
		})
	},
	beforeDestroy() {
		document.querySelector('body').style.background = "";
	},
	components: {
		'l-map': window.Vue2Leaflet.LMap,
		'l-tile-layer': window.Vue2Leaflet.LTileLayer,
		'l-marker': window.Vue2Leaflet.LMarker,
	}
});