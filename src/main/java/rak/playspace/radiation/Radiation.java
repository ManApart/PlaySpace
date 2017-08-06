package rak.playspace.radiation;

import java.util.ArrayList;

import rak.utility.MathFunctions;

public class Radiation {
	private RadiationType type;
	private int radiationLevel;
	private int radiationProduced;
	private boolean isBlocked;
	
	public Radiation(RadiationType type){
		this.type = type;
	}
	
	public void produceRadiation() {
		setRadiationLevel(radiationLevel + radiationProduced);
	}

	public boolean shouldRadiate(){
		return getRadiationLevel() > 0;
	}
	
	public void radiate(ArrayList<Radiation> neighbors) {
		ArrayList<Radiation> validNeighbors = getNeighborsToRadiateTo(neighbors);
		if (validNeighbors.size() > 1){
			int totalRadiation = getTotalRadiation(validNeighbors);
			int amountForEachRadiation = totalRadiation/validNeighbors.size();
			
			setRadiationLevels(validNeighbors, amountForEachRadiation);
		}
	}
	
	private ArrayList<Radiation> getNeighborsToRadiateTo(ArrayList<Radiation> neighbors) {
		ArrayList<Radiation> validNeighbors = new ArrayList<>();
		for (Radiation neighbor: neighbors){
			if (isValidToRadiateTo(neighbor)){
				validNeighbors.add(neighbor);
			}
		}
		validNeighbors.add(this);
		return validNeighbors;
	}

	private boolean isValidToRadiateTo(Radiation neighbor) {
		return !neighbor.isRadiationBlocked() 
				&& getRadiationLevel() > neighbor.getRadiationLevel();
	}
	
	private int getTotalRadiation(ArrayList<Radiation> radiations) {
		int total = 0;
		for (Radiation radiation : radiations){
			total += radiation.getRadiationLevel();
		}
		return total;
	}
	
	private void setRadiationLevels(ArrayList<Radiation> radiations, int radiationLevel) {
		for (Radiation radiation : radiations){
			radiation.setRadiationLevel(radiationLevel);
		}
		
	}

	public RadiationType getRadiationType(){
		return type;
	}
	
	public RadiationType getType() {
		return type;
	}

	public int getRadiationLevel() {
		return radiationLevel;
	}
	
	public void setRadiationLevel(int radiationLevel) {
		radiationLevel = MathFunctions.clamp(radiationLevel, 0, 100);
		this.radiationLevel = radiationLevel;
	}

	public void setRadiationProduced(int amount) {
		this.radiationProduced = amount;
	}

	public boolean isRadiationBlocked() {
		return isBlocked;
	}

	public void setRadiationBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}


}
