$(document).ready(function() {
    $("#editor").jqxEditor({
        width: "100%",
        createCommand: function (name) {
            switch (name) {
                case 'topic link':
                    return {
                        type: 'button',
                        tooltip: 'Topic Link',
                        init: function (widget) {
                            widget.jqxButton({height: 25, width: 40});
                            widget.html("<span style='line-height: 23px;'>Topic Link</span>");
                        },
                        refresh: function (widget, style) {
                            // go here when selection changes
                        },
                        action: function (widget, editor) {
                            // action of command
                            editor.jqxEditor('print');
                        }
                    };
//                case 'tag link':
            }
        }
    });
});

function markUp(type) {
    // get editor textarea object
    var editor = $('#editor');
    // get selected text, length of entire text, start and end index of selected text
    var text = window.getSelection();
    var len = editor.val().length;
    var start = editor[0].selectionStart;
    var end = editor[0].selectionEnd;

    // modify text depending on the type of markUp
    switch (type) {
        case 'boldlink':    // bold link
            text = ('[[').concat(text).concat(']]');
            break;
        case 'link':        // regular link
            text = ('{{').concat(text).concat('}}');
            break;
        default:
            break;
    }

    // replace selected text with markup text
    editor.val(editor.val().substring(0, start) + text + editor.val().substring(end, len));
}

// allows for wiki markup shortcut keys
$(document).on('keydown', '#editor', function (e) {
    if (e.ctrlKey && e.keyCode === 76) {
        if (e.shiftKey) {
            markUp('boldlink'); // bold hyperlink
        } else {
            markUp('link');     // regular hyperlink
        }
    }
});