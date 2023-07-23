import BaseApiUrl from './baseUrl.js';

class orderUrls extends BaseApiUrl {

    static ORDER_BASE_URL = 'order';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}`,
        method: 'POST',
        token: false
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{token}
        method: 'POST',
        token: true
    };

    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{token}
        method: 'DELETE',
        token: true
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/get-all`,
        method: 'GET',
        token: false
    };

    static STATUS = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{status}
        method: 'PATCH',
        token: true
    };

    static REPORT = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/report`,
        method: 'GET',
        queryParameters: {
            token: '',
            orderReport: '',
            num: 0
        },
        token: true
    };

}

export default orderUrls;