class Router {
    sendHttpRequest(method, url, data, token) {
        const requestOptions = {
            method: method,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            }
        };

        if (data) {
            requestOptions.body = JSON.stringify(data);
        }

        return fetch(url, requestOptions)
            .then(res => {
                if (res.ok) {
                    return res.json();
                } else {
                    throw new Error('Request failed');
                }
            }).catch(error => {
                console.error('Error fetching data:', error);
                // throw an error or return a default value
                throw error;
            });
    }

    createFetch(endpoint, pathVariable, params, token, data) {

        if (!(endpoint.method in {GET: 1, POST: 1, PUT: 1, DELETE: 1, PATCH: 1})) {
            throw new Error(`Invalid HTTP method: ${endpoint.method}`);
        }
        if (endpoint.token && !token){
            throw new Error(`Invalid request, token is needed.`);
        }

        let url = endpoint.url;

        if (pathVariable) {
            url += pathVariable;
        }
        if (params) {
            url += '?' + Object.keys(params).map(
                key => `${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`).join('&');
        }

        const urlObject = {
            url: url,
            method: endpoint.method
        };

        return this.sendHttpRequest(urlObject.method, urlObject.url, data, token)
            .then(responseData => {
                console.log(responseData['data'])
                return responseData['data'];
            });
    }

}