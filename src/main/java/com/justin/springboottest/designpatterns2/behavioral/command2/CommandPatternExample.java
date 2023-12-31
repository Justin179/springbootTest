package com.justin.springboottest.designpatterns2.behavioral.command2;

import java.util.ArrayList;
import java.util.List;

public class CommandPatternExample {
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();

        TextFile textFile = new TextFile("file1.txt");
        System.out.println(textFileOperationExecutor.executeOperation(new OpenTextFileOperation(textFile)));
        System.out.println(textFileOperationExecutor.executeOperation(new SaveTextFileOperation(textFile)));
    }
}

class TextFileOperationExecutor {
    private final List<TextFileOperation> textFileOperations = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }
}

 interface TextFileOperation {
    String execute();
}

 class OpenTextFileOperation implements TextFileOperation {
    private TextFile textFile;

    public OpenTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.open();
    }
}

 class SaveTextFileOperation implements TextFileOperation {
    private TextFile textFile;

    public SaveTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.save();
    }
}

 class TextFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public String open() {
        return "Opening file " + name;
    }

    public String save() {
        return "Saving file " + name;
    }
}




