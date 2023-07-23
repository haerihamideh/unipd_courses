import storageUrls from "./urls/storageUrls.js";
import {createGenericTable} from "./table/table.js";

let data;
const router = new Router();

export const onInitial = async () => {
    try {
        data = await router.createFetch(storageUrls.GET_ALL);
        console.log(data)
        populateTable(data);
    } catch (e) {
        console.log("Error: ", e);
    }

}

// Populate table with data
function populateTable(data) {

    const tableData = data.map(obj => {
        const isDrug = obj.drug !== null;
        const newObj = {
            Name: isDrug ? obj.drug.name : obj.material.name,
            Type: isDrug ? "Drug" : "Material",
            Amount: obj.amount,
            Price: isDrug ? obj.drug.price : obj.material.price,
            Threshold: isDrug ? obj.drug.limitation : obj.material.limitation
        };
        return newObj;
    });
    console.log(tableData)
    createGenericTable(
        "storage-list",
        ["Name", "Type", "Price", "Amount", "Threshold"],
        tableData,
    );
}

const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Filter the data
    const selectedType = document.getElementById('filterType').value;

    const filteredData = data.filter(obj => {

        return selectedType === '' || obj[selectedType] !== null;
    });


    const elem = document.getElementById('storage-list');
    elem.remove();
    populateTable(filteredData);

});