package org.unibl.etf.central.server.service;

import java.util.List;

import org.unibl.etf.central.server.repository.VehiclePartRepository;
import org.unibl.etf.model.VehiclePart;

public class VehiclePartService {
	
	private final VehiclePartRepository partsRepository;

	public VehiclePartService() {
		partsRepository = VehiclePartRepository.getInstance();
	}
	
	public List<VehiclePart> getAllParts() {
		return partsRepository.getParts();
	}
	
	 public VehiclePart findByCode(String code) {
		return partsRepository.getParts()
                .stream()
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElse(null);	 
	 }
	 
	 public boolean createPart(VehiclePart part) {
		 if (findByCode(part.getCode()) != null) {
			 return false;
		 }
		 partsRepository.save(part);
		 return true;
	 }
	 
	 public VehiclePart removePart(String code) {
		 VehiclePart existing = findByCode(code);
		 if (existing != null) {
			 partsRepository.delete(code);
			 return existing;
		 }
		 return null;
	 }

	 public VehiclePart updatePart(String code, VehiclePart part) {
		 VehiclePart existing = findByCode(code);
		 if (existing == null) {
			 return null;
		 }
		 removePart(code);
		 partsRepository.save(part);
		 return part;
	 }

}
