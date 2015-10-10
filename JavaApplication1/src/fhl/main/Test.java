package fhl.main;

import java.io.IOException;
import java.net.UnknownHostException;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.STradeRecord;
import pro.xstore.api.message.records.SymbolRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData.ServerEnum;
import pro.xstore.api.sync.SyncAPIConnector;

public class Test {

	public static void main(String[] args) {
		
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
		} catch (APICommandConstructionException e) {
			e.printStackTrace();
		} catch (APICommunicationException e) {
			e.printStackTrace();
		} catch (APIReplyParseException e) {
			e.printStackTrace();
		} catch (APIErrorResponse e) {
			e.printStackTrace();
		}
	}
}							

