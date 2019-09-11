$(document).ready(function() {
	$("#btnUpload").click(function(event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();

		// Get form
		var form = $('#fileUploadForm')[0];
		console.log(form);
		// Create an FormData object
		var data = new FormData(form);
		// If you want to add an extra field for the FormData
		// data.append("CustomField", "This is some extra data, testing");

		// disabled the submit button
		$("#btnUpload").prop("disabled", true);

		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "./Upload",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				$("#resultUpload").text(data);
				console.log("SUCCESS : ", data);
				$("#btnUpload").prop("disabled", false);

			},
			error : function(e) {

				$("#resultUpload").text(e.responseText);
				console.log("ERROR : ", e);
				$("#btnUpload").prop("disabled", false);

			}
		});

	});

});