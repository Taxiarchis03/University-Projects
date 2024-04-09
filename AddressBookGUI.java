import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AddressBookGUI extends JFrame {
    private ArrayList<Contact> contacts;
    private DefaultListModel<String> contactListModel;
    private JList<String> contactList;
    private JButton addButton;
    private JButton deleteButton;

    public AddressBookGUI() {
        contacts = new ArrayList<>();
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        addButton = new JButton("Προσθήκη");
        deleteButton = new JButton("Διαγραφή");

        // Διαμόρφωση του παραθύρου
        setTitle("Address Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Διαμόρφωση του κεντρικού panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Διαμόρφωση του panel για τη λίστα επαφών
        JPanel listPanel = new JPanel();
        listPanel.setBorder(BorderFactory.createTitledBorder("Επαφές"));
        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        // Διαμόρφωση του panel για τα κουμπιά
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Σύνδεση των κουμπιών με τις αντίστοιχες λειτουργίες
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        // Φόρτωση των επαφών από το αρχείο κειμένου
        loadContacts();
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog(this, "Όνομα:");
        if (name != null && !name.isEmpty()) {
            String phoneNumber = JOptionPane.showInputDialog(this, "Τηλέφωνο:");
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                String address = JOptionPane.showInputDialog(this, "Διεύθυνση:");
                if (address != null && !address.isEmpty()) {
                    Contact contact = new Contact(name, phoneNumber, address);
                    contacts.add(contact);
                    contactListModel.addElement(contact.getName());
                    saveContacts();
                }
            }
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            contacts.remove(selectedIndex);
            contactListModel.remove(selectedIndex);
            saveContacts();
        }
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("abook.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    String address = parts[2].trim();
                    Contact contact = new Contact(name, phoneNumber, address);
                    contacts.add(contact);
                    contactListModel.addElement(contact.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("abook.txt"))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Contact {
        private String name;
        private String phoneNumber;
        private String address;

        public Contact(String name, String phoneNumber, String address) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddress() {
            return address;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressBookGUI().setVisible(true);
            }
        });
    }
}
