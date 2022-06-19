Vue.component("sportFasility", {
	data: function () {
		    return {
		      facilities:null
		    }
	},
	template: `
	<div>
	Prikaz sportske opreme:
	<table border="1">
	<tr bgcolor="lightgrey">
		<th>Naziv</th>
		<th>tip</th>
		<th>Lokacija</th>
		<th>Presek stanja</th>
		<th>&nbsp;</th>
	</tr>
		
	<tr v-for="f in facilities">
		<td>{{f.name }}</td>
		<td>{{f.facilityType}}</td>
		<td>{{f.location }}</td>
		<td>{{f.averageRating}}</td>
	</tr>
</table>
	<p>
		<a href="#/sc">Pregled sadržaja korpe</a>
	</p>
</div>
,	
	
	` 

	
	,
	methods : {
		addToCart : function (product) {
			
		}
	},
	mounted () {
        axios
          .get('rest/facilities/')
          .then(response => (this.facilities = response.data))
    },
});	  