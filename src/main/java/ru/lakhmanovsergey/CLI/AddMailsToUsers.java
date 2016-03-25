package ru.lakhmanovsergey.CLI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddMailsToUsers {
    private List<String[]> users=new ArrayList<String[]>();
    private List<String[]> mails=new ArrayList<String[]>();

    public AddMailsToUsers(File usrsFile,File mailsFile) throws IOException {
        users=fileReader(usrsFile);
        mails=fileReader(mailsFile);
    }

    private List<String[]> fileReader(File file) throws IOException {
        List<String[]> result = new ArrayList<String[]>();
        String line;
        BufferedReader reader=new BufferedReader(new FileReader(file));
        while ((line=reader.readLine())!=null){
            String[] record;
            record=line.split(";");
            result.add(record);
        }
        return result;
    }

    public void testOut(){
        for (String[] user : users) {
            for (String s : user) {
                System.out.print(s+" : ");
            }
            System.out.println();
        }
    }
}

