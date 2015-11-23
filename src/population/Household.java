package population;

import java.util.ArrayList;

public class Household
{
	ArrayList<Consumer> familyMembers;
	
	public Household()
	{
		familyMembers = new ArrayList<Consumer>();
	}
	
	public void addToHousehold(Consumer newFamiliyMember)
	{
		familyMembers.add(newFamiliyMember);
		newFamiliyMember.setHousehold(this);
	}
	
	public ArrayList<Consumer> getFamilyMembers()
	{
		return familyMembers;
	}
	
	public int getAmountOfFamiliyMembers()
	{
		return familyMembers.size();
	}
}