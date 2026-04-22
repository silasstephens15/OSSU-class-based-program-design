
import tester.*;

    class Person {
        String name;
        IPet pet;
        int age;
     
        Person(String name, IPet pet, int age) {
            this.name = name;
            this.pet = pet;
            this.age = age;
        }

        boolean isOlder(Person other){
            return other.age < this.age;
        }
    }


    interface IPet { }
     

    class Cat implements IPet {
        String name;
        String kind;
        boolean longhaired;
     
        Cat(String name, String kind, boolean longhaired) {
            this.name = name;
            this.kind = kind;
            this.longhaired = longhaired;
        }
    }
     

    class Dog implements IPet {
        String name;
        String kind;
        boolean male;
     
        Dog(String name, String kind, boolean male) {
            this.name = name;
            this.kind = kind;
            this.male = male;
        }
    }

    class ExamplesPets{
        ExamplesPets(){}

        Person john = new Person("John", null, 20) ;
        Person barry = new Person("Barry", null, 18) ;
        Person susie = new Person("Susie", null, 24) ;
        Person larry = new Person("Larry", null, 27) ;

        boolean testIsOlder(Tester t){
            return t.checkExpect(this.john.isOlder(barry), true) &&
                    t.checkExpect(this.barry.isOlder(susie), false);
        }
    }