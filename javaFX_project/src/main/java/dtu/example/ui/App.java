package dtu.example.ui;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.UserInterface;

public class App {

    public static void main(String[] args) throws OperationNotAllowedException {
        new UserInterface().run();
    }

}