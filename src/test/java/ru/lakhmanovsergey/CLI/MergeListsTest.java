package ru.lakhmanovsergey.CLI;

import org.codehaus.plexus.util.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by lsp on 28.03.16.
 */
public class MergeListsTest{

    MergeLists mergeLists;
    MergeLists mergeListsOld;

    boolean isEqualsLists(List<String[]> list1,List<String[]> list2){
        if (list1.size()==list2.size()){
            HashSet<String> tempHash=new HashSet<>();
            for (String[] strings : list1) {
                tempHash.add(Arrays.toString(strings));
            }
            for (String[] strings : list2) {
                tempHash.add(Arrays.toString(strings));
            }
            if (tempHash.size()==list1.size()&&tempHash.size()==list2.size()) return true;
        }
        return false;
    }

    @Before
    public void testTestInit() throws Exception {
        mergeLists = new MergeLists(new File("data/users.csv"),new File("data/mailusers.csv"));
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data/mergeLists.serial"));
        mergeListsOld = (MergeLists)ois.readObject();
        ois.close();
    }

    //@Test
    public void setSerialization() throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("data/mergeLists.serial"));
        oos.writeObject(mergeLists);
    }
    @Test
    public void testTestList1() throws Exception {
        assertTrue(isEqualsLists(mergeListsOld.getList1(),mergeLists.getList1()));
        //mergeLists.listPrint(mergeLists.getList2());
    }

    @Test
    public void testTestList2() throws Exception {
        assertTrue(isEqualsLists(mergeListsOld.getList2(),mergeLists.getList2()));
        //mergeLists.listPrint(mergeLists.getList2());
    }

    @Test
    public void testTestListOut() throws Exception {
        assertTrue(isEqualsLists(mergeListsOld.getListOut(),mergeLists.getListOut()));
        //mergeLists.listPrint(mergeLists.getList2());
    }

    @Test
    public void testMergeMailboxes(){mergeLists.listPrint(mergeLists.getListOut());}

    @Test
    public void testListWriteToFile(){mergeLists.listWriteToFile(mergeLists.getListOut(),new File("data/mergeListsOut.csv"));}
}