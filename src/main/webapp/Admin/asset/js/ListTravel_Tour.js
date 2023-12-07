document.addEventListener('DOMContentLoaded', function() {
    const divItems = document.querySelectorAll('.item');
    let prevClickedItem = document.querySelector('.item-init');

    divItems.forEach(function(divItem) {
        divItem.addEventListener('click', function() {
            if (prevClickedItem !== null) {
                prevClickedItem.style.borderBottom = 'none';
                prevClickedItem.style.backgroundColor = '#f3f3f3';
            }
            divItem.style.borderBottom = 'solid';
            divItem.style.backgroundColor = '#cecece';
            prevClickedItem = divItem;
        });
    });
});
