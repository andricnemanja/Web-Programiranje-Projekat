Vue.component("successfulPurchase", {
	data: function () {
		    return {
				membership: {},
		    }
	},
	template: ` 
	<div class="container py-5 successfulPurchase justify-content-center">

            <div class="row justify-content-center">
                <img src="/FatPass/images/successfulPurchase.png">
                <h1 class="title pb-4 text-center">Uspešna kupovina</h1>
                <h5 class="pb-3 text-center ">Detalji vaše porudžbine:</h5>
                <div class="col-3">
                    <div class="details text-left">
                        <ul class="list-unstyled my-2 text-small text-left">
                            <li class="mb-3"><span class="list-item">Cena:&nbsp</span> {{membership.price}} RSD</li>
                            <li class="mb-3"><span class="list-item">Broj treninga:&nbsp</span> {{membership.numberOfRemainingVisits}}</li>
                            <li class="mb-3"><span class="list-item">Važi do:&nbsp</span> {{membership.endDate | dateFormat('DD.MM.YYYY.')}}</li>
                        </ul>
                    </div>
                    <div class="choose-button text-center">
                        <a class="btn btn-primary" href="#/">Nazad</button>
                    </div>
                </div>
            </div>
        </div>
    	`,
	
	methods: {
	},
	mounted() {
		this.membership = this.$route.params.membership;

		if(!this.membership){
			this.$router.push("/membership");
		}

	},

	filters:{
		dateFormat: function(value, format){
			var parsed = moment(value);
			return parsed.format(format);
		}
	}
});