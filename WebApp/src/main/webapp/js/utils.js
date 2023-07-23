export function getBase64ProfilePicture() {
    return new Promise((resolve) => {
        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];

        // Create a new FileReader object
        const reader = new FileReader();

        // Set up a function to be called when the file is read
        reader.onload = function (event) {
            // Get the loaded file data as a base64 encoded string
            const base64String = event.target.result;

            // Send the base64 string to the backend
            resolve(base64String);
        };

        // Read the file as a data URL (base64 encoded string)
        reader.readAsDataURL(file);
    });
}

export function getTokenFromLocalStorage() {
    return localStorage.getItem("token");
}

export function getRouteParam (index = 1) {
    return window.location.hash.split("/")[index]
}

// Helper function to parse the JWT token payload
export function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join(''));

    return JSON.parse(jsonPayload);
}

// Function to dynamically generate the dropdown options based on the user's role
export function generateRoleOptions(role, userForm) {
    const roleSelect = userForm.querySelector('select[name="role"]');
    const options = [
        { value: "3", label: "Staff" }
    ];

    if (role.toLowerCase() === "superadmin") {
        options.unshift({ value: "2", label: "Admin" });
    }

    // Add options to the dropdown
    options.forEach(option => {
        const optionElement = document.createElement('option');
        optionElement.value = option.value;
        optionElement.textContent = option.label;
        roleSelect.appendChild(optionElement);
    });
}

export function getPageTitle() {
    const appName = "Semicolon";
    const hashName = window.location.hash.split("/")[0].replace("#", "").replaceAll("-", " ");

    if (hashName === "") {
        return appName;
    }

    const pageName = hashName.substring(0,1).toUpperCase() + hashName.substring(1);
    return `${appName} - ${pageName}`;

} 


export function extractRole(token) {
    const payload = parseJwt(token);
    const role = payload.Role;
    return role;
}