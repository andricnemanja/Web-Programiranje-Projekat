Vue.component("sportFacility", {
	data: function () {
		    return {
		      facilities: {}
		    }
	},
	 template: ` 
	<div class="sport-facilities-wrapper">
		<div class="one-sport-facility" v-for="f in facilities">
			<img class="facility-image" v-bind:src="f.imageName"></img>
			<p class="average-rating">{{f.averageRating}}</p>
			
			<h4 class="facility-name">{{f.name}}</h4>
			<p class="facility-address">{{f.location.streetName}} {{f.location.houseNumber}} {{f.location.city}}</p>
			<hr>
			<p class="facility-type">{{f.facilityType}}</p>
		</div>
	</div>  
    	`,
	mounted() {
		axios.get('rest/facilities/')
			.then(response => (this.facilities = response.data))
		    
	},
	
	methods: {
	
	
	}
});