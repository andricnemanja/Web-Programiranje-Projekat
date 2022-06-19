const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const SportFasility = {template: '<sportFacility></sportFacility>'}


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login},
	    { path: '/registration', component: Registration},
	    { path: '/sportFacility', component : SportFasility}
	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

