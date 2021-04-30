import user.adminUser;

import java.io.*;


public class Main {
    public static void main(String[] args){
        adminUser user = new adminUser("Testing", 6, true);
        System.out.print(user.userID());
    }
}