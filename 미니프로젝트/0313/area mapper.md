# area mapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="resource.BackstreetMapper">

 	<select id="changeConsulting1"  resultType="string">
 		select change_coname from change where area_id= #{area_id} and q_id=2
 	</select>
 	
 	<select id="sales_1Area1"  resultType="_long">
 		select month_sal_money from sales_1 where area_id= #{area_id} and serv_id=#{serv_id} and q_id=2
 	</select>
 	
 	
</mapper>






```

