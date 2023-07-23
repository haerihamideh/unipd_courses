import BaseApiUrl from './baseUrl.js';

class accountUrls extends BaseApiUrl {

    static LOGIN = {
        url: `${BaseApiUrl.BASE_URL}/account`,
        method: 'GET',
        token: false
    };

    static LOGOUT = {
        url: `${BaseApiUrl.BASE_URL}/account/`,
        method: 'POST',
        token: true
    };

}
export default accountUrls;