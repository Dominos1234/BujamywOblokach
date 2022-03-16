export let api = {};

api.server = `http://127.0.0.1:8080/api`;
api.headers = {Accept: 'application/json', 'Content-Type': 'application/json'};

api.get = async name => {
    const url = name ?
        `${api.server}/champion?name=${name}` :
        `${api.server}/champions`;
    const result = await fetch(url, {method: 'GET', headers: api.headers});
    if(!result.ok) {
        console.log('get failed');
        return;
    }

    const json = await result.json();

    return json;
};

api.post = async champion => {
//    champion = {...champion};
    const url = `${api.server}/champion`;

    console.log(champion);

    console.log(JSON.stringify(champion));
    const result = await fetch(url, {method: 'POST', headers: api.headers, body: JSON.stringify(champion)});
    if(!result.ok) {
        return false;
    } else {
        return true;
    }
};