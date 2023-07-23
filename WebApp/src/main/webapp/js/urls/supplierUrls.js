import BaseApiUrl from './baseUrl.js';

class supplierUrls extends BaseApiUrl {

    static SUPPLIER_BASE_URL = 'supplier';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}`,
        method: 'POST',
        token: true
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT',
        token: true
    };

    static REMOVE = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
        token: true
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/supplier/get-all`,
        method: 'GET',
        token: false
    };


}

export default supplierUrls;