import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial =  () => {

    let receiptData = []
    JSON.parse(localStorage.getItem("getData")).map((item, index) => {
        const newObject = {
            id: item.id,
            paymentMethod: item.paymentMethod,
            date: item.date.split("T")[0]
        }
        return receiptData.push(newObject)
    })
    console.log("New Data: ", receiptData)

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            onCancel: () => {},
            submit: "Submit",
            onSubmit: () => {}
        },
        text: {
            active: false
        }
    }

    createGenericTable(
        "reciept_list",
        ['id', 'date', 'paymentMethod'],
        receiptData,
        null,
        "default"
    );

};