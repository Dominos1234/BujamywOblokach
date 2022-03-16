<script>
    export let columnNames;
</script>

    <div class="dummy">
    </div>
    <div class="container-fluid row mainContainer">
        {#each columnNames as columnName}
            <div class="col d-flex align-items-center">
                <p class="align-middle">{columnName}</p>
            </div>
        {/each}
        <div class="col d-flex align-items-center hide">
            <button class="btn btn-warning">Edit</button>
        </div>
    </div>

<style>
    .dummy {
        height: 40px;
        background-color: white;
        /* border-top: 1px solid red; */
    }
    .mainContainer {
        border-bottom: 5px solid black;
        height: 40px;
        margin-bottom: 15px;
        font-weight: bold;
        position: fixed;
        top: 0;
        left: 0;
        background-color: white;
        /* border-top: 1px solid red; */
    }

    .hide {
        visibility: hidden;
    }
</style>