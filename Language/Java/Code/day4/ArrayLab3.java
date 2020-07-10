package day4;

public class ArrayLab3 {

	public static void main(String[] args) {
		char java[] = {'J','a','v','a'};

		for(int su=0;su<java.length;su++)
		{if((int)(java[su])>91) {              // if(java[i] >='a' && java[i] <='z')
			java[su]=(char)(java[su]-32);		//java[i] -=32;
		}else java[su]=(char)(java[su]+32);		//else if(java[i] >='A' && java[i] <='Z')
		}										//java[i] +=32;
		
		System.out.println("변환된 배열 : "+java[0]+","+java[1]+","+java[2]+","+java[3]);
		//for(int i = 0; i<java.length;i++){
		//if(i != java.length-1) System.out.print(java[i]+",");
		//else System.out.print(java[i]);
	}

}
