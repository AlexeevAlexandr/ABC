package сом.example.application.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ListUsers {
    private ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> listUsers(){
        try(FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/users.csv"));
            Scanner sc = new Scanner(fileInputStream))
        {
            while (sc.hasNext()){
                list.add(sc.nextLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new ListUsers().listUsers().get(2));
        System.out.println(new ListUsers().listUsers().size());
    }
}
