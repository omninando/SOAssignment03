package com.company;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.util.Random;/** * the CounterWithThread class has the program to run a * multi threaded slot machine Interface created using Java * Language. This application was made as an Assignment for * CSCI 215 Operating Systems at Clark University. * * Edited by Fernando de Almeida Coelho on 10/02/14. */public class CounterWithThread extends Frame{    private TextField textWindow, textWindow2, textWindow3, messageWindow;    private int count = 0, count2 = 0, count3 = 0, result1, result2, result3;    private Button toggleButton;    public static final int HEIGHT = 150;    public static final int WIDTH = 200;    CounterRunner1 counter1;    CounterRunner2 counter2;    CounterRunner3 counter3;    public static void main(String[] args)    {        CounterWithThread counterWindow = new CounterWithThread();        counterWindow.setVisible(true);    }//main    public CounterWithThread()    {        configureGUI();        addToWindow();    }//CounterWithThread    private void configureGUI()    {        setSize(WIDTH, HEIGHT);        setTitle("Multi Threaded version");        setBackground(Color.white);        setLayout(new FlowLayout());        textWindow = new TextField(3);        textWindow2 = new TextField(3);        textWindow3 = new TextField(3);        toggleButton = new Button("Start");    }//configureGUI    private void addToWindow()    {        addWindowListener(new WindowDestroyer());        add(textWindow);        textWindow.setText(" * ");        add(textWindow2);        textWindow2.setText(" * ");        add(textWindow3);        textWindow3.setText(" * ");        toggleButton.addActionListener(new ToggleButtonListener());        add(toggleButton);        messageWindow = new TextField(15);        add(messageWindow);        messageWindow.setText("Starting....");    }//addToWindow    /*    These inner classes does the counting. When an object in    this class is started (using the Thread method start()),    the run() method is automatically invoked. This goes    into an infinite loop that is concurrent with the    listener object for the button. In this way, actionPerformed()    can be invoked "at the same time" that run() is executing.     */    private class CounterRunner1 extends Thread    {        Random random1 = new Random();        public void run()        {            while (true) try            {                Thread.sleep(100);                result1 = ((count++) % 10);                textWindow.setText(Integer.toString(result1));            }            catch (InterruptedException e)            {                int randomInt1 = random1.nextInt(100);                int i = 0;                while (i < randomInt1)                {                    try { Thread.sleep(100); }                    catch (InterruptedException e1) { System.err.println("Interrupted."); }                    result1 = (count++) % 10;                    textWindow.setText("  " + Integer.toString(result1));                    i++;                }                break;            }        }    }//CounterRunner1    private class CounterRunner2 extends Thread    {        Random random2 = new Random();        public void run()        {            while (true) try            {                Thread.sleep(100);                result2 = ((count2++) % 10);                textWindow2.setText(Integer.toString(result2));            }            catch (InterruptedException e)            {                int randomInt2 = random2.nextInt(100);                int i = 0;                while (i < randomInt2)                {                    try { Thread.sleep(100); }                    catch (InterruptedException e1) { System.err.println("Interrupted."); }                    result2 = (count2++) % 10;                    textWindow2.setText("  " + Integer.toString(result2));                    i++;                }                break;            }        }    }//CounterRunner2    private class CounterRunner3 extends Thread    {        Random random3 = new Random();        public void run()        {            while (true) try            {                Thread.sleep(150);                result3 = ((count3++) % 10);                textWindow3.setText(Integer.toString(result3));            }            catch (InterruptedException e)            {                int randomInt3 = random3.nextInt(50);                int i = 0;                while (i < randomInt3)                {                    try { Thread.sleep(150); }                    catch (InterruptedException e1) { System.err.println("Interrupted."); }                    result3 = (count3++) % 10;                    textWindow3.setText("  " + Integer.toString(result3));                    i++;                }                break;            }        }    }//CounterRunner3    /*     * This inner class is the private listener class for the     * toggleButton.     */    private class ToggleButtonListener implements ActionListener    {        public void actionPerformed(ActionEvent e)         {            if (e.getActionCommand().equals("Stop"))            {                messageWindow.setText("Wait a bit....");                toggleButton.setLabel("Wait");                counter1.interrupt();                counter2.interrupt();                counter3.interrupt();                try                {                    counter1.join();                    counter2.join();                    counter3.join();                }                catch (InterruptedException e1) { System.err.println("Interrupted."); }                if ((result1 == result2) && (result2 == result3)) {  messageWindow.setText("You Win!"); }                else { messageWindow.setText("Sorry, You Lose!"); }                toggleButton.setLabel("Start");            }            else if (e.getActionCommand().equals("Start"))            {                counter1 = new CounterRunner1();                counter2 = new CounterRunner2();                counter3 = new CounterRunner3();                counter1.start();                counter2.start();                counter3.start();                toggleButton.setLabel("Stop");                messageWindow.setText("Playing.....");            }        }//actionPerformed    }}//CounterWithThread