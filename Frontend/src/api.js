export let api = {};

api.server = `http://192.168.20.21:8081/api`;
api.headers = {Accept: '*/*', 'Content-Type': 'application/json'};

api.get = async name => {
    const url = name ?
        `${api.server}/champion?name=${name}` :
        `${api.server}/champions`;
    console.log(url);
    const result = await fetch(url, {method: 'GET', headers: api.headers});
    if(!result.ok) {
        console.log('get failed');
        return;
    }

    const json = await result.json();

    return json;
};

api.post = async (champion, createNew = false) => {
    const url = `${api.server}/champion`;

    console.log(champion);

    console.log(JSON.stringify(champion));
    console.log(createNew ? 'PUT' : 'POST');
    const result = await fetch(url, {method: createNew ? 'PUT' : 'POST', headers: api.headers, body: JSON.stringify(champion)});
    if(!result.ok) {
        return false;
    } else {
        return true;
    }
};

api.stats = async () => {
    const url = `${api.server}/stats`;

    const result = await fetch(url);
    
    if(result.ok) {
        const json = await result.json();
        console.log(json);
        return json;
    } else {
        alert('Cannot get stats');
        return;
    }
};