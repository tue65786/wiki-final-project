$(document).ready(function () {
    $("#editor").jqxEditor({
        width: "100%",
        tools: 'bold italic underline | format font size | color background | left \\n\
                center right | outdent indent | ul ol | image | link | html | clean | \\n\
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
                            // action of command
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
                            // action of command
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
            selectedText = "";
    
    if (iframeWindow.getSelection) {
        selectedText = iframeWindow.getSelection().toString();
    } else if (iframeDocument.selection && iframeDocument.selection.type != "Control") {
        selectedText = iframeDocument.selection.createRange().text;
    }
//    var len = editor.val().length;
//    var start = editor.val()[0].selectionStart;
//    var end = editor.val()[0].selectionEnd;
    
//    // modify text depending on the type of markUp
    switch (type) {
        case 'topic':    // bold link
            selectedText = ('[[').concat(selectedText).concat(']]');
            break;
        case 'tag':        // regular link
            selectedText = ('{{').concat(selectedText).concat('}}');
            break;
        default:
            break;
    }alert($('#editor')[0]);

//    // replace selected text with markup text
//    editor.val(editor.val().substring(0, start) + text + editor.val().substring(end, len));
}

// allows for wiki markup shortcut keys
// DOES NOT WORK ANYMORE
//$(document).on('keydown', $('#editor'), function (e) {
//    if (e.ctrlKey && e.keyCode === 76) {
//        if (e.shiftKey) {
//            markUp('boldlink'); // bold hyperlink
//        } else {
//            markUp('link');     // regular hyperlink
//        }
//    }
//});