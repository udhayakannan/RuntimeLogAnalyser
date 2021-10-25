/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logdetails;

import com.Parse.Fileparse;
import com.bean.Filecreation;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.parameters.Bean;
import java.util.List;

/**
 *
 * @author tkmagq7
 */
public class Environment implements Bean{

    String fileParse = null, localFilePath = null;

    public String Environmentdetails(String hostname, String hostFilePath, String hostFilename, String username, String password, int nooffiles, int nooflines) throws SftpException, JSchException {

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = null;
        String[] Host = hostname.split(":");
        String[] Instance = hostFilePath.split(":");
        String[] InstanceFile = hostFilename.split(":");
        for (int i = 0; i < Host.length; i++) {

            for (int j = 0; j < Instance.length; j++) {

                if (nooflines == 9999) {
                    localFilePath = InstanceFile[j];
                } else {
                    localFilePath = Host[i] + InstanceFile[j];
                }
                session = jsch.getSession(username, Host[i], 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword(password);
                session.connect();
                Channel channel = session.openChannel("sftp");
                sftpChannel = (ChannelSftp) channel;
                sftpChannel.connect();
                System.out.println(Host[i] + "Server Connected");
                Filecreation f=new Filecreation();
f.makedirectory("\\nmon1");
                System.out.println("File path Accessed : " + Instance[j] + InstanceFile[j]);
                sftpChannel.get(Instance[j] + InstanceFile[j],path+"\\nmon1\\"+localFilePath);
                System.out.println("File Downloaded : " + localFilePath);
                sftpChannel.exit();
                session.disconnect();

                fileParse = localFilePath;
                setFileParse(fileParse);

                System.out.println(fileParse);

            }
            System.out.println("Completed");
        }
        return fileParse;

    }

    public String getFileParse() {
        return fileParse;
    }

    public void setFileParse(String fileParse) {
        this.fileParse = fileParse;
    }

}
