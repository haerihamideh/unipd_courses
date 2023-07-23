import {createGenericTable} from "./table/table.js";
export const onInitial = () => {
    
    let receiptData = []
    if(localStorage.getItem("getData")) {
        JSON.parse(localStorage.getItem("getData")).map((item, index) => {
            const newObject = {
                id: item.id,
                paymentMethod: item.paymentMethod,
                date: item.date.split("T")[0]
            }
            return receiptData.push(newObject)
        })
    }


    const footerContent = {
        text: {
            active: false,
            left: "Total: 5$",
            center: ""
        },
        button: {
            active: false
        }
    }

    createGenericTable(
        "receipt_list",
        ["id", "PaymentMethod", "date"],
        receiptData,
        footerContent,
        'default'
    );

};