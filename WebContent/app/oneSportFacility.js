Vue.component("oneSportFacility", {
	data: function () {
		    return {
				selectedFacility:{},
				comments:{}
		    }
	},
	 template: ` 
	 <div class="container-fluid selected-sport-facility">
		<div class="row justify-content-center facility-row">
			<div class="col text-center facility-div">
				<img class="facility-image" v-bind:src="selectedFacility.imageName"></img>
				<p class="average-rating"><img src="/FatPass/images/stars/oneStar.png"/> {{selectedFacility.averageRating}}</p>
				<h4 class="facility-name">{{selectedFacility.name}}</h4>
				<p class="facility-address">{{selectedFacility.location.streetName}} {{selectedFacility.location.houseNumber}}, {{selectedFacility.location.city}}</p>
				<hr>
				<p class="facility-type">{{selectedFacility.facilityType}}</p>

				<h3>Šta korisnici kažu o ovom objektu:</h3>

				<div class="row justify-content-center">
					<div class="col-3 text-center comment-div" v-for="com in comments">
						<p><b>{{com.customer.firstName}} {{com.customer.lastName}}</b></p>
						<img v-bind:src="'/FatPass/images/stars/' + com.rating + '.png'" />
						<p>{{com.commentText}}</p>
					</div>
				</div>


			</div>
			<div class="col map-div">

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
		}
	},
	mounted() {
		this.selectedFacility = this.$route.params.selectedFacility;
		
		if(!this.selectedFacility){
			this.$router.push("/sportFacility");
		}

		axios.get('rest/comments/' + this.selectedFacility.name)
			.then(response => (this.comments = response.data));

		document.body.style.background = "#cccccc"

	}
});