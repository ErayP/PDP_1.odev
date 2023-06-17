/**
*
* @author ERAY POLAT mail: eray.polat@ogr.sakarya.edu.tr
* @since 17.04.2023
* @group 1-A
* 
*/
package odev;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stubz
		String dosyaString = args[0];
		File file = new File(args[0]);
		FileWriter javadocFile = new FileWriter("javadoc.txt");
		FileWriter cokluFile = new FileWriter("cokluyorum.txt");
		FileWriter tekFile = new FileWriter("tekyorum.txt");
		BufferedWriter javaDocWriter =new BufferedWriter(javadocFile);
		BufferedWriter cokluWriter =new BufferedWriter(cokluFile);
		BufferedWriter tekliWriter =new BufferedWriter(tekFile);
		Scanner okuma = new Scanner(file); 
		String metinOku = "";
		while (okuma.hasNextLine()){
			metinOku = metinOku.concat(okuma.nextLine()+"\n");
		}
		int tekYorum_say=0;
		int cokluYorum_say=0;
		int javaDoc_say=0;
		String[] bolunmusMetin = metinOku.split("(?m)(?<=\\})\\s*(?=\\n|\\z)");
		Pattern funPattern = Pattern.compile("(\\w+)(?=\\()");
		Pattern tekYorumPattern = Pattern.compile("((\\/\\/\\s*)(\\w+.*))");
		Pattern cokluYorumPattern = Pattern.compile("(\\/\\*(\\n)?)(.*(\\n)?)(\\*\\/(\\n)?)");
		Pattern javaDocPattern = Pattern.compile("\\/\\*\\*[\\s\\S]*?\\*\\/");
		Pattern funcStrtPattern =Pattern.compile("(\\w+)\\((.*)?(\\)(\\s)?)(\\{)");
		Pattern classPattern = Pattern.compile("(?<=class )(.*)(?=\\{)");
		for(int sayac =0;sayac<bolunmusMetin.length;sayac++) {
			Matcher matcherFuncMatcher = funPattern.matcher(bolunmusMetin[sayac]);
			Matcher tekYorumMatcher = tekYorumPattern.matcher(bolunmusMetin[sayac]);
			Matcher cokluYoruMatcher = cokluYorumPattern.matcher(bolunmusMetin[sayac]);
			Matcher javaDocMatcher = javaDocPattern.matcher(bolunmusMetin[sayac]);
			Matcher funcStrtMatcher = funcStrtPattern.matcher(bolunmusMetin[sayac]);
			Matcher classMatcher = classPattern.matcher(bolunmusMetin[sayac]);
			if(classMatcher.find()) {
				System.out.println("Sinif: "+classMatcher.group());
			}
			if(matcherFuncMatcher.find()) {
				System.out.println("\tFonksiyon: "+matcherFuncMatcher.group());
				tekYorum_say=0;
				cokluYorum_say=0;
			}
			while(tekYorumMatcher.find()) {
				tekYorum_say++;
				tekliWriter.write("Fonksiyon :"+matcherFuncMatcher.group()+"\n"+tekYorumMatcher.group()+"\n-----------------\n");
			}
			while(cokluYoruMatcher.find()) {
				cokluYorum_say++;
				cokluWriter.write("Fonksiyon :"+matcherFuncMatcher.group()+"\n"+cokluYoruMatcher.group()+"\n-----------------\n");
			}
			while(javaDocMatcher.find()) {
				javaDoc_say++;
				javaDocWriter.write("Fonksiyon :"+matcherFuncMatcher.group()+"\n"+javaDocMatcher.group()+"\n-----------------\n");
			}
			if(funcStrtMatcher.find()) {
				System.out.println("\t\tTek satir yorum sayisi:     "+tekYorum_say);
				System.out.println("\t\tCok satirli yorum sayisi:   "+cokluYorum_say);
				System.out.println("\t\tJavadoc yorum sayisi:       "+javaDoc_say);
				System.out.println("---------------------------------------------");
				tekYorum_say=0;
				cokluYorum_say=0;
				javaDoc_say=0;
			}
		}
		javaDocWriter.close();
		tekliWriter.close();
		cokluWriter.close();
	}
}
			
