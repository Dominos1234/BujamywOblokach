<script>
	import { onMount } from 'svelte';

	import {api} from './api.js';

	import Panel from './Panel.svelte';
	import Table from './Table.svelte';

	let champions = [];
	let selectedPaginationOption = 10;
	
	$: paginationOptions = (recordsCount => {
		let current = 10;
		let array = [];
		while(current < recordsCount) {
			array.push(current);
			current *= 2;
			current = Math.floor(current * 100) / 100;
		}
		array.push(Math.floor(current * 100) / 100);

		selectedPaginationOption = array[array.length - 1];

		return array;
	})(champions.length);

	let currentPage = 0;
	$: pagesCount = Math.ceil(champions.length / selectedPaginationOption);
	
	onMount(async () => {
		const fetchedChampions = await api.get();
		if(fetchedChampions) {
			champions = fetchedChampions;
			console.log(champions);
		}
	});


</script>

<main>
	<Table records={champions} currentPage={currentPage} pagesCount={pagesCount} pageSize={selectedPaginationOption}></Table>
	
	<Panel paginationOptions={paginationOptions}
		selectedPaginationOption={selectedPaginationOption}
		onSelectPaginationOption={paginationOption => {selectedPaginationOption = paginationOption; currentPage = 0;}}
		pagesCount ={pagesCount}
		currentPage={currentPage}
		onSetCurrentPage={page => {currentPage = page;}}
	></Panel>
</main>

<style>
</style>