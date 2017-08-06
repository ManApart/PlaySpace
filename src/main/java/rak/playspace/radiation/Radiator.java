package rak.playspace.radiation;

import java.util.ArrayList;
import java.util.HashMap;

public class Radiator {
	private HashMap<RadiationType, Radiation> radiations;

	public Radiator(){
		radiations = createRadiations();
	}
	
	private HashMap<RadiationType, Radiation> createRadiations() {
		HashMap<RadiationType, Radiation> radiations = new HashMap<RadiationType, Radiation>();
		for (RadiationType type : RadiationType.values()){
			radiations.put(type, new Radiation(type));
		}
		return radiations;
	}
	
	public void setRadiation(RadiationType type, Radiation radiation){
		radiations.put(type, radiation);
	}

	public int getRadiationLevel(RadiationType type){
		return radiations.get(type).getRadiationLevel();
	}
	
	public boolean isRadiationBlocked(RadiationType type){
		return radiations.get(type).isRadiationBlocked();
	}
	
	public void produceRadiation(){
		for (Radiation radiation : radiations.values()){
			radiation.produceRadiation();
		}
	}

	public void radiate(ArrayList<Radiator> neighborRadiators) {
		for (Radiation radiation : radiations.values()){
			if (radiation.shouldRadiate()){
				ArrayList<Radiation> neighborRadiations = getRadiationNeighbors(radiation.getType(), neighborRadiators);
				radiation.radiate(neighborRadiations);
			}
		}
		
	}

	private ArrayList<Radiation> getRadiationNeighbors(RadiationType type, ArrayList<Radiator> neighborRadiators) {
		ArrayList<Radiation> nieghborRadiations = new ArrayList<>();
		for (Radiator radiator : neighborRadiators){
			Radiation neighbor = radiator.radiations.get(type);
			nieghborRadiations.add(neighbor);
		}
		return nieghborRadiations;
	}
}
