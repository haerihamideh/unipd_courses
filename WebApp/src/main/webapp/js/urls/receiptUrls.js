import BaseApiUrl from './baseUrl.js';

class receiptUrls extends BaseApiUrl {

    static RECEIPT_BASE_URL = 'receipt';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}`,
        method: 'POST',
        token: false
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };
    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/get-pharmacy`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/get-pharmacy`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET',
        token: false
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT',
        token: false
    };

}
export default receiptUrls;