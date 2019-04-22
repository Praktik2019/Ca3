const URL = "http://localhost:8084/jwtbackend";

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

class ApiFacade {
    makeOptions(method, addToken, body) {
        var opts = {
            method: method,
            headers: {
                "Content-type": "application/json",
                'Accept': 'application/json',
            }
        }
        if (addToken && this.loggedIn()) {
            opts.headers["x-access-token"] = this.getToken();
        }
        if (body) {
            opts.body = JSON.stringify(body);
        }
        return opts;
    }
    setToken = (token) => {
        localStorage.setItem('jwtToken', token)
    }
    getToken = () => {
        return localStorage.getItem('jwtToken')
    }
    loggedIn = () => {
        const loggedIn = this.getToken() != null;
        return loggedIn;
    }
    logout = () => {
        localStorage.removeItem("jwtToken");
    }
    login = (user, pass) => {
        const options = this.makeOptions("POST", true, { username: user, password: pass });
        return fetch(URL + "/api/login", options, true)
            .then(handleHttpErrors)
            .then(res => { this.setToken(res.token) })
    }
    postOrder = (cart) => {
        const options = this.makeOptions("POST", true, { candy: cart }); //True add's the token
        console.log("Hello from postOrder");
        return fetch(URL + "/api/order", options).then(handleHttpErrors);
    }
    fetchData = () => {
        const options = this.makeOptions("GET", true); //True add's the token
        return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
    }
    fetchTableData = () => {
        const options = this.makeOptions("GET", true); 
        return fetch(URL + "/api/candy", options).then(handleHttpErrors);
    }
    fetchCandyDetails = (id) => {
        const options = this.makeOptions("GET", true); 
        console.log("Fetching from: " + URL + "/api/candy/" + id);
        return fetch(URL + "/api/candy/" + id, options).then(handleHttpErrors);
    }
}
const facade = new ApiFacade();
export default facade;