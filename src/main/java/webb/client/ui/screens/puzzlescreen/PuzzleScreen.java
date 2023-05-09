package webb.client.ui.screens.puzzlescreen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.awt.Container;
import java.io.DataOutput;
import java.util.concurrent.Future;
import javax.swing.SpringLayout;
import webb.client.authentication.AuthenticationManager;
import webb.client.ui.WebbWindow;
import webb.client.ui.constants.WebbAudio;
import webb.client.ui.helpers.http.FutureReply;
import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.RequestType;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.client.ui.popup.congratulations.PopupCongratulations;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.screens.puzzlescreen.StopWatch.StopWatchCallback;
import webb.client.ui.screens.puzzlescreen.confetti.BackgroundConfetti;
import webb.client.ui.testing.DummyData.DummyPlayPuzzleData;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;
import webb.shared.dtos.puzzle.user.created.CreateUserPuzzleDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;

/**
 * The screen that displays the puzzle, that the user interacts with.
 * This screen also has the sidebar, which displays the puzzle number, stars remaining, etc.
 */
public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;
    private StopWatch stopWatch;

    private PuzzleLevelDTO puzzleToResetTo;

    private BackgroundConfetti confettiMachine;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        confettiMachine = new BackgroundConfetti();
        layout.putConstraint(SpringLayout.NORTH, confettiMachine, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, confettiMachine, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, confettiMachine, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, confettiMachine, 0, SpringLayout.WEST, contentPane);
        this.add(confettiMachine);

        //------------------ SIDEBAR ------------------
        sidePanel = new PuzzleSideScreen(this);
        layout.putConstraint(SpringLayout.EAST, sidePanel, -10, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, -10, SpringLayout.HEIGHT, contentPane);
        this.add(sidePanel);

        //------------------ PUZZLE COMPONENT ------------------
        puzzleComponent = new PuzzleComponent(this);
        layout.putConstraint(SpringLayout.NORTH, puzzleComponent, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, puzzleComponent, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, puzzleComponent, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, puzzleComponent, -10, SpringLayout.WEST, sidePanel);
        this.add(puzzleComponent);

        // Populate with dummy data for testing
        //populateWithDummyData();

    }

    /**
     * Sets the puzzle to display on the screen.
     * @param puzzle The puzzle to display.
     * TODO: Finish this method once we have real data!
     */
    public void setPuzzle(PuzzleLevelDTO puzzle) {
        confettiMachine.enableConstantConfetti(false);
        this.puzzleToResetTo = puzzle;
        sidePanel.setStarsRemaining(puzzle.getTotalStars(), puzzle.getTotalStars());
        sidePanel.setPuzzleNumber(puzzle.getId(), puzzle.getNumStars());
        puzzleComponent.setPuzzle(puzzle);

        if(stopWatch != null) {
            stopWatch.stop();
        }

        //System.out.println("Start timer!");
        stopWatch = new StopWatch(new StopWatchCallback() {
            @Override
            public void updateLabels(long currentTime) {
                sidePanel.setTimeRemaining(currentTime);
                // System.out.println("Time remaining: " + currentTime);
            }
        });

        sidePanel.setPlayersCompleted(puzzle.getSolvedByNumPlayers());

        stopWatch.start();

        tellServerWeLoadedThePuzzle();
    }

    /**
     * Resets the puzzle board by setting it to the puzzle that was last set.
     */
    public void reset() {
        if(this.puzzleToResetTo == null) {
            System.err.println("Puzzle to reset to is null! Returning...");
            return;
        }
        setPuzzle(puzzleToResetTo);
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_IN_GAME);
    }

    protected void onPuzzleComplete() {
        confettiMachine.enableConstantConfetti(true);
        System.out.println("Puzzle complete!");
        stopWatch.stop();
        //confettiMachine.enableConstantConfetti(true);
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_PUZZLE_COMPLETE);

        final long time = stopWatch.getTime();

        sendWeFinishedTheLevel((unused) -> {
            HTTPRequestOptions<UserDTO> options = new HTTPRequestOptions<>();
            WebbWebUtilities.makeRequestAsync("users/" + AuthenticationManager.getInstance().getCurrentUser().getUsername(), UserDTO.class, options, (response) -> {
                showPopup(new PopupCongratulations(this, time, response.getStats()));
            });
        });



    }

    protected PuzzleComponent getPuzzleComponent() {
        return puzzleComponent;
    }

    protected void exitPuzzle() {
        stopWatch.stop();
        this.switchScreenTo(ScreenType.SELECT_PUZZLE);
    }

    @Override
    public void onShow() {
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_IN_GAME);
    }

    private boolean weCanSendTheServerUpdates = false;
    private void tellServerWeLoadedThePuzzle() {
        weCanSendTheServerUpdates = false;
        System.out.println("Telling server we loaded the puzzle");
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setRequestType(RequestType.POST);

        ObjectMapper mapper = new ObjectMapper();

        CreateUserPuzzleDTO dto = new CreateUserPuzzleDTO(
                AuthenticationManager.getInstance().getCurrentUser().getUsername(),
                this.puzzleComponent.getPuzzleLevel().getId()
        );

        final ObjectNode node = mapper.valueToTree(dto);


        options.setPostData(node);
        options.setShouldDisplayError(false);

        WebbWebUtilities.makeRequestAsync("puzzles/users", options, (response) -> {
            System.out.println("Server responded to puzzle load request!");
            weCanSendTheServerUpdates = true;
        });
    }

    protected void sendWeFinishedTheLevel(FutureReply<Void> callback) {
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setRequestType(RequestType.PUT);
        options.setShouldDisplayError(false); //if this request fails, oh well.

        ObjectMapper mapper = new ObjectMapper();
        UserPuzzleDTO dto = puzzleComponent.getAsDTO();
        dto.setCompleted(true);
        dto.setSolveTime(stopWatch.getTime());
        ObjectNode node = mapper.valueToTree(dto);

        options.setPostData(node);

        final String endpoint = "puzzles/users/" + AuthenticationManager.getInstance().getCurrentUser().getUsername() + "/" + this.puzzleComponent.getPuzzleLevel().getId();
        WebbWebUtilities.makeRequestAsync(endpoint, options, (response) -> {
            System.out.println("Server responded to puzzle update request!");
            callback.reply(null);
        });
    }

    protected void sendGameUpdatesToServer() {
        if(!weCanSendTheServerUpdates) {
            System.out.println("We can't send the server updates yet!");
            return;
        }
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setRequestType(RequestType.PUT);
        options.setShouldDisplayError(false); //if this request fails, oh well.

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.valueToTree(puzzleComponent.getAsDTO());

        options.setPostData(node);

        final String endpoint = "puzzles/users/" + AuthenticationManager.getInstance().getCurrentUser().getUsername() + "/" + this.puzzleComponent.getPuzzleLevel().getId();
        WebbWebUtilities.makeRequestAsync(endpoint, options, (response) -> {
            System.out.println("Server responded to puzzle update request!");
        });
    }
}
