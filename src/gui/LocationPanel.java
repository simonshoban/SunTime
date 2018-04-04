package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import data.WebAddress;
import toolkit.Toolkit;

public class LocationPanel extends SunTimePanel {
    private JTextField city;
    private JTextField country;
    private JButton submit;
    
    public LocationPanel(MainContainer container) {
        city = new JTextField("Lima");
        country = new JTextField("Peru");
        submit = new JButton("Submit");
        submit.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                WebAddress newLocation = new WebAddress(
                        Toolkit.DOMAIN, 
                        city.getText(),
                        country.getText()
                    );
                
                container.scrapeNewInfo(newLocation);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) { 
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
        
        add(city, BorderLayout.NORTH);
        add(country, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
    }
    
    public void resetAllFields() {
        
    }
    
    public void resetField() {
        
    }
}
