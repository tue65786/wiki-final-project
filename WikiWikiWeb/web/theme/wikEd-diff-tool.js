// @name        wikEd diff tool
// @version     1.0.0
// @date        September 25, 2014
// @description online tool for improved word-based diff library with block move detection
// @homepage    https://cacycle.altervista.org/wikEd-diff-tool.html
// @requires    https://en.wikipedia.org/w/index.php?title=User:Cacycle/diff.js&action=raw&ctype=text/javascript
// @author      Cacycle (https://en.wikipedia.org/wiki/User:Cacycle)
// https://en.wikipedia.org/wiki/User:Cacycle/wikEdDiff
// @license     released into the public domain

// JSHint options: W004: is already defined, W100: character may get silently deleted
/* jshint -W004, -W100, newcap: true, browser: true, jquery: true, sub: true, bitwise: true, curly: true, evil: true, forin: true, freeze: true, globalstrict: true, immed: true, latedef: true, loopfunc: true, quotmark: single, strict: true, undef: true */
/* global console */

// turn on ECMAScript 5 strict mode
'use strict';

// define global objects
var WikEdDiffTool = {};
var WikEdDiff;
var wikEdDiffConfig;
var WED;


//
// WikEdDiffTool.init(): initialize
//

WikEdDiffTool.init = function() {

	// set debug shortcut
	if ( (WED === undefined) && (window.console !== undefined ) ) {
		WED = window.console.log;
	}

	// define config variable
	if (wikEdDiffConfig === undefined) {
		wikEdDiffConfig = {};
	}

	// define all wikEdDiff options
	WikEdDiffTool.options = [
		'fullDiff',
		'showBlockMoves',
		'charDiff',
		'repeatedDiff',
		'recursiveDiff',
		'recursionMax',
		'unlinkBlocks',
		'blockMinLength',
		'unlinkMax',
		'coloredBlocks',
		'debug',
		'timer',
		'unitTesting',
		'noUnicodeSymbols',
		'stripTrailingNewline'
	];

	// continue after content has loaded
	if (window.addEventListener !== undefined) {
		window.addEventListener('DOMContentLoaded', WikEdDiffTool.load);
	}
	else {
		window.onload = WikEdDiffTool.load;
	}
	return;
};


//
// WikEdDiffTool.load(): run diff
//

WikEdDiffTool.load = function() {

  // attach event handlers
	
	return;
};


//
// WikEdDiffTool.diff(): click handler for compare button, get options and text versions, call wikEdDiff.diff()
//

WikEdDiffTool.diff = function() {

	wikEdDiffConfig = { fullDiff: false, showBlockMoves: true, charDiff: true, repeatedDiff: true, recursiveDiff: true, recursionMax: 5, unlinkBlocks: true, blockMinLength: 3, unlinkMax: 10, coloredBlocks: false,debug:false,timer:false,unitTesting:false,noUnicodeSymbols:false,stripTrailingNewline:false };
//	wikEdDiffConfig.blockMinLength = parseInt(document.getElementById('blockMinLength').value);
//	wikEdDiffConfig.unlinkMax = parseInt(document.getElementById('unlinkMax').value);
//	wikEdDiffConfig.recursionMax = parseInt(document.getElementById('recursionMax').value);

	// calculate the diff
	var oldString = "My love. Hello\nWorld. how are u"//document.getElementById('old').value;
	var newString = "Hello World My love. how r you";//document.getElementById('new').value;
	var wikEdDiff = new WikEdDiff();
	var diffHtml = wikEdDiff.diff(oldString, newString);
	document.getElementById('diff').innerHTML = diffHtml;
	return;
};

WikEdDiffTool.diff = function(oldString, newString,destinationDIV) {

	wikEdDiffConfig = { fullDiff: false, showBlockMoves: true, charDiff: true, repeatedDiff: true, recursiveDiff: true, recursionMax: 5, unlinkBlocks: true, blockMinLength: 3, unlinkMax: 10, coloredBlocks: false,debug:false,timer:false,unitTesting:false,noUnicodeSymbols:false,stripTrailingNewline:false };
//	wikEdDiffConfig.blockMinLength = parseInt(document.getElementById('blockMinLength').value);
//	wikEdDiffConfig.unlinkMax = parseInt(document.getElementById('unlinkMax').value);
//	wikEdDiffConfig.recursionMax = parseInt(document.getElementById('recursionMax').value);

	// calculate the diff
	
	var wikEdDiff = new WikEdDiff();
	var diffHtml = wikEdDiff.diff(oldString, newString);
	document.getElementById(destinationDIV).innerHTML = diffHtml;
	return;
};


//
// WikEdDiffTool.clear(): click handler for clear button, clear example text and results
//

WikEdDiffTool.clear = function() {

	document.getElementById('old').value = '';
	document.getElementById('new').value = '';
	WikEdDiffTool.diff();
	return;
};


// initialize WikEdDiffTool
WikEdDiffTool.init();