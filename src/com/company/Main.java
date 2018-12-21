package com.company;

import java.io.*;
import java.util.*;


public class Main {

    private static List<Character> letter = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    public static void main(String[] args) throws Exception
    {
        ArrayList<Character> book = read("D:\\Desktop\\voina_i_mir.txt");
        ArrayList<Character> glava = read("D:\\Desktop\\glava.txt");
        ArrayList<Character> codeglavi = coder(glava);
        Map<Integer, Character> book_frq = FreqTable.frequency(book);
        Map<Integer, Character> glava_frq = FreqTable.frequency(codeglavi);
        for(int i=0;i<codeglavi.size();i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (codeglavi.get(i)==book_frq.get(j))
                {
                    codeglavi.set(i, glava_frq.get(j));
                    break;
                }
            }
        }

        File f2 = new File("D:\\Desktop\\DecodeGlavi.txt");
        BufferedWriter wrbuf = new BufferedWriter(new FileWriter(f2));

        for(int i = 0; i< codeglavi.size();i++)
        {
            wrbuf.write(codeglavi.get(i));
        }
        wrbuf.close();

        ArrayList<Character> decod1 = read("D:\\Desktop\\DecodeGlavi.txt");
        Map<Integer, String> book_big = Bigramm.frequency_big(book);
        Map<Integer, String> glava_big = Bigramm.frequency_big(decod1);
        for(int i = 0; i<decod1.size() - 1; i++)
        {
            String s = String.valueOf(decod1.get(i).toString() + decod1.get(i+1).toString());
            for(int j = 0; j < 10; j++)
            {
                if(s.equals(book_big.get(j)))
                {
                    char[] mas = glava_big.get(j).toCharArray();
                    decod1.set(i, mas[0]);
                    decod1.set(i+1, mas[1]);
                    break;
                }
            }
        }

        File f3 = new File("D:\\Desktop\\DecodeGlavi1.txt");
        BufferedWriter wrbuf1 = new BufferedWriter(new FileWriter(f3));

        for(int i = 0; i< decod1.size();i++)
        {
            wrbuf1.write(decod1.get(i));
        }
        wrbuf1.close();
    }

    public static ArrayList<Character> read(String filename) throws FileNotFoundException, IOException
    {
        ArrayList<Character> file = new ArrayList<>();
        File f = new File(filename);
        BufferedReader scanner = new BufferedReader(new FileReader(f));
        int c;
        while((c = scanner.read()) != -1)
        {
            file.add(Character.toLowerCase((char) c));
        }
        scanner.close();
        return file;
    }


    public static ArrayList<Character> coder(ArrayList<Character> file) throws Exception
    {
        ArrayList<Character> newFile = (ArrayList<Character>) file.clone();
        for (int i = 0; i < file.size(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (file.get(i) == letter.get(j))
                {
                    newFile.set(i, letter.get(25 - j));
                }
            }
        }

        File f1 = new File("D:\\Desktop\\ShifrGlavi.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f1));

        for(int i = 0; i< newFile.size();i++)
        {
            file.set(i, newFile.get(i));
            writer.write(newFile.get(i));
        }
        writer.close();
        return file;
    }






}
