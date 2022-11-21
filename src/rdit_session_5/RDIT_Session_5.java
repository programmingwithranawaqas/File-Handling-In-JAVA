/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rdit_session_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author waqasali
 */
public class RDIT_Session_5 {

    static void menu()
    {
        System.out.println("1. Insert New Course");
        System.out.println("2. Read All Courses");
        System.out.println("3. Search course by ID");
        System.out.println("4 Exit");
    }
    static void addNewCourse()
    {
        int cId;
        String cName;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the course id : ");
        cId = input.nextInt();
        
        input.nextLine();
        System.out.println("Enter the course name : ");
        cName = input.nextLine();
        
        Course c = new Course(cId, cName);
        
        try{
            FileWriter fw =new FileWriter("courses.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(c.toString());
            bw.newLine();
            
            bw.close();
            fw.close();
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int choice;
        while(true)
        {
            menu();
            choice = input.nextInt();
            if(choice == 1)
            {
                addNewCourse();
            }
            else if(choice == 2)
            {
                displayAllCourses();
            }
            else if(choice==3)
            {
                int id;
                System.out.print("Enter the id of course : ");
                id = input.nextInt();
                
                Course c = searchCourse(id);
                
                if(c==null)
                {
                    System.out.println(id+" not found");
                }
                else
                {
                    System.out.println("Course found with name : "+c.getName());
                }
                
            }
            else if(choice==4)
            {
                break;
            }
            else 
            {
                System.out.println("Invalid input");
            }
        }
    }

    private static void displayAllCourses() {
        
        try{
            FileReader fr = new FileReader("courses.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                String arr[] = line.split(",");
                System.out.println(arr[0] + " " + arr[1]);
            }
            
            br.close();
            fr.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static Course searchCourse(int id) {
        try{
            FileReader fr = new FileReader("courses.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                String arr[] = line.split(",");
                int fileId = Integer.parseInt(arr[0]);
                if(id == fileId)
                {
                    return new Course(fileId, arr[1]);
                }
            }
           
            
            br.close();
            fr.close();
            
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
}
