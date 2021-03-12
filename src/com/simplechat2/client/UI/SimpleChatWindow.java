package com.simplechat2.client.UI;

import com.simplechat2.common.Message;
import com.simplechat2.common.TUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimpleChatWindow extends JFrame{
    private JTextField txtMsg;
    private JButton btnEnviar;
    private JTextArea txtPChat;
    private JTextArea txtPUsrs;
    private JLabel lblUsrs;
    private JLabel lblvacio;
    private JPanel panel;
    private JScrollPane panelMensajes;
    private JScrollPane panelUsuarios;
    private SocketHandler sh;
    private TUser c;
    private UIListener ls;

    public SimpleChatWindow(){
        super("Simple Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,300);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridc = new GridBagConstraints();
        txtPChat = new JTextArea(10,20);
        txtPUsrs = new JTextArea(10,8);
        lblUsrs = new JLabel("Usuarios");
        lblUsrs.setHorizontalTextPosition(JLabel.CENTER);
        lblvacio = new JLabel();
        txtPUsrs.setEditable(false);
        txtPChat.setEditable(false);
        txtPChat.setAutoscrolls(true);
        //txtPChat.setColumns(20);
        //txtPChat.setRows(20);
        panelMensajes = new JScrollPane(txtPChat);
        panelUsuarios = new JScrollPane(txtPUsrs);
        //panelUsuarios.set
        panelMensajes.setAutoscrolls(true);

        gridc.gridx = 0;
        gridc.gridy = 0;
        gridc.insets = new Insets(5,5,5,5);
        panel.add(lblvacio,gridc);
        panel.add(panelMensajes,gridc);

        gridc.gridx = 1;
        gridc.gridy = 0;
        panel.add(lblUsrs,gridc);
        //panel.add(panelUsuarios,gridc);

        txtMsg = new JTextField(20);
        btnEnviar = new JButton("Enviar");
        btnEnviar.setPreferredSize(new Dimension(90,20));

        gridc.gridx = 0;
        gridc.gridy = 1;
        panel.add(panelMensajes,gridc);
        panel.add(txtMsg,gridc);

        gridc.gridx = 1;
        gridc.gridy = 1;
        panel.add(panelUsuarios,gridc);

        gridc.gridx = 0;
        gridc.gridy = 2;
        panel.add(txtMsg,gridc);
        gridc.gridx = 1;
        gridc.gridy = 2;
        panel.add(btnEnviar,gridc);

        add(panel);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = txtMsg.getText();
                if(str.startsWith("/")){
                    str=str.replace('/',' ');
                    str=str.trim();
                    if(str.equals("quit")){
                        try{
                            ls.join();
                        }catch(InterruptedException ex){
                            JOptionPane.showMessageDialog(null,"Hubo un error al cerrar el Listener");
                        }
                        System.exit(0);
                    }
                }
                Message m = new Message(c.getUsername(),str);
                System.out.println("Enviando: " + m);
                sh.sendObject(m);
                System.out.println("Enviado");
                //txtPChat.append(str + "\n");
                txtMsg.setText("");
                m=null;
            }
        });
        txtMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = txtMsg.getText();
                if(str.startsWith("/")){
                    str=str.replace('/',' ');
                    str=str.trim();
                    if(str.equals("quit")){
                        System.exit(0);
                    }
                }
                Message m = new Message(c.getUsername(),str);
                System.out.println("Enviando: " + m);
                sh.sendObject(m);
                System.out.println("Enviado");
                //txtPChat.append(str + "\n");
                txtMsg.setText("");
                m=null;
            }
        });

    }

    public void setSh(SocketHandler sh){
        this.sh=sh;
    }

    public void setC(TUser c){
        this.c=c;
    }

    public void launchListener(){
        ls = new UIListener(sh,txtPChat);
        ls.start();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleChatWindow w = new SimpleChatWindow();
                UIListener ls = new UIListener(w.sh, w.txtPChat);
                System.out.println("Lanzando hilo de escucha");
                ls.start();
                w.setVisible(true);
            }
        });

    }
}
