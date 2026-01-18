package fop_group_assignment;

import java.util.*;  //import the library

public class RecurrenceService { // defines a class

    private static final String FILE = "recurrent.csv";  //constant variable for file
    private static final String HEADER = "eventId,interval,times,endDate"; // constant variable for file header

    public static void save(RecurrentEvent r) {  //method that saves recurrent 
        List<String> lines = FileService.readAll(FILE);//read all the line in file and stores in llist called lines

        // If file does not exist or is empty, write header first
        if (lines == null || lines.isEmpty()) {
            List<String> init = new ArrayList<>();
            init.add(HEADER);
            FileService.writeAll(FILE, init);
        }

        FileService.appendLine(FILE, r.toString());//converts r to string then add new li ne at end 
    }

    public static void update(RecurrentEvent r) {  // New method to update recurrence
        List<String> lines = FileService.readAll(FILE);
        
        // If file does not exist or is empty, just save it
        if (lines == null || lines.isEmpty()) {
            save(r);
            return;
        }

        List<String> out = new ArrayList<>();
        out.add(HEADER);
        boolean found = false;
        
        // Start from 1 to skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;
            
            String[] s = line.split(",", -1);
            
            try {
                int id = Integer.parseInt(s[0]);
                if (id == r.getEventId()) {
                    // Replace with updated recurrence
                    out.add(r.toString());
                    found = true;
                } else {
                    out.add(line);
                }
            } catch (NumberFormatException e) {
                // skip malformed lines 
                out.add(line);
            }
        }
        
        // If not found, add new entry
        if (!found) {
            out.add(r.toString());
        }
        
        FileService.writeAll(FILE, out);
    }

    public static void delete(int eventId) {//method to delete with id
        List<String> lines = FileService.readAll(FILE); //redas all line and store it in line

        // Nothing to delete
        if (lines == null || lines.isEmpty()) {
            return;
        }

        List<String> out = new ArrayList<>(); //new list called out
        out.add(HEADER); // always keep header

        // Start from 1 to skip header
        for (int i = 1; i < lines.size(); i++) { //for loop
            String line = lines.get(i);//gets current line
            if (line.trim().isEmpty()) continue; //if empty skip and go next line

            String[] s = line.split(",", -1);// split line by this

            try {
                int id = Integer.parseInt(s[0]); //converts id to number
                if (id != eventId) {           // check  the row we want to delete
                    out.add(line);             //keeps it
                }
            } catch (NumberFormatException e) {
                // skip malformed lines 
                out.add(line);
            }
        }

        FileService.writeAll(FILE, out);  //writes the cl,eaned list back
    }

    public static RecurrentEvent get(int eventId) {  // New method to get recurrence by event ID
        List<String> lines = FileService.readAll(FILE);
        
        if (lines == null || lines.isEmpty()) {
            return null;
        }
        
        // Start from 1 to skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;
            
            String[] s = line.split(",", -1);
            
            try {
                int id = Integer.parseInt(s[0]);
                if (id == eventId) {
                    return new RecurrentEvent(
                        id,
                        s[1],
                        Integer.parseInt(s[2]),
                        s[3]
                    );
                }
            } catch (NumberFormatException e) {
                // skip malformed lines 
            }
        }
        
        return null;
    }

    public static List<RecurrentEvent> getAll() {  // Optional: get all recurrences
        List<String> lines = FileService.readAll(FILE);
        List<RecurrentEvent> result = new ArrayList<>();
        
        if (lines == null || lines.isEmpty()) {
            return result;
        }
        
        // Start from 1 to skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;
            
            String[] s = line.split(",", -1);
            
            try {
                result.add(new RecurrentEvent(
                    Integer.parseInt(s[0]),
                    s[1],
                    Integer.parseInt(s[2]),
                    s[3]
                ));
            } catch (NumberFormatException e) {
                // skip malformed lines 
            }
        }
        
        return result;
    }
}

        