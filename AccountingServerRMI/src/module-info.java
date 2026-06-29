/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module AccountingServerRMI {
	requires java.rmi;
	requires transitive SharedModule;
	requires java.logging;
	
	exports org.unibl.etf.accounting.rmi;
}