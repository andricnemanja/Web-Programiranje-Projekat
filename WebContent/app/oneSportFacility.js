Vue.component("oneSportFacility", {
	data: function () {
		    return {
				selectedFacility:{},
				comments:{},
				isMembershipValid:false,
				todayDateInMiliseconds:0,
				workoutsForFacility:[],
				today:"",
				workoutDTO:{checkInDateTime:null, workoutID:null},
				selectedWorkout:{},
				canUserLeaveComment:false,
				commentDTO:{facilityName:"", text:"", rating:1},

				url: 'https://tile.thunderforest.com/atlas/{z}/{x}/{y}.png?apikey=14681ea1598f4acba24e168748f298ef',
				attribution:
				  '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
				zoom: 16,
				center: [47.413220, -1.219482],
				marker: L.latLng(47.413220, -1.219482),
				bounds: null

		    }
	},
	 template: ` 
	 <div class="container-fluid h-100 selected-sport-facility">
		<div class="row h-100 justify-content-center facility-row">
			<div class="col h-100 text-center facility-div">
				<img class="facility-image" v-bind:src="selectedFacility.imageName"></img>
				<p class="average-rating"><img src="/FatPass/images/stars/oneStar.png"/> {{selectedFacility.averageRating}}</p>
				<h4 class="facility-name">{{selectedFacility.name}}</h4>
				<p class="facility-address">{{selectedFacility.location.streetName}} {{selectedFacility.location.houseNumber}}, {{selectedFacility.location.city}}</p>
				<hr>
				<p class="facility-type">{{selectedFacility.facilityType}}</p>

				<button type="button" class="btn btn-primary my-4" data-bs-toggle="collapse" data-bs-target="#createWorkout" :disabled='!isMembershipValid'>Zakaži trening</button>
				<p class="membership-not-valid" v-if='!isMembershipValid'>🛈 Morate uplatiti članarinu</p>

				<div id="createWorkout" class="collapse">
					<input type="date" class="date" :min="workoutDTO.checkInDateTime" v-model="workoutDTO.checkInDateTime">
					<br/>
					<select v-model="workoutDTO.workoutID">
						<option v-for="workout in workoutsForFacility" :value="workout.id">{{workout.name}}</option>
					</select>
					<br/>
					<button type="button" class="btn btn-link my-4" v-on:click="createWorkout()">Potvrdi</button>
				</div>

				<h3>Šta korisnici kažu o ovom objektu:</h3>

				<div class="row justify-content-center">
					<div class="col-3 text-center comment-div" v-for="com in comments">
						<p><b>{{com.customer.firstName}} {{com.customer.lastName}}</b></p>
						<img v-bind:src="'/FatPass/images/stars/' + com.rating + '.png'" />
						<p>{{com.commentText}}</p>
					</div>
				</div>

				<div clas="new-comment-div" v-if="canUserLeaveComment">
					<button type="button" class="btn btn-primary my-4" data-bs-toggle="collapse" data-bs-target="#newComment">Ostavi komentar</button>
					<div id="newComment" class="collapse">
						<textarea name="new-comment-text" cols="30" rows="5" v-model="commentDTO.text"></textarea>
						<br/>
						<label>Ocena: </label>
						<select v-model="commentDTO.rating">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						<br/>
						<button type="button" class="btn btn-link my-4" v-on:click="saveComment()">Potvrdi</button>
					</div>
				</div>

				<h3 class="workout-schedule">Raspored treninga:</h3>

				<div class="facility-workouts text-start">
					<div class="col-12 workout-div" v-for="workout in workoutsForFacility">
						<div class="row align-items-center">
							<div class="col">
								<img v-bind:src="'/FatPass/images/workouts/' + workout.imageName + ''"/>
							</div>
							<div class="col text-center">
								<h5>{{workout.name}}</h5>
								<p>{{workout.description}}</p>
							</div>
							<div class="col text-center">
								<p>{{workout.additionalPayment | additionalPaymentFormat()}}</p>
							</div>
						</div>
					</div>
				</div>


			</div>
			<div class="col mh-100 map-div">
				<l-map
				style="height: 1000px; width: auto"
				:zoom="zoom"
				:center="center"
				@update:zoom="zoomUpdated"
				@update:center="centerUpdated"
				@update:bounds="boundsUpdated">

					<l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
					<l-marker :lat-lng="marker"></l-marker>

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
		createWorkout: function(){
			axios.post('rest/workoutHistory/createWorkout', this.workoutDTO)

			this.$router.push("/sportFacility");
		},
		selectWorkout: function(id){
			this.workoutDTO.workoutID = id;
		},
		saveComment: async function(){
			this.commentDTO.facilityName = this.selectedFacility.name;
			await axios.post('rest/comments/saveComment', this.commentDTO);

			await axios.get('rest/comments/' + this.selectedFacility.name)
				.then(response => (this.comments = response.data));

			await axios.get('rest/comments/canUserLeaveComment/' + this.selectedFacility.name)
				.then(response => (this.canUserLeaveComment = response.data));
		},


		zoomUpdated (zoom) {
			this.zoom = zoom;
		},
		centerUpdated (center) {
			this.center = center;
		},
		boundsUpdated (bounds) {
			this.bounds = bounds;
		}
	},
	mounted() {
		this.selectedFacility = this.$route.params.selectedFacility;
		
		if(!this.selectedFacility){
			this.$router.push("/sportFacility");
		}

		axios.get('rest/comments/' + this.selectedFacility.name)
			.then(response => (this.comments = response.data));

		axios.get('rest/membership/isMembershipValid')
			.then(response => (this.isMembershipValid = response.data));

		axios.get('rest/workoutHistory/getWorkoutsForFacility/' + this.selectedFacility.name)
			.then(response => (this.workoutsForFacility = response.data));

		axios.get('rest/comments/canUserLeaveComment/' + this.selectedFacility.name)
			.then(response => (this.canUserLeaveComment = response.data));

		this.todayDateInMiliseconds = Date.now();
		this.today = new Date();
		let dd = String(this.today.getDate()).padStart(2, '0');
		let mm = String(this.today.getMonth() + 1).padStart(2, '0'); 
		let yyyy = this.today.getFullYear();

		this.checkInDateTime = yyyy + '-' + mm + '-' + dd;

		this.center = [this.selectedFacility.location.latitude, this.selectedFacility.location.longitude];
		this.marker = L.latLng(this.selectedFacility.location.latitude, this.selectedFacility.location.longitude);


		

		document.body.style.background = "linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%)";

	},
	components: {
		'l-map': window.Vue2Leaflet.LMap,
		'l-tile-layer': window.Vue2Leaflet.LTileLayer,
		'l-marker': window.Vue2Leaflet.LMarker,
	  },
	filters:{
		additionalPaymentFormat(value){
			if(value == 0)
				return "Nema doplate";
			return value + " RSD";
		}
	}
});