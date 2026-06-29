/**
 * 
 */
/**
 * @author andjelabalaban
 *
 */
module MQ {
	requires transitive SharedModule;
	requires com.rabbitmq.client;
	requires com.fasterxml.jackson.databind;
	requires org.slf4j;
	
	exports org.unibl.etf.mq.sender;
	exports org.unibl.etf.mq.receiver;
}