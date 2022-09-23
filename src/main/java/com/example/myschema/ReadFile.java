package com.example.myschema;

import javax.xml.bind.JAXBElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile{

    public static void main(String[] args) {
        ArrayList<TextInput> media = new ArrayList<TextInput>();
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("nzhrsscid_000000005.xml"));
            String line;
            boolean isItem = false;

            while ((line = inputStream.readLine()) != null){
                //System.out.println(line);

                if(line.endsWith("<item>")){
                    isItem = true;
                    TextInput temp = new TextInput();
                    media.add(temp);
                }else if(line.endsWith("</item>")){
                    isItem = false;
                }

                if(isItem){
                    if((line.endsWith("</title>"))){
                        media.get(media.size()-1).setTitle(line.substring(7,line.length()-8));
                    }else if((line.endsWith("</link>"))){
                        media.get(media.size()-1).setLink(line.substring(6,line.length()-7));
                    }else if((line.endsWith("</description>"))){
                        media.get(media.size()-1).setDescription(line.substring(13,line.length()-14));
                    }
                }
            }

            for(int i=0; i<media.size(); i++){
                System.out.println("Title: "+media.get(i).getTitle());
                System.out.println("Link: "+media.get(i).getLink());
                System.out.println("Description: "+media.get(i).getDescription());
                System.out.println();
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}