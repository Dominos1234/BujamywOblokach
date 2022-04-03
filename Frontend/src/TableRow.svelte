<script>
    import { onMount } from "svelte";
    import {api} from './api';

    import TableCell from './TableCell.svelte';

    export let record;

    let editable = false;
</script>

{#if !editable}
<div class="row">
    {#each Object.values(record) as value}
        <TableCell value={value}></TableCell>
    {/each}
    <div class="col d-flex align-items-center">
        <button class={"btn btn-warning"} on:click={() => {editable = true; }}>
        {'Edit'}
        </button>
    </div>
</div>
{:else}
<div class="row">
    {#each Object.keys(record) as key}
        <div class="col d-flex align-items-center">
            <input disabled={key == 'id'} bind:value={record[key]} />
        </div>
    {/each}
    <div class="col d-flex align-items-center">
        <button class={"btn btn-success"}
            on:click={async () => {if(await api.post(record)) editable = false; else console.log('cannot update');}}>
        {'Save'}
        </button>
    </div>
</div>
{/if}

<style>
    div {
        border-bottom: 2px solid grey;
        height: 40px;
        margin-bottom: 15px;
    }
    input {
        width: 100%;
    }
</style>