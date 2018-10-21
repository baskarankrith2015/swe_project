package com.pancakes;

import java.io.*;

/**
 * Created by krithikabaskaran on 10/20/18.
 */
public class HtmlFileReader {

    String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        StringBuffer stringBuffer= new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            stringBuffer.append(st);

        return stringBuffer.toString();
    }
}
