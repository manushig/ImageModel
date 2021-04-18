package images.imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AddTextFrame extends JFrame implements ActionListener {

  private String buttonsString;
  private JButton buttonList[];
  private String buffer;
  private JTextField text;
  private JButton spaceBar;
  private JButton backSpace;
  private JButton enter;
  private JButton clearAll;
  private JButton addText;
  private JPanel panel;
  private JPanel buttonPanel;
  private JPanel textPanel;

  private ImageViewInterface parentFrame;

  public AddTextFrame(ImageViewInterface parentFrame) {
    this.parentFrame = parentFrame;
    this.setSize(200, 200); // Sets the x and y dimension of the frame
    this.setLocation(150, 150);
    this.setTitle("Add Text");
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

    buffer = "";

    panel = new JPanel(new BorderLayout(10, 10));
    panel.setPreferredSize(new Dimension(400, 400));

    buttonsString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.,-!?";

    textPanel = new JPanel(new BorderLayout());

    JLabel note = new JLabel();
    note.setText("Note: Longer text will be truncated if it doesn't fit");
    textPanel.add(note, BorderLayout.NORTH);

    JTextArea t = new JTextArea();
    text = new JTextField(100);
    text.setActionCommand("" + buffer);
    text.setEditable(false);
    text.setPreferredSize( new Dimension( 200, 24 ) );
    textPanel.add(text, BorderLayout.SOUTH);

    panel.add(textPanel, BorderLayout.NORTH);

    buttonPanel = new JPanel();

    int number = buttonsString.length();
    buttonList = new JButton[number];

    for (int i = 0; i < number; i++) {
      buttonList[i] = new JButton("" + buttonsString.charAt(i));
      buttonPanel.add(buttonList[i]);
      buttonList[i].addActionListener(this);
    }

    spaceBar = new JButton("Space Bar");
    buttonPanel.add(spaceBar);
    spaceBar.addActionListener(this);

    backSpace = new JButton("Back Space");
    buttonPanel.add(backSpace);
    backSpace.addActionListener(this);

    clearAll = new JButton("Clear All");
    buttonPanel.add(clearAll);
    clearAll.addActionListener(this);

    enter = new JButton("Enter");
    buttonPanel.add(enter);
    enter.addActionListener(this);

    panel.add(buttonPanel, BorderLayout.CENTER);

    addText = new JButton("Add Text");
    panel.add(addText, BorderLayout.SOUTH);
    addText.addActionListener(this);

    this.add(panel);
    pack();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    int number = buttonsString.length();
    if (event.getSource() == backSpace) {
      buffer = buffer.substring(0, buffer.length() - 1);
      text.setText("" + buffer);
    } else if (event.getSource() == spaceBar) {
      buffer += " ";
      text.setText("" + buffer);
    } else if (event.getSource() == enter) {
      buffer += "\n";
      text.setText("" + buffer);
    } else if (event.getSource() == clearAll) {
      buffer = "";
      text.setText("" + buffer);
    } else if (event.getSource() == addText) {
      this.dispose();
      parentFrame.setAddTextToPattern(buffer.toString());
    } else {
      for (int i = 0; i < number; i++) {
        if (event.getSource() == buttonList[i]) {
          buffer += buttonsString.charAt(i);
          text.setText("" + buffer);
          break;
        }
      }
    }

  }
}