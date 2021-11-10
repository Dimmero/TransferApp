package core;

import windows.MainWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class ServiceTagParsing {
    public static ArrayList<String> listOfServiceTags = new ArrayList<>();
    public static HashMap<Integer, String> mapOfServiceTags = new HashMap<>();

    public ArrayList<String> trimStringToServiceTags(JTextArea textArea) {
        if (!textArea.getText().isEmpty()) {
            String allAsOne = textArea.getText().replace("\n", "");
            StringBuilder oneTag = new StringBuilder();
            for (int i = 0; i < allAsOne.toCharArray().length; i++) {
                oneTag.append(allAsOne.toCharArray()[i]);
                if ((i + 1) % 7 == 0) {
                    listOfServiceTags.add(oneTag.toString());
                    oneTag.delete(0, oneTag.length());
                }
            }
        }
        return listOfServiceTags;
    }

    public static HashMap<Integer, String> getMapOfServiceTags(ArrayList<String> list) {
        IntStream.range(0, list.size())
                .forEach(index -> {
                    mapOfServiceTags.put(index, list.get(index));
                });
        return mapOfServiceTags;
    }
}
