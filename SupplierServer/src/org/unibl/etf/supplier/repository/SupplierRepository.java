package org.unibl.etf.supplier.repository;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.unibl.etf.model.Supplier;
import org.unibl.etf.properties.ConfigManager;

public class SupplierRepository {
	
	private static String FILE_PATH = ConfigManager.SUPPLIERS_PATH;
	private List<Supplier> suppliers = new ArrayList<>();
	private static SupplierRepository instance = null;
	
	private SupplierRepository() {
		loadSuppliers(FILE_PATH);
	}
	
	private void loadSuppliers(String filePath) {
		try(BufferedReader in = new BufferedReader(new FileReader(filePath))){
			 String line;
			 while((line = in.readLine()) != null) {
				 String parts[] = line.split("#");
				 if(parts.length == 3) {
					 String name = parts[0];
					 String host = parts[1];
					 int port = Integer.parseInt(parts[2]);
					 suppliers.add(new Supplier(name, host, port));
				 }
			 }
		} catch(IOException e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "Exception: ", e);
		}
	}
	
	public List<Supplier> getSuppliers(){
		return suppliers;
	}
	
	public static SupplierRepository getInstance() {
		if(instance == null) {
    		instance = new SupplierRepository();
    	}
    	return instance;
	}
}
