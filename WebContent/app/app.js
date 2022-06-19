const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const SportFacility = {template: '<sportFacility></sportFacility>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login},
	    { path: '/registration', component: Registration},
	    { path: '/sportFacility', component : SportFacility}
	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

