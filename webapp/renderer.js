// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// No Node.js APIs are available in this process because
// `nodeIntegration` is turned off. Use `preload.js` to
// selectively enable features needed in the rendering
// process.

const baseUrl = 'http://localhost:9090/myApp';
const gU = (endpoint) => `${baseUrl}${endpoint}`;

let mat = [];

function render(matrix) {
	const rows = matrix.length;
	const cols = matrix[0].length;
	
	const table = document.getElementById("matrix-container");
	for(let i = 0; i < rows; i++) {
		const ele = document.createElement('tr');
		for(let j = 0; j < cols; j++) {
			const column = document.createElement('td');
			column.bgColor = getBgColor(matrix[i][j]);
			column.id = i + "_" + j;
			column.height = 10;
			column.width = 20;
			column.align = "center";
			column.innerHTML = matrix[i][j];
			column.onclick = (e) => processClickEvent(e);
			ele.appendChild(column);
		}
		table.appendChild(ele);
	}
}

function getBgColor(char){
	switch(char){
		case 'x': return "#4c4c4c";
		case 'o': return "#ffffff";
		case 's': return "#cc0000";
		case 'd': return "#00FFFF";
		case '#': return "#00cc00";
	}
}


function parseId(ids) {
	return ids.split("_").map(e => parseInt(e, 10));
}

function processClickEvent(e) {
	e.preventDefault();
	if(e.shiftKey){
		handleSourceClick(e);
	}else{
		handleDestClick(e);
	}
}

function handleDestClick(event){
	event.preventDefault();
	const val = event.target.innerHTML;
	const [ r, c ] = parseId(event.target.id);
	console.log('-----------',val, r, c);

	fetch(gU(`/toggle-dest?r=${r}&c=${c}`))
	.then(res=>res.json())
	.then(response => {
		const { status } = response;
		if(response.status != 500){
			console.log(response);
			const { colNumber, rowNumber, destinationId } = response;
			if(document.getElementById(destinationId).innerHTML == 'd'){
				document.getElementById(destinationId).innerHTML = 'o';
				document.getElementById(destinationId).bgColor = getBgColor('o');
			}else if(document.getElementById(destinationId).innerHTML == 'o'){
				document.getElementById(destinationId).innerHTML = 'd';
				document.getElementById(destinationId).bgColor = getBgColor('d');
			}

		}
	})
}

function handleSourceClick (event){
	event.preventDefault();
	const val = event.target.innerHTML;
	const [ r, c ] = parseId(event.target.id);
	console.log('-----------',val, r, c);

	fetch(gU(`/set-source?r=${r}&c=${c}`))
	.then(res => res.json())
	.then(response => {
		const { status } = response;
		if(response.status != 500){
			console.log(response);
			const { oldSource, newSource } = response;
			const { colNumber: colNumberN, rowNumber: rowNumberN, sourceId: sourceIdN } = newSource;
			if (oldSource !== null) {
				const { colNumber: colNumberO , rowNumber: rowNumberO, sourceId: sourceIdO } = oldSource;
				document.getElementById(sourceIdO).innerHTML = 'o';
				document.getElementById(sourceIdO).bgColor = getBgColor('o');
			}
			document.getElementById(sourceIdN).innerHTML = 's';
			document.getElementById(sourceIdN).bgColor = getBgColor('s');

		}
	});
}

function reset(){
	fetch(gU("/reset-map"))
	.then(res => res.json())
	.then(response =>{
		const matrix = response.mapMatrix;
		const rows = matrix.length;
		const cols = matrix[0].length;
		for(let i=0;i<rows;i++){
			for(let j=0;j<cols;j++){
				document.getElementById(i+"_"+j).innerHTML = matrix[i][j];
				document.getElementById(i+"_"+j).bgColor = getBgColor(matrix[i][j]);
			}
		}
	})

}

function find(){
	fetch(gU("/find-path"))
	.then(res => res.json())
	.then(response =>{
		const matrix = response.mapMatrix;
		console.log(matrix);
		const rows = matrix.length;
		const cols = matrix[0].length;
		for(let i=0;i<rows;i++){
			for(let j=0;j<cols;j++){
				document.getElementById(i+"_"+j).innerHTML = matrix[i][j];
				document.getElementById(i+"_"+j).bgColor = getBgColor(matrix[i][j]);
			}
		}
	})
}

fetch(gU("/get-map"))
.then(res => res.json())
.then(response => {
	console.log(response)
	/** @type {string[]} */
	const matrix = response.mapMatrix;
	render(matrix);
	
}).catch(error => console.error(error));

