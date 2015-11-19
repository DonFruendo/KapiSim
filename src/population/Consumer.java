package population;

public class Consumer
{
	public int age;
	public int wealth;
	public boolean gender;
	public boolean working;
	
	public Consumer(int age, int wealth, boolean gender, boolean working)
	{
		this.age = age;
		this.wealth = wealth;
		this.gender = gender;
		this.working = working;
	}
	
	
	public String toString()
	{
		return age + "\t" + wealth + "\t" + ((gender)? "male":"female") + "\t" + working;
	}
	
	
}
