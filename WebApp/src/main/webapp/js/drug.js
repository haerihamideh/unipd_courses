import drugUrls from './urls/drugUrls.js';
import {createGenericTable} from "./table/table.js";

// Get all drugs data
let data;
const router = new Router();

export const onInitial = async () => {
    try {
        data = await router.createFetch(drugUrls.GET_ALL);
        populateTable(data);
    } catch (e) {
        console.log("Error: ", e);
    }

}

// Populate table with data
function populateTable(data) {

    const tableData = data.map(obj => {
        const newObj = {
            Name: obj.name,
            "Age Group": obj.ageGroup,
            Sensivity: obj.sensitive ? "Sensitive" : "Not Sensitive",
            Price: obj.price,
            Limitation: obj.limitation
        };
        return newObj;
    });
    console.log(tableData)
    createGenericTable(
        "drug-list",
        ["Name", "Age Group", "Sensivity", "Price", "Limitation"],
        tableData,
    );
}

// Filter table based on selected options
const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const sensitivityFilter = document.getElementById('filterSensitivity').value;
    const ageFilter = document.getElementById('filterAge').value;

    let filteredData = data.filter((drug) => {
        let pass = true;

        if (ageFilter !== '' && drug.ageGroup !== ageFilter) {
            pass = false;
        }
        if (sensitivityFilter !== '' && drug.sensitive !== (sensitivityFilter == 'true')) {
            pass = false;
        }

        return pass;
    });

    const elem = document.getElementById('drug-list');
    elem.remove();
    populateTable(filteredData);
});