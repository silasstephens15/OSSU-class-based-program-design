
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

        void perish(){this.pet = new NoPet();}

        boolean sameNamePet(String name){return this.pet.name() == name;}
    }


    interface IPet { public String name(); }
     

    class Cat implements IPet {
        String name;
        String kind;
        boolean longhaired;
     
        Cat(String name, String kind, boolean longhaired) {
            this.name = name;
            this.kind = kind;
            this.longhaired = longhaired;
        }

        public String name(){return this.name;}
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
        public String name(){return this.name;}
    }

    class NoPet implements IPet{
        NoPet(){}

        public String name(){return "no pet";}
    }

    class ExamplesPets{
        ExamplesPets(){}

        Person john = new Person("John", new Dog("Spot", "Border Collie", false), 20) ;
        Person barry = new Person("Barry", new Cat("Tom", "Cat", false), 18) ;
        Person susie = new Person("Susie", new NoPet(), 24) ;
        Person larry = new Person("Larry", new Dog("Argos", "Hound", true), 27) ;

        boolean testIsOlder(Tester t){
            return t.checkExpect(this.john.isOlder(barry), true) &&
                    t.checkExpect(this.barry.isOlder(susie), false);
        }

        boolean testName(Tester t){
            return t.checkExpect(this.john.sameNamePet("Spot"), true) &&
                    t.checkExpect(this.barry.sameNamePet("Tim"), false);
        }


        boolean testPerish(Tester t){
            john.perish();
            return t.checkExpect(this.john.sameNamePet("no pet"), true);
        }
    }