package christmas;

import christmas.controller.EventPlanner;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.init();
        eventPlanner.run();
    }
}
