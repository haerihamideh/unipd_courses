import BaseApiUrl from './baseUrl.js';

class userUrls extends BaseApiUrl {

    static USER_BASE_URL = 'user';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}`,
        method: 'POST',
        token: true
    };

    static EDIT(id) {
        return {
            url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/${id}`,
            method: 'PUT',
            token: true
        }
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}`,
        method: 'GET',
        token: false
    };


    static GET_BY_ID(id) {
        return {
            url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/${id}`,
            method: 'GET',
            token: false
        }
    };


    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/delete/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
        token: true
    };

    static CHANGE_STATUS(id, status) {
        return {
            url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/change-status/${id}/${status}`,
            method: 'PATCH',
            token: true
        }
};


}

export default userUrls;