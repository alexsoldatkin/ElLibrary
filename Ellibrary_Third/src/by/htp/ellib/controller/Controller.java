package by.htp.ellib.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.CommandProvider;
import by.htp.ellib.dao.impl.ConnectionPoolException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String PARAMETER_COMMAND = "command";
	
	private final CommandProvider provider = new CommandProvider();
	

	
    public Controller() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName = request.getParameter(PARAMETER_COMMAND);
		Command command = provider.getCommand(commandName);
		try {
			command.execute(request, response);
		} catch (ConnectionPoolException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
		
	}
	 
	

}
