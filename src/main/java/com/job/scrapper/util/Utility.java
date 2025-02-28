package com.job.scrapper.util;

import com.job.scrapper.model.JobData;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Utility {
    private static final String basefolder="./";

    public static String createData(List<List<String>> data) {
        StringBuilder res=new StringBuilder();
        var alreadySentSet=readHashFromFile();
        if(!data.isEmpty()) {
            boolean isNew=true;
            var counter=0;
            for (var eachLine : data) {
                var appNumber = generateHash(eachLine.get(1));
                if (!alreadySentSet.contains(appNumber)) {
                    if(isNew) {
                        res.append("JOB FROM AROGYASATHI").append("\n\n");
                        isNew=false;
                    }
                    res.append("JOB SEQUENCE: ").append(++counter).append("\n\n").
                            append("Job title: ").append(eachLine.get(1)).append("\n\n")
                            .append("Contact Details: ").append(eachLine.get(2)).append("\n\n")
                            .append("Last Date: ").append(eachLine.get(3)).append("\n\n")
                            .append("Post Name: ").append(eachLine.get(6)).append("\n\n")
                            .append("Level: ").append(eachLine.get(7));
                    res.append("\n\n\n");
                    alreadySentSet.add(appNumber);
                }
            }
        }
        writeHashValuesToFile(alreadySentSet);
        return res.toString();
    }



    public static String createDataForGoogle(List<JobData> googleData) {
        StringBuilder res=new StringBuilder();
        var alreadySentSet=readHashFromFile();

        if(!googleData.isEmpty()) {
            boolean isNew=true;
            var counter=0;
            for (var data : googleData) {
                var appNumber = data.generateHash();
                if (!alreadySentSet.contains(appNumber)) {
                    if(isNew) {
                        res.append("JOB FROM GOOGLE").append("\n\n").append("URL: ").append(googleData.get(0).getGoogleUrl())
                                .append("\n\n");
                        isNew=false;
                    }
                    res.append("JOB SEQUENCE: ").append(++counter).append("\n\n").
                            append("Job title: ").append(data.getJobName()).append("\n\n")
                            .append("Institute: ").append(data.getInstitute()).append("\n\n")
                            .append("City: ").append(data.getCity());
                    res.append("\n\n\n");
                    alreadySentSet.add(appNumber);
                }
            }
        }
        writeHashValuesToFile(alreadySentSet);
        return res.toString();

    }
    public static String generateHash(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // Add leading zero if necessary
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<String> readHashFromFile() {
        String filePath = basefolder+"alreadySenttxt"; // Replace with your file path
        var set=new HashSet<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line);
            }
        } catch (IOException e) {
            log.error("Unable to read the file ",e);
        }
        return set;
    }


    public static void writeHashValuesToFile(Set<String> set) {
        String filePath = basefolder+ "alreadySenttxt"; // Replace with your file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : set) {
                writer.write(line);
                writer.newLine(); // Move to the next line
            }
        } catch (IOException e) {
            log.error("Unable to write to the file ",e);
        }
    }

    public static String joinString(String dataToSend, String dataFromGoogle) {

        return dataToSend + "\n\n\n\n" +dataFromGoogle;
    }
}
