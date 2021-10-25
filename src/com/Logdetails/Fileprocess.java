/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Logdetails;
import com.Parse.Fileparse;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 *
 * @author tkmagq7
 */
public class Fileprocess {


     String fileParse=null,localFilePath=null;


 public void Download(String Host,String User,String Password,String file,String Serverpath) throws SftpException, JSchException
 {
    JSch jsch = new JSch();
    Session session = null;
    ChannelSftp sftpChannel = null;

System.out.println("Server Connection Details"+User+"Host"+Host+"Password"+Password);


    session = jsch.getSession(User,Host, 22);

    session.setConfig("StrictHostKeyChecking", "no");
    session.setPassword(Password);
    session.connect();
    Channel channel = session.openChannel("sftp");
    sftpChannel = (ChannelSftp)channel;
    sftpChannel.connect();
    System.out.println("Server Connected");

    System.out.println("File path Accessed : "+Serverpath+file);
    sftpChannel.put(file,Serverpath+file);
    System.out.println("File Uploaded : ");
    sftpChannel.exit();
    session.disconnect();

file = null;






}


}
