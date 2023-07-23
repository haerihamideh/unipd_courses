import userUrls from "./urls/userUrls.js";
import {
    getBase64ProfilePicture,
    getTokenFromLocalStorage,
    generateRoleOptions,
    getRouteParam,
    extractRole
} from "./utils.js";

const router = new Router();

export const onInitial = async () => {
    const userId = getRouteParam();
    const editUserForm = document.getElementById('edit-user-form');
    // Extract the role from the JWT token payload
    const token = getTokenFromLocalStorage();
    const role = extractRole(token);

    // Generate the role dropdown options based on the user's role
    generateRoleOptions(role, editUserForm);

    try {
        const fetchedData = await router.createFetch(userUrls.GET_BY_ID(userId));
        fillFormData(fetchedData);
    } catch (error) {
        console.log("Error: ", error);
    }
}

const editUserForm = document.getElementById('edit-user-form');
editUserForm.addEventListener('submit', async function (event) {
    event.preventDefault();
    const userId = getRouteParam();
    const formData = new FormData(editUserForm);
    const body = Object.fromEntries(formData);

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }
    console.log("here", body);
    if (body.profilePicture.size !== 0) {
        body.profilePicture = await getBase64ProfilePicture();
    } else {
        body.profilePicture = null;
    }

    const token = getTokenFromLocalStorage();

    const updatedData = await router.createFetch(userUrls.EDIT(userId), null, null, token, body);

});

const statusBtn = document.getElementById('status-btn');
statusBtn.addEventListener('click', async function (event) {
    event.preventDefault();
    const userId = getRouteParam();
    const token = getTokenFromLocalStorage();
    const accountStatus = document.querySelector("[name='accountStatus']");
    const newStatus = accountStatus.value == "ACTIVE" ? "DEACTIVE" : "ACTIVE";

    const updatedData = await router.createFetch(userUrls.CHANGE_STATUS(userId, newStatus), null, null, token, null);
    accountStatus.value = newStatus;
    event.target.textContent = newStatus === "ACTIVE" ? "Disable" : "Enable";
});

function fillFormData(data) {
    const editUserForm = document.getElementById('edit-user-form');
    const statusBtn = document.getElementById('status-btn');
    console.log("data image", data['profileImage']);
    editUserForm.querySelector('input[name="name"]').value = data.name;
    editUserForm.querySelector('input[name="lastName"]').value = data.lastName;
    editUserForm.querySelector('input[name="birthDate"]').value = data.birthDate.split("T")[0];
    editUserForm.querySelector('select[name="gender"]').value = data.gender;
    editUserForm.querySelector('select[name="role"]').value = data.role.id;
    editUserForm.querySelector('input[name="phoneNumber"]').value = data.phoneNumber;
    editUserForm.querySelector('input[name="email"]').value = data.email;
    editUserForm.querySelector('input[name="address"]').value = data.address;
    editUserForm.querySelector('input[name="accountStatus"]').value = data.accountStatus;
    editUserForm.querySelector('img[id="avatar-preview"]').src = data['profileImage'];
    setImage(data['profileImage'])
    statusBtn.textContent = data.accountStatus === "ACTIVE" ? "Disable" : "Enable";
}


document.getElementById("editProfilePicture").addEventListener("change", handleFileSelect);

function handleFileSelect(event) {
    const fileInput = event.target;
    const avatarPreview = document.getElementById("avatar-preview");

    if (fileInput.files && fileInput.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            avatarPreview.setAttribute("src", e.target.result);
        };

        reader.readAsDataURL(fileInput.files[0]);
    }
}

function setImage(image) {
    const avatarPreview = document.getElementById("avatar-preview");
    avatarPreview.setAttribute("src", image);
}