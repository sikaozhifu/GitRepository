package com.test.query;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SelectFile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectFile frame = new SelectFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("文件名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 129, 87, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 14));
		textField.setBounds(249, 130, 146, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查找");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String keyword = textField.getText();
				File[] listRoots = File.listRoots();//获取所有盘符
				for (File file : listRoots) {
					printAbsolutePath(file, keyword);//打印所有匹配文件的绝对路径
				}
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton.setBounds(488, 129, 92, 30);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("查找文件");
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(260, 34, 177, 39);
		contentPane.add(label);
	}
	
	/**
	 * 打印所有匹配的文件路径
	 * @param file
	 * @param keyword
	 */
	public static void printAbsolutePath(File file,String keyword) {
		List<File> list = searchFiles(file, keyword);
		for (File f : list) {
			System.out.println(f.getAbsolutePath());
		}
	}
	
	/**
	 * 查找匹配文件
	 * @param file
	 * @param keyword
	 * @return
	 */
	public static List<File> searchFiles(File file, final String keyword){
		List<File> result = new ArrayList<File>();
		if (file.isFile()) {
			result.add(file);
		}
		File[] listFiles = file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return true;
				}
				if (pathname.getName().toLowerCase().contains(keyword)) {
					return true;
				}
				return false;
			}
		});
		
		if (listFiles != null) {
			for (File f : listFiles) {
				if (f.isFile()) {
					result.add(f);
				}else {
					result.addAll(searchFiles(f, keyword));
				}
			}
		}
		return result;
	}
}
