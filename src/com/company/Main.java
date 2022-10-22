package com.company;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.lang.String;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //read the file from text
        Scanner read = new Scanner(new File("C:\\Users\\maian\\Stack_Queue\\src\\text3.txt"));
        StackQueue addTag = new StackQueue();
        //integer for counting the line
        int countLine = 0;

        //create the character will be removed in the String

        String specialChar_end = "/";
        String char_a = "a";
        String char_excla = "!";

        while (read.hasNext()) {
            String data = read.nextLine();

            //******using Matcher and pattern to extract the string contain < > in the  HTML file.
            Pattern pattern = Pattern.compile("<[^>]*>");
            Matcher matcher = pattern.matcher(data);
            //print out the special chars
            //*******loop through the line in file text
            while (matcher.find()) {
                // System.out.println(matcher.group());
                addTag.push(matcher.group());
                addTag.top();
                System.out.println(addTag.top());
                System.out.println(addTag.top().getClass().getSimpleName());

                //***** check and pass the first base if have in HTML, it will pass without checking the end tag.
                if(char_a.equals(Character.toString(addTag.top().charAt(1)))){
                    String subStringa = addTag.top().substring(2, addTag.top().length()-1);
                    String rest_String = addTag.top().replace(subStringa,"");
                    System.out.println(addTag.top().charAt(2));
                    System.out.println(rest_String);
                    addTag.push(rest_String);
                    System.out.println(countLine);
                    continue;
                }
                //if(char_excla.indexOf(addTag.top().charAt(1))<0){
                if(char_excla.equals(Character.toString(addTag.top().charAt(1)))){
                    String subString_excla = addTag.top().substring(4, addTag.top().length()-3);
                    String rest_String_excla = addTag.top().replace(subString_excla,"");

                    System.out.println(rest_String_excla);
                    addTag.pop(rest_String_excla);
                    System.out.println(countLine);
                    continue;
                }

                if (addTag.top().equals("<!DOCTYPE html>")) {
                    System.out.println("yes");
                    System.out.println(countLine);
                     continue;

                 //**** check if the next tag contain the character "/" or not.
                    // if not it will continue to loop
                }
                if (specialChar_end.indexOf(addTag.top().charAt(1)) < 0) {
                    System.out.println(countLine);
                    continue;
                }



                // *** check if the next tag contain the character "/" or not. if yes
                // *** it will check the condition
                //*** remove the char "/" to check if two strings are the same, then we will pop out
               //if (specialChar_end.indexOf(addTag.top().charAt(1)) >= 0) {
                if(specialChar_end.equals(Character.toString(addTag.top().charAt(1)))){
                   String a = addTag.top().replace("/", "");
                   //System.out.println("get end " + a);
                   //the bottom tag is the previous tag which will compare with the next tag
                   String bottomTag = addTag.getm();
                    //System.out.println("Bottom" + bottomTag);
                    if (a.equals(bottomTag)) {
                        System.out.println("get the match" + a + bottomTag);
                        addTag.pop(a);
                        addTag.pop(bottomTag);
                        System.out.println(countLine);
                        continue;
                    }
                    else{
                        System.out.println("Oops ... There is a problem . ." +
                                "The " + addTag.top() + " tag at line " + (countLine + 1) + " does not meet the tag rules . . ");
                        break;
                    }
                }

                //check if the HTML file contains the
               if(addTag.top().equals("<br>") || addTag.top().equals("<hr>")) {
                   addTag.pop(addTag.top());
                   System.out.println(countLine);
                   continue;

               }

        }
            countLine++;



        }



        System.out.println(countLine);
        System.out.println("Congratulations. . . " +
                "The given HTML file meets all the tag rules. . ");



    }
}
 


