package ru.lakhmanovsergey.CLI;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by lsp on 25.03.16.
 */
public class AddMailsToUsersTest {

    @Test
    public void testTestOut() throws Exception {
        AddMailsToUsers addMailsToUsers=new AddMailsToUsers(new File("data/users.csv"),new File("data/mailusers.csv"));
        addMailsToUsers.testOut();
    }
}