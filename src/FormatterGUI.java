import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;

public class FormatterGUI {

	private JFrame frmOqFormatter;
	private JTextArea txtrAnswer;
	private JTextArea txtrQuestion;
	private JTextArea txtrHashtags;
	private JTextArea txtrOutput;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormatterGUI window = new FormatterGUI();
					window.frmOqFormatter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormatterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmOqFormatter = new JFrame();
		frmOqFormatter.setTitle("OQ Formatter");
		frmOqFormatter.setBounds(100, 100, 450, 300);
		frmOqFormatter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		frmOqFormatter.getContentPane().setLayout(gridBagLayout);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frmOqFormatter.getContentPane().add(scrollPane, gbc_scrollPane);

		txtrQuestion = new JTextArea();
		scrollPane.setViewportView(txtrQuestion);
		txtrQuestion.setWrapStyleWord(true);
		txtrQuestion.setLineWrap(true);
		txtrQuestion.setText("Question");
		txtrQuestion.addKeyListener(new EnterListener());

		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridheight = 3;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 0;
		frmOqFormatter.getContentPane().add(scrollPane_3, gbc_scrollPane_3);

		txtrOutput = new JTextArea();
		scrollPane_3.setViewportView(txtrOutput);
		txtrOutput.setLineWrap(true);
		txtrOutput.setWrapStyleWord(true);

		txtrOutput.setText("Output");

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		frmOqFormatter.getContentPane().add(scrollPane_1, gbc_scrollPane_1);

		txtrAnswer = new JTextArea();
		scrollPane_1.setViewportView(txtrAnswer);
		txtrAnswer.setWrapStyleWord(true);
		txtrAnswer.setLineWrap(true);
		txtrAnswer.setText("Answer");
		txtrAnswer.addKeyListener(new EnterListener());

		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 2;
		frmOqFormatter.getContentPane().add(scrollPane_2, gbc_scrollPane_2);

		txtrHashtags = new JTextArea();
		scrollPane_2.setViewportView(txtrHashtags);
		txtrHashtags.setWrapStyleWord(true);
		txtrHashtags.setLineWrap(true);
		txtrHashtags.setText("Hashtags (Separate by spaces)");
		txtrHashtags.addKeyListener(new EnterListener());

		scrollPane.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane_1.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane_2.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane_3.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		scrollPane.addKeyListener(new EnterListener());
		scrollPane_1.addKeyListener(new EnterListener());
		scrollPane_2.addKeyListener(new EnterListener());
		scrollPane_3.addKeyListener(new EnterListener());

	}

	public class EnterListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			FormatWorker formatWorker = new FormatWorker();
			formatWorker.execute();

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	public class FormatWorker extends SwingWorker<String, Void> {

		@Override
		protected String doInBackground() throws Exception {
			Formatter formatter = new Formatter();
			formatter.setQuestion(txtrQuestion.getText());
			formatter.setAnswer(txtrAnswer.getText());
			String[] hashtags = txtrHashtags.getText().split(" ");
			formatter.setHashtag(hashtags);
			try {
				formatter.format();
			} catch (StringIndexOutOfBoundsException e) {
				
			}

			return formatter.getPost();
		}

		protected void done() {
			try {
				String post = get();

				txtrOutput.setText(post);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
