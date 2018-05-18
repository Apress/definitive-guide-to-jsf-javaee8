function pageChangeListener(event) {
	if (event.status == "success") {
		var page = document.getElementById("content").dataset.page;
		var url = location.pathname + "?page=" + page;
		history.pushState(null, document.title, url);
	}
}