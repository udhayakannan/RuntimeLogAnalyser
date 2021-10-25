/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Parse;

import com.jdbc.ErrorFilecsv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Fileparse {

    public String Fileparse(String Fileparse, String localfilepath, int y, int nof, int nol) {
        String Search1 = null;

        System.out.println("Fileparse "+Fileparse);
        System.out.println("localfilepath "+localfilepath);

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String data2Write = null;
        try {
            archivo = new File(Fileparse);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int line = 1;
            String fileName = null;

            if (y == 6) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please search the word:");
                Search1 = sc.nextLine();
            }

            switch (y) {

                case 1:
                    fileName = "Error" + localfilepath;
                    break;
                case 2:
                    fileName = "Verbose" + localfilepath;
                    break;
                case 3:
                    fileName = "SQLDEBUG" + localfilepath;
                    break;
                case 4:
                    fileName = "Split" + localfilepath;
                    break;
                case 5:
                    fileName = "STUCK" + localfilepath;
                    break;
                case 6:
                    fileName = Search1 + localfilepath;
                    break;

            }


            ErrorFilecsv Error1 = new ErrorFilecsv(fileName, false);
            Error1.openFile();




            String linea;
            while ((linea = br.readLine()) != null) {

                String buffer = linea.toLowerCase();
                switch (y) {
                    case 1:
                        if (buffer.contains("[error]")) {
                            data2Write =linea;

                            //    System.out.println(data2Write);
                            Error1.writeFile(data2Write);
                            line++;
                        }
                        if (buffer.contains("<error>")) {
                            data2Write = "ErrorCount:" + line + " " + "Error" + linea;
                            //    System.out.println(data2Write);
                            Error1.writeFile(data2Write);


                            line++;
                        }
                        if (buffer.contains("errordescription")) {
                            data2Write = "ErrorCount:" + line + " " + "Error" + linea;
                            //   System.out.println(data2Write);
                            Error1.writeFile(data2Write);
                            line++;
                        }


                        break;
                    case 2:
                        if ((buffer.contains("timer"))
                                && (buffer.contains("end"))) {
                            data2Write = linea;
                            Error1.writeFile(data2Write);
                            System.out.println("VERBOSE:" + linea);
                        }

                        break;
                    case 3:
                        if (buffer.contains("sqldebug")) {
                            String SQLDEBUG1 = linea.split("SQLDEBUG")[0].trim().substring(0, 23);
                            String SQLDEBUG = linea.split("Executing sql")[1];
                            data2Write = SQLDEBUG1 + "-->" + SQLDEBUG;
                            Error1.writeFile(data2Write);
                            System.out.println("SQLDEBUG:" + SQLDEBUG1 + "-->" + SQLDEBUG);
                        }

                        break;
                    case 4:

                        data2Write = linea;
                        Error1.writeFile(data2Write);

                        for (int j = 1; j <= nof; j++) {
                            FileWriter fstream1 = new FileWriter(localfilepath + "Split" + j + ".txt");     // Destination File Location
                            BufferedWriter out = new BufferedWriter(fstream1);

                            for (int i = 1; i <= nol; i++) {
                                linea = br.readLine();
                                if (linea != null) {
                                    out.write(linea);
                                    if (i != nol) {
                                        out.newLine();
                                    }
                                }
                            }
                            out.close();
                        }
                        break;
                    case 5:
                        if (buffer.contains("stuck")) {
                            System.out.println("STUCK:" + linea);
                            data2Write = "STUCK:" + linea;
                            Error1.writeFile(data2Write);
                        }

                        break;
                    case 6:
                        if (buffer.contains(Search1.toLowerCase())) {
                            data2Write = "Occurance:" + line + " " + "Search:" + linea;
                            System.out.println(data2Write);
                            Error1.writeFile(data2Write);
                            line++;
                        }

                        break;
                }
            }
            //  System.out.print(fileName1);
            Error1.closeFile();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return Fileparse;
    }
}
