import BaseApiUrl from './baseUrl.js';

class materialUrls extends BaseApiUrl {

    static MATERIAL_BASE_URL = 'material';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}`,
        method: 'POST',
        token: false
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}/`,
        method: 'POST',
        token: false
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}/`,
        method: 'PUT',
        token: false
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}`,
        method: 'GET',
        queryParameters: {
            countryOfProduction: '',
            supplierId: '',
            gender: ''
        },
        token: false
    };
}

export default materialUrls;