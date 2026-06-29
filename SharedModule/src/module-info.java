/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module SharedModule {
	requires java.logging;
//	requires junit;
//	requires org.junit.jupiter.api;
	requires jakarta.xml.bind;
    requires jakarta.activation;
//	requires jdk.jfr;
	requires org.apache.pdfbox;
	
	exports org.unibl.etf.logger;
	exports org.unibl.etf.model;
	exports org.unibl.etf.dto;
	exports org.unibl.etf.pdf;
	exports org.unibl.etf.properties;
	exports org.unibl.etf.exception;
	
    opens org.unibl.etf.model to jakarta.xml.bind;
}