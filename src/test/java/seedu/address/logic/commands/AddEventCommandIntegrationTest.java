package seedu.address.logic.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.address.commons.core.Config;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddEventCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs(), new Account());
    }

    @Test
    public void execute_newEvent_success() throws Exception {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs(), new Account());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(prepareCommand(validEvent, model), model,
                String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    /*@Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event eventInList = new Event(model.getEventBook().getEventList().get(0));
        assertCommandFailure(prepareCommand(eventInList, model), model, AddEventCommand.MESSAGE_DUPLICATE_EVENT);
    }*/

    /**
     * Generates a new {@code AddEventCommand} which upon execution, adds {@code event} into the {@code model}.
     */
    private AddEventCommand prepareCommand(Event event, Model model) {
        AddEventCommand command = new AddEventCommand(event);
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new Config());
        return command;
    }
}
