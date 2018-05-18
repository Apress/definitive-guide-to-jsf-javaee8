function startLongRunningProcess() {
	jsf.push.open("push");
	document.getElementById("status").innerHTML = "Long running process has started ... (this will take 5 seconds)";
}
function endLongRunningProcess(result) {
	jsf.push.close("push");
	document.getElementById("status").innerHTML = result;
}
