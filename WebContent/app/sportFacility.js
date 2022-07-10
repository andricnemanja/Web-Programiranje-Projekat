Vue.component("sportFacility", {
	data: function () {
		    return {
		      facilities: {},
			  backupFacilities:{},
			  nameSearhField:"",
			  citySearchOption:"",
			  facilityTypeOption:"",
			  ratingOption:"",
			  searchDTO:{name:"", city:"", rating:0, facilityType:"NULL", sortingStrategy:"NULL"},


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
		 	<div class="col h-100 facilities-column">

			 	<div class="search-fields">
					<input v-model="searchDTO.name" v-on:input="search()" type="text" placeholder="Pretraži po imenu">

					<select v-model="searchDTO.city" v-on:change="search()">
						<option value="">Svi gradovi</option>
						<option value="Novi Sad">Novi Sad</option>
						<option value="Beograd">Beograd</option>
						<option value="Sabac">Šabac</option>
					</select>

					<select v-model="searchDTO.rating" v-on:change="search()">
						<option value="0">Sve ocene</option>
						<option value="1">Veće od 1</option>
						<option value="2">Veće od 2</option>
						<option value="3">Veće od 3</option>
						<option value="4">Veće od 4</option>
					</select>
					<select v-model="searchDTO.facilityType" v-on:change="search()">
						<option value="NULL">Discipline</option>
						<option value="GYM">Teretane</option>
						<option value="DANCE_STUDIO">Plesni studio</option>
						<option value="POOL">Bazeni</option>
					</select>
					<select v-model="searchDTO.sortingStrategy" v-on:change="search()">
						<option value="NULL">Sortiranje</option>
						<option value="RATING_ASC">Ocena(rastuće)</option>
						<option value="RATING_DESC">Ocena(opadajuće)</option>
						<option value="NAME_ASC">Naziv(rastuće)</option>
						<option value="NAME_DESC">Naziv(opadajuće)</option>
						<option value="LOCATION_ASC">Lokacija(rastuće)</option>
						<option value="LOCATION_DESC">Lokacija(opadajuće)</option>
					</select>
				</div>

				<div class="one-sport-facility text-center" v-for="f in facilities" v-on:click="showSelectedFacility(f)">
					<img class="facility-image" v-bind:src="f.imageName"></img>
					<p class="average-rating"><img src="/FatPass/images/stars/oneStar.png"/> {{f.averageRating}}</p>			
					<h4 class="facility-name">{{f.name}}</h4>
					<p class="facility-address">{{f.location.streetName}} {{f.location.houseNumber}}, {{f.location.city}}</p>
					<hr>
					<p class="facility-type">{{f.facilityType}}</p>
					<div class="fa-solid fa-location-dot" v-on:mouseover="zoomUpdated(17); centerUpdated([f.location.latitude, f.location.longitude])"></div>
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
					<l-marker v-for="facility in facilities" :lat-lng="getMarkerLocation(facility.location.latitude, facility.location.longitude)">
						<l-popup>
							<div class="text-center">
								<img class="popup-facility-image" v-bind:src="facility.imageName"></img>
								<h4 class="popup-facility-name" v-on:click="showSelectedFacility(facility)">{{facility.name}}</h4>
								<p class="facility-address">{{facility.location.streetName}} {{facility.location.houseNumber}}, {{facility.location.city}}</p>
							</div>
						</l-popup>
					</l-marker>
				</l-map>
			</div>
		</div>
	</div>  
    	`,
	
	methods: {
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
		},
		search: function(){
			axios.post('rest/facilities/search', this.searchDTO)
				.then(response => (this.facilities = response.data));
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
		'l-popup': window.Vue2Leaflet.LPopup
	}
});