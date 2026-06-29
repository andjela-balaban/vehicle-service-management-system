module SupplierApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires SharedModule;
	requires SupplierServer2;
	requires MQ;
	requires java.logging;
	
	opens application to javafx.graphics, javafx.fxml;
}
