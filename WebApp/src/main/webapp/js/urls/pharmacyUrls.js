import BaseApiUrl from './baseUrl.js';

class pharmacyUrls extends BaseApiUrl {

    static PHARMACY_BASE_URL = 'pharmacy';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}`,
        method: 'POST',
        token: true
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT',
        token: true
    };

    static ADD_STAFF = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/add-staff/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT',
        token: true
    };

    static DELETE_STAFF = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/delete-staff/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
        token: true
    };

    static GET = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };

    static DELETE = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
        token: true
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/get-all`,
        method: 'GET',
        token: false
    };

    static PHARMACY_ACTIVATION = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/get-all`, //NOTE: ADD THIS TO URL /{pharmacyId}
        method: 'PATCH',
        token: true
    };

}

export default pharmacyUrls;