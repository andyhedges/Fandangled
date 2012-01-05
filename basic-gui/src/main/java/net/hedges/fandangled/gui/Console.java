package net.hedges.fandangled.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class Console extends JTextArea{
	
	public Console(){
		PrintStream ps = new PrintStream(new ConsoleOutputStream());
		System.setErr(ps);
		System.setOut(ps);
	}
	
	private class ConsoleOutputStream extends OutputStream{

		@Override
		public void write(int c) throws IOException {
			Console.this.append((char)c + "");
		}
		
	}

}
