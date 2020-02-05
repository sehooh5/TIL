# Sample

[TOC]

### Sample1 : 기본 작성 방법



#### HelloSpringApp.java

```java
package sample1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class HelloSpringApp {
	public static void main(String[] args) {
		//Spring DI 컨테이너 초기화
		ApplicationContext factory
		    	= new ClassPathXmlApplicationContext("sample1/beans.xml");
				//아규먼트로 설정된 xml 을 src 가서 찾는다(=src/sample1...)
				//beans.xml 대로 객체를 생성한다
		System.out.println("**** Container Initialization End ****");
		
		//DL : getBean
		MessageBean bean=(MessageBean)factory.getBean("messageBean");
		bean.sayHello();//아규먼트 없는     
		bean.sayHello("banana", 1500);//아규먼트 있는   
		//Spring 객체를 생성하면 한개만 생성해서 사용한다(싱글톤)
		//따라서 참조값이 밑에 세개 같다
		System.out.println(bean);
		System.out.println(factory.getBean("messageBean"));
		System.out.println(factory.getBean("messageBean"));
		((ClassPathXmlApplicationContext)factory).close();
	}
}

```



#### MessageBean.java

```java
package sample1;
//interface 는 확장성과 변환성이 좋아서 사용하는게 좋다
public interface MessageBean {
	public void sayHello();
	public void sayHello(String a, int b);
}

```



#### MessageBeaImpl.java

```java
package sample1;

public class MessageBeanImpl implements MessageBean{
	private String fruit;
	private int cost;	
	public MessageBeanImpl() {
		super();//조상의 생성자 호출
		System.out.println("MessageBeanImpl Default Constructor Call");
	}
	public MessageBeanImpl(String fruit) {
		super();
		this.fruit = fruit;
		System.out.println(fruit + " :  MessageBeanImpl Constructor Call");
	}

	public void setCost(int cost) {
		this.cost = cost;
		System.out.println("setCost() Call");
	}
	//에노테이션으로 Override 된것을 알려줘야한다
	@Override
	public void sayHello() {      
		System.out.println(fruit + "   " + cost);
	}

	@Override                     
	public void sayHello(String fruit, int cost) {   
		System.out.println(fruit + "   " + cost);
	}
}
```



#### beans.xml - 객체 관리 형식(정해져있음)

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	//빈이름 = id
	<bean id="messageBean" class="sample1.MessageBeanImpl" >
        //Constructor Injection
        //이 태그가 없으면 아규먼트 없는 생성자 호출
		<constructor-arg>
			<value>strawberry</value>
		</constructor-arg>	
        //Setter Injection
		<property name="cost">
			<value>3000</value>
		</property>
	</bean>	
