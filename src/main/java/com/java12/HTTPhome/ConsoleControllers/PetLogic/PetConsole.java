package com.java12.HTTPhome.ConsoleControllers.PetLogic;

import com.java12.HTTPhome.ConsoleControllers.OrderLogic.OrderConsole;
import com.java12.HTTPhome.ConsoleInterface;
import com.java12.HTTPhome.ConstUrlsInterface;
import com.java12.HTTPhome.Entity.Pet.Category;
import com.java12.HTTPhome.Entity.Pet.Pet;
import com.java12.HTTPhome.Entity.Pet.PetStatus;
import com.java12.HTTPhome.Entity.Pet.Tag;
import com.java12.HTTPhome.Service.PetService;

import java.io.File;
import java.io.IOException;

public class PetConsole implements ConsoleInterface {
    private CommandPetUtils utils = new CommandPetUtils();
    private PetService petService = new PetService();

    public void execute() {
        System.out.println("Hello your working with pet console. \nEnter 'help' to get more information!");
        boolean isAlive = true;
        PetCommands typeCommand;
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
                case POST:
                    createNewPetCommand();
                    break;
                case UPDATE:
                    updateExistPetCommand();
                    break;
                case DELETE:
                    deletePetCommand();
                    break;
                case GET_BY_ID:
                    getByIdCommand();
                    break;
                case GET_BY_STATUS:
                    getByStatusCommand();
                    break;
                case PUT_UPDATE:
                    putUpdateCommand();
                    break;
                case UPLOAD_FILE:
                    uploadFileCommand();
                    break;
                case ORDER_CONSOLE:
                    new OrderConsole().execute();
                    break;
            }

        }


    }

    private void uploadFileCommand() {
        System.out.println("Enter id of your pet: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter path of your file: ");
        File file = new File(scanner.nextLine());
        System.out.println("Enter format of your file: ");
        String format = scanner.nextLine();
        try {
            System.out.println(petService.uploadFileForPetById(id, file, format));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postPut(boolean mark) {
        Pet pet = Pet.builder().build();
        System.out.println("Enter information about new pet: ");
        System.out.println("####");
        System.out.println("Enter name: ");
        pet.setName(scanner.nextLine());
        System.out.println("Enter id: ");
        pet.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter status: ");
        pet.setStatus(getStatus(scanner.nextLine()));

        Category category = Category.builder().build();
        System.out.println("Enter info about category of your pet: ");
        System.out.println("####");
        System.out.println("Enter id of category: ");
        category.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter name of category: ");
        category.setName(scanner.nextLine());
        System.out.println("####");
        pet.setCategory(category);

        System.out.println("Let`s start with tags");
        System.out.println("####");
        System.out.println("Enter number of your tags: ");
        int tagNum = Integer.parseInt(scanner.nextLine());
        Tag[] tags = new Tag[tagNum];
        Tag current;
        for (int i = 0; i < tagNum; i++) {
            System.out.println("Enter data of your tag: ");
            current = Tag.builder().build();
            System.out.println("Enter name: ");
            current.setName(scanner.nextLine());
            System.out.println("Enter id: ");
            current.setId(Integer.parseInt(scanner.nextLine()));
            tags[i] = current;
        }

        pet.setTags(tags);
        System.out.println("####");

        System.out.println("Let`s start with photoUrls");
        System.out.println("####");
        System.out.println("Enter your photoUrls in line (example:\n firstUrl secondUrl thirdUrl): ");
        String[] urls = scanner.nextLine().split(" ");
        pet.setPhotoUrls(urls);
        System.out.println("####");

        try {
            if (mark) {
                System.out.println(petService.postPetToStore(pet));
            } else {
                System.out.println(petService.putPetToStore(pet));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void putUpdateCommand() {
        postPut(false);

    }

    private void getByStatusCommand() {

        try {
            System.out.println("Enter status: ");
            String jArr = petService.getPetsByStatus(getStatus(scanner.nextLine()));
            if (jArr.startsWith("[")) {

                Pet[] pets = jsonParser.fromJson(jArr, Pet[].class);
                for (Pet pet : pets) {
                    System.out.println(pet.toString() + "\n");
                }
            } else {
                System.out.println(jArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getByIdCommand() {
        System.out.println("Enter your id: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            String answer = petService.getPetById(id);
            if (answer.startsWith(ConstUrlsInterface.errorType)) {
                System.out.println(answer);
            } else {
                Pet pet = jsonParser.fromJson(answer, Pet.class);
                System.out.println(pet.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deletePetCommand() {
        System.out.println("Enter your pet id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Are you sure?\n Enter (+) - to confirm: ");
        if (scanner.nextLine().contains("+")) {
            try {
                System.out.println(petService.deleteById(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Operation canceled");
        }
    }

    private void updateExistPetCommand() {
        System.out.println("Enter data of your pet: ");
        Pet pet = Pet.builder().build();
        System.out.println("Enter name of your pet: ");
        pet.setName(scanner.nextLine());
        System.out.println("Enter id of your pet: ");
        pet.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter status of your pet: ");
        String line = scanner.nextLine();
        pet.setStatus(getStatus(line));
        try {
            System.out.println(petService.updatePetByIdStatusName(pet));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createNewPetCommand() {
        postPut(true);

    }

    private void helpCommand() {
        for (PetCommands commands : PetCommands.values()) {
            if (!commands.equals(PetCommands.UNKNOWN)) {
                System.out.println(commands.getDescription());
            }
        }
    }

    private PetStatus getStatus(String name) {
        if (name.equalsIgnoreCase("available")) {
            return PetStatus.available;
        } else {
            if (name.equalsIgnoreCase("pending")) {
                return PetStatus.pending;
            } else {
                return PetStatus.sold;
            }
        }
    }


}
