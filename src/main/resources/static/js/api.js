/**
 * This JS file is used for Search API call and sorting API output data
 *
 * @author Dipak Patil.
 */
$(document).ready(function () {
	
	//This function is triggered on page load.
	$("#data-table").ready(function (event) {
    	console.log("SUCCESS : ", event);
    	var sortingByCol = $("#selected_field").val(); 
    	console.log("SORTING FIELD SELECTED : ", sortingByCol);
    	var searchString  = "software";
    	invokeAPI(searchString, sortingByCol);
    });
	
	//This function is triggered on click of 'Search Button'
	$("#search-button").click(function (event) {
		search(event)
    });
	
	//This function is triggered on click of 'Search Button'
	$("#selected_field").change(function (event) {
		search(event)
    });
	
	//This function is triggered when user enters after entering filter text
	$("#filter-text").keypress(function (e) {
	        var code = (e.keyCode ? e.keyCode : e.which);
	        //alert(code);
	        if (code == 13) {
	            $("#search-button").trigger('click');
	            return true;
	        }
	    });
});

/**
 * This function uses ajax to invoke the Rest API which internally calls public API to get Job data.
 * @param filterString Search data using this filter String.
 * @param sortingByCol Sort data using this Field.
 */
function invokeAPI(filterString, sortingByCol) {
	 $.ajax({
	        type: "GET",
	        contentType: "application/json",
	        //url: "http://api.dataatwork.org/v1/jobs/autocomplete?begins_with=%22" + filter_text + "%22",
	        url: "/getJobs?begins_with=%22" + filterString + "%22",
	        dataType: 'json',
	        cache: false,
	        timeout: 100000,
	        success: function (data) {
	            //Clear errors if exists. 
	            $('#error_result').show().html("");
	            $('#num_results').show().html("Found " + data.length + " Jobs");
	            console.log("SUCCESS : ", JSON.stringify(data, undefined, 4));
	            sortedData = sortByKey(data, sortingByCol);
	            console.log("SORTED Dat: ", JSON.stringify(sortedData, undefined, 4));
	            createTable(sortedData);
	        },
	        error: function (e) {
	        	console.log("ERRORS : ", e);
	            console.log("ERRORS : ", e.responseText);
                $('#error_result').show().html(e.responseText); //this is my div with messages
                
                //Clear existing result.
                $('#num_results').show().html("");
                var tableDiv = document.getElementById("showData");
                tableDiv.innerHTML = "";
	        }
	    });
}

/**
 * This function populates filter string and selected sorting field from Web page and invokes
 * a function to execute REST api.
 * @param event
 */
function search(event) {
	console.log("SUCCESS : ", event);
	var filterString = $("#filter-text").val();
	if(filterString == "") {
		filterString = "software";
	}
	console.log("Changed to : ", filterString);
	
	//Get Sorting by field
	var sortingByCol = $("#selected_field").val(); 
	console.log("SORTING FIELD SELECTED : ", sortingByCol);
	
	invokeAPI(filterString, sortingByCol);
}

/**
 * This function creates HTML table by using specified data from the JSON object. 
 * @param data JSON object
 */
function createTable(jsonData) {
	console.log("Creating Table...");
	 var col = [];
     for (var i = 0; i < jsonData.length; i++) {
         for (var key in jsonData[i]) {
             if (col.indexOf(key) == -1) {
                 col.push(key);
             }
         }
     }

     // Create table
     var table = document.createElement("table");
     // creating table header 
     var tr = table.insertRow(-1);
     for (var i = 0; i < col.length; i++) {
         var th = document.createElement("th");
         th.innerHTML = col[i];
         tr.appendChild(th);
     }

     // Set json data in a table.
     for (var i = 0; i < jsonData.length; i++) {
         tr = table.insertRow(-1);
         for (var j = 0; j < col.length; j++) {
             var cell = tr.insertCell(-1);
             cell.innerHTML = jsonData[i][col[j]];
         }
     }

     // 
     var tableDiv = document.getElementById("showData");
     tableDiv.innerHTML = "";
     tableDiv.appendChild(table);
}

/**
 * This function sorts a given object with specified key in json object.
 * @param jsonObject JSON Object
 * @param key Sort Key
 * @returns Alphabetically sorted JSON object
 */
function sortByKey(jsonObject, key) {
    return jsonObject.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
}