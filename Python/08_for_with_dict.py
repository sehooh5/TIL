classroom = {
    'teacher' : 'kim',
    'student1' : 'dylan',
    'student2' : 'genius'
}

# dict 는 그냥 출력하면 키만 나옴
for member in classroom:
    print(member)   # key 출력


for member in classroom.keys():
    print(member)   # key 출력

# dict 의 values들 찾아오는 방법
for member in classroom:
    print(classroom[member])    # values 출력


for member in classroom.values():
    print(member)   # values 출력


# items : 둘 다 출력
for key, value in classroom.items():
    print(key,":" , value)  # tuple 객체