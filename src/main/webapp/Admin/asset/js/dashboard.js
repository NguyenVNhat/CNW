document.addEventListener('DOMContentLoaded', function() {

    const buttonmodal = document.querySelectorAll('.divmodal');
    buttonmodal.forEach(function(button){
        const modal = button.querySelector('.modal');
        button.addEventListener('mouseover',function() {
            modal.style.display = 'block';
        })
        button.addEventListener('mouseout',function() {
            modal.style.display = 'none';
        })
    })



});
