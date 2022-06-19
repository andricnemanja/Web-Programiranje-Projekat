Vue.component("login", {
	data: function () {
		    return {
		      sc: null,
		      total: 0
		    }
	},
	template: ` 
	
	
	<div class="login-box">
		<img class="logo" src="images/logoE592A4.png">
		<form id="forma">

				<input type="text" name="username" placeholder="Korisničko ime">
				<input type="password" name="password" placeholder="Lozinka">
				<input type="submit" value="Prijavi se">
				<p>Nemas nalog? <a href="#/registration">Registruj se</a></p>
				

			<p id="error" hidden="true"></p>
			<p id="success" hidden="true"></p>
		</form>
	</div>
	
	

`
	, 
	methods : {
		init : function() {
			this.sc = {};
			this.total = 0.0;
		}, 
		clearSc : function () {
			if (confirm('Da li ste sigurni?') == true) {
				axios
		          .post('rest/proizvodi/clearSc')
		          .then(response => (this.init()))
			}
		} 
	},
	mounted () {
		document.body.style.background = "url(\"images/Background.jpg\")"
      /*  axios
          .get('rest/proizvodi/getJustSc')
          .then(response => (this.sc = response.data));
        axios
        .get('rest/proizvodi/getTotal')
        .then(response => (this.total = response.data));*/
    }
});