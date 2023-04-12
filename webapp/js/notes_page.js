
let profileButton = document.getElementById("profile-btn");
if (profileButton) {
	profileButton.addEventListener("click", () => {
		console.log("clicked");
		window.location.href = "my_profile.jsp";
	});
}


let addNoteButton = document.getElementById("add-note-btn");
if (addNoteButton != null) addNoteButton.addEventListener("click", removeHidden);

function removeHidden() {
	// Removes the hidden class to show form
	let element = document.getElementById("add-note-form");
	element.classList.remove("hidden");
	
	// removes the button
	let button = document.getElementById("add-note-btn");
	button.remove();
}

// Fixes the formatting with less than 3 notes
let numOfNotes = document.getElementsByClassName("note").length;
if (numOfNotes > 0 &&  numOfNotes < 3) {
	let main = document.getElementById("main-content");
	main.classList.add("two-notes-or-less-fix");
}


let cancelButton = document.getElementById("cancel-btn");
if (cancelButton) {
	cancelButton.addEventListener("click", () => {
		history.back();
	});
}


let testButton = document.getElementById("test-btn");
if (testButton) {
	let textarea = document.getElementById("textarea");
	testButton.addEventListener("click", () => {
		console.log(textarea.value);
	})
}


let editNoteTextarea = document.getElementById("edit-body");
if (editNoteTextarea != null) {
	editNoteTextarea.style.height = editNoteTextarea.scrollHeight + 3 + "px";
}



