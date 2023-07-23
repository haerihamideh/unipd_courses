import BaseApiUrl from './baseUrl.js';

class drugUrls extends BaseApiUrl {

    static DRUG_BASE_URL = 'drug';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}`,
        method: 'POST',
        token: false
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}//${drugUrls.DRUG_BASE_URL}/`,
        method: 'POST',
        token: false
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}/`,
        method: 'PUT',
        token: false
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}`,
        method: 'GET',
        queryParameters: {
            countryOfProduction: '',
            supplierId: '',
            gender: '',
            isSensitive: '',
            shape: ''
        },
        token: false
    };
}

export default drugUrls;