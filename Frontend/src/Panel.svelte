<script>
import { api } from './api';
import {utils} from './utils.js';

import Selector from './Selector.svelte';

export let paginationOptions;
export let onSelectPaginationOption;
export let selectedPaginationOption;
export let currentPage;
export let onSetCurrentPage;
export let pagesCount = 0;
export let queryName;
export let findByName;


const toArray = number => {
    if(!number) {
        return [];
    } else {
        return Array.from(Array(number).keys());
    }
};

const showStats = async () => {
    const stats = await api.stats();
    if(stats) {
        let str = '';
        Object.keys(stats).forEach((key) => {
            str += `${key}: ${utils.formatNumber(stats[key])}\n`;
        });
        alert(str);
    }
};
</script>

<div class="placeholder">
</div>
<div class="container-fluid mainContainer">
    <div class="row">
        <h5 class="col">Show stats:</h5>
        <button class="col btn btn-primary" on:click={showStats}> Show stats</button>
    </div>
    <div class="row">
        <h5 class="col">Find by name:</h5>
        <input  type="text" class="col" bind:value={queryName} on:input={newValue => {findByName(newValue)}}/>
    </div>
    <div class="row">
        <h5>Page:</h5>
    </div>
        <Selector options={toArray(pagesCount)} onSelect={onSetCurrentPage} selectedOption={currentPage} modifier={item => item + 1}></Selector>
    <div class="row">
        <h5>Number of records per page:</h5>
    </div>
    <div class="row">
        <Selector options={paginationOptions} onSelect={onSelectPaginationOption} selectedOption={selectedPaginationOption}></Selector>
    </div>
</div>

<style>

.mainContainer {
    position: fixed;
    left: 0;
    bottom: 0;
    background-color: white;
    border-top: 5px solid black;
}

.placeholder {
    height: 200px;
}
</style>