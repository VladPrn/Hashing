package edu.vladprn.java;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class App {
	private JFrame frame;
	private JLabel lblStatus;
	private JComboBox<String> cmbMethod;
	
	private File fileDict;
	private File fileInput;
	private File fileOutput;
	private JTextField textCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Хеширование");
		frame.setBounds(100, 100, 280, 249);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDictionaryPath = new JLabel("Файл словаря:");
		lblDictionaryPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDictionaryPath.setBounds(10, 11, 121, 24);
		frame.getContentPane().add(lblDictionaryPath);
		
		JLabel lblTextPath = new JLabel("Текстовый файл:");
		lblTextPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTextPath.setBounds(10, 46, 121, 24);
		frame.getContentPane().add(lblTextPath);
		
		JButton btnDictionaryPath = new JButton("select");
		btnDictionaryPath.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FileDialog fd = new FileDialog(frame);
				fd.show();
				btnDictionaryPath.setLabel(fd.getFile());
				fileDict = new File(fd.getDirectory() + fd.getFile());
			}
		});
		btnDictionaryPath.setBounds(165, 14, 89, 23);
		frame.getContentPane().add(btnDictionaryPath);
		
		JButton btnFilePath = new JButton("select");
		btnFilePath.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog fd = new FileDialog(frame);
				fd.show();
				btnFilePath.setLabel(fd.getFile());
				fileInput = new File(fd.getDirectory() + fd.getFile());
			}
		});
		btnFilePath.setBounds(165, 49, 89, 23);
		frame.getContentPane().add(btnFilePath);
		
		JButton btnStart = new JButton("Начать");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start();
			}
		});
		btnStart.setBounds(10, 183, 89, 23);
		frame.getContentPane().add(btnStart);
		
		JLabel lblResultPath = new JLabel("Выходной файл:");
		lblResultPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultPath.setBounds(10, 82, 121, 24);
		frame.getContentPane().add(lblResultPath);
		
		JButton btnOutputPath = new JButton("select");
		btnOutputPath.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame);
				fd.show();
				btnOutputPath.setLabel(fd.getFile());
				fileOutput = new File(fd.getDirectory() + fd.getFile());
			}
		});
		btnOutputPath.setBounds(165, 85, 89, 23);
		frame.getContentPane().add(btnOutputPath);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(109, 183, 145, 14);
		frame.getContentPane().add(lblStatus);
		
		cmbMethod = new JComboBox<String>();
		cmbMethod.setModel(new DefaultComboBoxModel<String>(new String[] {"Метод цепочек", "Метод открытой адресации", "Метод двойного хэширования"}));
		cmbMethod.setSelectedIndex(0);
		cmbMethod.setBounds(10, 117, 244, 20);
		frame.getContentPane().add(cmbMethod);
		
		JLabel lblCount = new JLabel("Количество вызовов:");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCount.setBounds(10, 148, 145, 24);
		frame.getContentPane().add(lblCount);
		
		textCount = new JTextField();
		textCount.setBounds(168, 152, 86, 20);
		frame.getContentPane().add(textCount);
		textCount.setColumns(10);
	}
	
	/**
	 * Start of general  logic of program
	 */
	private void start() {
		CollisionMethod md = null;
		switch (cmbMethod.getSelectedIndex()) {
			case 0: md = CollisionMethod.CHAINS_METHOD; break;
			case 1: md = CollisionMethod.OPEN_ADRESSING_METHOD; break;
			case 2: md = CollisionMethod.DOUBLE_HASHING_METHOD; break;
		}
		
		int count;
		try {
			count = Integer.parseInt(textCount.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "В поле количество вызовов должно находится число!");
			return;
		}
		
		if (processingFile(md) == -1) {
			return;
		}
		
		long timeLeft = 0;
		for (int i = 0; i < count; i++) {
			long timeExecute = processingFile(md);
			if (timeExecute == -1) {
				return;
			}
			timeLeft += timeExecute;
		}
		lblStatus.setText("Завершено (" + timeLeft + ")");
	}
	
	/**
	 * 
	 * @param md the method of collision resolution
	 * @return the time of work in milliseconds <br>
	 * return -1 unless error 
	 */
	private long processingFile(CollisionMethod md) {
		try {
			if (fileDict == null || !fileDict.canRead()) {
				throw new FileNotFoundException("Проверьте правильность указания пути к файлу словаря!");
			}
			if (fileInput == null || !fileInput.canRead()) {
				throw new FileNotFoundException("Проверьте правильность указания пути к входному файлу!");
			}
			if (fileOutput == null || !fileOutput.canRead()) {
				throw new FileNotFoundException("Проверьте правильность указания пути к выходному файлу!");
			}
			
			long timeBegin = System.currentTimeMillis();
			
			Dictionary dict = new Dictionary(md);
			dict.loadDictionary(fileDict);
			BufferedReader br = new BufferedReader(new FileReader(fileInput));
			PrintWriter pw = new PrintWriter(new FileWriter(fileOutput));
			
			char ch;
			boolean is_letter = false;
			StringBuilder word = new StringBuilder("");
			
			while (br.ready())
			{
				ch = (char) br.read();
				if (Character.isAlphabetic(ch)) {
					if (!is_letter) {
						word.setLength(0);
					}
					is_letter = true;
					word.append(ch);
				}
				else {
					if (is_letter) {
						if (dict.contains(word.toString())) {
							pw.print("<" + word + ">");
						}
						else {
							pw.print(word);
						}
					}
					is_letter = false;
					pw.print(ch);
				}
			}
			
			br.close();
			pw.close();
			
			return System.currentTimeMillis() - timeBegin;
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return -1;
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Ошибка при чтении файла!");
			return -1;
		}
	}
}