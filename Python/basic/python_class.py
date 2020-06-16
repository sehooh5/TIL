class Dog:
    # MyDogList (pascal case, upper camel case)
    # myDogList (camel case)
    kind = 'canine'  # 클래스 변수 : 인스턴스가 갖고있지 않음

    def __init__(self, name):
        self.name = name    # 인스턴스 변수 : 인스턴스가 만들어질때 사용됨


my_dog = Dog('lulu')
your_dog = Dog('sena')

print(my_dog.kind)  # 인스턴스 안에 kind가 없기 때문에
print(your_dog.kind)  # 인스턴스를 만든 class를 참고하여 찾음,,그래도 없으면 파이썬 표준 라이브러리까지 감(built in)
print(my_dog.name)
print(your_dog.name)


class Dog:

    tricks = []             # mistaken use of a class variable

    def __init__(self, name):
        self.name = name

    def add_trick(self, trick):
        self.tricks.append(trick)


my_dog = Dog('lulu')
your_dog = Dog('sena')
my_dog.add_trick('wal wal')
your_dog.add_trick('faw faw')

print(my_dog.tricks)
print(your_dog.tricks)


class Dog:

    dog_count = 1

    # tricks = []  # 이렇게 사용하면 올바른 사용법이 아니다

    def __init__(self, name):
        self.name = name
        self.tricks = []  # 각자 인스턴스에 리스트를 갖고싶으면 여기에 들어가야함

    def add_trick(self, trick):
        self.tricks.append(trick)

    @classmethod
    def my_count(cls):
        cls.dog_count += 1


my_dog = Dog('lulu')
your_dog = Dog('sena')
my_dog.add_trick('wal wal')
your_dog.add_trick('faw faw')

print(my_dog.tricks)
print(your_dog.tricks)


# print(help(str.capitalize))
'apple'.capitalize()  # = str.capitalize('apple')

variable = 'apple'

# 단축형
print(variable.capitalize())  # 객체지향
# self가 작성되는 이유
print(str.capitalize(variable))  # 절차지향

# 절차지향 vs 객체 지향
# 데이터가 흘러다니는 것으로 보는 시각 -> 데이터가 중심이 되는 시각으로 변했다

# 절차지향 (함수형)
# greeting(데이터)


def greeting(name):
    return f'hello, {name}'


print(greeting('Dylan'))


# 객체지향
# 데이터.greeting()
class Person:

    def __init__(self, name):
        self.name = name

    def greeting(self):
        return f'hello, {self.name}'


my_var = Person('Dylan')
print(my_var.greeting())
