package by.htp.ellib.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.ellib.controller.command.impl.AddNewBookCommand;
import by.htp.ellib.controller.command.impl.AuthorizationCommand;
import by.htp.ellib.controller.command.impl.ChangeLocale;
import by.htp.ellib.controller.command.impl.DeleteBookCommand;
import by.htp.ellib.controller.command.impl.FindByAuthorCommand;
import by.htp.ellib.controller.command.impl.FindByGenreCommand;
import by.htp.ellib.controller.command.impl.FindByTitleCommand;
import by.htp.ellib.controller.command.impl.GoToAddNewBookPageCommand;
import by.htp.ellib.controller.command.impl.GoToAdminPageCommand;
import by.htp.ellib.controller.command.impl.GoToDeleteBookCommand;
import by.htp.ellib.controller.command.impl.GoToFindByAuthorCommand;
import by.htp.ellib.controller.command.impl.GoToFindByGenreCommand;
import by.htp.ellib.controller.command.impl.GoToFindByTitleCommand;
import by.htp.ellib.controller.command.impl.GoToIndexPageCommand;
import by.htp.ellib.controller.command.impl.GoToRegistrationCommand;
import by.htp.ellib.controller.command.impl.RegistrationCommand;
import by.htp.ellib.controller.command.impl.SeeAllUsersCommand;
import by.htp.ellib.controller.command.impl.ShowAllBooksCommand;

public class CommandProvider {
	
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("go_to_registration_page", new GoToRegistrationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("go_to_index", new GoToIndexPageCommand());
		commands.put("change_locale", new ChangeLocale());
		commands.put("admin", new GoToAdminPageCommand());
		commands.put("add_new_book", new AddNewBookCommand());
		commands.put("see_all_users", new SeeAllUsersCommand());
		commands.put("go_to_add_new_book", new GoToAddNewBookPageCommand());
		commands.put("find_by_author", new FindByAuthorCommand());
		commands.put("find_by_title", new FindByTitleCommand());
		commands.put("find_by_genre", new FindByGenreCommand());
		commands.put("go_to_find_by_author", new GoToFindByAuthorCommand());
		commands.put("go_to_find_by_title", new GoToFindByTitleCommand());
		commands.put("go_to_find_by_genre", new GoToFindByGenreCommand());
		commands.put("show_all_books", new ShowAllBooksCommand());
		commands.put("delete_book", new DeleteBookCommand());
		commands.put("go_to_delete_book", new GoToDeleteBookCommand());


	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}
