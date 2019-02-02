package com.test.query;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class TestSelectFile {
	public static void main(String[] args) {
		File[] listRoots = File.listRoots();
		String keyword = "tes";
		for (File file : listRoots) {
			printAbsolutePath(file, keyword);
		}
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
