# Alex's Project

## Description of Project:
The application will be a gacha game where the user can pulls for characters and use them to fight enemies. 
This project is of interest to me because I love gacha games—the one I've played the longest thus far is Genshin
Impact. I also think this would be a really cool way for me to integrate art into code since I draw in my free time! 
Moreover, I think a gacha game sufficiently covers all the criteria of this term project since gacha games are about 
collecting characters and items, all of which is data that needs to be saved.



***

**Ways User Interacts With Game:**
- User can pull for characters with *limited game currency*
- User can enter an interface to see what *characters & items* the user has
- During the fights, the user can interact with the selected characters to make them use an ability

***

**Interfaces:**
- Main Page: Has buttons to  go into other interfaces
- Gacha Page: Where user can pull characters or items
- Character Page: Where user can see Characters & Items

***

**User Stories:**
- As a user, I want to be able to pull for characters & items
- As a user, I want to be able to see all of the characters & items I have
- As a user, I want to be able to equip items onto characters
- As a user, I want to be able to unequip items from characters
- As a user, I want to be able to see the specific details of items and characters
- As a user, I want to be able to see my pity count
- As a user, I want to be able to add currency and check my current currency
- As a user, I want to be able to save my character and item inventory (if I want to)
- As a user, I want to be able to save my current amount of currency and pity (if I want to)
- As a user, I want to be able to have the option to load my saved inventories, currency, and pity


***

# Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking on the "Hire" button & going to the 
GachaMenuGUI. When there, by pressing the "1 Hire"/"10 Hire" button, eventually either a Character or Item (depends on 
which banner you are on//Can swap between the two by pressing the two buttons on the very right) will be added to your 
corresponding inventory.
- You can generate the second required action related to adding Xs to a Y by clicking on the "EMPLOYEES" button & going 
to the InventoryMenuGUI. When there, if you have characters and items in your inventory, then you can click the weapon
button on the ID card and you will see many items (depending on how many you have) and you will see that there is an
"Equip" button. If you press that "Equip" button, then the item will be added to the character. (You can press the 
weaponButton again to get out of the weapon menu GUI)
- You can locate my visual component by just simply running the GUI. There will be an image of a building on
the MainMenuGUI. By clicking on "Hire" on the MainMenuGUI, you will go to the GachaMenuGUI and see some images of the 
character/item banners. If you have some characters or items, you can go to the InventoryMenuGUI by clicking "EMPLOYEES" 
on MainMenuGUI and you will see at least 3-4 images of characters and/or items.
- You can save the state of my application by going to the MainMenuGUI and clicking
the "Save" button
- You can reload the state of my application by going to the MainMenuGUI and clicking the
"Load" button

***

# Phase 4: Task 2
If an item or character is pulled, then the event log should look like:
=========Event Logs=========

...

Mon Apr 10 11:25:02 PDT 2023
Pulled: Poop!

Mon Apr 10 11:25:04 PDT 2023
Pulled: Poop!

Mon Apr 10 11:25:06 PDT 2023
Pulled: TestName1!

Mon Apr 10 11:25:08 PDT 2023
Pulled: TestItem2!

...

=========Event Logs=========

If an item is equipped onto a character, then the event log should look like:
=========Event Logs=========

...

Mon Apr 10 11:26:20 PDT 2023
TestItem1 is now equipped on TestName4

...

=========Event Logs=========

Note: The "..." signify that the sample only is a portion of the EventLog

***

# Phase 4: Task 3

I think there is a lot of things I could refactor to make my project
more efficient and manageable. 

First, I think one thing I could do is add an abstract class
for my Item and Characters class and/or my CharacterGacha and ItemGacha class to extend. This is because there
are a lot of overlap in methods and a lot of duplicate code—the gacha classes especially have a lot of copied code 
since it does the same thing albeit with different objects. Not only would this reduce the amount of work to implement each class, but it would improve coupling by needing to 
change code in only one place instead of having to go to both classes.

Another change I would make to the design is maybe make a Pity class that counts the pity and a character/item
inventory class since I feel like the CharacterGacha and ItemGacha class do too many things in a single class. I'd make 
it so that CharacterGacha and ItemGacha can focus on pulling a character/item, while CharacterInventory and ItemInventory 
can handle adding characters and items to the inventory and displaying character/item information; the Pity class could
keep track of the character and item pity. By separating out these different functions, it makes the code more 
readable and improves overall cohesion.