package com.properties;


public class teacher
        implements java.io.Serializable
{
    //	private String name1;
    private String name;
    //	private Person student;
    transient  private int age;  //表示该属性不进行序列化和反序列化
    public teacher(String name)
    {
        this.name = name;
//		this.student = student;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }


    //	public void setStudent(Person student)
//	{
//		this.student = student;
//	}
//	public Person getStudent()
//	{
//		return this.student;
//	}
    private static final long serialVersionUID = 9495495;   //类版本号
}