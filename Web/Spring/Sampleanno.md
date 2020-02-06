# Sampleanno

[TOC]

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

