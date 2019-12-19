package day11.case3;

public class TVFactory {
	public static TV getTV(String beanName){	//메서드 내에 non static 없기에 static 사용 가능
		TV obj = null;							//static 사용으로 패키지명 없이 클래스명.메서드명 사용가능
		if(beanName.equals("samsung")){			//*******언제 static 쓰는지 다시 잘 정리
			obj = new SamsungTV();
		} else if(beanName.equals("lg")){
			obj = new LgTV();
		}
		return obj;
	}

}
