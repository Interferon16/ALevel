alert("hi");
var element = document.getElementById("first_image");
var start_width = element.width;
var start_height = element.height;
element.addEventListener('mouseover', function () {
    this.style.width = (start_width * 1.1) + 'px';
    this.style.height = (start_height * 1.1) + 'px';
}, false);
element.addEventListener('mouseout', function () {
    this.style.width = start_width + 'px';
    this.style.height = start_height + 'px';
}, false);

head = document.getElementsByClassName("header");
for (var i = 0; i < head.length; i++) {
    head[i].addEventListener('mouseover', function () {
        this.style.color = '#047c2d';
    }, false);
    head[i].addEventListener('mouseout', function () {
        this.style.color = '#000000';
    }, false);
    head[i].addEventListener('click',function () {
        content=document.getElementsByClassName(this.id);
        if(content[0].style.display=="none"){
            content.style.display="inline";
        }else if(content[0].style.display=="inline") {
            content.style.display = "none";
        }
    })
}

