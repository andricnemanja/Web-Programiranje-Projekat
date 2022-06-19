var app = new Vue({
	el: '#facilities',
	data: {
		facilities: null,
		
	},
	 template: ` 
    	<div>
    		<h3>Prikaz sportskih objekata</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Ime</th>
	    			<th>Tip</th>
	    			<th>Lokacija</th>
	    			<th>Presek</th>
	    		</tr>
	    			
	    		<tr v-for="f in facilities">
	    			<td>{{f.name}}</td>
	    			<td>{{f.facilityType}}</td>
	    			<td>{{f.location}}</td>
	    			<td>{{f.averageRating}}</td>
	    			
	    	</table>
    	</div>		  
    	`,
	mounted() {
		axios.get('rest/facilities/')
			.then(response => (this.facilities = response.data))
	},
	methods: {
	
	
	}
});