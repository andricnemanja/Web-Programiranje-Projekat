Vue.component("workoutHistory", {
	data: function () {
		    return {
				workoutHistory: {},
				workoutHistoryGroup: {},
				workoutHistoryPersonal: {},
				currentUser: {},
				todayDateInMiliseconds:null
		    }
	},
	 template: ` 
	 <div class="container workout-history-wrapper">
		<h3 class = "title">Vaši treninzi u prethodnih mesec dana:</h3>
		<div class="customer-workouts" v-bind:hidden="currentUser.userType != 'CUSTOMER'">
			<table class="table table-hover">
				<thead>
				<tr>	
					<th>Naziv treninga</th>
					<th>Objekat</th>
					<th>Datum</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="workout in workoutHistory">
					<td>{{workout.workout.name}}</td>
					<td>{{workout.workout.sportFacility.name}}</td>
					<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
				</tr>
				</tbody>
			</table>
		</div>

		<div class="coach-workouts" v-bind:hidden="currentUser.userType != 'COACH'">

		<ul class="nav nav-pills">
			<li class="nav-item">
			  <a class="nav-link active" data-bs-toggle="pill" href="#group">Grupni</a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" data-bs-toggle="pill" href="#personal">Personalni</a>
			</li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane container active" id="group">
				<table class="table table-hover">
					<thead>
					<tr>
						<th>Naziv treninga</th>
						<th>Objekat</th>
						<th>Datum</th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="workout in workoutHistoryGroup">
						<td>{{workout.workout.name}}</td>
						<td>{{workout.workout.sportFacility.name}}</td>
						<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
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
						<th></th>
					</tr>
					</thead>
					<tbody>
					<tr v-for="workout in workoutHistoryPersonal">
						<td>{{workout.workout.name}}</td>
						<td>{{workout.workout.sportFacility.name}}</td>
						<td>{{workout.checkInDateTime | dateFormat('DD.MM.YYYY.')}}</td>
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
			this.workoutHistoryPersonal.splice(this.workoutHistoryPersonal.findIndex(a => a.id === workout.id) , 1)
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
		}
	}
});