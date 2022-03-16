export let utils = {};

utils.formatNumber = value => {
    if(!isNaN(value))
        return Math.round(value) == value ? value : parseFloat(value).toFixed(2);
    else {
        return value;
    }
};