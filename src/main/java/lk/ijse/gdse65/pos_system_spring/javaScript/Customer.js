 $(document).ready(function (){
    $("#customer-nav").click(function () {
        loadAllCustomer();
    });
     $("#save_customer").click(function () {
         let formData = new FormData();
         formData.append("customer_id", $("#cust_id").val());
         formData.append("name", $("#name").val());
         formData.append("address", $("#address").val());
         formData.append("contact", $("#contact").val());

         $.ajax({
             method: "POST",
             url: "http://localhost:8080/POS_system_spring_war_exploded/api/v1/customer",
             async: true,
             processData: false,
             contentType: false,
             data: formData,
             success: function (data) {
                 reset();
                 alert("Customer saved successfully.");
             },
             error: function (xhr, status, error) {
                 alert("Error: " + error);
             }
         });
     });


     $("#update_customer").click(function (){
         let formData = new FormData();
         formData.append("customer_id", $("#cust_id").val());
         formData.append("name", $("#name").val());
         formData.append("address", $("#address").val());
         formData.append("contact", $("#contact").val());

    $.ajax({
        method:"PATCH",
        url:"http://localhost:8080/POS_system_spring_war_exploded/api/v1/customer",
        async:true,
        processData: false,
        contentType: false,
        data:formData,
        success: function (data) {
            reset()
            alert("saved")
        },
        error: function (xhr, exception) {
            alert("Error")
        }

    })

});

$("#delete_customer").click(function () {
    let customer_idF = $("#cust_id").val();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/POS_system_spring_war_exploded/api/v1/customer/" + customer_idF,
        processData: false,
        contentType: false,
        async: true,
        success: function (data) {
            reset()
            alert("Customer deleted successfully");
        },
        error: function (xhr, exception) {
            alert("Error deleting customer");
        }
    });
});


$("#customer_reset").click(function () {
    reset();
});
const reset = () => {
    $("#cust_id").val("");
    $("#name").val("");
    $("#address").val("");
    $("#contact").val("");
    loadAllCustomer();
}

const loadAllCustomer = () => {
    $("#customer-tbl-body").empty();
    $.ajax({
        url: "http://localhost:8080/POS_system_spring_war_exploded/api/v1/customer",
        method: "GET",
        dataType: "json",
        success: function (resp) {
            console.log(resp);
            for (const customer of resp) {
                let row = `<tr><td>${customer.customer_id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.contact}</td></tr>;`
                $("#customer-tbl-body").append(row);
            }
            callMethod();
        }
    });
}
function callMethod(){
    $("#customer-tbl-body>tr").click(function (){
        let customer_id =$(this).children().eq(0).text();
        let name =$(this).children().eq(1).text();
        let address =$(this).children().eq(2).text();
        let contact =$(this).children().eq(3).text();

        $("#cust_id").val(customer_id);
        $("#name").val(name);
        $("#address").val(address);
        $("#contact").val(contact);
    })
}
});

