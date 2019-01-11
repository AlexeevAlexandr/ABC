package сом.example.application.model;

import com.opencsv.bean.CsvToBeanBuilder;
import сом.example.application.dao.RegisteredVisitorsDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListUsers {

    private ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> getUsersFromFile(){
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

    public void setUsersToFile(AppUser user){
        try(PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/resources/users.csv",true))){
            printWriter.write(getDataFromUser(user));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getDataFromUser(AppUser user){
        return user.getUserId().toString() + "," +
                user.getUserName() + "," +
                user.getFirstName() + "," +
                user.getLastName() + "," +
                user.isEnabled() + "," +
                user.getEmail() + "," +
                user.getCountryCode() + "," +
                user.getGender() + "," +
                user.getEncryptedPassword() + "\n";
    }

    private List<RegisteredVisitorsDAO> readFromCSV(){
        List<RegisteredVisitorsDAO> beans = null;
        try {
        beans = new CsvToBeanBuilder(new FileReader("src/main/resources/users.csv"))
                .withType(RegisteredVisitorsDAO.class).build().parse();
        }catch (Exception e){e.printStackTrace();}
        return beans;
    }

    private void getPasswordByName(int index){
        ListUsers listUsers = new ListUsers();
//        System.out.println(listUsers.readFromCSV().get(index).getPassword());
        while (listUsers.readFromCSV().listIterator().hasNext()){
            System.out.println(listUsers.readFromCSV().listIterator().nextIndex());
        }

            System.out.println(list.get(1));
    }

    public static void main(String[] args) {
//        System.out.println(new WebSecurityConfig().isMatchesPassword("abc","abc"));
        new ListUsers().getPasswordByName(1);
    }

}
