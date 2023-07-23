import materialUrls from './urls/materialUrls.js';
import {createGenericTable} from "./table/table.js";

// Get all materials data
let data;
const router = new Router();


export const onInitial = async () => {
    try {
        data = await router.createFetch(materialUrls.GET_ALL);
        console.log(data)
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
            Supplier: obj.supplier.name,
            Gender: obj.gender,
            Price: obj.price,
            "Country of Production": obj.countryOfProduction
        };
        return newObj;
    });

    createGenericTable(
        "material-list",
        ["Name", "Age Group", "Supplier", "Gender", "Price", "Country of Production"],
        tableData,
    );
}

// Filter table based on selected options
const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const ageFilter = document.getElementById('filterAge').value;
    const genderFilter = document.getElementById('filterGender').value;

    let filteredData = data.filter((material) => {
        let pass = true;

        if (ageFilter !== '' && material.ageGroup !== ageFilter) {
            pass = false;
        }
        if (genderFilter !== '' && material.gender !== genderFilter) {
            pass = false;
        }

        return pass;
    });
    const elem = document.getElementById('material-list');
    elem.remove();
    populateTable(filteredData);
});