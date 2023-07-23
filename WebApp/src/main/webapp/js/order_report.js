import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    // const orderData = [{
    //     ID:1 , Price:12 , Status:"DELIVERED" , Date:"12-01-2023"
    // },{
    //     ID:2 , Price:32 , Status:"PENDING" , Date:"05-04-2023"
    // },{
    //     ID:3 , Price:6 , Status:"DELIVERED" , Date:"30-11-2023"
    // },{
    //     ID:4 , Price:89 , Status:"CANCELLED" , Date:"11-09-2023"
    // }]

    let orderData = []
    if(localStorage.getItem("getData")) {
        JSON.parse(localStorage.getItem("getData")).map((item, index) => {
            const newObject = {
                id: item.id,
                price: item.price,
                status:item.status,
                date: item.orderDate
            }
            return orderData.push(newObject)
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
        "order_list",
        ["id", "price", "status", "date"],
        orderData,
        footerContent,
        'default'
    );

};