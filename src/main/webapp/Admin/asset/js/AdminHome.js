document.addEventListener('DOMContentLoaded', function() {
    const divItems = document.querySelectorAll('.divitem');

    divItems.forEach(function(divItem) {
        const parentItem = divItem.querySelector('.parent-item');
        const subItem = divItem.querySelector('.sub-item');
        const index = divItem.querySelector('.index');

        parentItem.addEventListener('click', function() {
            const currentlyOpen = document.querySelector('.divitem.open');

            const arraw = divItem.querySelector('.fa-caret-right');
            if (currentlyOpen && currentlyOpen !== divItem) {
                const currentSubItem = currentlyOpen.querySelector('.sub-item');
                const currentIndex = currentlyOpen.querySelector('.index');

                currentlyOpen.style.height = '80px';
                currentSubItem.style.display = 'none';
                currentIndex.style.marginLeft = '-10px';
                currentlyOpen.classList.remove('open');
            }

            if (divItem.classList.contains('open')) {
                divItem.style.height = '80px';
                subItem.style.display = 'none';
                index.style.marginLeft = '-10px';
                arraw.style.rotate = '0deg';
                divItem.classList.remove('open');
            } else {
                divItem.style.height = '280px';
                subItem.style.display = 'block';
                index.style.marginLeft = '0px';
                arraw.style.rotate = '90deg';
                divItem.classList.add('open');
            }
        });
    });


});

