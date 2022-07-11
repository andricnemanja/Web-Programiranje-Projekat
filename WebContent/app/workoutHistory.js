Vue.component("workoutHistory", {
	data: function () {
		    return {
				workoutHistory: {},
				workoutHistoryGroup: {},
				workoutHistoryPersonal: {},
				currentUser: {},
				todayDateInMiliseconds:null,
				searchDTO:{facilityName:"", minPrice:-1, maxPrice:-1, withoutAdditionalPayment:false, facilityType:"NULL", workoutType:"NULL", sortingStrategy:"NULL", fromDate:null, toDate:null},
				minPrice:"",
				maxPrice:""
		    }
	},
	template: ` 
	<div class="container workout-history-wrapper">
		<h3 class = "title">Vaši treninzi:</h3>
	
		<ul class="nav nav-pills" v-bind:hidden="currentUser.userType != 'COACH'">
			<li class="nav-item">
			<a class="nav-link active" data-bs-toggle="pill" href="#group">Grupni</a>
			</li>
			<li class="nav-item">
			<a class="nav-link" data-bs-toggle="pill" href="#personal">Personalni</a>
			</li>
		</ul>
		<div class="search-fields">
			<input class="name-input" v-model="searchDTO.facilityName" v-on:input="search()" type="text" placeholder="Pretraži po imenu objekta">
			<input class="min-price-input" v-model="minPrice" v-on:input="search()" type="text" placeholder="min cena">
			<input class="max-price-input" v-model="maxPrice" v-on:input="search()" type="text" placeholder="max cena">
			<input type="checkbox" id="additionalPayment" v-model="searchDTO.withoutAdditionalPayment" v-on:change="search()">
            <label for="additionalPayment">Bez doplate</label>

			<input type="text" onfocus="(this.type='date')" class="from-date" placeholder="Od" v-model="searchDTO.fromDate" v-on:change="search()" >
			<input type="text" onfocus="(this.type='date')" class="to-date" placeholder="Do" v-model="searchDTO.toDate" v-on:change="search()"> 

			<select v-model="searchDTO.workoutType" v-on:change="search()">
				<option value="NULL">Tip treninga</option>
				<option value="PERSONAL">Personalni</option>
				<option value="GROUP">Grupni</option>
				<option value="GYM">Teretana</option>
			</select>


			<select v-model="searchDTO.facilityType" v-on:change="search()">
				<option value="NULL">Discipline</option>
				<option value="GYM">Teretane</option>
				<option value="DANCE_STUDIO">Plesni studio</option>
				<option value="POOL">Bazeni</option>
			</select>

			<select class="sort" v-model="searchDTO.sortingStrategy" v-on:change="search()">
				<option value="NULL">Sortiranje</option>
				<option value="NAME_ASC">Naziv(rastuće)</option>
				<option value="NAME_DESC">Naziv(opadajuće)</option>
				<option value="ADDITIONAL_PAYMENT_ASC">Doplata(rastuće)</option>
				<option value="ADDITIONAL_PAYMENT_DESC">Doplata(opadajuće)</option>
				<option value="DATE_ASC">Datum(rastuće)</option>
				<option value="DATE_DESC">Datum(opadajuće)</option>
			</select>
		</div>

		<div class="customer-workouts" v-bind:hidden="currentUser.userType != 'CUSTOMER'">
			<table class="table table-hover">
				<thead>
				<tr>	
					<th>Naziv treninga</th>
					<th>Objekat</th>
					<th>Datum</th>
					<th>Doplata</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="workout in workoutHistory">
					<td>{{workout.workout.name}}</td>
					<td>{{workout.workout.sportFacility.name}}</td>
					<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
					<td>{{workout.workout.additionalPayment | additionalPaymentFormat()}}</td>
				</tr>
				</tbody>
			</table>
		</div>

		<div class="coach-workouts" v-bind:hidden="currentUser.userType != 'COACH'">

		
		<div class="tab-content">
			<div class="tab-pane container active" id="group">
				<table class="table table-hover">
					<thead>
					<tr>
						<th>Naziv treninga</th>
						<th>Objekat</th>
						<th>Datum</th>
						<th>Doplata</th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="workout in workoutHistoryGroup">
						<td>{{workout.workout.name}}</td>
						<td>{{workout.workout.sportFacility.name}}</td>
						<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
						<td>{{workout.workout.additionalPayment | additionalPaymentFormat()}}</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-pane container fade" id="personal">
			<table class="table table-hover">
					<thead>
					<tr>
						<th>Naziv treninga</th>
						<th>Objekat</th>
						<th>Datum</th>
						<th>Doplata</th>
						<th></th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="workout in workoutHistoryPersonal">
						<td>{{workout.workout.name}}</td>
						<td>{{workout.workout.sportFacility.name}}</td>
						<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
						<td>{{workout.workout.additionalPayment | additionalPaymentFormat()}}</td>
						<td><button type="button" class="btn btn-outline-secondary" v-on:click="cancelWorkout(workout)" :disabled = "((workout.checkInDateTime - todayDateInMiliseconds) < 172800000) ? true : false">Otkaži</button></td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	
	</div>
	</div>
    	`,
	
	methods: {
		cancelWorkout : function(workout){
			this.workoutHistoryPersonal.splice(this.workoutHistoryPersonal.findIndex(a => a.id === workout.id) , 1);
			axios.get('rest/workoutHistory/deleteWorkout/' + workout.id);
		},
		search: function(){
			if(isNaN(this.minPrice) || this.minPrice == ""){
				this.searchDTO.minPrice = -1;
			}
			else{
				this.searchDTO.minPrice = this.minPrice;
			}
			if(isNaN(this.maxPrice) || this.maxPrice == ""){
				this.searchDTO.maxPrice = -1;
			}
			else{
				this.searchDTO.maxPrice = this.maxPrice;
			}

			if(this.currentUser.userType == "CUSTOMER"){
				axios.post('rest/workoutHistory/searchCustomerWorkouts', this.searchDTO)
					.then(response => this.workoutHistory = response.data);
			}	
			else if(this.currentUser.userType == "COACH"){
				axios.post('rest/workoutHistory/searchCoachGroupWorkout', this.searchDTO)
					.then(response => this.workoutHistoryGroup = response.data);
					
				axios.post('rest/workoutHistory/searchCoachPersonalWorkout', this.searchDTO)
					.then(response => this.workoutHistoryPersonal = response.data);
			}		
		}
	},
	async mounted() {

		await axios.get('rest/currentUser/')
			.then(response => this.currentUser = response.data);

		if(this.currentUser.userType == "CUSTOMER"){
			await axios.get('rest/workoutHistory/')
				.then(response => this.workoutHistory = response.data);
		}	
		else if(this.currentUser.userType == "COACH"){
			await axios.get('rest/workoutHistory/coachGroupWorkoutHistory/')
				.then(response => this.workoutHistoryGroup = response.data);
				
			await axios.get('rest/workoutHistory/coachPersonalWorkoutHistory/')
				.then(response => this.workoutHistoryPersonal = response.data);
		}		
			
		todayDateInMiliseconds = Date.now();
		

	},

	filters:{
		dateFormat: function(value, format){
			var parsed = moment(value);
			return parsed.format(format);
		},
		additionalPaymentFormat(value){
			if(value == 0)
				return "Nema doplate";
			return value + " RSD";
		}
	}
});