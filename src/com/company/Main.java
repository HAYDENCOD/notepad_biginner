package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    JFrame frame;
    JMenuBar menuBar;
    JMenu file;
    JMenu edit;
    JMenuItem open, newFile, save,print, exit;
    JMenuItem undo,paste,selectAll,cut,copy,newPrompt,newPrompt1,newPrompt2 ;
    JMenu view;

    JMenu format;
    JMenu help;
    JFileChooser fileChooser;
    JTextArea textArea;
    Clipboard clip;

    Main(){
        frame = new JFrame("NOTEPAD Application");
        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");
        format = new JMenu("Format");
        help = new JMenu("Help");

        newFile = new JMenuItem("New           Ctrl +  N");
        open = new JMenuItem("Open            Ctrl +  O");
        save = new JMenuItem("Save            Ctrl + S");
        print = new JMenuItem("Print          Ctrl +  P");
        exit = new JMenuItem("Exit");
        undo = new JMenuItem("Undo                Ctrl+Z");
        cut = new JMenuItem("Cut           Ctrl+x");
        copy = new JMenuItem("Copy          Ctrl+C");
        paste = new JMenuItem("Paste              Ctrl+V");
        selectAll = new JMenuItem("Select All     Ctrl+A ");
        newPrompt = new JMenuItem("new features coming soon.....");
        newPrompt1 = new JMenuItem("new features coming soon.....");
        newPrompt2 = new JMenuItem("new features coming soon.....");
        textArea = new JTextArea();
        fileChooser = new JFileChooser();
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);


        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Lucida Console",Font.PLAIN,18);
        textArea.setFont(font);

        frame.add(textArea);

        file.add(open);
        file.add(newFile);
        file.add(save);
        file.add(view);
        file.add(exit);


        edit.add(undo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        format.add(newPrompt);
        view.add(newPrompt1);
        help.add(newPrompt2);


        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);
        menuBar.add(help);

        frame.setJMenuBar(menuBar);

        OpenListener openL = new OpenListener();
        NewListener NewL = new NewListener();
        SaveListener saveL = new SaveListener();
        ExitListener exitL = new ExitListener();
        open.addActionListener(openL);
        newFile.addActionListener(NewL);
        save.addActionListener(saveL);
        exit.addActionListener(exitL);

        //UndoListener UndoL = new UndoListener();
        PasteListener pasteL = new PasteListener();
        //EditListener EditL = new EditListener();
        //SelectListener SelectL = new SelectListener();
        //undo.addActionListener(UndoL);
        //paste.addActionListener(EditL);
        //selectAll.addActionListener(SelectL);

        frame.setSize(800,600);
        frame.setVisible(true);


    }
         class OpenListener implements ActionListener {
             @Override
             public void actionPerformed(ActionEvent e) {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
                File file = fileChooser.getSelectedFile();
                textArea.setText("");
                Scanner in = null;
                try{
                    in = new Scanner(file);
                    while (in.hasNext()) {
                        String line = in.nextLine();
                        textArea.append(line+"\n");
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    in.close();
                }
            }
        }




         }
         class SaveListener implements ActionListener {

             @Override
             public void actionPerformed(ActionEvent e) {
                 if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
                     File file = fileChooser.getSelectedFile();
                     PrintWriter out = null;
                     try {
                         out = new PrintWriter(file);
                         String output = textArea.getText();
                         System.out.println(output);
                         out.println(output);
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     } finally {
                         try {
                             out.flush();
                         } catch(Exception ex1){

                         }
                         try {
                             out.close();
                         } catch (Exception ex1){

                         }
                     }
                 }
             }
         }
         class NewListener implements ActionListener {

             @Override
             public void actionPerformed(ActionEvent e) {
                 textArea.setText("");
//                 frame.add(newFile);
//                 textArea.add(newFile+ "\n");
             }
         }
         class ExitListener implements ActionListener {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         }
         class PasteListener implements ActionListener {

             @Override
             public void actionPerformed(ActionEvent e) {
                 Transferable cliptran = clip.getContents(Main.this);
                 try{
                     String se1 = (String) cliptran.getTransferData(DataFlavor.stringFlavor);
                     textArea.replaceRange(se1,textArea.getSelectionStart(), textArea.getSelectionEnd());

                 }
                 catch (Exception exc){
                     System.out.println("not string flavour");
                 }
             }
         }

    public static void main(String[] args) {
        Main n = new Main();
    }
}