package сом.example.application.model;

import сом.example.application.config.WebSecurityConfig;

import java.io.*;
import java.util.ArrayList;
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

    private String getPasswordByName(String userName){
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/users.csv"))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] cols = line.split(",");
                if (cols[1].equals(userName)){
                    result = cols[8];
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private boolean isMatchesPassword(String passwordInput, String passwordChecker){
        return new WebSecurityConfig().isMatchesPassword(passwordInput,passwordChecker);
    }

    public boolean checkPassword(String name, String inputPassword){
       ListUsers listUsers = new ListUsers();
       String passwordChecker = listUsers.getPasswordByName(name);
       return listUsers.isMatchesPassword(inputPassword, passwordChecker);
    }

    public static void main(String[] args) {
        System.out.println((new ListUsers().checkPassword("admin","password")));
    }

}
