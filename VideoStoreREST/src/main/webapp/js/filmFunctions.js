window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {
	
	document.filmForm.lookup.addEventListener('click', function(event) {
		event.preventDefault();
		let filmId = document.filmForm.filmId.value;
		if (!isNaN(filmId) && filmId > 0) {
			getFilm(filmId);
		}
	});

	newFilmForm.addFilmButton.addEventListener('click', function(evt) {
		evt.preventDefault();
		console.log('Adding film');
		//		let newFilm = {};
		//		newFilm.title = newFilmForm.title.value;
		let newFilm = {
			title: newFilmForm.title.value,
			description: newFilmForm.description.value,
			releaseYear: newFilmForm.releaseYear.value,
			rating: newFilmForm.rating.value,
			length: newFilmForm.length.value,
			language: { 
				id: newFilmForm.language.value
			}
		};

		console.log(newFilm);
		addFilm(newFilm);
	});
	
	loadLanguages();
}


function loadLanguages() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/languages');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				let languages = JSON.parse(xhr.responseText);
				displayLanguages(languages);
			}
			else {
				displayError('Film not found: ' + filmId);
			}
		}
	};
	xhr.send();
}

function displayLanguages(languageList) {
	let list = document.getElementById('languageList');
	list.textContent = '';
	for (let lang of languageList) {
		let opt = document.createElement('option');
		opt.textContent = lang.name;
		opt.value = lang.id;
		list.appendChild(opt);
	}
}

function getFilm(filmId) {
//	if ( ! filmId || isNaN(filmId) ) {
//		throw new Error('Invalid film id')
//	}
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/films/' + filmId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				let data = xhr.responseText;
				let film = JSON.parse(data);
				displayFilm(film);
				getActors(filmId);
			}
			else {
				displayError('Film not found: ' + filmId);
			}
		}
	};
	xhr.send();
}

function displayError(msg) {
	let dataDiv = document.getElementById('filmData');
	dataDiv.textContent = '';
	let errorMsg = document.createElement('h2');
	errorMsg.textContent = msg;
	dataDiv.appendChild(errorMsg);
	let actorDiv = document.getElementById('actorData');
	actorDiv.textContent = '';
}

function displayFilm(film) {
	let dataDiv = document.getElementById('filmData');
	dataDiv.textContent = '';
	let titleHeader = document.createElement('h1');
	titleHeader.textContent = film.title;
	dataDiv.appendChild(titleHeader);
	let desc = document.createElement('blockquote');
	desc.textContent = film.description;
	dataDiv.appendChild(desc);
	let ul = document.createElement('ul');
	dataDiv.appendChild(ul);
	let li = document.createElement('li');
	li.textContent = 'Rated ' + film.rating;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = film.releaseYear;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = film.length + " minutes";
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Language: " + film.language.name;
	ul.appendChild(li);
}

function getActors(filmId) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/films/' + filmId + '/actors');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				let actorList = JSON.parse(xhr.responseText);
				displayActors(actorList);
			}
			else {
				displayError('Film not found: ' + filmId);
			}
		}
	};
	xhr.send();
}

function displayActors(actors) {
	let actorDiv = document.getElementById('actorData');
	actorDiv.textContent = '';
	if (actors && Array.isArray(actors) && actors.length > 0) {
		let h3 = document.createElement('h3');
		h3.textContent = 'Cast';
		actorDiv.appendChild(h3);
		let ul = document.createElement('ul');
		actorDiv.appendChild(ul);
		for (let actor of actors) {
			let li = document.createElement('li');
			li.textContent = actor.firstName + " " + actor.lastName;
			ul.appendChild(li);
		}
	}
}

function addFilm(newFilm) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/films');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if ( xhr.status === 200 || xhr.status === 201) {
				let createdFilm = JSON.parse(xhr.responseText);
//				displayFilm(createdFilm);
				getFilm(createdFilm.id);
			}
			else {
				displayError("Error creating new film: " + xhr.status);
			}
		}
	};
	
	xhr.setRequestHeader("Content-type", "application/json");
	let newFilmJson = JSON.stringify(newFilm);
	xhr.send(newFilmJson);
}


