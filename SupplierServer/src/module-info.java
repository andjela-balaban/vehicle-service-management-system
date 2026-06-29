/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module SupplierServer2 {
	requires transitive SharedModule;
	requires MQ;
	requires AccountingServerRMI;
	requires org.jsoup;
	requires java.rmi;
	requires java.logging;
	
	exports org.unibl.etf.supplier.server;
	exports org.unibl.etf.supplier.service;
	exports org.unibl.etf.supplier.repository;
}