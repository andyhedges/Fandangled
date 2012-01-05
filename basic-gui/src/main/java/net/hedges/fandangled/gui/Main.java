package net.hedges.fandangled.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.hedges.fandangled.bindings.builder.InterfaceBuilder;
import net.hedges.fandangled.bindings.domain.Interface;
import net.hedges.fandangled.codec.docx.DocxCodec;

public class Main extends JFrame {

	private JPanel contentPane;
	private final Action openAction = new OpenFile();
	private File idlFile = null;

	/**
	 * This whole thing is a hack. I just wanted to make it easier for users to get started with IDL and documentation
	 * TODO: tidy this up a lot
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextArea editor = null;
	private JTextArea console = null;
	private final Action generateAction = new GenerateAction();
	private final Action clearAction = new ClearAction();
	private final Action saveAction = new SaveAction();
	private JProgressBar generateProgress;

	public Main() {
		setTitle("Fandangled Extremely Basic GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(609, 464);
		setLocationByPlatform(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(openAction);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAction(saveAction);
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setOneTouchExpandable(true);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(new TitledBorder(null, "Console",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(consolePanel);
		consolePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane consoleScrollPane = new JScrollPane();
		consolePanel.add(consoleScrollPane, BorderLayout.CENTER);

		console = new Console();
		console.setFont(new Font("Courier New", console.getFont().getStyle(), console.getFont().getSize()));
		consoleScrollPane.setViewportView(console);

		JPanel panel_1 = new JPanel();
		consolePanel.add(panel_1, BorderLayout.SOUTH);

		JButton clear = new JButton("Clear");
		clear.setAction(clearAction);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_1.createSequentialGroup()
						.addContainerGap(512, Short.MAX_VALUE)
						.addComponent(clear)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(clear)));
		panel_1.setLayout(gl_panel_1);

		JPanel editorPanel = new JPanel();
		editorPanel.setBorder(new TitledBorder(null, "Editor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(editorPanel);
		editorPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane editorScrollPane = new JScrollPane();
		editorPanel.add(editorScrollPane, BorderLayout.CENTER);

		editor = new JTextArea();
		editor.setFont(new Font("Courier New", editor.getFont().getStyle(), editor.getFont().getSize()));

		editorScrollPane.setViewportView(editor);

		JPanel panel_3 = new JPanel();
		editorPanel.add(panel_3, BorderLayout.SOUTH);

		JButton generate = new JButton("Generate");
		generate.setAction(generateAction);
		
		generateProgress = new JProgressBar();
		generateProgress.setIndeterminate(true);
		generateProgress.setString("generating…");
		generateProgress.setVisible(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(340, Short.MAX_VALUE)
					.addComponent(generateProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generate))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(generateProgress, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(generate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
	}

	private void open(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder text = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			text.append(line + "\n");
		}
		editor.setText(text.toString());
		br.close();
	}

	private class OpenFile extends AbstractAction {
		public OpenFile() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open IDL");
		}

		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(Main.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					Main.this.open(file);
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(Main.this, ioe.getMessage(),
							"Couldn't read file", JOptionPane.ERROR_MESSAGE);
				}
				idlFile = file;
			}
		}
	}

	private class GenerateAction extends AbstractAction {
		public GenerateAction() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Generate Fandangled Output");
		}

		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showOpenDialog(Main.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (file.exists()) {
					int ret = JOptionPane
							.showConfirmDialog(
									Main.this,
									"This file exists are you sure you wish to overwrite?",
									"Overwrite?!", JOptionPane.WARNING_MESSAGE);
					if (ret != JOptionPane.OK_OPTION) {
						return;
					}
				}
				if (!file.isDirectory()) {
					JOptionPane.showMessageDialog(Main.this,
							"This is not a directory", "Not a directory",
							JOptionPane.ERROR_MESSAGE);
				}
				
				SwingWorker<Void, Void> sw = new GenerateWorker(file);
				sw.execute();
			}
		}
	}

	private class GenerateWorker extends SwingWorker<Void, Void>{
		
		private File file = null;

		public GenerateWorker(File file){
			this.file = file;
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			Main.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			generateProgress.setVisible(true);
			generateProgress.setValue(50);
			generate(file);
			Main.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return null;
		}
		
		protected void done(){
			generateProgress.setVisible(false);
		}
		
	}
	
	private  void generate(File file) {
		try {
			DocxCodec codec = new DocxCodec();
			ByteArrayInputStream in = new ByteArrayInputStream(editor.getText()
					.getBytes());
			Interface interf = InterfaceBuilder.parse(in);
			codec.encode(interf, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ClearAction extends AbstractAction {
		public ClearAction() {
			putValue(NAME, "Clear");
			putValue(SHORT_DESCRIPTION, "Clear the console");
		}
		public void actionPerformed(ActionEvent e) {
			console.setText("");
		}
	}
	private class SaveAction extends AbstractAction {
		public SaveAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Save Action");
		}
		public void actionPerformed(ActionEvent e) {
			try{
				Main.this.save();
			} catch(IOException ioe){
				JOptionPane.showMessageDialog(Main.this,
						"Couldn't save file", "Couldn't save file",
						JOptionPane.ERROR_MESSAGE);	
			}
		}
	}

	public void save() throws IOException {
		FileWriter fileWriter = new FileWriter(idlFile);
		fileWriter.write(editor.getText());
		fileWriter.close();
	}
}
