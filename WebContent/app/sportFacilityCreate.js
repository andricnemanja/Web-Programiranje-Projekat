Vue.component("sportFacilityCreate", {
	data: function () {
		    return {
		     	newFacility : {
					name: null,
					facilityType : null,
					location: null,
					imageName:null}
			
			
					
		    }
	},
	 template: ` 
	 	 <div class="div-for-create">
            <p>Dodajte novi sportski objekat</p>
            <form id="forma">
               
                <input type="text" 
                 class="input"
                 placeholder="ime"/>
                 <br/><br/>
                 <select name=” optionlist ” class="input">
                 <option>Teretana</option>
                 <option>Bazen </option>
                 <option>Sportski centar</option>
                 <option> Sauna </option>
                 <option> Plesni studio </option>
                 </select>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="geografska sirina"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="geografska duzina"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="broj ulice"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="naziv ulice"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="postanski kod"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="grad"/>
                 <br/><br/>
                 <input class="input"
                 type="text"
                 placeholder="logo"/>
                 <br/><br/><br/>
                 <button class="button">Kreiraj</button>
     
                 <p id="error" hidden="true"></p>
                 <p id="success" hidden="true"></p>
             </form>
    
        </div>
    	`,
	
	methods: {
		
		
	},
	mounted() {
		
	alert("stiglaa");
		
	}
});