import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";
import supplierUrls from "./urls/supplierUrls.js";
import storageUrls from "./urls/storageUrls.js";
import userUrls from "./urls/userUrls.js";

function validateName() {
    const nameInput = document.getElementById("product-name");

    // Skip validation if the name field doesn't exist
    if (!nameInput) {
        return true;
    }

    const name = nameInput.value.trim();

    // Perform the validation for the name field
    if (name === "") {
        nameInput.classList.add("is-invalid");
        return false;
    } else {
        nameInput.classList.remove("is-invalid");
        return true;
    }
}

function validatePrice() {
    const priceInput = document.getElementById("price");

    // Skip validation if the price field doesn't exist
    if (!priceInput) {
        return true;
    }

    const price = priceInput.value.trim();

    // Perform the validation for the price field
    if (price === "") {
        priceInput.classList.add("is-invalid");
        return false;
    } else {
        priceInput.classList.remove("is-invalid");
        return true;
    }
}

function validateLimitation() {
    const limitationInput = document.getElementById("limitation");

    // Skip validation if the limitation field doesn't exist
    if (!limitationInput) {
        return true;
    }

    const limitation = limitationInput.value.trim();

    // Perform the validation for the limitation field
    if (limitation === "") {
        limitationInput.classList.add("is-invalid");
        return false;
    } else {
        limitationInput.classList.remove("is-invalid");
        return true;
    }
}

function validateExpirationDate() {
    const expirationDateInput = document.getElementById("expiration-date");
    const currentDate = new Date().toISOString().split("T")[0];
    const expirationDate = expirationDateInput.value;

    if (expirationDate < currentDate) {
        console.error("Expiration date should not be before the current date");
        expirationDateInput.classList.add("is-invalid");
        return false;
    } else {
        expirationDateInput.classList.remove("is-invalid");
        return true;
    }
}

export async function fetchAndPopulateSuppliers() {
    console.log("HERE")
    const router = new Router();
    const suppliers = await router.createFetch(supplierUrls.GET_ALL);
    console.log(suppliers)
    const selectElement = document.getElementById("supplierId");

    // Clear existing options
    selectElement.innerHTML = "";

    // Create and append option elements
    suppliers.forEach((supplier) => {
        const optionElement = document.createElement("option");
        optionElement.value = supplier.id;
        optionElement.textContent = supplier.name;
        selectElement.appendChild(optionElement);
    });
}
export const unInitial =  () => {
    return fetchAndPopulateSuppliers();
}
export async function sendData() {
    const supplierListValue = document.getElementById("supplierId").value;
    const supplierListInt = parseInt(supplierListValue, 10);

    const productType = document.getElementById("product-type").value;
    const name = document.getElementById("product-name").value;
    const supplier = supplierListInt;
    const gender = document.getElementById("gender").value;
    const ageGroup = document.getElementById("age-group").value;
    const expirationDate = document.getElementById("expiration-date").value;
    const countryOfProduction = document.getElementById("country-of-production").value;
    const price = document.getElementById("price").value;
    const description = document.getElementById("description").value;
    const image = null;

    const isPriceValid = validatePrice();
    const isLimitationValid = validateLimitation();
    const isExpirationDateValid = validateExpirationDate();

    const isNameValid = validateName();

    if (!isNameValid || !isPriceValid || !isLimitationValid || !isExpirationDateValid) {
        console.error("Invalid input");
        return null;
    }


    const data = {
        productType,
        name,
        supplier,
        gender,
        ageGroup,
        expirationDate,
        countryOfProduction,
        description,
        price,
        image,
    };

    if (productType === "DRUG") {
        const isSensitive = document.getElementById("sensitivity").value;
        const limitation = document.getElementById("limitation").value;
        const needPrescription = document.getElementById("need-prescription").value;
        const shape = document.getElementById("shape").value;
        data.shape = shape;
        data.isSensitive = isSensitive;
        data.limitation = limitation;
        data.needPrescription = needPrescription;
    }

    const router = new Router();
    if (data.productType === "DRUG") {
        return router.createFetch(drugUrls.ADD, null, null, null, data);
    } else if (data.productType === "MATERIAL") {
        return router.createFetch(materialUrls.ADD, null, null, null, data);
    }
    return null;
}

export const addProduct = async (event) => {
    event.preventDefault();
    try {
        const responseData = await sendData();
        window.history.back();
    } catch (error) {
        let errorMessage = "An error occurred while sending data. Please try again later.";
        if (error.response && error.response.data && error.response.data.msg) {
            errorMessage = error.response.data.msg;
        }
        alert("Error: " + errorMessage);
    }
};

export const cancelButton = () => {
    // window.location.href = "supplier.html"; // Replace with the desired URL
    window.history.back();
};
