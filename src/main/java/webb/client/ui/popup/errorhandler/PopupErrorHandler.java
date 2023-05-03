package webb.client.ui.popup.errorhandler;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbCustomScrollpane;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.popup.WebbPopup;

/**
 * A simple popup to display a stack trace.
 */
public class PopupErrorHandler extends WebbPopup {

    private final Throwable exception;

    private static String lastFunnyTitle = "";
    private static String lastMemberName = "";

    private static final String[] FUNNY_TITLES = {
            "Oh Snap! An error occurred!",
            "Dammit %name%, I told you to fix that bug!",
            "Oh no, not again!",
            "Seriously, how did you manage to break this?",
            "I'm sorry, Dave. I'm afraid I can't do that.",
            "Seriously? I thought we fixed that!",
            "Something went wrong. I blame %name%.",
            "Well. This is awkward.",
            "Well. That's not good.",
            "Well. That was unexpected.",
            "Well. That's not supposed to happen.",
            "It looks like something went wrong.",
            "It worked on my machine!",
            "Who pushed broken code to master?",
            "Who pushed broken code to master? %name%?",
            "Who pushed broken code to main?",
            "Who pushed broken code to main? %name%?",
            "It was %name%'s fault, I swear!",
            "Haha %name%! Your code has a bug in it!",
            "Well fooey.",
            "Well that's just great.",
            "Well that's just great. Thanks %name%.",
            "Thanks %name%. You broke it.",
            "If you see this, %name% broke it.",
            "If you see this, something went very wrong.",
            "If you see this, something went very wrong. %name% probably broke it.",
            "afhdsfahldufhpewf WHY WHY WHY did this happen!!?",
            "Lesson learned: Don't let %name% code.",
            "Lesson learned: Don't write code at 3am.",
            "I should have gone to bed instead of coding.",
            "I should have paid more attention in class.",
            "I blame %name%.",
            "Have you tried turning it off and on again?",
            "Have you tried putting it in rice?",
            "Oh well. We tried.",
            "Lets hope this doesn't happen again.",
            "Shhh. Just pretend this never happened.",
            "Shhh. Just pretend that you didn't see this.",
            "Hey, make sure to tell %name% that they broke it.",
            "Please let %name% know this is broken.",
            "Lets git-blame this, and see who messed up this time!",
    };

    private static final String[] MEMBER_NAMES = {
            "Eric",
            "Chris",
            "Brandon",
            "Seth",
            "ChatGPT",
            "GitHub Copilot",
    };

    /**
     * Creates a new error handler popup.
     * This popup will display the given exception.
     * @param exception The exception to display.
     */
    public PopupErrorHandler(Throwable exception) {
        super(getRandomFunnyTitle());
        this.setExitButton(true);
        this.exception = exception;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        //textArea.setForeground(Color.decode("#E65864"));
        textArea.setForeground(Color.RED.darker());

        textArea.setText(getStackTrace(exception));

        WebbCustomScrollpane scroll = new WebbCustomScrollpane(
                textArea,
                WebbColors.c90,
                WebbColors.c6C,
                WebbColors.c6C.darker(),
                WebbColors.c6C.darker().darker()
        );

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        layout.putConstraint(SpringLayout.NORTH, scroll, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scroll, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, scroll, -10, SpringLayout.EAST, contentPane);

        contentPane.add(scroll);
    }

    private static String getRandomFunnyTitle() {

        //never repeat the same funny title twice in a row
        String funnyTitle = getRandomFromArr(FUNNY_TITLES);
        while (funnyTitle.equals(lastFunnyTitle)) {
            funnyTitle = getRandomFromArr(FUNNY_TITLES);
        }
        lastFunnyTitle = funnyTitle;

        //if the funny title doesn't contain a name, no need to replace anything
        if(!funnyTitle.contains("%name%")) {
            return funnyTitle;
        }

        //never repeat the same member name twice in a row
        String memberName = getRandomFromArr(MEMBER_NAMES);
        while (memberName.equals(lastMemberName)) {
            memberName = getRandomFromArr(MEMBER_NAMES);
        }
        lastMemberName = memberName;

        //replace %name% with a random member name, if it exists
        return funnyTitle.replace("%name%", memberName);
    }

    private static String getRandomFromArr(String[] arr) {
        return arr[(int) (Math.random() * arr.length)];
    }

    private static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
