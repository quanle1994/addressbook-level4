package seedu.address.logic.commands;

//@@author quanle1994

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalUsers.getTypicalAccount;
import static seedu.address.testutil.TypicalUsers.userBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Config;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Logic;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.user.ReadOnlyUser;
import seedu.address.model.user.User;
import seedu.address.ui.UiManager;

/**
 * Contains integration tests (interaction with the Model) for {@code LockCommand}.
 */
public class LockCommandIntegrationTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs(), getTypicalAccount(),
                new Config());
    }

    @Test
    public void execute_duplicateUserException() throws Exception {
        User invalidUser = new User("a", "555555555555555555", "e");

        thrown.expect(CommandException.class);
        thrown.expectMessage(LockCommand.MESSAGE_EXISTING_USER);

        prepareLockCommand(invalidUser, model).execute();
    }

    @Test
    public void execute_newUser_success() throws Exception {
        User validUser = new User("e", "555555555555555555", "e");

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs(),
                model.getAccount(), new Config());
        expectedModel.persistUserAccount(validUser);

        assertCommandSuccess(prepareLockCommand(validUser, model), model,
                String.format(LockCommand.MESSAGE_SUCCESS, validUser), expectedModel);
    }

    @Test
    public void execute_removeUser_success() throws Exception {
        ReadOnlyUser validUser = userBuilder("a", "1", "a");
        Model expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs(),
                model.getAccount(), new Config());
        expectedModel.deleteUser(validUser.getUserId(),
                validUser.getPassword()
        );

        assertCommandSuccess(prepareRemoveUserCommand("a", "a", true, model), model,
                String.format(RemoveUserCommand.MESSAGE_REMOVE_USER_SUCCESS, "a"), expectedModel);
    }

    /**
     * Generates a new {@code LockCommand} which upon execution, adds {@code user} into the {@code model}.
     */
    private LockCommand prepareLockCommand(User user, Model model) {
        LockCommand command = new LockCommand(user.getUserId(), user.getPassword());
        UserPrefs userPrefs = new UserPrefs();
        Config config = new Config();
        Logic logic = null;
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new Config(),
                new UiManager(logic, config, userPrefs));
        return command;
    }

    /**
     * Generates a new {@code RemoveUserCommand} which upon execution, removes {@code user} into the {@code model}.
     */
    private RemoveUserCommand prepareRemoveUserCommand(String userName, String password, boolean cascade, Model model) {
        RemoveUserCommand command = new RemoveUserCommand(userName, password, cascade);
        UserPrefs userPrefs = new UserPrefs();
        Config config = new Config();
        Logic logic = null;
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new Config(),
                new UiManager(logic, config, userPrefs));
        return command;
    }
}
