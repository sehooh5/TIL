package day14.copy;

class Name {
	String firstName;
	String lastName;

	Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean equals(Object o) {
		boolean result = false ; 
		if(o!=null&&o instanceof Name) {	//o가 null 이 아니고 Name 일때
			Name obj = (Name)o;				//o가 Object 여서 Name으로 형변환
			if(firstName.equals(obj.firstName)	//firstName과 obj. 가 받는 firstName 같으면
					&&lastName.equals(obj.lastName)) {
				result = true;					//true 리턴
			}
		}
		return result;
	}
	public int hashCode() {
		return firstName.hashCode()+lastName.hashCode();
	}

	public String toString() {
		return "[" + firstName + lastName + "]";
	}
}
