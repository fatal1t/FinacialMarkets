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
            Session session = new Session("495753", "e83f7cea", "DEMO");
            LoginController Login = new LoginController(session, "495753", "e83f7cea", "DEMO");
            Login.DoLogin();            
            System.out.println(session.isIsLogged());
            
            //testovaci prasecinas
            //Core core = Core.getInstance();
            //core.InitializeCore(session);
            //core.start();
            /*
		
		try {
			
			// Create new connector
			SyncAPIConnector connector = new SyncAPIConnector(ServerEnum.DEMO);
	        
			// Create new credentials
			// TODO: Insert your credentials
			Credentials credentials = new Credentials("495753", "e83f7cea");
			
			// Create and execute new login command
	        LoginResponse loginResponse = APICommandFactory.executeLoginCommand(
	        		connector, 		// APIConnector
	        		credentials		// Credentials
	        );
	        
	        // Check if user logged in correctly
	        if(loginResponse.getStatus() == true) {
	        	
	        	// Print the message on console
	        	System.out.println("User logged in");
	        	
	        	// Create and execute all symbols command (which gets list of all symbols available for the user)
	        	AllSymbolsResponse availableSymbols = APICommandFactory.executeAllSymbolsCommand(connector);
	        	
	        	// Print the message on console
	        	System.out.println("Available symbols:");
	        	
	        	// List all available symbols on console
	        	for(SymbolRecord symbol : availableSymbols.getSymbolRecords()) {
	        		System.out.println("-> " + symbol.getSymbol() + " Ask: " + symbol.getAsk() + " Bid: " + symbol.getBid());
	        	}
                        StreamingListener listener = new StreamingListener()
                        {
                            public void receiveTradeRecord(STradeRecord tradeRecord) {
                                System.out.println("Stream trade record: " + tradeRecord);
                            }
                            @Override
                            public void receiveTickRecord(STickRecord tickRecord) {
                                System.out.print("Stream tick record: " + tickRecord);
                            }
                        };
                        connector.connectStream(listener);
                        connector.subscribePrice("EURUSD");                       
	
	        } else {
	        	
	        	// Print the error on console
	        	System.err.println("Error: user couldn't log in!");	  
	        	
	        }
	        
	        // Close connection
	        connector.close();
	        System.out.println("Connection closed");
	        
	    // Catch errors
	    } catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APICommandConstructionException | APICommunicationException | APIReplyParseException | APIErrorResponse e) {
			e.printStackTrace();
		}
                    */
	}
}							

