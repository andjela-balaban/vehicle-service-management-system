module ClientApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires SharedModule;
	requires ChatApp;
	requires java.net.http;
	requires java.ws.rs;
	requires java.logging;
    requires java.xml;  
	
	opens application to javafx.graphics, javafx.fxml;
}
