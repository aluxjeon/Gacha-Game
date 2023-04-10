package model;

import java.util.ArrayList;
import java.util.Random;


public class CharacterGacha implements Collectible {
    private final ArrayList<Characters> fourStarCharacterRoster;
    private final ArrayList<Characters> fiveStarCharacterRoster;
    private int characterPity = 0;
    private final ArrayList<Characters> characterList;

    // EFFECTS: Make new CharacterGacha object where two arrayLists are created representing the possible
    // characters you can pull for and the characterList (the list of characters you own) is set by characterList
    public CharacterGacha(ArrayList<Characters> characterList) {
        fourStarCharacterRoster = new ArrayList<>();
        fiveStarCharacterRoster = new ArrayList<>();
        this.characterList = characterList;
    }

    //EFFECTS: Returns the character pity
    public int getPity() {
        return this.characterPity;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: Adds amount to pity
    public void addPity(int amount) {
        this.characterPity += amount;
    }

    //MODIFIES: this
    //EFFECTS: Adds 4* character into roster for pulls
    public void addFourStarCharacterRoster(Characters characters) {
        fourStarCharacterRoster.add(characters);
    }

    //MODIFIES: this
    //EFFECTS: Adds 5* character into roster for pulls
    public void addFiveStarCharacterRoster(Characters characters) {
        fiveStarCharacterRoster.add(characters);
    }

    //EFFECTS: Returns the list of possible 4* characters you can pull for
    public ArrayList<Characters> getFourStarCharacterRoster() {
        return this.fourStarCharacterRoster;
    }

    //EFFECTS: Returns the list of possible 5* characters you can pull for
    public ArrayList<Characters> getFiveStarCharacterRoster() {
        return this.fiveStarCharacterRoster;
    }

    //EFFECTS: Returns the list of characters you own
    public ArrayList<Characters> getCharacterList() {
        return this.characterList;
    }

    //MODIFIES: this
    //EFFECTS: 1. Randomly produces an int between 0 ~ 999, 0 ~ 4, and 0 ~ 4.
    //         2. Adds one to character pity
    //         3. If character pity is equal to 50, then reset pity to 0 and pull a 5* char.
    //            If you don't have this char already, then add to your character inventory/
    //            Else, add one to the copies of the char
    //         4. If character pity isn't equal to 50, then run nonPityGacha with the set 5* & 4* char
    @Override
    public void pull() {
        int luck;
        int fiveStarIndex;
        int fourStarIndex;
        Random rand = new Random();
        Random fiveStarLuck = new Random();
        Random fourStarLuck = new Random();

        luck = rand.nextInt(1000); // Produce an int between [0 ~ 999]
        fiveStarIndex = fiveStarLuck.nextInt(5); // Produce an index between [0 ~ 4]
        fourStarIndex = fourStarLuck.nextInt(5); // Produce an index between [0 ~ 4]
        this.characterPity = characterPity + 1; // Add one to pity

        if (this.characterPity == 50) {
            this.characterPity = 0;
            System.out.println("5 Star Character! " + (fiveStarCharacterRoster.get(fiveStarIndex)).getName());
            log("Pulled: 5 Star Character! " + (fiveStarCharacterRoster.get(fiveStarIndex)).getName());
            if (!(characterList.contains(fiveStarCharacterRoster.get(fiveStarIndex)))) {
                characterList.add(fiveStarCharacterRoster.get(fiveStarIndex));
            } else {
                fiveStarCharacterRoster.get(fiveStarIndex).addCopies();
            }
        } else {
            System.out.println(nonPityGacha(luck, (fourStarCharacterRoster.get(fourStarIndex)),
                    (fiveStarCharacterRoster.get(fiveStarIndex))));
        }
    }

    //MODIFIES: this
    //EFFECTS: if luck is between [100,150], then return 5* char. if luck is between [500, 600], then return
    // 4* char. If you already have the character, then add one to copy, else add char to inventory. Else, return poop.
    // Don't add poop to inventory.
    public String nonPityGacha(int luck, Characters fourStarCharacter,Characters fiveStarCharacter) {
        if ((100 <= luck) && (luck <= 150)) {
            if (!(characterList.contains(fiveStarCharacter))) {
                characterList.add(fiveStarCharacter);
            } else {
                fiveStarCharacter.addCopies();
            }
            log("Pulled: 5 Star Character! " + fiveStarCharacter.getName());
            return ("5 Star Character! " + fiveStarCharacter.getName());
        } else if ((500 <= luck) && (luck <= 600)) {
            if (!(characterList.contains(fourStarCharacter))) {
                characterList.add(fourStarCharacter);
            } else {
                fourStarCharacter.addCopies();
            }
            log("Pulled: 4 Star Character! " + fourStarCharacter.getName());
            return ("4 Star Character! " + fourStarCharacter.getName());
        } else {
            log("Pulled: Poop!");
            return "Poop!";
        }
    }

    //MODIFIES: this
    //EFFECTS: pulls ten times by looping pull() ten times
    @Override
    public void tenPull() {
        for (int i = 0; i < 10; i++) {
            pull();
        }
    }

    //EFFECTS: Returns an arraylist of all character names of the characters you own
    public ArrayList<String> showAllCharacters() {
        ArrayList<String> currentCharacterList = new ArrayList<>();
        int i = 0;
        for (Characters character : characterList) {
            currentCharacterList.add(characterList.get(i).getName());
            i++;
        }
        return currentCharacterList;
    }

    //EFFECTS: Returns the name, rarity, equipment, and copies of character in an arraylist
    public ArrayList<String> showCharacterDetails(String name) {
        int i = 0;
        ArrayList<String> characterDetails = new ArrayList<>();
        for (Characters character : characterList) {
            if (character.getName().equals(name)) {
                characterDetails.add(characterList.get(i).getName());
                String rarity = Integer.toString(characterList.get(i).getRarity());
                characterDetails.add("Rarity: " + rarity);
                characterDetails.add(characterList.get(i).getEquipment());
                String copies = Integer.toString(characterList.get(i).getCopies());
                characterDetails.add("Copies: " + copies);
            } else {
                i++;
            }
        }
        return characterDetails;
    }

    //MODIFIES: EventLog
    //EFFECTS: Makes a new event with the input and adds it to EventLog
    private void log(String event) {
        EventLog.getInstance().logEvent(new Event(event));
    }
}
