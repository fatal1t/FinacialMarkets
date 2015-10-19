package fhl.main;

import fhl.main.core.Core;
import fhl.main.logincontroller.LoginController;
import fhl.main.sessionstorage.Session;

/**
 *
 * @author Filip
 */
public class Main {

	public static void main(String[] args) {
            Configuration config = Configuration.getInstance("D:\\Users\\Filip\\GitHub\\FinacialMarkets"
                    + "\\JavaApplication\\build\\classes\\fhl\\main\\config.config");
            Session session = new Session(config.getUsername(), config.getPassword(),config.getServerType());
            LoginController Login = new LoginController(session);
            Login.DoLogin();            
            System.out.println(session.isIsLogged());
            
            //testovaci prasecinas
            Core core = Core.getInstance();
            core.InitializeCore(session);
            core.start();
            
	}
}							

