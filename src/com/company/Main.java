package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    // Так делать нельзя!
        IComand comand = new MouseClick();
        comand.execute();

        System.out.println("--------------------------------------------");

        // для этого придумали паттерн(команды)
        // здесь мы вызываем обработчик команд, ався реализация их скрыта от наших глаз
        Receiver receiver = new Receiver();
        receiver.add(new MouseClick());
        receiver.add(new MousePress());
        receiver.add(new MouseClick());
        receiver.run();
    }
}

// обычный интерфес и класс(действия)
interface IComand{
    void execute();
    void error();
}

class MouseClick implements IComand{
    @Override
    public void execute() {
        System.out.println("Click");
    }

    @Override
    public void error() {
        System.out.println("revert");
    }
}

class MousePress implements IComand{
    @Override
    public void execute() {
        System.out.println("Press");
    }

    @Override
    public void error() {
        System.out.println("revert");
    }
}

// но мы делаем прослойку которая будет в себе содержать команды
class Receiver{
    List<IComand> comands = new ArrayList<>();

    void add(IComand comand){
        comands.add(comand);
    }

    void run(){
        try {
            for (IComand c: comands){
                c.execute();
            }
        }catch (Exception e){
            for (IComand c: comands){
                c.error();
            }
        }

    }
}