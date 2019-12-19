package day11.case3;

public class TVFactory {
	public static TV getTV(String beanName){	//�޼��� ���� non static ���⿡ static ��� ����
		TV obj = null;							//static ������� ��Ű���� ���� Ŭ������.�޼���� ��밡��
		if(beanName.equals("samsung")){			//*******���� static ������ �ٽ� �� ����
			obj = new SamsungTV();
		} else if(beanName.equals("lg")){
			obj = new LgTV();
		}
		return obj;
	}

}
