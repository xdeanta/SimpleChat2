package com.simplechat2.client.UI;

import com.simplechat2.client.SocketHandler;
import com.simplechat2.common.TUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField txtUsr;
    private JPasswordField pfPasswd;
    private JButton btnLogin;
    private JButton btnCerrar;
    private JLabel lblUsuario;
    private JLabel lblPasswd;
    private JPanel panel;
    private SocketHandler sh;

    public Login(){
        super("Simple Chat Login");
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        lblPasswd = new JLabel("Contraseña:");
        lblUsuario = new JLabel("Usuario:");
        txtUsr = new JTextField(10);
        pfPasswd = new JPasswordField(10);

        lblUsuario.setHorizontalAlignment(JTextField.LEFT);

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = 0;
        grid.gridy = 0;
        //grid.ipadx = 2;
        grid.insets = new Insets(0,0,5,5);
        panel.add(lblUsuario,grid);

        grid.gridx = 1;
        grid.gridy = 0;
        //grid.insets = new Insets(0,0,1,1);
        panel.add(txtUsr, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        //grid.ipadx = 2;
        //grid.insets = new Insets(0,0,10,10);
        panel.add(lblPasswd,grid);

        grid.gridx = 1;
        grid.gridy = 1;
        //grid.ipadx = 2;
        //grid.insets = new Insets(0,0,5,5);
        panel.add(pfPasswd,grid);

        btnLogin = new JButton("Login");
        btnCerrar = new JButton("Cerrar");

        grid.gridx = 0;
        grid.gridy = 2;
        grid.insets = new Insets(5,0,0,0);
        panel.add(btnLogin,grid);

        grid.gridx = 1;
        grid.gridy = 2;
        //grid.insets = new Insets(0,5,0,0);
        panel.add(btnCerrar,grid);

        add(panel);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean connected=false;
                TUser c = new TUser(txtUsr.getText(), new String(pfPasswd.getPassword()));
                sh.sendObject(c);
                connected=sh.receiveBool();
                if(connected){
                    SimpleChatWindow w = new SimpleChatWindow();
                    w.setSh(sh);
                    w.setC(c);
                    w.launchListener();
                    w.setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudo realizar la conexion");
                    System.exit(0);
                }
            }
        });
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void setSh(SocketHandler sh){
        this.sh=sh;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login lg = new Login();

                lg.setVisible(true);
            }
        });

    }
}
