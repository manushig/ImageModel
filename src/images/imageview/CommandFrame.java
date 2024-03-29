package images.imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * CommandFrame, a JFrame to take batch script input.
 *
 */
public class CommandFrame extends JFrame implements ActionListener {

  /**
   * It is the generated serial version id.
   */
  private static final long serialVersionUID = -2931950600634961973L;

  private JTextArea commandTextArea;
  private JButton executeButton;
  private ImageViewInterface parentFrame;

  /**
   * Constructs a CommandFrame.
   * 
   * @param parentFrame It is the parent ImageViewImp frame.
   */
  public CommandFrame(ImageViewInterface parentFrame) {
    Objects.requireNonNull(parentFrame);
    this.parentFrame = parentFrame;
    this.setSize(200, 200); // Sets the x and y dimension of the frame
    this.setLocation(150, 150);
    this.setTitle("Batch Script Commands");
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

    this.setLayout(new BorderLayout());

    JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    commandPanel.setLayout(new BorderLayout(10, 10));

    commandTextArea = new JTextArea();

    JScrollPane scrollPane = new JScrollPane(commandTextArea,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(850, 450));

    commandPanel.add(scrollPane, BorderLayout.NORTH);

    executeButton = new JButton();
    executeButton.setText("Execute");
    executeButton.addActionListener(this);
    executeButton.setEnabled(true);

    commandPanel.add(executeButton, BorderLayout.SOUTH);

    this.add(commandPanel);

    pack();
    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent event) {
    Objects.requireNonNull(event);
    if (event.getSource() == executeButton) {
      this.dispose();
      parentFrame.setCommandText(commandTextArea.getText());
    }

  }
}
