package P3;

import java.util.ArrayList;

public class Person {

	private final String name;
	 ArrayList<Person> list=new ArrayList<Person>();
	 public Person(String name) {
	  this.name=name;
	 }
	 public void friend(Person fname) {
	  this.list.add(fname);
	 }
	 public ArrayList<Person> friendship() {
	  return list;
	 }
	 public String n() {
	  return name;
	 }
	

}
