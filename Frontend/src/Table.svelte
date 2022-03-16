<script>
import TableRow from './TableRow.svelte';
import TableHeader from './TableHeader.svelte';

export let records = [];
export let currentPage = 0;
export let pagesCount;
export let pageSize;

$: currentRecords = ((currentPage, pagesCount)=>{
    if(!pagesCount) {
        return records;
    }
    return records.slice(currentPage * pageSize, Math.min(currentPage + 1) * pageSize, records.length);
})(currentPage, pagesCount);
</script>

<div class="container-fluid">
    <TableHeader columnNames={records.length > 0 ? Object.keys(records[0]) : ['#','#','#','#','#','#','#','#','#','#','#','#','#']} />
    {#each currentRecords as record(record.id)}
        <TableRow record={record}/>
    {/each}
</div>