
import supplierUrls from "./urls/supplierUrls.js";
import {createButtonsAndText, createGenericTable, createTable} from "./table/table.js";
import orderUrls from "./urls/orderUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import '../js/table/fancyTable.js'


let data;
const router = new Router();
console.log(Promise.resolve(router.createFetch(orderUrls.GET_ALL)),"HEREEEE")
// console.log(Promise.resolve(router.createFetch(supplierUrls.GET_ALL)),"SUPPLIER")

export const onInitial = async () => {
    try {
        data = await router.createFetch(orderUrls.GET_ALL);
        console.log("data: ",data)
        populateTable(data);
    } catch (e) {
        console.log("Error: ", e);
    }
}


// Populate table with data
function populateTable(data) {

    const tableData = data.map(obj => {
        // const isOrder = obj.order !== null
        const newObj = {
            id: obj.id ? obj.id : "1", //isOrder ? obj.drug.name : obj.material.name,
            status: obj.status,
            price: obj.price, //isOrder ? obj.drug.price : obj.material.price,
            date: obj.orderDate,
        };
        return newObj;
    });
    console.log("this is table dat: ", tableData)
    const footerContent = {
        button: {
          active: false
        },
        text: {
            active: false,
            left: "",
            center: ""
        }
    }
    createGenericTable(
        "order-list",
        ["id", "price", "status" , "date"],
        tableData,
        footerContent,
        "default",
    );

    const filterForm = document.getElementById('filterForm');
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();

        // Filter the data
        const selectedSupplier = document.getElementById('filterSupplier').value;
        const selectedType = document.getElementById('filterType').value;

        const filteredData = data.filter(obj => {

            return (selectedType === '' || obj[selectedType] !== null) && (selectedSupplier === '' || obj[selectedSupplier] !== null) ;
        });

        const elem = document.getElementById('order-list');
        elem.remove();
        console.log(elem)
        populateTable(filteredData);

    });
}

export const supplierOption = async () => {
    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    //supplier option
    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name => {
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}

export const calculateTotal = () => {
    var total = 0;
    var checkboxes = document.getElementsByClassName("order-checkbox");

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            var price = parseFloat(checkbox.getAttribute("data-price"));
            total += price;
        }
    }
    document.getElementById("total-price").textContent = "Total Price: $" + total.toFixed(2);
}


function generateFooterText(firstText, secondText) {
    // Create the first text element
    const firstTextElement = $("<span>", {
        text: firstText,
        css: {
            fontWeight: "bold",
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the second text element
    const secondTextElement = $("<span>", {
        text: secondText,
        css: {
            fontWeight: "bold",
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the container for the text elements
    const textContainer = $("<div>", {
        class: "text-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            width: "40%", // Adjust the width as needed
        },
    });

    // Append the text elements to the container
    textContainer.append(firstTextElement, secondTextElement);

    // Create the main container for the footer
    const container = $("<div>", {
        class: "bottom-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginTop: "0.75rem", // Adjust the top margin as needed
        },
    });

    // Append the table to the container
    // $(".border").append(table);

    // Append the container to the page or desired parent element
    $(".border").append(container);

    // Append the text container to the main container
    container.append(textContainer);
}

// Example usage
// generateFooterText("First Text", "Second Text");
