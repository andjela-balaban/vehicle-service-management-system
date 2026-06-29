package org.unibl.etf.central.server.repository;

import redis.clients.jedis.Jedis;
import org.unibl.etf.model.VehiclePart;
import org.unibl.etf.properties.ConfigManager;
import java.util.*;

public class VehiclePartRepository {
	
	private static final String HOST = ConfigManager.REDIS_HOST;
    private static int PORT = ConfigManager.REDIS_PORT;
	private Jedis jedis;
	private List<VehiclePart> parts;
	private static VehiclePartRepository instance = null;

    private VehiclePartRepository(Jedis jedis) {
        this.jedis = jedis;
        this.parts = new ArrayList<>();
        load();
    }

    // The key value is "part:{code}"
    private String getKey(String code) {
        return "part:" + code;
    }
    
    public void save(VehiclePart part) {
        String key = getKey(part.getCode());
        jedis.hmset(key, part.toMap());
        update(part);
    }
    
    public boolean update(VehiclePart part) {
    	parts.remove(part); 
    	return parts.add(part);
    }
    
    public boolean delete(String code) {
        jedis.del(getKey(code));
        return parts.removeIf(p -> p.getCode().equals(code));
    }
    
    private void load() {
    	parts.clear();
        Set<String> keys = jedis.keys("part:*");
        for (String key : keys) {
            String code = key.split(":")[1];
            VehiclePart part = findByCode(code);
            if (part != null) {
            	parts.add(part);
            }
        }
    }
    
    private VehiclePart findByCode(String code) {
        Map<String, String> map = jedis.hgetAll(getKey(code));
        if (map == null || map.isEmpty()) {
            return null;
        }
        VehiclePart part = new VehiclePart();
        part.setCode(code);
        part.setName(map.get("name"));
        part.setManufacturer(map.get("manufacturer"));
        part.setPrice(Double.parseDouble(map.get("price")));
        part.setAvailableQuantity(Integer.parseInt(map.get("availableQuantity")));
        part.setDescription(map.get("description"));
        return part;
    }
    
    public List<VehiclePart> getParts() { 
    	return parts;
    }
    
    public static VehiclePartRepository getInstance() {
    	if(instance == null) {
    		Jedis jedis = new Jedis(HOST, PORT);
    		instance = new VehiclePartRepository(jedis);
    	}
    	return instance;
    }
}
