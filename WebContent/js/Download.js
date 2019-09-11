$(document).ready(function() {
	$("#btnDownload").click(function(event) {

		// stop submit the form, we will post it manually.
		// event.preventDefault();
		var uname = $("input[name='uname']").val();
		// disabled the submit button
		$("#btnDownload").prop("disabled", true);

		$.ajax({
			type : "GET",
			url : "./Download",
			data : {
				uname : uname
			},
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {

			},
			error : function(e) {

				$("#resultDownload").text(e.responseText);
				console.log("ERROR : ", e);
				$("#btnDownload").prop("disabled", false);

			}
		});

	});

});