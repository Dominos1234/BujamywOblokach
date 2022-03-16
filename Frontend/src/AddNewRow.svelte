<script>
    export let sampleRecord;

    $: record = ((obj) => {
        if(!sampleRecord) return {};

        let o = {...obj};
        delete o.id;
        Object.keys(o).forEach(k => o[k] = '');
        return o;
        })(sampleRecord);
</script>
<div class="row">
    {#each Object.keys(record) as key}
        <div class="col d-flex align-items-center">
            <input bind:value={record[key]} />
        </div>
    {/each}
    <div class="col d-flex align-items-center">
        <button class={"btn btn-success"}
            on:click={async () => {if(await api.post(record, true)) editable = false; else console.log('cannot create');}}>
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