import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    let userData = []
    if(localStorage.getItem("getData")) {
        JSON.parse(localStorage.getItem("getData")).map((item, index) => {
            const newObject = {
                Name: item.name,
                LastName: item.lastName,
                Role:item.role.role,
                Status: item.accountStatus,
                Address: item.address
            }
            return userData.push(newObject)
        })
    }

    const footerContent = {
        button: {
            active: false,
            cancel: "Cancel",
            onCancel: () => {},
            submit: "Submit",
            onSubmit: () => {}
        }
        ,
        text: {
            active: false,
            left: "Total: 5$",
            center: "WhatEver!"
        }
    }

    // showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    createGenericTable(
        "user_list",
        ["","Name", "LastName", "Role", "Address", "Status"],
        userData,
        footerContent,
        'user'
        );

};