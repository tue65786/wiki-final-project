$(document).ready(function () {
	$("#editor").jqxEditor({
		width: "100%",
		tools: 'bold italic underline | format font size | color background | left \\n\
                center right | outdent indent | ul ol | image | link | \\n\
                topic tag',
		createCommand: function (name) {
			switch (name) {
				case 'topic':
					return {
						type: 'button',
						tooltip: 'Topic Link',
						init: function (widget) {
							widget.jqxButton({height: 25, width: 20});
							widget.html("<span style='line-height: 23px;'>[]</span>");
						},
						refresh: function (widget, style) {
							// go here when selection changes
						},
						action: function (widget, editor) {
							markUp('topic');
						}
					};
				case 'tag':
					return {
						type: 'button',
						tooltip: 'Tag Link',
						init: function (widget) {
							widget.jqxButton({height: 25, width: 20});
							widget.html("<span style='line-height: 23px;'>{}</span>");
						},
						refresh: function (widget, style) {
							// go here when selection changes
						},
						action: function (widget, editor) {
							markUp('tag');
						}
					};
			}
		}
	});
});

function markUp(type) {
	// get hightlighted text and store into selectedText
	var iframe = $('#editor').find('iframe')[0],
			iframeWindow = iframe.contentWindow || iframe,
			iframeDocument = iframe.contentDocument || iframe.contentWindow.document,
			selectedText = "", start, end,
			len = $('#editor').val().length;

	if (iframeWindow.getSelection) {
		selectedText = iframeWindow.getSelection().toString();
		start = $("#editor").val().indexOf(iframeWindow.getSelection());
		end = start + iframeWindow.getSelection().toString().length;
	} else if (iframeDocument.selection && iframeDocument.selection.type != "Control") {
		// never tested this; for Internet Explorer
		selectedText = iframeDocument.selection.createRange().text;
		start = $("#editor").val().indexOf(iframeDocument.selection.createRange().text);
		end = start + iframeDocument.selection.createRange().text.length;
	}

	// modify text depending on the type of markUp
	switch (type) {
		case 'topic':    // bold link
			selectedText = ('[[').concat(selectedText).concat(']]');
			break;
		case 'tag':        // regular link
			selectedText = ('{{').concat(selectedText).concat('}}');
			break;
		default:
			break;
	}

	// replace selected text with markup text
	$('#editor').val($('#editor').val().substring(0, start) + selectedText + $('#editor').val().substring(end, len));
}