const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const SportFacility = {template: '<sportFacility></sportFacility>'}
const OneSportFacility = {template: '<oneSportFacility></oneSportFacility>'}
const WorkoutHistory = {template: '<workoutHistory></workoutHistory>'}
const Membership = {template: '<membership></membership>'}
const Cart = {template: '<cart></cart>'}
const SuccessfulPurchase = {template: '<successfulPurchase></successfulPurchase>'}



const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', name:"login", component: Login},
	    { path: '/registration', name:"registration", component: Registration},
	    { path: '/sportFacility', component : SportFacility},
	    { path: '/oneSportFacility', name:"oneSportFacility", component: OneSportFacility},
	    { path: '/workoutHistory', name:"workoutHistory", component: WorkoutHistory},
	    { path: '/membership', name:"membership", component: Membership},
	    { path: '/cart', name:"cart", component: Cart},
	    { path: '/successfulPurchase', name:"successfulPurchase", component: SuccessfulPurchase}

	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

