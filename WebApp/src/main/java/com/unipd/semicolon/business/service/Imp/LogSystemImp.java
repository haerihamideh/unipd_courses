package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.LocalTimeService;
import com.unipd.semicolon.business.service.LogSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class LogSystemImp implements LogSystem {

    @Autowired
    private LocalTimeService localTimeService;
    private String baseDirectory = "log/serviceLog/"; // Path to log directory.

    @Override
    public void logUtil(String msg) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();

        // finding the class name and package.
        String callingPackage = elements[1].getClassName();
        String className = callingPackage.substring(callingPackage.lastIndexOf('.') + 1);

        // finding the method that we call the logUtil inside it.
        String callingMethodName = elements[1].getMethodName();

        System.out.println("The calling class is: " + className);
        System.out.println("The calling method is: " + callingMethodName);

        String directoryName = baseDirectory + className;
        File directory = new File(directoryName);
        if (!directory.exists()) { // If directory does not exist create new one
            boolean result = directory.mkdirs();
            if (!result) {
                System.out.println("Failed to create directory: " + directoryName);
            }
        }

        String fileName = className + "-" + localTimeService.getLocalDate() + ".txt";

        String outputFilePath = directoryName + File.separator + fileName;
        // File.separator : we will separate  address and the name.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            writer.write("------------------------------------------\n");
            writer.write(String.format("Time: %s\n", localTimeService.getLocalDateTime()));
            writer.write(String.format("Package: %s\n", callingPackage));
            writer.write(String.format("Class name: %s\n", className));
            writer.write(String.format("Method name: %s\n", callingMethodName));
            writer.write(String.format("Message: %s\n", msg));
            writer.write("------------------------------------------\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + outputFilePath, e);
        }

    }
}
