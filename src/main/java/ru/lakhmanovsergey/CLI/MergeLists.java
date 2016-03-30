package ru.lakhmanovsergey.CLI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MergeLists implements Serializable{
    private List<String[]> list1;
    private List<String[]> list2;
    private List<String[]> listOut;

    public MergeLists(File list1File,File list2File) throws IOException {
        list1=fileReader(list1File);
        list2=fileReader(list2File);
        //listOut=merging(list1,list2);
    }

    private List<String[]> fileReader(File file) throws IOException {
        List<String[]> result = new ArrayList<String[]>();
        String line;
        BufferedReader reader=new BufferedReader(new FileReader(file));
        while ((line=reader.readLine())!=null){
            String s=line.replaceAll("^\\s*","");
            line=s.replaceAll("\\s*$","");
            String[] record=line.split("\\s*;\\s*");
            result.add(record);
        }
        return result;
    }

    public List<String[]> getList1() {
        return list1;
    }

    public List<String[]> getList2() {
        return list2;
    }

    public List<String[]> getListOut() { return listOut; }

    public void listPrint(List<String[]> list){
        for (String[] record : list) {
            for (String s : record) {
                System.out.print(s.isEmpty()?"NULL":s+":");
                //System.out.print(s);
            }
            System.out.println();
        }
    }
    private List<String[]> merging(List<String[]> list1,List<String[]> list2){
        List<String[]> result=new ArrayList<String[]>();
        for (String[] record1 : list1) {
            for (String[] record2 : list2) {
                if (record2.length>1&record1[0].equals(record2[0])) {
                    String[] recOut = new String[]{record1[0], record2[1]};
                    result.add(recOut);
                }
            }
        }
        return result;
    }
}

