<script>
    export let options = [];
    export let selectedOption;
    export let onSelect;
    export let modifier = x => x;
</script>

<div class="row">
    {#each options as option}
        {#if selectedOption == option}
            <button class='col btn btn-secondary' on:click={onSelect(option)}>{modifier(option)}</button>
        {:else}
            <button class='col btn btn-primary' on:click={onSelect(option)}>{modifier(option)}</button>
        {/if}
    {/each}
</div>