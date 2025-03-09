Britney Glory Chen/0806022410020
IDE: IntelliJ IDEA Community Edition 2024.2.3
openjdk version "18.0.2" 2022-07-19
OpenJDK Runtime Environment Corretto-18.0.2.9.1 (build 18.0.2+9-FR)
OpenJDK 64-Bit Server VM Corretto-18.0.2.9.1 (build 18.0.2+9-FR, mixed mode, sharing)

approach:
so i decided to make the linkedlist and array in one code by making a everyday task (array) and addedTask (linkedlist), this way i only need to make 1 program for both the linkedlist and array. I also decided to make some methods but only for repeating segments like the displaytasks method.

challenges:
i had a lot of challenges for this project and they are figuring out how to clear a console, how to use stack, how to use linked list, how to check if the user's input is not a string, how to fix my code because the variable have been initialized before, how there was a problem with displaying only one of the unfinished task list, and how to make the different responses on case 3
and why i want to input a new task in the option 2 part
for how to use stack and linked list i use the website geeksforgeeks The other problems i asked chatgpt how to do it, turns out you can;t clear console in intellij, chatgpt gave the way to check if the user's input is not a string,
chatgpt also fixed my code by changing the variable into a new variable, chatgpt told me that i must add an extra scanner.nextline(0 part after the scanner.int so that it can work, and chatgpt showed me how to make the if condition so that it can finally display the right thing, chatgpt made the math condition for me

the program:
when you run the program, you will be presented with a couple of options. Then you will have to input a number based on the option you want to do. Based on the option that you chose, you will get different outcomes. If you chose option 1 then the program will display all the tasks that you have (unfinished and completed). If you chose option 2 you can change one of the unfinished task into a new task that you want. If you chose option 3, then the program will show you how many task you have completed, based on the amount of completed tasks you have, you will get a different outcome. If you chose option 4, then u can update the status of the unfinished tasks or the completed tasks, for the unfinished tasks you can only update the status to done where the unfinished task will be moved to the completed tasks lists, for the completed tasks you can only update the status of the last completed task where the last completed task will be moved to the unfinished tasks list that you want. If you chose option 5, you can add a new task that you want to the added tasks list which also counts as a unfinished tasks list. If you chose option 6, you can remove whichever task you have added in the added tasks list. if you chose option 7, then the program will end
