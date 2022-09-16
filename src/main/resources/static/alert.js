alert("Script 001!");
loadScript('alert2.js');


function loadScript(src) {
    let script = document.createElement('script');
    script.src = src;
    document.head.append(script);
}