package model;

import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.Random;

public class CharacterGacha implements Collectible {
    private final ArrayList<Characters> fourStarCharacterRoster;
    private final ArrayList<Characters> fiveStarCharacterRoster;
    private int characterPity = 0;
    private final ArrayList<Characters> characterList;

    public CharacterGacha(ArrayList<Characters> characterList) {
        fourStarCharacterRoster = new ArrayList<>();
        fiveStarCharacterRoster = new ArrayList<>();
        this.characterList = characterList;
    }

    public int getPity() {
        return this.characterPity;
    }

    // EFFECTS: Add 4* character into roster for pulls
    public void addFourStarCharacterRoster(Characters characters) {
        fourStarCharacterRoster.add(characters);
    }

    // EFFECTS: Add 5* character into roster for pulls
    public void addFiveStarCharacterRoster(Characters characters) {
        fiveStarCharacterRoster.add(characters);
    }

    public ArrayList<Characters> getFourStarCharacterRoster() {
        return this.fourStarCharacterRoster;
    }

    public ArrayList<Characters> getFiveStarCharacterRoster() {
        return this.fiveStarCharacterRoster;
    }

    public ArrayList<Characters> getCharacterList() {
        return this.characterList;
    }

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

    public String nonPityGacha(int luck, Characters fourStarCharacter,Characters fiveStarCharacter) {
        if ((100 <= luck) && (luck <= 150)) {
            if (!(characterList.contains(fiveStarCharacter))) {
                characterList.add(fiveStarCharacter);
            } else {
                fiveStarCharacter.addCopies();
            }
            return ("5 Star Character! " + fiveStarCharacter.getName());
        } else if ((500 <= luck) && (luck <= 600)) {
            if (!(characterList.contains(fourStarCharacter))) {
                characterList.add(fourStarCharacter);
            } else {
                fourStarCharacter.addCopies();
            }
            return ("4 Star Character! " + fourStarCharacter.getName());
        } else {
            return "Poop!";
        }
    }

    @Override
    public void tenPull() {
        for (int i = 0; i < 10; i++) {
            pull();
        }
    }

    //EFFECTS: Shows an arraylist of all character names of the characters you own
    public ArrayList<String> showAllCharacters() {
        ArrayList<String> currentCharacterList = new ArrayList<>();
        int i = 0;
        for (Characters character : characterList) {
            currentCharacterList.add(characterList.get(i).getName());
            i++;
        }
        return currentCharacterList;
    }

    //EFFECTS: Shows the name, rarity, and equipment of character in an arraylist
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

}
