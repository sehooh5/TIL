# Sampleanno

[TOC]

---

### Sampleanno01



#### User.java

```java
package sampleanno01;

public class User {
	private String name;
	private int age;	
	private String hobby;

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getHobby() {
		return hobby;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", hobby=" + hobby + "]";
	}
}
```

#### UserMain.java

```java
package sampleanno01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {

	public static void main(String[] args) {
		ApplicationContext factory 
		    = new ClassPathXmlApplicationContext("sampleanno01/bean.xml");
		System.out.println("**** Container Initialization End ****");		
		UserShow ob=factory.getBean("myProcess0", UserShow.class);
		System.out.println(ob.toString());	
		System.out.println("-----------------");		
		UserShow ob1=factory.getBean("myProcess1", UserShow.class);
		System.out.println(ob1.toString());	
		System.out.println("-----------------");		
		UserShow ob2=factory.getBean("myProcess2", UserShow.class);
		System.out.println(ob2.toString());	
		System.out.println("-----------------");		
		UserShow ob3=factory.getBean("myProcess3", UserShow.class);
		System.out.println(ob3.toString());	
		
		((ClassPathXmlApplicationContext)factory).close();
	}
}
```

#### UserShow.java

```java
package sampleanno01;

public class UserShow {
	private User user;

	public UserShow() {		
		super();
		System.out.println("Constructor Call(no-args)");
	}
	public UserShow(User user) {
		super();
		this.user = user;
		System.out.println("Constructor Call(User-args)");
	}
	
	public void setUser(User user1) {
		System.out.println("Setter Call by Autowire");
		this.user = user1;
	}

	@Override
	public String toString() {
		return "UserShow [user=" + user + "]";
	}

}

```

#### bean.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="user"  class="sampleanno01.User">
	<property name="name" value="Dooly"/>
	<property name="age"  value="10"/>
	<property name="hobby" value="swimming"/>
</bean>

<bean id="myProcess0"  class="sampleanno01.UserShow" />

<!-- autowire 객체와 자동으로 결합해주는  = setter 메스드를 세팅해주는 의미-->

<!-- 알아서 setter 를 통해서 객체를 등록해준다
userShow 의 setter 메서드는 setUser 즉 user 라는 이름으로 만들어진 객체를 세팅해달라는 의미 -->
<bean id="myProcess1"  class="sampleanno01.UserShow"  
										autowire="byName"/>
<!-- 어떤 이름이든 상관 없다 타입으로 세팅 -->										
<bean id="myProcess2"  class="sampleanno01.UserShow"  
										autowire="byType"/>
<!-- 생성자를 호출해서 넣어줘 -->
<bean id="myProcess3"  class="sampleanno01.UserShow"  
										autowire="constructor"/>

</beans>
```

#### 

---



### Sampleanno02 : autowire



#### MyFoodMgr.java

```java
package sampleanno02;

//setter, toString()추가
public class MyFoodMgr{
	private Food favoriteFood;
	private Food unFavoriteFood;	
	
	public MyFoodMgr() {}
    //타입이 두개니까 이름에 따라서 전달되게 된다
	public MyFoodMgr(Food favoriteFood, Food unFavoriteFood) {
		super();
		this.favoriteFood = favoriteFood;
		this.unFavoriteFood = unFavoriteFood;
		System.out.println(favoriteFood);
		System.out.println(unFavoriteFood);
	}
	public void setFavoriteFood(Food favoriteFood) {
		this.favoriteFood = favoriteFood;
	}
	public void setUnFavoriteFood(Food unFavoriteFood) {
		this.unFavoriteFood = unFavoriteFood;
	}
	@Override
	public String toString() {
		return "[Food1=" + favoriteFood + ", Food2=" + unFavoriteFood + "]";
	}
}
```

#### Food.java

```java
package sampleanno02;

public class Food {
	private String foodName;
	private int foodPrice;
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	@Override
	public String toString() {
		return "Food [foodName=" + foodName + ", foodPrice=" + foodPrice + "]";
	}
}
```

#### FoodTest.java

```java
package sampleanno02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FoodTest {
	public static void main(String[] args) {
		ApplicationContext factory = 
				new ClassPathXmlApplicationContext("sampleanno02/bean1.xml");

		MyFoodMgr ob=factory.getBean("myFood", MyFoodMgr.class);
		System.out.println(ob.toString());

		((ClassPathXmlApplicationContext) factory).close();
	}
}
```

#### Bean1.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!-- 테스트1 -->
<!-- 
<bean id="myFood" class="sampleanno02.MyFoodMgr">
	<property name="favoriteFood" ref="favoriteFood"/>
	<property name="unFavoriteFood" ref="unFavoriteFood"/>
</bean>

<bean id="favoriteFood" class="sampleanno02.Food" >
	<property name="foodName" value="Bread"/>
	<property name="foodPrice" value="1500"/>
</bean>
<bean id="unFavoriteFood" class="sampleanno02.Food" >
	<property name="foodName" value="Noodle"/>
	<property name="foodPrice" value="2500"/>
</bean> -->

<!-- 테스트2 -->
<bean id="myFood" class="sampleanno02.MyFoodMgr" autowire="constructor"/>

<bean id="favoriteFood" class="sampleanno02.Food" >
	<property name="foodName" value="Noodle"/>
	<property name="foodPrice" value="2500"/>
</bean>
<bean id="unFavoriteFood" class="sampleanno02.Food" >	
	<property name="foodName" value="Bread"/>
	<property name="foodPrice" value="1500"/>
</bean>
</beans>
```

#### Bean2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<bean id="myFood" class="sampleanno02.MyFoodMgr" autowire="byName"/>
<bean id="favoriteFood" class="sampleanno02.Food" p:foodName="Bread" p:foodPrice="1500"/>
<bean id="unFavoriteFood" class="sampleanno02.Food" p:foodName="Noodle" p:foodPrice="2500"/>
</beans>
```



---



### Sampleanno01



#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### Messageean.java

```java

```

#### MessageBean.xml

```xml

```

#### MessageBean.xml

```xml

```





### Sampleanno01



#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### Messageean.java

```java

```

#### MessageBean.xml

```xml

```

#### MessageBean.xml

```xml

```





### Sampleanno01



#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### Messageean.java

```java

```

#### MessageBean.xml

```xml

```

#### MessageBean.xml

```xml

```





### Sampleanno01



#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### Messageean.java

```java

```

#### MessageBean.xml

```xml

```

#### MessageBean.xml

```xml

```





### Sampleanno01



#### MessageBean.java

```java

```

#### MessageBean.java

```java

```

#### Messageean.java

```java

```

#### MessageBean.xml

```xml

```

#### MessageBean.xml

```xml

```

