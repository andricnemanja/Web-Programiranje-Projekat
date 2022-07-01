const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const SportFacility = {template: '<sportFacility></sportFacility>'}
const SportFacilityCreate = {template: '<sportFacilityCreate></sportFacilityCreate>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login},
	    { path: '/registration', component: Registration},
	    { path: '/sportFacility', component : SportFacility},
	    { path: '/sportFacilityCreate', component : SportFacilityCreate},
	  
	   
	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

