package persistence;

import model.WorkRoom;
import org.json.JSONObject;

import java.io.*;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/

//TODO
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //TODO
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //TODO
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //TODO
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(WorkRoom wr) {
        JSONObject json = wr.toJson();
        saveToFile(json.toString(TAB));
    }

    //TODO
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //TODO
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

// Based on JsonSerializationDemo.