/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module Shared {
	requires java.logging;
	requires junit;
	requires org.junit.jupiter.api;
	requires jakarta.xml.bind;
    requires jakarta.activation;
	requires jdk.jfr;
	requires org.apache.pdfbox;
    opens org.unibl.etf.model to jakarta.xml.bind;
}