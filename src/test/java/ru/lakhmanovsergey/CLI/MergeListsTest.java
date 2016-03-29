package ru.lakhmanovsergey.CLI;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by lsp on 28.03.16.
 */
public class MergeListsTest {

    @Test
    public void testTestOut() throws Exception {
        MergeLists mergeLists=new MergeLists(new File("data/users.csv"),new File("data/mailusers.csv"));
        //mergeLists.listPrint(mergeLists.getList1());
        mergeLists.listPrint(mergeLists.getList2());
    }
}