</beans>
```



### Sample2 : Construct-arg 비교



#### FooTestApp.java

```java
package sample2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FooTestApp {
	public static void main(String[] args) {
        //interface 이름은 중요하지 않다
		ApplicationContext factory
    		= new ClassPathXmlApplicationContext("sample2/applicationContext.xml");
		System.out.println("**** Container Initialization End ****");
		
		System.out.println("\n\nScope(singleton/prototype)");
		InterFoo ob1=(InterFoo)factory.getBean("foo0");
		System.out.println(ob1);
		InterFoo ob2=(InterFoo)factory.getBean("foo0");
		System.out.println(ob2);
		InterFoo ob3=(InterFoo)factory.getBean("foo0");
		System.out.println(ob3);
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}

```



#### Foo.java

```java
package sample2;

public class Foo implements InterFoo{
	public Foo() {
		System.out.println("Foo - Create object");
	}
	public Foo(String str) {
		System.out.println(str);
		System.out.println("-----------------------");
	}
	public Foo(String str, int n) {
		System.out.println(str + "  " + n);
		System.out.println("-----------------------");
	}
	public Foo(String str, int n, boolean b) {
		System.out.println(str + "  " + n + "   " + b);
		System.out.println("-----------------------");
	}
	public Foo(Bar bar) {
		System.out.println("Create object example");
		System.out.println("----------------------- ");
	}
}
```



#### InterFoo.java

```java
package sample2;

public interface InterFoo {

}
```



#### Bar.java

```java
package sample2;

public class Bar {
	public Bar() {
		System.out.println("Bar - Create object");
	}
}
```



#### applicationContect.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<!-- 아규먼트 안받는애 -->
<bean id="foo0" class="sample2.Foo" scope="prototype"/> <!-- prototyp/sigleton(default) -->
<bean id="foo1" class="sample2.Foo">
	<constructor-arg value="XYZ"/>	
</bean>
<bean id="foo2" class="sample2.Foo">
	<constructor-arg  value="ABC"/>
	<constructor-arg  value="100"/>
</bean>
<bean id="foo3" class="sample2.Foo">
	<!-- index 주면 순서를 바꿔서 넣어줄 수 있다 -->
	<constructor-arg index="1"  type="int"  value="50"/>
	<constructor-arg index="0"  type="java.lang.String" value="xyz"/>
	<constructor-arg index="2"  type="boolean" value="true"/>
</bean>
<bean id="foo4" class="sample2.Foo">
	<!-- bar객체 받는애 생성 -->
	<constructor-arg ref="bar" />
</bean>
<bean id="bar"  class="sample2.Bar" />
	
</beans>
```



---



### Sample3



#### UserServiceApp.java

```java
package sample3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceApp {
	public static void main(String[] args) {
		ApplicationContext factory
        		=new ClassPathXmlApplicationContext("sample3/applicationContext.xml");
		System.out.println("** Container Initialization End **");
		
		UserService u1=(UserService)factory.getBean("userService");
		UserVo vo = (UserVo)factory.getBean("obj1");
		u1.addUser(vo);
		System.out.println(u1);
		System.out.println("----------------------------------------------------");
		
//두번째 아규먼트로 어떤 타입으로 받을지 지정해줘도 된다(class 객체형-확장자까지 줘야한다)
		UserService u2=factory.getBean("userService", UserService.class);
		UserVo vo2 = (UserVo)factory.getBean("obj2");
		u2.addUser(vo2);
		System.out.println(u2);		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```

#### UserService.java

```java
package sample3;

public interface UserService {
	public void addUser(UserVo vo);
}
```

#### UserServiceImpl.java

```java
package sample3;

public class UserServiceImpl implements UserService{
	public UserServiceImpl() {
		super();
		System.out.println("UserService Constructor Call");
	}

	@Override
	public void addUser(UserVo vo) {
		System.out.println("UserService : addUser() Method Call");
		System.out.println("NAME : " + vo.getUserName());
	}
}
```

#### UserVo.java

```java
package sample3;

public class UserVo {
	private String userName;

	public UserVo(String userName) {
		super();
		this.userName = userName;
		System.out.println("UserVO Constructor Call");
	}

	public String getUserName() {
		return userName;
	}
}
```

#### ApplicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="userService" class="sample3.UserServiceImpl" 
                                                    scope="prototype"/>
<bean id="obj1" class="sample3.UserVo">
	<constructor-arg value="Dooly"/>	
</bean>
<bean id="obj2" class="sample3.UserVo">
	<constructor-arg value="Duke"/>	
</bean>
</beans>
```



----



### Sample4 : Abstract 상속

- Abstract.getInstance 해야한다



#### TestApp.java

```java
package sample4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("sample4/app.xml");
		
		AbstractTest bean = (AbstractTest) factory.getBean("test");
		System.out.println("Today : " + bean.dayInfo() + ".");
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```

#### 

#### app.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="test" class="sample4.AbstractTest" 
                          factory-method="getInstance"/>

</beans>
```

#### AbstractTest.java

```java
package sample4;
import java.util.Calendar;
import java.util.GregorianCalendar;
public abstract class AbstractTest {
    //dayInfo 라는 메서드
	public abstract String dayInfo();	
    //getInstance 팩토리 메서드
	public static AbstractTest getInstance(){
		GregorianCalendar cal=new GregorianCalendar();
		int day=cal.get(Calendar.DAY_OF_WEEK);
		AbstractTest test = null;
		switch(day)
		{
			case 1 : test = new Sunday(); break;
			case 2 : test = new Monday(); break;
			case 3 : test = new Tuesday(); break;
			case 4 : test = new Wednesday(); break;
			case 5 : test = new Thursday(); break;
			case 6 : test = new Friday(); break;
			case 7 : test = new Saturday(); break;
		}
		return test;
	}
}
```

#### Monday-Sunday.java

```java
package sample4;


public class Monday extends AbstractTest{
	@Override
	public String dayInfo() {
		return "Monday";
	}
}

//이하 같음
```

#### TestApp.java

```java
package sample4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("sample4/app.xml");
		
		AbstractTest bean = (AbstractTest) factory.getBean("test");
		System.out.println("Today : " + bean.dayInfo() + ".");
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```



---



### Sample5 : Setter Injection&프리픽스 사용



#### BirthdayEx.java

```java
package sample5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BirthdayEx { 
	public static void main(String[] args) {
		ApplicationContext factory = 
			       new ClassPathXmlApplicationContext("sample5/date.xml");
		
		//DateVo ob1=(DateVo)factory.getBean("hong");
		DateVo ob1 = factory.getBean("hong", DateVo.class);
		System.out.println(ob1.toString());
			
		DateVo  ob2=factory.getBean("lee", DateVo.class);
		System.out.println(ob2.toString());
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```

#### DateVo.java

```java
package sample5;
public class DateVo {
	private String name;
	private String birth;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return name + "'s birthday : " + birth;
	}
}
```

#### date.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
****프리픽스 네임선언이 있어야한다!!
xmlns:p="http://www.springframework.org/schema/p"
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<!-- consctructor-arg 가 없으니까 Setter Injection -->
<bean id="hong" class="sample5.DateVo">
	<property name="name" value="Duke"/>
	<property name="birth" value="1990-01-01"/>
</bean>

<bean id="lee" class="sample5.DateVo" 
	p:name="Dooly"  p:birth="2000-12-12"/>	
</beans>
```



---



### Sample6 : 

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

