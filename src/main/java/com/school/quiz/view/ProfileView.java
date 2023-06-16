package com.school.quiz.view;

import org.apache.commons.text.WordUtils;

import com.school.quiz.view.Theme.GlassPanel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ProfileView extends GlassPanel {

    private ArrayList<String[]> userData;
    private JPanel userCard;

    public ProfileView(ArrayList<String[]> userdata) {
        this.userData = userdata;

        setLayout(null);
        setBounds(250, 170, 1300, 680);

        // Create the card panel
        userCard = new JPanel();
        userCard.setBackground(new Color(75, 159, 150)); // Specify the custom background color
        userCard.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150)));
        userCard.setBounds(50, 120, 700, 420); // Adjust the size and position as needed
        userCard.setLayout(null); // Use null layout for manual component positioning

        // Create the heading label
        JLabel headingLabel = new JLabel("PROFILE");
        headingLabel.setBounds(0, 5, 700, 40); // Adjust the position and size as needed
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        userCard.add(headingLabel);

        // Create labels for the fields
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(30, 50, 100, 30);
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(idLabel);

        JLabel idText = new JLabel();
        idText.setBounds(140, 50, 150, 30);
        idText.setForeground(Color.WHITE);
        idText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(idText);

        // Create labels for the fields
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(30, 90, 100, 30);
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(firstNameLabel);

        JLabel firstNameText = new JLabel();
        firstNameText.setBounds(140, 90, 150, 30);
        firstNameText.setForeground(Color.WHITE);
        firstNameText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(firstNameText);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(350, 90, 100, 30);
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(lastNameLabel);

        JLabel lastNameText = new JLabel();
        lastNameText.setBounds(460, 90, 150, 30);
        lastNameText.setForeground(Color.WHITE);
        lastNameText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(lastNameText);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 130, 100, 30);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(usernameLabel);

        JLabel usernameText = new JLabel();
        usernameText.setBounds(140, 130, 150, 30);
        usernameText.setForeground(Color.WHITE);
        usernameText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(usernameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 170, 100, 30);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(emailLabel);

        JLabel emailText = new JLabel();
        emailText.setBounds(140, 170, 200, 30);
        emailText.setForeground(Color.WHITE);
        emailText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(emailText);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 210, 100, 30);
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(ageLabel);

        JLabel ageText = new JLabel();
        ageText.setBounds(140, 210, 150, 30);
        ageText.setForeground(Color.WHITE);
        ageText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(ageText);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 250, 100, 30);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(roleLabel);

        JLabel roleText = new JLabel();
        roleText.setBounds(140, 250, 150, 30);
        roleText.setForeground(Color.WHITE);
        roleText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(roleText);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 290, 100, 30);
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(addressLabel);

        JLabel addressText = new JLabel();
        addressText.setBounds(140, 290, 150, 30);
        addressText.setForeground(Color.WHITE);
        addressText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(addressText);

        JLabel phoneLabel = new JLabel("Phone No. :");
        phoneLabel.setBounds(30, 330, 100, 30);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userCard.add(phoneLabel);

        JLabel phoneText = new JLabel();
        phoneText.setBounds(140, 330, 150, 30);
        phoneText.setForeground(Color.WHITE);
        phoneText.setFont(new Font("Arial", Font.PLAIN, 14));
        userCard.add(phoneText);

        for (String data[] : userData) {
            idText.setText(data[0]);
            firstNameText.setText(WordUtils.capitalize(data[1]));
            lastNameText.setText(WordUtils.capitalize(data[2]));
            usernameText.setText(WordUtils.capitalize(data[3]));
            emailText.setText(WordUtils.capitalize(data[4]));
            ageText.setText("not set");
            roleText.setText(WordUtils.capitalize(data[5]));
            addressText.setText("not set");
            phoneText.setText("not set");
        }

        // Add the card panel to the ProfileView

        add(userCard);

    }
}
