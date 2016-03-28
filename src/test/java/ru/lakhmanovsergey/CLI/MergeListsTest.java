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
        MergeLists mergeLists=new MergeLists(new File("data/fping.out"),new File("data/arp.out"));
        mergeLists.listOut(mergeLists.getListOut());
        //mergeLists.listOut(mergeLists.getList2());
    }
}