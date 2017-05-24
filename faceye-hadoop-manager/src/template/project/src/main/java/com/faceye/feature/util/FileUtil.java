package com.faceye.feature.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;

public class FileUtil
{
	public static boolean upload(File file, String path, String newFileName)
	{
		InputStream is = null;
		OutputStream os = null;
		boolean result = false;
		try
		{
			File dir = new File(path);
			if (!dir.exists())
			{
				dir.mkdirs();
			}
			File destFile = new File(path, newFileName);
			if (destFile.exists())
			{
				destFile.delete();
				destFile = new File(path, newFileName);
			}
			if (!destFile.exists())
			{
				destFile.createNewFile();
			}
			is = new FileInputStream(file);
			os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			while ((length = is.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
			result = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (null != is)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (null != os)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static final String file = "f:/print.txt";

	public static void write(String fileName, String text) throws IOException
	{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		out.println(text);
		out.close();
	}

	public static void write(String text)
	{
		try
		{
			write(file, text);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String[] readText(String path)
	{
		if (path == null)
		{
			path = file;
		}
		String[] result = null;
		String str = readTextFile(path);
		result = str.split("\n");
		return result;
	}

	public static String readTextFile(String path)
	{
		String text = "";
		StringBuffer buffer = new StringBuffer();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(path));
			String s;
			while ((s = in.readLine()) != null)
			{
				buffer.append(s);
				buffer.append("\n");
			}
			in.close();
			buffer.append("\n");
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		text = buffer.toString();
		return text;
	}
}
