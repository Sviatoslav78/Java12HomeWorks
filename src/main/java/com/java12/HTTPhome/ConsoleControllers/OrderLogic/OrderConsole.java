package com.java12.HTTPhome.ConsoleControllers.OrderLogic;

import com.java12.HTTPhome.ConsoleControllers.PetLogic.PetConsole;
import com.java12.HTTPhome.ConsoleInterface;
import com.java12.HTTPhome.ConstUrlsInterface;
import com.java12.HTTPhome.Entity.Order.Order;
import com.java12.HTTPhome.Entity.Order.OrderStatus;
import com.java12.HTTPhome.Service.OrderService;

import java.io.IOException;

public class OrderConsole implements ConsoleInterface {
    private OrderService orderService = new OrderService();
    private CommandsOrderUtils utils = new CommandsOrderUtils();

    public void execute() {
        System.out.println("Hello your working with order console. \nEnter 'help' to get more information!");
        boolean isAlive = true;
        OrderCommands typeCommand;
        while (isAlive) {
            System.out.println("######");
            System.out.println("Enter command: ");
            typeCommand = utils.getCommand(scanner.nextLine());
            switch (typeCommand) {
                case HELP:
                    helpCommand();
                    break;
                case UNKNOWN:
                    System.out.println("Unknown command, try again, please.");
                    break;
                case EXIT:
                    System.out.println("Thanks for attention!");
                    isAlive = false;
                    break;
                case ADD:
                    createNewOrderCommand();
                    break;
                case GET_INVANTORY:
                    getInventoryCommand();
                    break;
                case DELETE:
                    deleteOrderCommand();
                    break;
                case GET_BY_ID:
                    getByIdCommand();
                    break;
                case PET_CONSOLE:
                    new PetConsole().execute();
                    break;

            }
        }
    }

    private void getByIdCommand() {
        System.out.println("Enter id: ");
        try {
            String answer = orderService.getOrderById(Integer.parseInt(scanner.nextLine()));
            if (answer.startsWith(ConstUrlsInterface.errorType)) {
                System.out.println(answer);
            } else {
                Order order = jsonParser.fromJson(answer, Order.class);
                System.out.println(order.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteOrderCommand() {
        System.out.println("Enter id: ");
        try {
            String answer = orderService.deleteById(Integer.parseInt(scanner.nextLine()));
            if (answer.startsWith(ConstUrlsInterface.errorType)) {
                System.out.println(answer);
            } else {
                Order order = jsonParser.fromJson(answer, Order.class);
                System.out.println(order.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getInventoryCommand() {
        try {

            String answer = orderService.getPetInventory();
            if (answer.startsWith(ConstUrlsInterface.errorType)) {
                System.out.println(answer);
            } else {
                System.out.println("Your inventory: ");
                answer = answer.substring(1, answer.length() - 1);
                String[] answerArr = answer.split(",");
                for (int i = 0; i < answerArr.length; i++) {
                    System.out.println(answerArr[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewOrderCommand() {
        Order order = Order.builder().build();
        System.out.println("Enter info for new order: ");
        System.out.println("Enter id: ");
        order.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter quality: ");
        order.setQuantity(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter petId");
        order.setPetId(Long.parseLong(scanner.nextLine()));
        System.out.println("Enter shipDate: ");
        order.setShipDate(scanner.nextLine());
        order.setComplete(false);
        OrderStatus status = null;
        while (status == null) {
            System.out.println("Enter status: ");
            status = getStatus(scanner.nextLine());
            if (status == null) {
                System.out.println("Sorry, unknown status");
            } else {
                order.setStatus(status);
            }
        }

        try {
            System.out.println(orderService.addOrderForPet(order));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private OrderStatus getStatus(String status) {
        if (status.equalsIgnoreCase("placed")) {
            return OrderStatus.placed;
        }
        if (status.equalsIgnoreCase("approved")) {
            return OrderStatus.approved;
        }
        if (status.equalsIgnoreCase("delivered")) {
            return OrderStatus.delivered;
        }
        return null;

    }

    private void helpCommand() {
        for (OrderCommands commands : OrderCommands.values()) {
            if (!commands.equals(OrderCommands.UNKNOWN)) {
                System.out.println(commands.getDescription());
            }
        }


    }
}
