/**
* @author Parth Trivedi , Paresh Patel
* @version 1.0
* @modifiedDate 17/09/2014
*/
function validate() {
	
	var a = document.getElementById("currentPassword");
    if (a.value == "" || a.value == null || a.value.toString().trim()=="") {
        alert("Enter Current Password");
        a.focus();
        return false;
    }    
    a = document.getElementById("newPassword");
    if (a.value == "" || a.value == null || a.value.toString().trim()=="") {
        alert("Enter New Password");
        a.focus();
        return false;
    }
    var b = document.getElementById("confirmPassword");
    if (b.value == "" || b.value == null || b.value.toString().trim()=="") {
        alert("Enter Confirm Password");
        b.focus();
        return false;
    } 
    if(a.value!=b.value)
	{
    	 alert("Please enter the same password as above");
         b.focus();
         return false;
	}
    return true;
}
$().ready(function() {
	$("#changePasswordForm").validate({
		errorPlacement: function(error, element) {
			$( element )
				.closest( "form" )
					.find( "label.error[for='" + element.attr( "id" ) + "']" )
						.append( error );
		},
		errorElement: "span",
		rules: {
			currentPassword:{
				required: true,
				minlength: 5,
				maxlength:25,
				alphanumeric:true
			},
			newPassword: {
				required: true,
				minlength: 5,
				maxlength:25,
				alphanumeric:true
			},
			confirmPassword: {
				required: true,
				minlength: 5,
				alphanumeric:true,
				equalTo: "#newPassword"
			}
		},
		messages: {
			currentPassword:{
				required: "Please provide a New password",
				minlength: "Your New password must be at least 5 characters long",
				maxlength: "Maximum 25 characters"
			},
			newPassword: {
				required: "Please provide a New password",
				minlength: "Your New password must be at least 5 characters long",
				maxlength: "Maximum 25 characters"
			},
			confirmPassword: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above",
				maxlength: "Maximum 25 characters"
			}			
		}
	});
});

