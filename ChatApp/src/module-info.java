/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module ChatApp {
	requires transitive SharedModule;
	requires java.logging;
	
	exports org.unibl.etf.chat.client;
	exports org.unibl.etf.chat.server;
}