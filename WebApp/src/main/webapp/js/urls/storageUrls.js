import BaseApiUrl from './baseUrl.js';


class storageUrls extends BaseApiUrl {

    static STORAGE_BASE_URL = 'storage';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}`,
        method: 'POST',
        token: true
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT',
        token: true
    };

    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`,
        method: 'DELETE',
        token: true
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}`,
        method: 'GET',
        token: false
    };

    static REPORT = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/report/`, //NOTE: ADD THIS TO URL /{pharmacyId}
        method: 'GET',
        token: false
    };


    static STATUS = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/report`,
        method: 'GET',
        token: false
    };


}
export default storageUrls;