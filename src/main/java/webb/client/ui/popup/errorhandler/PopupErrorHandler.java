package webb.client.ui.popup.errorhandler;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import webb.client.ui.popup.WebbPopup;

/**
 * A simple popup to display a stack trace.
 */
public class PopupErrorHandler extends WebbPopup {

    private final Throwable exception;

    /**
     * Creates a new error handler popup.
     * This popup will display the given exception.
     * @param exception The exception to display.
     */
    public PopupErrorHandler(Throwable exception) {
        super("Oh Snap! An error occurred!");
        this.setExitButton(true);
        this.exception = exception;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setForeground(Color.decode("#E65864"));

        textArea.setText(getStackTrace(exception));

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        layout.putConstraint(SpringLayout.NORTH, scroll, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scroll, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, scroll, -10, SpringLayout.EAST, contentPane);

        contentPane.add(scroll);
    }

    static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
