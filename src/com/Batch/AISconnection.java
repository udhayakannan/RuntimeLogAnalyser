/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Batch;

import com.jcraft.jsch.*;
import com.jdbc.Csvcreation;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ukannan_np
 */
public class AISconnection {

    public void dbservernode1() throws IOException {
        String host = "iecwxuvuodb320";
        String user = "psbabu";
        String password = "Ushika";
        String command = "/home/psbabu/Automation.sh";
        connect(host, user, password);
    }

    public void dbservernode2() throws IOException {
        String host = "iecwxuvuodb323";
        String user = "psbabu";
        String password = "Ushika";
        String command = "/home/psbabu/Automation.sh";
        connect(host, user, password);
    }

    public void appservernode1() throws IOException {
        String host = "iecwxuvuapp323";
        String user = "psbabu";
        String password = "Ushika";
        String command = "/home/psbabu/Automation.sh";
        connect(host, user, password);
    }

    public void appservernode2() throws IOException {
        String host = "iecwxuvuapp328";
        String user = "psbabu";
        String password = "Ushika";
        String command = "/home/psbabu/Automation.sh";
        connect(host, user, password);
    }

    public void connect(String host, String user, String password) throws IOException {
        System.out.println(host + " Connection Establish");
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = f.format(new Date());

        Csvcreation csv = new Csvcreation("Batchjobprecheck" + starttime.substring(0, 12) + ".csv", true);
        csv.Ofile();
        /*   String host = "iecwxuvuapp322";
        String user = "orawls";
        String password = "SX1JBuoatA";*/
        String command = "ps -ef | grep java";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            System.out.println(host + " Connected");
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

            InputStream in = channelExec.getInputStream();

            channelExec.setCommand("/home/psbabu/Automation.sh");
            channelExec.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                System.out.println(++index + " : " + line);
            }
            channelExec.disconnect();
            Channel channel = session.openChannel("sftp");
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.connect();
            sftpChannel.get("/home/psbabu/processfile.txt", host + "processfile.txt");

            sftpChannel.exit();

            session.disconnect();
            csv.Wfile(host + ",Pre check completed");
            System.out.println(host + " precheck Done!");
            csv.Cfile();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        /*   Channel channel = session.openChannel("shell");
            channel.setInputStream(new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8)));
            channel.setOutputStream(System.out);
            InputStream in = channel.getInputStream();
            StringBuilder outBuff = new StringBuilder();
            int exitStatus = -1;
            
            channel.connect();
            System.out.println ("Connected");
            
             
                
              
            while (true) {                
                for (int c; ((c = in.read()) >= 0);) {
                    outBuff.append((char) c);
                    System.out.println(outBuff.toString());
                }
                
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    exitStatus = channel.getExitStatus();
                    break;
                }
            }
            channel.disconnect();
            session.disconnect();
            
        // print the buffer's contents
        System.out.println(outBuff.toString());
        // print exit status
        System.out.println ("Exit status of the execution: " + exitStatus);
        if ( exitStatus == 0 ) {
            System.out.println (" (OK)\n");
        } else {
            System.out.println (" (NOK)\n");
        }
        
        } catch (JSchException ioEx) {
            System.err.println(ioEx.toString());
        }*/
    }
}
