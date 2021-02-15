class Employee
{
	private int id;
	private String name;
	private double salary;
	Employee()
	{
		
		
	}
	Employee(int id,String name,Double salary)
	{
		this.id=id;
		this.name=name;
		this.salary=salary;
	}
	
	public void setId(int id)
	{
		this.id=id;
		
		
	}
	public int getId()
	{
		return id;
		
		
	}
	
	public void setName(String name)
	{
		
		this.name=name;
		
	}
	public String getName()
	{
		return name;
	}
	public void setSalary(Double salary)
	{
		this.salary=salary;
	}
	public double getSalary()
	{
		return salary;
	}
	
}