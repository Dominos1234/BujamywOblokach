<script>
import Selector from './Selector.svelte';

export let paginationOptions;
export let onSelectPaginationOption;
export let selectedPaginationOption;
export let currentPage;
export let onSetCurrentPage;
export let pagesCount = 0;


const toArray = number => {
    if(!number) {
        return [];
    } else {
        return Array.from(Array(number).keys());
    }
};
</script>

<div class="placeholder">
</div>
<div class="container-fluid mainContainer">
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