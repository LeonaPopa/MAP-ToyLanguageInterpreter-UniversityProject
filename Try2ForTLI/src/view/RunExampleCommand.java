package view;

import model.exceptions.MyException;

public class RunExampleCommand extends Command{
    private controller.Controller controller;
    public RunExampleCommand(String key, String description, controller.Controller controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() {
        try {
            controller.executeAllSteps();
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
