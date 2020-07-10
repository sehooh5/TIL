# exam2 : setter injection, 프리픽스

#### bean1.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!-- 일단 자료넣고 -->
<bean id="h1" class="exam2.Homework">
	<constructor-arg name="homeworkName"  value="국어" />
</bean>
<bean id="h2" class="exam2.Homework">
	<constructor-arg name="homeworkName"  value="영어" />
</bean>
<bean id="h3" class="exam2.Homework">
	<constructor-arg name="homeworkName"  value="수학" />
</bean>

[ref로 값을 가져와주는데 property name에는 setter 메서드의 이름을 설정해줌]
<bean id="st1" class="exam2.Student">
	<property name="name"  value="둘리"/>
	<property name="myHomework"  ref="h1"/>
</bean>
<bean id="st2" class="exam2.Student">
	<property name="name"  value="도우너"/>
	<property name="myHomework"  ref="h2"/>
</bean>
<bean id="st3" class="exam2.Student">
	<property name="name"  value="또치"/>
	<property name="myHomework"  ref="h3"/>
</bean>

</beans>






```

#### bean2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:c="http://www.springframework.org/schema/c"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


<bean id="h1"  class="exam2.Homework" 	c:homeworkName="국어" />
<bean id="h2"  class="exam2.Homework" 	c:_0="영어" />
<bean id="h3"  class="exam2.Homework" 	c:_0="수학" />

<!-- setter injection 은 p: 즉 property 로 인덱스값이 아닌 setter 이름을 줘야한다
if setterName ===> p:name -->
<bean id="st1" class="exam2.Student"  p:myHomework-ref="h1"  p:name="둘리"/>                           	
<bean id="st2" class="exam2.Student"  p:myHomework-ref="h2"  p:name="도우너"/>                           	
<bean id="st3" class="exam2.Student"  p:myHomework-ref="h3"  p:name="또치"/>                           	

</beans>

```

#### Homework.java

```java
package exam2;

public class Homework{
	private String homeworkName ;
	
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public Homework(String homeworkName) {
		super();
		this.homeworkName = homeworkName;
	}
	 
}

```

#### Student.java

```java
package exam2;

public class Student{
	private String name ;
	private Homework myHomework;
	
	public Student() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Homework getMyHomework() {
		return myHomework;
	}

	public void setMyHomework(Homework myHomework) {
		this.myHomework = myHomework;
	}
}

```

#### StudyApp.java

```java
package exam2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudyApp {
	public static void main(String[] args) {
		ApplicationContext factory =
				   new ClassPathXmlApplicationContext("exam2/bean2.xml");
		
		//toString() 안만들고 출력하는 방법
        //Homework 의 homeworkName 가져올때 Student의 객체에서 getMyHomework 메서드 호출 후 getHomeworkName 호출해줘야 한다
		Student st1 = (Student)factory.getBean("st1");
		System.out.println(st1.getName()+"는 "+st1.getMyHomework().getHomeworkName()+"를 학습합니다.");
		
		Student st2 = (Student)factory.getBean("st2");
		System.out.println(st2.getName()+"는 "+st2.getMyHomework().getHomeworkName()+"를 학습합니다.");
		
		Student st3 = (Student)factory.getBean("st3");
		System.out.println(st3.getName()+"는 "+st3.getMyHomework().getHomeworkName()+"을 학습합니다.");
		
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}

```

