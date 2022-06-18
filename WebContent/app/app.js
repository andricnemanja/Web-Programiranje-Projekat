const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }


const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login},
	    { path: '/registration', component: Registration},
	  ]
});

var app = new Vue({
	router,
	el: '#wrapper'
});

