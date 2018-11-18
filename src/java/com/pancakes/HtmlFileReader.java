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
        while ((st = br.readLine()) != null) {
            stringBuffer.append(st);
        }


        return stringBuffer.toString();
    }
    String readFile(String fileName,String userCookie) throws IOException {
        File file = new File(fileName);
        StringBuffer stringBuffer= new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            if (st.contains("MARKER-COOKIE")) {
                st = st.replace("MARKER-COOKIE", userCookie);
            }
            stringBuffer.append(st);
        }


        return stringBuffer.toString();
    }

    public String readFile(String fileName, String userCookie, String orderID) throws IOException {
        File file = new File(fileName);
        StringBuffer stringBuffer= new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            if (st.contains("MARKER-COOKIE")) {
                st = st.replace("MARKER-COOKIE", userCookie);
            }
            if (st.contains("MARKER-TRACKING-ID")) {
                st = st.replace("MARKER-TRACKING-ID", orderID);
            }
            stringBuffer.append(st);
        }
        return stringBuffer.toString();
    }
}
