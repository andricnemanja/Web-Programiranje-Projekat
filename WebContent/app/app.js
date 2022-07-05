const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const SportFacility = {template: '<sportFacility></sportFacility>'}
const OneSportFacility = {template: '<oneSportFacility></oneSportFacility>'}
const WorkoutHistory = {template: '<workoutHistory></workoutHistory>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login},
	    { path: '/registration', component: Registration},
	    { path: '/sportFacility', component : SportFacility},
	    { path: '/oneSportFacility', name:"oneSportFacility", component: OneSportFacility},
	    { path: '/workoutHistory', name:"workoutHistory", component: WorkoutHistory}

	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

