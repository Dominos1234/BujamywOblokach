<script>
import { onMount } from 'svelte';

    import {api} from './api.js';

    export let records;
    export let updateRecords;
    export let sampleRecord;

    let record = {};

    $: {
        if(records) {
            clearRecord(records);
        }
    }

    const clearRecord = records => {
        if(records) {
            let o = {...records[0]};
            delete o.id;
            Object.keys(o).forEach(k => o[k] = '');
            
            record = o;
        }
    };
</script>
<div class="row">
    <div class="col d-flex align-items-center">
        <input disabled={true} />
    </div>
    {#each Object.keys(record) as key}
        <div class="col d-flex align-items-center">
            <input type="text" on:input={e => {record[key] = e.target.value}}/>
        </div>
    {/each}
    <div class="col d-flex align-items-center">
        <button class={"btn btn-success"}
            on:click={async () => {
                if(await api.post(record, true)) {
                    updateRecords();
                    clearRecord(records);
                }
                else {
                    console.log('cannot create');
                    alert('Invalid data.');
                }
            }
        }>
        {'Save'}
        </button>
    </div>
</div>

<style>
    input {
        width: 100%;
    }
    div {
        border-bottom: 2px solid grey;
        height: 40px;
        margin-bottom: 15px;
    }
</style>