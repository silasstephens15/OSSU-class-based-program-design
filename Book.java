import tester.*;

class Book {
    String name;
    String author;
    int year;
    double price;

    Book(String name, String author, int year, double price){
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    boolean titleBefore(Book other){
        return this.name.compareTo(other.name) < 0;
    }
}

interface ILoBook{
    ILoBook sortByTitle();
    ILoBook insert(Book other);
}

class MtLoBook implements ILoBook{
    MtLoBook(){}

    public ILoBook sortByTitle(){
        return this;
    }

    public ILoBook insert(Book other){
        return new ConsLoBook(other, this);
    }
}

class ConsLoBook implements ILoBook{
    Book first;
    ILoBook rest;

    ConsLoBook(Book first, ILoBook rest){
        this.first = first;
        this.rest = rest;
    }

    public ILoBook sortByTitle(){
        return this.rest.sortByTitle().insert(this.first);
    }

    public ILoBook insert(Book other){
        if (this.first.titleBefore(other)){
            return new ConsLoBook(this.first, this.rest.insert(other));
        }
        else{
            return new ConsLoBook(other, this);
        }
    }
}

class ExamplesBooks{
    ExamplesBooks(){}

    Book hp1 = new Book("hp1", "JKR", 2000, 10);
    Book hp2 = new Book("hp2", "JKR", 2000, 15);
    Book lotr = new Book("lotr", "JRRT", 1980, 20);

    ILoBook list1 = new ConsLoBook(lotr, new ConsLoBook(hp1, new MtLoBook()));
    ILoBook list2 = new ConsLoBook(hp2, list1);

    ILoBook sortedList1 = new ConsLoBook(hp1, new ConsLoBook(lotr, new MtLoBook()));
    ILoBook sortedList2 = new ConsLoBook(hp1, new ConsLoBook(hp2, new ConsLoBook(lotr, new MtLoBook())));

    boolean testBooks(Tester t){
        return t.checkExpect(list1.sortByTitle(), sortedList1) &&
                t.checkExpect(list2.sortByTitle(), sortedList2);
    }
}
