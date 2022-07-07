Vue.component("cart", {
	data: function () {
		    return {
				selectedMembership: ""
		    }
	},
	template: ` 
	<div class="container py-5 cart">
		<h1 class="title pb-5 text-center">Izabrana članarina</h1>

		<div class="row justify-content-center">
			<div class="col-lg-4" v-if="selectedMembership == 'MONTH_12'">
				<div class="bg-white p-5 rounded-lg shadow box">

					<div class="header text-center px-5">
						<h1 class="h6 text-uppercase font-weight-bold mb-4">Paket mesec 12</h1>
						<h1>2.499 RSD</h1>
						<p class="text-small">mesečno</p>
						<div class="separator mx-auto"></div>
					</div>

					<div class="details text-left">
						<ul class="list-unstyled my-5 text-small text-left">
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp 12 treninga mesečno</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp 1 ulazak dnevno</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp Pristup svim objektima iz ponude</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-lg-4" v-if="selectedMembership == 'MONTH_FULL'">
				<div class="bg-white p-5 rounded-lg shadow box">

					<div class="header text-center px-5">
						<h1 class="h6 text-uppercase font-weight-bold mb-4">Paket mesec full</h1>
						<h1>2.999 RSD</h1>
						<p class="text-small">mesečno</p>
						<div class="separator mx-auto"></div>
					</div>

					<div class="details text-left">
						<ul class="list-unstyled my-5 text-small text-left">
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp Neograničen broj treninga</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp 1 ulazak dnevno</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp Pristup svim objektima iz ponude</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-lg-4" v-if="selectedMembership == 'YEAR'">
				<div class="bg-white p-5 rounded-lg shadow box">

					<div class="header text-center px-5">
						<h1 class="h6 text-uppercase font-weight-bold mb-4">Paket godina</h1>
						<h1>24.999 RSD</h1>
						<p class="text-small">godišnje</p>
						<div class="separator mx-auto"></div>
					</div>

					<div class="details text-left">
						<ul class="list-unstyled my-5 text-small text-left">
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp Neograničen broj treninga</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp 1 ulazak dnevno</li>
							<li class="mb-3"><span class="checkmark">✓ </span>&nbsp Pristup svim objektima iz ponude</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="w-100"></div>
			<input type="text" class="promo-code" placeholder=" Promo kod"/>
			<div class="w-100"></div>
			<button type="button" class="btn btn-primary" v-on:click="chooseMembership('MONTH_12')">Kupi</button>
		</div>
	</div>
    	`,
	
	methods: {

	},
	mounted() {
		this.selectedMembership = this.$route.params.membership;

		if(!this.selectedMembership){
			this.$router.push("/membership");
		}

	}
});