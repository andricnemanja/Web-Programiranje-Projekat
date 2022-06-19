Vue.component("sportFacility", {
	data: function () {
		    return {
		      facilities: {},
		      name: "",
		      facilityType: "",
		      location: "",
		      averageRating: 5
		    }
	},
	 template: ` 
    	<div class="sportFasility view">
    		<h3>Prikaz sportskih objekata</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Ime</th>
	    			<th>Tip</th>
	    			<th>Lokacija</th>
	    			<th>Presek</th>
	    		</tr>
	    			
	    		
	    			
	    	</table>
    	</div>		  
    	`,
	mounted() {
		axios.get('rest/facilities/')
			.then(response => (this.facilities = response.data, console.log(response)))
		    
	},
	
	methods: {
	
	
	}
});