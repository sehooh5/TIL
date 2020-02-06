# exam1 : 인사, LocalDate, 팩토리메소드

#### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="morning" class="exam1.MorningGreetingImpl" />	
<bean id="afternoon" class="exam1.AfternoonGreetingImpl" />	
<bean id="evening" class="exam1.EveningGreetingImpl" />	
<bean id="night" class="exam1.NightGreetingImpl" />	
<bean id="time" class="java.time.LocalDateTime" factory-method="now"/>
<!-- factory-method 없으면 객체생성이 안된다..now라는 객체를 생성하게 해줌 -->
</beans>
```



#### Greeting.java

```java
package exam1;

public interface Greeting {
	   public void greet();
	}
```



#### AfternoonGreetingImpl.java.....

```java
package exam1;

public class AfternoonGreetingImpl implements Greeting{
	public AfternoonGreetingImpl() {
		super();
	}

	@Override
	public void greet() {
		System.out.println("즐거운 오후되세용.");
	}
}
```



#### GreetingTest.java

```java
package exam1;
import java.time.LocalDateTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class GreetingTest {
	public static void main(String[] args) {

		ApplicationContext factory
		    	= new ClassPathXmlApplicationContext("exam1/beans.xml");
		
		LocalDateTime time = (LocalDateTime)factory.getBean("time");
		int hours = time.getHour();
		System.out.println(hours);
		Greeting greet;
		if(hours>=6&&hours<12) {
			greet = (Greeting)factory.getBean("morning");		
		}else if(hours>=12&&hours<17) {
			greet = (Greeting)factory.getBean("afternoon");
		}else if(hours>=17&&hours<22) {
			greet = (Greeting)factory.getBean("evening");
		}else {
			greet = (Greeting)factory.getBean("night");
		}
		greet.greet();
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```

